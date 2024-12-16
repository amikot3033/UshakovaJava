package J2.J2C;

import java.util.ArrayList;
import java.util.List;

public class J2C11 {

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

    //11. Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
    public static int[][] compressMatrix(int[][] matrix) {
        System.out.println("11. Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.");
        int rows = matrix.length;
        int cols = matrix[0].length;

        List<Integer> nonZeroRows = new ArrayList<>();
        List<Integer> nonZeroCols = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            boolean isZeroRow = true;
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 0) {
                    isZeroRow = false;
                    break;
                }
            }
            if (!isZeroRow) {
                nonZeroRows.add(i);
            }
        }

        for (int j = 0; j < cols; j++) {
            boolean isZeroCol = true;
            for (int i : nonZeroRows) {
                if (matrix[i][j] != 0) {
                    isZeroCol = false;
                    break;
                }
            }
            if (!isZeroCol) {
                nonZeroCols.add(j);
            }
        }

        int[][] compressedMatrix = new int[nonZeroRows.size()][nonZeroCols.size()];
        for (int i = 0; i < nonZeroRows.size(); i++) {
            for (int j = 0; j < nonZeroCols.size(); j++) {
                compressedMatrix[i][j] = matrix[nonZeroRows.get(i)][nonZeroCols.get(j)];
            }
        }

        return compressedMatrix;
    }    public static void main(String[] args) {

        int[][] matrix = new int[][]{{0, 0, 0}, {0, 1, 3}, {0, 2, 3}};
        System.out.println("Исходная матрица: ");
        printMatrix(matrix);
        //11. Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
        int[][] matrixoCmpress = compressMatrix(matrix);
        printMatrix(matrixoCmpress);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
