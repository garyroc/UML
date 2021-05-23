package PaintToolButton;

import Draw.DrawController;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerticalButton extends JButton{
    protected boolean mousePressed = false;
    protected boolean mouseOver = false;
    protected boolean buttonSelected = false;
    protected int buttonSideLength = 80;
    protected DrawController theDrawController;
    protected VerticalButtonController verticalButtonController;

    /* Constructor */
    public VerticalButton(String text, DrawController givenDrawController, VerticalButtonController givenButtonController) {
        super(text);
        theDrawController = givenDrawController;
        verticalButtonController = givenButtonController;
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false); //BackGround disappear
        this.setMaximumSize(new Dimension(buttonSideLength, buttonSideLength));

        MouseAdapter mouseListener = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if(contains(me.getX(), me.getY())){
                    mousePressed = true;
                    verticalButtonController.restAllSelectedState();
                    buttonSelected = true;
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
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(buttonSideLength, buttonSideLength);
    }

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
    }

}
