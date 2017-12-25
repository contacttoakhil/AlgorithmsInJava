package main.test.java.strings;

import main.java.strings.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStrings {

    private static final String SAMPLE_STRING = "This is an apple";

    @Test
    public void testStrings()
    {
        testReverseWordsInString();
        testReverseWordCharactersInString();
        testLevenshteinDistance();
    }

    private void testReverseWordsInString() {
        assertEquals("apple an is This", StringUtils.reverseWordsInString(SAMPLE_STRING));
    }

    private void testReverseWordCharactersInString() {
        assertEquals("sihT si na elppa", StringUtils.reverseWordCharacters(SAMPLE_STRING));
    }

    private void testLevenshteinDistance() {
        assertEquals(0, StringUtils.levenshteinDistance("",""));
        assertEquals(1, StringUtils.levenshteinDistance("","a"));
        assertEquals(1, StringUtils.levenshteinDistance("frog", "fog"));
        assertEquals(7, StringUtils.levenshteinDistance("elephant", "hippo"));
        assertEquals(7, StringUtils.levenshteinDistance("aaapppp", ""));
    }
}
