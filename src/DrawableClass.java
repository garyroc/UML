import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawableClass extends Drawable {
    private static int OFFSET_CORRECTION = 40;
    private int width = 100;
    private int heigh = 40;
    private int connectionPointWidth = 10;
    private Rectangle2D objArea; //For check point overlap purpose

    public DrawableClass(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth, false);
        text = "Class";
        objArea = new Rectangle(startPoint.x,startPoint.y, width, 3*heigh);
        northPoint = new Point(givenPoint.x+(width/2), givenPoint.y-connectionPointWidth);
        eastPoint = new Point(givenPoint.x+width, givenPoint.y+(heigh*3/2));
        southPoint = new Point(givenPoint.x+(width/2) ,givenPoint.y+heigh*3);
        westPoint = new Point(givenPoint.x-connectionPointWidth, givenPoint.y+(heigh*3/2));
    }

    @Override
    protected void paintObject(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(startPoint.x, startPoint.y, width, heigh);
        g.drawRect(startPoint.x, startPoint.y+OFFSET_CORRECTION, width, heigh);
        g.drawRect(startPoint.x, startPoint.y+2*OFFSET_CORRECTION, width, heigh);
        g.fillRect(northPoint.x, northPoint.y, connectionPointWidth, connectionPointWidth);
        g.fillRect(eastPoint.x, eastPoint.y, connectionPointWidth, connectionPointWidth);
        g.fillRect(southPoint.x, southPoint.y, connectionPointWidth, connectionPointWidth);
        g.fillRect(westPoint.x, westPoint.y, connectionPointWidth, connectionPointWidth);
        g.drawString(text,startPoint.x+(width/3),startPoint.y+(heigh/2));
    }

    @Override
    public boolean checkOverlap(Point givenPoint) {
        return objArea.contains(givenPoint);
    }
}
