package com.proyecto_final_ed.models;

import java.util.Map;

import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class VisualizeGraph {
    private org.graphstream.graph.Graph graph;
    private int edgeCounter = 0;

    public VisualizeGraph() {


        graph = new SingleGraph("Directed Graph");
        graph.setAttribute("ui.stylesheet",
                "node {" +
                        "   fill-color: green;" +
                        "   size: 30px;" +
                        "   text-size: 18px;" +
                        "   text-alignment: center;" +
                        "   text-color: white;" +
                        "   stroke-mode: plain;" +
                        "   stroke-color: black;" +
                        "}" +
                        "node.marked {" +
                        "   fill-color: orange;" +
                        "}" +
                        "edge {" +
                        "   fill-color: gray;" +
                        "   arrow-size: 12px, 6px;" +
                        "   size: 2px;" +
                        "   text-size: 14px;" +
                        "}");
    }

    public void buildFromGraph(Map<String, Map<String, Integer>> graphData) {

        for (String fromNode : graphData.keySet()) {
            addNode(fromNode);

            for (Map.Entry<String, Integer> edge : graphData.get(fromNode).entrySet()) {
                String toNode = edge.getKey();
                int value = edge.getValue();

                addNode(toNode);
                addEdge(fromNode, toNode, value);
            }
        }
    }

    private void addNode(String nodeId) {
        if (graph.getNode(nodeId) == null) {
            Node node = graph.addNode(nodeId);
            node.setAttribute("ui.label", nodeId);
        }
    }

    private void addEdge(String from, String to, int value) {
        String edgeId = "edge-" + edgeCounter++;
        if (graph.getEdge(edgeId) == null) {
            graph.addEdge(edgeId, from, to, true).setAttribute("ui.label", String.valueOf(value));
        }
    }

    public Pane obtainView() {

        FxViewer viewer = new FxViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();

        View view = viewer.addDefaultView(false);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent((javafx.scene.Node) view);

        Pane pane = new Pane(scrollPane);

        scrollPane.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefHeightProperty().bind(pane.heightProperty());

        return pane;
    }
}
