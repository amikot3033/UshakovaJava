package J11;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BTest {

    @Test
    void testBuildObjectList() {
        List<String> lines = Arrays.asList("Object1 123", "Object2 456", "Object1 789", "Object3 234", "Object2 100");

        List<B.Pair> expected = Arrays.asList(
                new B.Pair("Object1", 123),
                new B.Pair("Object2", 456),
                new B.Pair("Object1", 789),
                new B.Pair("Object3", 234),
                new B.Pair("Object2", 100)
        );

        List<B.Pair> result = B.buildObjectList(lines);

        assertEquals(expected.size(), result.size(), "Размеры списков не совпадают");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).name, result.get(i).name, "Наименования не совпадают");
            assertEquals(expected.get(i).code, result.get(i).code, "Шифры не совпадают");
        }
    }

    @Test
    void testCompressList() {
        List<B.Pair> objects = Arrays.asList(
                new B.Pair("Object1", 123),
                new B.Pair("Object2", 456),
                new B.Pair("Object1", 789),
                new B.Pair("Object3", 234),
                new B.Pair("Object2", 100)
        );

        List<B.Pair> expected = Arrays.asList(

                new B.Pair("Object2", 100), // Object2 с минимальным шифром
                new B.Pair("Object1", 123),  // Object1 с минимальным шифром
                new B.Pair("Object3", 234)  // Object3, уникальное имя
        );

        List<B.Pair> result = B.compressList(objects);

        assertEquals(expected.size(), result.size(), "Размеры списков не совпадают");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).name, result.get(i).name, "Наименования не совпадают");
            assertEquals(expected.get(i).code, result.get(i).code, "Шифры не совпадают");
        }
    }


    @Test
    void testFileReading() {
        List<String> expected = Arrays.asList(
                "Object1 123", "Object2 456", "Object1 789", "Object3 234", "Object2 100"
        );

        String filename = "test_input.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : expected) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> lines = B.readFile(filename);

        assertEquals(expected.size(), lines.size(), "Размеры списков не совпадают");
        assertTrue(lines.containsAll(expected), "Содержимое файла не совпадает с ожиданием");

        new File(filename).delete();
    }

    @Test
    void testEmptyInputFile() {
        String filename = "empty_input.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> lines = B.readFile(filename);

        assertTrue(lines.isEmpty(), "Файл должен быть пустым");

        new File(filename).delete();
    }
}
