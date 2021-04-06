import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawableCompositeBox extends Drawable {
    Point boxEndPoint;
    CompositeTypeObj belongedCompObj;
    private Rectangle2D objArea;

    public DrawableCompositeBox(Point givenStartPoint, Point givenEndPoint, int givenDepth, CompositeTypeObj givenCompositeTypeObj) {
        super(givenStartPoint,givenDepth,false);
        belongedCompObj = givenCompositeTypeObj;
        endPoint = givenEndPoint;
//        objArea = new Rectangle(startPoint.x-1,startPoint.y-1, startPoint.x-endPoint.x+2, startPoint.y-endPoint.y+2);
        objArea = makeRectangle(startPoint.x,startPoint.y, endPoint.x, endPoint.y);
    }

    @Override
    protected void paintObject(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.draw(objArea);
    }

    @Override
    public void setSelectedState(boolean givenValue) {
        super.setSelectedState(givenValue);
        //還需要完成把下轄所有的drawable都設定為selected
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

    public Point getBoxEndPoint() { return boxEndPoint; }
}
