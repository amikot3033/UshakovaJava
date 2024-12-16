package J2.J2C;

public class J2C5 {

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

    //5. Вывести числа от 1 до k в виде матрицы N x N слева направо и сверху вниз.
    public static int[][] fillMatrixWithSequence(int n) {
        System.out.println("5. Вывести числа от 1 до k в виде матрицы N x N слева направо и сверху вниз. n = " + n);
        int[][] matrix = new int[n][n];
        int k = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = k++;
            }
        }
        return matrix;
    }
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите размерность матрицы n: ");
//        int n = scanner.nextInt();
        int n = 5;

        //5. Вывести числа от 1 до k в виде матрицы N x N слева направо и сверху вниз.
        int [][]matrixK = fillMatrixWithSequence(5);
        printMatrix(matrixK);

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
