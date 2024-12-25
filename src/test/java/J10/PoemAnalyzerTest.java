package J10;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PoemAnalyzerTest {

    @Test
    void testAnalyzeLetterFrequency() {
        String input = "Hello World!";
        Map<Character, Integer> result = PoemAnalyzer.analyzeLetterFrequency(input);

        assertEquals(7, result.size());
        assertEquals(1, result.get('h'));
        assertEquals(3, result.get('l'));
        assertEquals(1, result.get('w'));
    }

    @Test
    void testAnalyzeLetterFrequency_EmptyInput() {
        String input = "";
        Map<Character, Integer> result = PoemAnalyzer.analyzeLetterFrequency(input);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeWordFrequency() {
        String input = "Hello, hello world!";
        Map<String, Integer> result = PoemAnalyzer.analyzeWordFrequency(input);

        assertEquals(2, result.size());
        assertEquals(2, result.get("hello"));
        assertEquals(1, result.get("world"));
    }

    @Test
    void testAnalyzeWordFrequency_EmptyInput() {
        String input = "";
        Map<String, Integer> result = PoemAnalyzer.analyzeWordFrequency(input);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeWordFrequency_SpecialCharacters() {
        String input = "!!! 123 ... hello";
        Map<String, Integer> result = PoemAnalyzer.analyzeWordFrequency(input);

        assertEquals(1, result.size());
        assertEquals(1, result.get("hello"));
    }
}
