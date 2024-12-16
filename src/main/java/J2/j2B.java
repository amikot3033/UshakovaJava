package J2;

import java.util.Arrays;

public class j2B {

    //1. Вывести на экран таблицу умножения
    public static void multiplicationTable() {
        System.out.println( "1. Вывести на экран таблицу умножения");
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                System.out.println(i + "*" + j + "=" + i * j);
            }
            System.out.println("____");
        }

    }
    //2. Вывести элементы массива в обратном порядке.
    public static void reversedArray(int[] array) {
        System.out.println( "2. Вывести элементы массива в обратном порядке");
        System.out.println(Arrays.toString(array));
        int[] reversedArray = new int[array.length];
        for (int i = array.length - 1, j = 0; i >= 0; i--, j++) {
            reversedArray[j] = array[i];
        }
        System.out.println(Arrays.toString(reversedArray));
    }

    //3. Определить принадлежность некоторого значения k интервалам (n, m], [n,
    //m), (n, m), [n, m].
    public static void checkIntervalBelonging(int k, int n, int m) {
        System.out.println("3. Определить принадлежность некоторого значения k интервалам (n, m], [n, m), (n, m), [n, m].");
        System.out.println("Проверка принадлежности числа " + k + " интервалам, n = " + n + ", m = " + m);
        System.out.println("(n, m]: " + (k > n && k <= m));
        System.out.println("[n, m): " + (k >= n && k < m));
        System.out.println("(n, m): " + (k > n && k < m));
        System.out.println("[n, m]: " + (k >= n && k <= m));
    }

    //4. Вывести на экран все числа от 1 до 100, которые делятся на 3 без остатка
    public static void multiplesOfThree() {
        System.out.println("4. Вывести на экран все числа от 1 до 100, которые делятся на 3 без остатка");
        System.out.println("Числа от 1 до 100, которые делятся на 3:");
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    //5. Сколько значащих нулей в двоичной записи числа 129?
    public static void countSignificantZeros(int number) {
        System.out.println("5. Сколько значащих нулей в двоичной записи числа 129?");
        String binary = Integer.toBinaryString(number);
        int first1 =  binary.indexOf("1");
        String binaryWithoutSignificantZeros = binary.substring(first1);
        long zeroCount = binaryWithoutSignificantZeros.chars().filter(c -> c == '0').count();

        System.out.println("Количество значащих нулей в двоичной записи числа " + number + " (" + binary + "): " + zeroCount);
    }

    //6. В системе счисления с некоторым основанием десятичное число 81 записывается в виде 100.
    // Найти это основание.
    public static void findBaseForNumberRepresentation(int decimalNumber, String representation) {
        System.out.println("6. В системе счисления с некоторым основанием десятичное число 81 записывается в виде 100. Найти это основание.");
        for (int base = 2; base <= 36; base++) {
            if (Integer.toString(decimalNumber, base).equals(representation)) {
                System.out.println("Основание, в котором " + decimalNumber + " записывается как " + representation + ": " + base);
                return;
            }
        }
        System.out.println("Основание не найдено.");
    }

    //7. Написать код программы, которая бы переводила числа из десятичной системы счисления в любую другую.
    public static String convertFromDecimal(int number, int base) {
        System.out.println("7. Написать код программы, которая бы переводила числа из десятичной системы счисления в любую другую.");
        System.out.println("Число в системе счисления с основанием " + base + " = " + Integer.toString(number, base));
        return Integer.toString(number, base);
    }

    //8. Написать код программы, которая бы переводила числа одной любой системы счисления в любую другую.
    public static String convertBetweenBases(String number, int fromBase, int toBase) {
        System.out.println("8. Написать код программы, которая бы переводила числа одной любой системы счисления в любую другую.");
        int decimalValue = Integer.parseInt(number, fromBase);
        System.out.println("Число " + number + " написанное в " + fromBase + "-системе счисления, в системе счисления с основанием " + toBase + " = " + Integer.toString(decimalValue, toBase));
        return Integer.toString(decimalValue, toBase);
    }

    //9. Ввести число от 1 до 12. Вывести на консоль название месяца, соответствую
    //щего данному числу. Осуществить проверку корректности ввода чисел.
    public static void printMonthByNumber(String monthNumber) {
        System.out.println("9. Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу. Осуществить проверку корректности ввода чисел.");
        System.out.println("Вы ввели:" + monthNumber);
        try {
            int intMonthNumber = Integer.parseInt(monthNumber);
            String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
            if (intMonthNumber >= 1 && intMonthNumber <= 12) {
                System.out.println("Месяц: " + months[intMonthNumber - 1]);
            } else {
                System.out.println("Некорректный ввод. Введите число от 1 до 12.");
            }
        } catch (Exception e) {
        System.out.println("Ошибка выбора операции. Введите целое число!");
        }

    }



    public static void main(String[] args) {
//1. Вывести на экран таблицу умножения.
        multiplicationTable();
//2. Вывести элементы массива в обратном порядке.
        int[] intArray = new int[]{1, 2, 3, 4, 5, 6};
        reversedArray(intArray);
//3. Определить принадлежность некоторого значения k интервалам (n, m], [n,
//m), (n, m), [n, m].
        checkIntervalBelonging(5, 5, 10);
//4. Вывести на экран все числа от 1 до 100, которые делятся на 3 без остатка.
        multiplesOfThree();
//5. Сколько значащих нулей в двоичной записи числа 129?
        countSignificantZeros(129);
//6. В системе счисления с некоторым основанием десятичное число 81 записывается в виде 100. Найти это основание.
        findBaseForNumberRepresentation(81, "100");

//7. Написать код программы, которая бы переводила числа из десятичной системы счисления в любую другую.
        String convertDeci = convertFromDecimal(2, 2);
//8. Написать код программы, которая бы переводила числа одной любой системы счисления в любую другую.
        String convertBase = convertBetweenBases("100", 3, 2);
//9. Ввести число от 1 до 12. Вывести на консоль название месяца, соответствую
//щего данному числу. Осуществить проверку корректности ввод
        printMonthByNumber("8");
        printMonthByNumber("33");
        printMonthByNumber("Август");

        System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}


