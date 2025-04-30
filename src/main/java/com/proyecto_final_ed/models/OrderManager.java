package com.proyecto_final_ed.models;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class OrderManager {



        private static Order[] orders = new Order[0]; 
        Queue<Order> queue = new LinkedList<Order>();

        public static void addOrder(Order order){ 

            Order[] newArray = Arrays.copyOf(orders, orders.length +1);
            newArray[newArray.length -1] = order;
            orders = newArray;
    
        }

        public static Order[] getOrders(){
            return orders;

        }

    
        public static void printOrders(){

            System.out.println("Total de ordenes: " +orders.length);
            for( int i = 0; i < orders.length; i++){

                System.out.println("Id de la orden: " + orders[i].getId());
                System.out.println( "Precio total: " +orders[i].getTotalPrice() +" Cantidad "+ orders[i].getAmountProducts() );
 

            }   
        } 



        public static boolean find(int id) {
            for (Order order : orders) {
                if (order.getId() == id) {
                    return true; 
                }
            }
            return false;
        }


        public static Order binarySearch(int id) {

            Arrays.sort(orders, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        
            int left = 0;
            int right = orders.length - 1;
        
            while (left <= right) {
                int mid = left + (right - left) / 2; 
                Order midOrder = orders[mid];
        
                if (midOrder.getId() == id) {
                    return midOrder; 
                } else if (midOrder.getId() < id) {
                    left = mid + 1; 
                } else {
                    right = mid - 1; 
                }
            }
        
            return null; 
        }
        
        public static boolean delete(int id) {
            boolean found = false;
            Order[] updatedOrders = new Order[orders.length - 1];
            int index = 0;
    
            for (Order order : orders) {
                if (order.getId() == id) {
                    found = true; 
                } else {
                    if (index < updatedOrders.length) {
                        updatedOrders[index++] = order;
                    }
                }
            }
    
            if (found) {
                orders = updatedOrders;
            }
    
            return found;
        }




    



}
