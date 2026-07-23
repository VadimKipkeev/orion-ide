/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
 */

/*
 *******************************************************************************
 * Custom iconic table UI control based on JList Swing component
 *******************************************************************************
 * Show table with icons and labels
 *******************************************************************************
 */
package JIconicTable;

// Grid item class for iconic table UI control
public class GridItem {
    private final String label;
    private final String iconPath;
    
    // Constructor
    public GridItem(String label, String iconPath) {
        this.label = label;
        this.iconPath = iconPath;
    }
    
    // Get label text of item method
    public String getLabelText() {
        return label;
    }
    
    // Get icon file path of item method
    public String getIconPath() {
        return iconPath;
    }
}