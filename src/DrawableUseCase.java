import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class DrawableUseCase extends Drawable{
    private int width = 100;
    private int heigh = 50;
    private int connectionPointWidth = 10;
    private Ellipse2D.Double objArea;

    public DrawableUseCase(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth, false);
        text = "Use Case";
        objArea = new Ellipse2D.Double(givenPoint.x, givenPoint.y, width, heigh);
        northPoint = new Point(givenPoint.x+(width/2), givenPoint.y-connectionPointWidth);
        eastPoint = new Point(givenPoint.x+width, givenPoint.y+(heigh/2));
        southPoint = new Point(givenPoint.x+(width/2) ,givenPoint.y+heigh);
        westPoint = new Point(givenPoint.x-connectionPointWidth, givenPoint.y+(heigh/2));
    }


    @Override
    protected void paintObject(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(startPoint.x, startPoint.y, width, heigh);
        g.fillRect(northPoint.x,northPoint.y,connectionPointWidth,connectionPointWidth);
        g.fillRect(eastPoint.x,eastPoint.y,connectionPointWidth,connectionPointWidth);
        g.fillRect(southPoint.x,southPoint.y,connectionPointWidth,connectionPointWidth);
        g.fillRect(westPoint.x,westPoint.y,connectionPointWidth,connectionPointWidth);
        g.drawString(text,startPoint.x+(width/4),startPoint.y+(heigh/2));
    }

    @Override
    public boolean checkOverlap(Point givenPoint) {
        return objArea.contains(givenPoint);
    }
}
