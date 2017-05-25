package org.interview.linkedlist;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
        /*
        3 - 5 - 8
        1 - 2 - 3
        recursion?
         */
        LinkedListNode solutionListHeadNode = new LinkedListNode(0);
        int carryOver = addTwoNodes(firstNumber, secondNumber, solutionListHeadNode);
        if (carryOver > 0) {
            solutionListHeadNode.setData(carryOver);
            return solutionListHeadNode;
        } else {
            return solutionListHeadNode.getNextNode();
        }
    }

    private static int addTwoNodes(LinkedListNode firstNumber, LinkedListNode secondNumber, LinkedListNode prevNode) {

        LinkedListNode newNode = new LinkedListNode(0);
        prevNode.setNextNode(newNode);

        int firstValue = 0;
        int secondValue = 0;

        if (firstNumber != null) {
            firstValue = firstNumber.getData();
        }

        if (secondNumber != null) {
            secondValue = secondNumber.getData();
        }

        int carryOver = 0;
        int nodeValue = firstValue + secondValue;
        if (nodeValue > 10) {
            nodeValue = nodeValue - 10;
            carryOver = 1;
        }

        int carryOverFromNextNodes = 0;
        if(firstNumber.getNextNode() != null || secondNumber.getNextNode() != null) {
            carryOverFromNextNodes = addTwoNodes(firstNumber.getNextNode(), secondNumber.getNextNode(), newNode);
        }

        newNode.setData(nodeValue + carryOverFromNextNodes);

        return carryOver;
    }

    public static boolean isPalindrome(LinkedListNode headNode) {
        Stack<Integer> stackOfListNodes = new Stack<Integer>();

        LinkedListNode currentNode = headNode;
        stackOfListNodes.push(currentNode.getData());
        while(currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
            stackOfListNodes.push(currentNode.getData());
        }

        currentNode = headNode;
        while(currentNode != null) {
            if (currentNode.getData() != stackOfListNodes.pop()) {
                return false;
            }
            currentNode = currentNode.getNextNode();
        }

        return true;
    }

    public static LinkedListNode getIntersectingNode(LinkedListNode list1HeadNode, LinkedListNode list2HeadNode) {
        Set<LinkedListNode> list1Nodes = new HashSet<LinkedListNode>();

        while(list1HeadNode != null) {
            list1Nodes.add(list1HeadNode);
            list1HeadNode = list1HeadNode.getNextNode();
        }

        while(list2HeadNode != null) {
            if(list1Nodes.contains(list2HeadNode)) {
                return list2HeadNode;
            }
            list2HeadNode = list2HeadNode.getNextNode();
        }

        return null;
    }

    public static LinkedListNode getIntersectingNodeWithoutUsingHashSet(LinkedListNode list1HeadNode, LinkedListNode list2HeadNode) {
        int list1Length = getLengthOfList(list1HeadNode);
        int list2Length = getLengthOfList(list2HeadNode);

        LinkedListNode list1CurrentNode = list1HeadNode;
        LinkedListNode list2CurrentNode = list2HeadNode;
        if(list1Length > list2Length) {
            // list 1 is longer.  advance the pointer forward on list 1
            while(list1Length > list2Length) {
                list1CurrentNode = list1CurrentNode.getNextNode();
                list1Length--;
            }

        } else if (list2Length > list1Length) {
            // list 2 is longer. advance the pointer forward on list 2
            while(list2Length > list1Length) {
                list2CurrentNode = list2CurrentNode.getNextNode();
                list2Length--;
            }
        }

        while(list1CurrentNode != null && list2CurrentNode != null) {
            if(list1CurrentNode == list2CurrentNode) {
                return list1CurrentNode;
            }
            list1CurrentNode = list1CurrentNode.getNextNode();
            list2CurrentNode = list2CurrentNode.getNextNode();
        }

        return null;
    }

    private static int getLengthOfList(LinkedListNode listHeadNode) {
        int listLength = 0;
        while(listHeadNode != null) {
            listLength++;
            listHeadNode = listHeadNode.getNextNode();
        }
        return listLength;
    }
}
