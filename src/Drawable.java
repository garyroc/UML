import javax.swing.*;
import java.awt.*;

public class Drawable extends CompositeProtocol {
    protected Point startPoint = new Point(0,0);
    protected String text;
    protected int objDepth;
    protected boolean isLineObj;
    protected boolean selectedState;

    /* Connection point declare */
    protected Point northPoint = null;
    protected Point eastPoint = null;
    protected Point southPoint = null;
    protected Point westPoint = null;

    public enum CONNECT_POSITION {
        NORTH, EAST, SOUTH, WEST
    }


    public Drawable(Point givenPoint, int givenDepth, boolean isLine) {
        super(OBJ_TYPE.DRAWABLE_OBJ);
        this.startPoint.x = givenPoint.x;
        this.startPoint.y = givenPoint.y;
        this.objDepth = givenDepth;
        this.isLineObj = isLine;
    }

    public int getX() { return startPoint.x; }
    public int getY() { return startPoint.y; }
    public void setStartPoint(Point givenPoint ) { startPoint = givenPoint; }
    public Point getStartPoint() { return startPoint; }
    public Point getNorthPoint() { return northPoint; }
    public Point getEastPoint() { return eastPoint; }
    public Point getSouthPoint() { return southPoint; }
    public Point getWestPoint() {return westPoint; }
    public boolean isLineObj() { return isLineObj; }
    public void setSelectedState(boolean givenValue) {
        selectedState = givenValue;
    }
    public boolean getSelectedState() {
        return selectedState;
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

    protected void paintObject(Graphics g){    }
    public boolean checkOverlap(Point givenPoint) { return false; }
}
