package com.proyecto_final_ed.models;

import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class VisualizeTree {

    private Graph graph;
    private int edgeCounter = 0;

    public VisualizeTree() {
        graph = new SingleGraph("Binary Tree");

        graph.setAttribute("ui.stylesheet",
            "node {" +
            "   fill-color: blue;" +
            "   size: 30px;" +
            "   text-size: 20px;" +
            "   text-alignment: center;" +
            "   text-color: white;" +
            "}" +
            "edge {" +
            "   fill-color: gray;" +
            "   size: 2px;" +
            "}"
        );
    }

    public void buildFromInOrder(List<Integer> values) {
        if (values.isEmpty()) return;

        buildTree(values, 0, values.size() - 1, null, 0, 0, 400);
    }

    private void buildTree(List<Integer> values, int start, int end, String parentId, int level, double x, double deltaX) {
        if (start > end) return;

        int mid = (start + end) / 2;
        String nodeId = "node-" + values.get(mid);

        try {
            Node newNode = graph.addNode(nodeId);
            newNode.setAttribute("ui.label", values.get(mid));
            newNode.setAttribute("xyz", x, -level * 100, 0);
        } catch (Exception e) {
            System.err.println("Error adding node: " + nodeId + " - " + e.getMessage());
            return;
        }

        if (parentId != null) {
            String edgeId = "edge-" + edgeCounter++; 
            try {
                graph.addEdge(edgeId, parentId, nodeId, false); 
            } catch (Exception e) {
                System.err.println("Error adding edge: " + edgeId + " - " + e.getMessage());
            }
        }


        buildTree(values, start, mid - 1, nodeId, level + 1, x - deltaX, deltaX / 2);
        buildTree(values, mid + 1, end, nodeId, level + 1, x + deltaX, deltaX / 2);
    }

    public Pane getView() {
        FxViewer viewer = new FxViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);


        viewer.disableAutoLayout();

        View view = viewer.addDefaultView(false);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent((javafx.scene.Node) view);

        scrollPane.setPrefWidth(700);
        scrollPane.setPrefHeight(400);


        Pane pane = new Pane(scrollPane);
        pane.setPrefWidth(700);
        pane.setPrefHeight(400);

        return pane;
    }
}
