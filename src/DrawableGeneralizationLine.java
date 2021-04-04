import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class DrawableGeneralizationLine extends Drawable{
    private double lineLength = 0;
    private Point endPoint = new Point(0,0);

    public DrawableGeneralizationLine(Point givenStartPoint) {
        super(givenStartPoint);
    }

    public DrawableGeneralizationLine(Point givenStartPoint, Point givenEndPoint) {
        super(givenStartPoint);
        endPoint = givenEndPoint;
    }

    @Override
    protected void paintObject(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        g2.draw ( new Line2D.Double (startPoint.x, startPoint.y, endPoint.x, endPoint.y) );
    }
}
