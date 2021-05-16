import javax.swing.*;

public class ToolbarController {
    DrawController mainDrawController;
    JPopupMenu theEditMenu;

    public ToolbarController (DrawController givenController) {
        this.mainDrawController = givenController;
        this.theEditMenu = new JPopupMenu();
        iniToolbarButton();
    }

    public void iniToolbarButton() {
        ToolbarGroupButton groupOption = new ToolbarGroupButton("Group", mainDrawController);
        ToolbarUngroupButton unGroupOption = new ToolbarUngroupButton("UnGroup", mainDrawController);
        ToolbarRenameButton reNameOption = new ToolbarRenameButton("Rename",mainDrawController);
        theEditMenu.add(groupOption);
        theEditMenu.add(unGroupOption);
        theEditMenu.add(reNameOption);
    }

    public JPopupMenu getTheEditMenu() { return theEditMenu; }
}
