package org.interview.stack;

/**
 * Important:  think of the structure like a linked list.
 *  The "nextSpot" array basically holds a pointer for this node to whatever index would be the next node in its stack
 *  The "topNode" array holds a pointer for a particular stack that points to the top node of the stack
 *  The "nextFreeIndex" points to the first empty node.  That node will then have an entry in the "nextSpot" array that
 *      will point to the next free spot in the array.  (Like a LinkedList of empty nodes)
 */

public class ArrayBackedMultiStack {
    private int[] stackData = new int[64];

    private int[] nextSpot = new int[64];
    private int[] topNode = new int[64];
    private int[] nextEntry = new int[64];

    // store the first free slot in the data array
    private int nextFreeIndex = 0;

    public ArrayBackedMultiStack(int numberOfStacks) {
        // initialize all stacks as empty
        for (int i = 0; i < numberOfStacks; i++) {
            topNode[i] = -1;
        }

        // initialize all spaces in the array as free
        for (int i = 0; i < stackData.length - 1; i++) {
            nextSpot[i] = i + 1;
        }
        nextSpot[stackData.length - 1] = -1;
    }

    public int pop(int stackNumber) {
        if (topNode[stackNumber] < 0) {
            throw new RuntimeException("stack " + stackNumber + " is empty.  nothing to pop!");
        }

        // get index of the top node of this stack
        int indexOfDataToPop = topNode[stackNumber];

        // point top of stack to next node in the stack
        topNode[stackNumber] = nextSpot[indexOfDataToPop];

        // attach old top (our popped node) to beginning of free list
        // pointer for this index points to next free index as its next value
        nextSpot[indexOfDataToPop] = nextFreeIndex;
        // this node is now the next free value, (moves to the front of the free list)
        nextFreeIndex = nextSpot[indexOfDataToPop];

        return stackData[indexOfDataToPop];
    }

    public void push(int stackNumber, int newValue) {
        // get the index of the next free slot in the main stackData array
        int indexForNewDataBeingPushed = nextFreeIndex;

        if (indexForNewDataBeingPushed < 0) {
            throw new RuntimeException("unable to push anything on to stack " + stackNumber + ". backing array is full!");
        }

        // "increment" the next free index to point to the next free spot after the one we just claimed
        nextFreeIndex = nextSpot[indexForNewDataBeingPushed];

        // the next node for this index we just claimed should be the old top of the stack that we're adding to.
        // the "next" node is the next one in the stack, the one that would be on top if we popped this node
        nextSpot[indexForNewDataBeingPushed] = topNode[stackNumber];
        // set the new top of this stack to point to the spot we just claimed
        topNode[stackNumber] = indexForNewDataBeingPushed;

        // add our data to the array
        stackData[indexForNewDataBeingPushed] = newValue;
    }

    public int peek(int stackNumber) {
        int topNodeOfStack = topNode[stackNumber];

        if (topNodeOfStack < 0) {
            throw new RuntimeException("nothing on stack " + stackNumber);
        }
        return stackData[topNodeOfStack];
    }

    public boolean isEmpty(int stackNumber) {
        int topNodeOfStack = topNode[stackNumber];

        if (topNodeOfStack < 0) {
            return true;
        }
        return false;
    }
}
