import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DrawableCompositeBox extends DrawableObject {
    private Rectangle2D objArea;
    protected Point oriRightDownPoint;
    protected ArrayList<DrawableObject> compositeDrawObjList;

    public DrawableCompositeBox(Point givenStartPoint, Point givenEndPoint, int givenDepth, CompositeTypeObj givenCompositeTypeObj) {
        super(givenStartPoint,givenDepth);
        text = "Composite";
        rightDownPoint = givenEndPoint;
        objArea = makeRectangle(leftUpPoint.x,leftUpPoint.y, rightDownPoint.x, rightDownPoint.y);
        oriLeftUpPoint = new Point(0,0);
        oriRightDownPoint = new Point(0,0);
        oriLeftUpPoint.x = leftUpPoint.x;
        oriLeftUpPoint.y = leftUpPoint.y;
        oriRightDownPoint.x = rightDownPoint.x;
        oriRightDownPoint.y = rightDownPoint.y;
    }

    public DrawableCompositeBox(ArrayList<DrawableObject> givenDrawObjList) {
        text = "Composite";
        initialCompositeDrawObj(givenDrawObjList);
        leftUpPoint = getChildLeftUpPoint();
        rightDownPoint = getChildRightDownPoint();
        objArea = makeRectangle(leftUpPoint.x,leftUpPoint.y, rightDownPoint.x, rightDownPoint.y);
        oriLeftUpPoint = new Point(leftUpPoint.x,leftUpPoint.y);
        oriRightDownPoint = new Point(rightDownPoint.x,rightDownPoint.y);
    }

    private void initialCompositeDrawObj(ArrayList<DrawableObject> givenDrawObjList){
        compositeDrawObjList = new ArrayList<>();
        for (DrawableObject drawObj : givenDrawObjList) {
            if (drawObj.getSelectedState()) {
                compositeDrawObjList.add(drawObj.composeObj());
            }
        }
        /* In order to avoid ConcurrentModificationException */
        for (DrawableObject drawObj : compositeDrawObjList) {
            givenDrawObjList.remove(drawObj);
        }
    }

    private Point getChildLeftUpPoint() {
        int leftUpX = 99999999;
        int leftUpY = 99999999;
        for (DrawableObject drawObj : compositeDrawObjList) {
            leftUpX = Math.min(leftUpX, drawObj.getLeftUpPoint().x);
            leftUpY = Math.min(leftUpY, drawObj.getLeftUpPoint().y);
        }
        return new Point(leftUpX,leftUpY);
    }

    private Point getChildRightDownPoint() {
        int rightDownX = 0;
        int rightDownY = 0;
        for (DrawableObject drawObj : compositeDrawObjList) {
            rightDownX = Math.max(rightDownX, drawObj.getRightDownPoint().x);
            rightDownY = Math.max(rightDownY, drawObj.getRightDownPoint().y);
        }
        return new Point(rightDownX,rightDownY);
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
        /*  Paint child */
        for (DrawableObject drawObj : compositeDrawObjList) {
            drawObj.paintObject(g);
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
        /* Set Child selected */
        for (DrawableObject drawObj : compositeDrawObjList) {
            drawObj.setSelectedState(givenValue);
        }
    }

    public CONNECT_POSITION isConnectToObj(Point givenPoint) { return null; }

    @Override
    public boolean checkPointOverlap(Point givenPoint) {
        return objArea.contains(givenPoint);
    }
    @Override
    public boolean checkHoleObjectOverlap(Shape givenShape) {
        if (givenShape.contains(leftUpPoint) && givenShape.contains(rightDownPoint)) {
            this.setSelectedState(true);
            return true;
        }
        return false;
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2)-6, Math.min(y1, y2)-6, Math.abs(x1 - x2)+10, Math.abs(y1 - y2)+10);
    }

    public int getChildrenSize() {
        return compositeDrawObjList.size();
    }

    public DrawableObject composeObj(){
        return this;
    }

    public ArrayList<DrawableObject> deComposeObj() {
        return compositeDrawObjList;
    }
}
