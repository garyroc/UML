import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class VerticalButton extends JButton{
    protected boolean mousePressed = false;
    protected boolean mouseOver = false;
    protected boolean buttonSelected = false;
    protected int buttonSideLength = 80;
    protected DrawController theMainController;

    /* Constructor */
    public VerticalButton(String text, DrawController givenController) {
        super(text);
        theMainController = givenController;
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false); //BackGound disapper
        this.setMaximumSize(new Dimension(buttonSideLength, buttonSideLength));

        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if(contains(me.getX(), me.getY())){
                    mousePressed = true;
                    buttonSelected = !buttonSelected;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent me){
                mousePressed = false;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me){
                mouseOver = false;
                mousePressed = false;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent me){
                mouseOver = contains(me.getX(), me.getY());
                repaint();
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    /* Method */
    public void resetSlectedState() {

    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(buttonSideLength, buttonSideLength);
    }

    /* 先註解，之後再來修正GUI的外觀部分 */
    @Override
    public void paintComponent(Graphics g) {
        /*Determine it should be selected state or not*/
        if (buttonSelected) {
            if(mousePressed){
                g.setColor(Color.WHITE);
            }
            else {
                g.setColor(Color.LIGHT_GRAY);
            }
        }
        else {
            if(mousePressed){
                g.setColor(Color.LIGHT_GRAY);
            }
            else {
                g.setColor(Color.WHITE);
            }
        }


        g.fillRect(0,0,buttonSideLength-1,79); //Draw the background rectangle

        if(mouseOver){
            g.setColor(Color.BLUE);
        }
        else{
            g.setColor(Color.BLACK);
        }
        g.drawRect(0,0,79,79); //Draw the outside rectangle

        g.setColor(Color.BLACK);
        g.setFont(getFont());
        FontMetrics metrics = g.getFontMetrics(getFont());
        int stringWidth = metrics.stringWidth(getText());
        int stringHeight = metrics.getHeight();
        g.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4);
    }

}
