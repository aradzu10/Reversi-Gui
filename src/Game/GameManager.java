package Game;


public class GameManager {

    private BoardManager board;
    private Player white;
    private Player black;
    private Player currentP;
    private Printer printer;

    public GameManager(int size_, BoardManager board_) {
        board = board_;
        printer = new ConsolePrinter();
        white = new ConsolePlayer(Checker.White);
        black = new ConsolePlayer(Checker.Black);
        currentP = white;
    }

    public void SetPrinter(Printer printer_) {
        printer = printer_;
    }

    public void SetPlayers(Player white_, Player black_) {
        white = white_;
        currentP = white_;
        black = black_;
    }

    public void StartGame() {

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
