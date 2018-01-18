package Game;/*
Name: Arad Zulti
ID: 315240564
*/

public abstract class Player {

	protected Checker player_color;

	public Player() {
	}

	public Player(Checker color) {
		player_color = color;
	}

	public Checker GetColor() {
		return player_color;
	}

	abstract Point GetPointFromPlayer();
}