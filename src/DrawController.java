import java.awt.*;
import java.awt.geom.Rectangle2D;
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

    public DrawController() {    }

    public void createDrawObj(Point givenStartPoint, Point givenEndPoint) {
        DrawableObject resultDrawObject = null;
        switch (currentState) {
            case SELECT:
                selectObj(givenStartPoint);
                if (!(isMoveObj())) {
                    resultDrawObject = new DrawableSelectBox(givenStartPoint,mainDepth);
                    drawnObjList.add(resultDrawObject);
                }
                break;
            case ASSOCIATION_LINE:
                drawnLineList.add(new DrawableAssociationLine(givenStartPoint, givenEndPoint,mainDepth));
                lineCreateCheck(drawnLineList.get(drawnLineList.size()-1));
                break;
            case GENERALIZATION_LINE:
                drawnLineList.add(new DrawableGeneralizationLine(givenStartPoint, givenEndPoint,mainDepth));
                lineCreateCheck(drawnLineList.get(drawnLineList.size()-1));
                break;
            case COMPOSITION_LINE:
                drawnLineList.add(new DrawableCompositionLine(givenStartPoint, givenEndPoint,mainDepth));
                lineCreateCheck(drawnLineList.get(drawnLineList.size()-1));
                break;
            case THE_CLASSOBJECT:
                resultDrawObject = new DrawableClass(givenStartPoint, mainDepth);
                drawnObjList.add(resultDrawObject);
                break;
            case THE_USECASE:
                resultDrawObject = new DrawableUseCase(givenStartPoint, mainDepth);
                drawnObjList.add(resultDrawObject);
                break;
        }
        if(resultDrawObject != null ) {
            mainDepth++;
        }
    }

    /* For mouse drag duration */
    public void detectMouseDrag(Point givenStartPoint, Point givenEndPoint) {
        switch (currentState) {
            case SELECT:
                if (isMoveObj()) {
                    moveObject(givenStartPoint, givenEndPoint);
                }
                else {
                    drawnObjList.get(drawnObjList.size()-1).updateDrawableObj(givenEndPoint); // update selecting box size
                }
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
                if (isMoveObj()) {
                    finishMoveObject();
                }
                else {
                    drawnObjList.remove(drawnObjList.size()-1);
                    selectGroupOfObj(givenStartPoint,givenEndPoint);
                }
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

    private void unSelectAll() {
        for (DrawableObject drawObj : drawnObjList) {
            drawObj.setSelectedState(false);
        }
    }

    private void selectObj(Point givenPoint) {
        unSelectAll();
        DrawableObject drawObj;
        /* reverse loop traverse */
        for (int i = drawnObjList.size()-1; i>=0 ; i--) {
            drawObj = drawnObjList.get(i);
            if (drawObj.checkPointOverlap(givenPoint)) {
                drawObj.setSelectedState(true);
                break;
            }
        }
    }

    private void selectGroupOfObj(Point givenStartPoint , Point givenEndPoint) {
        Shape rect = new Rectangle2D.Float(Math.min(givenStartPoint.x,givenEndPoint.x), Math.min(givenStartPoint.y,givenEndPoint.y), Math.abs(givenStartPoint.x-givenEndPoint.x), Math.abs(givenStartPoint.y-givenEndPoint.y));
        for(DrawableObject drawObj : drawnObjList) {
            drawObj.checkHoleObjectOverlap(rect);
        }
    }

    /* If there is any object under selected return true */
    private boolean isMoveObj() {
        for(DrawableObject drawObj : drawnObjList) {
            if (drawObj.getSelectedState()) {
                return true;
            }
        }
        return false;
    }

    public void groupObj() {
        DrawableObject compositeBox = new DrawableCompositeBox(drawnObjList);
        drawnObjList.add(compositeBox);
        if (compositeBox.getChildrenSize() <= 1) {
            unGroupObj();
        }
    }

    public void unGroupObj() {
        ArrayList<DrawableObject> deletingObj = new ArrayList<>();
        ArrayList<DrawableObject> decomposeObjList = new ArrayList<>();
        for (DrawableObject drawObj : drawnObjList) {
            if (drawObj.deComposeObj() != null ) {
                decomposeObjList.addAll(drawObj.deComposeObj());
                deletingObj.add(drawObj);
            }
        }
        drawnObjList.removeAll(deletingObj);
        drawnObjList.addAll(decomposeObjList);
    }

    private void moveObject(Point givenStartPoint, Point givenEndPoint) {
        int x_mov = givenEndPoint.x - givenStartPoint.x;
        int y_mov = givenEndPoint.y - givenStartPoint.y;
        for (DrawableObject drawObj : drawnObjList) {
            if (drawObj.getSelectedState()) {
                drawObj.moveDrawableObj(x_mov,y_mov);
            }
        }
    }

    private void finishMoveObject() {
        for (DrawableObject drawObj : drawnObjList) {
            if (drawObj.getSelectedState()) {
                drawObj.finishMovement();
            }
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
//        ArrayList<Drawable> selectedObjList = getSelectedObj();
//        if (selectedObjList.size() == 1) {
//            Drawable renamingObj = selectedObjList.get(0);
//            renamingObj.setText(givenName);
//        }
    }

    public void refreshCanvas() {
        theMainCanvas.updateCanvas();
    }

    public void setMainCanvas(MyCanvas givenCanvas) {
        theMainCanvas = givenCanvas;
    }
}
