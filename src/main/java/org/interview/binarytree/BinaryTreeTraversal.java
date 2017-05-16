package org.interview.binarytree;

import java.util.*;

public class BinaryTreeTraversal {
    
    public static List<BinaryTreeNode> simpleOrdering(final BinaryTreeNode root) {
        List<BinaryTreeNode> nodeList = new ArrayList<BinaryTreeNode>();
        Queue<BinaryTreeNode> nodeQueue = new LinkedList<BinaryTreeNode>();
        nodeQueue.add(root);

        while(nodeQueue.isEmpty() == false) {
            BinaryTreeNode currentNode = nodeQueue.remove();
            nodeList.add(currentNode);

            if (currentNode.getLeftChild() != null) {
                nodeQueue.add(currentNode.getLeftChild());
            }
            if (currentNode.getRightChild() != null) {
                nodeQueue.add(currentNode.getRightChild());
            }
        }

        return nodeList;
    }

    public static List<BinaryTreeNode> modifiedOrdering(final BinaryTreeNode root) {
        List<BinaryTreeNode> nodeList = new ArrayList<BinaryTreeNode>();
        Queue<List<BinaryTreeNode>> nodeListQueue = new LinkedList<List<BinaryTreeNode>>();

        List<BinaryTreeNode> level1NodeList = new ArrayList<BinaryTreeNode>();
        level1NodeList.add(root);
        nodeList.add(root);
        nodeListQueue.add(level1NodeList);

        int level = 1;

        while(nodeListQueue.isEmpty() == false) {
            level++;
            List<BinaryTreeNode> currentLevelNodeList = nodeListQueue.remove();
            List<BinaryTreeNode> nextLevelNodeList = new ArrayList<BinaryTreeNode>();

            currentLevelNodeList.forEach( binaryTreeNode -> {
                if (binaryTreeNode.getLeftChild() != null) {
                    nextLevelNodeList.add(binaryTreeNode.getLeftChild());
                }

                if (binaryTreeNode.getRightChild() != null) {
                    nextLevelNodeList.add(binaryTreeNode.getRightChild());
                }
            });

            if (nextLevelNodeList.isEmpty() == false) {
                nodeListQueue.add(nextLevelNodeList);
            }

            if (level % 2 == 0) {
                List<BinaryTreeNode> listToReverse = new ArrayList<BinaryTreeNode>();
                listToReverse.addAll(nextLevelNodeList);

                Collections.reverse(listToReverse);
                nodeList.addAll(listToReverse);
            } else {
                nodeList.addAll(nextLevelNodeList);
            }
        }
        return nodeList;
    }
}
