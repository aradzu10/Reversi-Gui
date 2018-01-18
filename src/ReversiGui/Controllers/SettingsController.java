package ReversiGui.Controllers;

import ReversiGui.Piece;
import ReversiGui.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private Slider boardSize;
    @FXML
    private ComboBox player1Color;
    @FXML
    private ComboBox player2Color;
    @FXML
    private Pane screen;
    @FXML
    private Button exitButton;

    private StackPane P1;
    private StackPane P2;
    private static Settings settings;
    private static Map<String, Color> colorMap;
    private static Map<Color, String> colorNameMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colorMap = new HashMap<>();
        colorMap.put("White", Color.WHITE);
        colorMap.put("Black", Color.BLACK);
        colorMap.put("Red", Color.RED);
        colorMap.put("Blue", Color.BLUE);
        colorMap.put("Green", Color.GREEN);
        colorMap.put("Yellow", Color.YELLOW);

        colorNameMap = new HashMap<>();
        colorNameMap.put(Color.WHITE, "White");
        colorNameMap.put(Color.BLACK, "Black");
        colorNameMap.put(Color.RED, "Red");
        colorNameMap.put(Color.BLUE, "Blue");
        colorNameMap.put(Color.GREEN, "Green");
        colorNameMap.put(Color.YELLOW, "Yellow");

        player1Color.getItems().clear();
        player2Color.getItems().clear();
        player1Color.getItems().addAll("White", "Black", "Red", "Blue", "Green", "Yellow");
        player2Color.getItems().addAll("White", "Black", "Red", "Blue", "Green", "Yellow");

        boardSize.setValue(settings.getSize());
        player1Color.getSelectionModel().select(colorNameMap.get(settings.getFirstPlayer()));
        player2Color.getSelectionModel().select(colorNameMap.get(settings.getSecondPlayer()));

        P1 = new StackPane();
        P2 = new StackPane();

        P1.getChildren().add(new Piece(settings.getFirstPlayer(), settings.getSecondPlayer(), Color.BLACK));
        P2.getChildren().add(new Piece(settings.getSecondPlayer(), settings.getFirstPlayer(), Color.BLACK));

        P1.relocate(55, 130);
        P2.relocate(190, 130);
        screen.getChildren().addAll(P1, P2);
    }

    public void saveSettings(ActionEvent event) {
        settings.setSize((int) boardSize.getValue());
        settings.setFirstPlayer(colorMap.get(player1Color.getSelectionModel().getSelectedItem().toString()));
        settings.setSecondPlayer(colorMap.get(player2Color.getSelectionModel().getSelectedItem().toString()));
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void exitWindow(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) exitButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void ChangePiece(ActionEvent event) {
        P1.getChildren().clear();
        P1.getChildren().add(new Piece(colorMap.get(player1Color.getSelectionModel().getSelectedItem().toString()),
                colorMap.get(player2Color.getSelectionModel().getSelectedItem().toString()), Color.BLACK));
        P2.getChildren().clear();
        P2.getChildren().add(new Piece(colorMap.get(player2Color.getSelectionModel().getSelectedItem().toString()),
                colorMap.get(player1Color.getSelectionModel().getSelectedItem().toString()), Color.BLACK));
    }

    public void display(Settings currentSettings) {
        settings = currentSettings;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../Fxml/Settings.fxml"));
        } catch (Exception e) {
            return;
        }
        Stage settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.setResizable(false);
        settingsStage.setTitle("Settings");
        settingsStage.setScene(new Scene(root, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE));

        settingsStage.showAndWait();
    }

    public static void StartSettings(Settings currentSettings) {
        SettingsController settingsController = new SettingsController();
        settingsController.display(currentSettings);
    }
}
