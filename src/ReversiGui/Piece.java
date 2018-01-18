package ReversiGui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static ReversiGui.Tile.CURRENT_TILE_WIDTH;
import static ReversiGui.Tile.CURRENT_TILE_HEIGHT;


public class Piece extends StackPane {

    public Piece(Color top, Color bottom, Color stroke) {

        double radius = Math.min(CURRENT_TILE_WIDTH, CURRENT_TILE_HEIGHT);
        Ellipse bg = new Ellipse(radius * 0.3125, radius * 0.26);
        bg.setFill(bottom);

        bg.setStroke(stroke);
        bg.setStrokeWidth(radius * 0.03);

        bg.setTranslateY(radius * 0.07);

        Ellipse ellipse = new Ellipse(radius * 0.3125, radius * 0.26);
        ellipse.setFill(top);

        ellipse.setStroke(stroke);
        ellipse.setStrokeWidth(radius * 0.03);


        getChildren().addAll(bg, ellipse);
    }

}
