package com.proyecto_final_ed.models;

public class Product {


    private int id;
    private String name;
    private String autor;
    private String category;

    private Double price;


    public Product(int id){
        this.id = id;
        this.name= null;

        this.autor=null;
        this.category=null;
        this.price=0.0;

    }

    public Product (String name, String autor, String category , Double price){

        if (ProductManager.getProducts().isEmpty()) {
            this.id = 0;
        } else {

            int maxId = 0;
            for (int i =0 ; i<ProductManager.getProducts().size(); i++){
                Product product = ProductManager.getProducts().get(i);
                if (product.getId() > maxId) {
                    maxId = product.getId();
                }
            }
            this.id = maxId + 1; 
        }


        this.name = name;
        this.autor = autor;
        this.category = category;

        this.price = price;
    }
    





    public void setName(String name){
        this.name= name;
    }

    public void setAutor(String autor){
        this.autor=autor;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setPrice(Double price){
        this.price = price;
    }


    public void printAttributes(){

        System.out.println(name + "  " + autor + "  " + category +" "  + price );

    }


    public int getId(){
        return id;
    }


    public String getName(){
        return name;
    }


    public String getAutor(){
        return autor;
    }

    public String getCategory(){
        return category;
    }

    public Double getPrice(){
        return price;
    }

    public static void main(String[] args) {
        Product product = new Product("hola","h","go", 1.0);


        product.setName("adios");
        product.getName();
        System.out.println(product.getName());
    }

   




    
}
