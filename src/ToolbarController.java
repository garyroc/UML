import javax.swing.*;

public class ToolbarController {
    DrawController mainDrawController;
    MyCanvas theCanvas;
    JPopupMenu theEditMenu;

    public ToolbarController (DrawController givenController, MyCanvas givenCanvas) {
        this.mainDrawController = givenController;
        this.theEditMenu = new JPopupMenu();
        this.theCanvas = givenCanvas;
        iniToolbarButton();
    }

    public void iniToolbarButton() {
        ToolbarGroupButton groupOption = new ToolbarGroupButton("Group", mainDrawController, theCanvas);
        ToolbarUngroupButton unGroupOption = new ToolbarUngroupButton("UnGroup", mainDrawController, theCanvas);
        ToolbarRenameButton reNameOption = new ToolbarRenameButton("Rename",mainDrawController, theCanvas);
        theEditMenu.add(groupOption);
        theEditMenu.add(unGroupOption);
        theEditMenu.add(reNameOption);
    }

    public JPopupMenu getTheEditMenu() { return theEditMenu; }

}
