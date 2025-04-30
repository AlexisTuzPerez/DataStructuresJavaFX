package com.proyecto_final_ed;

import java.io.IOException;

import com.proyecto_final_ed.models.Graph;
import com.proyecto_final_ed.models.Product;
import com.proyecto_final_ed.models.ProductManager;
import com.proyecto_final_ed.models.Tree;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application   {


    @Override
    public void start(Stage primaryStage) throws IOException {

        Product product;
        product = new Product("Damn", "Kendrick Lamar", "Album", 90.0);
        Tree.insert(product.getId());
        ProductManager.addProduct(product);
        product = new Product("GKMC", "Kendrick Lamar", "Album", 100.0);
        Tree.insert(product.getId());
        ProductManager.addProduct(product);
        product = new Product("111", "Milo J", "Album", 50.0);
        Tree.insert(product.getId());
        ProductManager.addProduct(product);
        product = new Product("166", "Milo J", "Album", 40.0);
        Tree.insert(product.getId());
        ProductManager.addProduct(product); 


         
        Graph.addEdge("a", "b", 5);
        Graph.addEdge("a", "d", 4);
        Graph.addEdge("b", "d", 1);
        Graph.addEdge("b", "e", 2);
        Graph.addEdge("b", "c", 7);
        Graph.addEdge("e", "d", 3);
        Graph.addEdge("e", "c", 6);
        Graph.addEdge("d", "c", 6); 
 
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.loadScene("primary", "/com/proyecto_final_ed/primary.fxml","/com/proyecto_final_ed/styles.css");
        sceneManager.loadScene("addProduct", "/com/proyecto_final_ed/addProduct.fxml","/com/proyecto_final_ed/styles.css");
        sceneManager.loadScene("seeProducts", "/com/proyecto_final_ed/seeProducts.fxml","/com/proyecto_final_ed/styles.css");
        sceneManager.loadScene("addOrder", "/com/proyecto_final_ed/addOrder.fxml","/com/proyecto_final_ed/styles.css");
        sceneManager.loadScene("seeOrders", "/com/proyecto_final_ed/seeOrders.fxml","/com/proyecto_final_ed/styles.css");
        sceneManager.loadScene("productsTree", "/com/proyecto_final_ed/productsTree.fxml","/com/proyecto_final_ed/styles.css");
        sceneManager.loadScene("stack", "/com/proyecto_final_ed/stack.fxml","/com/proyecto_final_ed/styles.css");
        sceneManager.loadScene("graph", "/com/proyecto_final_ed/graph.fxml","/com/proyecto_final_ed/styles.css");
        sceneManager.loadScene("binarySearch", "/com/proyecto_final_ed/binarySearch.fxml","/com/proyecto_final_ed/styles.css");
        SceneManager.showScene("primary");


    }


} 


/* 
 

 
mvn javafx:run




 */