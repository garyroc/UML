import java.awt.*;

public class DrawableLine extends Drawable {
    protected Point endPoint;
    protected boolean hasFinish = false;
    public DrawableLine(Point givenStartPoint, Point givenEndPoint, int givenDepth) {
        super(givenStartPoint, givenDepth, true);
        endPoint = givenEndPoint;
    }

    public void setEndPoint(Point givenPoint) {
        endPoint = givenPoint;
    }
    public Point getEndPoint() {
        return endPoint;
    }
    public void setHasFinish(boolean givenValue) {
        hasFinish = givenValue;
    }

    public boolean getHasFinish() {
        return hasFinish;
    }

}
