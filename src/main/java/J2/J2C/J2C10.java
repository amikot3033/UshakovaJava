package J2.J2C;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class J2C10 {

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

    //10. Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
    public static int[][] removeRowsAndColsWithMax(int[][] matrix) {
        System.out.println("10. Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.");
        // Поиск максимального элемента
        int max = Integer.MIN_VALUE;
        int n = matrix.length;
        List<Integer> maxRows = new ArrayList<>();
        List<Integer> maxCols = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ( matrix[i][j] > max) {
                    max = matrix[i][j];
                    maxRows.clear();
                    maxCols.clear();
                    maxRows.add(i);
                    maxCols.add(j);
                } else if ( matrix[i][j] == max) {
                    if (!maxRows.contains(i)) maxRows.add(i);
                    if (!maxCols.contains(j)) maxCols.add(j);
                }
            }
        }
        // Удаление строк и столбцов
        int newRowCount = n - maxRows.size();
        int newColCount = n - maxCols.size();
        int[][] result = new int[newRowCount][newColCount];
        int newRow = 0;

        for (int i = 0; i < n; i++) {
            if (maxRows.contains(i)) continue;
            int newCol = 0;
            for (int j = 0; j < n; j++) {
                if (maxCols.contains(j)) continue;
                result[newRow][newCol++] = matrix[i][j];
            }
            newRow++;
        }
        return result;
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
        //10. Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
        int[][] matrixRemoveRowsAndColsWithMax = removeRowsAndColsWithMax(matrix);
        printMatrix(matrixRemoveRowsAndColsWithMax);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
