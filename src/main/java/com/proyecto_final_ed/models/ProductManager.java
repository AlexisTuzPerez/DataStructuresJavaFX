package com.proyecto_final_ed.models;

public class ProductManager {


    private static LinkedList<Product> products = new LinkedList<>();



    public static void addProduct(Product product){ 


        products.add(product);

    }

    public static LinkedList<Product>  getProducts(){

        return products;


    }


   

    public static boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.removeById(id);
                return true; 
            }
        }
        return false; 
    }


    public static boolean find(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {

                return true; 
            }
        }
        return false;
    }

    public static Product getProductsById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {

                return products.get(i); 
            }
        }
        return null;
    }
    
    
}
