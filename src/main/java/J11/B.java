package J11;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) {

        List<String> lines = readFile("src/main/java/J11/input.txt"); // Чтение данных из файла
        List<Pair> objects = buildObjectList(lines);
        List<Pair> compressedList = compressList(objects);

        for (Pair pair : compressedList) {
            System.out.println(pair);
        }
    }

    static class Pair {
        String name;
        int code;

        Pair(String name, int code) {
            this.name = name;
            this.code = code;
        }

        @Override
        public String toString() {
            return name + " " + code;
        }
    }

    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<Pair> buildObjectList(List<String> lines) {
        List<Pair> objects = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                try {
                    String name = parts[0];
                    int code = Integer.parseInt(parts[1]);
                    objects.add(new Pair(name, code));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка формата шифра для строки: " + line);
                }
            }
        }
        return objects;
    }

    public static List<Pair> compressList(List<Pair> objects) {
        objects.sort(Comparator.comparingInt(o -> o.code));

        List<Pair> compressedList = new ArrayList<>();
        Set<String> seenNames = new HashSet<>();

        for (Pair object : objects) {
            if (!seenNames.contains(object.name)) {
                compressedList.add(object);
                seenNames.add(object.name);
            }
        }

        return compressedList;
    }
}
