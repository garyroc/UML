import java.awt.*;

public class Drawable extends CompositeProtocol implements Comparable {
    protected Point startPoint = new Point(0,0);
    protected Point endPoint;
    protected String text;
    protected int objDepth;
    protected boolean isLineObj;
    protected boolean selectedState;
    protected boolean isMoving = false;

    /* Connection point declare */
    protected Point northPoint = null;
    protected Point eastPoint = null;
    protected Point southPoint = null;
    protected Point westPoint = null;

    @Override
    public int compareTo(Object o) {
        int compareDepth = ((Drawable)o).getObjDepth();
        return this.objDepth-compareDepth;
    }

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
    public void setText(String givenName) { text = givenName; }
    public void setStartPoint(Point givenPoint ) { startPoint = givenPoint; }
    public Point getStartPoint() { return startPoint; }
    public Point getNorthPoint() { return northPoint; }
    public Point getEastPoint() { return eastPoint; }
    public Point getSouthPoint() { return southPoint; }
    public Point getWestPoint() {return westPoint; }
    public boolean isLineObj() { return isLineObj; }
    public void setIsMoving(boolean givenValue) { isMoving = givenValue; }
    public boolean isMoving() { return isMoving; }
    public void setSelectedState(boolean givenValue) { selectedState = givenValue; }
    public boolean getSelectedState() { return selectedState; }
    public void moveDrawableObj(int x_mov, int y_mov) {
        startPoint.x = startPoint.x+x_mov;
        startPoint.y = startPoint.y+y_mov;
    }
    public int getObjDepth() { return objDepth; }

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
    public Point getLeftUpPoint() { return startPoint; }
    public Point getRightDownPoint() { return endPoint; }

    protected void paintObject(Graphics g){    }
    public boolean checkPointOverlap(Point givenPoint) { return false; }
    public boolean checkHoleObjectOverlap(Shape givenShape) { return false; }
}
