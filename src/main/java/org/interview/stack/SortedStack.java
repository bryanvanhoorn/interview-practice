package org.interview.stack;

public class SortedStack {


    ArrayBackedStack storageStack;

    public SortedStack() {
        storageStack = new ArrayBackedStack();
    }

    public void push(int item) {

        ArrayBackedStack tempStack = new ArrayBackedStack();

        while (storageStack.isEmpty() == false && item > storageStack.peek()) {
            tempStack.push(storageStack.pop());
        }

        storageStack.push(item);

        while (tempStack.isEmpty() == false) {
            storageStack.push(tempStack.pop());
        }
    }

    public int pop() {
        return storageStack.pop();
    }

    public int peek() {
        return storageStack.peek();
    }

    public boolean isEmpty() {
        return storageStack.isEmpty();
    }
}
