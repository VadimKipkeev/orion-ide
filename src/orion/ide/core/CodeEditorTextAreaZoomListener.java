/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 *******************************************************************************
 * Code editor text area zoom event listener class
 *******************************************************************************
 * Implements zoom in and zoom out methods
 *******************************************************************************
 */
package orion.ide.core;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import java.awt.Font;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import org.fife.ui.rsyntaxtextarea.Style;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class CodeEditorTextAreaZoomListener implements MouseWheelListener {
    
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */
    private final RSyntaxTextArea editorTextArea;
    private static final int MIN_FONT_SIZE = 8; // Minimal font size constant
    private static final int MAX_FONT_SIZE = 60; // Maximized font size constant
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    // Constructor
    public CodeEditorTextAreaZoomListener(RSyntaxTextArea textArea) {
        this.editorTextArea = textArea;
    }
    
    // Set zoom in and zoom out by mouse wheel scroll : method
    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        
        // Check Ctrl or Cmd key pressed
        if(event.isControlDown() || event.isMetaDown()) {
            Font font = editorTextArea.getFont();
            int currentSize = font.getSize();
            
            int newSize = currentSize - event.getWheelRotation(); // => 1 for scroll down, -1 for scroll up
            
            // Set control for minimal and maximize font size
            if(newSize >= MIN_FONT_SIZE && newSize <= MAX_FONT_SIZE) {
                zoomToSize(newSize);
            }
            
            // Consume event for form UI controls not scrolling
            event.consume();
        } else {
            
            // If Ctrl or Cmd key not pressed return control to form UI
            if(editorTextArea.getParent() != null) {
                editorTextArea.getParent().dispatchEvent(event);
            }
        }
    }
    
    // Set editor text area zoom size : method
    public void zoomToSize(int size) {
        Font currentFont = editorTextArea.getFont();
        Font newFont = currentFont.deriveFont((float) size);
        
        // Update editor text area font
        editorTextArea.setFont(newFont);
        
        // Update font fo all syntax highlighting elements
        SyntaxScheme scheme = editorTextArea.getSyntaxScheme();
        
        if(scheme != null) {
            scheme = (SyntaxScheme) scheme.clone();
            
            for(int i = 0; i < scheme.getStyleCount(); i++) {
                Style schemeStyle = scheme.getStyle(i);
                
                if(schemeStyle != null && schemeStyle.font != null) {
                    schemeStyle.font = schemeStyle.font.deriveFont((float) size);
                }
            }
            
            editorTextArea.setSyntaxScheme(scheme);
        }
    }
}
