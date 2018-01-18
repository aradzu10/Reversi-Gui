package Game;

public interface GameRule {
    void FindAllAvailable(Checker[][] board, int board_size, Checker color);
}
