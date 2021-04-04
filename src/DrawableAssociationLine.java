import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.lang.Math;

public class DrawableAssociationLine extends DrawableLine {
    private double lineLength = 0;
//    private Point endPoint = new Point(0,0);


    public DrawableAssociationLine(Point givenStartPoint, Point givenEndPoint, int givenDepth) {
        super(givenStartPoint, givenEndPoint, givenDepth);
//        endPoint = givenEndPoint;
    }

    @Override
    protected void paintObject(Graphics g) {
        float arrowRatio = 0.5f;
        float arrowLength = 80.0f;
        double veeX;

        g.setColor(Color.BLACK);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
//        Graphics2D g2 = (Graphics2D) g;
//        BasicStroke stroke = ( BasicStroke ) g2.getStroke();
//
//        lineLength = Math.sqrt(Math.abs(startPoint.x-endPoint.x)^2+Math.abs(startPoint.y-startPoint.y)^2);
//        veeX = 350.f - stroke.getLineWidth() * 0.5f / arrowRatio;
//
//        // vee
//        Path2D.Float path = new Path2D.Float();
//        path.moveTo ( veeX - arrowLength, -arrowRatio*arrowLength );
//        path.lineTo ( veeX, 0.0f );
//        path.lineTo ( veeX - arrowLength, arrowRatio*arrowLength );

//        g2.setColor(Color.BLACK);
//        g2.draw( path );
//        g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
//        g2.draw ( new Line2D.Double (startPoint.x, startPoint.y, endPoint.x, endPoint.y) );

    }

//    public void setEndPoint(Point newPoint) {
//        endPoint = newPoint;
//    }
}

