import javax.swing.*;

public abstract class Toolbar_PopMenuItem extends JMenuItem {
    DrawController theDrawController;
    MyCanvas theCanvas;
    public Toolbar_PopMenuItem(String text , DrawController givenDrawController, MyCanvas givenCanvas) {
        super(text);
        this.theDrawController = givenDrawController;
        this.theCanvas = givenCanvas;
    }

}
