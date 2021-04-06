import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DrawController {
    private PAINT_TOOL currentState = PAINT_TOOL.THE_CLASSOBJECT;
    protected ArrayList<CompositeProtocol> mainCompositeTree = new ArrayList<CompositeProtocol>();
    protected ArrayList<Drawable> drawingObjectList;
    private int mainDepth = 0;

    private boolean movingObj =  false;
//    private boolean selectet

    public enum PAINT_TOOL {
        SELECET, ASSOCIATION_LINE, GENERALIZATION_LINE, COMPOSITION_LINE, THE_CLASSOBJECT, THE_USECASE
    }

    public DrawController() {

    }

    public void createDrawOject(Point givenStartPoint, Point givenEndPoint) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECET:
                selectObj(givenStartPoint);
                if (checkMovingObj()) {
                    movingObj = true;
                }
                else {
                    resultDrawObject = new DrawableSelectBox(givenStartPoint, mainDepth);
                }
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
        if(resultDrawObject != null ) {
            mainCompositeTree.add(resultDrawObject);
            mainDepth++;
        }

        if (currentState == PAINT_TOOL.ASSOCIATION_LINE || currentState == PAINT_TOOL.GENERALIZATION_LINE || currentState == PAINT_TOOL.COMPOSITION_LINE) {
            determineLineExistOrNot(resultDrawObject,givenEndPoint);
        }
    }

    /*For mouse drag duration*/
    public void createDrawOject(Point givenStartPoint, Point givenEndPoint, boolean isMouseDrage) {
        Drawable resultDrawObject = null;
        if ((mainCompositeTree.get(mainCompositeTree.size()-1).myType == CompositeProtocol.OBJ_TYPE.DRAWABLE_OBJ)){
            resultDrawObject = (Drawable) mainCompositeTree.get(mainCompositeTree.size()-1);
        }
        if (resultDrawObject != null ) {
            switch (currentState) {
                case SELECET:
                    if (!movingObj) {
                        ((DrawableSelectBox)resultDrawObject).updateWidthAndHeigh(givenEndPoint);
                    }

                    break;
                case THE_CLASSOBJECT:
                    break;
                case THE_USECASE:
                    break;
                default:
//                resultDrawObject = (Drawable) mainCompositeTree.get(mainCompositeTree.size()-1);
                    if (resultDrawObject.isLineObj) {
                        if ((resultDrawObject).getSelectedState()) {
                            ((DrawableLine) resultDrawObject).setEndPoint(givenEndPoint);
                        }
                    }
                    break;
            }
        }
    }

    /* Call when mouse release */
    public void checkDrawObj(Point givenStartPoint, Point givenEndPoint) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECET:
                if(movingObj) {

                }
                else {
                    selectGroupOfObj(givenStartPoint, givenEndPoint);
                    mainCompositeTree.remove((mainCompositeTree.size()-1));

                }
                movingObj = false;
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
                drawingObjectList.add(((CompositeTypeObj) treeMember).getRepresentDrawableObj());
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
                drawingObjectList.add(((CompositeTypeObj) traversingTreeMember).getRepresentDrawableObj());
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
                if (testingObj.checkPointOverlap(givenEndPoint)) {
                    endPointConnectObj = testingObj;
                }
                if (testingObj.checkPointOverlap(underTestLine.startPoint)) {
                    startPointConnectObj = testingObj;
                }
            }
        }

        if (endPointConnectObj == null || endPointConnectObj.isLineObj()) {
            mainCompositeTree.remove((mainCompositeTree.size()-1));
        }
        else {
            /* connect line to the object connect point*/
            Point startPoint = underTestLine.getStartPoint();
            double leastDistance = 999.00d;
            Point leastDistancePoint = null;
            ArrayList<Point> testingPointList = new ArrayList<Point>();
            testingPointList.add(endPointConnectObj.northPoint);
            testingPointList.add(endPointConnectObj.eastPoint);
            testingPointList.add(endPointConnectObj.southPoint);
            testingPointList.add(endPointConnectObj.westPoint);

            if (startPoint.equals(givenEndPoint)) {
                /*
                * The line first created.
                * */
                ((DrawableLine)underTestLine).setStartPointObject(endPointConnectObj);
                ((DrawableLine)underTestLine).setEndPointObject(endPointConnectObj);
                int i = 0; //iterator
                Drawable.CONNECT_POSITION resultPosition = null;
                for (Drawable.CONNECT_POSITION position : Drawable.CONNECT_POSITION.values()) {
                    if ( testingPointList.get(i).distance(givenEndPoint) < leastDistance) {
                        leastDistance = testingPointList.get(i).distance(givenEndPoint);
                        leastDistancePoint = testingPointList.get(i);
                        resultPosition = position;
                    }
                    i++;
                }
                ((DrawableLine)underTestLine).setEndPointPosition(resultPosition);
                ((DrawableLine)underTestLine).setStartPointPosition(resultPosition);
                underTestLine.setStartPoint(leastDistancePoint);
                ((DrawableLine)underTestLine).setEndPoint(leastDistancePoint);
            }
            else {
                /*
                * The line have finished.
                * */
                if (startPointConnectObj.equals(endPointConnectObj)) {
                    mainCompositeTree.remove((mainCompositeTree.size()-1));
                }
                else {
                    ((DrawableLine)underTestLine).setEndPointObject(endPointConnectObj);
                    int i = 0; //iterator
                    Drawable.CONNECT_POSITION resultPosition = null;
                    for (Drawable.CONNECT_POSITION position : Drawable.CONNECT_POSITION.values()) {
                        if ( testingPointList.get(i).distance(givenEndPoint) < leastDistance) {
                            leastDistance = testingPointList.get(i).distance(givenEndPoint);
                            leastDistancePoint = testingPointList.get(i);
                            resultPosition = position;
                        }
                        i++;
                    }

                    ((DrawableLine)underTestLine).setEndPointPosition(resultPosition);
                    ((DrawableLine)underTestLine).setEndPoint(leastDistancePoint);
                }
            }
        }
    }

    private void selectObj(Point givenPoint) {
        createDrawingList(mainCompositeTree);
        Drawable drawObj_d;
//        for(Drawable drawObj : drawingObjectList) {
//            drawObj.setSelectedState(false);
//            if (drawObj.checkPointOverlap(givenPoint)) {
//                drawObj.setSelectedState(true);
//            }
//        }

        for (CompositeProtocol drawObj : mainCompositeTree) {
            if(drawObj.myType == CompositeProtocol.OBJ_TYPE.DRAWABLE_OBJ) {
                drawObj_d = (Drawable) drawObj;
            }
            else {
                drawObj_d = ((CompositeTypeObj)drawObj).getRepresentDrawableObj();
            }
            if (!drawObj_d.isLineObj()) {
                drawObj_d.setSelectedState(false);
                if (drawObj_d.checkPointOverlap(givenPoint)) {
                    drawObj_d.setSelectedState(true);
                }
            }
        }

    }

    private void selectGroupOfObj(Point givenStartPoint , Point givenEndPoint) {
        Shape selectingShape = ((DrawableSelectBox)mainCompositeTree.get((mainCompositeTree.size()-1))).getRect();
        Drawable drawObj_d;
        for (CompositeProtocol drawObj : mainCompositeTree) {
            if (drawObj.myType == CompositeProtocol.OBJ_TYPE.DRAWABLE_OBJ) {
                /* For Drawing object */
                drawObj_d = (Drawable) drawObj;
            }
            else {
                drawObj_d = ((CompositeTypeObj)drawObj).getRepresentDrawableObj();
            }
            if (!drawObj_d.isLineObj()) {
                drawObj_d.setSelectedState(false);
                if (drawObj_d.checkHoleObjectOverlap(selectingShape)) {
                    drawObj_d.setSelectedState(true);
                }
            }
        }
    }

    private boolean checkMovingObj() {
        boolean testResult = false;
        createDrawingList(mainCompositeTree);
        for(Drawable drawObj : drawingObjectList) {
            if (drawObj.getSelectedState()) {
                testResult = true;
            }
        }
        return testResult;
    }

    private ArrayList<CompositeProtocol> newTraversalCompositeTree (ArrayList<CompositeProtocol> givenCompositeTree) {
        ArrayList<CompositeProtocol> resultList = new ArrayList<CompositeProtocol>();
        ArrayList<CompositeProtocol> tempList;
        for (CompositeProtocol traversingTreeMember : givenCompositeTree) {
            if (traversingTreeMember.myType == CompositeProtocol.OBJ_TYPE.COMPOSITE_OBJ) {
                tempList = newTraversalCompositeTree(((CompositeTypeObj) traversingTreeMember).getTheCompositeList());
                resultList.addAll(tempList);
            }
            else {
                resultList.add(traversingTreeMember);
            }
        }
        return resultList;
    }

    public void groupObj() {
        ArrayList<CompositeProtocol> beenSelectedList = new ArrayList<CompositeProtocol>();
        Drawable drawableObj;
        Drawable drawingCompositeBox;
        for (CompositeProtocol treeMember : mainCompositeTree) {
            if (treeMember.myType == CompositeProtocol.OBJ_TYPE.DRAWABLE_OBJ) {
                drawableObj = (Drawable) treeMember;
            }
            else {
                drawableObj = ((CompositeTypeObj) treeMember).getRepresentDrawableObj();
            }
            if (drawableObj.getSelectedState()) { //if have been selected then add into list.
                beenSelectedList.add(treeMember);
            }
        }
        if (!beenSelectedList.isEmpty() && beenSelectedList.size() > 1) {
            /* Remove selected object from mainCompositeTree */
            for (CompositeProtocol removeObj : beenSelectedList) {
                mainCompositeTree.remove(removeObj);
            }
            /* Create new CompositeTree Object */
            CompositeProtocol resultNewObj = new CompositeTypeObj(beenSelectedList);
            mainCompositeTree.add(resultNewObj);
            Point boxStartPoint = ((CompositeTypeObj)resultNewObj).getLeftUpMostPoint();
            Point boxEndPoint = ((CompositeTypeObj)resultNewObj).getRightDownMostPoint();
            drawingCompositeBox = new DrawableCompositeBox(boxStartPoint,boxEndPoint,mainDepth, (CompositeTypeObj) resultNewObj);
            ((CompositeTypeObj) resultNewObj).setRepresentDrawableObj(drawingCompositeBox);

            mainDepth++;
        }
    }

    public void unGroupObj() {
        ArrayList<CompositeProtocol> beenSelectedList = new ArrayList<CompositeProtocol>();
        Drawable drawableObj;
        Drawable drawingCompositeBox;
        for (CompositeProtocol treeMember : mainCompositeTree) {
            if (treeMember.myType == CompositeProtocol.OBJ_TYPE.COMPOSITE_OBJ) {
                drawableObj = ((CompositeTypeObj)treeMember).getRepresentDrawableObj();
                if (drawableObj.getSelectedState()) {
                    beenSelectedList.add(treeMember);
                }
            }
        }
            /* Remove selected object from mainCompositeTree */
            for (CompositeProtocol removeObj : beenSelectedList) {
                mainCompositeTree.remove(removeObj);
                mainCompositeTree.addAll(removeObj.decompose(removeObj));
                ((CompositeTypeObj)removeObj).setRepresentDrawableObj(null);
            }
            /* Create new CompositeTree Object */
    }

    private void updateAllLineConnection(){
        DrawableLine updatingLineObj;
        Drawable connectTargetObj;
        for (Drawable drawObj : drawingObjectList) {
            if (drawObj.isLineObj()) {
                updatingLineObj = (DrawableLine) drawObj;
                connectTargetObj = updatingLineObj.getStartPointObject();
                updatingLineObj.setStartPoint(connectTargetObj.getConnectedPoint(updatingLineObj.getStartPointPosition()));
                connectTargetObj = updatingLineObj.getEndPointObject();
                updatingLineObj.setEndPoint(connectTargetObj.getConnectedPoint(updatingLineObj.getEndPointPosition()));
            }
        }
    }

}
