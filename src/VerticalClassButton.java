import java.awt.*;
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* Paint diagram */
        g.setColor(Color.GRAY);
        g.fillRect(20,15,40,36);
        g.setColor(Color.BLACK);
        g.drawRect(20,15,40,12);
        g.drawRect(20,27,40,12);
        g.drawRect(20,39,40,12);
        /* Paint String */
        g.setFont(getFont());
        FontMetrics metrics = g.getFontMetrics(getFont());
        int stringWidth = metrics.stringWidth(getText());
        int stringHeight = metrics.getHeight();
        g.drawString(getText(), getWidth()/2 - stringWidth/2, stringHeight+55);
    }
}

