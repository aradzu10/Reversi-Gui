package Game;

public class Reversi {

	private GameManager game;
	private BoardManager board;

	public Reversi(int size) {
		board = new BoardManager(size);
		game = new GameManager(board);
		ChangeSettings();
	}

	private void ChangeSettings() {
	}

	public GameManager GetGameManager() {
		return game;
	}

	public void SetPrinter(Printer printer) {
		game.SetPrinter(printer);
	}

	public void SetPlayers(Player player1, Player player2) {
		game.SetPlayers(player1, player2);
	}

	public void Start() {
		game.StartGame();
	}
}