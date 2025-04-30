package com.proyecto_final_ed.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree {
    private static Node root; 
    private static List<Integer> nodes = new ArrayList<>(); 

    private static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void insert(int value) {
        nodes.add(value); 
        Collections.sort(nodes); 
        root = buildTree(0, nodes.size() - 1);
    }

    private static Node buildTree(int start, int end) {
        if (start > end) {
            return null; 
        }

        int mid = (start + end) / 2;
        Node node = new Node(nodes.get(mid)); 

        node.left = buildTree(start, mid - 1);
        node.right = buildTree(mid + 1, end);

        return node;
    }


    public static List<Integer> preOrderList() {
        List<Integer> result = new ArrayList<>();
        preOrderTraversal(root, result);
        return result;
    }

    private static void preOrderTraversal(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.value); 
        preOrderTraversal(node.left, result); 
        preOrderTraversal(node.right, result);
    }

    public static List<Integer> inOrderList() {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private static void inOrderTraversal(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, result);
        result.add(node.value); 
        inOrderTraversal(node.right, result); 
    }

    public static List<Integer> postOrderList() {
        List<Integer> result = new ArrayList<>();
        postOrderTraversal(root, result);
        return result;
    }

    private static void postOrderTraversal(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left, result);
        postOrderTraversal(node.right, result); 
        result.add(node.value); 
    }

    public static int find(int value) {
        Node current = root;

        while (current != null) {
            if (value == current.value) {
                return value;
            }
            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return -1;
    }

    public static boolean deleteId(int value) {
        if (root == null || find(value) == -1) {
            return false; 
        }

        nodes.remove(Integer.valueOf(value));
        root = buildTree(0, nodes.size() - 1); 
        return true; 
    }

    //Testing
    public static void main(String[] args) {

        Tree.insert(0);
        Tree.insert(1);
        Tree.insert(2);
        Tree.insert(3);
        Tree.insert(4);
        Tree.insert(5);
        Tree.insert(6);

        System.out.println("PreOrder: " + Tree.preOrderList());   // [3, 1, 0, 2, 5, 4, 6]
        System.out.println("InOrder: " + Tree.inOrderList());     // [0, 1, 2, 3, 4, 5, 6]
        System.out.println("PostOrder: " + Tree.postOrderList()); // [0, 2, 1, 4, 6, 5, 3]
    }
}
