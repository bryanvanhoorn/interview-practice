package org.interview.linkedlist

import spock.lang.Specification
import spock.lang.Unroll

class LinkedListSpec extends Specification {

    def "should be able to remove duplicates from an unsorted singly linked list"() {
        given: "we have a linked list with one dupe in it"
        LinkedListNode headNode = getLinkedListWithDupes()
        assert countNodesInList(headNode) == 6

        when:
        LinkedListUtils.removeDuplicates(headNode)

        then:
        // assert that traversing the list gives us the correct numbers, minus the dupe
        // assert that length of the list is now 1 less
        assert countNodesInList(headNode) == 5
        assert headNode.getData() == 1
        assert headNode.getNextNode().getData() == 2
        assert headNode.getNextNode().getNextNode().getData() == 5
        assert headNode.getNextNode().getNextNode().getNextNode().getData() == 4
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getData() == 3
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getNextNode() == null
    }

    def "should be able to remove duplicates from an unsorted singly linked list without using extra data structure"() {
        given: "we have a linked list with one dupe in it"
        LinkedListNode headNode = getLinkedListWithDupes()
        assert countNodesInList(headNode) == 6

        when:
        LinkedListUtils.removeDuplicatesWithoutSeparateDataStructure(headNode)

        then:
        // assert that traversing the list gives us the correct numbers, minus the dupe
        // assert that length of the list is now 1 less
        assert countNodesInList(headNode) == 5
        assert headNode.getData() == 1
        assert headNode.getNextNode().getData() == 2
        assert headNode.getNextNode().getNextNode().getData() == 5
        assert headNode.getNextNode().getNextNode().getNextNode().getData() == 4
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getData() == 3
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getNextNode() == null
    }

    LinkedListNode getLinkedListWithDupes() {
        LinkedListNode headNode = new LinkedListNode(1)
        addNodeToList(headNode, 2)
        addNodeToList(headNode, 5)
        addNodeToList(headNode, 4)
        addNodeToList(headNode, 3)
        addNodeToList(headNode, 4)

        return headNode
    }

    void addNodeToList(LinkedListNode node, int data) {

        LinkedListNode currentEndOfList = getEndOfList(node)

        LinkedListNode nextNode = new LinkedListNode(data)
        currentEndOfList.setNextNode(nextNode)
    }

    LinkedListNode getEndOfList(LinkedListNode node) {
        LinkedListNode currentEndOfList = node
        while (currentEndOfList.getNextNode() != null) {
            currentEndOfList = currentEndOfList.getNextNode()
        }

        return currentEndOfList
    }

    int countNodesInList(LinkedListNode headNode) {
        LinkedListNode currentEndOfList = headNode
        int nodeCounter = 1
        while (currentEndOfList.getNextNode() != null) {
            nodeCounter++
            currentEndOfList = currentEndOfList.getNextNode()
        }

        return nodeCounter
    }

    @Unroll
    def "should be able to find the kth to last element of a singly linked list"() {
        given:
        LinkedListNode headNode = getLinkedListWithDupes()

        when:
        LinkedListNode kthToLastNode = LinkedListUtils.getKthToLastElementOfList(headNode, k)

        then:
        kthToLastNode.getData() == expectedNodeData

        where:
        k | expectedNodeData
        2 | 3
        4 | 5
    }

    def "should be able to remove a node from the middle of a singly linked list"() {
        given:
        LinkedListNode headNode = getLinkedListWithDupes()
        assert countNodesInList(headNode) == 6
        LinkedListNode thirdToLastNode = LinkedListUtils.getKthToLastElementOfList(headNode, 3)

        when:
        LinkedListUtils.removeNode(thirdToLastNode)

        then:
        // assert that traversing the list gives us the correct numbers, minus the dupe
        // assert that length of the list is now 1 less
        assert countNodesInList(headNode) == 5
        assert headNode.getData() == 1
        assert headNode.getNextNode().getData() == 2
        assert headNode.getNextNode().getNextNode().getData() == 5
        assert headNode.getNextNode().getNextNode().getNextNode().getData() == 3
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getData() == 4
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getNextNode() == null
    }

    /*
    Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes
    greater than or equal to x.  If x is contained within the list, the values of x only need to be after the
    elements less than x.  The partition element x can appear anywhere in the "right partition"; it does not need to
    appear between the left and right partitions.

    3->5->8->5->10->2->1 (partition =5)
    3->1->2->10->5->5->8
     */
    def "should be able to partition a linked list around value x"() {
        given:
        LinkedListNode headNode = getLinkedList()

        when:
        headNode = LinkedListUtils.partitionAround(headNode, 5)

        then:
        assert headNode.getData() == 1
        assert headNode.getNextNode().getData() == 2
        assert headNode.getNextNode().getNextNode().getData() == 3
        assert headNode.getNextNode().getNextNode().getNextNode().getData() == 5
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getData() == 8
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getNextNode().getData() == 5
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getNextNode().getNextNode().getData() == 10
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getNextNode().getNextNode().getNextNode() == null
    }

    LinkedListNode getLinkedList() {
        LinkedListNode headNode = new LinkedListNode(3)
        addNodeToList(headNode, 5)
        addNodeToList(headNode, 8)
        addNodeToList(headNode, 5)
        addNodeToList(headNode, 10)
        addNodeToList(headNode, 2)
        addNodeToList(headNode, 1)

        return headNode
    }

    /*
    You have two numbers represented by a LinkedList, where each node contains a single digit.
    Digits are stored in reverse order, such that the 1's digit is at the head of the list.  Write a function
    that adds the two numbers and returns the sum as a linked list.
     */
    def "should be able to add 2 numbers represented as linked lists with 1's digit at the head"() {
        given:
        LinkedListNode firstNumberNode = new LinkedListNode(3)
        addNodeToList(firstNumberNode, 5)
        addNodeToList(firstNumberNode, 8)

        LinkedListNode secondNumberNode = new LinkedListNode(1)
        addNodeToList(secondNumberNode, 2)
        addNodeToList(secondNumberNode, 3)

        when:
        LinkedListNode actualOutputList = LinkedListUtils.addTwoNumbersInReverseOrder(firstNumberNode, secondNumberNode)

        then:
        assert actualOutputList.getData() == 4
        assert actualOutputList.getNextNode().getData() == 7
        assert actualOutputList.getNextNode().getNextNode().getData() == 1
        assert actualOutputList.getNextNode().getNextNode().getNextNode().getData() == 1
        assert actualOutputList.getNextNode().getNextNode().getNextNode().getNextNode() == null
    }

    def "should be able to add 2 numbers represented as linked lists with 1's digit at the tail"() {
        given:
        LinkedListNode firstNumberNode = new LinkedListNode(3)
        addNodeToList(firstNumberNode, 5)
        addNodeToList(firstNumberNode, 8)

        LinkedListNode secondNumberNode = new LinkedListNode(1)
        addNodeToList(secondNumberNode, 2)
        addNodeToList(secondNumberNode, 3)

        when:
        LinkedListNode actualOutputList = LinkedListUtils.addTwoNumbersInForwardOrder(firstNumberNode, secondNumberNode)

        then:
        assert actualOutputList.getData() == 4
        assert actualOutputList.getNextNode().getData() == 8
        assert actualOutputList.getNextNode().getNextNode().getData() == 1
        assert actualOutputList.getNextNode().getNextNode().getNextNode() == null
    }

    def "should be able to add 2 numbers represented as linked lists with 1's digit at the tail and multiple carryover digits"() {
        given:
        LinkedListNode firstNumberNode = new LinkedListNode(8)
        addNodeToList(firstNumberNode, 5)
        addNodeToList(firstNumberNode, 8)

        LinkedListNode secondNumberNode = new LinkedListNode(4)
        addNodeToList(secondNumberNode, 2)
        addNodeToList(secondNumberNode, 3)

        when:
        LinkedListNode actualOutputList = LinkedListUtils.addTwoNumbersInForwardOrder(firstNumberNode, secondNumberNode)

        then:
        assert actualOutputList.getData() == 1
        assert actualOutputList.getNextNode().getData() == 2
        assert actualOutputList.getNextNode().getNextNode().getData() == 8
        assert actualOutputList.getNextNode().getNextNode().getNextNode().getData() == 1
        assert actualOutputList.getNextNode().getNextNode().getNextNode().getNextNode() == null
    }

    def "should be able to check if a linked list is a palindrome" () {
        given:
        LinkedListNode headNodeOfPalindromeList = getPalindromeLinkedList()

        when:
        boolean actualOutput = LinkedListUtils.isPalindrome(headNodeOfPalindromeList)

        then:
        assert actualOutput == true
    }

    LinkedListNode getPalindromeLinkedList() {
        LinkedListNode headNode = new LinkedListNode(3)
        addNodeToList(headNode, 5)
        addNodeToList(headNode, 8)
        addNodeToList(headNode, 5)
        addNodeToList(headNode, 3)

        return headNode
    }

    def "should be able to check if a linked list is not a palindrome" () {
        given:
        LinkedListNode headNodeOfPalindromeList = getNonPalindromeLinkedList()

        when:
        boolean actualOutput = LinkedListUtils.isPalindrome(headNodeOfPalindromeList)

        then:
        assert actualOutput == false
    }

    LinkedListNode getNonPalindromeLinkedList() {
        LinkedListNode headNode = new LinkedListNode(3)
        addNodeToList(headNode, 2)
        addNodeToList(headNode, 8)
        addNodeToList(headNode, 5)
        addNodeToList(headNode, 3)

        return headNode
    }

    /*
    Given two singly linked lists, determine if the two lists intersect.  Return the intersecting node.  Node that
    the intersection is defined based on reference, not value.

    O(n^2) - get first node of first list, iterate through second list looking for match.  repeat
    - can be done in O(A+B) time and O(1) additional space
    - could iterate through 1st list and load all nodes into a set
        - for each node in the second list, see if it exists in the set
     */
    def "should be able to determine if 2 same-length linked lists intersect"() {
        given:
        LinkedListNode list1HeadNode = new LinkedListNode(1)
        addNodeToList(list1HeadNode, 2)
        addNodeToList(list1HeadNode, 3)
        addNodeToList(list1HeadNode, 4)

        LinkedListNode list2HeadNode = new LinkedListNode(5)
        addNodeToList(list2HeadNode, 6)
        LinkedListNode endOfList = getEndOfList(list2HeadNode)

        LinkedListNode secondToLastNode = LinkedListUtils.getKthToLastElementOfList(list1HeadNode, 2)
        endOfList.setNextNode(secondToLastNode)

        when:
        LinkedListNode intersectingNode = LinkedListUtils.getIntersectingNode(list1HeadNode, list2HeadNode)

        then:
        assert intersectingNode == secondToLastNode
    }

    def "should be able to determine if 2 different length linked lists intersect without using hash set"() {
        given:
        LinkedListNode list1HeadNode = new LinkedListNode(1)
        addNodeToList(list1HeadNode, 2)
        addNodeToList(list1HeadNode, 3)
        addNodeToList(list1HeadNode, 4)

        LinkedListNode list2HeadNode = new LinkedListNode(5)
        addNodeToList(list2HeadNode, 6)
        addNodeToList(list2HeadNode, 7)
        addNodeToList(list2HeadNode, 8)
        LinkedListNode endOfList = getEndOfList(list2HeadNode)

        LinkedListNode secondToLastNode = LinkedListUtils.getKthToLastElementOfList(list1HeadNode, 2)
        endOfList.setNextNode(secondToLastNode)

        when:
        LinkedListNode intersectingNode = LinkedListUtils.getIntersectingNodeWithoutUsingHashSet(list1HeadNode, list2HeadNode)

        then:
        assert intersectingNode == secondToLastNode
    }

    /*
    Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
     */

}
