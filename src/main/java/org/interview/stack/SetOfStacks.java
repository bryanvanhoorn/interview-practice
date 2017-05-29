package org.interview.stack;

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

    public int peek () {
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
