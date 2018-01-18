package Game;
/*
Name: Arad Zulti
ID: 315240564
*/

public class GameManager {

    private BoardManager board;
    private Player white;
    private Player black;
    private Player currentP;
    private Graphic printer;

    public GameManager(int size_, BoardManager board_) {
        board = board_;
        printer = new ConsolePrinter();
        white = new ConsolePlayer(Checker.White);
        black = new ConsolePlayer(Checker.Black);
        currentP = white;
    }

    public void SetPrinter(Graphic printer_) {
        printer = printer_;
    }

    public void SetPlayers(Player white_, Player black_) {
        white = white_;
        currentP = white_;
        black = black_;
    }

    public void StartGame() {
        //        Player currentPlayer = white;
//        Player nextPlayer = black;
//        Player tmpP;
//
//        while (true) {
//            board.FindAllAvailable(currentPlayer.GetColor());
//            printer.PrintBoard(board.GetBoard());
//            printer.PrintTurnOf(currentPlayer.GetColor());
//            if (!board.CheckPlayerAvailable(currentPlayer.GetColor())) {
//                if (board.CheckIfGameEnded()) {
//                    Checker winner = board.ReturnWinner();
//                    if (winner == Checker.Nothing) {
//                        printer.PrintMessage("It's a tie!");
//                    } else {
//                        printer.PrintWinnerMessage(winner);
//                    }
//                    break;
//                }
//                printer.PrintMessage("No available move");
//                printer.PrintMessage("Turn go to next player");
//                tmpP = currentPlayer;
//                currentPlayer = nextPlayer;
//                nextPlayer = tmpP;
//                continue;
//            }
//            printer.PrintAvilable(board.GetBoard(), CheckerHelper.GetAvailableChecker(currentPlayer.GetColor()));
//            printer.PrintMessage("Please enter location to put disc (format: \"row colum\"): ");
//            Point tmp = currentPlayer.GetPointFromPlayer();
//            while (!board.DoTurn(tmp.getRow(), tmp.getCol(), currentPlayer.GetColor())) {
//                printer.PrintMessage("You entered incorrect location...");
//                tmp = currentPlayer.GetPointFromPlayer();
//            }
//            tmpP = currentPlayer;
//            currentPlayer = nextPlayer;
//            nextPlayer = tmpP;
//        }
        board.FindAllAvailable(currentP.GetColor());
        printer.PrintBoard(board.GetBoard());
        printer.PrintTurnOf(currentP.GetColor());
        printer.PrintScore(board.getScoreWhite(), board.getScoreBlack());

    }

    public void DoTurn(Point point) {

        board.DoTurn(point.getRow(), point.getCol(), currentP.GetColor());

        currentP = currentP == white ? black : white;
        board.FindAllAvailable(currentP.GetColor());

        if (!board.CheckPlayerAvailable(currentP.GetColor())) {
            if (board.CheckIfGameEnded()) {
                printer.PrintTurnOf(Checker.Nothing);
                printer.PrintScore(board.getScoreWhite(), board.getScoreBlack());
                Checker winner = board.ReturnWinner();
                if (winner == Checker.Nothing) {
                    printer.PrintMessage("IT'S A TIE!");
                } else {
                    printer.PrintWinnerMessage(winner);
                }
                printer.PrintBoard(board.GetBoard());
                return;
            }
            printer.PrintMessage("No available move\nTurn go to next player");
            currentP = currentP == white ? black : white;
        }

        printer.PrintBoard(board.GetBoard());
        printer.PrintTurnOf(currentP.GetColor());
        printer.PrintScore(board.getScoreWhite(), board.getScoreBlack());
    }


}
