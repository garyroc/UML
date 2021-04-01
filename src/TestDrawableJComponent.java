import javax.swing.*;
import java.awt.*;

public class TestDrawableJComponent extends JComponent {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(100, 100, 100, 80);
        g.setColor(Color.BLACK);
        g.drawRect(100, 100, 50, 80);
        g.drawRect(100, 150, 50, 80);
    }
}
