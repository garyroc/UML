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

    public Drawable createDrawOject(Point givenStartPoint, Point givenEndPoint) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECET:

                break;
            case ASSOCIATION_LINE:
                resultDrawObject = new DrawableAssociationLine(givenStartPoint, givenEndPoint,mainDepth);
                resultDrawObject.setSelectedState(true);
//                if (!determineLineExistOrNot(resultDrawObject,givenEndPoint)){
//                    resultDrawObject = null;
//                }

//                resultDrawObject = (Drawable) mainCompositeTree.get(mainCompositeTree.size()-1);
//                if (resultDrawObject.isLineObj) {
//                    determineLineExistOrNot(resultDrawObject,givenEndPoint);
//                }
//                else {
//                    resultDrawObject = new DrawableAssociationLine(givenStartPoint, givenEndPoint,mainDepth);
//                }

                // Determine the line should exist or not
//                resultDrawObject = new DrawableAssociationLine(givenStartPoint, givenEndPoint,mainDepth);
//                mainCompositeTree.add(resultDrawObject);
//                mainDepth++;
                break;
            case GENERALIZATION_LINE:
                break;
            case COMPOSITION_LINE:
                break;
            case THE_CLASSOBJECT:
                resultDrawObject = new DrawableClass(givenStartPoint, mainDepth);
//                mainDepth++;
//                mainCompositeTree.add(resultDrawObject);
                break;
            case THE_USECASE:
                resultDrawObject = new DrawableUseCase(givenStartPoint, mainDepth);
//                mainDepth++;

                break;
        }
        mainCompositeTree.add(resultDrawObject);
        mainDepth++;

        if (currentState == PAINT_TOOL.ASSOCIATION_LINE || currentState == PAINT_TOOL.GENERALIZATION_LINE || currentState == PAINT_TOOL.COMPOSITION_LINE) {
            determineLineExistOrNot(resultDrawObject,givenEndPoint);
        }

        return resultDrawObject;
    }

    /*For mouse drag duration*/
    public Drawable createDrawOject(Point givenStartPoint, Point givenEndPoint, boolean isMouseDrage) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECET:

                break;
            case ASSOCIATION_LINE:
                resultDrawObject = (Drawable) mainCompositeTree.get(mainCompositeTree.size()-1);
                if (resultDrawObject.isLineObj) {
                    if ((resultDrawObject).getSelectedState()) {
                        ((DrawableLine) resultDrawObject).setEndPoint(givenEndPoint);
                    }
                }
//                else {
//                    resultDrawObject = new DrawableAssociationLine(givenStartPoint, givenEndPoint,mainDepth);
//                    mainDepth++;
//                    mainCompositeTree.add(resultDrawObject);
//                }

//                ((DrawableLine) resultDrawObject).setEndPoint(givenEndPoint);
                break;
            case GENERALIZATION_LINE:
                break;
            case COMPOSITION_LINE:
                break;
        }
        return resultDrawObject;
    }

    public void checkDrawObj(Point givenStartPoint, Point givenEndPoint) {
        Drawable resultDrawObject = null;
        switch (currentState) {
            case SELECET:

                break;
            case ASSOCIATION_LINE:
                resultDrawObject = (Drawable) mainCompositeTree.get(mainCompositeTree.size()-1);
                if (resultDrawObject.isLineObj && resultDrawObject.getSelectedState()) {
                    determineLineExistOrNot(resultDrawObject,givenEndPoint);
                    resultDrawObject.setSelectedState(false);
                }
                break;
            case GENERALIZATION_LINE:
                break;
            case COMPOSITION_LINE:
                break;
            default:
                break;
        }
    }

    protected boolean checkObjectExist(){
        /* Under construction*/
        return false;
    }

    public void changePaintTool(PAINT_TOOL givenPaintTool) {
        currentState = givenPaintTool;
    }

    public void mousePressedHappend(Point startPoint, Point endPoint) {


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
        Drawable connectedDrawingObj = null;
        createDrawingList(mainCompositeTree);
        for ( Drawable testingObj : drawingObjectList) {
            if (!testingObj.isLineObj()) {
                if (testingObj.checkOverlap(givenEndPoint)) {
                    connectedDrawingObj = testingObj;
                }
            }
        }
        /* connect line to the object */
        //Need improve
        if (connectedDrawingObj == null) {
            mainCompositeTree.remove((mainCompositeTree.size()-1));
//            ((DrawableLine)underTestLine).setEndPoint(connectedDrawingObj.northPoint);
        }
        else {
            ((DrawableLine) underTestLine).setEndPoint(connectedDrawingObj.northPoint);
//            ((DrawableLine) underTestLine).setHasFinish(true);
//            ((DrawableLine) mainCompositeTree.get((mainCompositeTree.size()-1))).setEndPoint(connectedDrawingObj.northPoint);
        }

    }


}
