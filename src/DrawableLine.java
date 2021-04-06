import java.awt.*;

public class DrawableLine extends Drawable {
//    protected Point endPoint;
    protected Drawable startPointObject;
    protected Drawable endPointObject;
    protected CONNECT_POSITION startPointPosition = null;
    protected CONNECT_POSITION endPointPosition = null;

    public DrawableLine(Point givenStartPoint, Point givenEndPoint, int givenDepth) {
        super(givenStartPoint, givenDepth, true);
        endPoint = givenEndPoint;
    }

    public void setEndPoint(Point givenPoint) { endPoint = givenPoint; }
    public Point getEndPoint() { return endPoint; }
    public void setStartPointObject(Drawable givenObj) { startPointObject = givenObj; }
    public void setEndPointObject(Drawable givenObj) { endPointObject = givenObj; }
    public void setStartPointPosition(CONNECT_POSITION givenPosition) { startPointPosition = givenPosition; }
    public void setEndPointPosition(CONNECT_POSITION givenPosition) { endPointPosition = givenPosition; }
    public CONNECT_POSITION getStartPointPosition() { return startPointPosition; }
    public CONNECT_POSITION getEndPointPosition() { return endPointPosition; }
    public Drawable getStartPointObject() { return startPointObject; }
    public Drawable getEndPointObject() {return endPointObject; }
}
