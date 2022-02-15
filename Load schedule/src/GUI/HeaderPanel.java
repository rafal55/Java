package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class HeaderPanel extends JPanel{
    
    //private static final int DEFAULT_WIDTH = 300;
    //private static final int DEFAULT_HEIGHT = 50;
    //private static final Color BACKGROUND_COLOR = Color.BLUE;

    
    public HeaderPanel() {
        
        //setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //setBackground(BACKGROUND_COLOR);
        Border border = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(border, "Header");
        setBorder(titled);
        
        JToolBar bar = new JToolBar();
        JButton quitButton = new JButton("Exit");
        quitButton.addActionListener(event -> System.exit(0));
        
        JButton plotButton = new JButton("Plot");
        
        
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        bar.add(quitButton);
        bar.add(plotButton);
        bar.add(button3);
        bar.add(button4);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(bar);

    }
}
