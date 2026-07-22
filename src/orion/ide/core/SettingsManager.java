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

import org.ini4j.Wini;
import java.io.File;
import java.io.IOException;

public class SettingsManager {
    
    // Settings file path constant
    private static final String CFG_FILE_NAME = "settings.ini";
    private static Wini ini;
    
    // Init class method
    public void init() {
        
        // Check settings file to exists
        File file = new File(System.getProperty("user.home") + "/Orion IDE/Config/" + CFG_FILE_NAME);
        
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.getLogger(SettingsManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
            try {
                ini = new Wini(file);
            } catch (IOException ex) {
                System.getLogger(SettingsManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
            writeSettingsByTemplate();
        } else {
            try {
                ini = new Wini(file);
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
    
    // Write new settings file by template
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
        
        String currentParamsGroup;
        
        currentParamsGroup = "Appearance";
        ini.put(currentParamsGroup, "currentTheme", "0");
        ini.put(currentParamsGroup, "currentEditorStyle", "0");
        ini.put(currentParamsGroup, "currentFontSize", "12");
        
        currentParamsGroup = "Git";
        ini.put(currentParamsGroup, "gitLogin", "");
        ini.put(currentParamsGroup, "gitPassword", "");
        ini.put(currentParamsGroup, "gitToken", "");
        
        currentParamsGroup = "Build";
        ini.put(currentParamsGroup, "neptuneSDKPath", "");
        ini.put(currentParamsGroup, "m-coreSDKPath", "");
    }
}
