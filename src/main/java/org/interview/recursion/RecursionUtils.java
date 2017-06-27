package org.interview.recursion;

import java.util.Arrays;

public class RecursionUtils {

    public static int countPossibleCombinations(int numberOfStairs) {
        if (numberOfStairs < 0) {
            return 0;
        } else if (numberOfStairs == 0) {
            return 1;
        } else {
            return countPossibleCombinations(numberOfStairs - 1) +
                    countPossibleCombinations(numberOfStairs - 2) +
                    countPossibleCombinations(numberOfStairs - 3);
        }
    }

    public static int findMagicIndex(int[] array, int startIndex, int endIndex) {
        // cut the array in half
        // if length/2 element is greater than length/2, look to the right
        // else, look to the left

        if (endIndex < startIndex) {
            return -1;
        }

        if (endIndex == startIndex && array[startIndex] != startIndex) {
            return -1;
        }

        int arrayLength = (endIndex - startIndex) + 1;
        int middleIndex = Math.round(arrayLength/2);

        if (middleIndex == array[middleIndex]) {
            return middleIndex;
        }

        int leftIndex = Math.min(middleIndex - 1, array[middleIndex]);
        int left = findMagicIndex(array, startIndex, leftIndex);
        if (left >= 0) {
            return left;
        }

        int rightIndex = Math.max(middleIndex + 1, array[middleIndex]);
        int right = findMagicIndex(array, rightIndex, endIndex);

        return right;

    }

}
