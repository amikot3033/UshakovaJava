package J2.J2C;

public class J2C14 {

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

    //14. Найти количество всех седловых точек матрицы (матрица А имеет седловую точку Аi, j, если Аi,
    // j является минимальным элементом в i-й строке и максимальным в j-м столбце).

    public static void countSaddlePoints(int[][] matrix) {
        System.out.println("14. Найти количество всех седловых точек матрицы");
        int count = 0;
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            int rowMin = Integer.MAX_VALUE, minIndex = -1;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < rowMin) {
                    rowMin = matrix[i][j];
                    minIndex = j;
                }
            }
            boolean isSaddlePoint = true;
            for (int k = 0; k < n; k++) {
                if (matrix[k][minIndex] > rowMin) {
                    isSaddlePoint = false;
                    break;
                }
            }
            if (isSaddlePoint) count++;
        }
        System.out.println("Количество седловых точек: " + count);
    }
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 0, 0}, {0, 1, 3}, {0, 2, 4}};

        System.out.println("Исходная матрица: ");
        printMatrix(matrix);
        //14. Найти количество всех седловых точек матрицы (матрица А имеет седловую точку Аi, j, если Аi,
        // j является минимальным элементом в i-й строке и максимальным в j-м столбце).
        countSaddlePoints(matrix);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
