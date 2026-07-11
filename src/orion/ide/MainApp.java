/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
*/
package orion.ide;

import orion.ide.ui.MainWindow;
import orion.ide.ui.PreloaderWindow;
import javax.swing.*;

// Orion IDE application main class
public class MainApp {
    
    // Entry point in application
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            
            // First show preloader window
            PreloaderWindow preloader = new PreloaderWindow();
            preloader.setVisible(true);
            
            // Set preloader timer to 5 second
            int delay = 5000;
            Timer timer;
            timer = new Timer(delay, e -> {
                
                // Close preloader window
                preloader.setVisible(false);
                preloader.dispose();
                
                // Show application main window
                MainWindow mainWindow = new MainWindow();
                mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
                mainWindow.setVisible(true);
            });
            
            timer.setRepeats(false); // Run this once
            timer.start(); // Initualize preloader timer
        });
    }
}