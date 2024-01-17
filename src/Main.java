// Prompt: Write a function canConstruct that takes in some target string, as well as an array of words in a word bank. 
//         Return a Boolean indicating whether or not it is possible to construct the target by concatenating together elements from the word bank.
//         You may reuse words from the word bank.

import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Common case
        printResults("abcdef", new String[] { "ab", "abc", "cd", "def", "abcd" });

        // Test with an empty string
        printResults("", new String[] { "cat", "dog", "bird" });

        // Test with a word that can't be constructed
        printResults("impossible", new String[] { "im", "pos", "sib", "le" });

        // Test with a single-character target
        printResults("a", new String[] { "a", "b", "c" });

        // Test with wordBank containing longer strings
        printResults("hello", new String[] { "hello world", "hello", "world" });

        // Test with an empty wordBank
        printResults("test", new String[] {});

        // Test with a target that matches exactly one word in the wordBank
        printResults("exactmatch", new String[] { "not", "exactmatch", "close" });

        // Test with repeated elements in wordBank
        printResults("repeat", new String[] { "re", "peat", "re", "at" });
    }

    static Boolean canConstruct(String target, String[] wordBank, HashMap<String, Boolean> memo) {
        // Encountering an empty string means we've successfully constructed the word
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target.isEmpty()) {
            return true;
        }

        for (String element : wordBank) {
            // If the wordBank contains a substring prefix, then remove that prefix from element and call recursively
            if (target.startsWith(element)) {
                String suffix = target.replaceFirst(element, "").trim();
                if (canConstruct(suffix, wordBank, memo)) {
                    memo.put(target, true);
                    return true;
                }
            }
        }
        memo.put(target, false);
        return false;
    }

    static void printResults(String target, String[] wordBank) {
        // StringBuilder for efficient concatenation
        StringBuilder outcome = new StringBuilder();

        outcome.append("\nTarget: ").append(target).append("\nWord Bank: ").append(getArrayAsString(wordBank)).append("\nResult: ").append(canConstruct(target, wordBank, new HashMap<String, Boolean>()));

        System.out.println(outcome.toString());
    }

    static String getArrayAsString(String[] wordBank) {
        String resultString = (wordBank != null)
            ? Arrays.stream(wordBank).map(String::valueOf).collect(Collectors.joining(", ", "[ ", " ]"))
            : "Empty Word Bank\n";

        return resultString;
    }
}