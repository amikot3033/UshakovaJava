package J2.J2C;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class J2C1 {

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


    //1. Упорядочить строки (столбцы) матрицы в порядке возрастания значений
    //элементов k-го столбца (строки).
    public static int[][] sortByColumn(int[][] matrix, int k) {
        System.out.println("1. Упорядочить столбцы матрицы в порядке возрастания значений элементов k-го столбца");
        System.out.println("k = " + k);
        int[][] sortedMatrix = matrix;
        Arrays.sort(sortedMatrix, Comparator.comparingInt(row -> row[k]));
        return sortedMatrix;
    }

    public static int[][] sortByRow(int[][] matrix, int k) {
        System.out.println("1. Упорядочить строки матрицы в порядке возрастания значений элементов k-ой строки");
        System.out.println("k = " + k);
        int cols = matrix[0].length;
        Integer[] columnIndices = new Integer[cols];
        for (int i = 0; i < cols; i++) {
            columnIndices[i] = i;
        }

        Arrays.sort(columnIndices, Comparator.comparingInt(col -> matrix[k][col]));

        int[][] sortedMatrix = new int[matrix.length][cols];
        for (int i = 0; i < cols; i++) {
            int originalColumn = columnIndices[i];
            for (int j = 0; j < matrix.length; j++) {
                sortedMatrix[j][i] = matrix[j][originalColumn];
            }
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
        //1. Упорядочить строки (столбцы) матрицы в порядке возрастания значений
        //элементов k-го столбца (строки).
        int [][]sortedMatrix = sortByColumn(matrix, 0); //по столбцу
        printMatrix(sortedMatrix);
        int [][]sortedMatrix2 = sortByRow(matrix, 0); //по строчке
        printMatrix(sortedMatrix2);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }

}





