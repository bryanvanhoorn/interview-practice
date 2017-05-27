package org.interview.stack

import spock.lang.Specification

class StackSpec extends Specification {
    
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

    def "should be able to implement 3 stacks using single array" () {
        given:
        ArrayBackedMultiStack arrayBackedMultiStack = new ArrayBackedMultiStack(3)

        expect:
        assert arrayBackedMultiStack.isEmpty(0) == true
        assert arrayBackedMultiStack.isEmpty(1) == true
        assert arrayBackedMultiStack.isEmpty(2) == true

        when: "I push a value onto one stack"
        arrayBackedMultiStack.push(0, 1)

        then:
        assert arrayBackedMultiStack.isEmpty(0) == false
        assert arrayBackedMultiStack.isEmpty(1) == true
        assert arrayBackedMultiStack.isEmpty(2) == true

        when: "I push a value onto another stack"
        arrayBackedMultiStack.push(2, 8)

        then:
        assert arrayBackedMultiStack.isEmpty(0) == false
        assert arrayBackedMultiStack.isEmpty(1) == true
        assert arrayBackedMultiStack.isEmpty(2) == false

        when: "I pop a value from one stack"
        int poppedValue = arrayBackedMultiStack.pop(0)

        then:
        assert poppedValue == 1
        assert arrayBackedMultiStack.isEmpty(0) == true
        assert arrayBackedMultiStack.isEmpty(1) == true
        assert arrayBackedMultiStack.isEmpty(2) == false
    }

    def "should throw exception if we try to push when backing array is full" () {
        given:
        ArrayBackedMultiStack arrayBackedMultiStack = new ArrayBackedMultiStack(3)

        when: "I push too many items on to the stacks"
        for(int i = 0; i < 30; i++) {
            arrayBackedMultiStack.push(0, i)
        }

        for(int i = 0; i < 30; i++) {
            arrayBackedMultiStack.push(1, i)
        }

        for(int i = 0; i < 30; i++) {
            arrayBackedMultiStack.push(2, i)
        }

        then:
        thrown(RuntimeException)
    }

    def "should throw exception if we try to pop from an empty stack" () {
        given:
        ArrayBackedMultiStack arrayBackedMultiStack = new ArrayBackedMultiStack(3)

        when:
        int poppedValue = arrayBackedMultiStack.pop(0)

        then:
        thrown(RuntimeException)
    }

    def "should be able to peek at top of stack" () {
        given:
        ArrayBackedMultiStack arrayBackedMultiStack = new ArrayBackedMultiStack(3)
        arrayBackedMultiStack.push(0, 5)

        when:
        int peekedValue = arrayBackedMultiStack.peek(0)

        then:
        assert peekedValue == 5
        assert arrayBackedMultiStack.isEmpty(0) == false
    }

    /*
    How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element?
    Push, pop and min should all operate in O(1) time.
     */



}
