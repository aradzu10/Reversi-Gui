package Game;/*
Name: Arad Zulti
ID: 315240564
*/

public class Reversi {

	private GameManager game;
	private BoardManager board;

	public Reversi(int size) {
		ChangeSettings();
		board = new BoardManager(size);
		game = new GameManager(size, board);
	}

	private void ChangeSettings() {
	}

	public GameManager GetGameManager() {
		return game;
	}

	public void SetPrinter(Graphic printer) {
		game.SetPrinter(printer);
	}

	public void SetPlayers(Player player1, Player player2) {
		game.SetPlayers(player1, player2);
	}

	public void Start() {
		game.StartGame();
	}
}