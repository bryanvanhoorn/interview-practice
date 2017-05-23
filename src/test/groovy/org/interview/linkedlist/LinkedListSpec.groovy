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

        LinkedListNode currentEndOfList = node
        while (currentEndOfList.getNextNode() != null) {
            currentEndOfList = currentEndOfList.getNextNode()
        }

        LinkedListNode nextNode = new LinkedListNode(data)
        currentEndOfList.setNextNode(nextNode)
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
     */

}
