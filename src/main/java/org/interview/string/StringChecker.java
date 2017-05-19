package org.interview.string;

import java.util.*;

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

            for (int j = i + 1; j < inputString.length(); j++) {
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
        for (int i = 0; i < string1Array.length; i++) {
            if (string1CharCounts.containsKey(string1Array[i])) {
                int currentCount = string1CharCounts.get(string1Array[i]);
                string1CharCounts.put(string1Array[i], currentCount++);
            } else {
                string1CharCounts.put(string1Array[i], 1);
            }
        }

        char[] string2Array = inputString2.toCharArray();
        for (int i = 0; i < string2Array.length; i++) {
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
        for (int i = 0; i < trueLength; i++) {
            if (inputStringArray[i] == ' ') {
                numberOfSpaces++;
            }
        }

        int index = trueLength + (numberOfSpaces * 2);

        for (int i = trueLength - 1; i >= 0; i--) {
            if (inputStringArray[i] == ' ') {
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

    public static boolean isPermutationOfPalindrome(String inputString) {
        Map<Character, Integer> characterCounterMap = new HashMap<Character, Integer>();
        char[] inputStringArray = inputString.toCharArray();
        for (int i = 0; i < inputStringArray.length; i++) {
            Character character = Character.valueOf(inputStringArray[i]);
            if (characterCounterMap.containsKey(character)) {
                Integer currentCount = characterCounterMap.get(character);
                currentCount = currentCount + 1;
                characterCounterMap.put(character, currentCount);
            } else {
                characterCounterMap.put(character, 1);
            }
        }

        // if 0 or 1 letter have odd-numbered occurrences and all others are even, we're good
        int oddNumberedLetters = 0;
        for (Integer count : characterCounterMap.values()) {
            if (count % 2 != 0) {
                oddNumberedLetters++;
            }
        }

        if (oddNumberedLetters <= 1) {
            return true;
        }

        return false;
    }

    public static boolean isZeroOrOneEditsAway(String inputString1, String inputString2) {
        // iterate char by char
        // check if insert helps first
        // if we make more than one edit, break and return false
        if (inputString1.equals(inputString2)) {
            return true;
        }

        int numberOfDifferences = 0;
        // if strings are same length, replacement is our only option
        if (inputString1.length() == inputString2.length()) {
            // iterate char by char
            char[] inputString1Array = inputString1.toCharArray();
            char[] inputString2Array = inputString2.toCharArray();
            for (int i = 0; i < inputString1Array.length; i++) {
                if (inputString1Array[i] != inputString2Array[i]) {
                    numberOfDifferences++;
                }
            }
        } else {
            // if strings are different length, we'll need to insert or remove
            // iterate char by char
            char[] shorterStringArray;
            char[] longerStringArray;
            if (inputString1.length() < inputString2.length()) {
                shorterStringArray = inputString1.toCharArray();
                longerStringArray = inputString2.toCharArray();
            } else {
                shorterStringArray = inputString2.toCharArray();
                longerStringArray = inputString1.toCharArray();
            }

            if (longerStringArray.length - shorterStringArray.length >= 2) {
                return false;
            }

            int index1 = 0, index2 = 0;
            while (index1 < shorterStringArray.length) {
                if (shorterStringArray[index1] == longerStringArray[index2]) {
                    index1++;
                    index2++;
                } else {
                    numberOfDifferences++;
                    index1++;
                    index2 += 2;
                }
            }
        }

        if (numberOfDifferences <= 1) {
            return true;
        }

        return false;
    }

    public static String compressString(String inputString) {
        char currentCharacter = inputString.charAt(0);
        int currentCharacterCounter = 0;
        StringBuilder outputStringBuilder = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == currentCharacter) {
                currentCharacterCounter++;
            } else {
                outputStringBuilder.append(currentCharacter);
                outputStringBuilder.append(currentCharacterCounter);
                currentCharacter = inputString.charAt(i);
                currentCharacterCounter = 1;
            }
        }
        outputStringBuilder.append(currentCharacter);
        outputStringBuilder.append(currentCharacterCounter);

        if (outputStringBuilder.length() < inputString.length()) {
            return outputStringBuilder.toString();
        } else {
            return inputString;
        }
    }

    public static int[][] zeroOutRowsAndColumnsContainingZeroes(int[][] inputMatrix) {
        Set<Integer> rowsToZeroOut = new HashSet<Integer>();
        Set<Integer> columnsToZeroOut = new HashSet<Integer>();
        // search through the matrix and find rows/columns containing 0
        for (int x = 0; x < inputMatrix.length; x++) {
            for (int y = 0; y < inputMatrix[x].length; y++) {
                if (inputMatrix[x][y] == 0) {
                    rowsToZeroOut.add(x);
                    columnsToZeroOut.add(y);
                }
            }
        }

        for (Integer rowNumber : rowsToZeroOut) {
            for (int y = 0; y < inputMatrix[rowNumber].length; y++) {
                inputMatrix[rowNumber][y] = 0;
            }
        }

        for (Integer columnNumber : columnsToZeroOut) {
            for (int x = 0; x < inputMatrix.length; x++) {
                inputMatrix[x][columnNumber] = 0;
            }
        }

        return inputMatrix;
    }

    public static boolean isRotation(String string1, String string2) {
        String stringToCheck = string1 + string1;

        return isSubstring(string2, stringToCheck);
    }

    private static boolean isSubstring(String string1, String string2) {
        return string2.contains(string1);
    }

}
