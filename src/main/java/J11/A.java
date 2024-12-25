package J11;

import java.util.*;

public class A {
    public static void main(String[] args) {
        // Пример использования
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int result = pairwiseSum(numbers);
        System.out.println("Результат попарного суммирования: " + result);
    }

    public static int pairwiseSum(List<Integer> numbers) {
        // Проверяем, что список не пустой
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Список чисел не должен быть пустым");
        }

        // Конвертируем список в очередь для обработки попарного суммирования
        Queue<Integer> queue = new LinkedList<>(numbers);

        // Пока в очереди больше одного элемента, продолжаем суммировать попарно
        while (queue.size() > 1) {
            Queue<Integer> nextQueue = new LinkedList<>();

            // Суммируем попарно
            while (queue.size() > 1) {
                int first = queue.poll();
                int second = queue.poll();
                nextQueue.add(first + second);
            }

            // Если в очереди остался один элемент, добавляем его в новую очередь
            if (!queue.isEmpty()) {
                nextQueue.add(queue.poll());
            }

            // Обновляем очередь на следующую итерацию
            queue = nextQueue;
        }

        // Возвращаем оставшийся элемент
        return queue.peek();
    }
}
