package ReversiGui;
import Game.Checker;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;

import static Game.Checker.Black;
import static Game.Checker.White;

public class Settings {

    private Color firstPlayer;
    private Color secondPlayer;
    private int size;

    public Settings() {
        firstPlayer = Color.WHITE;
        secondPlayer = Color.BLACK;
        size = 8; // standard board
    }

    public int getSize(){
        return size;
    }

    public Color getFirstPlayer() {
        return firstPlayer;
    }

    public Color getSecondPlayer() {
        return secondPlayer;
    }

    public void setFirstPlayer(Color firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public void setSecondPlayer(Color secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void setSize(int size) {
        this.size = size;
    }


    /*public void display() {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
            Stage settingsStage = new Stage();
            settingsStage.initModality(Modality.APPLICATION_MODAL);
            settingsStage.setTitle("Settings");
            settingsStage.setScene(new Scene(root, 400, 250));
            settingsStage.showAndWait();
        } catch (Exception exceprion) {

        }


        Stage settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.setMinWidth(250);

        Label label = new Label();
        label.setText("Choose Starting Player");
        Button toggleButton = new Button();
        Button closeButton = new Button("close");
        closeButton.setOnAction(event -> settingsStage.close());
        toggleButton.setOnAction(event -> this.switchPlayer());

        VBox layout = new VBox();
        layout.getChildren().addAll(closeButton, toggleButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        settingsStage.setScene(scene);
        settingsStage.showAndWait();
    }*/
}
