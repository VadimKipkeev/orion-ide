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
import com.formdev.flatlaf.FlatDarkLaf;

// Orion IDE application main class
public class MainApp {
    
    // Set FlatLaf theme
    public static boolean isDarkTheme;
    
    // Entry point in application
    public static void main(String[] args) {
        
        // Initualize FlatLaf Swing theme
        if(isDarkTheme == false) {
            try {
                FlatLightLaf.setup(); // Light theme enabled
            } catch (Exception ex) {
                System.err.println("FlatLaf library not loaded!");
            }
        } else {
            try {
                FlatDarkLaf.setup(); // Dark theme enabled
            } catch (Exception ex) {
                System.err.println("FlatLaf library not loaded!");
            }
        }
        
        // Run code by timer
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