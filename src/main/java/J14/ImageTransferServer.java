package J14;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ImageTransferServer {
    private static final int PORT = 8080;
    private static final String IMAGE_FOLDER = "src/images";
    private static final String WEB_ROOT = "src/web";
    private static List<File> images;
    private static Map<String, String> users = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        images = loadImages(IMAGE_FOLDER);
        if (images.isEmpty()) {
            System.out.println("Нет изображений для отправки.");
            return;
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        System.out.println("Сервер запущен на http://localhost:" + PORT);
        System.out.println("Регистрация: http://localhost:" + PORT + "/register");


        server.createContext("/register", exchange -> {
            System.out.println("Получен запрос к /register: " + exchange.getRequestMethod());
            try {
                if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                    String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Регистрация</title>
                </head>
                <body>
                <h1>Регистрация пользователя</h1>
                <form method="POST" action="/register">
                    <label for="username">Имя пользователя:</label>
                    <input type="text" id="username" name="username" required>
                    <button type="submit">Зарегистрироваться</button>
                </form>
                </body>
                </html>
                """;
                    sendResponse(exchange, html, "text/html");
                } else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                    String requestBody = new String(exchange.getRequestBody().readAllBytes());

                    String username = parseParameter(requestBody, "username");

                    if (username != null && !username.trim().isEmpty()) {

                        if (users == null) {
                            System.err.println("Ошибка: users не инициализирован!");
                        } else {
                            System.out.println("Добавляем пользователя: " + username);
                        }
                        users.put(username, username);
                        exchange.getResponseHeaders().add("Location", "/inbox?username=" + username);
                        exchange.sendResponseHeaders(302, -1); // Код 302 для перенаправления
                        System.out.println("Пользователь подключился: " + username);
                    } else {
                        sendResponse(exchange, "Некорректное имя пользователя.", "text/plain");
                    }
                } else {
                    sendResponse(exchange, "Метод не поддерживается.", "text/plain");
                }
            } catch (Exception e) {
                System.err.println("Ошибка в обработке запроса: " + e);
                e.printStackTrace();
                exchange.sendResponseHeaders(500, -1);
            }
        });


        server.createContext("/", exchange -> {
            StringBuilder response = new StringBuilder("<html><body><h1>Выбор изображения</h1>");

            response.append("<h2>Активные пользователи</h2><ul>");
            for (String user : users.keySet()) {
                response.append("<li>").append(user).append("</li>");
            }
            response.append("</ul>");

            response.append("<h2>Доступные изображения</h2><ul>");
            for (int i = 0; i < images.size(); i++) {
                File image = images.get(i);
                response.append("<li>")
                        .append("<form method=\"POST\" action=\"/send\">")
                        .append("<input type=\"hidden\" name=\"imageId\" value=\"").append(i).append("\"/>")
                        .append("Кому отправить: ")
                        .append("<input type=\"text\" name=\"recipient\" placeholder=\"Имя пользователя\" required/>")
                        .append(image.getName())
                        .append(" <button type=\"submit\">Отправить</button>")
                        .append("</form>")
                        .append("</li>");
            }
            response.append("</ul></body></html>");
            sendResponse(exchange, response.toString(), "text/html");
        });

        server.createContext("/send", exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                String requestBody = new String(exchange.getRequestBody().readAllBytes());
                String imageIdStr = parseParameter(requestBody, "imageId");
                String recipient = parseParameter(requestBody, "recipient");

                try {
                    int imageId = Integer.parseInt(imageIdStr);
                    File image = images.get(imageId);

                    if (users.containsKey(recipient)) {
                        users.put(recipient, image.getName());
                        sendResponse(exchange, "Изображение отправлено пользователю " + recipient, "text/plain");
                        System.out.println("Изображение " + image.getName() + " отправлено пользователю " + recipient);
                    } else {
                        sendResponse(exchange, "Пользователь " + recipient + " не найден.", "text/plain");
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    sendResponse(exchange, "Неверный ID изображения.", "text/plain");
                }
            } else {
                sendResponse(exchange, "Метод не поддерживается.", "text/plain");
            }
        });

        server.createContext("/inbox", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            String username = parseParameter(query, "username");

            if (username != null && users.containsKey(username)) {
                String imageName = users.get(username);
                if (imageName != null) {
                    users.put(username, username);
                    File image = new File(IMAGE_FOLDER, imageName);

                    exchange.getResponseHeaders().add("Content-Type", "image/jpeg");
                    exchange.sendResponseHeaders(200, image.length());

                    try (OutputStream os = exchange.getResponseBody()) {
                        Files.copy(image.toPath(), os);
                    }
                } else {
                    sendResponse(exchange, "Нет новых изображений.", "text/plain");
                }
            } else {
                sendResponse(exchange, "Пользователь не найден.", "text/plain");
            }
        });

        server.setExecutor(null);
        server.start();


        System.out.println("Текущая рабочая директория: " + new File(".").getAbsolutePath());

    }

    private static List<File> loadImages(String folderPath) {
        List<File> images = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile() && isImage(file)) {
                    images.add(file);
                }
            }
        } else {
            System.err.println("Папка " + folderPath + " не существует или не является директорией.");
        }
        return images;
    }

    private static boolean isImage(File file) {
        String[] extensions = {"jpg", "jpeg", "png", "gif"};
        String name = file.getName().toLowerCase();
        for (String ext : extensions) {
            if (name.endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }

    private static void sendResponse(HttpExchange exchange, String response, String contentType) throws IOException {
        byte[] responseBytes = response.getBytes();
        exchange.getResponseHeaders().set("Content-Type", contentType + "; charset=UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

    private static String parseParameter(String query, String parameter) {
        if (query == null || parameter == null) {
            return null;
        }
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length == 2 && parameter.equals(pair[0])) {
                return pair[1];
            }
        }
        return null;
    }


    private static void handleRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();

        if ("/".equals(path) || "/index.html".equals(path)) {
            File htmlFile = new File(WEB_ROOT + "/index.html");
            if (htmlFile.exists()) {
                sendResponse(exchange, 200, Files.readAllBytes(htmlFile.toPath()), "text/html");
            } else {
                sendResponse(exchange, 404, "Файл index.html не найден.".getBytes(), "text/plain");
            }
            return;
        }

        if ("/".equals(path) || "/index.html".equals(path)) {
            sendResponse(exchange, 200, "<h1>Сервер работает!</h1>".getBytes(), "text/html");
            return;
        }

        File requestedFile = new File(WEB_ROOT + path);
        if (requestedFile.exists() && requestedFile.isFile()) {
            String contentType = getContentType(path);
            sendResponse(exchange, 200, Files.readAllBytes(requestedFile.toPath()), contentType);
        } else {
            sendResponse(exchange, 404, "Файл не найден.".getBytes(), "text/plain");
        }
    }

    private static String getContentType(String path) {
        if (path.endsWith(".html")) return "text/html";
        if (path.endsWith(".css")) return "text/css";
        if (path.endsWith(".js")) return "application/javascript";
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".gif")) return "image/gif";
        return "application/octet-stream";
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, byte[] responseBytes, String contentType) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

}

