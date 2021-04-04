import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerticalAssociationLineButton extends VerticalButton{

    /* Constructor */
    public VerticalAssociationLineButton(String text, DrawController givenDrawController, VerticalButtonController givenButtonController) {
        super(text, givenDrawController, givenButtonController);
        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                super.mousePressed(me);
                theDrawController.changePaintTool(DrawController.PAINT_TOOL.ASSOCIATION_LINE);
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
}
