/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 ******************************************************************************
 * FlatLaf Visual Studio like dark theme class
 *******************************************************************************
 * Setup FlatLaf theme and return theme name
 *******************************************************************************
 */
package orion.ide.ui.theme;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatDarkLaf;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class FlatLafVSDark extends FlatDarkLaf {
    
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */
    // Set theme name
    public static final String THEME_NAME = "FlatLafVSDark"; // Constant value
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    // Register FlatLaf theme : method
    public static boolean setup() {
        try {
            FlatLaf.registerCustomDefaultsSource("resources/themes");
            boolean isRegister = setup(new FlatLafVSDark());
            
            if(!isRegister) {
                System.err.println("Can't loaded " + THEME_NAME + " theme!");
            }
            
            return isRegister;
        } catch(Exception ex) {
            System.err.println("Can't loaded FlatLafVSDark theme!");
            return false;
        }
    }
    
    // Get theme name : method
    @Override
    public String getName() {
        return THEME_NAME;
    }
}