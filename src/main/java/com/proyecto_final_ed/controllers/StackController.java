package com.proyecto_final_ed.controllers;

import com.proyecto_final_ed.SceneManager;
import com.proyecto_final_ed.models.Product;
import com.proyecto_final_ed.models.ProductManager;
import com.proyecto_final_ed.models.StackManager;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class StackController {

    @FXML
    private VBox contentBox;

    @FXML
    private TextField idInput;


    @FXML
    private Label confirmText;


    @FXML
    private void backMenu(){

        SceneManager.showScene("primary");
    }

    @FXML 
    private void addProducts(){

        String stringId = idInput.getText();

        if (verifyInfo( stringId )== true){

            int id = Integer.parseInt(stringId);


            if (ProductManager.find(id)== true) {

                Product product =ProductManager.getProductsById(id);

                System.out.println(  product.getId()   +" /" + product.getName() + " / " + product.getAutor() + " / " + product.getCategory() + " /" + product.getPrice());
                StackManager.addProduct(product);
    
                showProducts();


                confirmText.setText("Producto agregador correctamente a la pila");
                confirmText.setTextFill(Color.GREEN);
                
            }else{

                confirmText.setText("No existe el producto con ID: " +  id);
                confirmText.setTextFill(Color.RED);

            }






        }else {

            confirmText.setText("Ingrese un valor entero");
            confirmText.setTextFill(Color.RED);

        }


    }

    private boolean verifyInfo(String input ){

        try{
            Integer.parseInt(input);

            return true;
        }catch (NumberFormatException e){
            return false;
        
        } 
    }




    @FXML
    private void deleteFromStack(){


        if (StackManager.getStack().size() > 0){

            System.out.println(StackManager.deleteProduct().getName());

            showProducts();

        }else {
            System.out.println("stack vacío");
        }


        





    }


    private void showProducts(){
        contentBox.getChildren().clear();

        VBox box = StackManager.getVBox();

        box.setAlignment(Pos.CENTER);
        box.setStyle("scrollPane");
    
        box.setSpacing(10);
        contentBox.getChildren().add(box);
    }




}

    

