package J11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ATest {

    @Test
    public void testSingleElement() {
        List<Integer> numbers = Collections.singletonList(42);
        int result = A.pairwiseSum(numbers);
        assertEquals(42, result, "Результат должен быть равен единственному элементу списка");
    }

    @Test
    public void testTwoElements() {
        List<Integer> numbers = Arrays.asList(10, 20);
        int result = A.pairwiseSum(numbers);
        assertEquals(30, result, "Результат должен быть равен сумме двух элементов");
    }

    @Test
    public void testOddNumberOfElements() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int result = A.pairwiseSum(numbers);
        assertEquals(6, result, "Результат должен быть корректным для нечетного количества элементов");
    }

    @Test
    public void testEvenNumberOfElements() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        int result = A.pairwiseSum(numbers);
        assertEquals(10, result, "Результат должен быть корректным для четного количества элементов");
    }

    @Test
    public void testComplexCase() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int result = A.pairwiseSum(numbers);
        assertEquals(15, result, "Результат должен быть корректным для сложного случая");
    }

    @Test
    public void testEmptyList() {
        List<Integer> numbers = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> A.pairwiseSum(numbers), "Пустой список должен вызывать исключение");
    }

    @Test
    public void testNullList() {
        assertThrows(IllegalArgumentException.class, () -> A.pairwiseSum(null), "null должен вызывать исключение");
    }
}
