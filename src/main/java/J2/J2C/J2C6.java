package J2.J2C;

public class J2C6 {

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

    //6. Округлить все элементы матрицы до целого числа.
    public static int[][] roundMatrix(Double[][] matrix) {
        System.out.println("6. Округлить все элементы матрицы до целого числа.");
        int[][] rMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rMatrix[i][j] = (int) Math.round(matrix[i][j]);
            }
        }
        return rMatrix;
    }
    public static void main(String[] args) {

//6. Округлить все элементы матрицы до целого числа.
        Double[][] matrixDouble = new Double[][] {{1.5, 2.5}, {2.222, 5.6666}};
        System.out.print("Исходная матрица: ");
        printMatrixD(matrixDouble);
        int [][]matrixRound = roundMatrix(matrixDouble);
        printMatrix(matrixRound);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
