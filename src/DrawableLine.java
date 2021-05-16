import java.awt.*;
import java.util.ArrayList;

public class DrawableLine extends Drawable {
    protected Point startPoint;
    protected Point endPoint;
    protected DrawableObject startPointObject;
    protected DrawableObject endPointObject;
    protected CONNECT_POSITION startPointPosition = null;
    protected CONNECT_POSITION endPointPosition = null;

    public DrawableLine(Point givenStartPoint, Point givenEndPoint, int givenDepth) {
        super(givenStartPoint, givenDepth, true);
        startPoint = givenStartPoint;
        endPoint = givenEndPoint;
    }

    public Point getStartPoint() { return startPoint; }
    public Point getEndPoint() { return endPoint; }
    public void setEndPoint(Point givenPoint) { endPoint = givenPoint; }
    public void setEndPoint() {
        endPoint = endPointObject.getConnectedPoint(endPointPosition);
    }

    public void setStartPointObject(DrawableObject givenObj) { startPointObject = givenObj; }
    public void setEndPointObject(DrawableObject givenObj) { endPointObject = givenObj; }
    public void setStartPointPosition(CONNECT_POSITION givenPosition) { startPointPosition = givenPosition; }
    public void setEndPointPosition(CONNECT_POSITION givenPosition) { endPointPosition = givenPosition; }
    public CONNECT_POSITION getStartPointPosition() { return startPointPosition; }
    public CONNECT_POSITION getEndPointPosition() { return endPointPosition; }
    public Drawable getStartPointObject() { return startPointObject; }
    public Drawable getEndPointObject() { return endPointObject; }

    /* For determine line exist or not */
    public boolean starPointcheck(ArrayList<DrawableObject> givenDrawObjList) {
        boolean testResult = false;
        CONNECT_POSITION connectPosition;
        for (DrawableObject drawObj : givenDrawObjList) {
            connectPosition = drawObj.isConnectToObj(startPoint);
            if (connectPosition != null ) {
                setStartPointObject(drawObj);
                setStartPointPosition(connectPosition);
                testResult = true;
                break;
            }
        }
        return testResult;
    }

    public boolean endPointcheck(ArrayList<DrawableObject> givenDrawObjList) {
        boolean testResult = false;
        CONNECT_POSITION connectPosition;
        for (DrawableObject drawObj : givenDrawObjList) {
            connectPosition = drawObj.isConnectToObj(endPoint);
            if (connectPosition != null ) {
                setEndPointObject(drawObj);
                setEndPointPosition(connectPosition);
                testResult = true;
                break;
            }
        }
        return testResult;
    }
}
