package J2.J2C;

import java.util.Random;

public class J2C9 {

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

    public static void printMatrixD(Double[][] matrix) {
        for (Double[] row : matrix) {
            for (Double elem : row) {
                System.out.print(elem + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    //9. Построить матрицу, вычитая из элементов каждой строки матрицы ее среднее арифметическое.
    public static Double[][] subtractRowAverage(int[][] matrix) {
        System.out.println("9. Построить матрицу, вычитая из элементов каждой строки матрицы ее среднее арифметическое.");
        int n = matrix.length;
        Double[][] result = new Double[n][n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
            }
            double mean = sum / n;
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[i][j] - mean;
            }
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
        //9. Построить матрицу, вычитая из элементов каждой строки матрицы ее среднее арифметическое.
        Double[][] matrixSubtractRowAverag =  subtractRowAverage(matrix);
        printMatrixD(matrixSubtractRowAverag);

        System.out.println("Разработчик: Ушакова Карина Б762-1");

    }
}
