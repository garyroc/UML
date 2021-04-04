import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class StartMain {
    public JFrame theMainFrame;
    public JPanel theToolbarPositionPanel;
    public JPanel theToolbarPanel;
    public JPanel theVerticalToolPanel;

    ArrayList<VerticalButton> verticalButtonlist = new ArrayList<VerticalButton>();

    public static void main(String[] args) {
        new StartMain().buildGUI();

    }

    public void buildGUI() {
        DrawController mainDrawController = new DrawController();
        theMainFrame = new JFrame("UML Editor");
        theMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout theMainLayout = new BorderLayout();

        theToolbarPositionPanel = new JPanel();
        theToolbarPositionPanel.setLayout(new BorderLayout());

        theToolbarPanel = new JPanel();
        theVerticalToolPanel = new JPanel();
        theVerticalToolPanel.setLayout(new BoxLayout(theVerticalToolPanel, BoxLayout.Y_AXIS));

        MyCanvas theMainCanvas = new MyCanvas(mainDrawController);



        JButton testbutton = new JButton("File");
        JButton testbutton2 = new JButton("Edit");


        /* Left handside tool bar button initialization */
        VerticalButtonController mainVerticalButtonController = new VerticalButtonController(mainDrawController);
        verticalButtonlist = mainVerticalButtonController.getVerticalButtonsList();


        theToolbarPanel.add(testbutton);
        theToolbarPanel.add(testbutton2);

        /*Left handside tool bar button add to Panel */
        for ( VerticalButton initializedButton : verticalButtonlist) {
            theVerticalToolPanel.add(initializedButton);
        }


        theToolbarPositionPanel.add(BorderLayout.WEST,theToolbarPanel);
        theMainFrame.getContentPane().add(BorderLayout.NORTH,theToolbarPositionPanel);
        theMainFrame.getContentPane().add(BorderLayout.WEST,theVerticalToolPanel);
        theMainFrame.getContentPane().add(BorderLayout.CENTER,theMainCanvas);
        theMainFrame.setSize(2000,1300);
        theMainFrame.setVisible(true);
    }



}


