package zdobnikova;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zdobnikova.model.Game;


import java.io.IOException;

public class RenjuApp extends Application {

    private static RenjuApp renjuApp;

    private Game game;
    private Stage stage;

    public static RenjuApp getRenjuApp() {
        return renjuApp;
    }

    public Game getGame() {
        return game;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) {

        renjuApp = this;

        stage = mainStage;
        stage.setTitle("RENJU");
        stage.setResizable(false);

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainRen.fxml"));
            mainStage.setScene(new Scene(root));
        } catch (IOException exc) {
            System.exit(0);
            exc.printStackTrace();
        }
        mainStage.show();
    }

    public void play() {

        game = new Game();

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gameRen.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException exc) {
            System.exit(0);
        }
    }

    public void toMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainRen.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException exc) {
            System.exit(0);
        }
    }

    public void toDescription() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("descRen.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException exc) {
            System.exit(0);
        }
    }


}
