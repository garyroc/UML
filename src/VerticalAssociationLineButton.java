import java.awt.*;
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(15,35,25,25);
        g.drawLine(15,35,25,45);
        g.drawLine(15,35,60,35);
        g.setFont(getFont());
        FontMetrics metrics = g.getFontMetrics(getFont());
        int stringWidth = metrics.stringWidth(getText());
        int stringHeight = metrics.getHeight();
        g.drawString(getText(), getWidth()/2 - stringWidth/2, stringHeight+55);
    }
}
