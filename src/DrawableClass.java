import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DrawableClass extends DrawableObject {
    private static int OFFSET_CORRECTION = 40;
    private int width = 100;
    private int height = 40;
    private int connectionPointWidth = 10;
    private Rectangle2D objArea; //For check point overlap purpose

    public DrawableClass(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth);
        text = "Class";
        objArea = new Rectangle(leftUpPoint.x-3,leftUpPoint.y-3, width+6, 3* height +6);
        northPoint = new Point(givenPoint.x+(width/2), givenPoint.y);
        eastPoint = new Point(givenPoint.x+width, givenPoint.y+(height *3/2));
        southPoint = new Point(givenPoint.x+(width/2) ,givenPoint.y+ height *3);
        westPoint = new Point(givenPoint.x, givenPoint.y+(height *3/2));

        rightDownPoint = new Point(leftUpPoint.x+width,leftUpPoint.y+ height *3);
        oriLeftUpPoint = new Point(0,0);
        oriLeftUpPoint.x = leftUpPoint.x;
        oriLeftUpPoint.y = leftUpPoint.y;
    }

    @Override
    protected void paintObject(Graphics g) {

        if (isMoving) {
            g.setColor(Color.GRAY);
            g.fillRect(leftUpPoint.x-2,leftUpPoint.y-2, width+4, 3* height +4);
            g.setColor(Color.BLACK);
            g.drawRect(leftUpPoint.x, leftUpPoint.y, width, height);
            g.drawRect(leftUpPoint.x, leftUpPoint.y+OFFSET_CORRECTION, width, height);
            g.drawRect(leftUpPoint.x, leftUpPoint.y+2*OFFSET_CORRECTION, width, height);
            g.drawRect(leftUpPoint.x-2,leftUpPoint.y-2, width+4, 3* height +4);
            if (getSelectedState()) {
                g.fillRect(northPoint.x, northPoint.y-connectionPointWidth, connectionPointWidth, connectionPointWidth);
                g.fillRect(eastPoint.x, eastPoint.y, connectionPointWidth, connectionPointWidth);
                g.fillRect(southPoint.x, southPoint.y, connectionPointWidth, connectionPointWidth);
                g.fillRect(westPoint.x-connectionPointWidth, westPoint.y, connectionPointWidth, connectionPointWidth);
            }
            g.drawString(text,leftUpPoint.x+(width/3),leftUpPoint.y+(height /2));

        }
        else {
            g.setColor(Color.GRAY);
            g.fillRect(oriLeftUpPoint.x-2, oriLeftUpPoint.y-2, width+4, 3* height +4);
            g.setColor(Color.BLACK);
            g.drawRect(oriLeftUpPoint.x, oriLeftUpPoint.y, width, height);
            g.drawRect(oriLeftUpPoint.x, oriLeftUpPoint.y+OFFSET_CORRECTION, width, height);
            g.drawRect(oriLeftUpPoint.x, oriLeftUpPoint.y+2*OFFSET_CORRECTION, width, height);
            g.drawRect(oriLeftUpPoint.x-2, oriLeftUpPoint.y-2, width+4, 3* height +4);
            if (getSelectedState()) {
                g.fillRect(northPoint.x, northPoint.y-connectionPointWidth, connectionPointWidth, connectionPointWidth);
                g.fillRect(eastPoint.x, eastPoint.y, connectionPointWidth, connectionPointWidth);
                g.fillRect(southPoint.x, southPoint.y, connectionPointWidth, connectionPointWidth);
                g.fillRect(westPoint.x-connectionPointWidth, westPoint.y, connectionPointWidth, connectionPointWidth);
            }
            g.drawString(text, oriLeftUpPoint.x+(width/3), oriLeftUpPoint.y+(height /2));
        }
    }

    @Override
    public void moveDrawableObj(int x_mov, int y_mov) {
        if (isMoving) {
            leftUpPoint.x = oriLeftUpPoint.x+x_mov;
            leftUpPoint.y = oriLeftUpPoint.y+y_mov;
            objArea = new Rectangle(leftUpPoint.x-3,leftUpPoint.y-3, width+6, 3* height +6);
            northPoint = new Point(leftUpPoint.x+(width/2), leftUpPoint.y);
            eastPoint = new Point(leftUpPoint.x+width, leftUpPoint.y+(height *3/2));
            southPoint = new Point(leftUpPoint.x+(width/2) ,leftUpPoint.y+ height *3);
            westPoint = new Point(leftUpPoint.x, leftUpPoint.y+(height *3/2));
        }
        else {
            oriLeftUpPoint.x = leftUpPoint.x;
            oriLeftUpPoint.y = leftUpPoint.y;
            rightDownPoint = new Point(oriLeftUpPoint.x+width, oriLeftUpPoint.y+ height *3);
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
