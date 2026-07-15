/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
*/
package orion.ide.ui;

import com.formdev.flatlaf.FlatLaf;
import orion.ide.ui.theme.FlatLafVSLight;
import orion.ide.ui.theme.FlatLafVSDark;

// FlatLaf theme manager class
public class ThemeManager {
    
    // Current theme name
    public static String currentThemeName = new String();
    
    // FlatLaf theme init
    public boolean init(int themeID) {
        
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
    
    // Get FlatLaf theme name
    public static String getCurrentThemeName() {
        String themeName = new String();
        
        if(!FlatLaf.isLafDark()) {
            themeName = FlatLafVSLight.THEME_NAME;
        } else {
            themeName = FlatLafVSDark.THEME_NAME;
        }
        
        return themeName;
    }
    
    // Get FlatLaf theme type method
    public static boolean getCurrentThemeType() {
        boolean isDarkTheme = FlatLaf.isLafDark();
        
        return isDarkTheme; // false -> light, true -> dark
    }
}
