package GUI;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class DataSheetPanel extends JPanel {
    //private static final int DEFAULT_WIDTH = 300;
    //private static final int DEFAULT_HEIGHT = 50;
    //private static final Color BACKGROUND_COLOR = Color.BLACK;
    private Object[][] cells;
    private String[] columnsName;
    private JTable table;
    private Plot plot;
    
    public DataSheetPanel(Plot plot) {
        
        this.plot = plot;
        
        Border border = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(border, "Data sheet");
        setBorder(titled);
        
        setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(100, 100));
        
        cells = new Object[][] {
                {0, 0, 0, 0, 0, 0, 0, 0,"stand still"},
                {50, 0, 0, 0, 0, 0, 0, 0, " "},
                {100, 0, 0, 0, 0, 0, 1, 0, "full speed"}
        };
        columnsName = new String[] {"Speed", "VIGV", "GT Load", "ST Load", "Gas flow", "Oil flow","S", "OH", "Comment"};  
        
        DefaultTableModel tableModel = new DefaultTableModel(cells, columnsName);
        

        table = new JTable(tableModel);
        table.setDragEnabled(true);
        table.setDropMode(DropMode.INSERT_ROWS);
        table.setTransferHandler(new TemplatesTransferHandler());
        JScrollPane scrollTable = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        tableModel.addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
              plot.update(table);
            }
        });
        

        add(scrollTable);

    }
    
    public JTable getTable() {
        return table;
    }
    
}
