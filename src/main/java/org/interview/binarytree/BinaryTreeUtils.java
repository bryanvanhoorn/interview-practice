package org.interview.binarytree;

import java.util.*;

public class BinaryTreeUtils {

    public static boolean isIdenticalBinaryTrees(BinaryTreeNode rootNode1, BinaryTreeNode rootNode2) {

        ArrayList<BinaryTreeNode> tree1NodeList = getListOfTreeNodes(rootNode1);
        ArrayList<BinaryTreeNode> tree2NodeList = getListOfTreeNodes(rootNode2);

        return tree1NodeList.equals(tree2NodeList);
    }

    private static ArrayList<BinaryTreeNode> getListOfTreeNodes(BinaryTreeNode rootNode) {
        ArrayList<BinaryTreeNode> treeNodeList = new ArrayList<BinaryTreeNode>();
        Queue<BinaryTreeNode> nodeQueue = new LinkedList<BinaryTreeNode>();

        nodeQueue.add(rootNode);

        while(nodeQueue.isEmpty() == false) {
            BinaryTreeNode treeNode = nodeQueue.remove();
            treeNodeList.add(treeNode);

            if (treeNode.getLeftChild() != null) {
                nodeQueue.add(treeNode.getLeftChild());
            }
            if (treeNode.getRightChild() != null) {
                nodeQueue.add(treeNode.getRightChild());
            }
        }

        return treeNodeList;
    }

    public static BinaryTreeNode getBinarySearchTreeFromSortedArray(int[] array) {

        if (array.length == 1) {
            BinaryTreeNode rootNode = new BinaryTreeNode(array[0]);
            return rootNode;
        }

        // find root node - middle value of list
        int midpointIndex;
        if (array.length % 2 == 0) {
            midpointIndex = (int) Math.ceil(array.length/2.0);
        } else {
            midpointIndex = (int) Math.ceil((array.length - 1)/2.0);
        }

        int rootNodeData = array[midpointIndex];

        BinaryTreeNode rootNode = new BinaryTreeNode(rootNodeData);

        int[] leftSideArray = Arrays.copyOfRange(array, 0, midpointIndex);
        int[] rightSideArray = Arrays.copyOfRange(array, midpointIndex + 1, array.length);

        // get list of values to the left, find root node for those
        rootNode.setLeftChild(getBinarySearchTreeFromSortedArray(leftSideArray));

        // get list of values to the right, find root node for those
        rootNode.setRightChild(getBinarySearchTreeFromSortedArray(rightSideArray));

        return rootNode;
    }

    public static ArrayList<LinkedList<BinaryTreeNode>> getLinkedListsForEachDepth(BinaryTreeNode rootNode) {
        ArrayList<LinkedList<BinaryTreeNode>> listOfLinkedLists = new ArrayList<>();

        Queue<LinkedList<BinaryTreeNode>> nodeQueue = new LinkedList<>();
        LinkedList<BinaryTreeNode> level0LinkedList = new LinkedList<>();
        level0LinkedList.add(rootNode);
        nodeQueue.add(level0LinkedList);
        listOfLinkedLists.add(level0LinkedList);

        while(nodeQueue.isEmpty() == false) {

            LinkedList<BinaryTreeNode> previousLevelLinkedList = nodeQueue.remove();
            LinkedList<BinaryTreeNode> currentLevelLinkedList = new LinkedList<>();

            Iterator<BinaryTreeNode> listIterator = previousLevelLinkedList.listIterator();
            while(listIterator.hasNext()) {
                BinaryTreeNode node = listIterator.next();

                if (node.getLeftChild() != null) {
                    currentLevelLinkedList.add(node.getLeftChild());
                }
                if (node.getRightChild() != null) {
                    currentLevelLinkedList.addLast(node.getRightChild());
                }

            }

            if (currentLevelLinkedList.size() > 0) {
                nodeQueue.add(currentLevelLinkedList);
                listOfLinkedLists.add(currentLevelLinkedList);
            }
        }

        return listOfLinkedLists;
    }

    public static boolean isBalanced(BinaryTreeNode rootNode) {

        if (rootNode == null) {
            return true;
        }

        int heightDiff = getHeight(rootNode.getLeftChild()) - getHeight(rootNode.getRightChild());

        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {
            return isBalanced(rootNode.getLeftChild()) && isBalanced(rootNode.getRightChild());
        }
    }

    private static int getHeight(BinaryTreeNode node) {

        if (node == null) {
            return -1;
        }

        return Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())) + 1;
    }

    /*
    Binary Search Tree - all left descendents <= n < all right descendents
     */
    public static boolean isBinarySearchTree(BinaryTreeNode rootNode) {
        return isSubtreeBinarySearchTree(rootNode);
    }

    private static boolean isSubtreeBinarySearchTree(BinaryTreeNode node) {
        if (node == null) {
            return true;
        }

        int rootNodeData = node.getData();
        int leftChildData = node.getLeftChild() != null ? node.getLeftChild().getData() : 0;
        // If there is no right child, set the value to root's data + 1 to force it to evaluate true
        int rightChildData = node.getRightChild() != null ? node.getRightChild().getData() : rootNodeData + 1;

        boolean isBinary = leftChildData <= node.getData() && node.getData() < rightChildData;

        if (isBinary == false) {
            return false;
        } else {
            return isSubtreeBinarySearchTree(node.getLeftChild()) && isSubtreeBinarySearchTree(node.getRightChild());
        }
    }
}
