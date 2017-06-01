package org.interview.graph;

import java.util.LinkedList;
import java.util.Queue;

public class GraphUtils {

    public static boolean isExistingRoute(Graph graph, GraphNode node1, GraphNode node2) {
        if (node1 == node2) {
            return true;
        }

        // mark all nodes as unvisited
        for (GraphNode node : graph.getNodes()) {
            node.setNodeState(GraphNode.state.UNVISITED);
        }

        // use queue to do breadth-first search
        Queue<GraphNode> nodeQueue = new LinkedList<GraphNode>();
        nodeQueue.add(node1);

        while (nodeQueue.isEmpty() == false) {
            GraphNode nextNode = nodeQueue.remove();

            if (nextNode.getNodeState() != GraphNode.state.VISITED) {

                nextNode.setNodeState(GraphNode.state.VISITING);

                if (nextNode == node2) {
                    return true;
                }

                for (GraphNode node : nextNode.getChildren()) {
                    nodeQueue.add(node);
                }

                nextNode.setNodeState(GraphNode.state.VISITED);
            }
        }

        return false;
    }

}
