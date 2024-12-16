package J2.J2C;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class J2C19 {

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
    //19. Путем перестановки элементов квадратной вещественной матрицы добиться
    //того, чтобы ее максимальный элемент находился в левом верхнем углу,
    // следующий по величине — в позиции (2, 2),
    // следующий по величине — в позиции (3, 3) и т.д., заполнив таким образом всю главную диагональ.
    private static int[][] placeMaxOnDiagonal(int[][] matrix) {
        System.out.println("19. Путем перестановки элементов квадратной вещественной матрицы добиться того, чтобы ее максимальный элемент находился в левом верхнем углу");
        int n = matrix.length;
        int[][] sortMatrix = matrix.clone();
        List<Integer> elements = new ArrayList<>();
        for (int[] row : sortMatrix) for (int elem : row) elements.add(elem);

        elements.sort(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            sortMatrix[i][i] = elements.get(i);
        }
        return sortMatrix;
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
        //19. Путем перестановки элементов квадратной вещественной матрицы добиться
        //того, чтобы ее максимальный элемент находился в левом верхнем углу,
        // следующий по величине — в позиции (2, 2),
        // следующий по величине — в позиции (3, 3) и т.д., заполнив таким образом всю главную диагональ.
        System.out.println("Исходная матрица: ");
        printMatrix(matrix);
        int[][] matrixPlaceMaxOnDiagonal = placeMaxOnDiagonal(matrix);
        printMatrix( matrixPlaceMaxOnDiagonal);

        System.out.println("Разработчик: Ушакова Карина Б762-1");

    }
}
