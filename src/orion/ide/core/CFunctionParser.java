/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 * -----------------------------------------------------------------------------
 * C function parser class
 * -----------------------------------------------------------------------------
 * Parse code block to function and add this to function list
 * -----------------------------------------------------------------------------
 */
package orion.ide.core;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.CaretEvent;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class CFunctionParser {
    
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
    private RSyntaxTextArea textArea;
    private JComboBox<FunctionItem> functionList;
    
    private boolean isListUpdate = false;
    private boolean isCaretUpdate = false;
    
    private final List<FunctionItem> functionArray = new ArrayList<>();
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    /*
     * -------------------------------------------------------------------------
     * Function item internal class
     * -------------------------------------------------------------------------
     */
    private static class FunctionItem {
        
        /*
         * ---------------------------------------------------------------------
         * INTERNAL CLASS FIELDS SECTION BEGIN
         * ---------------------------------------------------------------------
         */
        String name;
        int position;
        /*
         * ---------------------------------------------------------------------
         * INTERNAL CLASS FIELDS SECTION END
         * ---------------------------------------------------------------------
         */
        
        /* ---------------------------------------------------------------------
         * PUBLIC INTERNAL CLASS METHODS
         * ---------------------------------------------------------------------
         */

        // Internal constructor
        public FunctionItem(String name, int position) {
            this.name = name;
            this.position = position;
        }
        
        // Send function item name to function list : method
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    /*
     * -------------------------------------------------------------------------
     * PUBLIC CLASS METHODS
     * -------------------------------------------------------------------------
     */

    // Constructor
    public CFunctionParser(RSyntaxTextArea textArea, JComboBox functionList) {
        this.textArea = textArea;
        this.functionList = functionList;
        
        // Scan all text at init
        parseFunctions();
        
        // Add text area event listener for update function list
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                parseFunctions();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                parseFunctions();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                parseFunctions();
            }
        });
        
        // Add combobox select event listener
        functionList.addActionListener((ActionEvent e) -> {
            if(isListUpdate)
                return;
            
            FunctionItem selected = (FunctionItem) functionList.getSelectedItem();
            
            if(selected != null) {
                isCaretUpdate = true;
                
                // Set cursor to function code block position
                textArea.setCaretPosition(selected.position);
                textArea.requestFocusInWindow();
                
                isCaretUpdate = false;
            }
        });
        
        // Add cursor move event listener
        textArea.addCaretListener((CaretEvent e) -> {
            if(isCaretUpdate || functionArray.isEmpty())
                return;
            
            int caretPosition = e.getDot();
            FunctionItem currentFunction = null;
            
            for(FunctionItem item : functionArray) {
                if(item.position <= caretPosition) {
                    currentFunction = item;
                } else {
                    break;
                }
            }
            
            if(currentFunction != null && currentFunction != functionList.getSelectedItem()) {
                isListUpdate = true;
                functionList.setSelectedItem(currentFunction);
                isListUpdate = false;
            }
        });
        
        
    }

    /*
     * -------------------------------------------------------------------------
     * PRIVATE CLASS FUNCTIONS
     * -------------------------------------------------------------------------
     */
    
    // Parsing functions with regex strings : function
    private void parseFunctions() {
        SwingUtilities.invokeLater(() -> {
            isListUpdate = true;
            
            Object currentSelected = functionList.getSelectedItem();
            String currentSelectedName = currentSelected != null ? currentSelected.toString() : null;
            
            functionList.removeAllItems();
            functionArray.clear();
            
            String text = textArea.getText();
            
            String regex = "\\b(?!if|while|for|switch|return)\\w+\\s+\\w+\\s*\\([^\\)]*\\)\\s*\\{";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            
            FunctionItem reselectItem = null;
            
            while(matcher.find()) {
                String match = matcher.group();
                String functionName = match.substring(0, match.indexOf('{')).trim();
                int position = matcher.end();
                
                FunctionItem item = new FunctionItem(functionName, position);
                
                functionArray.add(item);
                functionList.addItem(item);
                
                if(currentSelectedName != null && functionName.equals(currentSelectedName)) {
                    reselectItem = item;
                }
            }
            
            if(reselectItem != null) {
                functionList.setSelectedItem(reselectItem);
            } else if(functionList.getItemCount() > 0) {
                isListUpdate = false;
                triggerCaretCheck();
                
                return;
            }
            
            isListUpdate = false;
        });
    }
    
    // Sync combobox with current cursor position
    private void triggerCaretCheck() {
        int caretPosition = textArea.getCaretPosition();
        FunctionItem currentFunction = null;
        
        for(FunctionItem item : functionArray) {
            if (item.position <= caretPosition) {
                currentFunction = item;
            } else {
                break;
            }
        }
        
        if (currentFunction != null) {
            isListUpdate = true;
            functionList.setSelectedItem(currentFunction);
            isListUpdate = false;
        } else {
            functionList.setSelectedIndex(-1);
        }
    }
}