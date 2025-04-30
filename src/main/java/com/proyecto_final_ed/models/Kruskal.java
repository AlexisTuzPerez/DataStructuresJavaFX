package com.proyecto_final_ed.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Kruskal {
    private int[] fathers; 
    private int mstWeight; 

    public Kruskal(int size) {
        fathers = new int[size];
        for (int i = 0; i < size; i++) {
            fathers[i] = i;
        }
    }


    private int find(int x) {
        if (fathers[x] == x) {
            return x;
        }
        fathers[x] = find(fathers[x]); 
        return fathers[x];
    }

    private void unite(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        fathers[fx] = fy;
    }

    public Map<String, Map<String, Integer>> computeMST(Map<String, Map<String, Integer>> inputGraph) {
        Map<String, Map<String, Integer>> mstGraph = new HashMap<>(); 
        List<Edge> edges = new ArrayList<>();
        Set<String> nodes = inputGraph.keySet();


        for (String node : nodes) {
            mstGraph.put(node, new HashMap<>());
        }

        for (String fromNode : nodes) {
            Map<String, Integer> neighbors = inputGraph.get(fromNode);
            for (Map.Entry<String, Integer> edge : neighbors.entrySet()) {
                String toNode = edge.getKey();
                int weight = edge.getValue();
                edges.add(new Edge(weight, fromNode, toNode));
            }
        }

        edges.sort(Comparator.comparingInt(edge -> edge.weight));
    
        for (Edge edge : edges) {
            int fromId = edge.fromNode.hashCode();
            int toId = edge.toNode.hashCode();

            if (find(fromId) != find(toId)) {
                unite(fromId, toId);
                mstGraph.get(edge.fromNode).put(edge.toNode, edge.weight);
                mstWeight += edge.weight;
            }
        }


        return mstGraph;
    }


    private static class Edge {
        int weight;
        String fromNode;
        String toNode;

        Edge(int weight, String fromNode, String toNode) {
            this.weight = weight;
            this.fromNode = fromNode;
            this.toNode = toNode;
        }
    }
    public int getWeight() {
        return mstWeight;
    }
    
    

    //Test
    public static void main(String[] args) {

        Graph.addEdge("1", "2", 4);
        Graph.addEdge("7", "2", 2);
        Graph.addEdge("6", "2", 3);
        Graph.addEdge("6", "5", 1);
        Graph.addEdge("5", "3", 20);
        Graph.addEdge("4", "3", 6);
        Graph.addEdge("1", "4", 7);
        Graph.addEdge("2", "5", 2);
        Graph.addEdge("2", "3", 1);


        Kruskal kruskal = new Kruskal(1000); 
        Map<String, Map<String, Integer>> mst = kruskal.computeMST(Graph.getGraph());


        for (String fromNode : mst.keySet()) {
            for (Map.Entry<String, Integer> edge : mst.get(fromNode).entrySet()) {
                System.out.println(fromNode + " -> " + edge.getKey() + " [weight: " + edge.getValue() + "]");
            }
        }
    }
}
