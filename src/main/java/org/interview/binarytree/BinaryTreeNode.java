package org.interview.binarytree;

import lombok.Data;

@Data
public class BinaryTreeNode {

    private int data;

    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;
    private BinaryTreeNode parentNode;

    public BinaryTreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }
}
