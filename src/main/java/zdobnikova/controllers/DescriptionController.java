package zdobnikova.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import zdobnikova.RenjuApp;

public class DescriptionController {
    @FXML
    Button menuButton;

    public void initialize(){
        menuButton.setOnMouseClicked(event -> {
            RenjuApp.getRenjuApp().toMenu();
        });
    }
}
