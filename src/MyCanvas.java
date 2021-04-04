import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.*;

public class MyCanvas extends JPanel {
    protected ArrayList<Drawable> theDrawedObjectList = new ArrayList<Drawable>();
    protected Drawable newDrawingObj;
    protected Point startPoint, endPoint;
    protected DrawController theDrawController;

    public MyCanvas(DrawController theMainDrawController) {
        theDrawController = theMainDrawController;

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startPoint = new Point(e.getX(), e.getY());
                endPoint = new Point(e.getX(), e.getY());
                theMainDrawController.createDrawOject(startPoint,endPoint);
                theDrawedObjectList = theMainDrawController.getDrawingList();
                repaint();
            }


            public void mouseReleased(MouseEvent e) {
//                if (endPoint != null) {
//                    newDrawingObj = theDrawController.createDrawOject(startPoint,endPoint);
//                    theDrawedObjectList.add(newDrawingObj);
//
//                }
                endPoint = null;
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endPoint = new Point(e.getX(),e.getY());
                repaint();
            }
        });
    }

    private boolean checkObjectexist() {
        boolean objectExist = false;

        return objectExist;
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for ( Drawable needDrawedObject : theDrawedObjectList) {
            needDrawedObject.paintObject(g);
        }

        if (startPoint != null && endPoint != null) {
            g.drawLine(startPoint.x,startPoint.y,endPoint.x,endPoint.y);
        }
    }

}
