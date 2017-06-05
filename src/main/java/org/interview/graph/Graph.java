package org.interview.graph;

import lombok.Getter;
import lombok.Setter;

public class Graph {

    @Getter
    @Setter
    private GraphNode[] nodes = new GraphNode[16];

    public GraphNode getNode(String name) {
        if (nodes.length == 0) {
            return null;
        }

        GraphNode node = null;

        int nodeIndex = 0;
        while(node == null && nodeIndex < nodes.length) {
            if (nodes[nodeIndex] != null && nodes[nodeIndex].getName().equals(name)) {
                node = nodes[nodeIndex];
            }
            nodeIndex++;
        }

        return node;
    }

    public void addNodeToGraph(GraphNode node) {

        boolean nodeAlreadyInList = false;
        int nodeIndex = 0;
        while(nodeAlreadyInList == false && nodeIndex < nodes.length) {
            if (nodes[nodeIndex] == node) {
                nodeAlreadyInList = true;
            }
            nodeIndex++;
        }

        if (nodeAlreadyInList == false) {
            nodeIndex = 0;
            while(nodeIndex < nodes.length && nodes[nodeIndex] != null) {
                nodeIndex++;
            }

            nodes[nodeIndex] = node;
            setNodes(nodes);
        }

    }

    public void removeNodeFromGraph(GraphNode node) {
        boolean foundNode = false;

        int nodeIndex = 0;
        while(foundNode == false && nodeIndex < nodes.length) {
            if(nodes[nodeIndex] == node) {
                foundNode = true;
                nodes[nodeIndex] = null;
            }

            nodeIndex++;
        }
    }
}
