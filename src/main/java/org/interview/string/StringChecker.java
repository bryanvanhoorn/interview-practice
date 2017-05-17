package org.interview.string;

public class StringChecker {

    public static boolean containsAllUniqueCharacters(String inputString) {

        boolean[] seenCharacters = new boolean[128];

        for (int i = 0; i < inputString.length(); i++) {
            if (seenCharacters[inputString.charAt(i)]) {
                return false;
            }

            seenCharacters[inputString.charAt(i)] = true;
        }

        return true;
    }
}
