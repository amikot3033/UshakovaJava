package J2;

import java.util.Arrays;
import java.util.Comparator;

public class j2A {

    public static void findShortestAndLongest(String[] argumentsArray){
        System.out.println("1. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину:");
        String maxLenght = argumentsArray[0];
        String minLenght = argumentsArray[0];
        for (int i = 0; i < argumentsArray.length; i++) {
            if (argumentsArray[i].length() > maxLenght.length()) {
                maxLenght = argumentsArray[i];
            }
            if (argumentsArray[i].length() < minLenght.length()) {
                minLenght = argumentsArray[i];
            }
        }
        System.out.println("maxLenght " + maxLenght + " " + maxLenght.length());
        System.out.println("minLenght " + minLenght + " " + minLenght.length());
    }

    //2. Упорядочить и вывести числа в порядке возрастания (убывания) значений их длины.
    public static void sortNumbersByLength(String[] argumentsArray){
        System.out.println("2. Упорядочить и вывести числа в порядке возрастания (убывания) значений их длины:");
        String[] sortedByLengthArray = argumentsArray;
        Arrays.sort(sortedByLengthArray, Comparator.comparingInt(String::length));
        System.out.println("По возрастанию " + Arrays.toString(sortedByLengthArray));
        Arrays.sort(sortedByLengthArray, Comparator.comparingInt(String::length).reversed());
        System.out.println("По убыванию " + Arrays.toString(sortedByLengthArray));
    }

    //3. Вывести на консоль те числа, длина которых меньше (больше) средней,
    //а также длину.
    public static void printNumbersByAverageLength(String[] argumentsArray) {
        System.out.println("3. Вывести на консоль те числа, длина которых меньше (больше) средней, а также длину.");
        double averageLength = Arrays.stream(argumentsArray).mapToInt(String::length).average().orElse(0);
        System.out.println("Числа, длина которых больше средней: ");
        System.out.println("Средняя длина: " + averageLength);
        for (int i = 0; i < argumentsArray.length; i++) {
            if (argumentsArray[i].length() > averageLength) {
                System.out.println(argumentsArray[i] + " " + argumentsArray[i].length());
            }
        }
        System.out.println("Числа, длина которых меньше средней: ");
        for (int i = 0; i < argumentsArray.length; i++) {
            if (argumentsArray[i].length() < averageLength) {
                System.out.println(argumentsArray[i] + " " + argumentsArray[i].length());
            }
        }
    }

    //4. Найти число, в котором число различных цифр минимально. Если таких
    //чисел несколько, найти первое из них.

    public static int countUniqueNumbers(String str) {
        return (int) str.chars()
                .distinct()
                .count();
    }

    public static void findNumberWithMinimumUniqueDigits(String[] argumentsArray) {
        System.out.println("4. Найти число, в котором число различных цифр минимально. Если таких чисел несколько, найти первое из них.");

        int minimumUniqueDigits = 10;
        String numberWithMinimumUniqueDigits = "";

        for (int i = 0; i < argumentsArray.length; i++) {
            System.out.println(argumentsArray[i] + " " + countUniqueNumbers(argumentsArray[i]));
            if (minimumUniqueDigits > countUniqueNumbers(argumentsArray[i]))
            {
                minimumUniqueDigits = countUniqueNumbers(argumentsArray[i]);
                numberWithMinimumUniqueDigits = argumentsArray[i];
            }
        }
        System.out.println("Число с минимальным количеством различных цифр: " + numberWithMinimumUniqueDigits);

    }

    //5. Найти количество чисел, содержащих только четные цифры, а среди них —
    //количество чисел с равным числом четных и нечетных цифр.
    public static int countEven(String str) {
        return (int) str.chars()
                .filter(c -> (c - '0') % 2 == 0)
                .count();
    }
    public static int countOdd(String str) {
        return (int) str.chars()
                .filter(c -> (c - '0') % 2 == 1)
                .count();
    }
    public static void countEvenDigitNumbers(String[] argumentsArray) {
        System.out.println("5. Найти количество чисел, содержащих только четные цифры, а среди них количество чисел с равным числом четных и нечетных цифр");
        System.out.println("Числа, содержащие только четные цифры: ");
        int countEvenNumbers = 0;
        for (int i = 0; i < argumentsArray.length; i++) {
            boolean allEven = argumentsArray[i].chars().allMatch(c -> (c - '0') % 2 == 0);
            if (allEven)
            {
                System.out.println(argumentsArray[i]);
                countEvenNumbers++;
            }
        }
        System.out.println("Всего чисел, содержащих только четные числа: " + countEvenNumbers);

        System.out.println("Числа, с равным числом четных и не четных цифр: ");
        for (int i = 0; i < argumentsArray.length; i++) {
            if (countEven(argumentsArray[i]) == countOdd(argumentsArray[i]))
            {
                System.out.println(argumentsArray[i]);
            }
        }
    }

    //6. Найти число, цифры в котором идут в строгом порядке возрастания. Если
    //таких чисел несколько, найти первое из них
    public static void  findNumberWithAscendingDigits(String[] argumentsArray) {
        System.out.println("6. Найти число, цифры в котором идут в строгом порядке возрастания. Если таких чисел несколько, найти первое из них");
        System.out.println("Числа, цифры в которых идут в строгом порядке возрастания: ");
        for (int i = 0; i < argumentsArray.length; i++)
        {
            if (argumentsArray[i].length() > 1){
            boolean ascendingDigits = true;
            for (int j = 1; j < argumentsArray[i].length(); j++)
                if (argumentsArray[i].charAt(j) > argumentsArray[i].charAt(j-1))
                {
                    ascendingDigits = true;
                }
                else
                {
                    ascendingDigits = false;
                    break;
                }

            if (ascendingDigits == true) {
                System.out.println(argumentsArray[i]);
            }
            }
        }
    }

    //7. Найти число, состоящее только из различных цифр.
    // Если таких чисел несколько, найти первое из них.

    public static void  findNumberWithAllUniqueDigits(String[] argumentsArray) {
        System.out.println("7. Число, состоящее только из различных цифр: ");
        for (int i = 0; i < argumentsArray.length; i++) {
            if ((countUniqueNumbers(argumentsArray[i]) == argumentsArray[i].length()) && (argumentsArray[i].length() > 1))
            {
                System.out.println(argumentsArray[i]);
            }
        }
    }

    //8. Среди чисел найти число-палиндром. Если таких чисел больше одного,
    //найти второе.
    public static void  findNumberPalindrome(String[] argumentsArray) {
        System.out.println("8. Число-палиндром: ");
        String palinds = "";
        for (int i = 0; i < argumentsArray.length; i++) {
            String reversed = "";
            char[] current = argumentsArray[i].toCharArray();
            for (int j = current.length - 1; j >= 0; j--)
            {
                reversed +=  current[j];
            }
            if (argumentsArray[i].equals(reversed))
            {
                palinds += argumentsArray[i] + " ";
            }
        }
        String[] palindromes = palinds.split(" ");
        System.out.println(palindromes[1]);
    }

    //9. Найти корни квадратного уравнения. Параметры уравнения передавать
    //с командной строкой.
    public static void  findRoots(double a, double b, double c) {
        System.out.println("9. Найти корни квадратного уравнения. ");
        System.out.println(a + "*x^2" + " + " + b + "*x" + " + " + c);
        System.out.println("Корни: ");
        double D = b * b - (4 * a *  c);
        Double x1 = (- b + Math.pow(D, 0.5)) / (2 * a);
        Double x2 = (- b - Math.pow(D, 0.5)) / (2 * a);
        System.out.println("x1 = " + x1 + " x2 = " + x2);
    }


    public static void main(String[] args) {
//Ввести n чисел с консоли.
//        System.out.println("Ввести n чисел с консоли:");
//        Scanner scan = new Scanner(System.in);
        //String arguments = scan.nextLine();
        System.out.println("Числа: ");
        String arguments = "2 22 3333 1223 12221 1 3 4 5 876 678";
        System.out.println("Числа: " + arguments);
        String[] argumentsArray = arguments.split(" ");

    //1. Найти самое короткое и самое длинное число. Вывести найденные числа
    //и их длину
    findShortestAndLongest(argumentsArray);
    //2. Упорядочить и вывести числа в порядке возрастания (убывания) значений
    //их длины.
    sortNumbersByLength(argumentsArray);
    //3. Вывести на консоль те числа, длина которых меньше (больше) средней,
    //а также длину
    printNumbersByAverageLength(argumentsArray);
    //4. Найти число, в котором число различных цифр минимально. Если таких
    //чисел несколько, найти первое из них.
    findNumberWithMinimumUniqueDigits(argumentsArray);
    //5. Найти количество чисел, содержащих только четные цифры, а среди них —
    //количество чисел с равным числом четных и нечетных цифр.
    countEvenDigitNumbers(argumentsArray);
    //6. Найти число, цифры в котором идут в строгом порядке возрастания. Если
    //таких чисел несколько, найти первое из них.
    findNumberWithAscendingDigits(argumentsArray);
    //7. Найти число, состоящее только из различных цифр.
    // Если таких чисел несколько, найти первое из них.
    findNumberWithAllUniqueDigits(argumentsArray);
    //8. Среди чисел найти число-палиндром. Если таких чисел больше одного,
    //найти второе.
    findNumberPalindrome(argumentsArray);
    //9. Найти корни квадратного уравнения. Параметры уравнения передавать
    //с командной строкой.

    //        System.out.println("Ввести параметры квадратного уравнения чисел с консоли:");
    //        Scanner scan = new Scanner(System.in);
    //String abc = scan.nextLine();
    // String[] abcArray = arguments.split(" ");
    String[] abcArray = new String[] {"2", "3", "-27"};
    findRoots(Double.parseDouble(abcArray[0]), Double.parseDouble(abcArray[1]), Double.parseDouble(abcArray[2]));

    System.out.println("Разработчик: Ушакова Карина Б762-1");
    }
}
