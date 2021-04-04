import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerticalClassButton extends VerticalButton{
    /* Constructor */
    public VerticalClassButton(String text, DrawController givenDrawController, VerticalButtonController givenButtonController) {
        super(text, givenDrawController, givenButtonController);

        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                super.mousePressed(me);
                theDrawController.changePaintTool(DrawController.PAINT_TOOL.THE_CLASSOBJECT);
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

}

