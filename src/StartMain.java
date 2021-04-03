import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class StartMain {
    JFrame theMainFrame;
    JPanel theToolbarPositionPanel;
    JPanel theToolbarPanel;
    JPanel theVerticalToolPanel;

    public static void main(String[] args) {
        new StartMain().buildGUI();
    }

    public void buildGUI() {
        theMainFrame = new JFrame("UML Editor");
        theMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout theMainLayout = new BorderLayout();

        theToolbarPositionPanel = new JPanel();
        theToolbarPositionPanel.setLayout(new BorderLayout());

        theToolbarPanel = new JPanel();
        theVerticalToolPanel = new JPanel();
        theVerticalToolPanel.setLayout(new BoxLayout(theVerticalToolPanel, BoxLayout.Y_AXIS));

        MyCanvas theMainCanvas = new MyCanvas();
        MyTestCanvas thetestCanvas = new MyTestCanvas();



        JButton testbutton = new JButton("File");
        JButton testbutton2 = new JButton("Edit");
        SelectButton theSelectButton = new SelectButton("Select");

        /* Left handside tool bar button initialization */
        VerticalButton theAssociationLineButton = new AssociationLineButton("Association Line");
        VerticalButton theGeneralizationLineButton = new GeneralizationLineButton("Generalization Line");
        VerticalButton theCompositionLineButton = new CompositionLineButton("Composition Line");
        VerticalButton theClassButton = new ClassButton("Class");
        VerticalButton theUseClassButton = new UseClassButton("Use Class");


//        VerticalButton testbutton4 = new VerticalButton("fuck2");

        theToolbarPanel.add(testbutton);
        theToolbarPanel.add(testbutton2);

        /*Left handside tool bar button add to Panel */
        theVerticalToolPanel.add(theSelectButton);
        theVerticalToolPanel.add(theAssociationLineButton);
        theVerticalToolPanel.add(theGeneralizationLineButton);
        theVerticalToolPanel.add(theCompositionLineButton);
        theVerticalToolPanel.add(theClassButton);
        theVerticalToolPanel.add(theUseClassButton);

        theToolbarPositionPanel.add(BorderLayout.WEST,theToolbarPanel);
        theMainFrame.getContentPane().add(BorderLayout.NORTH,theToolbarPositionPanel);
        theMainFrame.getContentPane().add(BorderLayout.WEST,theVerticalToolPanel);
        theMainFrame.getContentPane().add(BorderLayout.CENTER,thetestCanvas);
        theMainFrame.setSize(2000,1300);
        theMainFrame.setVisible(true);
    }



}


