package org.interview.stack

import spock.lang.Specification

class SetOfStacksSpec extends Specification {

    def "should be able to push and pop on a set of stacks"() {
        given:
        SetOfStacks setOfStacks = new SetOfStacks()

        expect:
        assert setOfStacks.isEmpty() == true

        when:
        setOfStacks.push(1)

        then:
        assert setOfStacks.isEmpty() == false

        when:
        int peekedValue = setOfStacks.peek()

        then:
        assert peekedValue == 1
        assert setOfStacks.isEmpty() == false

        when:
        int poppedValue = setOfStacks.pop()

        then:
        assert poppedValue == 1
        assert setOfStacks.isEmpty() == true
    }

    def "should be able to see that a new stack is added and removed when needed"() {
        given:
        SetOfStacks setOfStacks = new SetOfStacks()

        expect:
        assert setOfStacks.isEmpty() == true
        assert setOfStacks.listOfStacks.size() == 1

        when:
        addElementsToStack(setOfStacks)

        then:
        assert setOfStacks.listOfStacks.size() == 2

        when:
        int poppedValue = setOfStacks.pop()

        then:
        assert poppedValue == 17
        assert setOfStacks.listOfStacks.size() == 1
    }

    def addElementsToStack(SetOfStacks setOfStacks) {
        setOfStacks.push(1)
        setOfStacks.push(2)
        setOfStacks.push(3)
        setOfStacks.push(4)
        setOfStacks.push(5)
        setOfStacks.push(6)
        setOfStacks.push(7)
        setOfStacks.push(8)
        setOfStacks.push(9)
        setOfStacks.push(10)
        setOfStacks.push(11)
        setOfStacks.push(12)
        setOfStacks.push(13)
        setOfStacks.push(14)
        setOfStacks.push(15)
        setOfStacks.push(16)
        setOfStacks.push(17)
    }

    def "should be able to pop a value from a specific sub-stack"() {
        given:
        SetOfStacks setOfStacks = new SetOfStacks()

        expect:
        assert setOfStacks.isEmpty() == true
        assert setOfStacks.listOfStacks.size() == 1

        when:
        addElementsToStack(setOfStacks)

        then:
        assert setOfStacks.listOfStacks.size() == 2

        when:
        int poppedValue = setOfStacks.popAt(0)

        then:
        assert poppedValue == 16
    }

    def "popAt() should throw an exception if we try to pop an index that doesn't exist"() {
        given:
        SetOfStacks setOfStacks = new SetOfStacks()

        when:
        setOfStacks.popAt(2)

        then:
        thrown(RuntimeException)
    }

    def "popAt() should cause all elements to be shifted down to fill in the gap"() {
        given:
        SetOfStacks setOfStacks = new SetOfStacks()

        expect:
        assert setOfStacks.isEmpty() == true
        assert setOfStacks.listOfStacks.size() == 1

        when:
        addElementsToStack(setOfStacks)

        then:
        assert setOfStacks.listOfStacks.size() == 2

        when:
        int poppedValue = setOfStacks.popAt(0)

        then:
        assert poppedValue == 16

        when:
        poppedValue = setOfStacks.popAt(0)

        then:
        assert poppedValue == 17
    }
}
