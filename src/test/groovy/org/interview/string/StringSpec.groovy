package org.interview.string

import org.interview.array.ArrayUtils
import spock.lang.Specification
import spock.lang.Unroll

class StringSpec extends Specification {

    @Unroll
    def "should identify if string has all unique characters"() {
        when:
        boolean actualOutputValue = StringChecker.containsAllUniqueCharacters(inputString)

        then:
        assert expectedOutputValue == actualOutputValue

        where:
        inputString | expectedOutputValue
        "abcde"     | true
        "abcdc"     | false
    }

    @Unroll
    def "should identify if string has all unique characters without using extra data structures"() {
        when:
        boolean actualOutputValue = StringChecker.containsAllUniqueCharactersNoOtherDataStructures(inputString)

        then:
        assert expectedOutputValue == actualOutputValue

        where:
        inputString | expectedOutputValue
        "abcde"     | true
        "abcdc"     | false
    }

    // Given two strings, write a method to determine if one is a permutation of the other
    // if one string is a permutation of the other, it contains all the same characters, likely in a different order
    @Unroll
    def "should identify if one string is a permutation of another"() {
        when:
        boolean actualOutputValue = StringChecker.isPermutation(inputString1, inputString2)

        then:
        assert expectedOutputValue == actualOutputValue

        where:
        inputString1 | inputString2 | expectedOutputValue
        "act"        | "cat"        | true
        "bad"        | "abc"        | false
    }

    @Unroll
    def "should identify if one string is a permutation of another using a HashTable"() {
        when:
        boolean actualOutputValue = StringChecker.isPermutationWithHashTable(inputString1, inputString2)

        then:
        assert expectedOutputValue == actualOutputValue

        where:
        inputString1 | inputString2 | expectedOutputValue
        "act"        | "cat"        | true
        "bad"        | "abc"        | false
    }

    @Unroll
    def "should be able to replace all spaces with '%20'"() {
        when:
        String actualUrlifiedString = StringChecker.urlifyString(inputString, trueLength)

        then:
        assert expectedUrlifiedString == actualUrlifiedString

        where:
        inputString                               | trueLength | expectedUrlifiedString
        "this is a string      "                  | 16         | "this%20is%20a%20string"
        "this has  multiple   spaces            " | 27         | "this%20has%20%20multiple%20%20%20spaces"
        "abcde"                                   | 5          | "abcde"
    }

    @Unroll
    def "should be able to check if a string is a permutation of a palindrome"() {
        when:
        boolean actualOutput = StringChecker.isPermutationOfPalindrome(inputString)

        then:
        assert expectedOutput == actualOutput

        where:
        inputString | expectedOutput
        "aacctto"   | true
        "aocctt"    | false
        "racecar"   | true
        "abcde"     | false
    }

    /*
    3 types of operations:  insert, replace or remove a char
    given 2 strings, write a function to check if these strings are 0 or 1 edits away
     */

    @Unroll
    def "should be able to check if 2 strings or 0 or 1 edits away from each other"() {
        when:
        boolean actualOutput = StringChecker.isZeroOrOneEditsAway(inputString1, inputString2)

        then:
        assert expectedOutput == actualOutput

        where:
        inputString1 | inputString2 | expectedOutput
        "pale"       | "ple"        | true
        "pale"       | "pales"      | true
        "pale"       | "bale"       | true
        "pale"       | "pale"       | true
        "pale"       | "bake"       | false
        "pale"       | "palest"     | false
    }

    @Unroll
    def "should be able to use simple compression on a string of upper and lower case letters"() {
        when:
        String actualOutputString = StringChecker.compressString(inputString)

        then:
        assert expectedOutputString == actualOutputString

        where:
        inputString   | expectedOutputString
        "aabcccccaaa" | "a2b1c5a3"
        "aabbcc"      | "aabbcc"
        "abc"         | "abc"
        "aaaaa"       | "a5"
        "aabCccccaaa" | "a2b1C1c4a3"
    }

    /*
        http://stackoverflow.com/questions/42519/how-do-you-rotate-a-two-dimensional-array
     */

    def "should be able to rotate NxN array 90 degrees"() {
        given:
        int[][] inputMatrix = get2DMatrix()

        when:
        int[][] outputMatrix = ArrayUtils.rotate2DMatrix(inputMatrix)

        then:
        outputMatrix[0][0] == 3
        outputMatrix[0][1] == 1
        outputMatrix[1][0] == 4
        outputMatrix[1][1] == 2

    }

    int[][] get2DMatrix() {

        int[][] intMatrix = [
                [1, 2],
                [3, 4]
        ]

        return intMatrix
    }

    /*
    Zero Matrix:  Write an algorithm such that if an element in an MxN matrix is 0,
    its entire row and column are set to 0
     */

    def "if an element in a matrix is 0, we should 0 out the entire row and column"() {
        given:
        int[][] inputMatrix = get4x4Matrix()

        when:
        int[][] actualOutputMatrix = StringChecker.zeroOutRowsAndColumnsContainingZeroes(inputMatrix)

        then:
        for (int x = 0; x < actualOutputMatrix.length; x++) {
            for (int y = 0; y < actualOutputMatrix[0].length; y++) {
                int valueToCheck = actualOutputMatrix[x][y]
                if (x == 2 || y == 2) {
                    assert valueToCheck == 0
                } else {
                    assert valueToCheck != 0
                }
            }
        }
    }

    int[][] get4x4Matrix() {

        return [
                [1, 2, 3, 4],
                [5, 6, 7, 8],
                [9, 10, 0, 12],
                [13, 14, 15, 16]
        ]
    }

    @Unroll
    def "should be able to check if string1 is a rotation of string2"() {
        when:
        boolean actualOutput = StringChecker.isRotation(string1, string2)

        then:
        assert expectedOutput == actualOutput

        where:
        string1       | string2       | expectedOutput
        "waterbottle" | "erbottlewat" | true
        "waterbottle" | "rebottlewat" | false
    }
}
