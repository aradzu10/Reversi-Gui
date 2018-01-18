package Game;

public class ClickNotifier {

    private GameManager gameManager;

    public ClickNotifier() {
        gameManager = null;
    }

    public ClickNotifier(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void NotifyPoint(int x, int y) {
        gameManager.DoTurn(new Point(x, y));
    }

}
