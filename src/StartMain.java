import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class StartMain {
    public JFrame theMainFrame;
    public JPanel theToolbarPositionPanel;
    public JPanel theToolbarPanel;
    public JPanel theVerticalToolPanel;
    public JPopupMenu theEditMenu;


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

        ToolbarController theToolbarController = new ToolbarController(mainDrawController,theMainCanvas);

        JButton fileButton = new JButton("File");
        ToolbarEditButton toolbarEditButton = new ToolbarEditButton("Edit",mainDrawController,theToolbarController);


        /* Left handside tool bar button initialization */
        VerticalButtonController mainVerticalButtonController = new VerticalButtonController(mainDrawController);
        verticalButtonlist = mainVerticalButtonController.getVerticalButtonsList();


        theToolbarPanel.add(fileButton);
        theToolbarPanel.add(toolbarEditButton);

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


