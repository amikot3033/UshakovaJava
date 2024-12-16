package J2.J2C;

import java.util.Arrays;
import java.util.Random;

public class J2C18 {

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
    //18. Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы значения их характеристик убывали. Характеристикой столбца прямоугольной
    //матрицы называется сумма модулей его элементов.
    public static int columnSum(int[][] matrix, int col) {
        return Arrays.stream(matrix).mapToInt(row -> Math.abs(row[col])).sum();
    }

    public static int[][] sortColumnsByCharacteristic(int[][] matrix) {
        System.out.println("18. Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы значения их характеристик убывали.");
        int n = matrix.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++){
            indices[i] = i;
        }
        int[][] sortMatrix = matrix.clone();
        Arrays.sort(indices, (a, b) -> Integer.compare(columnSum(sortMatrix, b), columnSum(sortMatrix, a)));

        int[][] sortedMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sortedMatrix[i][j] = matrix[i][indices[j]];
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(sortedMatrix[i], 0, matrix[i], 0, n);
        }
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
        //18. Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы значения их характеристик убывали. Характеристикой столбца прямоугольной
        //матрицы называется сумма модулей его элементов.
        int[][] matrixSortColumnsByCharacteristic = sortColumnsByCharacteristic(matrix);
        printMatrix( matrixSortColumnsByCharacteristic);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
