package ReversiGui.Controllers;

import Game.*;
import ReversiGui.GuiPrinter;
import ReversiGui.SettingParser;
import ReversiGui.Settings;
import ReversiGui.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private VBox window;
    @FXML
    private MenuBar myMenuBar;
    @FXML
    private SplitPane screen;
    @FXML
    private GridPane boardPane;
    @FXML
    private GridPane messagesPane;

    private Settings settings;
    private Settings updatedSettings;
    private ClickNotifier clickNotifier;
    private GuiPrinter guiPrinter;

    @FXML
    public void initialize() {
        settings = SettingParser.GetSettingsFromFile();

        updatedSettings = settings;

        messagesPane.getColumnConstraints().clear();
        messagesPane.getRowConstraints().clear();
        for (int i = 0; i < 3; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / 3);
            messagesPane.getColumnConstraints().add(colConst);

            RowConstraints rowConst = new RowConstraints();
            messagesPane.getRowConstraints().add(rowConst);
        }

        messagesPane.setMinSize(Tile.DEFAULT_SIZE * 4, Tile.DEFAULT_SIZE * 4);
        messagesPane.setMaxSize(Tile.DEFAULT_SIZE * 4, Tile.DEFAULT_SIZE * 4);

        clickNotifier = new ClickNotifier();
        guiPrinter = new GuiPrinter(boardPane, messagesPane,
                settings.getFirstPlayer(), settings.getSecondPlayer(), clickNotifier);

        window.widthProperty().addListener((observable, oldValue, newValue) -> {
            screen.setDividerPosition(0, 0.6);
            double tileNewSize = newValue.doubleValue() * 0.6 / settings.getSize();
            Tile.CURRENT_TILE_WIDTH = tileNewSize;
            guiPrinter.PrintBoard();
        });

        window.heightProperty().addListener((observable, oldValue, newValue) -> {
            double tileNewSize = (newValue.doubleValue() - myMenuBar.getHeight()) / settings.getSize();
            Tile.CURRENT_TILE_HEIGHT = tileNewSize;

            guiPrinter.PrintBoard();
        });
    }

    public void startGame(ActionEvent event) {
        if (settings.getSize() != updatedSettings.getSize()) {
            Tile.CURRENT_TILE_WIDTH = Tile.DEFAULT_SIZE;
            Tile.CURRENT_TILE_HEIGHT = Tile.DEFAULT_SIZE;
        }
        settings = updatedSettings;

        Reversi reversi = new Reversi(settings.getSize());
        guiPrinter.setPlayersColors(settings.getFirstPlayer(), settings.getSecondPlayer());

        int size = settings.getSize();
        boardPane.getColumnConstraints().clear();
        boardPane.getRowConstraints().clear();
        for (int i = 0; i < size; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / size);
            boardPane.getColumnConstraints().add(colConst);

            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / size);
            boardPane.getRowConstraints().add(rowConst);
        }

        clickNotifier.setGameManager(reversi.GetGameManager());

        if (settings.getSize() == 4) {
            ((Stage) window.getScene().getWindow()).setMinWidth(settings.getSize() * Tile.DEFAULT_SIZE * 2
                    + Tile.DEFAULT_SIZE * 4);
            ((Stage) window.getScene().getWindow()).setMinHeight(settings.getSize() * Tile.DEFAULT_SIZE
                    + myMenuBar.getHeight() * 2);
            ((Stage) window.getScene().getWindow()).setWidth(settings.getSize() * Tile.DEFAULT_SIZE * 2
                    + Tile.DEFAULT_SIZE * 4);
            ((Stage) window.getScene().getWindow()).setHeight(settings.getSize() * Tile.DEFAULT_SIZE
                    + myMenuBar.getHeight() * 2);
        } else {
            ((Stage) window.getScene().getWindow()).setMinWidth(settings.getSize() * Tile.DEFAULT_SIZE
                    + Tile.DEFAULT_SIZE * 4);
            ((Stage) window.getScene().getWindow()).setMinHeight(settings.getSize() * Tile.DEFAULT_SIZE
                    + myMenuBar.getHeight());
            ((Stage) window.getScene().getWindow()).setWidth(settings.getSize() * Tile.DEFAULT_SIZE
                    + Tile.DEFAULT_SIZE * 4);
            ((Stage) window.getScene().getWindow()).setHeight(settings.getSize() * Tile.DEFAULT_SIZE
                    + myMenuBar.getHeight());
        }


        reversi.SetPrinter(guiPrinter);
        reversi.Start();
    }

    public void openSettings(ActionEvent event) {
        updatedSettings = SettingParser.GetSettingsFromFile();
        SettingsController.StartSettings(updatedSettings);
        SettingParser.WriteSettingsToFile(updatedSettings);
    }

    public void exitWindow(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void display(Stage primaryStage) {
        VBox root;
        try {
            root = FXMLLoader.load(getClass().getResource("Fxml/Menu.fxml"));
        } catch (Exception e) {
            return;
        }

        primaryStage.setTitle("Reversi");
        primaryStage.setScene(new Scene(root, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE));

        primaryStage.show();
    }

    public static void StartMenu(Stage primaryStage) {
        MenuController menuController = new MenuController();
        menuController.display(primaryStage);
    }
}
