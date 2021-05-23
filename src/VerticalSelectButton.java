import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerticalSelectButton extends VerticalButton{
    /* Constructor */
    public VerticalSelectButton(String text, DrawController givenDrawController, VerticalButtonController givenButtonController) {
        super(text, givenDrawController, givenButtonController);

        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                super.mousePressed(me);
                theDrawController.changePaintTool(DrawController.PAINT_TOOL.SELECT);
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* Paint diagram */
        int[]x = {20,30,45};
        int[]y = {20,45,35};
        int[]t_x = {28,30,49,45};
        int[]t_y = {33,30,45,50};
        g.setColor(Color.BLACK);
        g.fillPolygon(x,y,3);
        g.fillPolygon(t_x,t_y,4);
        /* Paint String */
        g.setFont(getFont());
        FontMetrics metrics = g.getFontMetrics(getFont());
        int stringWidth = metrics.stringWidth(getText());
        int stringHeight = metrics.getHeight();
        g.drawString(getText(), getWidth()/2 - stringWidth/2, stringHeight+55);
    }
}
