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
        String themeName;
        FlatLaf theme = new FlatLaf();
        if(!FlatLaftheme.isLafDark()) {
            themeName = FlatLaf.getClass().getName();
        }
        
        return themeName;
    }
    
    // Get FlatLaf theme type method
    public static boolean getCurrentThemeType() {
        boolean isDarkTheme = FlatLaf.isLafDark();
        
        return isDarkTheme; // false -> light, true -> dark
    }
}
