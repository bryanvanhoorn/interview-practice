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

}
