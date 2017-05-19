package org.interview.string

import spock.lang.Specification
import spock.lang.Unroll
import org.interview.string.StringChecker

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
}
