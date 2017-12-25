package main.java.strings;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {
    private static final String EMPTY_STRING = "";

    /**
     * reverse the input string
     * @param string string to be reversed
     * @return the reverse of the input string
     */
    public static String reverse(String string) {
        Objects.requireNonNull(string);
        return new StringBuilder(string).reverse().toString();
    }

    /**
     * join the strings into a string using delimiter
     * @param stringList list of string to be joined
     * @param delimiter delimiter to use
     * @return joined string using delimiter
     */
    public static String join(List<String> stringList, String delimiter) {
        final StringJoiner stringJoiner = new StringJoiner(delimiter);
        for(String string : stringList) {
            stringJoiner.add(string);
        }
        return stringJoiner.toString();
    }

    /**
     * join the strings into a string using delimiter while making use of streams.
     * @param stringList list of string to be joined
     * @param delimiter delimiter to use
     * @return joined string using delimiter
     */
    public static String joinUsingStreams(List<String> stringList, String delimiter) {
        return stringList.stream().collect(Collectors.joining(delimiter));
    }

    /**
     * join the list of E(bounded type) using delimiter while making use of streams. The toString method is use on each item to get spring representation.
     * @param list list of obejcts to be joined
     * @param delimiter delimiter to use
     * @return joined string using delimiter
     */
    public  static <E> String joinObjects(List<E> list, String delimiter) {
        return list.stream().map(Object::toString).collect(Collectors.joining(delimiter));
    }


    /**
     * This method will reverse the words order in a sentence passed to this method.
     * For example - if input is "This is an apple" output would be "apple an is This".
     * Source: https://stackoverflow.com/a/34175771/1216775
     * @param str sentence whose words need to be reversed in order
     * @return sentence in which order of words is reversed.
     *
     */
    public static String reverseWordsInString(String str) { // original source: https://stackoverflow.com/a/34175771/1216775
        List<String> revWordsList = Pattern.compile(" +").splitAsStream(str)
                .collect(LinkedList::new, LinkedList::addFirst, (a,b)->a.addAll(0, b));
        return String.join(" ", revWordsList);
    }

    /**
     * This method will reverse the words order in a sentence passed to this method.
     * For example - if input is "This is an apple" output would be "sihT si na elppa". Source: https://stackoverflow.com/a/34175771/1216775
     * @param str sentence whose characters of words need to be reversed in order
     * @return sentence in which order of characters of words is reversed.
     */
    public static String reverseWordCharacters(String str) {
        return Pattern.compile(" +").splitAsStream(str)
                .map(word->new StringBuilder(word).reverse())
                .collect(Collectors.joining(" "));
    }

    /**
     * Returns true is string is palindrome else false.
     * @param string string to verify
     * @return true if palindrome else false.
     */
    private static boolean isPalindrome(String string) {
        int n = string.length();
        for (int i = 0; i < (n/2); ++i) {
            if (string.charAt(i) != string.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This implementation needs extra storage as it makes use of StringBuilder.
     * @param string string to verify
     * @return true if palindrome else false.
     */
    private static boolean isPalindromeUsingBuilder(String string) {
        return string.equals(new StringBuilder(string).reverse().toString());
    }

    /**
     * Informally, the Levenshtein distance between two words is the minimum number of single-character edits (insertions, deletions or substitutions) required to change one word into the other. This program finds the Levenshtein distance between two Strings.
     * Source: https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
     * @param lhs
     * @param rhs
     * @return
     */
    public static int levenshteinDistance(String lhs, String rhs) {
        int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];

        for (int i = 0; i <= lhs.length(); i++)
            distance[i][0] = i;
        for (int j = 1; j <= rhs.length(); j++)
            distance[0][j] = j;

        for (int i = 1; i <= lhs.length(); i++)
            for (int j = 1; j <= rhs.length(); j++)
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));

        return distance[lhs.length()][rhs.length()];
    }
    private static int minimum(int i, int j, int k) {
        return Math.min( Math.min(i,j), k);
    }
}
