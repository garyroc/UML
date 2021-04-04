import java.util.ArrayList;

public class CompositeTypeObj extends CompositeProtocol {
    private ArrayList<CompositeProtocol> theCompositeList = new ArrayList<CompositeProtocol>();

    CompositeTypeObj(ArrayList<CompositeProtocol> givenList) {
        super(OBJ_TYPE.COMPOSITE_OBJ);
        theCompositeList = givenList;
    }

    public ArrayList<CompositeProtocol> getTheCompositeList() {
        return theCompositeList;
    }
}
