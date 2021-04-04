import javax.swing.*;
import java.awt.*;

public class Drawable extends CompositeProtocol {
    protected Point startPoint = new Point(0,0);
    protected String text;
    protected int objDepth;

    public Drawable(Point givenPoint) {
        super(OBJ_TYPE.DRAWABLE_OBJ);
        this.startPoint.x = givenPoint.x;
        this.startPoint.y = givenPoint.y;
    }

    public Drawable(Point givenPoint, int givenDepth) {
        super(OBJ_TYPE.DRAWABLE_OBJ);
        this.startPoint.x = givenPoint.x;
        this.startPoint.y = givenPoint.y;
        this.objDepth = givenDepth;
    }

    public int getX() { return startPoint.x; }
    public int getY() { return startPoint.y; }

    protected void paintObject(Graphics g){    }
}
