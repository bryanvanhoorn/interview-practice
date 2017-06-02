package org.interview.graph

import org.interview.binarytree.BinaryTreeNode
import org.interview.binarytree.BinaryTreeUtils
import spock.lang.Specification

class TreeAndGraphSpec extends Specification {

    def "Should be able to determine if there is a route between two nodes"() {
        given:
        GraphNode node0 = new GraphNode("0")
        GraphNode node1 = new GraphNode("1")
        GraphNode node2 = new GraphNode("2")
        GraphNode node3 = new GraphNode("3")
        GraphNode node4 = new GraphNode("4")
        GraphNode node5 = new GraphNode("5")
        GraphNode node6 = new GraphNode("6")

        node0.setChildren([node1] as GraphNode[])
        node1.setChildren([node2] as GraphNode[])
        node2.setChildren([node0, node3] as GraphNode[])
        node3.setChildren([node2] as GraphNode[])

        node4.setChildren([node6] as GraphNode[])
        node6.setChildren([node5] as GraphNode[])
        node5.setChildren([node4] as GraphNode[])

        Graph graph = new Graph()
        graph.setNodes([node0, node1, node2, node3, node4, node5, node6] as GraphNode[])

        when:
        boolean actualOutput = GraphUtils.isExistingRoute(graph, node0, node3)

        then:
        assert actualOutput == true

    }

    def "Should be able to determine if there is not a route between two nodes"() {
        given:
        GraphNode node0 = new GraphNode("0")
        GraphNode node1 = new GraphNode("1")
        GraphNode node2 = new GraphNode("2")
        GraphNode node3 = new GraphNode("3")
        GraphNode node4 = new GraphNode("4")
        GraphNode node5 = new GraphNode("5")
        GraphNode node6 = new GraphNode("6")

        node0.setChildren([node1] as GraphNode[])
        node1.setChildren([node2] as GraphNode[])
        node2.setChildren([node0, node3] as GraphNode[])
        node3.setChildren([node2] as GraphNode[])

        node4.setChildren([node6] as GraphNode[])
        node6.setChildren([node5] as GraphNode[])
        node5.setChildren([node4] as GraphNode[])

        Graph graph = new Graph()
        graph.setNodes([node0, node1, node2, node3, node4, node5, node6] as GraphNode[])

        when:
        boolean actualOutput = GraphUtils.isExistingRoute(graph, node0, node5)

        then:
        assert actualOutput == false
    }

    def "should be able to create a binary search tree based on a sorted array with unique elements" () {
        //binary search tree = all left descendents <= n < all right descendents
        given:
        int[] integerArray = [1, 2, 3, 4, 5, 6, 7]

        when:
        BinaryTreeNode actualRootNode = BinaryTreeUtils.getBinarySearchTreeFromSortedArray(integerArray)

        then:
        BinaryTreeNode expectedBinaryTreeRootNode = getExpectedBinarySearchTree()
        assert BinaryTreeUtils.isIdenticalBinaryTrees(actualRootNode, expectedBinaryTreeRootNode)
    }

    BinaryTreeNode getExpectedBinarySearchTree() {
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(1)
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(2)
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(3)
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(4)
        BinaryTreeNode binaryTreeNode5 = new BinaryTreeNode(5)
        BinaryTreeNode binaryTreeNode6 = new BinaryTreeNode(6)
        BinaryTreeNode binaryTreeNode7 = new BinaryTreeNode(7)

        binaryTreeNode4.setLeftChild(binaryTreeNode2)
        binaryTreeNode4.setRightChild(binaryTreeNode6)
        binaryTreeNode2.setLeftChild(binaryTreeNode1)
        binaryTreeNode2.setRightChild(binaryTreeNode3)
        binaryTreeNode6.setLeftChild(binaryTreeNode5)
        binaryTreeNode6.setRightChild(binaryTreeNode7)

        return binaryTreeNode4
    }

    def "should be able to analyze a binary tree and create linked lists of the nodes at each depth D" () {
        given:
        BinaryTreeNode rootNode = getBinaryTree()

        when:
        ArrayList<LinkedList<BinaryTreeNode>> linkedLists = BinaryTreeUtils.getLinkedListsForEachDepth(rootNode)

        then:
        assert linkedLists.get(0).size() == 1
        assert linkedLists.get(0).pop().data == 1

        assert linkedLists.get(1).size() == 2
        assert linkedLists.get(1).removeFirst().data == 2
        assert linkedLists.get(1).removeFirst().data == 3

        assert linkedLists.get(2).size() == 4
        assert linkedLists.get(2).removeFirst().data == 4
        assert linkedLists.get(2).removeFirst().data == 5
        assert linkedLists.get(2).removeFirst().data == 6
        assert linkedLists.get(2).removeFirst().data == 7
    }

    BinaryTreeNode getBinaryTree() {
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(1)
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(2)
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(3)
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(4)
        BinaryTreeNode binaryTreeNode5 = new BinaryTreeNode(5)
        BinaryTreeNode binaryTreeNode6 = new BinaryTreeNode(6)
        BinaryTreeNode binaryTreeNode7 = new BinaryTreeNode(7)

        binaryTreeNode1.setLeftChild(binaryTreeNode2)
        binaryTreeNode1.setRightChild(binaryTreeNode3)
        binaryTreeNode2.setLeftChild(binaryTreeNode4)
        binaryTreeNode2.setRightChild(binaryTreeNode5)
        binaryTreeNode3.setLeftChild(binaryTreeNode6)
        binaryTreeNode3.setRightChild(binaryTreeNode7)

        return binaryTreeNode1
    }

    /*
    Check Balanced:  Implement a function to check if a binary tree is balanced.  For the purposes of this
    question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any node
    never differ by more than one.
     */

    /*
    Validate BST: Implement a function to check if a binary tree is a binary search tree.
     */

    /*
    Successor: Write an algorithm to find the "next" node (i.e. in-order successor) of a given node in a
    binary search tree.  You may assume that each node has a link to its parent.
     */

    /*
    Build Order:  You are given a list of projects and a list of dependencies (which is a list of pairs of
    projects, where the second project is dependent on the first project).  All of a project's dependencies
    must be built before the project is.  Find a build order that will allow the projects to be built. If
    there is no valid build order, return an error.

    Example Input:
        projects: a, b, c, d, e, f
        dependencies:  (a,d), (f,b), (b,d), (f,a), (d,c)

    Example Output:  f, e, a, b, d, c
     */

    /*
    First Common Ancestor:  Design an algorithm and write code to find the first common ancestor of two
    nodes in a binary tree. Avoid storing additional nodes in a data structure.  Note: this is not necessarily
    a binary search tree.
     */

    /*
    BST Sequences: A binary search tree was created by traversing through an array from left to right
    and inserting each element.  Given a binary search tree with distinct elements, print all possible
    arrays that could have led to this tree.

    Example input:  2 is root node, and has children 1 and 3
    Example output:  {2,1,3},{2,3,1}
     */

    /*
    Check Subtree:  T1 and T2 are two very large binary trees, with T1 much bigger than T2.  Create an algorithm to
    determine if T2 is a subtree of T1.

    A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2.
     */

    /*
    Random Node:  You are implementing a binary tree class from scratch which, in addition to insert, find and delete,
    has a method getRandomNode() which returns a random node from the tree.  All node should be equally likely to be
    chosen.  Design and implement an algorithm for getRandomNode, and explain how you would implement the rest
    of the methods.
     */

    /*
    Paths with Sum:  You are given a binary tree in which each node contains an integer value (which might be
    positive or negative).  Design an algorithm to count the number of paths that sum to a given value.  The path
    does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent
    nodes to child nodes).
     */
}
