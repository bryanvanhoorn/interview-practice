package org.interview.animal;

public class ArrayBackedAnimalStack {
    private Animal[] stackData = new Animal[16];
    private int topOfStackIndex = -1;

    public Animal pop() {
        if (topOfStackIndex < 0) {
            throw new RuntimeException("nothing on the stack");
        }

        Animal poppedValue = stackData[topOfStackIndex];
        topOfStackIndex--;
        return poppedValue;
    }

    public void push(Animal newValue) {
        topOfStackIndex++;
        stackData[topOfStackIndex] = newValue;
    }

    public Animal peek() {
        if (topOfStackIndex < 0) {
            throw new RuntimeException("nothing on the stack");
        }
        return stackData[topOfStackIndex];
    }

    public boolean isEmpty() {
        if (topOfStackIndex < 0) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (topOfStackIndex == stackData.length - 1) {
            return true;
        }
        return false;
    }
}
