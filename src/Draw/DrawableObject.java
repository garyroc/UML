package Draw;

import java.awt.*;
import java.util.ArrayList;

public class DrawableObject extends Drawable implements CompositePortocolInterface {
    protected Point leftUpPoint;
    protected Point oriLeftUpPoint;
    protected Point rightDownPoint;
    /* DrawableObj Connection point declare */
    protected Point northPoint = null;
    protected Point eastPoint = null;
    protected Point southPoint = null;
    protected Point westPoint = null;

    public DrawableObject() {

    }

    public DrawableObject(Point givenPoint, int givenDepth) {
        super(givenDepth);
        leftUpPoint = givenPoint;
    }

    public Point getConnectedPoint(CONNECT_POSITION givenPosition) {
        return switch (givenPosition) {
            case NORTH -> getNorthPoint();
            case EAST -> getEastPoint();
            case SOUTH -> getSouthPoint();
            case WEST -> getWestPoint();
        };
    }

    public Point getLeftUpPoint() { return leftUpPoint; }
    public Point getRightDownPoint() { return rightDownPoint; }
    public Point getNorthPoint() { return northPoint; }
    public Point getEastPoint() { return eastPoint; }
    public Point getSouthPoint() { return southPoint; }
    public Point getWestPoint() {return westPoint; }

    public LineConnectionPackage isConnectToObj(Point givenPoint) {
        CONNECT_POSITION resultPosition = null;
        if (checkPointOverlap(givenPoint)) {
            /* Calculate nearest connect position */
            double closestDistance = 9999999999999999.9; // give a very big number
            for (CONNECT_POSITION position : CONNECT_POSITION.values()) {
                switch (position) {
                    case NORTH:
                        if (northPoint.distance(givenPoint) < closestDistance) {
                            closestDistance = northPoint.distance(givenPoint);
                            resultPosition = CONNECT_POSITION.NORTH;
                        }
                    case EAST:
                        if (eastPoint.distance(givenPoint) < closestDistance) {
                            closestDistance = eastPoint.distance(givenPoint);
                            resultPosition = CONNECT_POSITION.EAST;
                        }
                    case SOUTH:
                        if (southPoint.distance(givenPoint) < closestDistance) {
                            closestDistance = southPoint.distance(givenPoint);
                            resultPosition = CONNECT_POSITION.SOUTH;
                        }
                    case WEST:
                        if (westPoint.distance(givenPoint) < closestDistance) {
                            closestDistance = westPoint.distance(givenPoint);
                            resultPosition = CONNECT_POSITION.WEST;
                        }
                }
            }
        }
        return new LineConnectionPackage(this,resultPosition); //if is overlap give closest point otherwise give null
    }

    public void updateDrawableObj(Point givenNewPoint) {    }


    public DrawableObject composeObj(){
        return null;
    }
    public ArrayList<DrawableObject> deComposeObj() {
        return null;
    }
}
