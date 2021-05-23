package Draw;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DrawableUseCase extends DrawableObject{
    private final int width = 100;
    private final int height = 50;
    private Ellipse2D.Double objArea;

    public DrawableUseCase(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth);
        text = "Use Case";
        objArea = new Ellipse2D.Double(leftUpPoint.x-3, leftUpPoint.y-3, width+6, height +6);
        northPoint = new Point(leftUpPoint.x+(width/2), leftUpPoint.y);
        eastPoint = new Point(leftUpPoint.x+width, leftUpPoint.y+(height /2));
        southPoint = new Point(leftUpPoint.x+(width/2) ,leftUpPoint.y+ height);
        westPoint = new Point(leftUpPoint.x, leftUpPoint.y+(height /2));

        rightDownPoint = new Point(leftUpPoint.x+width,leftUpPoint.y+ height);
        oriLeftUpPoint = new Point(leftUpPoint.x,leftUpPoint.y);
    }

    @Override
    protected void paintObject(Graphics g) {
        Point drawingPoint;
        if (this.getSelectedState()) {
            drawingPoint = leftUpPoint;
        }
        else {
            drawingPoint = oriLeftUpPoint;
        }
        g.setColor(Color.orange);
        g.fillOval(drawingPoint.x-2, drawingPoint.y-2, width+4, height +4); // Paint Background
        g.setColor(Color.BLACK);
        g.drawOval(drawingPoint.x, drawingPoint.y, width, height);
        g.drawOval(drawingPoint.x-2, drawingPoint.y-2, width+4, height +4);
        // Paint connection point
        if (getSelectedState()) {
            int connectionPointSize = 10;
            g.fillRect(northPoint.x-(connectionPointSize /2),northPoint.y- connectionPointSize, connectionPointSize, connectionPointSize);
            g.fillRect(eastPoint.x,eastPoint.y-(connectionPointSize /2), connectionPointSize, connectionPointSize);
            g.fillRect(southPoint.x-(connectionPointSize /2),southPoint.y, connectionPointSize, connectionPointSize);
            g.fillRect(westPoint.x- connectionPointSize,westPoint.y-(connectionPointSize /2), connectionPointSize, connectionPointSize);
        }
        g.drawString(text,drawingPoint.x+(width/4),drawingPoint.y+(height /2)); // paint String
    }

    @Override
    public void moveDrawableObj(int x_mov, int y_mov) {
        leftUpPoint.x = oriLeftUpPoint.x + x_mov;
        leftUpPoint.y = oriLeftUpPoint.y + y_mov;
        northPoint = new Point(leftUpPoint.x+(width/2), leftUpPoint.y);
        eastPoint = new Point(leftUpPoint.x+width, leftUpPoint.y+(height /2));
        southPoint = new Point(leftUpPoint.x+(width/2) ,leftUpPoint.y+ height);
        westPoint = new Point(leftUpPoint.x, leftUpPoint.y+(height /2));
    }

    @Override
    public void finishMovement() {
        oriLeftUpPoint = new Point(leftUpPoint.x,leftUpPoint.y);
        objArea = new Ellipse2D.Double(leftUpPoint.x-3, leftUpPoint.y-3, width+6, height +6);
        rightDownPoint = new Point(oriLeftUpPoint.x+width,oriLeftUpPoint.y+ height);
    }

    @Override
    public boolean checkPointOverlap(Point givenPoint) {
        return objArea.contains(givenPoint);
    }
    @Override
    public void checkHoleObjectOverlap(Shape givenShape) {
        if (givenShape.contains(this.getNorthPoint()) && givenShape.contains(this.getEastPoint()) && givenShape.contains(this.getSouthPoint()) && givenShape.contains(this.getWestPoint())) {
            this.setSelectedState(true);
        }
    }
    @Override
    public DrawableObject composeObj(){
        return this;
    }
}
