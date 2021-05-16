import java.awt.*;
import java.util.ArrayList;

public class CompositeTypeObj extends CompositeProtocol {
    private ArrayList<CompositeProtocol> theCompositeList = new ArrayList<CompositeProtocol>();
    Drawable representDrawableObj;

    public CompositeTypeObj(ArrayList<CompositeProtocol> givenList) {
        super(OBJ_TYPE.COMPOSITE_OBJ);
        theCompositeList = givenList;
    }

    public ArrayList<CompositeProtocol> getTheCompositeList() {
        return theCompositeList;
    }

    public void setBelongObjSelected(boolean givenValue) {
        ArrayList<CompositeProtocol> drawObjList = newTraversalCompositeTree(theCompositeList);
//        for (CompositeProtocol drawObj : drawObjList) {
//            Drawable drawObj_d = (Drawable) drawObj;
//            if (!drawObj_d.isLineObj()) {
//                drawObj_d.setSelectedState(givenValue);
//            }
//        }
    }

    public Point getLeftUpMostPoint() {
        Point LeftUpPoint = new Point(99999,99999);
//        ArrayList<CompositeProtocol> drawObjList = newTraversalCompositeTree(theCompositeList);
//        for (CompositeProtocol drawObj : drawObjList) {
//            Drawable drawObj_d = (Drawable) drawObj;
//            if (drawObj_d.getLeftUpPoint().x < LeftUpPoint.x) {
//                LeftUpPoint.x = drawObj_d.getLeftUpPoint().x;
//            }
//            if (drawObj_d.getLeftUpPoint().y < LeftUpPoint.y) {
//                LeftUpPoint.y = drawObj_d.getLeftUpPoint().y;
//            }
//        }
        return LeftUpPoint;
    }

    public Point getRightDownMostPoint() {
        Point RightDownPoint = new Point(0,0);
//        ArrayList<CompositeProtocol> drawObjList = newTraversalCompositeTree(theCompositeList);
//        for (CompositeProtocol drawObj : drawObjList) {
//            Drawable drawObj_d = (Drawable) drawObj;
//            if (drawObj_d.getRightDownPoint().x > RightDownPoint.x) {
//                RightDownPoint.x = drawObj_d.getRightDownPoint().x;
//            }
//            if (drawObj_d.getRightDownPoint().y > RightDownPoint.y) {
//                RightDownPoint.y = drawObj_d.getRightDownPoint().y;
//            }
//        }
        return RightDownPoint;
    }

    public Drawable getRepresentDrawableObj() { return representDrawableObj; }
    public void setRepresentDrawableObj(Drawable givenObj) { representDrawableObj = givenObj; }

    private ArrayList<CompositeProtocol> newTraversalCompositeTree (ArrayList<CompositeProtocol> givenCompositeTree) {
        ArrayList<CompositeProtocol> resultList = new ArrayList<CompositeProtocol>();
        ArrayList<CompositeProtocol> tempList;
//        for (CompositeProtocol traversingTreeMember : givenCompositeTree) {
//            if (traversingTreeMember.myType == CompositeProtocol.OBJ_TYPE.COMPOSITE_OBJ) {
//                tempList = newTraversalCompositeTree(((CompositeTypeObj) traversingTreeMember).getTheCompositeList());
//                resultList.addAll(tempList);
//                resultList.add(((CompositeTypeObj) traversingTreeMember).getRepresentDrawableObj());
//            }
//            else {
//                resultList.add(traversingTreeMember);
//            }
//        }
        return resultList;
    }
}
