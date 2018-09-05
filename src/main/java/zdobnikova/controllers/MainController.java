package zdobnikova.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import zdobnikova.RenjuApp;

public class MainController {

    @FXML
    Button playButton;

    @FXML
    Button descriptionButton;

    public void initialize() {
        playButton.setOnMouseClicked(e -> {
            RenjuApp.getRenjuApp().play();
        });

        descriptionButton.setOnMouseClicked(event -> {
            RenjuApp.getRenjuApp().toDescription();
        });
    }


}
