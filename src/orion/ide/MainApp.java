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
import com.formdev.flatlaf.FlatLightLaf;

// Orion IDE application main class
public class MainApp {
    
    // Entry point in application
    public static void main(String[] args) {
        
        // Initualize FlatLaf Swing theme
        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("FlatLaf library not loaded!");
        }
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            
            // First show preloader window
            PreloaderWindow preloader = new PreloaderWindow();
            preloader.show(true);
            
            // Set preloader timer to 5 second
            int delay = 5000;
            Timer timer;
            timer = new Timer(delay, e -> {
                
                // Close preloader window
                preloader.show(false);
                preloader.close();
                
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