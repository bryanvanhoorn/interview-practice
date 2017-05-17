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

    public static boolean containsAllUniqueCharactersNoOtherDataStructures(String inputString) {

        for (int i = 0; i < inputString.length(); i++) {
            char charToCheck = inputString.charAt(i);

            for(int j = i +1; j < inputString.length(); j++) {
                if (charToCheck == inputString.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }
}
