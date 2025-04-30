package com.proyecto_final_ed.models;

import java.util.Arrays;

public class Order {

    private int id;
    private  int[] productsArray = new int[0];
    private int[] amountArray = new int[0];

    private int amountProducts; 
    private double totalPrice;

    public Order( ){

        this.id = OrderManager.getOrders().length;
        this.amountProducts = 0;
        this.totalPrice = 0;


    }

    public void setAmountProducts(int amount){
        this.amountProducts=amount;
    }

    public void setTotalPrice(Double totalPrice){
        this.totalPrice = totalPrice;
    }

    public int getId(){
        return id;
    }

    public int[] getProductsArray(){
        return productsArray;


    }

    public int[] getAmountArray(){
        return amountArray;
    }


    public int getAmountProducts(){
        return amountProducts;
    }

    public Double getTotalPrice(){
        return totalPrice;
    }

    public  void printProductsArray(){

        for (int i = 0; i < productsArray.length; i++) {
            System.out.println(" Id: " + productsArray[i] + " cantidad: " + amountArray[i]);
        }


    }

    public int [] addProductToArray( int productId, int amount ){

        int[] newArray = Arrays.copyOf(productsArray, productsArray.length +1);
        newArray[newArray.length -1] = productId;
        productsArray = newArray;
        int[] newArrayAmount = Arrays.copyOf(amountArray, amountArray.length +1);
        newArrayAmount[newArrayAmount.length -1] = amount;
        amountArray = newArrayAmount;

        return newArray;
    }

   








    
}
