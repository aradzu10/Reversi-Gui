package Game;

public class ConsolePrinter implements Printer {

    @Override
    public void PrintTurnOf(Checker player) {
        System.out.println("It's the turn of player " + ReturnLetterToPrint(player) + ":");
    }

    @Override
    public void PrintBoard(Checker[][] board) {
        int size = board.length;
        System.out.print("current board: \n |");
        int counter = 2;
        for (int i = 0; i < size; i++) {
            System.out.print(" " + (i + 1) + " |");
            counter += 4;
        }
        System.out.println("");

        System.out.println(new String(new char[counter]).replace("\0", "-"));
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < size; j++) {
                System.out.print(" " + ReturnLetterToPrint(board[i][j]) + " |");
            }
            System.out.println("");
            System.out.println(new String(new char[counter]).replace("\0", "-"));
        }
    }


    @Override
    public void PrintAvilable(Checker[][] board, Checker avilable_to_print) {
        int size = board.length;
        System.out.println("Your avilable options are:");
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == avilable_to_print) {
                    System.out.print("(" + (r + 1) + "," + (c + 1) + ") ");
                }
            }
        }
        System.out.println("");
    }

    @Override
    public void PrintMessage(String s) {
        System.out.println(s);
    }

    @Override
    public void PrintWinnerMessage(Checker winner) {
        System.out.println("THE GAME ENDED!");
        System.out.println("THE WINNER IS: " + ReturnLetterToPrint(winner));
    }

    private char ReturnLetterToPrint(Checker checker) {
        switch (checker) {
            case Black:
                return 'X';
            case White:
                return 'O';
            case AvailableB:
            case AvailableW:
            case Nothing:
                return ' ';
            default:
                break;
        }
        return 0;
    }

    @Override
    public void PrintScore(int scorePlayer1, int scorePlayer2) {
    }
}