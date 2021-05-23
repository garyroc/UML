import java.awt.*;

public class Drawable implements Comparable {
    protected String text;
    protected int objDepth;
    protected boolean selectedState = false;

    public enum CONNECT_POSITION {
        NORTH, EAST, SOUTH, WEST
    }

    public Drawable() {

    }

    public Drawable(int givenDepth) {
        this.objDepth = givenDepth;
    }

    @Override
    public int compareTo(Object o) {
        int compareDepth = ((Drawable)o).getObjDepth();
        return this.objDepth-compareDepth;
    }

    public void setText(String givenName) { text = givenName; }
    public void setSelectedState(boolean givenValue) { selectedState = givenValue; }
    public boolean getSelectedState() { return selectedState; }
    public void moveDrawableObj(int x_mov, int y_mov) {    }
    public void finishMovement() {   }
    public int getObjDepth() { return objDepth; }

    protected void paintObject(Graphics g){    }
    public boolean checkPointOverlap(Point givenPoint) { return false; }
    public boolean checkHoleObjectOverlap(Shape givenShape) { return false; }
}
