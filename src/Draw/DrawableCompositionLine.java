package Draw;

import java.awt.*;
import java.awt.geom.Line2D;

public class DrawableCompositionLine extends DrawableLine{
    double phi = Math.toRadians(40);
    int barb = 20;

    public DrawableCompositionLine(Point givenStartPoint, Point givenEndPoint, int givenDepth) {
        super(givenStartPoint, givenEndPoint, givenDepth);
    }

    @Override
    protected void paintObject(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.RED);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        drawArrowHead(g2,endPoint,startPoint,Color.RED);
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
        Line2D.Double line = new Line2D.Double(tip.x, tip.y, x, y);
        g2.draw(line);
        rho = theta - phi;

        x2 = x - barb * Math.cos(rho);
        y2 = y - barb * Math.sin(rho);
        g2.draw(new Line2D.Double(x, y, x2, y2));
        rho = theta - phi;

        x = tip.x - barb * Math.cos(rho);
        y = tip.y - barb * Math.sin(rho);
        g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
        g2.draw(new Line2D.Double(x2, y2, x, y));
    }
}
