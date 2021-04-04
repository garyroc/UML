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
//                resultDrawObject = new DrawableAssociationLine(givenStartPoint, givenEndPoint);
                resultDrawObject = new DrawableAssociationLine(givenStartPoint, givenEndPoint, mainDepth);
                mainDepth++;
                break;
            case GENERALIZATION_LINE:
                break;
            case COMPOSITION_LINE:
                break;
            case THE_CLASSOBJECT:
//                resultDrawObject = new DrawableClass(givenStartPoint);
                resultDrawObject = new DrawableClass(givenStartPoint, mainDepth);
                mainDepth++;
                mainCompositeTree.add(resultDrawObject);
                break;
            case THE_USECASE:
//                resultDrawObject = new DrawableUseCase(givenStartPoint);
                resultDrawObject = new DrawableUseCase(givenStartPoint, mainDepth);
                mainDepth++;
                mainCompositeTree.add(resultDrawObject);
                break;
        }
        return resultDrawObject;
    }

    public void chagePaintTool(PAINT_TOOL givenPaintTool) {

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
        for (CompositeProtocol travingTreeMember : compositeTree) {
            if (travingTreeMember.myType == CompositeProtocol.OBJ_TYPE.COMPOSITE_OBJ) {
                traversalCompositeTree(((CompositeTypeObj)travingTreeMember).getTheCompositeList());
            }
            else {
                drawingObjectList.add((Drawable) travingTreeMember);
            }
        }
    }


}
