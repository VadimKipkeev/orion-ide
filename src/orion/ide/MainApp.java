/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 *******************************************************************************
 * Orion IDE application main class
 *******************************************************************************
 * Not return values from methods
 *******************************************************************************
 */
package orion.ide;

/* 
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import orion.ide.ui.MainWindow;
import orion.ide.ui.PreloaderWindow;
import orion.ide.ui.ThemeManager;
import orion.ide.core.SettingsManager;
import javax.swing.*;
/*
 *------------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class MainApp {
    
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */
    // Create settings manager object
    private static final SettingsManager settingsManager = new SettingsManager();
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */

    // Entry point method in application
    public static void main(String[] args) {
        
        // Init FlatLaf theme at application start
        settingsManager.init();
        int currentThemeID = Integer.parseInt(settingsManager.getParam("Appearance", "currentTheme"));
        
        ThemeManager uiThemeManager = new ThemeManager();
        uiThemeManager.init(currentThemeID);
        
        // Run code by timer
        javax.swing.SwingUtilities.invokeLater(() -> {
            
            // First show preloader window
            PreloaderWindow preloaderWindow = new PreloaderWindow();
            preloaderWindow.show(true);
            
            // Set preloader timer to 5 seconds
            final int delay = 5000;
            Timer timer;
            timer = new Timer(delay, e -> {
                
                // Close preloader window
                preloaderWindow.show(false);
                preloaderWindow.close();
                
                // Show application main window
                MainWindow mainWindow = new MainWindow();
                mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
                mainWindow.setVisible(true);
            });
            
            timer.setRepeats(false); // Run this once
            timer.start(); // Initualize preloader timer
        });
    }
}