package org.interview.linkedlist

import spock.lang.Specification

class LinkedListSpec extends Specification {

    /*
    Write code to remove duplicates from an unsorted linked list.
    Bonus:  How would you solve this problem if a temporary buffer is not allowed?
     */
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
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getNextNode() ==null
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
        assert headNode.getNextNode().getNextNode().getNextNode().getNextNode().getNextNode() ==null
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
        while(currentEndOfList.getNextNode() != null) {
            currentEndOfList = currentEndOfList.getNextNode()
        }

        LinkedListNode nextNode = new LinkedListNode(data)
        currentEndOfList.setNextNode(nextNode)
    }

    int countNodesInList(LinkedListNode headNode) {
        LinkedListNode currentEndOfList = headNode
        int nodeCounter = 1
        while(currentEndOfList.getNextNode() != null) {
            nodeCounter++
            currentEndOfList = currentEndOfList.getNextNode()
        }

        return nodeCounter
    }

    /*
    Implement an algorithm to find the kth to last element of a singly linked list.
     */

    /*
    Implement an algorithm to delete a node in the middle (i.e. any node but the first and last node, not necessarily
    the exact middle) of a singly linked list, given only access to that node.

    Example:  input is the node c in this list:  a->b->c->d->e->f
    Result:  Nothing is returned, but list now looks like:  a->b->d->e->f

     */

    /*
    Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes
    greater than or equal to x.  If x is contained within the list, the values of x only need to be after the
    elements less than x.  The partition element x can appear anywhere in the "right partition"; it does not need to
    appear between the left and right partitions.
     */

}
