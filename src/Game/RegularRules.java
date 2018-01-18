package Game;

public class RegularRules implements GameRule {

    public void FindAllAvailable(Checker[][] board, int board_size, Checker color) {
        DeleteAllAvailable(board, board_size);
        FindAvailableByColor(board, board_size, color);
    }

    private void DeleteAllAvailable(Checker[][] board, int board_size) {
        for (int r = 0; r < board_size; r++) {
            for (int c = 0; c < board_size; c++) {
                switch (board[r][c]) {
                    case AvailableB:
                    case AvailableW:
                        board[r][c] = Checker.Nothing;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void FindAvailableByColor(Checker[][] board, int board_size, Checker available) {
        boolean check = false;
        for (int r = 0; r < board_size; r++) {
            for (int c = 0; c < board_size; c++) {
                Checker checker = board[r][c];
                if (checker == Checker.Black || checker == Checker.White) {
                    continue;
                }
                if (CheckIfAvailable(board, board_size, r, c, available) == true) {
                        board[r][c] = Checker.GetAvailableChecker(available);
                        check = true;
                }
            }
        }
        if (!check) {
            int x = 5;
        }
    }

    public boolean CheckIfAvailable(Checker[][] board, int board_size, int row, int col, Checker to_put) {
        int limit;

        limit = board_size - row - 1;
        if (CheckByDirection(board, row, col, to_put, Direction.up, limit))
            return true;

        limit = row - 1;
        if (CheckByDirection(board, row, col, to_put, Direction.down, limit))
            return true;

        limit = board_size - col - 1;
        if (CheckByDirection(board, row, col, to_put, Direction.right, limit))
            return true;

        limit = col - 1;
        if (CheckByDirection(board, row, col, to_put, Direction.left, limit))
            return true;

        limit = (((board_size - row) < (board_size - col)) ?
                (board_size - row) : (board_size - col)) - 1;
        if (CheckByDirection(board, row, col, to_put, Direction.upRight, limit))
            return true;

        limit = ((row < col) ? row + 1 : col + 1) - 1;
        if (CheckByDirection(board, row, col, to_put, Direction.downLeft, limit))
            return true;

        limit = ((row + 1 < (board_size - col)) ? row + 1 : (board_size - col)) - 1;
        if (CheckByDirection(board, row, col, to_put, Direction.downRight, limit))
            return true;

        limit = (((board_size - row) < col + 1) ? (board_size - row) : col + 1) - 1;
        if (CheckByDirection(board, row, col, to_put, Direction.upLeft, limit))
            return true;

        return false;
    }

    private boolean CheckByDirection(Checker[][] board, int row, int col, Checker to_put, Point d, int limit) {
        Checker to_turn_around = Checker.GetOppositeChecker(to_put);
        int r = row + d.getRow();
        int c = col + d.getCol();
        boolean found_checker = false;
        for (int i = 0; i < limit; i++) {
            if (board[r][c] != to_turn_around) {
                if (found_checker && board[r][c] == to_put) {
                    return true;
                }
                break;
            }
            found_checker = true;
            r += d.getRow();
            c += d.getCol();
        }
        return false;
    }
}