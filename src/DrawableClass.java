import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawableClass extends DrawableObject {
    private final int width = 100;
    private final int height = 40;
    private Rectangle2D objArea; //For check point overlap purpose

    public DrawableClass(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth);
        text = "Class";
        objArea = new Rectangle(leftUpPoint.x-3,leftUpPoint.y-3, width+6, 3* height +6);
        northPoint = new Point(leftUpPoint.x+(width/2), leftUpPoint.y);
        eastPoint = new Point(leftUpPoint.x+width, leftUpPoint.y+(height *3/2));
        southPoint = new Point(leftUpPoint.x+(width/2) ,leftUpPoint.y+ height *3);
        westPoint = new Point(leftUpPoint.x, leftUpPoint.y+(height *3/2));

        rightDownPoint = new Point(leftUpPoint.x+width,leftUpPoint.y+ height *3);
        oriLeftUpPoint = new Point(leftUpPoint.x,leftUpPoint.y);
    }

    @Override
    protected void paintObject(Graphics g) {
        int offsetCorrection = 40;
        Point drawingPoint;
        if (this.getSelectedState()) {
            drawingPoint = leftUpPoint;
        }
        else {
            drawingPoint = oriLeftUpPoint;
        }
        g.setColor(Color.GRAY);
        g.fillRect(drawingPoint.x-2,drawingPoint.y-2, width+4, 3* height +4); //Paint Background
        //Paint Body
        g.setColor(Color.BLACK);
        g.drawRect(drawingPoint.x, drawingPoint.y, width, height);

        g.drawRect(drawingPoint.x, drawingPoint.y+ offsetCorrection, width, height);
        g.drawRect(drawingPoint.x, drawingPoint.y+2* offsetCorrection, width, height);
        g.drawRect(drawingPoint.x-2,drawingPoint.y-2, width+4, 3* height +4);
        //Paint connection point
        if (getSelectedState()) {
            int connectionPointSize = 10;
            g.fillRect(northPoint.x, northPoint.y- connectionPointSize, connectionPointSize, connectionPointSize);
            g.fillRect(eastPoint.x, eastPoint.y, connectionPointSize, connectionPointSize);
            g.fillRect(southPoint.x, southPoint.y, connectionPointSize, connectionPointSize);
            g.fillRect(westPoint.x- connectionPointSize, westPoint.y, connectionPointSize, connectionPointSize);
        }
        g.drawString(text,drawingPoint.x+(width/3),drawingPoint.y+(height /2)); //Paint name
    }

    @Override
    public void moveDrawableObj(int x_mov, int y_mov) {
        leftUpPoint.x = oriLeftUpPoint.x+x_mov;
        leftUpPoint.y = oriLeftUpPoint.y+y_mov;
        northPoint = new Point(leftUpPoint.x+(width/2), leftUpPoint.y);
        eastPoint = new Point(leftUpPoint.x+width, leftUpPoint.y+(height *3/2));
        southPoint = new Point(leftUpPoint.x+(width/2) ,leftUpPoint.y+ height *3);
        westPoint = new Point(leftUpPoint.x, leftUpPoint.y+(height *3/2));
    }

    @Override
    public void finishMovement() {
        oriLeftUpPoint = new Point(leftUpPoint.x,leftUpPoint.y);
        objArea = new Rectangle(leftUpPoint.x-3,leftUpPoint.y-3, width+6, 3* height +6);
        rightDownPoint = new Point(oriLeftUpPoint.x+width, oriLeftUpPoint.y+ height *3);
    }

    @Override
    public boolean checkPointOverlap(Point givenPoint) {
        return objArea.contains(givenPoint);
    }

    @Override
    public boolean checkHoleObjectOverlap(Shape givenShape) {
        if (givenShape.contains(this.getNorthPoint()) && givenShape.contains(this.getEastPoint()) && givenShape.contains(this.getSouthPoint()) && givenShape.contains(this.getWestPoint())) {
            this.setSelectedState(true);
            return true;
        }
        return false;
    }

    @Override
    public DrawableObject composeObj(){
        return this;
    }
}
