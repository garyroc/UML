import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.*;

public class MyCanvas extends JPanel {
    protected ArrayList<Drawable> theDrawnObjectList = new ArrayList<>();
    protected Point startPoint, endPoint;
    protected DrawController theDrawController;
    protected boolean mouseDrag = false;

    public MyCanvas(DrawController theMainDrawController) {
        theDrawController = theMainDrawController;
        theDrawController.setMainCanvas(this);
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startPoint = new Point(e.getX(), e.getY());
                endPoint = new Point(e.getX(), e.getY());
                theDrawController.createDrawObj(startPoint,endPoint);
                theDrawnObjectList = theDrawController.getDrawingList();
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                endPoint = new Point(e.getX(), e.getY());
                theDrawController.checkDrawObj(startPoint,endPoint);
                theDrawnObjectList = theDrawController.getDrawingList();
                mouseDrag = false;
                endPoint = null;
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                mouseDrag = true;
                endPoint = new Point(e.getX(),e.getY());
                theDrawController.detectMouseDrag(startPoint,endPoint);
                theDrawnObjectList = theDrawController.getDrawingList();
                repaint();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for ( Drawable needDrawObject : theDrawnObjectList) {
            needDrawObject.paintObject(g);
        }
    }

    public void updateCanvas() {
        theDrawnObjectList = theDrawController.getDrawingList();
        repaint();
    }
}
