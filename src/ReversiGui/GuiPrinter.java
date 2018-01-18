package ReversiGui;

import Game.Checker;
import Game.ClickNotifier;
import Game.Printer;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static ReversiGui.Tile.DEFAULT_SIZE;


public class GuiPrinter implements Printer {

    private GridPane boardPane;
    private GridPane messagesPane;
    private Color firstPlayer;
    private Color secondPlayer;
    private ClickNotifier clickNotifier;
    private Checker[][] lastBoardPlayed;

    public GuiPrinter(GridPane boardPane, GridPane messagesPane, Color firstPlayer,
                      Color secondPlayer, ClickNotifier clickNotifier) {
        this.boardPane = boardPane;
        this.messagesPane = messagesPane;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.clickNotifier = clickNotifier;
        this.lastBoardPlayed = null;
    }

    @Override
    public void PrintBoard(Checker[][] board) {
        int size = board.length;

        lastBoardPlayed = board;
        boardPane.getChildren().clear();

        boardPane.setMinSize(Tile.CURRENT_TILE_WIDTH * size, Tile.CURRENT_TILE_HEIGHT * size);
        boardPane.setMaxSize(Tile.CURRENT_TILE_WIDTH * size, Tile.CURRENT_TILE_HEIGHT * size);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Piece piece = new Piece(Color.TRANSPARENT, Color.TRANSPARENT,
                        Color.TRANSPARENT);
                Tile tile = new Tile();
                final int row = x;
                final int col = y;

                if (board[x][y] == Checker.AvailableB || board[x][y] == Checker.AvailableW) {
                    tile.setOnMouseClicked(e -> {
                        clickNotifier.NotifyPoint(row, col);
                        e.consume();
                    });

                    piece = new Piece(Color.TRANSPARENT, Color.TRANSPARENT,
                            board[x][y] == Checker.AvailableW ? firstPlayer : secondPlayer);
                    piece.setOnMouseClicked(e -> {
                        clickNotifier.NotifyPoint(row, col);
                        e.consume();
                    });
                }
                boardPane.add(tile, y, x);
                boardPane.add(piece, y, x);

                if (board[x][y] == Checker.White || board[x][y] == Checker.Black) {
                    piece = new Piece(board[x][y] == Checker.White ? firstPlayer : secondPlayer,
                            board[x][y] == Checker.White ? secondPlayer : firstPlayer, Color.BLACK);
                    boardPane.add(piece, y, x);
                }
            }
        }
    }

    public void PrintBoard() {
        if (lastBoardPlayed == null) {
            return;
        }
        PrintBoard(lastBoardPlayed);
    }

    @Override
    public void PrintTurnOf(Checker player) {

        messagesPane.getChildren().clear();

        Piece pieceFirstPlayer = new Piece(firstPlayer, secondPlayer, Color.BLACK);
        Piece pieceSecondPlayer = new Piece(secondPlayer, firstPlayer, Color.BLACK);

        StackPane stackPaneTurnOf = new StackPane();
        Rectangle turnOf = new Rectangle(Tile.DEFAULT_SIZE, 5);
        turnOf.setFill(player == Checker.Nothing ? Color.TRANSPARENT : Color.RED);
        turnOf.setTranslateY(DEFAULT_SIZE * 0.5);
        stackPaneTurnOf.getChildren().add(turnOf);

        StackPane stackPaneKeepSpace = new StackPane();
        Rectangle keepSpace = new Rectangle(Tile.DEFAULT_SIZE, 5);
        keepSpace.setFill(Color.TRANSPARENT);
        keepSpace.setTranslateY(DEFAULT_SIZE * 0.5);
        stackPaneKeepSpace.getChildren().add(keepSpace);


        messagesPane.add(pieceFirstPlayer, 1, 0);
        messagesPane.add(pieceSecondPlayer, 2, 0);
        if (player == Checker.White) {
            messagesPane.add(stackPaneTurnOf, 1, 0);
            messagesPane.add(stackPaneKeepSpace, 2, 0);
        } else {
            messagesPane.add(stackPaneTurnOf, 2, 0);
            messagesPane.add(stackPaneKeepSpace, 1, 0);
        }
    }

    @Override
    public void PrintMessage(String s) {

        StackPane stackPaneMessage = new StackPane();
        Label message = new Label(s);
        message.setTranslateY(DEFAULT_SIZE * 0.75);
        stackPaneMessage.getChildren().add(message);
        messagesPane.add(stackPaneMessage, 1, 2);
    }

    @Override
    public void PrintScore(int scorePlayer1, int scorePlayer2) {
        StackPane stackPaneScore = new StackPane();
        Label labelScore = new Label("Score:");
        labelScore.setTranslateY(DEFAULT_SIZE * 0.5);
        stackPaneScore.getChildren().add(labelScore);

        StackPane stackPaneP1 = new StackPane();
        Label labelP1 = new Label(String.valueOf(scorePlayer1));
        labelP1.setTranslateY(DEFAULT_SIZE * 0.5);
        stackPaneP1.getChildren().add(labelP1);

        StackPane stackPaneP2 = new StackPane();
        Label labelP2 = new Label(String.valueOf(scorePlayer2));
        labelP2.setTranslateY(DEFAULT_SIZE * 0.5);
        stackPaneP2.getChildren().add(labelP2);

        messagesPane.add(stackPaneScore, 0, 1);
        messagesPane.add(stackPaneP1, 1, 1);
        messagesPane.add(stackPaneP2, 2, 1);
    }

    public void setPlayersColors(Color firstPlayer, Color secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    @Override
    public void PrintWinnerMessage(Checker winner) {
        StackPane stackPaneWinnerP = new StackPane();
        Piece winnerP = new Piece(winner == Checker.White ? firstPlayer : secondPlayer,
                winner == Checker.White ? secondPlayer : firstPlayer, Color.BLACK);
        winnerP.setTranslateY(DEFAULT_SIZE * 0.75);
        stackPaneWinnerP.getChildren().add(winnerP);


        StackPane stackPaneWinnerL = new StackPane();
        Label winnerL = new Label("IS THE WINNER!");
        winnerL.setTranslateY(DEFAULT_SIZE * 0.75);
        stackPaneWinnerL.getChildren().add(winnerL);

        messagesPane.add(stackPaneWinnerP, 0, 2);
        messagesPane.add(stackPaneWinnerL, 1, 2);
        messagesPane.setColumnSpan(stackPaneWinnerL, 2);


    }

    @Override
    public void PrintAvilable(Checker[][] board, Checker avilable_to_print) {

    }
}
