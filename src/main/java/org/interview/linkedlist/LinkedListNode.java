package org.interview.linkedlist;

import lombok.Data;

@Data
public class LinkedListNode {
    private LinkedListNode nextNode = null;
    private int data;

    public LinkedListNode(int data) {
        this.data = data;
    }
}
