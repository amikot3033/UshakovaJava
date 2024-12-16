package J2.J2C;

import java.util.Random;

import static J2C.J2C2.shiftMatrix;

public class J2C12 {

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

    //12. В матрице найти минимальный элемент и переместить его на место заданного элемента путем перестановки строк и столбцов.
    public static int[][] moveMinElement(int[][] matrix, int targetRow, int targetCol) {
        System.out.println("12. В матрице найти минимальный элемент и переместить его на место заданного элемента путем перестановки строк и столбцов.");
        System.out.println(targetRow + ", " + targetCol);
        int n = matrix.length;
        int min = Integer.MAX_VALUE;
        int minRow = -1, minCol = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minRow = i;
                    minCol = j;
                }
            }
        }

        System.out.println(min + " " + minCol + " " + minRow);

        if (minRow < targetRow){
            matrix = shiftMatrix(matrix, "down", targetRow-minRow);
        }
        else{
            matrix = shiftMatrix(matrix, "up", minRow-targetRow);
        }

        if (minCol < targetCol){
            matrix = shiftMatrix(matrix, "rigth", targetCol-minCol);
        }
        else{
            matrix = shiftMatrix(matrix, "left", minCol-targetCol);

        }

        return matrix;
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
        //12. В матрице найти минимальный элемент и переместить его на место заданного элемента путем перестановки строк и столбцов.
        int[][] matrixMoveMinElement = moveMinElement(matrix, 0, 0);
        printMatrix(matrixMoveMinElement);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
