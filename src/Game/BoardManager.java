package Game;


public class BoardManager {

    private int board_size;
    private Checker[][] board;
    private AvailableFinder finder;
    private int scoreWhite;
    private int scoreBlack;

    public BoardManager(int size) {
        board_size = size;
        board = new Checker[size][size];
        finder = new RegularRules();
        Init();
    }

    private void Init() {
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                board[i][j] = Checker.Nothing;
            }
        }
        int mid = (board_size / 2) - 1;
        board[mid][mid] = Checker.White;
        board[mid + 1][mid] = Checker.Black;
        board[mid][mid + 1] = Checker.Black;
        board[mid + 1][mid + 1] = Checker.White;
        scoreWhite = 2;
        scoreBlack = 2;
    }

    public void SetRule(AvailableFinder finder_) {
        finder = finder_;
    }

    public int GetSize() {
        return board_size;
    }

    public void ResizeAndInit(int newSize) {
        board_size = newSize;
        board = new Checker[newSize][newSize];
        Init();
    }

    public boolean IsAvailable(int row, int colum, Checker available_to_check) {
        if ((row < 0 || row >= board_size) || (colum < 0 || colum >= board_size)) {
            return false;
        }
        if (board[row][colum] == available_to_check) {
            return true;
        }
        return false;
    }

    public void FindAllAvailable(Checker color) {
        finder.FindAllAvailable(board, board_size, color);
    }

    public boolean DoTurn(int row, int col, Checker to_put) {
        int limit;
        Checker check_available = CheckerHelper.GetAvailableChecker(to_put);

        if (!IsAvailable(row, col, check_available)) {
            return false;
        }

        AddScore(to_put, 1);
        board[row][col] = to_put;

        limit = board_size - row - 1;
        FlipByDirection(row, col, to_put, Direction.up, limit);

        limit = row - 1;
        FlipByDirection(row, col, to_put, Direction.down, limit);

        limit = board_size - col - 1;
        FlipByDirection(row, col, to_put, Direction.right, limit);

        limit = col - 1;
        FlipByDirection(row, col, to_put, Direction.left, limit);

        limit = (((board_size - row) < (board_size - col)) ?
                (board_size - row) : (board_size - col)) - 1;
        FlipByDirection(row, col, to_put, Direction.upRight, limit);

        limit = ((row < col) ? row + 1 : col + 1) - 1;
        FlipByDirection(row, col, to_put, Direction.downLeft, limit);

        limit = ((row + 1 < (board_size - col)) ? row + 1 : (board_size - col)) - 1;
        FlipByDirection(row, col, to_put, Direction.downRight, limit);

        limit = (((board_size - row) < col + 1) ? (board_size - row) : col + 1) - 1;
        FlipByDirection(row, col, to_put, Direction.upLeft, limit);

        return true;
    }

    private void FlipByDirection(int row, int col, Checker to_put, Point d, int limit) {

        Checker to_turn_around = CheckerHelper.GetOppositeChecker(to_put);
        int r = row + d.getRow(); int c = col + d.getCol();
        boolean found_checker = false;
        for (int i = 0; i < limit; i++) {
            if (board[r][c] != to_turn_around) {
                if (found_checker && board[r][c] == to_put) {
                    r = row + d.getRow(); c = col + d.getCol();
                    for (int j = 0; j < i; j++) {
                        board[r][c] = to_put;
                        AddScore(to_put, 1);
                        AddScore(to_turn_around, -1);
                        r += d.getRow(); c += d.getCol();
                    }
                }
                break;
            }
            found_checker = true;
            r += d.getRow(); c += d.getCol();
        }
    }

    public boolean CheckPlayerAvailable(Checker color) {
        Checker available = CheckerHelper.GetAvailableChecker(color);

        for (int r = 0; r < board_size; r++) {
            for (int c = 0; c < board_size; c++) {
                if (board[r][c] == available) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getScoreWhite() {
        return scoreWhite;
    }

    public int getScoreBlack() {
        return scoreBlack;
    }

    public boolean CheckIfGameEnded() {
        FindAllAvailable(Checker.White);
        if (CheckPlayerAvailable(Checker.White)) {
            return false;
        }
        FindAllAvailable(Checker.Black);
        if (CheckPlayerAvailable(Checker.Black)) {
            return false;
        }
        return true;
    }

    public Checker ReturnWinner() {
        int counterB = 0, counterW = 0;
        for (int r = 0; r < board_size; r++) {
            for (int c = 0; c < board_size; c++) {
                if (board[r][c] == Checker.Black) {
                    counterB++;
                }
                if (board[r][c] == Checker.White) {
                    counterW++;
                }
            }
        }
        if (counterB < counterW) {
            return Checker.White;
        }
        return Checker.Black;
    }

    public Checker[][] GetBoard() {
        return board;
    }

    private void AddScore(Checker player, int amount) {
        if (player == Checker.White) {
            scoreWhite += amount;
        } else {
            scoreBlack += amount;
        }
    }
}