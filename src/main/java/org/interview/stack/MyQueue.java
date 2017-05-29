package org.interview.stack;

public class MyQueue {

    ArrayBackedStack arrayBackedStack;

    public MyQueue() {
        this.arrayBackedStack = new ArrayBackedStack();
    }

    public void add(int item) {
        arrayBackedStack.push(item);
    }

    public int remove() {
        ArrayBackedStack tempStack = new ArrayBackedStack();
        while(arrayBackedStack.isEmpty() == false) {
            tempStack.push(arrayBackedStack.pop());
        }

        int removedValue = tempStack.pop();

        while(tempStack.isEmpty() == false) {
            arrayBackedStack.push(tempStack.pop());
        }

        return removedValue;
    }

    public int peek() {
        ArrayBackedStack tempStack = new ArrayBackedStack();
        while(arrayBackedStack.isEmpty() == false) {
            tempStack.push(arrayBackedStack.pop());
        }

        int peekedValue = tempStack.peek();

        while(tempStack.isEmpty() == false) {
            arrayBackedStack.push(tempStack.pop());
        }

        return peekedValue;
    }

}
