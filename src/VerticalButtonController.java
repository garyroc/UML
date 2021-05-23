import java.util.ArrayList;

public class VerticalButtonController {
    private final ArrayList<VerticalButton> verticalButtonsList = new ArrayList<>();
    DrawController mainDrawController;
    public VerticalButtonController(DrawController givenDrawController) {
        mainDrawController = givenDrawController;
        iniVerticalButList();
    }

    public void iniVerticalButList() {
        verticalButtonsList.add(new VerticalSelectButton("Select", mainDrawController, this));
        verticalButtonsList.add(new VerticalAssociationLineButton("Association", mainDrawController, this));
        verticalButtonsList.add(new VerticalGeneralizationLineButton("Generalization", mainDrawController, this));
        verticalButtonsList.add(new VerticalCompositionLineButton("Composition", mainDrawController, this));
        verticalButtonsList.add(new VerticalClassButton("Class",mainDrawController, this));
        verticalButtonsList.add(new VerticalUseClassButton("Use Class", mainDrawController, this));
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
