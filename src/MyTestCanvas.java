import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.*;

public class MyTestCanvas extends JPanel {
    ArrayList<Drawable> theDrawedObjectList = new ArrayList<Drawable>();
    Drawable newDrawingObject;
    Point startPoint, endPoint;

    public MyTestCanvas() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startPoint = new Point(e.getX(),e.getY());
                newDrawingObject = makeDarwingObject(startPoint);
                theDrawedObjectList.add(newDrawingObject);
                repaint();
            }


            public void mouseReleased(MouseEvent e) {
                if (endPoint != null) {
                    newDrawingObject = makeAssociationLine(startPoint,endPoint);
                    theDrawedObjectList.add(newDrawingObject);
                }
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

    private Drawable makeDarwingObject(Point givenPoint) {
//        return new DrawableClass(xPos, yPos);
        return new DrawableUseCase(givenPoint);
    }

    private Drawable makeAssociationLine(Point givenStartPoint) {
        return new DrawableAssociationLine(givenStartPoint);
    }

    private Drawable makeAssociationLine(Point givenStartPoint, Point givenEndPoint) {
        return new DrawableAssociationLine(givenStartPoint, givenEndPoint);
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
