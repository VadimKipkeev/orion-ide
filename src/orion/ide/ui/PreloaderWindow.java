/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
*/
package orion.ide.ui;

import javax.swing.*;
import java.awt.*;

// Preloader window class
public class PreloaderWindow extends JWindow {
    
    // Preloader background image
    final Image image;
    
    public PreloaderWindow() {
        
        // Set preloader background image
        image = new ImageIcon(getClass().getResource("/resources/images/preloader.png")).getImage();
        
        // Get preloader background image
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);
        
        // Set preloader window size by image dimensions
        this.setSize(imgWidth, imgHeight);
        
        // Set preloader background image position
        this.setLocationRelativeTo(null);   
    }
    
    @Override
    public void paint(Graphics graph) {
        
        // Show image in preloader window
        graph.drawImage(image, 0, 0, this);
    }
}