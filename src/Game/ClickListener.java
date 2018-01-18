package Game;

public class ClickListener {

    private Point point;
    private Boolean valid;

    public ClickListener() {
        point = null;
        valid = false;
    }

    public Point getPoint() {
        valid = false;
        return point;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setPoint(Point point) {
        valid = true;
        this.point = point;
    }

}
