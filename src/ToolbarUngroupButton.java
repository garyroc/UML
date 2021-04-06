import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolbarUngroupButton extends Toolbar_PopMenuItem {

    public ToolbarUngroupButton (String text, DrawController givenDrawController, MyCanvas givenCanvas) {
        super(text, givenDrawController, givenCanvas);
        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if(contains(me.getX(), me.getY())){
                    theDrawController.unGroupObj();
                    theCanvas.updateDrawObjectList();
                    repaint();
                }
            }
        };

        addMouseListener(mouseListener);
    }
}
