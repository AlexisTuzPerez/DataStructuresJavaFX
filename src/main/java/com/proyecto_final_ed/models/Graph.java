package com.proyecto_final_ed.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {

    private static final Map<String, Map<String, Integer>> graph = new HashMap<>();

    private Graph() {

    }

    public static void addNode(String node) {
        graph.putIfAbsent(node, new HashMap<>());
    }

    public static void addEdge(String from, String to, int value) {
        addNode(from);
        addNode(to);


        graph.get(from).put(to, value);
    }

    
    public static Boolean find(String node) {
        if (graph.containsKey(node)) {
            return true;
        }
        return false;
    }
    

    public static void deleteNode(String node) {

        graph.remove(node);

        for (Map<String, Integer> neighbors : graph.values()) {
            neighbors.remove(node);
        }
    }

    public static Set<String> getNodes() {
        return graph.keySet();
    }

    public static Map<String, Integer> getEdges(String node) {
        return graph.getOrDefault(node, Map.of());
    }

    public static boolean findNode(String node) {
        return graph.containsKey(node);
    }

    public static void printGraph() {
        for (String node : graph.keySet()) {
            System.out.println("Node " + node + " connects to: ");
            for (Map.Entry<String, Integer> edge : graph.get(node).entrySet()) {
                System.out.println("  -> " + edge.getKey() + " with value " + edge.getValue());
            }
        }
    }

    public static Map<String, Map<String, Integer>> getGraph() {
        return graph;
    }

    //Testing
    public static void main(String[] args) {

        Graph.addEdge("A", "B", 10);
        Graph.addEdge("A", "C", 20);
        Graph.addEdge("B", "C", 30);
        Graph.addEdge("A", "B", 40); 


        System.out.println("Nodes: " + Graph.getNodes());


        System.out.println("Edges from A: " + Graph.getEdges("A"));
        System.out.println("Edges from B: " + Graph.getEdges("B"));


        Graph.printGraph();
    }
}
