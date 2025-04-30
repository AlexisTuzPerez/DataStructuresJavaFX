package com.proyecto_final_ed.models;

import java.util.Stack;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class StackManager {


    private static Stack<Product> stack = new Stack<>();

    private static VBox contentBox = new VBox();


    public static void addProduct(Product product){

        stack.push(product);
        addToContentBox(product);
        


    }


    public static void addToContentBox(Product product){

        Label productLabel = new Label("");

        productLabel.setText(  product.getId()   + " / " + product.getName() + " / " + product.getAutor() + " / " + product.getCategory() + " / "+ product.getPrice());
        String idString = String.format("%s", product.getId());
        productLabel.setId(idString);
        productLabel.getStyleClass().add("scrollPaneChild");
        contentBox.getChildren().add(0,productLabel); 

    }

    public static Product deleteProduct(){

        contentBox.getChildren().remove(0);
        return stack.pop();

    }


    public static VBox getVBox(){
        return contentBox;
    }

    public static Stack<Product> getStack(){
        return stack;
    }

    
}
