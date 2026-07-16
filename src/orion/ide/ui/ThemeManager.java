/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
 */

/*
 *******************************************************************************
 * Theme manager class
 *******************************************************************************
 * Control application FlatLaf Swing themes
 *******************************************************************************
 */
package orion.ide.ui;

import javax.swing.UIManager;
import javax.swing.LookAndFeel;
import com.formdev.flatlaf.FlatLaf;
import orion.ide.ui.theme.FlatLafVSLight;
import orion.ide.ui.theme.FlatLafVSDark;

public class ThemeManager {
    
    // Current theme name
    public static String currentThemeName;
    
    // FlatLaf theme init method
    public final boolean init(int themeID) {
        
        // Set FlatLaf theme by ID
        switch(themeID) {
            case 0 -> {
                FlatLafVSLight.setup();
                return true;
            }
            
            case 1 -> {
                FlatLafVSDark.setup();
                return true;
            }
            
            default -> {
                FlatLafVSLight.setup();
                return true;
            }
        }
    }
    
    // Get FlatLaf theme name method
    public static String getCurrentThemeName() {
        final String themeName;
        final LookAndFeel currentTheme = UIManager.getLookAndFeel();
        
        if(currentTheme instanceof FlatLaf) {
            themeName = currentTheme.getName();
            return themeName;
        } else {
            System.err.println("Current look and feel of application is not FlatLaf!");
            return null;
        }
    }
    
    // Get FlatLaf theme type method
    public static boolean getCurrentThemeType() {
        boolean isDarkTheme = FlatLaf.isLafDark();
        
        return isDarkTheme; // false -> light, true -> dark
    }
}
