/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
 */

/*
 *******************************************************************************
 * Orion IDE application main class
 *******************************************************************************
 * Not return values from methods
 *******************************************************************************
 */
package orion.ide;

import orion.ide.ui.MainWindow;
import orion.ide.ui.PreloaderWindow;
import orion.ide.ui.ThemeManager;
import orion.ide.core.SettingsManager;
import javax.swing.*;

public class MainApp {
    
    private static final SettingsManager settings = new SettingsManager();
    
    // Entry point method in application
    public static void main(String[] args) {
        
        // Init FlatLaf theme at application start
        settings.init();
        int currentThemeID = Integer.parseInt(settings.getParam("Appearance", "currentTheme"));
        
        ThemeManager uiThemeManager = new ThemeManager();
        uiThemeManager.init(currentThemeID);
        
        // Run code by timer
        javax.swing.SwingUtilities.invokeLater(() -> {
            
            // First show preloader window
            PreloaderWindow preloader = new PreloaderWindow();
            preloader.show(true);
            
            // Set preloader timer to 5 seconds
            final int delay = 5000;
            Timer timer;
            timer = new Timer(delay, e -> {
                
                // Close preloader window
                preloader.show(false);
                preloader.close();
                
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