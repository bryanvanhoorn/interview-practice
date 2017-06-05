package org.interview.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public static List<String> getBuildOrder(List<String> projectList, List<String> dependencyList) {
        // Build directed graph of dependency chain
        Graph dependencyGraph = new Graph();

        for(String dependency : dependencyList) {
            String[] dependentNodes = dependency.split(",");

            if (projectList.contains(dependentNodes[0]) == false || projectList.contains(dependentNodes[1]) == false) {
                throw new RuntimeException ("detected dependency on project not in our project list");
            }

            // 1 is dependent on 0, so point 1 -> 0
            GraphNode node1 = dependencyGraph.getNode(dependentNodes[1]);
            if (node1 == null) {
                node1 = new GraphNode(dependentNodes[1]);
                dependencyGraph.addNodeToGraph(node1);
            }

            GraphNode node0 = dependencyGraph.getNode(dependentNodes[0]);
            if (node0 == null) {
                node0 = new GraphNode(dependentNodes[0]);
                dependencyGraph.addNodeToGraph(node0);
            }

            // if node0 is not already in the list of children, add it
            addChild(node1, node0);
        }

        for(GraphNode node : dependencyGraph.getNodes()) {
            if (node != null) {
                System.out.println("Node " + node.getName() + " is dependent on the following nodes: ");
                for (GraphNode childNode : node.getChildren()) {
                    if (childNode != null) {
                        System.out.println(childNode.getName());
                    }
                }

            }
        }

        List<String> orderedProjectList = new ArrayList<>();

        // iterate through the projects in our list, add them and their dependencies in order
        for(String project : projectList) {
            // if this node is already in our ordered list, skip it
            if (orderedProjectList.contains(project) == false) {
                GraphNode projectNode = dependencyGraph.getNode(project);

                if (projectNode == null) {
                    // there are no dependencies either way, so add this one
                    orderedProjectList.add(project);
                } else {
                    resetNodesToUnvisited(dependencyGraph);
                    addNodesToList(projectNode, orderedProjectList);
                }
            }
        }

        System.out.println(orderedProjectList);

        return orderedProjectList;
    }

    private static void resetNodesToUnvisited(Graph dependencyGraph) {
        for(GraphNode node : dependencyGraph.getNodes()) {
            if (node != null) {
                node.setNodeState(GraphNode.state.UNVISITED);
            }
        }
    }

    private static void addNodesToList(GraphNode node, List<String> orderedProjectList) {
        if (node == null || orderedProjectList.contains(node.getName())) {
            return;
        }

        if(node.getNodeState() == GraphNode.state.VISITING) {
            // if we were already visiting this node, that means we have a cycle
            // in our dependency chain
            throw new RuntimeException("cycle detected in dependency chain");
        } else {
            node.setNodeState(GraphNode.state.VISITING);
        }

        GraphNode[] childList = node.getChildren();
        for(int i = 0; i < childList.length; i++) {
            GraphNode childNode = childList[i];
            if (childNode != null) {
                addNodesToList(childNode, orderedProjectList);
            }
        }

        orderedProjectList.add(node.getName());
        node.setNodeState(GraphNode.state.VISITED);
    }

    private static void addChild(GraphNode parentNode, GraphNode childNode) {
        GraphNode[] childNodes =  parentNode.getChildren();

        boolean nodeAlreadyInList = false;
        int nodeIndex = 0;
        while(nodeAlreadyInList == false && nodeIndex < childNodes.length) {
            if (childNodes[nodeIndex] == childNode) {
                nodeAlreadyInList = true;
            }
            nodeIndex++;
        }

        if (nodeAlreadyInList == false) {
            GraphNode[] newChildNodes = new GraphNode[childNodes.length + 1];
            nodeIndex = 0;
            while(nodeIndex < childNodes.length) {
                newChildNodes[nodeIndex] = childNodes[nodeIndex];
                nodeIndex++;
            }

            newChildNodes[nodeIndex] = childNode;
            parentNode.setChildren(newChildNodes);
        }
    }

}
