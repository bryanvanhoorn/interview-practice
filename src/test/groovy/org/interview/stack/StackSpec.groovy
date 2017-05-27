package org.interview.stack

import spock.lang.Specification

class StackSpec extends Specification {

    /*
    Use a single array to implement 3 stacks
     */

    def "should be able to implement a stack using an array" () {
        given:
        ArrayBackedStack arrayBackedStack = new ArrayBackedStack()
        int valueToAdd = 1

        expect:
        assert arrayBackedStack.isEmpty() == true

        when:
        arrayBackedStack.push(valueToAdd)

        then:
        assert arrayBackedStack.isEmpty() == false

        when:
        int peekedValue = arrayBackedStack.peek()

        then:
        assert peekedValue == valueToAdd

        when:
        int poppedValue = arrayBackedStack.pop()

        then:
        assert poppedValue == valueToAdd
        assert arrayBackedStack.isEmpty() == true
    }

    /*
    How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element?
    Push, pop and min should all operate in O(1) time.
     */



}
