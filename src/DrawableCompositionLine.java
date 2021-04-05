import java.awt.*;

public class DrawableCompositionLine extends DrawableLine{
    private double lineLength = 0;

    public DrawableCompositionLine(Point givenStartPoint, Point givenEndPoint, int givenDepth) {
        super(givenStartPoint, givenEndPoint, givenDepth);
    }

    @Override
    protected void paintObject(Graphics g) {
        g.setColor(Color.RED);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
