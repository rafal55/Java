package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        
        setTitle("Load Scheduler");
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setMinimumSize(new Dimension(screenSize.width / 2 + 200, screenSize.height / 2 + 200));
        setLocationByPlatform(true);
        
        setJMenuBar(new MenuBar());
        DataSheetPanel table = new DataSheetPanel();
        //PlotPanel plot = new PlotPanel(table);
        DataSheetPlotPanel plot = new DataSheetPlotPanel();
        
        add(new HeaderPanel(), BorderLayout.NORTH);
        add(new ToolsPanel(), BorderLayout.EAST);
        add(new TemplatesPanel(), BorderLayout.WEST);
        add(new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,  plot, table));
        //add(new PlotTable(dataSheetPanel));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        //RepaintManager.currentManager(getRootPane()).setDoubleBufferingEnabled(false);
        //((JComponent) getContentPane()).setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new MainFrame();
            }
        });
    }

}
