package zdobnikova.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import zdobnikova.RenjuApp;
import zdobnikova.model.Game;
import zdobnikova.model.Point;
import zdobnikova.model.Stone;

import java.util.Optional;

public class GameController {


    @FXML
    Button menuButton;

    @FXML
    GridPane mainGrid;

    @FXML
    Text status;

    @FXML
    AnchorPane background;

    private final int stoneRadius = 15;

    private Game game = RenjuApp.getRenjuApp().getGame();


    public void initialize() {
        drawBoard();
        setButtons();
        updateStatus();
        menuButton.setOnMouseClicked(event -> RenjuApp.getRenjuApp().toMenu());
    }

    private void setButtons() {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                Circle buttonCircle = new Circle(stoneRadius);
                buttonCircle.setFill(Color.rgb(0, 0, 0, 0.0));
                Point point = new Point(x, y);
                mainGrid.add(buttonCircle, x, y);
                buttonCircle.setOnMouseEntered(event ->
                        buttonCircle.setFill(Color.rgb(173, 175, 53, 0.5)));
                buttonCircle.setOnMouseExited(event ->
                        buttonCircle.setFill(Color.rgb(0, 0, 0, 0.0)));
                buttonCircle.setOnMouseClicked(event ->
                        makeMove(point));
            }
        }
    }

    private void drawBoard() {
        for (int i = 0; i < game.getWidth(); i++) {
            Line line = new Line(stoneRadius + i * 2 * stoneRadius, stoneRadius,
                    stoneRadius + i * 2 * stoneRadius, game.getHeight() * (2 * stoneRadius - 1));
            line.setFill(Color.BLACK);
            background.getChildren().add(line);
        }
        for (int i = 0; i < game.getHeight(); i++) {
            Line line = new Line(stoneRadius, stoneRadius + i * 2 * stoneRadius,
                    game.getHeight() * (2 * stoneRadius - 1), stoneRadius + i * 2 * stoneRadius);
            line.setFill(Color.BLACK);
            background.getChildren().add(line);
        }
    }

    private void makeMove(Point point) {
        if (game.getCurrentPlayer() == Stone.BLACK) {
            if (game.foulMax(point)) foulMaxAlert();
            else if (game.fork(point)) foulForkAlert();
            if (game.makeMove(point)) {
                mainGrid.add(drawImage(), point.getX(), point.getY());
                updateStatus();
                findWinner();
            }
        } else if (game.makeMove(point)) {
            mainGrid.add(drawImage(), point.getX(), point.getY());
            updateStatus();
            findWinner();
        }

    }

    private void foulForkAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You are playing black, you can't build fork like this!");
        alert.setHeaderText("FOUL!");
        alert.showAndWait();
    }

    private void foulMaxAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You are playing black, you can't build long rows!");
        alert.setHeaderText("FOUL!");
        alert.showAndWait();
    }


    private Circle drawStone() {
        Circle circle = new Circle(stoneRadius);
        if (game.getCurrentPlayer() == Stone.BLACK) {
            circle.setFill(Color.BLACK);
        } else circle.setFill(Color.LIGHTBLUE);
        return circle;
    }

    private ImageView drawImage() {
        if (game.getCurrentPlayer() == Stone.WHITE) {
            return new ImageView(new Image("pics/black.png", stoneRadius * 2,
                    stoneRadius * 2, false, true));
        } else return new ImageView(new Image("pics/white.png", stoneRadius * 2,
                stoneRadius * 2, false, true));
    }

    private void updateStatus() {
        if (game.getCurrentPlayer() == Stone.BLACK) {
            status.setText("BLACK'S TURN");
        } else status.setText("WHITE'S TURN");
    }

    private void findWinner() {
        if (game.getWinner() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to play again?");
            if (game.getWinner() == Stone.BLACK) {
                alert.setHeaderText("BLACK WINS");
            } else alert.setHeaderText("WHITE WINS");
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                RenjuApp.getRenjuApp().play();
            } else RenjuApp.getRenjuApp().toMenu();
        }
    }
}
