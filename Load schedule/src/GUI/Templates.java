package GUI;

import java.util.*;

public final class Templates extends LinkedHashMap<String, String[][]> {
    /**
     * 
     */
    private static Map<String, String[][]> templatesMap;
    private static String[][] data;
    
   //Static block
    static {
        templatesMap = new LinkedHashMap<String, String[][]>();

        data = new String[][]{
                {"0", "-77", "0", "0", "0", "0", "0", "0", "Standstill"}};
        templatesMap.put("Standstill", data);

        data = new String[][]{
                {"30", "-50", "0", "0", "0", "0", "1", "0", "Start up Idle"},
                {"100", "-50", "0", "0", "20.4", "15.8", "0", "0.25", " "}};
        templatesMap.put("Start up Idle", data);
        
        data = new String[][]{
                { "1.6", "-77", "0",   "0",   "0",   "0",   "0",   "0",   "Motor roll"},
                { "0",   "-77", "0",   "0",   "0",   "0",    "",   "0",   "Motor roll step 1"},
                { "16.6", "-77", "0",   "0",   "0",   "0",    "",   "0",   ""},
                { "50",  "-77", "0",   "0",   "0",   "0",    "",   "0",   "Motor roll step 2"},
                { "3.3",   "-77", "0",   "0",   "0",  "0",   "",    "0",   ""},
                { "1.6", "-77", "0",   "0",   "0",   "0",    "",  "0",  ""},
                { "0",   "-77", "0",   "0",   "0",   "0",    "",  "0",   ""}};
        templatesMap.put("Motor roll", data);
    }   
 
    public Templates() { }
    

    public static String[] getTemplatesNamesList()
    {
        String[] string = new String[templatesMap.keySet().size()];
        
        return templatesMap.keySet().toArray(string);
    }
    
    public static String[][] getValue(String key){
        
        return templatesMap.get(key);
    }

}
