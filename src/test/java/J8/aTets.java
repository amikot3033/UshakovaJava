package J8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class aTets {

    @Test
    void testRemoveTextBetweenSimpleCase() {
        String input = "Пример (удалить) текста";
        char start = '(';
        char end = ')';
        String expected = "Пример  текста";
        assertEquals(expected, A.removeTextBetween(input, start, end));
    }

    void testRemoveTextBetweenStars() {
        String input = "Пример *удалить* текста";
        char start = '*';
        char end = '*';
        String expected = "Пример  текста";
        assertEquals(expected, A.removeTextBetween(input, start, end));
    }
}
