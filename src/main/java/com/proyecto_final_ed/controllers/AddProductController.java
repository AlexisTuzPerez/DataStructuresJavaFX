package com.proyecto_final_ed.controllers;

import com.proyecto_final_ed.SceneManager;
import com.proyecto_final_ed.models.Product;
import com.proyecto_final_ed.models.ProductManager;
import com.proyecto_final_ed.models.Tree;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class AddProductController {


    @FXML
    private Label texto;
    @FXML 
    private Label confirmText;
    @FXML
    private TextField nameInput;
    @FXML 
    private TextField autorInput;
    @FXML 
    private TextField priceInput;
    @FXML
    private MenuButton categoryMenu;



    private String name;
    private String autor;
    private  String category;

    private String stringPrice;
    private Double price;



    @FXML
    private void option( ActionEvent event){

        category= ((MenuItem) event.getSource()).getText();
        categoryMenu.setText(category);


    }



    @FXML
    private void addProduct(){



        name = nameInput.getText();
        autor = autorInput.getText();

        stringPrice = priceInput.getText();



        if (verifyInfo(stringPrice, category) == true){
            price = Double.parseDouble(stringPrice);



            confirmText.setText("Producto Agregador Correctamente");
            confirmText.setTextFill(Color.GREEN);
            texto.setText("Continue Agregando Productos o Regrese");
            addProductToList(name, autor, category, price);



            Tree.insert(ProductManager.getProducts().get(ProductManager.getProducts().size()-1).getId());

        } else {
            confirmText.setText("Error al ingresar datos");
            confirmText.setTextFill(Color.RED);

        }
        

    }

    private boolean verifyInfo(String price, String category ){

        if (category == null){
            return false;
        }

        try{
            Double.parseDouble(price);

            return true;
        }catch (NumberFormatException e){
            return false;
        
        } 
    }
    private void addProductToList(String name, String autor, String category, Double price){
        Product product = new Product(name, autor, category, price);


        ProductManager.addProduct(product);


    }
 
    private void resetScene(){

        name = null;
        autor= null;
        category= null;

        stringPrice= null;
        price= null;
        texto.setText("Agrege la información correspondiente:");

        
        confirmText.setTextFill(Color.WHITE);
        confirmText.setText("");
        

        nameInput.setText("Nombre");
        autorInput.setText("Autor");
        priceInput.setText("Precio");
        categoryMenu.setText("Category");

    }


    @FXML
    private void backMenu(){
        resetScene();

        SceneManager.showScene("primary");
        

    }

       @FXML
    private void infoText(){

         Alert infoAlert = new Alert(AlertType.INFORMATION);
         infoAlert.setTitle("Info ");
         infoAlert.setHeaderText("Info ");

        String info= "Los productos se guardan en una lista doblemente ligada, en esta pantalla se pueden agregar productos \n" +
                     "\n"+ 
                     "Se debe escoger una categoria, y el precio debe ser un Double \n"

                     ;
        infoAlert.setContentText(info);
        infoAlert.showAndWait();

    }

    





}