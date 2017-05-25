package org.interview.linkedlist;

import lombok.Data;

@Data
public class LinkedListNode {
    private LinkedListNode nextNode = null;
    private int data;

    @Override
    public String toString() {
        return "LinkedListNode{" + "data=" + data + '}';
    }

    public LinkedListNode(int data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        return (this == o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + data;
        return result;
    }
}
