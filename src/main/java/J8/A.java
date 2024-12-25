package J8;

import java.util.Scanner;

public class A {
        public static void main(String[] args) {

            String text = "hello *world* !! *jj* *";

            char startChar = '*';

            System.out.println("Введите символ конца:");
            char endChar = '*';

            String result = removeTextBetween(text, startChar, endChar);

            System.out.println("Результат:");
            System.out.println(result);
        }

        public static String removeTextBetween(String text, char startChar, char endChar) {
            String start = escapeSpecialCharacter(startChar);
            String end = escapeSpecialCharacter(endChar);

            String regex = start + ".*?" + end;
            return text.replaceAll(regex, "");
        }

        private static String escapeSpecialCharacter(char c) {
            //экранирование символов
            if ("[](){}.*+?^$|".indexOf(c) != -1) {
                return "\\" + c;
            }
            return String.valueOf(c);
        }
    }


