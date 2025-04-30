package com.proyecto_final_ed.controllers;

import com.proyecto_final_ed.SceneManager;
import com.proyecto_final_ed.models.OrderManager;
import com.proyecto_final_ed.models.QueueManager;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class SeeOrdersController {


 
    @FXML
    private VBox contentBox;


    @FXML 
    private Label confirmText;
     

    @FXML 
    private ScrollPane scrollPane;     
    @FXML
    private HBox box;
    
    
    @FXML 
    private Button showOrdersButton; 
    
    
    @FXML
    private void backMenu(){
    
        System.out.println("backMenu");
        SceneManager.showScene("primary");
    }
    
    
    @FXML
    private void popOrderFromQueue(){
    
        System.out.println("tamaño: " + QueueManager.getQueueSize());
    
    
        if (QueueManager.getQueueSize() > 0){

            int id =QueueManager.popOrderFromQueue().getId();
    
            System.out.println("Orden con id: " +  id + "eliminada");

            QueueManager.getVBox().getChildren().remove(0);
            
            confirmText.setText("Orden completada");
            confirmText.setTextFill(Color.GREEN);


            ordersInScreen();
    
        }else{
            confirmText.setText("No existen ordenes");
            confirmText.setTextFill(Color.RED);

        }
    
    }
            
    @FXML
    private void ordersInScreen(){
        if (QueueManager.getQueueSize() >0){

            contentBox.getChildren().clear();
    
            VBox box= QueueManager.getVBox();
            box.setAlignment(Pos.CENTER);
            box.setStyle("scrollPane");
    
            box.setSpacing(10);
            contentBox.getChildren().add(box);    
    
            box.getChildren().remove(showOrdersButton);

        } else {
            confirmText.setText("No existen ordenes");
            confirmText.setTextFill(Color.RED);
        }

    }

    
    
    
    


    @FXML
    private void showOrders(){




        contentBox.getChildren().clear();
        QueueManager.resetBox();


        for (int i =0;i < OrderManager.getOrders().length; i++){

            QueueManager.addOrder(OrderManager.getOrders()[i]);


        }

        ordersInScreen();



     
      

    }

    @FXML
    private void infoText(){

        Alert infoAlert = new Alert(AlertType.INFORMATION);
        infoAlert.setTitle("Info ");
        infoAlert.setHeaderText("Info ");

       String info= "Las ordenes guardadas se almacenan en una cola. Se muestran los productos en la cola. \n" +
                    "\n"+ 
                    " Se utiliza el método poll para eliminar de la cola "
                   



                    ;
       infoAlert.setContentText(info);
       infoAlert.showAndWait();

   }

}
