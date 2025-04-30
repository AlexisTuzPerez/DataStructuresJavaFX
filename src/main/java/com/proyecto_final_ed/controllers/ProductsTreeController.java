package com.proyecto_final_ed.controllers;

import java.util.ArrayList;
import java.util.List;

import com.proyecto_final_ed.SceneManager;
import com.proyecto_final_ed.models.Product;
import com.proyecto_final_ed.models.ProductManager;
import com.proyecto_final_ed.models.Tree;
import com.proyecto_final_ed.models.VisualizeTree;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ProductsTreeController {


    @FXML
    private TextField idInput;

    @FXML
    private VBox treeBox;

    @FXML 
    private TextField addNodeInput;

    @FXML 
    private TextField searchProductInput;

    @FXML 
    private Label confirmText;


    @FXML
    private ArrayList<Integer> addedNodes = new ArrayList<>();

    private String idString;

    private int id;

    @FXML 
    private void backMenu(){

        for (int i=0; i < addedNodes.size(); i++) {
            Tree.deleteId(addedNodes.get(i));

            ProductManager.deleteProduct(addedNodes.get(i));
        }

        showTree();
        confirmText.setText(" ");
        searchProductInput.setText("buscar_producto");

        addNodeInput.setText("add_Node");

        idInput.setText("id_producto_a_eliminar");
        SceneManager.showScene("primary");
    }

   


    @FXML
    private void deleteProduct(){


        idString=idInput.getText();

        if (verifyInfo(idString)== true){
            id = Integer.parseInt(idString);
            if (Tree.find(id) == -1 ){

                confirmText.setText("Producto con ID:  " + id + " no existe");
                confirmText.setTextFill(Color.RED);
    
            } else {

                ProductManager.deleteProduct(id);
                Tree.deleteId(id);


                showTree();

                confirmText.setText("Nodo eliminado correctamente");
                confirmText.setTextFill(Color.GREEN);

            }



        } else {

            confirmText.setText("Ingrese un valor entero");
            confirmText.setTextFill(Color.RED);
            
        }


    }


    private boolean verifyInfo(String idString ){

        try{
            Integer.parseInt(idString);

            return true;
        }catch (NumberFormatException e){
            return false;
        
        } 
    }


    @FXML
    private void showTree() {


        treeBox.getChildren().clear();


        VisualizeTree visualizer = new VisualizeTree();
        visualizer.buildFromInOrder(Tree.inOrderList());

        visualizer.getView().prefWidthProperty().bind(treeBox.widthProperty());
        visualizer.getView().prefHeightProperty().bind(treeBox.heightProperty());


        treeBox.getChildren().add(visualizer.getView());
}
  


    private Boolean verifyInt(String value){

        try{
            Integer.parseInt(value);

            return true;
        }catch (NumberFormatException e){
            return false;
        
        } 
    }


    @FXML 
    private void addNode(){

        

        String addNode = addNodeInput.getText();

        if (verifyInt(addNode)== true){


            int id = Integer.parseInt(addNode);

            if (Tree.find(id)== -1){

                Tree.insert(id);

                Product product = new Product(id);

                ProductManager.addProduct(product);

                showTree();

                addedNodes.add(id);

                confirmText.setText("Nodo agregado correctamente");
                confirmText.setTextFill(Color.GREEN);





            }else{


                confirmText.setText("El nodo con valor: " + id + " ya existe en el arbol");
                confirmText.setTextFill(Color.RED);

      

            }

   


        }else{
            confirmText.setText("El valor del nodo no es entero ");
            confirmText.setTextFill(Color.RED);
        }






    }


    @FXML
    private void searchProduct(){


        String searchProduct = searchProductInput.getText();

        


        if (verifyInt(searchProduct)== true){

            int id = Integer.parseInt(searchProduct);

            if(Tree.find(id)==-1){

                confirmText.setText("El nodo con id: " + id + " no existe en el arbol");
                confirmText.setTextFill(Color.RED);

            }else{


                Product product = ProductManager.getProducts().getById(id);


                confirmText.setText("Producto ID: " + product.getId() +  " Nombre: " + product.getName() + " precio: " + product.getPrice());
                confirmText.setTextFill(Color.GREEN);

            }








        }else{
            confirmText.setText("Ingrese un valor entero");
            confirmText.setTextFill(Color.RED);
        }


    }


    @FXML 
    private void inOrder(){



        String text="Recorrido IN ORDEN: " + Tree.inOrderList();



        confirmText.setTextFill(Color.LIGHTBLUE);


        confirmText.setText(text);




    }
    @FXML
    private void postOrder() {


        String text="Recorrido POST ORDEN: " + Tree.postOrderList();





        confirmText.setTextFill(Color.LIGHTBLUE);

        confirmText.setText(text);



    }
    
  

    @FXML
    private void preOrder() {


        List<Integer> preOrder = Tree.preOrderList();

        String text="Recorrido PRE ORDEN: " + preOrder;




    
        confirmText.setTextFill(Color.LIGHTBLUE);


        confirmText.setText(text);


   
    }



    @FXML
    private void infoText(){

        Alert infoAlert = new Alert(AlertType.INFORMATION);
       infoAlert.setTitle("Info ");
       infoAlert.setHeaderText("Info ");

       String info= "Se crear un arbol con los productos almacenados previamente (lo mismo que agregó para agregar pedidos   \n" +
                    "\n"+ 
                    "De igual manera de pueden ingresar, eliminar y buscar nodos, asegurarse que los valores sean enteros \n"

                    ;
       infoAlert.setContentText(info);
       infoAlert.showAndWait();

   }
    
    



    
}
