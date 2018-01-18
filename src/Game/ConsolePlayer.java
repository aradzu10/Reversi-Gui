package Game;/*
Name: Arad Zulti
ID: 315240564
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsolePlayer extends Player {

	public ConsolePlayer(Checker color) {
		super(color);
	}

	public Point GetPointFromPlayer() {
		int row, col;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			s = br.readLine();
		} catch (Exception e) {
			s = "";
		}
		String[] input = s.split(" ");
		if (input.length != 2) {
			return new Point(-1, -1);
		}

		try {
			row = Integer.parseInt(input[0]);
			col = Integer.parseInt(input[1]);
		} catch (NumberFormatException e) {
			return new Point(-1, -1);
		}

		return new Point(row - 1, col - 1);
	}
}

