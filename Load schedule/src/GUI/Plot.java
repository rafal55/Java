
package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;


public class Plot extends JPanel {
    /**
     * 
     */
    //private JTextArea textArea;
    private Color bkgColor = new Color(0, 0, 50);
    private JTable table;
    
    public Plot() {
        
        setLayout(new BorderLayout());
        setPreferredSize(getMaximumSize());
        setBackground(bkgColor);
      
        /*
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setDragEnabled(true);
        add(textArea);
        */
        }
    public void update(JTable table) {
        this.table = table;
        repaint();
    }
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        
        int time_step = 15;
        int panelHeight = getHeight();
        int s_x, s_y, e_x, e_y;
        s_x = 0;
        s_y = (int) panelHeight;
        
        int row;
        try {
            row = table.getRowCount();
            
            for(int i = 0; i < row; i++) {

                e_x = (i+1) * time_step;
                String st = table.getValueAt(i, 0).toString();
                e_y = panelHeight - (int) Double.parseDouble(st);
                g.drawLine(s_x, s_y, e_x , e_y);
                s_x = e_x;
                s_y = e_y; 
            }
        } catch(NullPointerException ex) {
            
        }
        
    }
}

