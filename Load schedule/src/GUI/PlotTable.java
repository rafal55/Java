package GUI;


import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.*;

public class PlotTable extends JPanel {

    private Object[][] cells;
    private String[] columnsName;
    private JTable table;

    public PlotTable(JPanel dataSheet) {

       cells = new Object[][]{{0, 0, 0, 0, 0, 0, 0, 0, "stand still"},

        };
        columnsName = new String[]{"Speed", "VIGV", "GT Load", "ST Load",
                "Gas flow", "Oil flow", "S", "OH", "Comment"};

        DefaultTableModel tableModel = new DefaultTableModel(cells,
                columnsName);
        table = new JTable(tableModel);
        table.setRowHeight(600);
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        table.setFillsViewportHeight(true);
        JScrollPane scrollTable = new JScrollPane(table);
        add(scrollTable);

        setVisible(true);
 
    }


    public JTable getTable() {
        return table;
    }
}

class MyTableCellRenderer extends JPanel implements TableCellRenderer {
    


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
 
        
        return this;
    }

    public void paintComponent(Graphics g) {
        
        g.setColor(Color.BLACK);
        
        int time_step = 15;
        int panelHeight = getHeight();
        int s_x, s_y, e_x, e_y;
        s_x = 0;
        s_y = (int) panelHeight;
        
        int row;
        
            row = 5;
            
            for(int i = 0; i < row; i++) {

                e_x = (i+1) * time_step;
                //String st = table.getValueAt(i, 0).toString();
                e_y = panelHeight - (int) Double.parseDouble("23");
                g.drawLine(s_x, s_y, e_x , e_y);
                s_x = e_x;
                s_y = e_y; 
            }
    }



}
