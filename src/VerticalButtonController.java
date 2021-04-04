import java.util.ArrayList;

public class VerticalButtonController {
    private ArrayList<VerticalButton> verticalButtonsList = new ArrayList<VerticalButton>();
    DrawController mainDrawController;
    public VerticalButtonController(DrawController givenDrawController) {
        mainDrawController = givenDrawController;
        initializeVerticalButtonsList();
    }

    public void initializeVerticalButtonsList() {
        VerticalButton initializedButton = new VerticalSelectButton("Select", mainDrawController);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalAssociationLineButton("Association Line", mainDrawController);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalGeneralizationLineButton("Generalization Line", mainDrawController);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalCompositionLineButton("Composition Line", mainDrawController);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalClassButton("Class",mainDrawController);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalUseClassButton("Use Class", mainDrawController);
        verticalButtonsList.add(initializedButton);
    }

    public ArrayList<VerticalButton> getVerticalButtonsList() {
        return verticalButtonsList;
    }
}
