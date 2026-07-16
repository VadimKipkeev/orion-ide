/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
 */

/*
 *******************************************************************************
 * Preloader window class
 *******************************************************************************
 * Show and close preloader window, needed for loading application resources
 *******************************************************************************
 */
package orion.ide.ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PreloaderWindow {
    
    // Preloader window
    private final JWindow window = new JWindow();
    
    // Constructor
    public PreloaderWindow() {
        
        // Set preloader background image
        final URL imageURL;
        imageURL = getClass().getResource("/resources/images/preloader.png");
        
        // Check preloader background image to exist
        if(imageURL == null) {
            System.err.println("Preloader image is not exists!");
        }
        
        final Image image = Toolkit.getDefaultToolkit().getImage(imageURL);
        final ImageIcon icon = new ImageIcon(image);
        
        // Attach image to preloader window
        final JLabel label = new JLabel(icon) {
            
            @Override
            protected void paintComponent(Graphics g) {
                
                // Set corrected scaling preloader background image
                Graphics2D g2 = (Graphics2D) g.create();
                
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } finally {
                    g2.dispose();
                }
            }
        };
        
        window.getContentPane().add(label, BorderLayout.CENTER);
        
        // Set preloader window size by background image dimensions
        window.pack();
        
        // Set preloader window location by center of screen
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenX = (screenSize.width - window.getWidth()) / 2;
        int screenY = (screenSize.height - window.getHeight()) / 2;
        window.setLocation(screenX, screenY);
    }
    
    // Show preloader window method
    public void show(boolean flag) {
        window.setVisible(flag);
    }
    
    // Close preloader window method
    public void close() {
        window.dispose();
    }
}