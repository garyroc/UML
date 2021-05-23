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
        super(givenDepth);
        startPoint = givenStartPoint;
        endPoint = givenEndPoint;
        selectedState = true;
    }

    public Point getStartPoint() { return startPoint; }
    public Point getEndPoint() { return endPoint; }
    public void setStartPoint(Point givenPoint) { startPoint = givenPoint; }
    public void setEndPoint(Point givenPoint) { endPoint = givenPoint; }

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
        LineConnectionPackage connectPackage;
        for (DrawableObject drawObj : givenDrawObjList) {
            connectPackage = drawObj.isConnectToObj(startPoint);
            if (connectPackage.position != null ) {
                setStartPointObject(connectPackage.drawObj);
                setStartPointPosition(connectPackage.position);
                startPoint = startPointObject.getConnectedPoint(startPointPosition); // update start point
                return true;
            }
        }
        return false;
    }

    public boolean endPointCheck(ArrayList<DrawableObject> givenDrawObjList) {
        LineConnectionPackage connectPackage;
        for (DrawableObject drawObj : givenDrawObjList) {
            connectPackage = drawObj.isConnectToObj(endPoint);
            if (connectPackage.position != null && drawObj != startPointObject) {
                setEndPointObject(connectPackage.drawObj);
                setEndPointPosition(connectPackage.position);
                endPoint = endPointObject.getConnectedPoint(endPointPosition); // update end point
                return true;
            }
        }
        return false;
    }

    public void updateLinePosition() {
        setStartPoint(startPointObject.getConnectedPoint(startPointPosition));
        setEndPoint(endPointObject.getConnectedPoint(endPointPosition));
    }
}
