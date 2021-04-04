import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerticalUseClassButton extends VerticalButton{
    /* Constructor */
    public VerticalUseClassButton(String text, DrawController givenDrawController, VerticalButtonController givenButtonController) {
        super(text, givenDrawController, givenButtonController);

        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                super.mousePressed(me);
                theDrawController.changePaintTool(DrawController.PAINT_TOOL.THE_USECASE);
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
}


