import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class DrawableSelectBox extends Drawable {
    private Shape rect;
    private int width;
    private int heigh;

    public DrawableSelectBox (Point givenStartPoint, int givenDepth) {
        super(givenStartPoint, givenDepth, true);
        updateWidthAndHeigh(givenStartPoint);
    }
    public void updateWidthAndHeigh(Point givenNewPoint) {
        rect = makeRectangle(startPoint.x, startPoint.y, givenNewPoint.x, givenNewPoint.y);
    }

    @Override
    protected void paintObject(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2.draw(rect);
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public Shape getRect() {
        return rect;
    }
}
