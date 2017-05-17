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


}
