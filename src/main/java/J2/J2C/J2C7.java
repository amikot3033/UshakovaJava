package J2.J2C;

import java.util.Random;

public class J2C7 {

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

    //7. Повернуть матрицу на 90, 180 или 270 градусов против часовой стрелки.
    public static int[][] rotateMatrix(int[][] matrix, int angle) {
        System.out.println("7. Повернуть матрицу на " + angle + " градусов против часовой стрелки.");
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        if (angle == 90) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rotated[j][n - 1 - i] = matrix[i][j];
                }
            }
        } else if (angle == 180) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rotated[n - 1 - i][n - 1 - j] = matrix[i][j];
                }
            }
        } else if (angle == 270) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rotated[n - 1 - j][i] = matrix[i][j];
                }
            }
        }
        return rotated;
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
//7. Повернуть матрицу на 90, 180 или 270 градусов против часовой стрелки.
        int [][]matrix90 = rotateMatrix(matrix, 90);
        printMatrix(matrix90);
        int [][]matrix180 = rotateMatrix(matrix, 180);
        printMatrix(matrix180);
        int [][]matrix270 = rotateMatrix(matrix, 270);
        printMatrix(matrix270);

        System.out.println("Разработчик: Ушакова Карина Б762-1");

    }
}
