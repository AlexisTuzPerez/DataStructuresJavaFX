package com.proyecto_final_ed.models;


import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class QueueManager {


    private static Queue <Order> queue = new LinkedList<>();
    private static VBox contentBox = new VBox();


    public static void addOrder(Order order){

        queue.add(order);

        addToContentBox(order);
    }

    public static void resetBox(){
        contentBox.getChildren().clear();
        queue.clear();
    }


    public static void addToContentBox(Order order){

        Label productLabel = new Label("");
        productLabel.setText( "Id: " + order.getId() + "/ " + "cantidad de productos: " + order.getAmountProducts() +" precio total: " + order.getTotalPrice());
        String idString = String.format("%s", order.getId());
        productLabel.setId(idString);
        productLabel.getStyleClass().add("scrollPaneChild");
        contentBox.getChildren().add(productLabel); 

    }



    public static Order popOrderFromQueue(){


        Order pollOrder = queue.poll();
        OrderManager.delete(pollOrder.getId());
        return pollOrder;

    }


    public static int getQueueSize(){
        return queue.size();
    }


    public static VBox getVBox(){
        return contentBox;
    }
















    
}
