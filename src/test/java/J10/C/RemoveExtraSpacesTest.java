package J10.C;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveExtraSpacesTest {

    private static final String TEST_DIRECTORY = Paths.get(System.getProperty("user.dir"), "test_output_directory").toString();
    private static final String INPUT_FILE = Paths.get(System.getProperty("user.dir"), "test_input_program.java").toString();
    private static final String OUTPUT_FILE = Paths.get(TEST_DIRECTORY, "output_program.java").toString();

    @BeforeAll
    public static void setup() throws IOException {
        // Создаём тестовый входной файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE))) {
            writer.write("public class Test {  \n\n");
            writer.write("    public static void main(String[] args) {    \t \t \n");
            writer.write("        System.out.println(\"Hello, World!\");      \n    }  \n");
            writer.write("}\n");
        }

        File testDir = new File(TEST_DIRECTORY);
        if (testDir.exists()) {
            deleteDirectory(testDir);
        }
    }

    @Test
    public void testRemoveExtraSpaces() throws IOException {
        RemoveExtraSpaces.main(new String[]{INPUT_FILE, OUTPUT_FILE});

        System.out.println("Абсолютный путь директории: " + new File(TEST_DIRECTORY).getAbsolutePath());
        System.out.println("Существует ли директория: " + new File(TEST_DIRECTORY).exists());

        File outputDir = new File(TEST_DIRECTORY);
        assertTrue(outputDir.exists() && outputDir.isDirectory(), "Директория не создана");

        File outputFile = new File(OUTPUT_FILE);
        assertTrue(outputFile.exists() && outputFile.isFile(), "Выходной файл не создан");

        String expectedContent = "public class Test {\n" +
                "public static void main(String[] args) {\n" +
                "System.out.println(\"Hello, World!\");\n" +
                "}\n" +
                "}";

        String actualContent = Files.readString(Paths.get(OUTPUT_FILE))
                .replace("\r\n", "\n")
                .trim();

        assertEquals(expectedContent, actualContent, "Содержимое файла не соответствует ожидаемому");
    }


    @AfterAll
    public static void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get(INPUT_FILE));
        deleteDirectory(new File(TEST_DIRECTORY));
    }

    private static void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                deleteDirectory(file);
            }
        }
        directory.delete();
    }
}
