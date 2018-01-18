package Game;

public class Direction {
    public static Point up = new Point(1,0);
    public static Point down = new Point(-1,0);
    public static Point right = new Point(0,1);
    public static Point left = new Point(0,-1);
    public static Point upRight = new Point(1,1);
    public static Point upLeft = new Point(1,-1);
    public static Point downRight = new Point(-1,1);
    public static Point downLeft = new Point(-1,-1);
    public static Point allD[] = {up, down, right, left, upRight, upLeft, downRight, downLeft};
}
