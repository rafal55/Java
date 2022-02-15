package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import java.io.IOException;

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

class TemplatesTransferHandler extends TransferHandler {

    // support for drag
    public int getSourceActions(JComponent source) {
        return COPY;
    }

    @Override
    protected Transferable createTransferable(JComponent source) {
        
        JList<String> list;

        try {

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
            int row;
            if (support.isDrop()) {
                JTable.DropLocation location = (JTable.DropLocation) support
                        .getDropLocation();
                row = location.getRow();

                String templateName = (String) transferable
                        .getTransferData(DataFlavor.stringFlavor);
                String[][] data = Templates.getValue(templateName);

                System.out.println(
                        "Setting value of " + templateName + " at row " + row);

                for (String[] s : data) {
                    model.insertRow(row++, s);
                }

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