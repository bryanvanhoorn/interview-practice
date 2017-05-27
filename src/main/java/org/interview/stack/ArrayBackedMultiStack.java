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
    private int[] bottomNode = new int[64];

    private int[] nextMinimumValue = new int[64];
    private int[] minimumValueNode = new int[64];

    // store the first free slot in the data array
    private int nextFreeIndex = 0;

    public ArrayBackedMultiStack(int numberOfStacks) {
        // initialize all stacks as empty
        for (int i = 0; i < numberOfStacks; i++) {
            topNode[i] = -1;
            bottomNode[i] = -1;
            minimumValueNode[i] = -1;
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

        // if top node is also bottom node, then update the bottom node
        if (indexOfDataToPop == bottomNode[stackNumber]) {
            bottomNode[stackNumber] = nextSpot[indexOfDataToPop];
        }

        // point top of stack to next node in the stack
        topNode[stackNumber] = nextSpot[indexOfDataToPop];

        // attach old top (our popped node) to beginning of free list
        // pointer for this index points to next free index as its next value
        nextSpot[indexOfDataToPop] = nextFreeIndex;
        // this node is now the next free value, (moves to the front of the free list)
        nextFreeIndex = nextSpot[indexOfDataToPop];

        // if we just popped the minimum value node of this stack, set the minimum value to the next one
        if (indexOfDataToPop == minimumValueNode[stackNumber]) {
            minimumValueNode[stackNumber] = nextMinimumValue[indexOfDataToPop];
        }

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

        // if this stack was empty before, update the bottom node pointer
        if(bottomNode[stackNumber] < 0) {
            bottomNode[stackNumber] = indexForNewDataBeingPushed;
        }

        updateMinimumNode(stackNumber, indexForNewDataBeingPushed, newValue);

        // add our data to the array
        stackData[indexForNewDataBeingPushed] = newValue;
    }

    private void updateMinimumNode(int stackNumber, int indexOfNewDataBeingPushed, int newValue) {
        // check if this value is smaller than the current min value.  if so, replace
        if (minimumValueNode[stackNumber] < 0) {
            // no current minimum, set this value as the min
            // next for this node essentially points to null to mark it as the bottom of the stack
            nextMinimumValue[indexOfNewDataBeingPushed] = minimumValueNode[stackNumber];
            // this node is now the minimum node in the stack
            minimumValueNode[stackNumber] = indexOfNewDataBeingPushed;
        } else {
            // need to insert this node in the linked list in value order
            // if this node's value is less than current minimum value, set it as the new minimum
            if (newValue < stackData[minimumValueNode[stackNumber]]) {
                // next minimum value for this stack now points to the old minimum
                nextMinimumValue[indexOfNewDataBeingPushed] = minimumValueNode[stackNumber];
                // this node is now the minimum node in the stack
                minimumValueNode[stackNumber] = indexOfNewDataBeingPushed;
            } else {
                // cycle through the minimum value list until you find a node greater than or
                // equal to this one.  update the pointers to place the node there

                // minimumValueNode[stackNumber] - this is the index of the minimum value
                // nextMinimumValue[minimumValueNode[stackNumber]] - this is the index of the next value in the list

                // 1 -> 2 -> 3 -> 4

                int currentMinValueIndex = minimumValueNode[stackNumber];
                int previousMinValueIndex = -1;
                while(currentMinValueIndex > 0 && stackData[currentMinValueIndex] < newValue) {
                    previousMinValueIndex = currentMinValueIndex;
                    currentMinValueIndex = nextMinimumValue[currentMinValueIndex];
                }
                // this is where we insert the node - the data at this index is greater than or equal to our value
                nextMinimumValue[indexOfNewDataBeingPushed] = nextMinimumValue[previousMinValueIndex];
                nextMinimumValue[previousMinValueIndex] = indexOfNewDataBeingPushed;
            }
        }
    }

    public int peek(int stackNumber) {
        int topNodeOfStack = topNode[stackNumber];

        if (topNodeOfStack < 0) {
            throw new RuntimeException("nothing on stack " + stackNumber);
        }
        return stackData[topNodeOfStack];
    }

    public int peekBottom(int stackNumber) {
        int bottomNodeOfStack = bottomNode[stackNumber];

        if (bottomNodeOfStack < 0) {
            throw new RuntimeException("nothing on stack " + stackNumber);
        }
        return stackData[bottomNodeOfStack];
    }

    public int min(int stackNumber) {
        return stackData[minimumValueNode[stackNumber]];
    }

    public boolean isEmpty(int stackNumber) {
        int topNodeOfStack = topNode[stackNumber];

        if (topNodeOfStack < 0) {
            return true;
        }
        return false;
    }
}
