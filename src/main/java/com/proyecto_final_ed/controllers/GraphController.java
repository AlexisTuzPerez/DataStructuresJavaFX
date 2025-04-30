package com.proyecto_final_ed.controllers;

import java.util.Map;

import com.proyecto_final_ed.SceneManager;
import com.proyecto_final_ed.models.Graph;
import com.proyecto_final_ed.models.Kruskal;
import com.proyecto_final_ed.models.VisualizeGraph;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GraphController {


    @FXML
    private TextField node1Input;

    @FXML
    private TextField node2Input;


    @FXML 
    private TextField valueInput;

    @FXML
    private VBox graphBox;


    @FXML
    private Label confirmText;


    @FXML
    private TextField deleteNodeInput;



    private String node1;

    private String node2;
    private String valueString;

    private int value;



    @FXML
    private void deleteNode(){

        String stringId = deleteNodeInput.getText();

        if (Graph.find(stringId)== true){
            Graph.deleteNode(stringId);
            showGraph();
            confirmText.setText("Nodo eliminado correctamente");
            confirmText.setTextFill(Color.GREEN);
        }else{


            confirmText.setText("No existe el nodo");
            confirmText.setTextFill(Color.RED);

        }

    }

    @FXML
    private void backMenu(){
        SceneManager.showScene("primary");
    }

    @FXML
    private void addEdge(){


        node1= node1Input.getText();

        node2= node2Input.getText();

        valueString= valueInput.getText();




        if (verifyInfo(valueString) == true){

            value = Integer.parseInt(valueString);

            Graph.addEdge(node1, node2, value);

            showGraph();

            confirmText.setText("Nodo agregado correctamente");
            confirmText.setTextFill(Color.GREEN);

        }else{

            confirmText.setText("Ingrese un valor entero");
            confirmText.setTextFill(Color.RED);

        }

    }

    @FXML
    private void showKruskal() {


        graphBox.getChildren().clear();
        VisualizeGraph visualizer = new VisualizeGraph();
        Map<String, Map<String, Integer>> graph = Graph.getGraph();
        Kruskal kruskal = new Kruskal(1000);
        Map<String, Map<String, Integer>> mst = kruskal.computeMST(graph);
        confirmText.setText(" " + kruskal.getWeight() );
        confirmText.setTextFill(Color.LIGHTBLUE);
        visualizer.buildFromGraph(mst);
        Pane graphView = visualizer.obtainView();
        graphView.prefWidthProperty().bind(graphBox.widthProperty());
        graphView.prefHeightProperty().bind(graphBox.heightProperty());
        graphBox.getChildren().add(graphView);
    }
    

    


    private boolean verifyInfo(String valueString){

        try{
            Integer.parseInt(valueString);

            return true;
        }catch (NumberFormatException e){
            return false;
        
        } 

    }

    @FXML
    private void showGraphButton(){
        showGraph();
    }

    private void showGraph(){

        graphBox.getChildren().clear();
        VisualizeGraph visualizer = new VisualizeGraph();
        visualizer.buildFromGraph(Graph.getGraph());
        Pane graphView = visualizer.obtainView();
        graphView.prefWidthProperty().bind(graphBox.widthProperty());
        graphView.prefHeightProperty().bind(graphBox.heightProperty());
        graphBox.getChildren().add(graphView);


    }

    @FXML
    private void infoText(){

        Alert infoAlert = new Alert(AlertType.INFORMATION);
        infoAlert.setTitle("Info ");
        infoAlert.setHeaderText("Info ");

       String info= "Se necesita enviar los pedidos, se puede diseñar un grafo con las distancias a los puntos de entrega \n" +
                    "\n"+ 
                    "Se utiliza el algoritmo de Kruskal para obtener el camino más cercano."+ 
                    "\n"+ 
                    "El valor de los nodos es un string, el valor de las aristas debe ser entero  "



                    ;
       infoAlert.setContentText(info);
       infoAlert.showAndWait();

   }

    
}
