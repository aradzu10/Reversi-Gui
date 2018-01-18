package Game;

public enum Checker {
    Nothing, White, Black, AvailableB, AvailableW;

    public static Checker GetOppositeChecker(Checker checker) {
        if (checker == Checker.Black) {
            return Checker.White;
        }
        if (checker == Checker.White) {
            return Checker.Black;
        }
        return Checker.Nothing;
    }

    public static Checker GetAvailableChecker(Checker checker) {
        if (checker == Checker.Black) {
            return Checker.AvailableB;
        }
        if (checker == Checker.White) {
            return Checker.AvailableW;
        }
        return Checker.Nothing;
    }
};