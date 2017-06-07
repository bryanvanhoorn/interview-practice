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

        while (nodeQueue.isEmpty() == false) {
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
            midpointIndex = (int) Math.ceil(array.length / 2.0);
        } else {
            midpointIndex = (int) Math.ceil((array.length - 1) / 2.0);
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

        while (nodeQueue.isEmpty() == false) {

            LinkedList<BinaryTreeNode> previousLevelLinkedList = nodeQueue.remove();
            LinkedList<BinaryTreeNode> currentLevelLinkedList = new LinkedList<>();

            Iterator<BinaryTreeNode> listIterator = previousLevelLinkedList.listIterator();
            while (listIterator.hasNext()) {
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

    // Successor of current node is leftmost node of right subtree
    // If current node does not have a right subtree, start working back up the tree
    public static BinaryTreeNode getNextNodeInBinarySearchTree(BinaryTreeNode inputNode) {

        if (inputNode.getRightChild() != null) {
            // get leftmost child of this right child
            BinaryTreeNode childNode = inputNode.getRightChild();

            if (childNode.getLeftChild() != null) {
                return getLeftmostChild(childNode);
            }

            return childNode;
        } else {
            // find a parent node that has a node to the right of me
            if (inputNode.getParentNode() == null) {
                // if right child and parent node are both null, then this is the rightmost
                // node - there is no next node
                return null;
            }

            BinaryTreeNode parentNode = inputNode.getParentNode();
            if (parentNode.getRightChild() == inputNode) {
                // this node is the right child of its parent, so we need to start working up the tree
                // this node's parent is less than it, but grandparent could be next
                BinaryTreeNode grandparentNode = parentNode.getParentNode();
                if (grandparentNode == null) {
                    // parent node is the root, and this is its right child.  there is no next node
                    return null;
                } else {
                    // move up the tree until we hit the root, (which means this node is already right most)
                    // or until we find a grandparent node where its right child is not the previous parent
                    while (grandparentNode != null && grandparentNode.getRightChild() == parentNode) {
                        parentNode = grandparentNode;
                        grandparentNode = parentNode.getParentNode();
                    }

                    if (grandparentNode == null) {
                        // we hit the top of the tree, so this node is the right most
                        return null;
                    } else {
                        // grandparent node has a right child that is not in the subtree we came from
                        // either grandparent is next, or we need to get its leftmost node under the right child
                        if (grandparentNode.getLeftChild() == parentNode) {
                            // we came up from the left, which means the grandparent is next
                            return grandparentNode;
                        }

                        return getLeftmostChild(grandparentNode.getRightChild());
                    }
                }
            } else {
                // this node is the left child - the parent node should be next
                return parentNode;
            }
        }
    }

    private static BinaryTreeNode getLeftmostChild(BinaryTreeNode node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }

        return node;
    }

    public static BinaryTreeNode getCommonAncestor(BinaryTreeNode rootNode, BinaryTreeNode node1, BinaryTreeNode node2) {

        if (covers(rootNode, node1) == false || covers(rootNode, node2) == false) {
            // one node or the other does not exist in the tree, so there are no common ancestors
            return null;
        }

        return ancestorHelper(rootNode, node1, node2);
    }

    private static BinaryTreeNode ancestorHelper(BinaryTreeNode rootNode, BinaryTreeNode node1, BinaryTreeNode node2){
        if (rootNode == null || rootNode == node1 || rootNode == node2) {
            return rootNode;
        }

        boolean leftChildIsAncestorOfNode1 = covers(rootNode.getLeftChild(), node1);
        boolean leftChildIsAncestorOfNode2 = covers(rootNode.getLeftChild(), node2);

        if (leftChildIsAncestorOfNode1 != leftChildIsAncestorOfNode2) {
            // nodes are in different subtrees. parent must be the ancestor
            return rootNode;
        }

        BinaryTreeNode nextNodeToSearch = leftChildIsAncestorOfNode1 ? rootNode.getLeftChild() : rootNode.getRightChild();

        return ancestorHelper(nextNodeToSearch, node1, node2);
    }

    private static boolean covers(BinaryTreeNode rootNode, BinaryTreeNode possibleChildNode) {
        if (rootNode == null) {
            return false;
        }

        if (rootNode == possibleChildNode) {
            return true;
        }

        return covers(rootNode.getLeftChild(), possibleChildNode) || covers(rootNode.getRightChild(), possibleChildNode);
    }

    private static boolean hasChild(BinaryTreeNode parentNode, BinaryTreeNode possibleChildNode) {
        if (parentNode == possibleChildNode) {
            return true;
        }

        boolean nodeFound = false;
        Queue<BinaryTreeNode> queue = new LinkedList<>();

        queue.add(parentNode);

        while(queue.isEmpty() == false && nodeFound == false) {
            BinaryTreeNode node = queue.remove();

            if (node == possibleChildNode) {
                nodeFound = true;
            }

            if(node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }

            if(node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
        }

        return nodeFound;
    }
}
