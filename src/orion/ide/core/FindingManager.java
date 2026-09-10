/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 * -----------------------------------------------------------------------------
 * Finding manager class
 * -----------------------------------------------------------------------------
 * Find and replace strings for RSyntaxTextArea component realise
 * -----------------------------------------------------------------------------
 */
package orion.ide.core;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.SearchEngine;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchResult;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class FindingManager {

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
    private final RSyntaxTextArea textArea;
    private final SearchContext context;
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
    // Constructor
    public FindingManager(RSyntaxTextArea textArea) {
        this.textArea = textArea;
        this.context = new SearchContext();
        
        // Finding settings
        this.context.setMatchCase(false);
        this.context.setWholeWord(false);
        this.context.setRegularExpression(false);
    }
    
    // Find first entering : method
    public boolean findContext(String findWord) {
        context.setSearchFor(findWord);
        context.setSearchForward(true);
        
        textArea.setCaretPosition(0);
        
        SearchResult result = SearchEngine.find(textArea, context);
        
        return result.wasFound();
    }
    
    // Find next entering : method
    public boolean findNext() {
        context.setSearchForward(true);
        
        SearchResult result = SearchEngine.find(textArea, context);
        
        return result.wasFound();
    }
    
    // Find previous entering : method
    public boolean findPreview() {
        context.setSearchForward(false);
        
        SearchResult result = SearchEngine.find(textArea, context);
        
        return result.wasFound();
    }
    
    // Replace selected string : method
    public boolean replaceText(String targetWord, String replaceWord) {
        context.setSearchFor(targetWord);
        context.setReplaceWith(replaceWord);
        
        SearchResult result = SearchEngine.replace(textArea, context);
        
        this.setTextMark(false); // Remove selection from finded text
        
        return result.wasFound();
    }
    
    // Replace all entering strings : method
    public int replaceAllText(String targetWord, String replaceWord) {
        context.setSearchFor(targetWord);
        context.setReplaceWith(replaceWord);
        
        SearchResult result = SearchEngine.replaceAll(textArea, context);
        
        this.setTextMark(false); // Remove selection from finded text
        
        return result.getCount();
    }
    
    // Get finding context settings data : method
    public SearchContext getContext() {
        return context;
    }
    
    // Set/remove selection from finded text
    public void setTextMark(boolean isMark) {
        if(isMark) {
            SearchEngine.markAll(textArea, context);
        } else {
            context.setSearchFor("");
            SearchEngine.markAll(textArea, context);
        }
    }
}