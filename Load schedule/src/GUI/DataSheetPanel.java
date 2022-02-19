package GUI;

import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.datatransfer.*;

import java.io.IOException;

public class DataSheetPanel extends JPanel {
    // private static final int DEFAULT_WIDTH = 300;
    // private static final int DEFAULT_HEIGHT = 50;
    // private static final Color BACKGROUND_COLOR = Color.BLACK;
    private Object[][] cells;
    private String[] columnsName;
    private JTable table;

    public DataSheetPanel() {

        Border border = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(border, "Data sheet");
        setBorder(titled);

        setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(100, 100));

        cells = new Object[][]{{0, 0, 0, 0, 0, 0, 0, 0, "stand still"},

        };
        columnsName = new String[]{"Speed", "VIGV", "GT Load", "ST Load",
                "Gas flow", "Oil flow", "S", "OH", "Comment"};

        DefaultTableModel tableModel = new DefaultTableModel(cells,
                columnsName);

        table = new JTable(tableModel);
        table.setDragEnabled(true);
        table.setDropMode(DropMode.INSERT_ROWS);
        table.setTransferHandler(new TemplatesTransferHandler());
        JScrollPane scrollTable = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollTable);

        tableModel.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                JSplitPane split = (JSplitPane) getParent();
                split.getTopComponent().repaint();
            }
        });

    }

    public JTable getTable() {
        return table;
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
                    String[][] data = TListModel.getTemplate(templateName);

                    System.out.println("Setting value of " + templateName
                            + " at row " + row);

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

}
