package J10.C;
import java.io.*;

public class RemoveExtraSpaces {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: Укажите входной и выходной файлы как аргументы.");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        File outputFile = new File(outputFilePath);
        File outputDir = outputFile.getParentFile();

        try {
            if (outputDir != null && !outputDir.exists()) {
                if (!outputDir.mkdirs()) {
                    throw new IOException("Не удалось создать директорию: " + outputDir.getAbsolutePath());
                }
            }

            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line.trim()).append("\n");
                }
            }

            String processedContent = content.toString()
                    .replaceAll("(?m)^\\s+", "") // Убираем пробелы в начале строки
                    .replaceAll("\\s{2,}", " "); // Заменяем несколько пробелов на один

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(processedContent.trim());
            }

            System.out.println("Обработка завершена. Результат записан в файл: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении или записи файла: " + e.getMessage());
        }
    }
}
