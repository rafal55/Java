package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

import java.util.*;
import java.io.IOException;

class DataSheetPlotPanel extends JPanel implements ComponentListener, TableModelListener {
    //private static final int DEFAULT_WIDTH = 300;
    //private static final int DEFAULT_HEIGHT = 50;
    //private static final Color BACKGROUND_COLOR = Color.BLACK;
    private Object[][] cells;
    private String[] columnsName;
    private JTable table;
   
    
    public DataSheetPlotPanel() {
        
        
        
        Border border = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(border, "Plot");
        setBorder(titled);
        
        setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(100, 400));
        
        cells = new Object[][] {{"0"}};
        columnsName = new String[] {"Stand still"};  
        
        DefaultTableModel tableModel = new DefaultTableModel(cells, columnsName);
 

        table = new JTable(tableModel);
       
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.setDragEnabled(true);
        table.setDropMode(DropMode.INSERT_COLS);
        table.setTransferHandler(new TemplatesPlotTransferHandler());
        
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        //table.setRowHeight(100);
        JScrollPane scrollTable = new JScrollPane(table);
        //table.setFillsViewportHeight(true);
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        table.getModel().addTableModelListener(this);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //table.showVerticalLines(false);
        /*
        
        tableModel.addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
              plot.update(table);
            }
        });
        */
        addComponentListener(this);
        add(scrollTable);

    }

    public void componentResized(ComponentEvent e){
      JSplitPane topPane = (JSplitPane) getParent();
      System.out.println("Table resized");
      int height = topPane.getTopComponent().getHeight()-50;
      table.setRowHeight(height);
    }
    public void componentMoved(ComponentEvent e){}
    public void componentShown(ComponentEvent e){}
    public void componentHidden(ComponentEvent e){}
    
    public void tableChanged(TableModelEvent e){
      table.repaint();
    }

    public JTable getTable() {
        return table;
    }
}
class MyTableCellRenderer extends JPanel implements TableCellRenderer {
    String[][] data;
    int column;
    JTable table;
    private void setData(String templateName){
      data = TListModel.getTemplate(templateName);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
              this.column = column;
              this.table = table;
              //System.out.println("roww "+ row +", col "+ column + ", header "+ table.getColumnName(column));
              setData((String) table.getColumnName(column));
 
        return this;
    }

    public void paintComponent(Graphics g) {
         
        g.setColor(Color.BLACK);
        
        int time_step = 15;
        int panelHeight = getHeight();
        int s_x, s_y, e_x, e_y;
        int column_final_width=0;
        

        int row;
        try{
            row = data.length;
            s_x = 0;
            s_y =  panelHeight - (int) Double.parseDouble(data[0][1]);
            for(int i = 0; i < row; i++) {
              System.out.println(row);
                e_x = (i+1) * time_step;
                e_y = panelHeight - (int) Double.parseDouble(data[i][1]);
              
                g.drawLine(s_x, s_y, e_x , e_y);
                s_x = e_x;
                s_y = e_y;
                column_final_width = e_x;
            }
            
            table.getColumnModel().getColumn(column).setPreferredWidth(column_final_width + 1);
           
        }catch (NullPointerException ex){
        System.out.println("No data to render");
        }
    }
}
class TemplatesPlotTransferHandler extends TransferHandler {

    // support for drag
    public int getSourceActions(JComponent source) {
        return COPY;
    }

    @Override
    protected Transferable createTransferable(JComponent source) {
        
        JList<String> list;

        try {
            System.out.println("createTransferable..." );
            list = (JList<String>) source;
            
        int index = list.getSelectedIndex();
        if (index < 0)
            return null;
        String txt = (String) list.getSelectedValue();
        
        return new StringTransferable(txt);
        
        } catch (ClassCastException ex) {
            return null;
        }
    }

    protected void exportDone(JComponent source, Transferable data,
            int action) {
        System.out.println("Export done");
    }

    // support for drop
    public boolean canImport(TransferSupport support) {

        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    public boolean importData(TransferSupport support) {
        JTable table = (JTable) support.getComponent();
        // DefaultListModel<ImageIcon> model = (DefaultListModel<ImageIcon>)
        // list
        // .getModel();
        // System.out.println("support.getComponent() " +
        // support.getComponent());
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        Transferable transferable = support.getTransferable();
        /*
         * List<DataFlavor> flavors = Arrays
         * .asList(transferable.getTransferDataFlavors());
         * 
         * List<Image> images = new ArrayList<>();
         */
        try {
            int col_n;
            if (support.isDrop()) {
                JTable.DropLocation location = (JTable.DropLocation) support
                        .getDropLocation();
                col_n = location.getColumn();

                String templateName = (String) transferable
                        .getTransferData(DataFlavor.stringFlavor);
                String[][] data = TListModel.getTemplate(templateName);

                System.out.println(
                        "Setting value of " + templateName + " at column " + col_n);

                
                    
                    
                    TableColumn col = new TableColumn(model.getColumnCount());
                    col.setHeaderValue(templateName);
                    table.addColumn(col);
                    model.addColumn(templateName, new Object[1]);
                    table.moveColumn(table.getColumnCount()-1, col_n);
                

                model.fireTableDataChanged();

            }
        } catch (IOException | UnsupportedFlavorException ex) {

            return false;
        }
        return true;
    }
}
class StringTransferable implements Transferable {
    
    private String theString;
    
    public StringTransferable(String s) {
        theString = s;
    }
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.stringFlavor};
    }
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.stringFlavor);
    }
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException {
        if (flavor.equals(DataFlavor.stringFlavor)) {
            return theString;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}

