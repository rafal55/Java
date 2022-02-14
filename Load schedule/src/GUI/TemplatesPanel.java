package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemplatesPanel extends JPanel {
    //private static final int DEFAULT_WIDTH = 100;
    //private static final int DEFAULT_HEIGHT = 200;
    //private static final Color BACKGROUND_COLOR = Color.GRAY;
    private ImageList list1;
    private JList<String> list;
    
    public TemplatesPanel() {
        //setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //setBackground(BACKGROUND_COLOR);
        
        Border border = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(border, "Templates");
        setBorder(titled);
        setLayout(new GridLayout(8,1));
        add(new JLabel("List of templates                                                 "));
        
        
        list1 = new ImageList(Paths.get("./pictures"));
        
        //list = new JList<>(new String[]{"AAA", "BBB", "CCC", "DDD", "CCC"});
        //list.setVisibleRowCount(4);
        //list.setDragEnabled(true);
        //JScrollPane scrollList = new JScrollPane(list);
        
        //add(scrollList);
        add(new JScrollPane(list1));
    }
}

class ImageList extends JList<ImageIcon> {
    public ImageList(Path dir) {
        DefaultListModel<ImageIcon> model = new DefaultListModel<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir)) {
            for (Path entry : entries)
                model.addElement(new ImageIcon(entry.toString()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setModel(model);
        setVisibleRowCount(0);
        setLayoutOrientation(JList.HORIZONTAL_WRAP);
        setDragEnabled(true);
        setDropMode(DropMode.ON_OR_INSERT);
        setTransferHandler(new ImageListTransferHandler());
    }
}

class ImageListTransferHandler extends TransferHandler {
    // support for drag

    public int getSourceActions(JComponent source) {
        return COPY;
    }
    @Override
    protected Transferable createTransferable(JComponent source) {
        ImageList list = (ImageList) source;
        int index = list.getSelectedIndex();
        if (index < 0)
            return null;
        ImageIcon icon = list.getModel().getElementAt(index);
        return new ImageTransferable(icon.getImage());
    }
    protected void exportDone(JComponent source, Transferable data,
            int action) {
        if (action == MOVE) {
            ImageList list = (ImageList) source;
            int index = list.getSelectedIndex();
            if (index < 0)
                return;
            DefaultListModel<?> model = (DefaultListModel<?>) list.getModel();
            model.remove(index);
        }
    }

    // support for drop

    public boolean canImport(TransferSupport support) {
        if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            if (support.getUserDropAction() == MOVE)
                support.setDropAction(COPY);
            return true;
        } else
            return support.isDataFlavorSupported(DataFlavor.imageFlavor);
    }

    public boolean importData(TransferSupport support) {
        ImageList list = (ImageList) support.getComponent();
        DefaultListModel<ImageIcon> model = (DefaultListModel<ImageIcon>) list
                .getModel();

        Transferable transferable = support.getTransferable();
        List<DataFlavor> flavors = Arrays
                .asList(transferable.getTransferDataFlavors());

        List<Image> images = new ArrayList<>();

        try {
            if (flavors.contains(DataFlavor.javaFileListFlavor)) {
                @SuppressWarnings("unchecked")
                List<File> fileList = (List<File>) transferable
                        .getTransferData(DataFlavor.javaFileListFlavor);
                for (File f : fileList) {
                    try {
                        images.add(ImageIO.read(f));
                    } catch (IOException ex) {
                        // couldn't read image--skip
                    }
                }
            } else if (flavors.contains(DataFlavor.imageFlavor)) {
                images.add((Image) transferable
                        .getTransferData(DataFlavor.imageFlavor));
            }

            int index;
            if (support.isDrop()) {
                JList.DropLocation location = (JList.DropLocation) support
                        .getDropLocation();
                index = location.getIndex();
                if (!location.isInsert())
                    model.remove(index); // replace location
            } else
                index = model.size();
            for (Image image : images) {
                model.add(index, new ImageIcon(image));
                index++;
            }
            return true;
        } catch (IOException | UnsupportedFlavorException ex) {
            return false;
        }
    }
}

class ImageTransferable implements Transferable {
    private Image theImage;
    public ImageTransferable(Image image) {
        theImage = image;
    }
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.imageFlavor};
    }
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.imageFlavor);
    }
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException {
        if (flavor.equals(DataFlavor.imageFlavor)) {
            return theImage;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}