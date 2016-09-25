package com.cosmos.calculator.Injection;

import dagger.ObjectGraph;

/**
 * Created by sattallah on 5/26/2016.
 */
public class GraphProvider {
    private static GraphProvider instance;
    private ObjectGraph graph;

    public static GraphProvider getInstance() {
        if (instance == null) {
            instance = new GraphProvider();
        }
        return instance;
    }

    private GraphProvider() {
        graph = ObjectGraph.create();
    }

    public void addModules(Object... modules) {
        graph = graph.plus(modules);
    }

    public ObjectGraph getGraph() {
        return graph;
    }
}
