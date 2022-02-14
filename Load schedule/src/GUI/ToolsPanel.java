package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.HashMap;

public class ToolsPanel extends JPanel {
    
    //private static final int DEFAULT_WIDTH = 50;
    //private static final int DEFAULT_HEIGHT = 200;
    //private static final Color BACKGROUND_COLOR = Color.GREEN;
    private HashMap<String,String> feelAndLook;
    private JComboBox<String> comboThemes;
    
    public ToolsPanel() {
        //setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //setBackground(BACKGROUND_COLOR);
        Border border = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(border, "Tools");
        setBorder(titled);
        
        feelAndLook = new HashMap<>();
        feelAndLook.put("Metal", "javax.swing.plaf.metal.MetalLookAndFeel");
        feelAndLook.put("Nimbus", "javax.swing.plaf.nimbus.NimbusLookAndFeel");
        feelAndLook.put("CDE/Motif", "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        feelAndLook.put("Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        feelAndLook.put("Windows Classic", "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        
        comboThemes = new JComboBox<String>();
        comboThemes.addItem("Metal");
        comboThemes.addItem("Nimbus");
        comboThemes.addItem("CDE/Motif");
        comboThemes.addItem("Windows");
        comboThemes.addItem("Windows Classic");
        
        
        comboThemes.addActionListener(event ->
                
                {
                    try {
                        UIManager.setLookAndFeel(feelAndLook.get(comboThemes.getItemAt(comboThemes.getSelectedIndex())));
                    } catch (ClassNotFoundException e) {
                        
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        
                        e.printStackTrace();
                    } catch (UnsupportedLookAndFeelException e) {
                        
                        e.printStackTrace();
                    }
                    SwingUtilities.updateComponentTreeUI(getParent().getParent());    
                }); 
        
        add(new JLabel("Themes"));
        add(comboThemes);
    }
}
