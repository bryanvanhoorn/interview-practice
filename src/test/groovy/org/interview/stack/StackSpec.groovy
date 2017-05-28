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
    def "should be able to peek at the bottom of the stack" () {
        given:
        ArrayBackedMultiStack arrayBackedMultiStack = new ArrayBackedMultiStack(3)
        arrayBackedMultiStack.push(0, 5)
        arrayBackedMultiStack.push(0, 4)
        arrayBackedMultiStack.push(0, 3)
        arrayBackedMultiStack.push(0, 2)

        when:
        int peekedValue = arrayBackedMultiStack.peekBottom(0)

        then:
        assert peekedValue == 5

        when:
        int poppedValue = arrayBackedMultiStack.pop(0)
        poppedValue = arrayBackedMultiStack.pop(0)
        poppedValue = arrayBackedMultiStack.pop(0)
        poppedValue = arrayBackedMultiStack.pop(0)

        then:
        assert poppedValue == 5
        assert arrayBackedMultiStack.isEmpty(0) == true

        when:
        arrayBackedMultiStack.peekBottom(0)

        then:
        thrown(RuntimeException)
    }

    def "should be able to ask for the minimum value in the stack" () {
        given:
        ArrayBackedMultiStack arrayBackedMultiStack = new ArrayBackedMultiStack(3)
        arrayBackedMultiStack.push(0, 5)
        arrayBackedMultiStack.push(0, 4)

        when:
        int minValue = arrayBackedMultiStack.min(0)

        then:
        assert minValue == 4

        when:
        arrayBackedMultiStack.push(0, 3)
        minValue = arrayBackedMultiStack.min(0)

        then:
        assert minValue == 3

        when:
        int poppedValue = arrayBackedMultiStack.pop(0)
        minValue = arrayBackedMultiStack.min(0)

        then:
        assert poppedValue == 3
        assert minValue == 4
    }






    /*
    Implement a MyQueue class which implements a queue using two stacks.
     */

    /*
    Write a program to sort a stack so that the smallest items are on the top.  Can use an additional temporary stack,
    but may not copy the elements into any other data structure, (such as an array).  Stack supports push, pop, peek and isEmpty
     */

    /*
    Animal shelter allows people to adopt dogs or cats based on FIFO.  People must adopt the oldest (based on arrival
    time) lf all animals at the shelter, or they can select dog or cat, and will get the oldest of the type
    they choose.  Cannot select which specific animal.  Create the data structures to maintain this system and
    implement operations such as enqueue, dequeueAny, dequeueDog and dequeueCat.  May use LinkedList.
     */
}
