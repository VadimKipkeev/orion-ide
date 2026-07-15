/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
*/
package orion.ide.ui.theme;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatDarkLaf;

// Set FlatLafVSDark theme
public class FlatLafVSDark extends FlatDarkLaf {
    
    // Set theme name
    public static final String THEME_NAME = "FlatLafVSDark";
    
    // Register FlatLaf theme
    public static boolean setup() {
        try {
            FlatLaf.registerCustomDefaultsSource("resources/themes");
            boolean isOk = setup(new FlatLafVSDark());
            
            if(!isOk) {
                System.err.println("Can't loaded " + THEME_NAME + " theme!");
            }
            
            return isOk;
        } catch(Exception ex) {
            System.err.println("Can't loaded FlatLaf theme!");
            return false;
        }
    }
    
    // Get theme name method
    @Override
    public String getName() {
        return THEME_NAME;
    }
}
