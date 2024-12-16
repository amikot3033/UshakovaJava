package J2.J2C;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class J2C15 {

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

    //15. Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках полученной матрицы возрастала.
    public static int[][] sortRowsBySum(int[][] matrix) {
        System.out.println("15. Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках полученной матрицы возрастала.");
        int[][] sortedMatrix = matrix.clone();
        Arrays.sort(sortedMatrix, Comparator.comparingInt(row -> Arrays.stream(row).sum()));
        return sortedMatrix;
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

        //15. Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках полученной матрицы возрастала.
        int[][] matrixSortRowsBySum = sortRowsBySum(matrix);
        printMatrix(matrixSortRowsBySum);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
