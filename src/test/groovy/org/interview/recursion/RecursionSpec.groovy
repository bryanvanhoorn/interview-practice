package org.interview.recursion

import spock.lang.Specification
import spock.lang.Unroll

class RecursionSpec extends Specification {

    @Unroll
    def "should be able to count different combinations for running up the stairs"() {
        when:
        int actualCombinations = RecursionUtils.countPossibleCombinations(n)

        then:
        assert actualCombinations == possibleCombinations

        where:
        n | possibleCombinations
        0 | 1
        1 | 1
        2 | 2
        3 | 4
        4 | 7
        5 | 13
    }

    @Unroll
    def "should be able to find the magic index in a sorted array"() {
        when:
        int actualMagicIndex = RecursionUtils.findMagicIndex(sortedArray, 0, sortedArray.length -1)

        then:
        assert actualMagicIndex == expectedMagicIndex

        where:
        sortedArray                       | expectedMagicIndex
        [1, 2, 3] as int[]                | -1
        [0, 3, 4] as int[]                | 0
        [0, 0, 1, 2, 4, 6, 7, 8] as int[] | 4
        [0, 0, 1, 2, 6, 6, 7, 8] as int[] | 0
    }

}
