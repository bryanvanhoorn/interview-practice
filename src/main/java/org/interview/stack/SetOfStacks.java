package org.interview.stack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SetOfStacks {
    List<ArrayBackedStack> listOfStacks = new ArrayList<ArrayBackedStack>();

    public SetOfStacks() {
        ArrayBackedStack arrayBackedStack = new ArrayBackedStack();
        listOfStacks.add(arrayBackedStack);
    }

    public void push(int valueToPush) {
        ArrayBackedStack latestStack = listOfStacks.get(listOfStacks.size() - 1);
        if (latestStack.isFull() == false) {
            latestStack.push(valueToPush);
        } else {
            ArrayBackedStack newArrayBackedStack = new ArrayBackedStack();
            newArrayBackedStack.push(valueToPush);
            listOfStacks.add(newArrayBackedStack);
        }
    }

    public int pop() {
        ArrayBackedStack latestStack = listOfStacks.get(listOfStacks.size() - 1);
        int poppedValue = latestStack.pop();

        if (latestStack.isEmpty()) {
            listOfStacks.remove(listOfStacks.size() - 1);
        }

        return poppedValue;
    }

    public int popAt(int stackIndex) {
        if (stackIndex >= listOfStacks.size()) {
            throw new RuntimeException("no stack at index " + stackIndex);
        }

        ArrayBackedStack arrayBackedStack = listOfStacks.get(stackIndex);
        int poppedValue = arrayBackedStack.pop();

        shiftAllElementsDown(stackIndex);

        if (arrayBackedStack.isEmpty()) {
            listOfStacks.remove(stackIndex);
        }

        return poppedValue;
    }

    private void shiftAllElementsDown(int startingStackIndex) {
        ArrayBackedStack currentStack = listOfStacks.get(startingStackIndex);
        if (currentStack.isFull() == false) {
            // check for next stack
            if (startingStackIndex + 1 < listOfStacks.size()) {
                ArrayBackedStack nextStack = listOfStacks.get(startingStackIndex + 1);

                // pop all values onto another stack until empty
                ArrayBackedStack tempStack = new ArrayBackedStack();
                while (nextStack.isEmpty() == false) {
                    tempStack.push(nextStack.pop());
                }

                // move 0th node to the current stack
                if (tempStack.isEmpty() == false) {
                    currentStack.push(tempStack.pop());
                }

                // push all nodes back onto next stack
                while (tempStack.isEmpty() == false) {
                    nextStack.push(tempStack.pop());
                }

                shiftAllElementsDown(startingStackIndex + 1);
            }
        }
    }

    public int peek() {
        ArrayBackedStack latestStack = listOfStacks.get(listOfStacks.size() - 1);
        return latestStack.peek();
    }

    public boolean isEmpty() {
        if (listOfStacks.isEmpty() || listOfStacks.get(0).isEmpty()) {
            return true;
        }
        return false;
    }
}
