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

}
