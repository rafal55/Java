package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    //private Color bkgColor = new Color(51, 52, 54);
    private Plot plot;
    private DataSheetPanel dataSheetPanel;
    
    public MainFrame() {
        
        setTitle("Load Scheduler");
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setMinimumSize(new Dimension(screenSize.width / 2 + 200, screenSize.height / 2 + 200));
        setLocationByPlatform(true);
        
        plot = new Plot();
        dataSheetPanel = new DataSheetPanel(plot);
        
        
        add(new HeaderPanel(), BorderLayout.NORTH);
        add(new ToolsPanel(), BorderLayout.EAST);
        add(new TemplatesPanel(), BorderLayout.WEST);
        JSplitPane splitPlot = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,  plot, dataSheetPanel);
        add(splitPlot);
            
        setJMenuBar(new MenuBar());
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        //RepaintManager.currentManager(getRootPane()).setDoubleBufferingEnabled(false);
        //((JComponent) getContentPane()).setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
        
     
    }
    
    public Plot getPlot() {
        return plot;
    }
    
    public DataSheetPanel getDataSheetPanel() {
        return dataSheetPanel;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new MainFrame();
            }
        });
    }

}
