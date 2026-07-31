/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 *******************************************************************************
 * JFileChooser file filter class for source files
 *******************************************************************************
 * Create file filter fo extensions: .h, .c, .cpp, .ui, .ini
 *******************************************************************************
 */
package orion.ide.core;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
*/
import java.io.File;
import javax.swing.filechooser.FileFilter;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class SourceFileFilter extends FileFilter {
    
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */
    private final String filterDescription;
    private final String[] filterExtArray;
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    // Constructor for single file extension
    public SourceFileFilter(String filterDescription, String filterExtension) {
        this.filterDescription = filterDescription;
        this.filterExtArray = new String[]{
            filterExtension.toLowerCase()
        };
    }
    
    // Constructor for several files extensions
    public SourceFileFilter(String filterDescription, String[] filterExtArray) {
        this.filterDescription = filterDescription;
        this.filterExtArray = new String[filterExtArray.length];
        
        for(int i = 0; i < filterExtArray.length; i++) {
            this.filterExtArray[i] = filterExtArray[i].toLowerCase();
        }
    }
    
    // This code set to shown files and folders : method
    @Override
    public boolean accept(File file) {
        
        // Folders always return true
        if(file.isDirectory())
            return true;
        
        String fileName = file.getName().toLowerCase();
        
        for(String fileExtension : this.filterExtArray) {
            if(fileName.endsWith("." + fileExtension)) {
                return true;
            }
        }
        
        return false;
    }
    
    // Get file extensions description in dialog window : method
    @Override
    public String getDescription() {
        return this.filterDescription;
    }
}
