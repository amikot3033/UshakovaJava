package J2.J2C;

import java.util.Random;

public class J2C2 {

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

    //2. Выполнить циклический сдвиг заданной матрицы на k позиций вправо
    //(влево, вверх, вниз).

    public static int[][] shiftMatrix(int[][] matrix, String direction, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] shiftedMatrix = new int[rows][cols];

        switch (direction.toLowerCase()) {
            case "up":
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        shiftedMatrix[i][j] = matrix[(i + k) % rows][j];
                    }
                }
                break;

            case "down":
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        shiftedMatrix[i][j] = matrix[(i - k + rows) % rows][j];
                    }
                }
                break;

            case "left":
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        shiftedMatrix[i][j] = matrix[i][(j + k) % cols];
                    }
                }
                break;

            case "right":
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        shiftedMatrix[i][j] = matrix[i][(j - k + cols) % cols];
                    }
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid direction. Use 'up', 'down', 'left', or 'right'.");
        }

        return shiftedMatrix;
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

        int k = 1;

        System.out.println("Сдвиг вверх на " + k + ":");
        printMatrix(shiftMatrix(matrix, "up", k));

        System.out.println("Сдвиг вниз на " + k + ":");
        printMatrix(shiftMatrix(matrix, "down", k));

        System.out.println("Сдвиг влево на " + k + ":");
        printMatrix(shiftMatrix(matrix, "left", k));

        System.out.println("Сдвиг вправо на " + k + ":");
        printMatrix(shiftMatrix(matrix, "right", k));

        System.out.println("Разработчик: Ушакова Карина Б762-1");

    }
}
