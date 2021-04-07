import java.util.ArrayList;

public class VerticalButtonController {
    private ArrayList<VerticalButton> verticalButtonsList = new ArrayList<VerticalButton>();
    DrawController mainDrawController;
    public VerticalButtonController(DrawController givenDrawController) {
        mainDrawController = givenDrawController;
        initializeVerticalButtonsList();
    }

    public void initializeVerticalButtonsList() {
        VerticalButton initializedButton = new VerticalSelectButton("Select", mainDrawController, this);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalAssociationLineButton("Association", mainDrawController, this);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalGeneralizationLineButton("Generalization", mainDrawController, this);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalCompositionLineButton("Composition", mainDrawController, this);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalClassButton("Class",mainDrawController, this);
        verticalButtonsList.add(initializedButton);
        initializedButton = new VerticalUseClassButton("Use Class", mainDrawController, this);
        verticalButtonsList.add(initializedButton);
    }

    public ArrayList<VerticalButton> getVerticalButtonsList() {
        return verticalButtonsList;
    }

    public void restAllSelectedState() {
        for ( VerticalButton initializedButton : verticalButtonsList) {
            initializedButton.buttonSelected = false;
        }
    }
}
