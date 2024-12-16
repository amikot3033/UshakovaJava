package J2.J2C;

import java.util.Random;

public class J2C13 {

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

    //13. Преобразовать строки матрицы таким образом, чтобы элементы, равные
    //нулю, располагались после всех остальных.
    public static int[][] moveZeroesToEnd(int[][] matrix) {
        System.out.println("13. Преобразовать строки матрицы таким образом, чтобы элементы, равные нулю, располагались после всех остальных.");
        int[][] zeroesMatrix = matrix.clone();

        for (int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i];
            int nonZeroIndex = 0;

            for (int j = 0; j < matrix.length; j++) {
                if (row[j] != 0) {
                    row[nonZeroIndex++] = row[j];
                }
            }
            // Заполнение нулями
            while (nonZeroIndex < matrix.length) {
                row[nonZeroIndex++] = 0;
            }
        }
        return zeroesMatrix;
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
        //13. Преобразовать строки матрицы таким образом, чтобы элементы, равные
        //нулю, располагались после всех остальных.
        int[][] matrixMoveZeroesToEnd = moveZeroesToEnd(matrix);
        printMatrix(matrixMoveZeroesToEnd);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
