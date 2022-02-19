package GUI;

import java.util.*;
import javax.swing.*;

public class TListModel extends AbstractListModel<String> {
    /**
     * 
    */
    private static Map<String, String[][]> templatesMap;
    private static String[][] data;

    // Static block
    static {

        templatesMap = new LinkedHashMap<String, String[][]>();
        
        data = new String[][]{
                {"0", "-77", "0", "0", "0", "0", "0", "0","Standstill"}
        };
        templatesMap.put("Standstill", data);
        
        data = new String[][]{
                {"0", "-77", "0", "0", "0", "0", "1", "0","Start up Idle"},
                {"30", "-77", "0", "0", "0", "0", "1", "0",""},
                {"100", "-50", "0", "0", "20.4", "15.8", "0", "0.25",""}
        };
        templatesMap.put("Start up Idle", data);
        
        data = new String[][]{
                { "0", "-77", "0",   "0",   "0",   "0",   "0",   "0",   "Motor roll"},
                { "1.6", "-77", "0",   "0",   "0",   "0",   "0",   "0",   ""},
                { "0",   "-77", "0",   "0",   "0",   "0",    "",   "0",   "Motor roll step 1"},
                { "16.6", "-77", "0",   "0",   "0",   "0",    "",   "0",   ""},
                { "50",  "-77", "0",   "0",   "0",   "0",    "",   "0",   "Motor roll step 2"},
                { "3.3",   "-77", "0",   "0",   "0",  "0",   "",    "0",   ""},
                { "1.6", "-77", "0",   "0",   "0",   "0",    "",  "0",  ""},
                { "0",   "-77", "0",   "0",   "0",   "0",    "",  "0",   ""}};
        templatesMap.put("Motor roll", data);
        
        data = new String[][]{
                { "100", "-50", "0",   "0",   "0",   "0",   "0",   "0",   "Idle"},
                { "100", "-50", "0",   "0",   "0",   "0",    "",   "0",   ""},
                { "100", "-50", "0",   "0",   "0",   "0",    "",   "0",   ""},
                { "100", "-50", "0",   "0",   "0",   "0",    "",   "0",   ""}};
        templatesMap.put("Idle", data);
    }

    public TListModel() {
    }

    public static String[][] getTemplate(String name) {

        return templatesMap.get(name);
    }

    @Override
    public int getSize() {

        return templatesMap.keySet().size();
    }

    @Override
    public String getElementAt(int index) {

        return (String) templatesMap.keySet().toArray()[index];
    }

}
