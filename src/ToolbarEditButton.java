import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolbarEditButton extends JButton {
    DrawController theDrawController;
    JPopupMenu theEditMenu;

    public ToolbarEditButton(String text, DrawController givenDrawController) {
        super(text);
        this.theDrawController = givenDrawController;
        this.theEditMenu = new JPopupMenu();
        initialButton();

        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if(contains(me.getX(), me.getY())){
                    popTheMenu();
                    repaint();
                }
            }
        };

        addMouseListener(mouseListener);
    }

    private void initialButton() {
        ToolbarGroupButton groupOption = new ToolbarGroupButton("Group", theDrawController);
        ToolbarUngroupButton unGroupOption = new ToolbarUngroupButton("UnGroup", theDrawController);
        ToolbarRenameButton reNameOption = new ToolbarRenameButton("Rename",theDrawController);
        theEditMenu.add(groupOption);
        theEditMenu.add(unGroupOption);
        theEditMenu.add(reNameOption);
    }

    private void popTheMenu() {
        theEditMenu.show(this,getX(),getY());
    }

}
