package J8;

import java.util.*;

public class B {

    public static void main(String[] args) {
        String text = "Арбуз обои яблоко этаж Балкон арена";

        List<String> sortedWords = sortWordsByFirstConsonant(text);

        System.out.println("Отсортированные слова:");
        for (String word : sortedWords) {
            System.out.println(word);
        }
    }

    public static List<String> sortWordsByFirstConsonant(String text) {
        String vowels = "аеёиоуыэюяАЕЁИОУЫЭЮЯ";
        String consonants = "бвгджзйклмнпрстфхцчшщБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";

        String[] words = text.split("\\s+");

        List<String> vowelWords = new ArrayList<>();
        for (String word : words) {
            if (!word.isEmpty() && vowels.indexOf(word.charAt(0)) != -1) {
                vowelWords.add(word);
            }
        }

        vowelWords.sort((word1, word2) -> {
            char firstConsonant1 = findFirstConsonant(word1, consonants);
            char firstConsonant2 = findFirstConsonant(word2, consonants);
            return Character.compare(firstConsonant1, firstConsonant2);
        });

        return vowelWords;
    }

    private static char findFirstConsonant(String word, String consonants) {
        for (char c : word.toCharArray()) {
            if (consonants.indexOf(c) != -1) {
                return c;
            }
        }
        return '\u0000'; // null-символ
    }
}
