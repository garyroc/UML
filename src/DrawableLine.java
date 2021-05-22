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
        super(givenStartPoint, givenDepth);
        startPoint = givenStartPoint;
        endPoint = givenEndPoint;
        selectedState = true;
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
    public boolean starPointCheck(ArrayList<DrawableObject> givenDrawObjList) {
        CONNECT_POSITION connectPosition;
        for (DrawableObject drawObj : givenDrawObjList) {
            connectPosition = drawObj.isConnectToObj(startPoint);
            if (connectPosition != null ) {
                setStartPointObject(drawObj);
                setStartPointPosition(connectPosition);
                startPoint = drawObj.getConnectedPoint(connectPosition); // update start point
                return true;
            }
        }
        return false;
    }

    public boolean endPointCheck(ArrayList<DrawableObject> givenDrawObjList) {
        CONNECT_POSITION connectPosition;
        for (DrawableObject drawObj : givenDrawObjList) {
            connectPosition = drawObj.isConnectToObj(endPoint);
            if (connectPosition != null && drawObj != startPointObject) {
                setEndPointObject(drawObj);
                setEndPointPosition(connectPosition);
                endPoint = drawObj.getConnectedPoint(connectPosition); // update end point
                return true;
            }
        }
        return false;
    }
}
