import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class DrawController {
    private PAINT_TOOL currentState = PAINT_TOOL.THE_CLASSOBJECT;
    protected ArrayList<Drawable> drawingObjectList;

    private ArrayList<DrawableObject> drawnObjList = new ArrayList<DrawableObject>();
    private ArrayList<DrawableLine> drawnLineList = new ArrayList<DrawableLine>();

    private int mainDepth = 0;
    private boolean movingObj =  false;
    private MyCanvas theMainCanvas;

    public enum PAINT_TOOL {
        SELECT, ASSOCIATION_LINE, GENERALIZATION_LINE, COMPOSITION_LINE, THE_CLASSOBJECT, THE_USECASE
    }

    public DrawController() {

    }

    public void createDrawObj(Point givenStartPoint, Point givenEndPoint) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECT:
//                selectObj(givenStartPoint);
//                if (checkMovingObj()) {
//                    movingObj = true;
//                    setMovingObjMoving(true);
//                }
//                else {
//                    resultDrawObject = new DrawableSelectBox(givenStartPoint, mainDepth);
//                }
                break;
            case ASSOCIATION_LINE:
                resultDrawObject = new DrawableAssociationLine(givenStartPoint, givenEndPoint,mainDepth);
                resultDrawObject.setSelectedState(true);
                drawnLineList.add((DrawableLine) resultDrawObject);
                break;
            case GENERALIZATION_LINE:
                resultDrawObject = new DrawableGeneralizationLine(givenStartPoint, givenEndPoint,mainDepth);
                resultDrawObject.setSelectedState(true);
                drawnLineList.add((DrawableLine) resultDrawObject);
                break;
            case COMPOSITION_LINE:
                resultDrawObject = new DrawableCompositionLine(givenStartPoint, givenEndPoint,mainDepth);
                resultDrawObject.setSelectedState(true);
                drawnLineList.add((DrawableLine) resultDrawObject);
                break;
            case THE_CLASSOBJECT:
                resultDrawObject = new DrawableClass(givenStartPoint, mainDepth);
                drawnObjList.add((DrawableObject) resultDrawObject);
                break;
            case THE_USECASE:
                resultDrawObject = new DrawableUseCase(givenStartPoint, mainDepth);
                drawnObjList.add((DrawableObject) resultDrawObject);
                break;
        }
        if(resultDrawObject != null ) {
            mainDepth++;
        }
        if (currentState == PAINT_TOOL.ASSOCIATION_LINE || currentState == PAINT_TOOL.GENERALIZATION_LINE || currentState == PAINT_TOOL.COMPOSITION_LINE) {
            lineCreateCheck((DrawableLine) resultDrawObject);
        }
    }

    /* For mouse drag duration */
    public void detectMouseDrag(Point givenStartPoint, Point givenEndPoint) {
        switch (currentState) {
            case SELECT:
                break;
            case ASSOCIATION_LINE:
            case GENERALIZATION_LINE:
            case COMPOSITION_LINE:
                if (drawnLineList.size() > 0) {
                    DrawableLine lineObj = drawnLineList.get(drawnLineList.size()-1); // get last line object
                    if (lineObj.getSelectedState()) {
                        lineObj.setEndPoint(givenEndPoint);
                    }
                }
                break;
            case THE_CLASSOBJECT:
            case THE_USECASE:
                break;
        }
    }

    /* Call when mouse release */
    public void checkDrawObj(Point givenStartPoint, Point givenEndPoint) {
        switch (currentState) {
            case SELECT:
                break;
            case ASSOCIATION_LINE:
            case GENERALIZATION_LINE:
            case COMPOSITION_LINE:
                if (drawnLineList.size() > 0) {
                    DrawableLine lineObj = drawnLineList.get(drawnLineList.size()-1); // get last line object
                    if (lineObj.getSelectedState()) {
                        lineFinishCheck(lineObj);
                    }
                }
                break;
            case THE_CLASSOBJECT:
            case THE_USECASE:
                break;
        }
    }

    public void changePaintTool(PAINT_TOOL givenPaintTool) {
        currentState = givenPaintTool;
    }

    public ArrayList<Drawable> getDrawingList() {
        drawingObjectList = new ArrayList<Drawable>();
        drawingObjectList.addAll(drawnObjList);
        drawingObjectList.addAll(drawnLineList);
        Collections.sort(drawingObjectList);
        return drawingObjectList;
    }

    private void createDrawingList() {
        drawingObjectList = new ArrayList<Drawable>();
        drawingObjectList.addAll(drawnObjList);
        drawingObjectList.addAll(drawnLineList);
    }

    private void lineCreateCheck(DrawableLine givenLineObj) {
        if (!(givenLineObj.starPointCheck(drawnObjList))) {
            drawnLineList.remove(givenLineObj); // delete last line obj, if it should not exist
        }
    }

    private void lineFinishCheck(DrawableLine givenLineObj) {
        if (givenLineObj.endPointCheck(drawnObjList)) {
            givenLineObj.setSelectedState(false);
        }
        else {
            drawnLineList.remove(givenLineObj); // delete last line obj, if it should not exist
        }
    }

    private void selectObj(Point givenPoint) {

    }

    private void selectGroupOfObj(Point givenStartPoint , Point givenEndPoint) {

    }

    private boolean checkMovingObj() {
        boolean testResult = false;
//        createDrawingList(mainCompositeTree);
//        for(Drawable drawObj : drawingObjectList) {
//            if (drawObj.getSelectedState()) {
//                testResult = true;
//            }
//        }
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

    }

    public void unGroupObj() {
//        ArrayList<CompositeProtocol> beenSelectedList = new ArrayList<CompositeProtocol>();
//        Drawable drawableObj;
//        for (CompositeProtocol treeMember : mainCompositeTree) {
//            if (treeMember.myType == CompositeProtocol.OBJ_TYPE.COMPOSITE_OBJ) {
//                /* Find Selected group */
//                drawableObj = ((CompositeTypeObj)treeMember).getRepresentDrawableObj();
//                if (drawableObj.getSelectedState()) {
//                    beenSelectedList.add(treeMember);
//                }
//            }
//        }
//        /* Remove selected object from mainCompositeTree */
//        for (CompositeProtocol removeObj : beenSelectedList) {
//            mainCompositeTree.remove(removeObj);
//            mainCompositeTree.addAll(removeObj.decompose(removeObj));
//            ((CompositeTypeObj)removeObj).setRepresentDrawableObj(null);
//        }
    }

    private ArrayList<Drawable> getSelectedObj() {

        return null;
    }

    private void setMovingObjMoving(boolean givenValue) {
        ArrayList<Drawable> movingObjList = getSelectedObj();
        for (Drawable movingObj : movingObjList) {
            movingObj.setIsMoving(givenValue);
        }
    }

    private void movingObject(Point givenStartPoint, Point givenEndPoint) {
        ArrayList<Drawable> movingObjList = getSelectedObj();
        int movementDistance_X = givenEndPoint.x - givenStartPoint.x;
        int movementDistance_Y = givenEndPoint.y - givenStartPoint.y;
        for (Drawable movingObj : movingObjList) {
            movingObj.moveDrawableObj(movementDistance_X,movementDistance_Y);
        }
    }

    private void updateAllLineConnection(){
//        DrawableLine updatingLineObj;
//        Drawable connectTargetObj;
//        createDrawingList(mainCompositeTree);
//        for (Drawable drawObj : drawingObjectList) {
//            if (drawObj.isLineObj()) {
//                updatingLineObj = (DrawableLine) drawObj;
//                connectTargetObj = updatingLineObj.getStartPointObject();
//                updatingLineObj.setStartPoint(connectTargetObj.getConnectedPoint(updatingLineObj.getStartPointPosition()));
//                connectTargetObj = updatingLineObj.getEndPointObject();
//                updatingLineObj.setEndPoint(connectTargetObj.getConnectedPoint(updatingLineObj.getEndPointPosition()));
//            }
//        }
    }

    public void reNameFunction(String givenName) {
        ArrayList<Drawable> selectedObjList = getSelectedObj();
        if (selectedObjList.size() == 1) {
            Drawable renamingObj = selectedObjList.get(0);
            renamingObj.setText(givenName);
        }
    }

    public void refreshCanvas() {
        theMainCanvas.updateCanvas();
    }

    public void setMainCanvas(MyCanvas givenCanvas) {
        theMainCanvas = givenCanvas;
    }
}
