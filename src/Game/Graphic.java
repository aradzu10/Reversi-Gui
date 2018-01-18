package Game;/*
Name: Arad Zulti
ID: 315240564
*/

public interface Graphic {

    void PrintBoard(Checker board[][]);

    void PrintTurnOf(Checker player);

    void PrintAvilable(Checker board[][], Checker avilable_to_print);

    void PrintMessage(String s);

    void PrintWinnerMessage(Checker winner);

    void PrintScore(int scorePlayer1, int scorePlayer2);
}