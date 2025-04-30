package com.proyecto_final_ed.controllers;

import com.proyecto_final_ed.SceneManager;
import com.proyecto_final_ed.models.LinkedList;
import com.proyecto_final_ed.models.Order;
import com.proyecto_final_ed.models.OrderManager;
import com.proyecto_final_ed.models.Product;
import com.proyecto_final_ed.models.ProductManager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class AddOrderController {


    @FXML
    private TextField idInput;


    @FXML 
    private TextField amountInput;

    @FXML
    private Label confirmText;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contentBox;

    @FXML
    private Label texto;


    private String stringId;
    private String stringAmount;

    private int id;

    private int amount;

    private Order order = new Order();


    private int index;

    private int amountProducts; 

    private double price;

    private double totalPrice;

    @FXML
    private TextField deleteOrderInput;


    @FXML
    private void addProductsToOrder(){



        stringId = idInput.getText(); 
        stringAmount = amountInput.getText();
        confirmText.setTextFill(Color.WHITE);

        if(  verifyInfo( stringAmount, stringId) == true){

            id = Integer.parseInt(stringId);
            amount = Integer.parseInt(stringAmount);

            if (verifyProductExists(id) == true && amount > 0 ){



                if (verifyProductosInArray(id, order)== true){

                    for (int i = 0; i < order.getProductsArray().length; i++){
                    
                        if (id == order.getProductsArray()[i]){    
                            index = i;
                        }
                    }
                    order.getAmountArray()[index] += amount;
                    showProductsInScreen();

                }else{

                    order.addProductToArray(id, amount);
                    showProductsInScreen();

                }
            
                confirmText.setText("Producto Agregador Correctamente");
                confirmText.setTextFill(Color.GREEN);
                texto.setText("Continue Agregando Productos o Guarde");

            } else {

                confirmText.setText("Error al ingresar datos, producto no existente o la cantidad debe ser mayor a cero");
                confirmText.setTextFill(Color.RED);
    
            }

        } else {

            confirmText.setText("Error al ingresar datos, verifique que los datos sean Enteros");
            confirmText.setTextFill(Color.RED);

        }

    }

    private Boolean verifyProductosInArray(int id, Order order){

        for (int i = 0; i < order.getProductsArray().length; i++){
            if (id == order.getProductsArray()[i]){
                return true;
            }
        }
        return false;



    }

    private Boolean verifyProductExists(int id){

        LinkedList<Product> products= ProductManager.getProducts();
        if (products.isEmpty()) {

            return false;
        }
        for ( int i =0; i<products.size(); i++){
            Product product = products.get(i);  
            if (product.getId() == id) {

                return true;
            }
        }
        return false;

       


    }
    private Boolean verifyInfo(String stringAmount, String stringId){

        try {
            Integer.parseInt(stringAmount);
            Integer.parseInt(stringId);
            return true;

        } catch ( NumberFormatException e){

            return false;
        }


    }

    private void showProductsInScreen(){

        contentBox.getChildren().clear();
        int []ids= order.getProductsArray();
        int [] amounts= order.getAmountArray();
        for (int i = 0; i < ids.length; i++){
            Label productLabel = new Label("");
            productLabel.setText( "Id: " + ids[i] + "/ " + "cantidad: " + amounts[i]);
            String idString = String.format("%s", ids[i]);

            productLabel.setId(idString);
            productLabel.getStyleClass().add("scrollPaneChild");
            contentBox.getChildren().add(productLabel);
        }

    }


    private void setAmountAndPrice(){


        if (order.getProductsArray().length > 0){

            for(int i= 0; i < order.getProductsArray().length; i++){
                 
                for(int j = 0; j < ProductManager.getProducts().size(); j++){
                    if ( order.getProductsArray()[i] ==  ProductManager.getProducts().get(j).getId()){
                        price = ProductManager.getProducts().get(j).getPrice();
                        break;
                    }
                }
                amountProducts += order.getAmountArray()[i];
                totalPrice += price*order.getAmountArray()[i];
            }
            order.setTotalPrice(totalPrice);
            order.setAmountProducts(amountProducts);

            OrderManager.addOrder(order);

            confirmText.setText("Orden Agregada Correctamente" + " cantidad de productos " + amountProducts + " Precio total: " + totalPrice);
            texto.setText("Continue Agregando Ordenes o Regrese");
            resetValues();

            confirmText.setTextFill(Color.LIGHTBLUE);
        } else { 
            confirmText.setText("Error al cargar Orden, no cuenta con productos");
            confirmText.setTextFill(Color.RED);
            
        }

    }

    private void resetScene(){
        confirmText.setText("");
        idInput.setText("Id");
        amountInput.setText("Cantidad");
        texto.setText("Agrege la información correspondiente:");
        contentBox.getChildren().clear();

    }

    private void resetValues(){

        order = new Order();
        stringId="";
        stringAmount="";
        id=0;
        amount=0;
        index=0;
        amountProducts=0; 
        price=0;
        totalPrice= 0;

    }

    @FXML
    private void saveOrder(){
        resetScene();



        //order.printProductsArray();
        //OrderManager.addOrder(order);


        setAmountAndPrice();

        resetValues();

        //OrderManager.printOrders();



    }


    @FXML
    private void deleteOrder(){

        String deleteString = deleteOrderInput.getText();
        System.out.println(deleteString);

        if (verifyInfo(deleteString) == true){

            int deleteId = Integer.parseInt(deleteString);
            if(OrderManager.find(deleteId) == true){

                OrderManager.delete(deleteId);

                confirmText.setText("Orden eliminada correctamente");
                confirmText.setTextFill(Color.GREEN);
            }else {

                confirmText.setText("No existe orden con el ID: " + deleteId);
                confirmText.setTextFill(Color.RED);
                

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
    private void backMenu(){
        resetScene();
        resetValues();
        SceneManager.showScene("primary");
        confirmText.setText("");
    }


    @FXML
    private void infoText(){

        Alert infoAlert = new Alert(AlertType.INFORMATION);
        infoAlert.setTitle("Info ");
        infoAlert.setHeaderText("Info ");

       String info= "Las Ordenes se almacenan en un arreglo. Las ordenes cuentan a su vez con un arreglo de productos  \n" +
                    "\n"+ 
                    "Es esta página se agregar ordenes y eliminar. Para añadir ordenes se deben añadir productos primero.  \n" +
                    "\n"




                    ;
       infoAlert.setContentText(info);
       infoAlert.showAndWait();

   }


    
}
