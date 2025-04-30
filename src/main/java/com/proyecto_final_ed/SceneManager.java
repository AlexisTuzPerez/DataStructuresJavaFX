package com.proyecto_final_ed;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SceneManager {   // primero los atributos

    private static Stage primaryStage;  // any instance created access to the same variable
    private static HashMap<String, Scene> scenes = new HashMap<>(); //samee

    public SceneManager (Stage primaryStage){        // contructor
        SceneManager.primaryStage = primaryStage;
    }
    public void loadScene(String name, String fxmlFile, String cssFile ) throws IOException { 

        FXMLLoader fxmlScene = new FXMLLoader(App.class.getResource(fxmlFile));  // cargar el fxml
        Parent root = fxmlScene.load();  // cambiar

        Scene scene = new Scene(root, 1200, 600);  // crear la scena
        primaryStage.setTitle(name); // poner el nombre
        primaryStage.setScene(scene);   //cambiar el scene

        if (cssFile != null && cssFile.isEmpty() == false){
            scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm()); // obtener el css
        } else {
            System.out.println("css can not be found");
        }
        scenes.put(name,scene);

    }

    public static void showScene(String name){  //this means i can access the method anywhere without creating instances
        Scene scene = scenes.get(name);
        if (scene != null){
            primaryStage.setScene(scene);
            primaryStage.show();

        } else {
            System.out.println(name +" scene does not exist ");
        }
     
    }
















    
}
