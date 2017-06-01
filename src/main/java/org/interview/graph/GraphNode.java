package org.interview.graph;

import lombok.Getter;
import lombok.Setter;

public class GraphNode {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private GraphNode[] children;

    public enum state {VISITED, VISITING, UNVISITED}

    @Getter
    @Setter
    private state nodeState;

    public GraphNode(String name) {
        this.name = name;
    }
}
