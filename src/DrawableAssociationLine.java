import java.awt.*;

public class DrawableAssociationLine extends DrawableLine {

    public DrawableAssociationLine(Point givenStartPoint, Point givenEndPoint, int givenDepth) {
        super(givenStartPoint, givenEndPoint, givenDepth);
    }

    @Override
    protected void paintObject(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}

