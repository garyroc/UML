import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class DrawableUseCase extends Drawable{
    private int width = 100;
    private int heigh = 50;
    private int connectionPointWidth = 10;
    private int connectionPointOffset = 7;
    protected Point oriStartPoint;
    private Ellipse2D.Double objArea;

    public DrawableUseCase(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth, false);
        text = "Use Case";
        objArea = new Ellipse2D.Double(startPoint.x-3, startPoint.y-3, width+6, heigh+6);
        northPoint = new Point(givenPoint.x+(width/2), givenPoint.y);
        eastPoint = new Point(givenPoint.x+width, givenPoint.y+(heigh/2));
        southPoint = new Point(givenPoint.x+(width/2) ,givenPoint.y+heigh);
        westPoint = new Point(givenPoint.x, givenPoint.y+(heigh/2));

        endPoint = new Point(startPoint.x+width,startPoint.y+heigh);
        oriStartPoint = new Point(0,0);
        oriStartPoint.x = startPoint.x;
        oriStartPoint.y = startPoint.y;
    }


    @Override
    protected void paintObject(Graphics g) {
        g.setColor(Color.BLACK);
        if (isMoving) {
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
        else {
            g.drawOval(oriStartPoint.x, oriStartPoint.y, width, heigh);
            g.drawOval(oriStartPoint.x-2, oriStartPoint.y-2, width+4, heigh+4);
            if (getSelectedState()) {
                g.fillRect(northPoint.x-(connectionPointWidth/2),northPoint.y-connectionPointWidth,connectionPointWidth,connectionPointWidth);
                g.fillRect(eastPoint.x,eastPoint.y-(connectionPointWidth/2),connectionPointWidth,connectionPointWidth);
                g.fillRect(southPoint.x-(connectionPointWidth/2),southPoint.y,connectionPointWidth,connectionPointWidth);
                g.fillRect(westPoint.x-connectionPointWidth,westPoint.y-(connectionPointWidth/2),connectionPointWidth,connectionPointWidth);
            }
            g.drawString(text,oriStartPoint.x+(width/4),oriStartPoint.y+(heigh/2));
        }

    }

    @Override
    public void moveDrawableObj(int x_mov, int y_mov) {
        if (isMoving) {
            startPoint.x = oriStartPoint.x+x_mov;
            startPoint.y = oriStartPoint.y+y_mov;
            objArea = new Ellipse2D.Double(startPoint.x-3, startPoint.y-3, width+6, heigh+6);
            northPoint = new Point(startPoint.x+(width/2), startPoint.y);
            eastPoint = new Point(startPoint.x+width, startPoint.y+(heigh/2));
            southPoint = new Point(startPoint.x+(width/2) ,startPoint.y+heigh);
            westPoint = new Point(startPoint.x, startPoint.y+(heigh/2));
        }
        else {
            oriStartPoint.x = startPoint.x;
            oriStartPoint.y = startPoint.y;
            endPoint = new Point(oriStartPoint.x+width,oriStartPoint.y+heigh);
        }
    }

    @Override
    public boolean checkPointOverlap(Point givenPoint) {
        return objArea.contains(givenPoint);
    }
    @Override
    public boolean checkHoleObjectOverlap(Shape givenShape) {
        boolean testResult = false;
        if (givenShape.contains(this.getNorthPoint()) && givenShape.contains(this.getEastPoint()) && givenShape.contains(this.getSouthPoint()) && givenShape.contains(this.getWestPoint())) {
            testResult = true;
        }
        return testResult;
    }
}
