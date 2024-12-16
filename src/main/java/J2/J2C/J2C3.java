package J2.J2C;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class J2C3 {

    // Метод для вывода матрицы на экран
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int elem : row) {
                System.out.print(elem + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    //3. Найти и вывести наибольшее число возрастающих\убывающих элементов
    //матрицы, идущих подряд.

    public static void findLongestSequences(int[][] matrix) {
        int maxIncreasing = 0;
        int maxDecreasing = 0;
        List<Integer> longestIncreasingSequence = new ArrayList<>();
        List<Integer> longestDecreasingSequence = new ArrayList<>();

        // Поиск в строках
        for (int[] row : matrix) {
            List<Integer> increasingSequence = findLongestIncreasing(row);
            List<Integer> decreasingSequence = findLongestDecreasing(row);

            if (increasingSequence.size() > maxIncreasing) {
                maxIncreasing = increasingSequence.size();
                longestIncreasingSequence = new ArrayList<>(increasingSequence);
            }
            if (decreasingSequence.size() > maxDecreasing) {
                maxDecreasing = decreasingSequence.size();
                longestDecreasingSequence = new ArrayList<>(decreasingSequence);
            }
        }

        // Поиск в столбцах
        for (int col = 0; col < matrix[0].length; col++) {
            int[] column = new int[matrix.length];
            for (int row = 0; row < matrix.length; row++) {
                column[row] = matrix[row][col];
            }

            List<Integer> increasingSequence = findLongestIncreasing(column);
            List<Integer> decreasingSequence = findLongestDecreasing(column);

            if (increasingSequence.size() > maxIncreasing) {
                maxIncreasing = increasingSequence.size();
                longestIncreasingSequence = new ArrayList<>(increasingSequence);
            }
            if (decreasingSequence.size() > maxDecreasing) {
                maxDecreasing = decreasingSequence.size();
                longestDecreasingSequence = new ArrayList<>(decreasingSequence);
            }
        }

        System.out.println("Наибольшее количество возрастающих элементов подряд: " + maxIncreasing);
        System.out.println("Элементы возрастающей последовательности: " + longestIncreasingSequence);
        System.out.println("Наибольшее количество убывающих элементов подряд: " + maxDecreasing);
        System.out.println("Элементы убывающей последовательности: " + longestDecreasingSequence);
    }

    private static List<Integer> findLongestIncreasing(int[] array) {
        List<Integer> currentSequence = new ArrayList<>();
        List<Integer> longestSequence = new ArrayList<>();

        currentSequence.add(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                currentSequence.add(array[i]);
            } else {
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = new ArrayList<>(currentSequence);
                }
                currentSequence.clear();
                currentSequence.add(array[i]);
            }
        }
        if (currentSequence.size() > longestSequence.size()) {
            longestSequence = currentSequence;
        }
        return longestSequence;
    }

    private static List<Integer> findLongestDecreasing(int[] array) {
        List<Integer> currentSequence = new ArrayList<>();
        List<Integer> longestSequence = new ArrayList<>();

        currentSequence.add(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                currentSequence.add(array[i]);
            } else {
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = new ArrayList<>(currentSequence);
                }
                currentSequence.clear();
                currentSequence.add(array[i]);
            }
        }
        if (currentSequence.size() > longestSequence.size()) {
            longestSequence = currentSequence;
        }
        return longestSequence;
    }


    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите размерность матрицы n: ");
//        int n = scanner.nextInt();
        int n = 5;
        int[][] matrix = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(2 * n + 1) - n;
            }
        }
        System.out.println("Исходная матрица: ");
        printMatrix(matrix);

        //3. Найти и вывести наибольшее число возрастающих\убывающих элементов
        //матрицы, идущих подряд.
        findLongestSequences(matrix);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }

}
