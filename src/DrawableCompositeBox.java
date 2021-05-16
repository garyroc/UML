import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawableCompositeBox extends DrawableObject {
    CompositeTypeObj belongedCompObj;
    private Rectangle2D objArea;
//    protected Point oriStartPoint;
    protected Point oriRightDownPoint;

    public DrawableCompositeBox(Point givenStartPoint, Point givenEndPoint, int givenDepth, CompositeTypeObj givenCompositeTypeObj) {
        super(givenStartPoint,givenDepth);
        text = "Composite";
        belongedCompObj = givenCompositeTypeObj;
        rightDownPoint = givenEndPoint;
        objArea = makeRectangle(leftUpPoint.x,leftUpPoint.y, rightDownPoint.x, rightDownPoint.y);
        oriLeftUpPoint = new Point(0,0);
        oriRightDownPoint = new Point(0,0);
        oriLeftUpPoint.x = leftUpPoint.x;
        oriLeftUpPoint.y = leftUpPoint.y;
        oriRightDownPoint.x = rightDownPoint.x;
        oriRightDownPoint.y = rightDownPoint.y;
    }

    @Override
    protected void paintObject(Graphics g) {
        if (isMoving) {
            objArea = makeRectangle(leftUpPoint.x,leftUpPoint.y, rightDownPoint.x, rightDownPoint.y);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.draw(objArea);
            g.drawString(text,leftUpPoint.x,leftUpPoint.y-10);
        }
        else {
            objArea = makeRectangle(oriLeftUpPoint.x,oriLeftUpPoint.y, oriRightDownPoint.x, oriRightDownPoint.y);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.draw(objArea);
            g.drawString(text,oriLeftUpPoint.x,oriLeftUpPoint.y-10);
        }
    }

    @Override
    public void moveDrawableObj(int x_mov, int y_mov) {
        if (isMoving) {
            leftUpPoint.x = oriLeftUpPoint.x + x_mov;
            leftUpPoint.y = oriLeftUpPoint.y + y_mov;
            rightDownPoint.x = oriRightDownPoint.x + x_mov;
            rightDownPoint.y = oriRightDownPoint.y + y_mov;
            objArea = makeRectangle(leftUpPoint.x,leftUpPoint.y, rightDownPoint.x, rightDownPoint.y);
        }
        else {
            oriLeftUpPoint = new Point(leftUpPoint.x, leftUpPoint.y);
            oriRightDownPoint = new Point(rightDownPoint.x, rightDownPoint.y);
            objArea = makeRectangle(oriLeftUpPoint.x,oriLeftUpPoint.y, oriRightDownPoint.x, oriRightDownPoint.y);
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
        if (givenShape.contains(this.getLeftUpPoint()) && givenShape.contains(this.getRightDownPoint())) {
            testResult = true;
        }
        return testResult;
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2)-6, Math.min(y1, y2)-6, Math.abs(x1 - x2)+10, Math.abs(y1 - y2)+10);
    }
}
