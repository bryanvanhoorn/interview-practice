package org.interview.stack;

public class ArrayBackedStack {
    private int[] stackData = new int[16];
    private int topOfStackIndex = -1;

    public int pop() {
        if (topOfStackIndex < 0) {
            throw new RuntimeException("nothing on the stack");
        }

        int popValue = stackData[topOfStackIndex];
        topOfStackIndex--;
        return popValue;
    }

    public void push(int newValue) {
        topOfStackIndex++;
        stackData[topOfStackIndex] = newValue;
    }

    public int peek() {
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
