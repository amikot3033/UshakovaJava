package J2.J2C;

import java.util.Random;

public class J2C8 {

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

    //8. Вычислить определитель матрицы.
    public static int[][] getSubMatrix(int[][] matrix, int excludedRow, int excludedCol) {
//        System.out.println("8. Вычислить определитель матрицы.");
        int n = matrix.length;
        int[][] subMatrix = new int[n - 1][n - 1];
        int subRow = 0, subCol;

        for (int row = 0; row < n; row++) {
            if (row == excludedRow) {
                continue;
            }
            subCol = 0;
            for (int col = 0; col < n; col++) {
                if (col == excludedCol) {
                    continue;
                }
                subMatrix[subRow][subCol] = matrix[row][col];
                subCol++;
            }
            subRow++;
        }
        return subMatrix;
    }
    public static int determinant(int[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int det = 0;
        for (int col = 0; col < n; col++) {
            int[][] subMatrix = getSubMatrix(matrix, 0, col);
            det += matrix[0][col] * determinant(subMatrix) * (col % 2 == 0 ? 1 : -1);
        }
        return det;
    }


    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите размерность матрицы n: ");
//        int n = scanner.nextInt();
        int n = 4;
        int[][] matrix = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(2 * n + 1) - n;
            }
        }
//8. Вычислить определитель матрицы.

        System.out.println("Исходная матрица: ");
        printMatrix(matrix);
        int determinant = determinant(matrix);
        System.out.print("Определитель: " + determinant);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
