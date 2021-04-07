import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawableClass extends Drawable {
    private static int OFFSET_CORRECTION = 40;
    private int width = 100;
    private int heigh = 40;
    private int connectionPointWidth = 10;
    protected Point oriStartPoint;
    private Rectangle2D objArea; //For check point overlap purpose

    public DrawableClass(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth, false);
        text = "Class";
        objArea = new Rectangle(startPoint.x-3,startPoint.y-3, width+6, 3*heigh+6);
        northPoint = new Point(givenPoint.x+(width/2), givenPoint.y);
        eastPoint = new Point(givenPoint.x+width, givenPoint.y+(heigh*3/2));
        southPoint = new Point(givenPoint.x+(width/2) ,givenPoint.y+heigh*3);
        westPoint = new Point(givenPoint.x, givenPoint.y+(heigh*3/2));

        endPoint = new Point(startPoint.x+width,startPoint.y+heigh*3);
        oriStartPoint = new Point(0,0);
        oriStartPoint.x = startPoint.x;
        oriStartPoint.y = startPoint.y;
    }

    @Override
    protected void paintObject(Graphics g) {

        if (isMoving) {
            g.setColor(Color.GRAY);
            g.fillRect(startPoint.x-2,startPoint.y-2, width+4, 3*heigh+4);
            g.setColor(Color.BLACK);
            g.drawRect(startPoint.x, startPoint.y, width, heigh);
            g.drawRect(startPoint.x, startPoint.y+OFFSET_CORRECTION, width, heigh);
            g.drawRect(startPoint.x, startPoint.y+2*OFFSET_CORRECTION, width, heigh);
            g.drawRect(startPoint.x-2,startPoint.y-2, width+4, 3*heigh+4);
            if (getSelectedState()) {
                g.fillRect(northPoint.x, northPoint.y-connectionPointWidth, connectionPointWidth, connectionPointWidth);
                g.fillRect(eastPoint.x, eastPoint.y, connectionPointWidth, connectionPointWidth);
                g.fillRect(southPoint.x, southPoint.y, connectionPointWidth, connectionPointWidth);
                g.fillRect(westPoint.x-connectionPointWidth, westPoint.y, connectionPointWidth, connectionPointWidth);
            }
            g.drawString(text,startPoint.x+(width/3),startPoint.y+(heigh/2));


        }
        else {
            g.setColor(Color.GRAY);
            g.fillRect(oriStartPoint.x-2,oriStartPoint.y-2, width+4, 3*heigh+4);
            g.setColor(Color.BLACK);
            g.drawRect(oriStartPoint.x, oriStartPoint.y, width, heigh);
            g.drawRect(oriStartPoint.x, oriStartPoint.y+OFFSET_CORRECTION, width, heigh);
            g.drawRect(oriStartPoint.x, oriStartPoint.y+2*OFFSET_CORRECTION, width, heigh);
            g.drawRect(oriStartPoint.x-2,oriStartPoint.y-2, width+4, 3*heigh+4);
            if (getSelectedState()) {
                g.fillRect(northPoint.x, northPoint.y-connectionPointWidth, connectionPointWidth, connectionPointWidth);
                g.fillRect(eastPoint.x, eastPoint.y, connectionPointWidth, connectionPointWidth);
                g.fillRect(southPoint.x, southPoint.y, connectionPointWidth, connectionPointWidth);
                g.fillRect(westPoint.x-connectionPointWidth, westPoint.y, connectionPointWidth, connectionPointWidth);
            }
            g.drawString(text,oriStartPoint.x+(width/3),oriStartPoint.y+(heigh/2));

        }
    }

    @Override
    public void moveDrawableObj(int x_mov, int y_mov) {
        if (isMoving) {
            startPoint.x = oriStartPoint.x+x_mov;
            startPoint.y = oriStartPoint.y+y_mov;
            objArea = new Rectangle(startPoint.x-3,startPoint.y-3, width+6, 3*heigh+6);
            northPoint = new Point(startPoint.x+(width/2), startPoint.y);
            eastPoint = new Point(startPoint.x+width, startPoint.y+(heigh*3/2));
            southPoint = new Point(startPoint.x+(width/2) ,startPoint.y+heigh*3);
            westPoint = new Point(startPoint.x, startPoint.y+(heigh*3/2));
        }
        else {
            oriStartPoint.x = startPoint.x;
            oriStartPoint.y = startPoint.y;
            endPoint = new Point(oriStartPoint.x+width,oriStartPoint.y+heigh*3);
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
