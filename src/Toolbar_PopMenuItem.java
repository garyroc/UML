import javax.swing.*;

public abstract class Toolbar_PopMenuItem extends JMenuItem {
    DrawController theDrawController;
    public Toolbar_PopMenuItem(String text , DrawController givenDrawController) {
        super(text);
        this.theDrawController = givenDrawController;
    }
}
