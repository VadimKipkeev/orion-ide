/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 *******************************************************************************
 * Preloader window class
 *******************************************************************************
 * Show and close preloader window, needed for loading application resources
 *******************************************************************************
 */
package orion.ide.ui;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import javax.swing.*;
import java.awt.*;
import java.net.URL;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class PreloaderWindow {
    
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */
    
    // Preloader window
    private final JWindow preloaderWindow = new JWindow();
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    // Constructor
    public PreloaderWindow() {
        
        // Set preloader background image
        final URL imageURL;
        imageURL = getClass().getResource("/resources/images/preloader.png");
        
        // Check preloader background image to exists
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
        
        preloaderWindow.getContentPane().add(label, BorderLayout.CENTER);
        
        // Set preloader window size by background image dimensions
        preloaderWindow.pack();
        
        // Set preloader window location by center of screen
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenX = (screenSize.width - preloaderWindow.getWidth()) / 2;
        int screenY = (screenSize.height - preloaderWindow.getHeight()) / 2;
        preloaderWindow.setLocation(screenX, screenY);
    }
    
    // Show preloader window : method
    public void show(boolean flag) {
        preloaderWindow.setVisible(flag);
    }
    
    // Close preloader window : method
    public void close() {
        preloaderWindow.dispose();
    }
}