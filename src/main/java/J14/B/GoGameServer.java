package J14.B;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.ConcurrentHashMap;

public class GoGameServer {
    private static final int BOARD_SIZE = 20; // Размер игрового поля
    private static final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private static char currentPlayer = 'X'; // Начинает игрок X
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/", exchange -> serveStaticFile(exchange, "web/go_game.html"));

        server.createContext("/go_style.css", exchange -> serveStaticFile(exchange, "web/go_style.css"));

        server.createContext("/go_script.js", exchange -> serveStaticFile(exchange, "web/go_script.js"));


        server.createContext("/state", exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                StringBuilder json = new StringBuilder("{\"board\":[");
                for (int i = 0; i < BOARD_SIZE; i++) {
                    json.append("[");
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        json.append("\"").append(board[i][j]).append("\"");
                        if (j < BOARD_SIZE - 1) json.append(",");
                    }
                    json.append("]");
                    if (i < BOARD_SIZE - 1) json.append(",");
                }
                json.append("],\"currentPlayer\":\"").append(currentPlayer).append("\"}");

                sendResponse(exchange, json.toString(), 200, "application/json");
            } else {
                sendResponse(exchange, "Метод не поддерживается.", 405, "text/plain");
            }
        });


        server.createContext("/move", exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                String[] params = body.split("&");
                int x = -1, y = -1;

                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        switch (keyValue[0]) {
                            case "x" -> x = Integer.parseInt(URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8));
                            case "y" -> y = Integer.parseInt(URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8));
                        }
                    }
                }

                if (x >= 0 && y >= 0 && x < BOARD_SIZE && y < BOARD_SIZE) {
                    synchronized (board) {
                        if (board[x][y] == ' ') {
                            board[x][y] = currentPlayer;
                            if (checkWin(x, y, currentPlayer)) {
                                sendResponse(exchange, "{\"message\":\"Игрок " + currentPlayer + " выиграл!\"}", 200, "application/json");
                                resetBoard();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                                sendResponse(exchange, "{\"message\":\"Ход принят!\"}", 200, "application/json");
                            }
                        } else {
                            sendResponse(exchange, "{\"error\":\"Эта клетка уже занята!\"}", 400, "application/json");
                        }
                    }
                } else {
                    sendResponse(exchange, "{\"error\":\"Некорректные координаты!\"}", 400, "application/json");
                }
            } else {
                sendResponse(exchange, "{\"error\":\"Метод не поддерживается.\"}", 405, "application/json");
            }
        });

        server.start();
        System.out.println("Сервер запущен на http://localhost:" + PORT);
    }

    private static boolean checkWin(int x, int y, char player) {
        int[] dx = {-1, 0, 1, 1};
        int[] dy = {1, 1, 1, 0};

        for (int d = 0; d < dx.length; d++) {
            int count = 1;

            for (int step = 1; step < 5; step++) {
                int nx = x + step * dx[d];
                int ny = y + step * dy[d];
                if (nx >= 0 && ny >= 0 && nx < BOARD_SIZE && ny < BOARD_SIZE && board[nx][ny] == player) {
                    count++;
                } else {
                    break;
                }
            }

            for (int step = 1; step < 5; step++) {
                int nx = x - step * dx[d];
                int ny = y - step * dy[d];
                if (nx >= 0 && ny >= 0 && nx < BOARD_SIZE && ny < BOARD_SIZE && board[nx][ny] == player) {
                    count++;
                } else {
                    break;
                }
            }

            if (count >= 5) {
                return true;
            }
        }

        return false;
    }

    private static void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    private static void serveStaticFile(HttpExchange exchange, String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            String contentType = getContentType(filePath) + "; charset=UTF-8";
            exchange.getResponseHeaders().add("Content-Type", contentType);
            exchange.sendResponseHeaders(200, file.length());
            try (OutputStream os = exchange.getResponseBody(); FileInputStream fis = new FileInputStream(file)) {
                fis.transferTo(os);
            }
        } else {
            sendResponse(exchange, "Файл не найден." + filePath, 404, "text/plain");
        }
    }

    private static String getContentType(String filePath) {
        if (filePath.endsWith(".html")) return "text/html";
        if (filePath.endsWith(".css")) return "text/css";
        if (filePath.endsWith(".js")) return "application/javascript";
        return "text/plain";
    }

    private static void sendResponse(HttpExchange exchange, String response, int statusCode, String contentType) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", contentType + "; charset=UTF-8");
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

}
