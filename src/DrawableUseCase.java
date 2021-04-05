import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class DrawableUseCase extends Drawable{
    private int width = 100;
    private int heigh = 50;
    private int connectionPointWidth = 10;
    private int connectionPointOffset = 7;
    private Ellipse2D.Double objArea;

    public DrawableUseCase(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth, false);
        text = "Use Case";
        objArea = new Ellipse2D.Double(startPoint.x-3, startPoint.y-3, width+6, heigh+6);
        northPoint = new Point(givenPoint.x+(width/2), givenPoint.y);
        eastPoint = new Point(givenPoint.x+width, givenPoint.y+(heigh/2));
        southPoint = new Point(givenPoint.x+(width/2) ,givenPoint.y+heigh);
        westPoint = new Point(givenPoint.x, givenPoint.y+(heigh/2));
    }


    @Override
    protected void paintObject(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(startPoint.x, startPoint.y, width, heigh);
        g.drawOval(startPoint.x-2, startPoint.y-2, width+4, heigh+4);
        if (getSelectedState()) {
            g.fillRect(northPoint.x-(connectionPointWidth/2),northPoint.y-connectionPointWidth,connectionPointWidth,connectionPointWidth);
            g.fillRect(eastPoint.x,eastPoint.y-(connectionPointWidth/2),connectionPointWidth,connectionPointWidth);
            g.fillRect(southPoint.x-(connectionPointWidth/2),southPoint.y,connectionPointWidth,connectionPointWidth);
            g.fillRect(westPoint.x-connectionPointWidth,westPoint.y-(connectionPointWidth/2),connectionPointWidth,connectionPointWidth);
        }
        g.drawString(text,startPoint.x+(width/4),startPoint.y+(heigh/2));
    }

    @Override
    public boolean checkOverlap(Point givenPoint) {
        return objArea.contains(givenPoint);
    }
}
