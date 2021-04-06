import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolbarEditButton extends JButton {
    DrawController theDrawController;
    ToolbarController theToolbarController;
    public ToolbarEditButton(String text, DrawController givenDrawController, ToolbarController givenToolbarController) {
        super(text);
        this.theToolbarController = givenToolbarController;
        this.theDrawController = givenDrawController;

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

    private void popTheMenu() {
        theToolbarController.getTheEditMenu().show(this,getX(),getY());
    }
}
