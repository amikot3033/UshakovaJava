package J8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class BTest {

    @Test
    void testSortWordsByFirstConsonant() {
        String input = "Арбуз обои яблоко этаж Балкон арена";
        List<String> expected = List.of("арена", "Арбуз", "яблоко", "обои", "этаж");

        List<String> result = B.sortWordsByFirstConsonant(input);

        assertEquals(expected, result);
    }

    @Test
    void testSortWordsByFirstConsonantEmptyInput() {
        String input = "";
        List<String> expected = List.of();

        List<String> result = B.sortWordsByFirstConsonant(input);

        assertEquals(expected, result);
    }

    @Test
    void testSortWordsByFirstConsonantNoVowelWords() {
        String input = "Балкон стена крыша";
        List<String> expected = List.of();

        List<String> result = B.sortWordsByFirstConsonant(input);

        assertEquals(expected, result);
    }
}

