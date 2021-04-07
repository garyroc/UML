import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolbarRenameButton extends Toolbar_PopMenuItem {

    public ToolbarRenameButton(String text, DrawController givenDrawController, MyCanvas givenCanvas) {
        super(text,givenDrawController, givenCanvas);
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
                "What is your name?", null);
        if (name != null) {
            theDrawController.reNameFunction(name);
        }
    }
}
