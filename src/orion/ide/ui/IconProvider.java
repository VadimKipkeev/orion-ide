/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 * -----------------------------------------------------------------------------
 * Icon provider class
 * -----------------------------------------------------------------------------
 * Control icons UI resources
 * -----------------------------------------------------------------------------
 */
package orion.ide.ui;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.UIManager;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class IconProvider {

    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */
    
    /*
     * -------------------------------------------------------------------------
     * PRIVATE CLASS FIELDS
     * -------------------------------------------------------------------------
     */

    // Base icon files path
    private static final String BASE_PATH = "resources/icons/";
    
    // Icons cache for store icon resources
    private static final Map<String, FlatSVGIcon> iconCache = new HashMap<>();

    static {
        UIManager.addPropertyChangeListener(evt -> {
            if ("lookAndFeel".equals(evt.getPropertyName())) {
                clearCache();
            }
        });
    }
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */

    /*
     * -------------------------------------------------------------------------
     * PUBLIC CLASS METHODS
     * -------------------------------------------------------------------------
     */

    /*
     * This is anonimus class, not contains constructor method
     */
    
    // Get icon by default size : method
    public static Icon getIcon(String name) {
        return getIcon(name, -1, -1);
    }

    // Get icon by custom size : method
    public static Icon getIcon(String name, int size) {
        return getIcon(name, size, size);
    }
    
    // Get icon by custom width and height size : method
    public static Icon getIcon(String name, int width, int height) {
        if (!name.endsWith(".svg")) {
            name += ".svg";
        }

        // Set icon folder by FlatLaf theme type
        String themeFolder = FlatLaf.isLafDark() ? "dark/" : "light/";
        String fullPath = BASE_PATH + themeFolder + name;
        
        // Set icons cache
        String cacheKey = String.format("%s%s_%dx%d", themeFolder, name, width, height);

        return iconCache.computeIfAbsent(cacheKey, (String key) -> {
            try {
                FlatSVGIcon svgIcon = new FlatSVGIcon(fullPath);
                
                if (width > 0 && height > 0) {
                    svgIcon = svgIcon.derive(width, height);
                }

                return svgIcon;
            } catch (Exception e) {
                System.err.println("Error SVG icon loading (" + themeFolder.trim() + "): " + fullPath);
            }
            return null;
        });
    }
    
    // Clean icons cache : method
    public static void clearCache() {
        iconCache.clear();
    }
}