
package GUI;

import javax.swing.*;


import java.awt.*;

public class Plot extends JPanel {
    
    private JTextArea textArea;
   // private Color bkgColor = new Color(51, 52, 54);

    public Plot() {
        
        setLayout(new BorderLayout());
        setPreferredSize(getMaximumSize());
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setDragEnabled(true);
        add(textArea);
        }
    }

