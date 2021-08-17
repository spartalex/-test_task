package ru.raif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static ru.raif.ListUtils.convertWordToFormatWithLargeFirstLetterAndLittleOthers;
import static ru.raif.ListUtils.printUniqueWordsCountInList;

public class ListUtilsTest {
    @Test
    public void wordsCountingTest() {
        Map<String, Integer> resultMap = printUniqueWordsCountInList(
                Arrays.asList("january", "FebrUaRy", "aa", "febrUary", "april", "a", "", "AA"));
        assertThat(resultMap.get("Aa"), equalTo(2));
        assertThat(resultMap.get("January"), equalTo(1));
        assertThat(resultMap.get("test"), equalTo(null));
    }

    @ParameterizedTest
    @CsvSource({"test,Test", "tEst,Test", "a,A"})
    public void wordTransformationTest(String wordBeforeTransformation, String wordAfterTransformation) {
        assertThat(convertWordToFormatWithLargeFirstLetterAndLittleOthers(wordBeforeTransformation),
                equalTo(wordAfterTransformation));
    }

    @Test
    public void emptyWordTransformationTest() {
        assertThat(convertWordToFormatWithLargeFirstLetterAndLittleOthers(""), equalTo(""));
    }

    @Test
    public void wordsSortingTest() {
        Map<String, Integer> resultMap = printUniqueWordsCountInList(
                Arrays.asList("january", "FebrUaRy", "aa", "febrUary", "april", "a", "", "AA"));
        List<String> sortedWords = new ArrayList<>(resultMap.keySet());

        for (int i = 0; i < sortedWords.size() - 1; i++) {
            assertThat(sortedWords.get(i).compareTo(sortedWords.get(i + 1)), lessThanOrEqualTo(0));
        }
    }
}
