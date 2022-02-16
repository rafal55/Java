package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;


public class TemplatesPanel extends JPanel {
    //private static final int DEFAULT_WIDTH = 50;
    //private static final int DEFAULT_HEIGHT = 20;
    // private static final Color BACKGROUND_COLOR = Color.GRAY;
    // private ImageList list1;
    private JScrollPane templatesList;
    private JList<String> list;
    private JLabel label;
    public TemplatesPanel() {
        //setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // setBackground(BACKGROUND_COLOR);

        Border border = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(border, "Templates");
        setBorder(titled);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String txt = String.format("<html><body style=\"text-align:justify ;  text-justify: inter-word;\">%s</body></html>",
                "Drag and drop the template name onto the table or the chart");
        label = new JLabel(txt);
        label.setPreferredSize(new Dimension(140,50));
    
        
        add(label);
        
        list = new JList<String>(Templates.getTemplatesNamesList());

        list.setVisibleRowCount(8);
        list.setDragEnabled(true);
        
        templatesList = new JScrollPane(list);
        add(new JScrollPane(templatesList));
    }
}

