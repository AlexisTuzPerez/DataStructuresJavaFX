package com.proyecto_final_ed.controllers;

import java.io.IOException;

import com.proyecto_final_ed.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PrimaryController {

    @FXML
    private void switchToSecondary( ) throws IOException {
        //System.out.println("hola");
    }

    @FXML 
    private void switchToAddProduct() throws IOException{
        SceneManager.showScene("addProduct");
    }

    @FXML
    private void switchToSeeProducts() throws IOException{

        SceneManager.showScene("seeProducts");
    }


    @FXML
    private void switchToAddOrder() throws IOException{
        SceneManager.showScene("addOrder");
    }


    @FXML 
    private void switchToSeeOrders() throws IOException{
        SceneManager.showScene("seeOrders");

    }

    @FXML 
    private void switchToProductsTree() throws IOException{
        SceneManager.showScene("productsTree");
    }

    @FXML
    private void switchToStack() throws IOException{
        SceneManager.showScene("stack");
    }

    @FXML
    private void switchToGraph() throws IOException{
        SceneManager.showScene("graph");


    }

    @FXML
    private void switchToSearch()throws IOException{
        SceneManager.showScene("binarySearch");


    }

    @FXML
    private void infoText(){

         Alert infoAlert = new Alert(AlertType.INFORMATION);
        infoAlert.setTitle("Menu Principal");
        infoAlert.setHeaderText("Menu Principal");

        String info= "Se tiene una tienda de música, la tienda necesita necesita almacenar información útil para su funcionamiento\n" +
                     "\n"+
                     "La Función del programa depende alrededor de los productos y ordenes, se debe añadir productos y registrar ordenes primero \n" +
                     " \n"+ 
                     "La aplicación cuenta con distintas funciones:  \n" +
                     "-Agregar productos (Lista enlazada) \n" +
                     "-Ver productos, eliminar productos, Ordernamiento QuickSort (Listas enlazadas, Quick Sort) \n" +
                     "-Árboles (visualizar nodos, agregar nodos, eliminar nodos, buscar nodos, recorridos) \n" +
                     "-Agregar ordenes, las ordenes son guardada en un arreglo (agregar orden, eliminar orden) \n" +
                     "-Colas: ver ordenes, se utiliza una cola, se pueden eliminar ordenes de la cola \n"+
                     "-Grafos: (visualizar, agregar nodos, eliminar nodos, algoritmo de kruskal) \n"+
                     "-Búsqueda binaria de ordenes \n" +
                     "-Pila: caja de productos (agregar productos, eliminar productos)"



                     ;
        infoAlert.setContentText(info);
        infoAlert.showAndWait();

    }

}
