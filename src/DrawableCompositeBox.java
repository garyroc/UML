import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawableCompositeBox extends Drawable {
    Point boxEndPoint;
    CompositeTypeObj belongedCompObj;
    private Rectangle2D objArea;
    protected Point oriStartPoint;
    protected Point oriEndPoint;

    public DrawableCompositeBox(Point givenStartPoint, Point givenEndPoint, int givenDepth, CompositeTypeObj givenCompositeTypeObj) {
        super(givenStartPoint,givenDepth,false);
        text = "Composite";
        belongedCompObj = givenCompositeTypeObj;
        endPoint = givenEndPoint;
//        objArea = new Rectangle(startPoint.x-1,startPoint.y-1, startPoint.x-endPoint.x+2, startPoint.y-endPoint.y+2);
        objArea = makeRectangle(startPoint.x,startPoint.y, endPoint.x, endPoint.y);
        oriStartPoint = new Point(0,0);
        oriEndPoint = new Point(0,0);
        oriStartPoint.x = startPoint.x;
        oriStartPoint.y = startPoint.y;
        oriEndPoint.x = endPoint.x;
        oriEndPoint.y = endPoint.y;
    }

    @Override
    protected void paintObject(Graphics g) {
        if (isMoving) {
            objArea = makeRectangle(startPoint.x,startPoint.y, endPoint.x, endPoint.y);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.draw(objArea);
            g.drawString(text,startPoint.x,startPoint.y-10);
        }
        else {
            objArea = makeRectangle(oriStartPoint.x,oriStartPoint.y, oriEndPoint.x, oriEndPoint.y);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.draw(objArea);
            g.drawString(text,oriStartPoint.x,oriStartPoint.y-10);
        }
    }

    @Override
    public void moveDrawableObj(int x_mov, int y_mov) {
        if (isMoving) {
            startPoint.x = oriStartPoint.x + x_mov;
            startPoint.y = oriStartPoint.y + y_mov;
            endPoint.x = oriEndPoint.x + x_mov;
            endPoint.y = oriEndPoint.y + y_mov;
            objArea = makeRectangle(startPoint.x,startPoint.y, endPoint.x, endPoint.y);
        }
        else {
            oriStartPoint = new Point(startPoint.x, startPoint.y);
            oriEndPoint = new Point(endPoint.x, endPoint.y);
            objArea = makeRectangle(oriStartPoint.x,oriStartPoint.y, oriEndPoint.x, oriEndPoint.y);
        }
    }

    @Override
    public void setSelectedState(boolean givenValue) {
        selectedState = givenValue;
        belongedCompObj.setBelongObjSelected(givenValue);
    }

    @Override
    public boolean checkPointOverlap(Point givenPoint) {
        return objArea.contains(givenPoint);
    }
    @Override
    public boolean checkHoleObjectOverlap(Shape givenShape) {
        boolean testResult = false;
        if (givenShape.contains(this.getStartPoint()) && givenShape.contains(this.getRightDownPoint())) {
            testResult = true;
        }
        return testResult;
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2)-6, Math.min(y1, y2)-6, Math.abs(x1 - x2)+10, Math.abs(y1 - y2)+10);
    }

}
