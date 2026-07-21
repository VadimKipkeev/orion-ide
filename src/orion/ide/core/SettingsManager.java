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
    private static File settingsFile;
    private static Wini ini;
    
    // Init class method
    public void init() {
        try {
            settingsFile = new File(System.getProperty("user.home") + "/Orion IDE/Config/" + CFG_FILE_NAME);
            
            // Check settings file to exists
            if(settingsFile != null) {
                ini = new Wini(settingsFile);
            } else {
                System.err.println("Configuration file is not exists!");
            }
        } catch (IOException ex) {
            System.getLogger(SettingsManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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
}
