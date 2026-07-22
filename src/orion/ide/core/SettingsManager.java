/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
 */

/*
 *******************************************************************************
 * Settings manager class
 *******************************************************************************
 * Read and store settings params
 *******************************************************************************
 */
package orion.ide.core;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.ini4j.Wini;

public class SettingsManager {
    private static final String CFG_FILE_NAME = "settings.ini"; // File name constant
    private static Wini ini; // INI control object
    
    // Init class method
    public void init() {
        
        // Check settings file to exists
        File settingsFile = new File(System.getProperty("user.home") + "/Orion IDE/Config/" + CFG_FILE_NAME); // Full path to settings file
        
        if(!settingsFile.exists()) {
            File settingsFilePath = new File(System.getProperty("user.home") + "/Orion IDE/Config"); // Settings file parent folders
            
            try {
                settingsFilePath.mkdirs(); // Create folders for settings file
                settingsFile.createNewFile(); // Create empty settings file
                writeSettingsByTemplate(); // Write template data to new settings file
                
                ini = new Wini(settingsFile); // Create INI control object
            } catch (IOException ex) {
                System.getLogger(SettingsManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            try {
                ini = new Wini(settingsFile); // Create INI control object
            } catch (IOException ex) {
                System.getLogger(SettingsManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    // Read param from settings file method
    public String getParam(String paramsGroup, String paramName) {
        String result = ini.get(paramsGroup, paramName);
        return result;
    }
    
    // Store param to settings file method
    public void storeParam(String paramsGroup, String param, String value) {
        try {
            ini.put(paramsGroup, param, value);
            ini.store();
        } catch (IOException ex) {
            System.getLogger(SettingsManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    // Write new settings file by template function
    private static void writeSettingsByTemplate() {
        
        /* Settings file template:
         * [Appearance]
         * currentTheme = 0
         * currentEditorStyle = 0
         * currentFontSize = 12
         * [Git]
         * gitLogin = ""
         * gitPassword = ""
         * gitToken = ""
         * {Build}
         * neptuneSDKPath = ""
         * m-coreSDKPath = ""
         */
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/Orion IDE/Config/settings.ini"))){
            fileWriter.write("[Appearance]");
            fileWriter.newLine();
            fileWriter.write("currentTheme = 0");
            fileWriter.newLine();
            fileWriter.write("currentEditorStyle = 0");
            fileWriter.newLine();
            fileWriter.write("currentFontSize = 12");
            fileWriter.newLine();
            fileWriter.write("[Git]");
            fileWriter.newLine();
            fileWriter.write("gitLogin =");
            fileWriter.newLine();
            fileWriter.write("gitPassword =");
            fileWriter.newLine();
            fileWriter.write("gitToken =");
            fileWriter.newLine();
            fileWriter.write("[Build]");
            fileWriter.newLine();
            fileWriter.write("neptuneSDKPath =");
            fileWriter.newLine();
            fileWriter.write("m-coreSDKPath =");
        } catch (IOException ex) {
            System.getLogger(SettingsManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
