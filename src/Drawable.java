import java.awt.*;
import java.util.ArrayList;

public class Drawable implements Comparable {
    protected String text;
    protected int objDepth;
    protected boolean selectedState = false;
    protected boolean isMoving = false;

    @Override
    public int compareTo(Object o) {
        int compareDepth = ((Drawable)o).getObjDepth();
        return this.objDepth-compareDepth;
    }

    public enum CONNECT_POSITION {
        NORTH, EAST, SOUTH, WEST
    }

    public Drawable(Point givenPoint, int givenDepth, boolean isLine) {
        this.objDepth = givenDepth;
    }

    public Drawable(int givenDepth) {
        this.objDepth = givenDepth;
    }

    public void setText(String givenName) { text = givenName; }
    public void setIsMoving(boolean givenValue) { isMoving = givenValue; }
    public boolean isMoving() { return isMoving; }
    public void setSelectedState(boolean givenValue) { selectedState = givenValue; }
    public boolean getSelectedState() { return selectedState; }
    public void moveDrawableObj(int x_mov, int y_mov) {    }
    public int getObjDepth() { return objDepth; }

    protected void paintObject(Graphics g){    }
    public boolean checkPointOverlap(Point givenPoint) { return false; }
    public boolean checkHoleObjectOverlap(Shape givenShape) { return false; }
}
