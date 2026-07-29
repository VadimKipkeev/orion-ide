/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 ******************************************************************************
 * FlatLaf Visual Studio like light theme class
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
import com.formdev.flatlaf.FlatLightLaf;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class FlatLafVSLight extends FlatLightLaf {
    
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */
    // Set FlatLaf theme name
    public static final String THEME_NAME = "FlatLafVSLight"; // Constant value
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    // Register FlatLaf theme : method
    public static boolean setup() {
        try {
            FlatLaf.registerCustomDefaultsSource("resources/themes");
            boolean isRegister = setup(new FlatLafVSLight());
            
            if(!isRegister) {
                System.err.println("Can't loaded " + THEME_NAME + " theme!");
            }
            
            return isRegister;
        } catch(Exception ex) {
            System.err.println("Can't loaded FlatLafVSLight theme!");
            return false;
        }
    }
    
    // Get theme name : method
    @Override
    public String getName() {
        return THEME_NAME;
    }
}