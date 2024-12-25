package J8;

import java.util.*;

public class C {

        private static final Map<Character, String> DIGIT_TO_LETTERS = Map.of(
                '2', "ABC",
                '3', "DEF",
                '4', "GHI",
                '5', "JKL",
                '6', "MNO",
                '7', "PQRS",
                '8', "TUV",
                '9', "WXYZ"
        );

        public static void main(String[] args) {

            String phoneNumber = "888130295050550";

            String[] dictionaryWords = {"TUVA", "TUV"};

            String result = findLongestWord(phoneNumber, Arrays.asList(dictionaryWords));

            System.out.println("Максимальная подстрока, соответствующая слову из словаря:");
            System.out.println(result);
        }

        public static String findLongestWord(String phoneNumber, List<String> dictionary) {
            String longestWord = "";

            for (String word : dictionary) {
                if (matchesPhoneNumber(phoneNumber, word) && word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }

            return longestWord;
        }

        private static boolean matchesPhoneNumber(String phoneNumber, String word) {
            int phoneIndex = 0;
            for (char c : word.toUpperCase().toCharArray()) {
                boolean matched = false;

                while (phoneIndex < phoneNumber.length()) {
                    char digit = phoneNumber.charAt(phoneIndex);
                    if (DIGIT_TO_LETTERS.containsKey(digit) && DIGIT_TO_LETTERS.get(digit).indexOf(c) != -1) {
                        matched = true;
                        phoneIndex++;
                        break;
                    }
                    else{
                        return false;
                    }
                }

                if (!matched) {

                    return false;
                }
            }

            return true;
        }
}

