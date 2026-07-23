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

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.SwingConstants;
import java.awt.*;

// Iconic table UI control class
public class IconicTable extends DefaultListCellRenderer {
    
    // Constructor
    public IconicTable() {
        
        // Set icon label on bottom side of icon
        setVerticalTextPosition(SwingConstants.BOTTOM);
        
        // Set icon label position to center
        setHorizontalTextPosition(SwingConstants.CENTER);
        
        // Set table element position by cell center
        setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        if(value instanceof GridItem item) {
            setText(item.getLabelText());
            
            java.net.URL iconURL = getClass().getClassLoader().getResource(item.getIconPath());
            
            if(iconURL != null) {
                setIcon(new FlatSVGIcon(iconURL));
            } else {
                setIcon(null);
            }
        }
        
        return this;
    }
}
