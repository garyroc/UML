import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    public JFrame theMainFrame;
    public JPanel theToolbarPositionPanel;
    public JPanel theToolbarPanel;
    public JPanel theVerticalToolPanel;
    ArrayList<VerticalButton> verticalButtonList = new ArrayList<>();

    public static void main(String[] args) {
        new Main().buildGUI();
    }

    public void buildGUI() {
        /* Main Frame initialize */
        theMainFrame = new JFrame("UML Editor");
        theMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* Panel initialize */
        theToolbarPositionPanel = new JPanel();
        theToolbarPositionPanel.setLayout(new BorderLayout());
        theToolbarPanel = new JPanel();
        theVerticalToolPanel = new JPanel();
        theVerticalToolPanel.setLayout(new BoxLayout(theVerticalToolPanel, BoxLayout.Y_AXIS));
        /* Controller and Canvas initialize */
        DrawController mainDrawController = new DrawController();
        MyCanvas theMainCanvas = new MyCanvas(mainDrawController);
        /*Button initialize */
        JButton fileButton = new JButton("File");
        ToolbarEditButton toolbarEditButton = new ToolbarEditButton("Edit",mainDrawController);
        /* Left hand side tool bar button initialization */
        VerticalButtonController mainVerticalButtonController = new VerticalButtonController(mainDrawController);
        verticalButtonList = mainVerticalButtonController.getVerticalButtonsList();
        /*Left hand side tool bar button add to Panel */
        for ( VerticalButton initializedButton : verticalButtonList) {
            theVerticalToolPanel.add(initializedButton);
        }
        /* Up tool bar add to Panel */
        theToolbarPanel.add(fileButton);
        theToolbarPanel.add(toolbarEditButton);
        theToolbarPositionPanel.add(BorderLayout.WEST,theToolbarPanel);
        theMainFrame.getContentPane().add(BorderLayout.NORTH,theToolbarPositionPanel);
        theMainFrame.getContentPane().add(BorderLayout.WEST,theVerticalToolPanel);
        theMainFrame.getContentPane().add(BorderLayout.CENTER,theMainCanvas);
        theMainFrame.setSize(1900,1000);
        theMainFrame.setVisible(true);
    }
}


