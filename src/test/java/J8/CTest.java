package J8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class CTest {

    @Test
    void testFindLongestWordMultipleMatches() {
        String phoneNumber = "43556";
        List<String> dictionary = List.of("HELLO", "HELL");
        String expected = "HELLO";

        String result = C.findLongestWord(phoneNumber, dictionary);

        assertEquals(expected, result);
    }

    @Test
    void testFindLongestWordEmptyDictionary() {
        String phoneNumber = "22836687";
        List<String> dictionary = List.of();
        String expected = "";

        String result = C.findLongestWord(phoneNumber, dictionary);

        assertEquals(expected, result);
    }

}

