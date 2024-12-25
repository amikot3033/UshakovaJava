package J10;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class PoemAnalyzer {

    public static void main(String[] args) {
        try {
            String inputFilePath = "src/main/java/J10/Poem.txt";
            String outputFilePath = "src/main/java/J10/Analysis.txt";

            String content = Files.readString(Path.of(inputFilePath));

            if (content.isBlank()) {
                System.out.println("Входной файл пуст.");
                return;
            }

            Map<Character, Integer> letterFrequency = analyzeLetterFrequency(content);
            Map<String, Integer> wordFrequency = analyzeWordFrequency(content);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write("Частота повторяемости букв:\n");
                for (Map.Entry<Character, Integer> entry : letterFrequency.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                }

                writer.write("\nЧастота повторяемости слов:\n");
                if (wordFrequency.isEmpty()) {
                    writer.write("Нет слов.\n");
                } else {
                    for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                        writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                    }
                }
            }

            System.out.println("Результат сохранен в " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    static Map<Character, Integer> analyzeLetterFrequency(String text) {
        Map<Character, Integer> frequencyMap = new TreeMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }
        return frequencyMap;
    }

    static Map<String, Integer> analyzeWordFrequency(String text) {
        Map<String, Integer> frequencyMap = new TreeMap<>();
        String[] words = text.toLowerCase().split("\\P{L}+");
        for (String word : words) {
            if (!word.isEmpty()) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        return frequencyMap;
    }
}

