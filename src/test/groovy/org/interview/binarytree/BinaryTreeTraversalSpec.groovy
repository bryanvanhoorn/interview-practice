package org.interview.binarytree

import spock.lang.Specification

/*
 *  Using the above data structure that represents a binary tree, write a method to return a list of nodes with a
 *  modified ordering - nodes from left-to-right and right-to-left alternatively at each level.
 *  E.g.:
 *              (5)       ---->  left-to-right
 *             /   \
 *           (4)   (1)    <----  right-to-left
 *          / \     /
 *       (6) (3)  (7)     ---->  left-to-right
 *       /          \
 *     (2)          (9)   <----  right-to-left
 *
 *     Result:
 *     Alternating BFS ordering = [ 5, 1, 4, 6, 3, 7, 9, 2 ]
 */

class BinaryTreeTraversalSpec extends Specification {

    def "Display nodes in left-to-right order"() {
        given:
        BinaryTreeNode rootNode = buildBinaryTree()

        when:
        List<BinaryTreeNode> leftToRightNode = BinaryTreeTraversal.simpleOrdering(rootNode)

        then:
        assert leftToRightNode.toString() == "[5, 4, 1, 6, 3, 7, 2, 9]"
    }

    def "Display nodes in modified order"() {
        given:
        BinaryTreeNode rootNode = buildBinaryTree()

        when:
        List<BinaryTreeNode> modifiedNodeList = BinaryTreeTraversal.modifiedOrdering(rootNode)

        then:
        assert modifiedNodeList.toString() == "[5, 1, 4, 6, 3, 7, 9, 2]"
    }

    private BinaryTreeNode buildBinaryTree() {
        BinaryTreeNode node2 = new BinaryTreeNode(2)
        BinaryTreeNode node6 = new BinaryTreeNode(6)
        node6.setLeftChild(node2)

        BinaryTreeNode node3 = new BinaryTreeNode(3)

        BinaryTreeNode node4 = new BinaryTreeNode(4)
        node4.setLeftChild(node6)
        node4.setRightChild(node3)

        BinaryTreeNode node9 = new BinaryTreeNode(9)

        BinaryTreeNode node7 = new BinaryTreeNode(7)
        node7.setRightChild(node9)

        BinaryTreeNode node1 = new BinaryTreeNode(1)
        node1.setLeftChild(node7)

        BinaryTreeNode node5 = new BinaryTreeNode(5)
        node5.setLeftChild(node4)
        node5.setRightChild(node1)

        return node5
    }
}
