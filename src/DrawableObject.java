import java.awt.*;
import java.util.ArrayList;

public class DrawableObject extends Drawable implements CompositePortocolInterface{
    protected Point leftUpPoint;
    protected Point oriLeftUpPoint;
    protected Point rightDownPoint;
    /* DrawableObj Connection point declare */
    protected Point northPoint = null;
    protected Point eastPoint = null;
    protected Point southPoint = null;
    protected Point westPoint = null;

    public DrawableObject(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth, false);
        leftUpPoint = givenPoint;
    }

    public Point getConnectedPoint(CONNECT_POSITION givenPosition) {
        Point resultPoint = null;
        switch (givenPosition) {
            case NORTH:
                resultPoint = getNorthPoint();
                break;
            case EAST:
                resultPoint = getEastPoint();
                break;
            case SOUTH:
                resultPoint = getSouthPoint();
                break;
            case WEST:
                resultPoint = getWestPoint();
                break;
        }
        return resultPoint;
    }

    public Point getLeftUpPoint() { return leftUpPoint; }
    public Point getRightDownPoint() { return rightDownPoint; }
    public Point getNorthPoint() { return northPoint; }
    public Point getEastPoint() { return eastPoint; }
    public Point getSouthPoint() { return southPoint; }
    public Point getWestPoint() {return westPoint; }

    public CONNECT_POSITION isConnectToObj(Point givenPoint) { return null; }

    public ArrayList<Drawable> composeObj(){
        return null;
    }
    public ArrayList<Drawable> deComposeObj(){
        return null;
    }
}
