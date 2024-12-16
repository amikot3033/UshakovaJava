package J2.J2C;

import java.util.Random;

public class J2C16 {

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

    //16. Найти число локальных минимумов. Соседями элемента матрицы назовем
    //элементы, имеющие с ним общую сторону или угол. Элемент матрицы называется локальным минимумом, если он строго меньше всех своих соседей.
    public static boolean isLocalMin(int[][] matrix, int i, int j) {
        int n = matrix.length;
        int val = matrix[i][j];
        int[] directions = {-1, 0, 1};

        for (int di : directions) {
            for (int dj : directions) {
                if (di == 0 && dj == 0) {
                    continue;
                }
                int ni = i + di, nj = j + dj;

                if (ni >= 0 && ni < n && nj >= 0 && nj < n && matrix[ni][nj] <= val){
                    return false;
                }
            }
        }

        System.out.println("Локальный минимум по координатам: i: "  + i + ", j: " + j + " "+matrix[i][j]);
        return true;
    }

    public static void countLocalMinima(int[][] matrix) {
        System.out.println("16. Найти число локальных минимумов.");
        int count = 0;
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isLocalMin(matrix, i, j)) count++;
            }
        }
        System.out.println("Количество локальных минимумов: " + count);
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

//16. Найти число локальных минимумов. Соседями элемента матрицы назовем
        //элементы, имеющие с ним общую сторону или угол. Элемент матрицы называется локальным минимумом, если он строго меньше всех своих соседей.
        countLocalMinima(matrix);

        System.out.println("Разработчик: Ушакова Карина Б762-1");

    }
}
