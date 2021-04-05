import java.awt.*;
import java.awt.geom.Line2D;

public class DrawableGeneralizationLine extends DrawableLine {
    private double lineLength = 0;

    public DrawableGeneralizationLine(Point givenStartPoint, Point givenEndPoint, int givenDepth) {
        super(givenStartPoint, givenEndPoint, givenDepth);
    }

    @Override
    protected void paintObject(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);

        g2.draw ( new Line2D.Double (startPoint.x, startPoint.y, endPoint.x, endPoint.y) );
    }
}
