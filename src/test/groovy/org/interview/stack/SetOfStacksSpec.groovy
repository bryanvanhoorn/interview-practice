package org.interview.stack

import spock.lang.Specification

class SetOfStacksSpec extends Specification {

    /*
Imagine a literal stack of plates.  If the stack gets too high, it might topple, so we would reasonably create
another stack.  Implement a data structure SetOfStacks that mimics this.  Should be composed of several
stacks and should create a new stack once the previous one exceeds capacity.  Push() and pop() should appear
to have standard functionality.

BONUS: Implement a function popAt(index) that performs a pop operation on a specific sub-stack.
 */

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

    def "should be able to see that a new stack is added and removed when needed" () {
        given:
        SetOfStacks setOfStacks = new SetOfStacks()

        expect:
        assert setOfStacks.isEmpty() == true
        assert setOfStacks.listOfStacks.size() == 1

        when:
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)
        setOfStacks.push(1)

        then:
        assert setOfStacks.listOfStacks.size() == 2

        when:
        int poppedValue = setOfStacks.pop()

        then:
        assert poppedValue == 1
        assert setOfStacks.listOfStacks.size() == 1
    }

}
