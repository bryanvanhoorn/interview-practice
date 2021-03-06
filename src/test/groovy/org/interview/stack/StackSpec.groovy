package org.interview.stack

import org.interview.animal.AnimalShelter
import org.interview.animal.Cat
import org.interview.animal.Dog
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

    def "should be able to add and remove items from a stack-backed queue"() {
        given:
        MyQueue queue = new MyQueue()

        when:
        queue.add(1)

        then:
        assert queue.peek() == 1

        when:
        queue.add(2)
        queue.add(3)
        queue.add(4)

        then:
        assert queue.peek() == 1

        when:
        int removedValue = queue.remove()

        then:
        assert removedValue == 1
        assert queue.peek() == 2
    }

    def "should be able to push and pop items on a sorted stack" () {
        given:
        SortedStack sortedStack = new SortedStack()
        assert sortedStack.isEmpty() == true

        when:
        sortedStack.push(1)
        sortedStack.push(2)

        then:
        assert sortedStack.isEmpty() == false
        assert sortedStack.peek() == 1

        when:
        int poppedValue = sortedStack.pop()

        then:
        assert poppedValue == 1
        assert sortedStack.peek() == 2

        when:
        sortedStack.push(1)

        then:
        assert sortedStack.peek() == 1
    }

    def "should be able to enqueue and dequeue generic animals from the animal shelter"() {
        given:
        AnimalShelter animalShelter = new AnimalShelter()

        when:
        Cat cat = new Cat("Cat")
        animalShelter.enqueue(cat)

        Dog shiner = new Dog("Shiner")
        animalShelter.enqueue(shiner)

        Dog copper = new Dog("Copper")
        animalShelter.enqueue(copper)

        then:
        assert animalShelter.dequeueAny() == cat
    }

    def "should be able to dequeue a dog even if it's not first in line" () {
        given:
        AnimalShelter animalShelter = new AnimalShelter()

        when:
        Cat cat = new Cat("Cat")
        animalShelter.enqueue(cat)

        Dog shiner = new Dog("Shiner")
        animalShelter.enqueue(shiner)

        Dog copper = new Dog("Copper")
        animalShelter.enqueue(copper)

        Dog poppedDog = animalShelter.dequeueDog()

        then:
        assert poppedDog == shiner
    }

    def "should be able to dequeue a cat even if it's not first in line" () {
        given:
        AnimalShelter animalShelter = new AnimalShelter()

        when:
        Dog shiner = new Dog("Shiner")
        animalShelter.enqueue(shiner)

        Cat cat = new Cat("Cat")
        animalShelter.enqueue(cat)

        Dog copper = new Dog("Copper")
        animalShelter.enqueue(copper)

        Cat poppedCat = animalShelter.dequeueCat()

        then:
        assert poppedCat == cat
    }
}
