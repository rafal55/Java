package GUI;
import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.awt.event.*;

public class PanelLayer extends LayerUI<JPanel> {
    
    public void installUI(JComponent c) 
    {
        super.installUI(c);
        ((JLayer<?>) c).setLayerEventMask(AWTEvent.KEY_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
    }
    
    public void uninstallUI(JComponent c) 
    {
        ((JLayer<?>) c).setLayerEventMask(0);
        super.uninstallUI(c);
    }

    
    protected void processKeyEvent(KeyEvent e, JLayer<? extends JPanel> l)
    {
        l.repaint();
    }
    
    protected void processFocusEvent(FocusEvent e, JLayer<? extends JPanel> l)
    { 
        if (e.getID() == FocusEvent.FOCUS_GAINED)
        {
            Component c = e.getComponent();
            c.setFont(null);
        }
        if (e.getID() == FocusEvent.FOCUS_LOST)
        {
            Component c = e.getComponent();
            c.setFont(null);
        }
    }
    
    public void paint(Graphics g, JComponent c) 
    {
        super.paint(g, c);
    
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));

        g2.setPaint(new Color(40, 0, 0));
        g2.fillRect(0, 0, c.getWidth(), c.getHeight());
        g2.dispose();
    }
}


