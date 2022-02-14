package GUI;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class DataSheetPanel extends JPanel {
    //private static final int DEFAULT_WIDTH = 300;
    //private static final int DEFAULT_HEIGHT = 50;
    //private static final Color BACKGROUND_COLOR = Color.BLACK;
    private Object[][] cells;
    private String[] columnsName;
    private JTable table;
    
    public DataSheetPanel() {
        
        Border border = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(border, "Data sheet");
        setBorder(titled);
        
        setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(100, 100));
        
        cells = new Object[][] {
                {0, 0, 0, 0, 0, 0, "stand still"},
                {50, 0, 0, 0, 0, 0, "half speed"},
                {100, 0, 0, 0, 0, 0, "full speed"}
        };
        columnsName = new String[] {"Speed", "VIGV", "GT Load", "ST Load", "Gas flow", "Oil flow", "Comment"};  
        
        table = new JTable(cells, columnsName);
        table.setDragEnabled(true);
        
        JScrollPane scrollTable = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollTable);
        
        
    }
}
