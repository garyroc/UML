package Toolbar;

import Draw.DrawController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolbarRenameButton extends Toolbar_PopMenuItem {

    public ToolbarRenameButton(String text, DrawController givenDrawController) {
        super(text,givenDrawController);
        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if(contains(me.getX(), me.getY())){
                    showDialog();
                }
            }
        };

        addMouseListener(mouseListener);
    }

    private void showDialog(){
        String name = JOptionPane.showInputDialog(this,
                "What name you want to change?", null);
        if ( name != null && !name.isEmpty()) {
            theDrawController.reNameFunction(name);
            theDrawController.refreshCanvas();
        }
    }
}
