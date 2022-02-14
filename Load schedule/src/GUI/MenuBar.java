package GUI;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenu fileMenu,
                  editMenu,
                  helpMenu;
    private JFileChooser chooser;

       
    public MenuBar() {

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");
           
       Action newAction = new AbstractAction("New")//, new ImageIcon("./pictures/new.png"))
        {
            public void actionPerformed(ActionEvent event)
            {
            JOptionPane.showMessageDialog(null, "New action to be defined...");
            }
        };
       // newAction.putValue(Action.MNEMONIC_KEY, 'N');
        
        Action openAction = new AbstractAction("Open")//, new ImageIcon("./pictures/new.png"))
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        chooser.setCurrentDirectory(new File("."));
                        int result = chooser.showOpenDialog(MenuBar.this);
                        if (result == JFileChooser.APPROVE_OPTION)
                        {
                            String name = chooser.getSelectedFile().getPath();
                            JOptionPane.showMessageDialog(null, name);
                        }
                    }
                };
        //.putValue(Action.MNEMONIC_KEY, 'O');
        
        Action saveAction = new AbstractAction("Save")//, new ImageIcon("./pictures/new.png"))
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        chooser.setCurrentDirectory(new File("."));
                        int result = chooser.showSaveDialog(MenuBar.this);
                        if (result == JFileChooser.APPROVE_OPTION)
                        {
                            String name = chooser.getSelectedFile().getPath();
                            JOptionPane.showMessageDialog(null, name);
                        }
                    }
                };
        //saveAction.putValue(Action.MNEMONIC_KEY, 'S');  
        
        Action saveAsAction = new AbstractAction("Save as")//, new ImageIcon("./pictures/new.png"))
                {
                    public void actionPerformed(ActionEvent event)
                    {
                    JOptionPane.showMessageDialog(null, "Save as action to be defined...");
                    }
                };
        //saveAsAction.putValue(Action.MNEMONIC_KEY, 'A');        
                
        Action exitAction = new AbstractAction("Exit")//, new ImageIcon("./pictures/exit.png"))
        {
            public void actionPerformed(ActionEvent event)
            {
            System.exit(0);
            }
        };
        
        Action cutAction = new AbstractAction("Cut")//, new ImageIcon("./pictures/cut.png"))
        {
            public void actionPerformed(ActionEvent event)
            {
            JOptionPane.showMessageDialog(null, "Cut action to be defined...");
            }
        };
        
        Action copyAction = new AbstractAction("Copy")//, new ImageIcon("./pictures/copy.png"))
        {
            public void actionPerformed(ActionEvent event)
            {
            JOptionPane.showMessageDialog(null, "Copy action to be defined...");
            }
        };
        
        Action pasteAction = new AbstractAction("Paste")//, new ImageIcon("./pictures/paste.png"))
        {
            public void actionPerformed(ActionEvent event)
            {
            JOptionPane.showMessageDialog(null, "Paste action to be defined...");
            }
        };
        
        Action aboutAction = new AbstractAction("About")//, new ImageIcon("./pictures/paste.png"))
        {
            public void actionPerformed(ActionEvent event)
            {
            JOptionPane.showMessageDialog(null, "About action to be defined...");
            }
        };

        fileMenu.add(newAction).setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        fileMenu.add(openAction).setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        fileMenu.addSeparator();
        fileMenu.add(saveAction).setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        fileMenu.add(saveAsAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);

        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);
        
        helpMenu.add(aboutAction);
        
    
        add(fileMenu);
        add(editMenu);
        add(helpMenu);
        
        chooser = new JFileChooser();
    }
    

}
