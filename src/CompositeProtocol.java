import java.util.ArrayList;

public class CompositeProtocol {
    public enum OBJ_TYPE {
        DRAWABLE_OBJ, COMPOSITE_OBJ
    }
    OBJ_TYPE myType;

    public CompositeProtocol() {

    }

    public CompositeProtocol(OBJ_TYPE givenType) {
        myType = givenType;
    }

    public static CompositeTypeObj compose(ArrayList<CompositeProtocol> givenList) {
        CompositeTypeObj resultComposite = new CompositeTypeObj(givenList);
        return resultComposite;
    }

    public ArrayList<CompositeProtocol> decompose(CompositeProtocol givenComposite) {
        if (givenComposite.myType == OBJ_TYPE.COMPOSITE_OBJ) {
            return ((CompositeTypeObj) givenComposite).getTheCompositeList();
        }
        else {
            ArrayList<CompositeProtocol> selfArray =  new ArrayList<CompositeProtocol>();
            selfArray.add(givenComposite);
            return selfArray;
        }
    }
}
