package com.proyecto_final_ed.models;

public class QuickSort {
    
    private static void swap(Product[] arr, int i, int j) {
        Product aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }

    private static int partition(Product[] arr, int low, int high) {
        double pivot = arr[high].getPrice();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j].getPrice() <= pivot) { 
                i++;
                swap(arr, i, j);
            }
        } 
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void quickSort(Product[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);       
            quickSort(arr, pi + 1, high);  
        }
    }


    public static void main(String[] args) {

        LinkedList<Product> products = ProductManager.getProducts();
        Product[] productsArray = new Product[products.size()];
        products.toArray(productsArray);

        System.out.println("Productos antes de ordenar:");
        for (Product product : productsArray) {
            System.out.println("Producto: " + product.getName() + ", Precio: " + product.getPrice());
        }

        quickSort(productsArray, 0, productsArray.length - 1);

        System.out.println("\nProductos después de ordenar:");
        for (Product product : productsArray) {
            System.out.println("Producto: " + product.getName() + ", Precio: " + product.getPrice());
        }
    }
}
