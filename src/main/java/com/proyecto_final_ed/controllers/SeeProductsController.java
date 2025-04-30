package com.proyecto_final_ed.controllers;

import java.io.IOException;

import com.proyecto_final_ed.SceneManager;
import com.proyecto_final_ed.models.Product;
import com.proyecto_final_ed.models.ProductManager;
import com.proyecto_final_ed.models.QuickSort;
import com.proyecto_final_ed.models.Tree;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;







public class SeeProductsController {

    @FXML
    private VBox contentBox;

    @FXML
    private Button showProductsButton;


    @FXML 
    private ScrollPane scrollPane;

    @FXML
    private TextField deleteProductInput;

    @FXML 
    private Label confirmText;



  
    @FXML
    private void showProductsInScreen(){

        scrollPane.setVisible(true);


        contentBox.getChildren().clear();

        com.proyecto_final_ed.models.LinkedList<Product> products = ProductManager.getProducts();
        System.out.println("Size: " + products.size());

        if (products.size() != 0){
            Product[] productsArray = new Product[products.size()];

            for (int i =0; i< products.size(); i++){
                productsArray[i] = products.get(i);

                Label productLabel = new Label("");
                productLabel.setText(  productsArray[i].getId()   +" /" + productsArray[i].getName() + " / " + productsArray[i].getAutor() + " / " + productsArray[i].getCategory() + " /" + productsArray[i].getPrice());
                productLabel.setId(productsArray[i].getName());
                productLabel.getStyleClass().add("scrollPaneChild");
                contentBox.getChildren().add(productLabel);

            } 

        }else {
            System.out.println("No existen productos");
        }

    }

    @FXML
    private void sortInScreen(){

        scrollPane.setVisible(true);

        contentBox.getChildren().clear();
        
        com.proyecto_final_ed.models.LinkedList<Product> products = ProductManager.getProducts();

        if (products.size() == 0) {
            System.out.println("No hay productos para ordenar.");
            return;
        }
        Product[] productsArray = new Product[products.size()];

        products.toArray(productsArray);

        QuickSort.quickSort(productsArray, 0, productsArray.length - 1);


        for (int i =0; i< products.size(); i++){

            Label productLabel = new Label("");
            productLabel.setText(  productsArray[i].getId()   +" /" + productsArray[i].getName() + " / " + productsArray[i].getAutor() + " / " + productsArray[i].getCategory() + " /" + productsArray[i].getPrice());
            productLabel.setId(productsArray[i].getName());
            productLabel.getStyleClass().add("scrollPaneChild");
            contentBox.getChildren().add(productLabel);

        } 

    }


    @FXML
    private void backMenu() throws IOException{
        SceneManager.showScene("primary");
        showProductsButton.setVisible(true);

        scrollPane.setVisible(false);
        contentBox.getChildren().clear();

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
    private void deleteProduct(){

        String input = deleteProductInput.getText();
        if (verifyInfo(input) == true  ){


            int id = Integer.parseInt(input);

            if (ProductManager.getProducts().contains(id)== true){
                ProductManager.getProducts().removeById(id);

                Tree.deleteId(id);

                showProductsInScreen();

                confirmText.setText("Producto eliminado correctamente");
                confirmText.setTextFill(Color.GREEN);

            }else{

                confirmText.setText("El producto con ID: " + id  + " no existe");
                confirmText.setTextFill(Color.RED);

            }

        }else{
            confirmText.setText("Ingrese un valor entero");
            confirmText.setTextFill(Color.RED);
        }

    }
    @FXML
    private void infoText(){

        Alert infoAlert = new Alert(AlertType.INFORMATION);
       infoAlert.setTitle("Info");
       infoAlert.setHeaderText("Info ");

       String info= "Se eliminan productos de una lista doblemente ligada \n" +
                    "\n"+ 
                    "Se genera un arreglo con los productos y se utiliza QuickSort para ordenarlos de menor a mayor precio \n"

                    ;
       infoAlert.setContentText(info);
       infoAlert.showAndWait();

   }

 










     
}
