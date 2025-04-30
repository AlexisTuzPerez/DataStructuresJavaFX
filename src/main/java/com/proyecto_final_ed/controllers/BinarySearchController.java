package com.proyecto_final_ed.controllers;

import com.proyecto_final_ed.SceneManager;
import com.proyecto_final_ed.models.Order;
import com.proyecto_final_ed.models.OrderManager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class BinarySearchController {

    @FXML
    private TextField idInput;

    @FXML
    private Label confirmText;


    @FXML
    private void backMenu(){
        SceneManager.showScene("primary");

        confirmText.setText("");
        confirmText.setTextFill(Color.WHITE);


    }

    @FXML
    private void searchProduct(){


        String idString = idInput.getText();


        if (verifyInfo(idString) == true){

            int id = Integer.parseInt(idString);

            Order order = OrderManager.binarySearch(id);

            if (order == null){
                confirmText.setText("No existe la orden: ");
                confirmText.setTextFill(Color.RED);

            } else {
                confirmText.setText("Orden del id: " + order.getId() + " Cantidad de productos: " + order.getAmountProducts() + " Total: " + order.getTotalPrice());
                confirmText.setTextFill(Color.GREEN);

            }

            


        }else{


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
    private void infoText(){

        Alert infoAlert = new Alert(AlertType.INFORMATION);
        infoAlert.setTitle("Info ");
        infoAlert.setHeaderText("Info ");

       String info= "Los pedidos son guardados de un arreglo \n" +
                    "\n"+ 
                    "Si necesita buscar algun pedido, ingrese el ID"+
                
                    "\n"+ 
                    "Asegurese que sea un entero. El código de la búsqueda binaria esta en el OrderManager.java "



                    ;
       infoAlert.setContentText(info);
       infoAlert.showAndWait();

   }



        
    
}
