package ru.raif;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ListUtils {
    public static void main(String[] args) {
        printUniqueWordsCountInList(Arrays.asList("Bob", "Alice", "Joe", "bob", "alice", "dEN"));
    }

    public static Map<String, Integer> printUniqueWordsCountInList(List<String> list) {
        list = list.stream()
                .map(ListUtils::convertWordToFormatWithLargeFirstLetterAndLittleOthers)
                .collect(Collectors.toList());

        Map<String, Integer> wordsCountMap = new TreeMap();
        for (String word : list) {
            if (wordsCountMap.containsKey(word)) {
                wordsCountMap.put(word, wordsCountMap.get(word) + 1);
            } else {
                wordsCountMap.put(word, 1);
            }
        }
        System.out.println(wordsCountMap);
        return wordsCountMap;
    }

    public static String convertWordToFormatWithLargeFirstLetterAndLittleOthers(String str) {
        if (str.length() > 1) {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
        } else if (str.length() == 1) {
            return String.valueOf(Character.toUpperCase(str.charAt(0)));
        } else return str;
    }
}
