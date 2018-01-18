package Game;

public class GuiPlayer extends Player {

    private ClickListener clickListener;

    public GuiPlayer(Checker color, ClickListener clickListener) {
        super(color);
        this.clickListener = clickListener;
    }

    @Override
    Point GetPointFromPlayer() {
        while (!clickListener.getValid()) {}
        return clickListener.getPoint();
    }
}
