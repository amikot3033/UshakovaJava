package J2.J2C;

import java.util.Random;

public class J2C4 {

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


    //4. Найти сумму элементов матрицы, расположенных между первым и вторым
    //положительными элементами каждой строки.
    public static void sumBetweenPositives(int[][] matrix) {
        System.out.println("4. Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.");
        for (int[] row : matrix) {
            int firstPos = -1;
            int secondPos = -1;
            int sum = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] > 0) {
                    if (firstPos == -1) {
                        firstPos = j;
                    }
                    else {
                        secondPos = j;
                        break;
                    }
                }
            }
            if (firstPos != -1 && secondPos != -1) {
                for (int j = firstPos + 1; j < secondPos; j++) {
                    sum += row[j];
                }
            }
            System.out.println("Сумма элементов между первым и вторым положительными в строке: " + sum);
        }
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
        //4. Найти сумму элементов матрицы, расположенных между первым и вторым
        //положительными элементами каждой строки.
        sumBetweenPositives(matrix);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }

}
