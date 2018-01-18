package ReversiGui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {

    public static final int DEFAULT_SIZE = 50;
    public static double CURRENT_TILE_WIDTH = 50;
    public static double CURRENT_TILE_HEIGHT = 50;

    public Tile() {

        setWidth(CURRENT_TILE_WIDTH);
        setHeight(CURRENT_TILE_HEIGHT);

        Rectangle tileB = new Rectangle();
        tileB.setWidth(CURRENT_TILE_WIDTH);
        tileB.setHeight(CURRENT_TILE_HEIGHT);

        tileB.setFill(Color.WHITE);
        tileB.setStroke(Color.WHITE);

        Rectangle tile = new Rectangle();
        tile.setWidth(CURRENT_TILE_WIDTH - 5);
        tile.setHeight(CURRENT_TILE_HEIGHT - 5);

        tile.setFill(Color.valueOf("#ffb366"));
        tile.setStroke(Color.BLACK);

        getChildren().addAll(tileB, tile);

    }
}
