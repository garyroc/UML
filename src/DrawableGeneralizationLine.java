import java.awt.*;
import java.awt.geom.Line2D;

public class DrawableGeneralizationLine extends DrawableLine {
    double phi = Math.toRadians(40);
    int barb = 20;

    public DrawableGeneralizationLine(Point givenStartPoint, Point givenEndPoint, int givenDepth) {
        super(givenStartPoint, givenEndPoint, givenDepth);
    }

    @Override
    protected void paintObject(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);

        g2.draw ( new Line2D.Double (startPoint.x, startPoint.y, endPoint.x, endPoint.y) );
        drawArrowHead(g2,endPoint,startPoint,Color.GREEN);
    }

    private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color)
    {
        g2.setPaint(color);
        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + phi;
        double x2, y2;
        x = tip.x - barb * Math.cos(rho);
        y = tip.y - barb * Math.sin(rho);
        Line2D.Double line1 = new Line2D.Double(tip.x, tip.y, x, y);
        g2.draw(line1);
        rho = theta - phi;
        x2 = x;
        y2 = y;

        x = tip.x - barb * Math.cos(rho);
        y = tip.y - barb * Math.sin(rho);
        Line2D.Double line2 = new Line2D.Double(tip.x, tip.y, x, y);
        g2.draw(line2);
        Line2D.Double line3 = new Line2D.Double(x2, y2, x, y);
        g2.draw(line3);
    }
}
