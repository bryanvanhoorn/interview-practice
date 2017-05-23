package org.interview.linkedlist;

public class LinkedListUtils {

    public static void removeDuplicates(LinkedListNode headNode) {
        boolean[] listContains = new boolean[128];

        LinkedListNode currentNode = headNode;
        LinkedListNode previousNode = null;

        listContains[currentNode.getData()] = true;

        while(currentNode.getNextNode() != null) {

            previousNode = currentNode;
            currentNode = currentNode.getNextNode();

            if(listContains[currentNode.getData()]) {
                removeNodeFromLinkedList(previousNode, currentNode);
                currentNode = previousNode;
            } else {
                listContains[currentNode.getData()] = true;
            }
        }
    }

    private static void removeNodeFromLinkedList(LinkedListNode previousNode, LinkedListNode nodeToRemove) {
        previousNode.setNextNode(nodeToRemove.getNextNode());
    }

    public static void removeDuplicatesWithoutSeparateDataStructure(LinkedListNode headNode) {

        LinkedListNode currentHeadNode = headNode;
        int valueToCompare;


        while(currentHeadNode.getNextNode() != null) {
            valueToCompare = currentHeadNode.getData();

            // loop through the rest of the nodes and look for this data
            LinkedListNode nodeBeingSearched = currentHeadNode.getNextNode();
            LinkedListNode previousNode = currentHeadNode;
            while (nodeBeingSearched != null) {
                if (valueToCompare == nodeBeingSearched.getData() && previousNode != nodeBeingSearched) {
                    removeNodeFromLinkedList(previousNode, nodeBeingSearched);
                    nodeBeingSearched = previousNode;
                } else {
                    previousNode = nodeBeingSearched;
                    nodeBeingSearched = nodeBeingSearched.getNextNode();
                }
            }
            currentHeadNode = currentHeadNode.getNextNode();
        }
    }

    public static LinkedListNode getKthToLastElementOfList(LinkedListNode headNode, int k) {
        // need 2 pointers:
        // one at beginning, and one at kth node.  iterate both until second pointer is at end
        // first pointer will have the node we need
        LinkedListNode leadNode = headNode;
        LinkedListNode followerNode = headNode;
        int nodeCounter = 1;

        while(leadNode.getNextNode() != null && nodeCounter < k) {
            leadNode = leadNode.getNextNode();
            nodeCounter++;
        }

        // Lead node is now pointed at kth element in the list
        while(leadNode.getNextNode() != null) {
            leadNode = leadNode.getNextNode();
            followerNode = followerNode.getNextNode();
        }

        return followerNode;
    }

    public static void removeNode(LinkedListNode nodeToRemove) {
        LinkedListNode currentNode = nodeToRemove;
        LinkedListNode nextNode = currentNode.getNextNode();

        while(nextNode != null) {
            currentNode.setData(nextNode.getData());

            if (nextNode.getNextNode() == null) {
                currentNode.setNextNode(null);
                nextNode = null;
            } else {
                currentNode = nextNode;
                nextNode = nextNode.getNextNode();
            }
        }
    }

    public static LinkedListNode partitionAround(LinkedListNode headNode, int partitionValue) {
        /*
        solution1:  create list of nodes < partitionVal
                    create list of nodes >= partitionVal
                    iterate through lists and stitch them together into linked list

        solution2:  iterate through nodes
                    if node < partitionVal, move it to beginning of linkedlist
         */
        LinkedListNode currentNode = headNode;
        LinkedListNode nextNode = currentNode.getNextNode();

        while(nextNode != null) {
            if (nextNode.getData() < partitionValue) {
                // move it to beginning of list
                LinkedListNode nextNodeAfterMove = nextNode.getNextNode();
                nextNode.setNextNode(headNode);
                headNode = nextNode;
                currentNode.setNextNode(nextNodeAfterMove);
            } else {
                currentNode = nextNode;
            }
            nextNode = currentNode.getNextNode();
        }

        return headNode;

    }

    public static LinkedListNode addTwoNumbersInReverseOrder(LinkedListNode firstNumberNode, LinkedListNode secondNumberNode) {
        // start with pointers to head of both
        // get data, add, store any remainder in a temp variable
        // advance both lists to next node
        // get data, add data and remainder from previous step, store remainder in temp
        LinkedListNode solutionListHeadNode = new LinkedListNode(0);
        LinkedListNode solutionListCurrentNode = solutionListHeadNode;

        int carryOver = 0;
        while(firstNumberNode != null || secondNumberNode != null) {
            int firstNumberData = 0;
            if (firstNumberNode != null) {
                firstNumberData = firstNumberNode.getData();
            }

            int secondNumberData = 0;
            if (secondNumberNode != null) {
                secondNumberData = secondNumberNode.getData();
            }

            int total = firstNumberData + secondNumberData + carryOver;

            if (total >= 10) {
                carryOver = 1;
                total = total - 10;
            } else {
                carryOver = 0;
            }

            solutionListCurrentNode.setData(total);

            //advance to the next nodes in our lists
            firstNumberNode = firstNumberNode.getNextNode();
            secondNumberNode = secondNumberNode.getNextNode();

            if (firstNumberNode != null || secondNumberNode != null) {
                // add an empty node for the next loop
                LinkedListNode nextNode = new LinkedListNode(0);
                solutionListCurrentNode.setNextNode(nextNode);
                solutionListCurrentNode = nextNode;
            }
        }

        if(carryOver > 0) {
            LinkedListNode nextNode = new LinkedListNode(carryOver);
            solutionListCurrentNode.setNextNode(nextNode);
        }

        return solutionListHeadNode;
    }

    public static LinkedListNode addTwoNumbersInForwardOrder(LinkedListNode firstNumber, LinkedListNode secondNumber) {
        return firstNumber;
    }
}
