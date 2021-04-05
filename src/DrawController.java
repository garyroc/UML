import java.awt.*;
import java.util.ArrayList;

public class DrawController {
    private PAINT_TOOL currentState = PAINT_TOOL.THE_CLASSOBJECT;
    protected ArrayList<CompositeProtocol> mainCompositeTree = new ArrayList<CompositeProtocol>();
    protected ArrayList<Drawable> drawingObjectList;
    private int mainDepth = 0;

    public enum PAINT_TOOL {
        SELECET, ASSOCIATION_LINE, GENERALIZATION_LINE, COMPOSITION_LINE, THE_CLASSOBJECT, THE_USECASE
    }

    public DrawController() {

    }

    public void createDrawOject(Point givenStartPoint, Point givenEndPoint) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECET:

                break;
            case ASSOCIATION_LINE:
                resultDrawObject = new DrawableAssociationLine(givenStartPoint, givenEndPoint,mainDepth);
                resultDrawObject.setSelectedState(true);
                break;
            case GENERALIZATION_LINE:
                resultDrawObject = new DrawableGeneralizationLine(givenStartPoint, givenEndPoint,mainDepth);
                resultDrawObject.setSelectedState(true);
                break;
            case COMPOSITION_LINE:
                resultDrawObject = new DrawableCompositionLine(givenStartPoint, givenEndPoint,mainDepth);
                resultDrawObject.setSelectedState(true);
                break;
            case THE_CLASSOBJECT:
                resultDrawObject = new DrawableClass(givenStartPoint, mainDepth);
                break;
            case THE_USECASE:
                resultDrawObject = new DrawableUseCase(givenStartPoint, mainDepth);
                break;
        }
        mainCompositeTree.add(resultDrawObject);
        mainDepth++;

        if (currentState == PAINT_TOOL.ASSOCIATION_LINE || currentState == PAINT_TOOL.GENERALIZATION_LINE || currentState == PAINT_TOOL.COMPOSITION_LINE) {
            determineLineExistOrNot(resultDrawObject,givenEndPoint);
        }
    }

    /*For mouse drag duration*/
    public void createDrawOject(Point givenStartPoint, Point givenEndPoint, boolean isMouseDrage) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECET:
                break;
            case THE_CLASSOBJECT:
                break;
            case THE_USECASE:
                break;
            default:
                resultDrawObject = (Drawable) mainCompositeTree.get(mainCompositeTree.size()-1);
                if (resultDrawObject.isLineObj) {
                    if ((resultDrawObject).getSelectedState()) {
                        ((DrawableLine) resultDrawObject).setEndPoint(givenEndPoint);
                    }
                }
                break;
        }
    }

    /* Call when mouse release */
    public void checkDrawObj(Point givenStartPoint, Point givenEndPoint) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECET:

                break;
            case THE_CLASSOBJECT:
                break;
            case THE_USECASE:
                break;
            default:
                resultDrawObject = (Drawable) mainCompositeTree.get(mainCompositeTree.size()-1);
                if (resultDrawObject.isLineObj && resultDrawObject.getSelectedState()) {
                    resultDrawObject.setSelectedState(false);
                    determineLineExistOrNot(resultDrawObject,givenEndPoint);
                }
                break;
        }

    }

    public void changePaintTool(PAINT_TOOL givenPaintTool) {
        currentState = givenPaintTool;
    }

    public ArrayList<Drawable> getDrawingList() {
        createDrawingList(mainCompositeTree);
        return drawingObjectList;
    }

    private void createDrawingList(ArrayList<CompositeProtocol> compositeTree) {
        drawingObjectList = new ArrayList<Drawable>();
        for (CompositeProtocol treeMember : compositeTree) {
            if (treeMember.myType == CompositeProtocol.OBJ_TYPE.COMPOSITE_OBJ) {
                traversalCompositeTree(((CompositeTypeObj)treeMember).getTheCompositeList());
            }
            else {
                drawingObjectList.add((Drawable) treeMember);
            }
        }
    }

    private void traversalCompositeTree (ArrayList<CompositeProtocol> compositeTree) {
        for (CompositeProtocol traversingTreeMember : compositeTree) {
            if (traversingTreeMember.myType == CompositeProtocol.OBJ_TYPE.COMPOSITE_OBJ) {
                traversalCompositeTree(((CompositeTypeObj)traversingTreeMember).getTheCompositeList());
            }
            else {
                drawingObjectList.add((Drawable) traversingTreeMember);
            }
        }
    }

    private void determineLineExistOrNot (Drawable underTestLine, Point givenEndPoint) {
        Drawable endPointConnectObj = null;
        Drawable startPointConnectObj = null;
        createDrawingList(mainCompositeTree);
        for ( Drawable testingObj : drawingObjectList) {
            if (!testingObj.isLineObj()) {
                if (testingObj.checkOverlap(givenEndPoint)) {
                    endPointConnectObj = testingObj;
                }
                if (testingObj.checkOverlap(underTestLine.startPoint)) {
                    startPointConnectObj = testingObj;
                }
            }
        }

        if (endPointConnectObj == null) {
            mainCompositeTree.remove((mainCompositeTree.size()-1));
        }
        else {
            /* connect line to the object connect point*/
            Point startPoint = underTestLine.getStartPoint();
            double leastDistance = 999.00d;
            Point leastDistancePoint = null;
            ArrayList<Point> testingPointList = new ArrayList<Point>();;
            testingPointList.add(endPointConnectObj.northPoint);
            testingPointList.add(endPointConnectObj.eastPoint);
            testingPointList.add(endPointConnectObj.southPoint);
            testingPointList.add(endPointConnectObj.westPoint);
            if (startPoint.equals(givenEndPoint)) {
                for (int i=0; i<4; i++) {
                    if ( testingPointList.get(i).distance(givenEndPoint) < leastDistance) {
                        leastDistance = testingPointList.get(i).distance(givenEndPoint);
                        leastDistancePoint = testingPointList.get(i);
                    }
                }
                underTestLine.setStartPoint(leastDistancePoint);
                ((DrawableLine)underTestLine).setEndPoint(leastDistancePoint);
            }
            else {
                if (startPointConnectObj.equals(endPointConnectObj)) {
                    mainCompositeTree.remove((mainCompositeTree.size()-1));
                }
                else {
                    for (int i=0; i<4; i++) {
                        if ( testingPointList.get(i).distance(givenEndPoint) < leastDistance) {
                            leastDistance = testingPointList.get(i).distance(givenEndPoint);
                            leastDistancePoint = testingPointList.get(i);
                        }
                    }
                    ((DrawableLine)underTestLine).setEndPoint(leastDistancePoint);
                }
            }
        }
    }

}
