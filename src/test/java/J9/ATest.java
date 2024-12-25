package J9;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ATest {

    @Test
    void testReadAndValidateNumbersValidFile() throws Exception {
        String testFilePath = "test_numbers_valid.txt";
        createTestFile(testFilePath, "en-US 12345.67\nfr-FR 12345,67\nde-DE 67890,12");

        List<Double> numbers = A.readAndValidateNumbers(testFilePath);

        assertEquals(3, numbers.size());
        assertEquals(12345.67, numbers.get(0));
        assertEquals(12345.67, numbers.get(1));
        assertEquals(67890.12, numbers.get(2));

        deleteTestFile(testFilePath);
    }

    @Test
    void testReadAndValidateNumbersInvalidLocale() throws CustomFileException {
        String testFilePath = "test_numbers_invalid_locale.txt";
        createTestFile(testFilePath, "invalid-locale 12345.67\nfr-FR 12345,67");

        List<Double> result = A.readAndValidateNumbers(testFilePath);

        assertEquals(1, result.size());
        assertEquals(12345.67, result.get(0), 0.01);

        deleteTestFile(testFilePath);
    }

    @Test
    void testReadAndValidateNumbersInvalidNumberFormat() throws CustomFileException {
        String testFilePath = "test_numbers_invalid_number.txt";
        createTestFile(testFilePath, "en-US invalid_number\nfr-FR 12345,67");

        List<Double> numbers = A.readAndValidateNumbers(testFilePath);

        assertEquals(1, numbers.size());
        assertEquals(12345.67, numbers.get(0));

        deleteTestFile(testFilePath);
    }

    @Test
    void testReadAndValidateNumbersFileNotFound() {
        String testFilePath = "non_existent_file.txt";

        Exception exception = assertThrows(CustomFileException.class, () -> A.readAndValidateNumbers(testFilePath));
        assertTrue(exception.getMessage().contains("Файл не найден"));
    }

    @Test
    void testReadAndValidateNumbersOutOfRangeNumber() throws CustomFileException {
        String testFilePath = "test_numbers_out_of_range.txt";
        createTestFile(testFilePath, "en-US 1.0E309\nfr-FR 12345,67");

        List<Double> numbers = A.readAndValidateNumbers(testFilePath);

        assertEquals(1, numbers.size());
        assertEquals(12345.67, numbers.get(0));

        deleteTestFile(testFilePath);
    }

    private void createTestFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка создания тестового файла", e);
        }
    }

    private void deleteTestFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            assertTrue(file.delete(), "Не удалось удалить тестовый файл");
        }
    }
}
