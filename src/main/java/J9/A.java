package J9;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class A {

    public static void main(String[] args) {
        String filePath = "src/main/java/J9/numbers.txt";
        try {
            List<Double> numbers = readAndValidateNumbers(filePath);
            double sum = numbers.stream().mapToDouble(Double::doubleValue).sum();
            double average = numbers.isEmpty() ? 0 : sum / numbers.size();

            System.out.println("Сумма чисел: " + sum);
            System.out.println("Среднее значение: " + average);
        } catch (CustomFileException | OutOfMemoryError e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public static List<Double> readAndValidateNumbers(String filePath) throws CustomFileException {
        List<Double> numbers = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new CustomFileException("Файл не найден или указан некорректный путь.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" ", 2);
                    if (parts.length != 2) {
                        throw new CustomFileException("Некорректный формат строки: " + line);
                    }

                    String localeString = parts[0];
                    String numberString = parts[1];

                    Locale locale = Locale.forLanguageTag(localeString);
                    if (locale.getLanguage().isEmpty() || locale.getCountry().isEmpty()) {
                        throw new CustomFileException("Некорректная локаль: " + localeString);
                    }

                    double number = parseNumber(numberString, locale);
                    numbers.add(number);
                } catch (CustomFileException | NumberFormatException e) {
                    System.err.println("Ошибка обработки строки: " + line + ". Причина: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new CustomFileException("Ошибка чтения файла: " + e.getMessage(), e);
        }

        return numbers;
    }

    public static double parseNumber(String numberString, Locale locale) throws CustomFileException {
        try {
            Number number = NumberFormat.getInstance(locale).parse(numberString);
            double result = number.doubleValue();

            if (result > Double.MAX_VALUE || result < -Double.MAX_VALUE) {
                throw new CustomFileException("Число выходит за пределы допустимого диапазона: " + result);
            }

            return result;
        } catch (ParseException e) {
            throw new CustomFileException("Некорректное число: " + numberString, e);
        }
    }
}


