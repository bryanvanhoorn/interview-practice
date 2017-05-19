package org.interview.string;

import java.util.Arrays;
import java.util.Hashtable;

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

    public static boolean isPermutation(String inputString1, String inputString2) {
        char[] string1Array = inputString1.toCharArray();
        char[] string2Array = inputString2.toCharArray();
        Arrays.sort(string1Array);
        Arrays.sort(string2Array);

        return Arrays.equals(string1Array, string2Array);
    }

    public static boolean isPermutationWithHashTable(String inputString1, String inputString2) {
        Hashtable<Character, Integer> string1CharCounts = new Hashtable<Character, Integer>();

        char[] string1Array = inputString1.toCharArray();
        for(int i = 0; i < string1Array.length; i++) {
            if (string1CharCounts.containsKey(string1Array[i])) {
                int currentCount = string1CharCounts.get(string1Array[i]);
                string1CharCounts.put(string1Array[i], currentCount++);
            } else {
                string1CharCounts.put(string1Array[i], 1);
            }
        }

        char[] string2Array = inputString2.toCharArray();
        for(int i = 0; i < string2Array.length; i++) {
            if (string1CharCounts.containsKey(string2Array[i])) {
                int currentCount = string1CharCounts.get(string2Array[i]);
                if (currentCount == 0) {
                    // not expecting any more instances of this character
                    return false;
                }
                string1CharCounts.put(string2Array[i], currentCount--);
            } else {
                return false;
            }
        }

        return true;
    }

    public static String urlifyString(String inputString, int trueLength) {
        // Too easy  :-)
        // /inputString = inputString.replaceAll(" ", "%20");

        char[] inputStringArray = inputString.toCharArray();

        int numberOfSpaces = 0;
        for(int i = 0; i < trueLength; i++) {
            if (inputStringArray[i] == ' ') {
                numberOfSpaces++;
            }
        }

        int index = trueLength + (numberOfSpaces * 2);

        for(int i = trueLength - 1; i >= 0; i--) {
            if(inputStringArray[i] == ' ') {
                inputStringArray[index - 1] = '0';
                inputStringArray[index - 2] = '2';
                inputStringArray[index - 3] = '%';
                index -= 3;
            } else {
                inputStringArray[index - 1] = inputStringArray[i];
                index--;
            }
        }

        return String.valueOf(inputStringArray);
    }

}
