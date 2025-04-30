module com.proyecto_final_ed {
    requires javafx.controls;
    requires javafx.fxml;

 
    //arboles
    requires gs.core;
    requires javafx.swing;

    

    requires gs.ui.javafx;


   





    requires transitive javafx.graphics; // para el problema del stage




   



    opens com.proyecto_final_ed to javafx.fxml;
    exports com.proyecto_final_ed;

    opens com.proyecto_final_ed.controllers to javafx.fxml;
    exports com.proyecto_final_ed.controllers;


    opens com.proyecto_final_ed.models to javafx.fxml;
    exports com.proyecto_final_ed.models;

    
}
