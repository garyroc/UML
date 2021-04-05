import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerticalGeneralizationLineButton extends VerticalButton{

    /* Constructor */
    public VerticalGeneralizationLineButton(String text, DrawController givenDrawController, VerticalButtonController givenButtonController) {
        super(text, givenDrawController, givenButtonController);
        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                super.mousePressed(me);
                theDrawController.changePaintTool(DrawController.PAINT_TOOL.GENERALIZATION_LINE);
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

}
