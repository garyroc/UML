package PaintToolButton;

import Draw.DrawController;

import java.awt.*;
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* Paint diagram */
        g.setColor(Color.BLACK);
        g.drawLine(15,35,25,25);
        g.drawLine(15,35,25,45);
        g.drawLine(25,25,25,45);
        g.drawLine(25,35,60,35);
        /* Paint String */
        g.setFont(getFont());
        FontMetrics metrics = g.getFontMetrics(getFont());
        int stringWidth = metrics.stringWidth(getText());
        int stringHeight = metrics.getHeight();
        g.drawString(getText(), getWidth()/2 - stringWidth/2, stringHeight+55);
    }
}
