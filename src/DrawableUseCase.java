import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DrawableUseCase extends DrawableObject{
    private int width = 100;
    private int height = 50;
    private int connectionPointWidth = 10;
    private int connectionPointOffset = 7;
//    protected Point oriStartPoint;
    private Ellipse2D.Double objArea;

    public DrawableUseCase(Point givenPoint, int givenDepth) {
        super(givenPoint,givenDepth);
        text = "Use Case";
        objArea = new Ellipse2D.Double(leftUpPoint.x-3, leftUpPoint.y-3, width+6, height +6);
        northPoint = new Point(givenPoint.x+(width/2), givenPoint.y);
        eastPoint = new Point(givenPoint.x+width, givenPoint.y+(height /2));
        southPoint = new Point(givenPoint.x+(width/2) ,givenPoint.y+ height);
        westPoint = new Point(givenPoint.x, givenPoint.y+(height /2));

        rightDownPoint = new Point(leftUpPoint.x+width,leftUpPoint.y+ height);
        oriLeftUpPoint = new Point(0,0);
        oriLeftUpPoint.x = leftUpPoint.x;
        oriLeftUpPoint.y = leftUpPoint.y;
    }

    @Override
    protected void paintObject(Graphics g) {
        if (isMoving) {
            g.setColor(Color.orange);
            g.fillOval(leftUpPoint.x-2, leftUpPoint.y-2, width+4, height +4);
            g.setColor(Color.BLACK);
            g.drawOval(leftUpPoint.x, leftUpPoint.y, width, height);
            g.drawOval(leftUpPoint.x-2, leftUpPoint.y-2, width+4, height +4);
            if (getSelectedState()) {
                g.fillRect(northPoint.x-(connectionPointWidth/2),northPoint.y-connectionPointWidth,connectionPointWidth,connectionPointWidth);
                g.fillRect(eastPoint.x,eastPoint.y-(connectionPointWidth/2),connectionPointWidth,connectionPointWidth);
                g.fillRect(southPoint.x-(connectionPointWidth/2),southPoint.y,connectionPointWidth,connectionPointWidth);
                g.fillRect(westPoint.x-connectionPointWidth,westPoint.y-(connectionPointWidth/2),connectionPointWidth,connectionPointWidth);
            }
            g.drawString(text,leftUpPoint.x+(width/4),leftUpPoint.y+(height /2));
        }
        else {
            g.setColor(Color.orange);
            g.fillOval(leftUpPoint.x-2, leftUpPoint.y-2, width+4, height +4);
            g.setColor(Color.BLACK);
            g.drawOval(oriLeftUpPoint.x, oriLeftUpPoint.y, width, height);
            g.drawOval(oriLeftUpPoint.x-2, oriLeftUpPoint.y-2, width+4, height +4);
            if (getSelectedState()) {
                g.fillRect(northPoint.x-(connectionPointWidth/2),northPoint.y-connectionPointWidth,connectionPointWidth,connectionPointWidth);
                g.fillRect(eastPoint.x,eastPoint.y-(connectionPointWidth/2),connectionPointWidth,connectionPointWidth);
                g.fillRect(southPoint.x-(connectionPointWidth/2),southPoint.y,connectionPointWidth,connectionPointWidth);
                g.fillRect(westPoint.x-connectionPointWidth,westPoint.y-(connectionPointWidth/2),connectionPointWidth,connectionPointWidth);
            }
            g.drawString(text,oriLeftUpPoint.x+(width/4),oriLeftUpPoint.y+(height /2));
        }

    }

    @Override
    public void moveDrawableObj(int x_mov, int y_mov) {
        if (isMoving) {
            leftUpPoint.x = oriLeftUpPoint.x+x_mov;
            leftUpPoint.y = oriLeftUpPoint.y+y_mov;
            objArea = new Ellipse2D.Double(leftUpPoint.x-3, leftUpPoint.y-3, width+6, height +6);
            northPoint = new Point(leftUpPoint.x+(width/2), leftUpPoint.y);
            eastPoint = new Point(leftUpPoint.x+width, leftUpPoint.y+(height /2));
            southPoint = new Point(leftUpPoint.x+(width/2) ,leftUpPoint.y+ height);
            westPoint = new Point(leftUpPoint.x, leftUpPoint.y+(height /2));
        }
        else {
            oriLeftUpPoint.x = leftUpPoint.x;
            oriLeftUpPoint.y = leftUpPoint.y;
            rightDownPoint = new Point(oriLeftUpPoint.x+width,oriLeftUpPoint.y+ height);
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
            return true;
        }
        return false;
    }
}
