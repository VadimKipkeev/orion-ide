/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 * -----------------------------------------------------------------------------
 * Code editor panel class
 * -----------------------------------------------------------------------------
 * Editor UI control methods
 * -----------------------------------------------------------------------------
 */
package orion.ide.ui;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.TreeSet;
import javax.naming.directory.SearchResult;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;
import orion.ide.core.CodeEditorTextAreaZoomListener;
import orion.ide.core.NumericFieldHelper;
import orion.ide.core.FindingManager;
import orion.ide.core.CFunctionParser;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class CodeEditorPanel extends javax.swing.JPanel {

    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */

    /*
     * -------------------------------------------------------------------------
     * PUBLIC CLASS FIELDS
     * -------------------------------------------------------------------------
     */
    
    // Editor text area font size used by default zoom size
    public int defaultEditorFontSize;
    
    // Code editor view
    public RSyntaxTextArea editorTextArea = new RSyntaxTextArea();
    public RTextScrollPane editorTextAreaScroller = new RTextScrollPane(editorTextArea);

    /*
     * -------------------------------------------------------------------------
     * PRIVATE CLASS FIELDS
     * -------------------------------------------------------------------------
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JToolBar CodeEditorToolbar;
    private javax.swing.JMenuItem CopyActionItem;
    private javax.swing.JMenuItem CutActionItem;
    private javax.swing.JButton DeleteTemplateButton;
    private javax.swing.JPopupMenu EditorTextPopupMenu;
    private javax.swing.JComboBox<String> FunctionsListButton;
    private javax.swing.JLabel FunctionsListLabel;
    private javax.swing.JButton GoToButton;
    private javax.swing.JDialog GoToDialogWindow;
    private javax.swing.JButton GoToLineButton;
    private javax.swing.JLabel GoToLineLabel;
    private javax.swing.JTextField GoToLineTextInput;
    private javax.swing.JButton GoToStringButton;
    private javax.swing.JLabel GoToStringLabel;
    private javax.swing.JTextField GoToStringTextInput;
    private javax.swing.JButton InsertEnumButton;
    private javax.swing.JButton InsertFunctionButton;
    private javax.swing.JButton InsertStructureButton;
    private javax.swing.JButton InsertTemplateButton;
    private javax.swing.JPopupMenu.Separator MenuSeparator13;
    private javax.swing.JButton NewBookmarkButton;
    private javax.swing.JButton NewTemplateButton;
    private javax.swing.JDialog NewTemplateWindow;
    private javax.swing.JButton NextBookmarkButton;
    private javax.swing.JMenuItem PasteActionItem;
    private javax.swing.JButton PrevBookmarkButton;
    private javax.swing.JMenuItem RedoActionItem;
    private javax.swing.JButton SaveTemplateButton;
    private javax.swing.JList<String> TemplateList;
    private javax.swing.JLabel TemplateListLabel;
    private javax.swing.JScrollPane TemplateListScroller;
    private javax.swing.JLabel TemplateNameLabel;
    private javax.swing.JTextField TemplateNameTextInput;
    private javax.swing.JDialog TemplatesWindow;
    private javax.swing.JToolBar.Separator ToolbarSeparator10;
    private javax.swing.JToolBar.Separator ToolbarSeparator11;
    private javax.swing.JToolBar.Separator ToolbarSeparator12;
    private javax.swing.JMenuItem UndoActionItem;
    // End of variables declaration//GEN-END:variables

    private final Gutter bookmarksManager;
    private final TreeSet<Integer> bookmarksList = new TreeSet<>();
    
    FindingManager fmanager;
    
    private String fileExtension;
    private String textBuffer = new String();
    
    private boolean isFileModified;
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
    public CodeEditorPanel() {
        initComponents();
  
        editorTextArea.setPopupMenu(EditorTextPopupMenu); // Set editor text area popup menu
        editorTextArea.addMouseWheelListener(new CodeEditorTextAreaZoomListener(editorTextArea)); // Set editor text area mouse wheel scroll event listener
        this.add(editorTextAreaScroller);
        
        // Set bookmarks manager
        this.bookmarksManager = editorTextAreaScroller.getGutter();
        bookmarksManager.setBookmarkingEnabled(true);
        bookmarksManager.setBookmarkIcon(getIcon("new_bookmark", 16)); // Set bookmark icon
        
        // Set finding manager
        this.fmanager = new FindingManager(editorTextArea);
        
        // Set input filter for line number text field
        NumericFieldHelper.makeNumericOnly(GoToLineTextInput);
        
        // Parse C functions and add to list
        SwingUtilities.invokeLater(() -> new CFunctionParser(editorTextArea, FunctionsListButton));
        
        // Compare text buffer with editor text area by timer
        new Timer(300, e -> checkFileModifiedStatus()).start();
    }

    // Update text buffer : method
    public void updateTextBuffer() {
        textBuffer = editorTextArea.getText();
        isFileModified = isModified();
    }
    
    // Set editor text area source text : method
    public void setEditorSourceText(String sourceText) {
        if(sourceText != null && !sourceText.equals("")) {
            editorTextArea.setText(sourceText);
            textBuffer = sourceText;
            isFileModified = false;
        }
    }
    
    // Get source code text : method
    public String getEditorSourceText() {
        String sourceText = editorTextArea.getText();
        return sourceText;
    }
    
    // Set editor text area syntax highlighting style : method
    public void setEditorSyntaxStyle() {
        fileExtension = MainWindow.newFileExtension;
        
        switch(fileExtension) {
            case ".h" -> {
                editorTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
                editorTextArea.setCodeFoldingEnabled(true);
                break;
            }
            
            case ".c" -> {
                editorTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
                editorTextArea.setCodeFoldingEnabled(true);
                break;
            }
            
            case ".cpp" -> {
                editorTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                editorTextArea.setCodeFoldingEnabled(true);
                break;
            }
            
            case ".ini" -> {
                editorTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_INI);
                editorTextArea.setCodeFoldingEnabled(true);
                break;
            }
            
            default -> {
                break;
            }
        }
    }
    
    // Update editor theme : method
    public void updateEditorTheme(String currentTheme) {
        int currentThemeID = Integer.parseInt(currentTheme);
        
        switch(currentThemeID) {
            case 0 -> {
                String themeName = "default";
                
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/" + themeName + ".xml"));
                    theme.apply(editorTextArea);
                    
                    SwingUtilities.updateComponentTreeUI(this.editorTextArea); 
                    this.editorTextArea.invalidate();
                    this.editorTextArea.validate();
                    this.editorTextArea.repaint();
                    
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            case 1 -> {
                String themeName = "dark";
                
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/" + themeName + ".xml"));
                    theme.apply(this.editorTextArea);
                    
                    SwingUtilities.updateComponentTreeUI(this.editorTextArea); 
                    this.editorTextArea.invalidate();
                    this.editorTextArea.validate();
                    this.editorTextArea.repaint();
                    
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
                        
            case 2 -> {
                String themeName = "eclipse";
                
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/" + themeName + ".xml"));
                    theme.apply(this.editorTextArea);
                    
                    SwingUtilities.updateComponentTreeUI(this.editorTextArea); 
                    this.editorTextArea.invalidate();
                    this.editorTextArea.validate();
                    this.editorTextArea.repaint();
                    
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            case 3 -> {
                String themeName = "idea";
                
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/" + themeName + ".xml"));
                    theme.apply(this.editorTextArea);
                    
                    SwingUtilities.updateComponentTreeUI(this.editorTextArea); 
                    this.editorTextArea.invalidate();
                    this.editorTextArea.validate();
                    this.editorTextArea.repaint();
                    
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            case 4 -> {
                String themeName = "vs";
                
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/" + themeName + ".xml"));
                    theme.apply(this.editorTextArea);
                    
                    SwingUtilities.updateComponentTreeUI(this.editorTextArea); 
                    this.editorTextArea.invalidate();
                    this.editorTextArea.validate();
                    this.editorTextArea.repaint();
                    
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            case 5 -> {
                String themeName = "monokai";
                
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/" + themeName + ".xml"));
                    theme.apply(this.editorTextArea);
                    
                    SwingUtilities.updateComponentTreeUI(this.editorTextArea); 
                    this.editorTextArea.invalidate();
                    this.editorTextArea.validate();
                    this.editorTextArea.repaint();
                    
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            case 6 -> {
                String themeName = "druid";
                
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/" + themeName + ".xml"));
                    theme.apply(this.editorTextArea);
                    
                    SwingUtilities.updateComponentTreeUI(this.editorTextArea); 
                    this.editorTextArea.invalidate();
                    this.editorTextArea.validate();
                    this.editorTextArea.repaint();
                    
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            default -> {
                String themeName = "default";
                
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/" + themeName + ".xml"));
                    theme.apply(this.editorTextArea);
                    
                    SwingUtilities.updateComponentTreeUI(this.editorTextArea); 
                    this.editorTextArea.invalidate();
                    this.editorTextArea.validate();
                    this.editorTextArea.repaint();
                    
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    // Undo editor text area : method
    public void undoLastAction () {
        editorTextArea.undoLastAction();
    }
    
    // Redo editor text area : method
    public void redoLastAction() {
        editorTextArea.redoLastAction();
    }
    
    // Cut selected text in editor text area : method
    public void cutTextAction () {
        editorTextArea.cut();
    }
    
    // Copy selected text in editor text area : method
    public void copyTextAction() {
        editorTextArea.copy();
    }
 
    // Paste saved text from buffer in editor text area
    public void pasteTextAction() {
        editorTextArea.paste();
    }
    
    // Zoom in view editor text area : method
    public void zoomInAction() {
        CodeEditorTextAreaZoomListener zoomListener = new CodeEditorTextAreaZoomListener(editorTextArea);
        
        int currentSize = editorTextArea.getFont().getSize();
        
        if(currentSize <= 58) {
            zoomListener.zoomToSize(currentSize + 2);
        }
    }
    
    // Zoom out view editor text area : method
    public void zoomOutAction() {
        CodeEditorTextAreaZoomListener zoomListener = new CodeEditorTextAreaZoomListener(editorTextArea);
        
        int currentSize = editorTextArea.getFont().getSize();
        
        if(currentSize >= 10) {
            zoomListener.zoomToSize(currentSize - 2);
        }
    }
    
    // Default zoom view editor text area : method
    public void setDefaultZoom() {
        CodeEditorTextAreaZoomListener zoomListener = new CodeEditorTextAreaZoomListener(editorTextArea);
        
        if(defaultEditorFontSize >= 8) {
            zoomListener.zoomToSize(defaultEditorFontSize);
        }
    }
    
    // Print text action : method
    public boolean printTextAction() {
        PrinterJob printer = PrinterJob.getPrinterJob();
        printer.setJobName("Print file");
        
        // Setup page format
        PageFormat pageFormat = printer.defaultPage();
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        
        printer.setPrintable(editorTextArea, pageFormat);
        
        try {
            printer.print();
        } catch(PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Error at print process: " + ex.getMessage(), "Print error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    // Show print text setup page : method
    public boolean printTextSetupAction() {
        PrinterJob printer = PrinterJob.getPrinterJob();
        printer.setJobName("Print file");
        
        // Setup page format
        PageFormat pageFormat = printer.defaultPage();
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        
        printer.setPrintable(editorTextArea, pageFormat);
        
        if(printer.printDialog()) {
            try {
                printer.print();
            } catch(PrinterException ex) {
                JOptionPane.showMessageDialog(null, "Error at print process: " + ex.getMessage(), "Print error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    // Show go to dialog window : method
    public void showGoToDialogWindow() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenX = (screenSize.width - GoToDialogWindow.getWidth()) / 2;
        int screenY = (screenSize.height - GoToDialogWindow.getHeight()) / 2;
        
        GoToDialogWindow.setLocation(screenX, screenY);
        GoToDialogWindow.setVisible(true);
    }
    
    // Create/delete new bookmark : method
    public void toggleBookmarkAction() {
        int selectedLine = editorTextArea.getCaretLineNumber();
        
        if(bookmarksList.contains(selectedLine)) {
            bookmarksList.remove(selectedLine);
            
            try {
                bookmarksManager.toggleBookmark(selectedLine);
            } catch (BadLocationException ex) {
                System.getLogger(CodeEditorPanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            bookmarksList.add(selectedLine);
            
            try {
                bookmarksManager.toggleBookmark(selectedLine);
            } catch (BadLocationException ex) {
                System.getLogger(CodeEditorPanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    // Go to next bookmark : method
    public void goToNextBookmark() {
        int currentLine = editorTextArea.getCaretLineNumber();
        GutterIconInfo[] bookmarks = bookmarksManager.getBookmarks();
        
        if(bookmarks.length == 0) {
            return;
        }
        
        int nextLine = -1;
        int firstLine = Integer.MAX_VALUE;
        
        for(GutterIconInfo info : bookmarks) {
            int bookmarkLine = getLineOfBookmark(info);
            
            if(bookmarkLine == -1) {
                continue;
            }
            
            if(bookmarkLine < firstLine) {
                firstLine = bookmarkLine;
            }
            
            if(bookmarkLine > currentLine) {
                if(nextLine == -1 || bookmarkLine < nextLine) {
                    nextLine = bookmarkLine;
                }
            }
        }
        
        if(nextLine == -1) {
            nextLine = firstLine;
        }
        
        moveCaretToLine(nextLine);
    }
    
    // Go to previous bookmark : method
    public void goToPrevBookmark() {
        int currentLine = editorTextArea.getCaretLineNumber();
        GutterIconInfo[] bookmarks = bookmarksManager.getBookmarks();
        
        if(bookmarks.length == 0) {
            return;
        }
        
        int prevLine = -1;
        int lastLine = -1;
        
        for(GutterIconInfo info : bookmarks) {
            int bookmarkLine = getLineOfBookmark(info);
            
            if(bookmarkLine == -1) {
                continue;
            }
            
            if(bookmarkLine > lastLine) {
                lastLine = bookmarkLine;
            }
            
            if(bookmarkLine < currentLine) {
                if(prevLine == -1 || bookmarkLine > prevLine) {
                    prevLine = bookmarkLine;
                }
            }
        }
        
        if(prevLine == -1) {
            prevLine = lastLine;
        }
        
        moveCaretToLine(prevLine);
    }
    
    // Find text action : method
    public void findTextAction(String findWord) {
        boolean result = fmanager.findContext(findWord);
        
        // Show message dialog window by can't finding
        if(!result) {
            JOptionPane.showMessageDialog(null, "The word " + findWord + " is missing!", "Find text", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Find next text action : method
    public void findNextTextAction() {
        boolean result = fmanager.findNext();
        
        // Show message dialog window by not find line of text
        if(!result) {
            JOptionPane.showMessageDialog(null, "Not find next result!", "Find text", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Find preview text action : method
    public void findPreviewTextAction() {
        boolean result = fmanager.findPreview();
        
        // Show message dialog window by not find line of text
        if(!result) {
            JOptionPane.showMessageDialog(null, "Not find preview result!", "Find text", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Replace text action : method
    public void replaceTextAction(String targetWord, String replaceWord) {
        boolean result = fmanager.replaceText(targetWord, replaceWord);
        
        // Show message dialog window by action result status
        if(!result) {
            JOptionPane.showMessageDialog(null, "Not find target text for replace!", "Replace text", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Text replace is success!", "Replace text", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Replace all text action : method
    public void replaceAllTextAction(String targetWord, String replaceWord) {
        int result = fmanager.replaceAllText(targetWord, replaceWord);
        
        // Show message dialog window by action result count
        if(result <= 0) {
            JOptionPane.showMessageDialog(null, "Not find target text for replace!", "Replace text", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(null, "The " + String.valueOf(result) + " strings is replaced.", "Replace text", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Remove text selection after finding : method
    public void removeFindSelection() {
        fmanager.setTextMark(false);
    }
    
    // Get caret line and column position : method
    public String getCaretPosition() {
        String lineNumber;
        String columnNumber;
        String result;
        
        lineNumber = String.valueOf(editorTextArea.getCaretLineNumber() + 1);
        columnNumber = String.valueOf(editorTextArea.getCaretOffsetFromLineStart() + 1);
        result = lineNumber + ":" + columnNumber;
        
        return result;
    }
    
    // Add C structure snippet to editor text area : method
    public void addCStructSnippet() {
        
        // C struct snippet
        String snippet = """
                         typedef struct {
                           ${FIELD_TYPE_1} ${fieldName1};
                           ${FIELD_TYPE_2} ${fieldName2};
                           ${FIELD_TYPE_3} ${fieldName3};
                           ${FIELD_TYPE_4} ${fieldName4};
                         } ${STRUCT_NAME}_T;
                         """;
        
        editorTextArea.replaceSelection(snippet);
        
        String[] tags = {"${FIELD_TYPE_1}", "${fieldName1}", "${FIELD_TYPE_2}", "${fieldName2}", "${FIELD_TYPE_3}", "${fieldName3}", "${FIELD_TYPE_4}", "${fieldName4}", "${STRUCT_NAME}"};
        java.util.List<javax.swing.text.Position> placeholders = new java.util.ArrayList<>();
        
        String text = editorTextArea.getText();
        
        try {
            for(String tag : tags) {
                int index = text.indexOf(tag);
                
                if(index >= 0) {
                    placeholders.add(editorTextArea.getDocument().createPosition(index));
                }
            }
        } catch(BadLocationException ex) {
            ex.printStackTrace();
        }
        
        final int[] current = {0};
        final boolean[] snippetMode = {true};
        
        // Show snippet highlight
        Highlighter highlighter = editorTextArea.getHighlighter();
        final Object[] highlightTag = {null};
        
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 230, 140));
        
        Runnable applyHighlight = () -> {
            try {
                if(highlightTag[0] != null)
                    highlighter.removeHighlight(highlightTag[0]);
                
                int position = placeholders.get(current[0]).getOffset();
                highlightTag[0] = highlighter.addHighlight(position, position + tags[current[0]].length(), painter);
            } catch(BadLocationException ignored) {}
        };
        
        // Set control mode keys listeners
        editorTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                
                // Press ESC key for exit edit snippet mode
                if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                    snippetMode[0] = false;
                    
                    if(highlightTag[0] != null)
                        highlighter.removeHighlight(highlightTag[0]);
                    
                    return;
                }
                
                // Press TAB key for go to next placeholder
                if(snippetMode[0] && e.getKeyCode() == java.awt.event.KeyEvent.VK_TAB) {
                    e.consume();
                    
                    current[0] = (current[0] + 1) % placeholders.size();
                    
                    int position = placeholders.get(current[0]).getOffset();
                    editorTextArea.setCaretPosition(position);
                    editorTextArea.select(position, position + tags[current[0]].length());
                    
                    applyHighlight.run();
                }
            }
        });
        
        // Go to first placeholder and highlight
        if(!placeholders.isEmpty()) {
            int position = placeholders.get(0).getOffset();
            
            editorTextArea.setCaretPosition(position);
            editorTextArea.select(position, position + tags[0].length());
            
            applyHighlight.run();
        }
    }
    
    // Add C enumeration snippet to editor text area : method
    public void addCEnumSnippet() {
        
        // C struct snippet
        String snippet = """
                         typedef enum {
                           ${FIELD_1},
                           ${FIELD_2},
                           ${FIELD_3},
                           ${FIELD_4},
                         } ${ENUM_NAME}_T;
                         """;
        
        editorTextArea.replaceSelection(snippet);
        
        String[] tags = {"${FIELD_1}", "${FIELD_2}", "${FIELD_3}", "${FIELD_4}", "${ENUM_NAME}"};
        java.util.List<javax.swing.text.Position> placeholders = new java.util.ArrayList<>();
        
        String text = editorTextArea.getText();
        
        try {
            for(String tag : tags) {
                int index = text.indexOf(tag);
                
                if(index >= 0) {
                    placeholders.add(editorTextArea.getDocument().createPosition(index));
                }
            }
        } catch(BadLocationException ex) {
            ex.printStackTrace();
        }
        
        final int[] current = {0};
        final boolean[] snippetMode = {true};
        
        // Show snippet highlight
        Highlighter highlighter = editorTextArea.getHighlighter();
        final Object[] highlightTag = {null};
        
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 230, 140));
        
        Runnable applyHighlight = () -> {
            try {
                if(highlightTag[0] != null)
                    highlighter.removeHighlight(highlightTag[0]);
                
                int position = placeholders.get(current[0]).getOffset();
                highlightTag[0] = highlighter.addHighlight(position, position + tags[current[0]].length(), painter);
            } catch(BadLocationException ignored) {}
        };
        
        // Set control mode keys listeners
        editorTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                
                // Press ESC key for exit edit snippet mode
                if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                    snippetMode[0] = false;
                    
                    if(highlightTag[0] != null)
                        highlighter.removeHighlight(highlightTag[0]);
                    
                    return;
                }
                
                // Press TAB key for go to next placeholder
                if(snippetMode[0] && e.getKeyCode() == java.awt.event.KeyEvent.VK_TAB) {
                    e.consume();
                    
                    current[0] = (current[0] + 1) % placeholders.size();
                    
                    int position = placeholders.get(current[0]).getOffset();
                    editorTextArea.setCaretPosition(position);
                    editorTextArea.select(position, position + tags[current[0]].length());
                    
                    applyHighlight.run();
                }
            }
        });
        
        // Go to first placeholder and highlight
        if(!placeholders.isEmpty()) {
            int position = placeholders.get(0).getOffset();
            
            editorTextArea.setCaretPosition(position);
            editorTextArea.select(position, position + tags[0].length());
            
            applyHighlight.run();
        }
    }
    
    // Add C function snippet to editor text area : method
    public void addCFunctionSnippet() {
        
        // C struct snippet
        String snippet = "${func_type_1} ${FUNC_TYPE_2} ${FuncName}(${arg_1}, ${arg_2});";
        
        editorTextArea.replaceSelection(snippet);
        
        String[] tags = {"${func_type_1}", "${FUNC_TYPE_2}", "${FuncName}", "${arg_1}", "${arg_2}"};
        java.util.List<javax.swing.text.Position> placeholders = new java.util.ArrayList<>();
        
        String text = editorTextArea.getText();
        
        try {
            for(String tag : tags) {
                int index = text.indexOf(tag);
                
                if(index >= 0) {
                    placeholders.add(editorTextArea.getDocument().createPosition(index));
                }
            }
        } catch(BadLocationException ex) {
            ex.printStackTrace();
        }
        
        final int[] current = {0};
        final boolean[] snippetMode = {true};
        
        // Show snippet highlight
        Highlighter highlighter = editorTextArea.getHighlighter();
        final Object[] highlightTag = {null};
        
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 230, 140));
        
        Runnable applyHighlight = () -> {
            try {
                if(highlightTag[0] != null)
                    highlighter.removeHighlight(highlightTag[0]);
                
                int position = placeholders.get(current[0]).getOffset();
                highlightTag[0] = highlighter.addHighlight(position, position + tags[current[0]].length(), painter);
            } catch(BadLocationException ignored) {}
        };
        
        // Set control mode keys listeners
        editorTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                
                // Press ESC key for exit edit snippet mode
                if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                    snippetMode[0] = false;
                    
                    if(highlightTag[0] != null)
                        highlighter.removeHighlight(highlightTag[0]);
                    
                    return;
                }
                
                // Press TAB key for go to next placeholder
                if(snippetMode[0] && e.getKeyCode() == java.awt.event.KeyEvent.VK_TAB) {
                    e.consume();
                    
                    current[0] = (current[0] + 1) % placeholders.size();
                    
                    int position = placeholders.get(current[0]).getOffset();
                    editorTextArea.setCaretPosition(position);
                    editorTextArea.select(position, position + tags[current[0]].length());
                    
                    applyHighlight.run();
                }
            }
        });
        
        // Go to first placeholder and highlight
        if(!placeholders.isEmpty()) {
            int position = placeholders.get(0).getOffset();
            
            editorTextArea.setCaretPosition(position);
            editorTextArea.select(position, position + tags[0].length());
            
            applyHighlight.run();
        }
    }
    
    // Show templates window : method
    public void showTemplatesWindow() {
        loadTemplateList();
        
        TemplatesWindow.setLocationRelativeTo(null);
        TemplatesWindow.setVisible(true);
    }
    
    // Check source text and text buffer to hidden symbols : method
    public boolean isModified() {
        String currentText = editorTextArea.getText().replace("\r\n", "\n").trim();
        String currentBuffer = textBuffer.replace("\r\n", "\n").trim();
        
        return !currentText.equals(currentBuffer); // => true or false
    }

    /*
     * -------------------------------------------------------------------------
     * PRIVATE CLASS FUNCTIONS
     * -------------------------------------------------------------------------
     */

    // Get string from GutterIconInfo object : function
    private int getLineOfBookmark(GutterIconInfo object) {
        try {
            return editorTextArea.getLineOfOffset(object.getMarkedOffset());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    // Move cursor to line : function
    private void moveCaretToLine(int line) {
        if(line < 0 || line >= editorTextArea.getLineCount()) {
            return;
        }
        
        try {
            int lineOffset = editorTextArea.getLineStartOffset(line);
            editorTextArea.setCaretPosition(lineOffset);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
    
    // Wrapper for get icon resources from icon provider : function
    private static Icon getIcon(String name) {
        return IconProvider.getIcon(name);
    }
    
    private static Icon getIcon(String name, int size) {
        return IconProvider.getIcon(name, size);
    }
    
    private static Icon getIcon(String name, int width, int height) {
        return IconProvider.getIcon(name, width, height);
    }
    
    // Check file modified status : function
    private boolean checkFileModifiedStatus() {
        JInternalFrame currentWindow = (JInternalFrame) SwingUtilities.getAncestorOfClass(JInternalFrame.class, this);
        
        if(currentWindow == null) {
            return false;
        }
        
        String windowTitle = currentWindow.getTitle();
        isFileModified = isModified();
            
        if(isFileModified && !windowTitle.endsWith("*")) {
            currentWindow.setTitle(windowTitle + "*");
            return true;
        } else if(!isFileModified && windowTitle.endsWith("*")) {
            currentWindow.setTitle(windowTitle.substring(0, windowTitle.length() - 1));
            return false;
        } else {
            currentWindow.setTitle(windowTitle);
            return false;
        }
    }
    
    // Go to line by number : function
    private void goToLineByNumber(int lineNumber) {
        try {
            int lineIndex = lineNumber - 1;
            
            // Check this line on exists in editor text area
            if(lineIndex < 0 || lineIndex >= editorTextArea.getLineCount()) {
                JOptionPane.showMessageDialog(null, "Line " + String.valueOf(lineNumber) + " is not exist!");
                return;
            }
            
            int lineOffset = editorTextArea.getLineStartOffset(lineIndex);
            
            // Go to line
            editorTextArea.setCaretPosition(lineOffset);
            editorTextArea.requestFocusInWindow();
        } catch(HeadlessException | BadLocationException ex) {
            ex.printStackTrace();
        }
    }
    
    // Go to line by string : function
    private void goToLineByString(String textToFind) {
        if(textToFind == null || textToFind.isEmpty()) {
            return;
        }
        
        // Set find context
        SearchContext context = new SearchContext();
        context.setSearchFor(textToFind);
        context.setMatchCase(false); // Without case sensitive
        context.setWholeWord(false); // Not search substring 
        context.setSearchForward(true); // Search next position
        context.setMarkAll(false); // Not markup all results
        
        // Find string
        SearchResult result;
        result = SearchEngine.find(editorTextArea, context);
        
        // Find next position by result is false
        if(!result.wasFound()) {
            editorTextArea.setCaretPosition(0);
            SearchEngine.find(editorTextArea, context);
        } else {
        }
        
        editorTextArea.requestFocusInWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EditorTextPopupMenu = new javax.swing.JPopupMenu();
        UndoActionItem = new javax.swing.JMenuItem();
        RedoActionItem = new javax.swing.JMenuItem();
        MenuSeparator13 = new javax.swing.JPopupMenu.Separator();
        CutActionItem = new javax.swing.JMenuItem();
        CopyActionItem = new javax.swing.JMenuItem();
        PasteActionItem = new javax.swing.JMenuItem();
        GoToDialogWindow = new javax.swing.JDialog();
        GoToLineLabel = new javax.swing.JLabel();
        GoToLineTextInput = new javax.swing.JTextField();
        GoToLineButton = new javax.swing.JButton();
        GoToStringLabel = new javax.swing.JLabel();
        GoToStringTextInput = new javax.swing.JTextField();
        GoToStringButton = new javax.swing.JButton();
        TemplatesWindow = new javax.swing.JDialog();
        TemplateListLabel = new javax.swing.JLabel();
        TemplateListScroller = new javax.swing.JScrollPane();
        TemplateList = new javax.swing.JList<>();
        NewTemplateButton = new javax.swing.JButton();
        DeleteTemplateButton = new javax.swing.JButton();
        InsertTemplateButton = new javax.swing.JButton();
        NewTemplateWindow = new javax.swing.JDialog();
        TemplateNameLabel = new javax.swing.JLabel();
        TemplateNameTextInput = new javax.swing.JTextField();
        SaveTemplateButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        CodeEditorToolbar = new javax.swing.JToolBar();
        FunctionsListLabel = new javax.swing.JLabel();
        FunctionsListButton = new javax.swing.JComboBox<>();
        ToolbarSeparator10 = new javax.swing.JToolBar.Separator();
        GoToButton = new javax.swing.JButton();
        ToolbarSeparator11 = new javax.swing.JToolBar.Separator();
        InsertStructureButton = new javax.swing.JButton();
        InsertEnumButton = new javax.swing.JButton();
        InsertFunctionButton = new javax.swing.JButton();
        ToolbarSeparator12 = new javax.swing.JToolBar.Separator();
        NewBookmarkButton = new javax.swing.JButton();
        PrevBookmarkButton = new javax.swing.JButton();
        NextBookmarkButton = new javax.swing.JButton();

        UndoActionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        UndoActionItem.setIcon(getIcon("undo_edit", 16));
        UndoActionItem.setText("Undo");
        UndoActionItem.addActionListener(this::UndoActionItemActionPerformed);
        EditorTextPopupMenu.add(UndoActionItem);

        RedoActionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        RedoActionItem.setIcon(getIcon("redo_edit", 16));
        RedoActionItem.setText("Redo");
        RedoActionItem.addActionListener(this::RedoActionItemActionPerformed);
        EditorTextPopupMenu.add(RedoActionItem);
        EditorTextPopupMenu.add(MenuSeparator13);

        CutActionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CutActionItem.setIcon(getIcon("cut_edit", 16));
        CutActionItem.setText("Cut");
        CutActionItem.addActionListener(this::CutActionItemActionPerformed);
        EditorTextPopupMenu.add(CutActionItem);

        CopyActionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CopyActionItem.setIcon(getIcon("copy_edit", 16));
        CopyActionItem.setText("Copy");
        CopyActionItem.addActionListener(this::CopyActionItemActionPerformed);
        EditorTextPopupMenu.add(CopyActionItem);

        PasteActionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        PasteActionItem.setIcon(getIcon("paste_edit", 16));
        PasteActionItem.setText("Paste");
        PasteActionItem.addActionListener(this::PasteActionItemActionPerformed);
        EditorTextPopupMenu.add(PasteActionItem);

        GoToDialogWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        GoToDialogWindow.setTitle("Go to");
        GoToDialogWindow.setIconImage(null);
        GoToDialogWindow.setIconImages(null);
        GoToDialogWindow.setMinimumSize(new java.awt.Dimension(720, 115));
        GoToDialogWindow.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        GoToDialogWindow.setName("GoToDialogWindow"); // NOI18N
        GoToDialogWindow.setResizable(false);
        GoToDialogWindow.setType(java.awt.Window.Type.POPUP);

        GoToLineLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GoToLineLabel.setLabelFor(GoToLineTextInput);
        GoToLineLabel.setText("Go to line at №");

        GoToLineTextInput.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        GoToLineTextInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        GoToLineButton.setText("Go to line");
        GoToLineButton.addActionListener(this::GoToLineButtonActionPerformed);

        GoToStringLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GoToStringLabel.setLabelFor(GoToStringTextInput);
        GoToStringLabel.setText("Go to string:");

        GoToStringTextInput.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        GoToStringTextInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        GoToStringButton.setText("Go to string");
        GoToStringButton.addActionListener(this::GoToStringButtonActionPerformed);

        javax.swing.GroupLayout GoToDialogWindowLayout = new javax.swing.GroupLayout(GoToDialogWindow.getContentPane());
        GoToDialogWindow.getContentPane().setLayout(GoToDialogWindowLayout);
        GoToDialogWindowLayout.setHorizontalGroup(
            GoToDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GoToDialogWindowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(GoToDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(GoToStringLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GoToLineLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(GoToDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GoToDialogWindowLayout.createSequentialGroup()
                        .addComponent(GoToLineTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GoToLineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GoToDialogWindowLayout.createSequentialGroup()
                        .addComponent(GoToStringTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GoToStringButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        GoToDialogWindowLayout.setVerticalGroup(
            GoToDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GoToDialogWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GoToDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GoToLineTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GoToLineButton)
                    .addComponent(GoToLineLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(GoToDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GoToStringTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GoToStringButton)
                    .addComponent(GoToStringLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        TemplatesWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        TemplatesWindow.setTitle("Templates");
        TemplatesWindow.setMinimumSize(new java.awt.Dimension(640, 480));
        TemplatesWindow.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        TemplatesWindow.setName("TemplatesWindow"); // NOI18N
        TemplatesWindow.setType(java.awt.Window.Type.POPUP);

        TemplateListLabel.setLabelFor(TemplateList);
        TemplateListLabel.setText("Template list");

        TemplateList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TemplateListScroller.setViewportView(TemplateList);

        NewTemplateButton.setText("New");
        NewTemplateButton.setToolTipText("Add new template");
        NewTemplateButton.addActionListener(this::NewTemplateButtonActionPerformed);

        DeleteTemplateButton.setText("Delete");
        DeleteTemplateButton.setToolTipText("Delete template");
        DeleteTemplateButton.addActionListener(this::DeleteTemplateButtonActionPerformed);

        InsertTemplateButton.setText("Insert");
        InsertTemplateButton.setToolTipText("Insert template");
        InsertTemplateButton.addActionListener(this::InsertTemplateButtonActionPerformed);

        javax.swing.GroupLayout TemplatesWindowLayout = new javax.swing.GroupLayout(TemplatesWindow.getContentPane());
        TemplatesWindow.getContentPane().setLayout(TemplatesWindowLayout);
        TemplatesWindowLayout.setHorizontalGroup(
            TemplatesWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TemplatesWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TemplatesWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TemplatesWindowLayout.createSequentialGroup()
                        .addComponent(TemplateListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(TemplateListScroller))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TemplatesWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InsertTemplateButton)
                    .addComponent(DeleteTemplateButton)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TemplatesWindowLayout.createSequentialGroup()
                        .addComponent(NewTemplateButton)
                        .addContainerGap())))
        );
        TemplatesWindowLayout.setVerticalGroup(
            TemplatesWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TemplatesWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TemplateListLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TemplatesWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TemplatesWindowLayout.createSequentialGroup()
                        .addComponent(NewTemplateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteTemplateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(InsertTemplateButton))
                    .addComponent(TemplateListScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
                .addContainerGap())
        );

        NewTemplateWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        NewTemplateWindow.setTitle("New template");
        NewTemplateWindow.setMinimumSize(new java.awt.Dimension(640, 115));
        NewTemplateWindow.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        NewTemplateWindow.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        NewTemplateWindow.setName("NewTemplateWindow"); // NOI18N
        NewTemplateWindow.setResizable(false);
        NewTemplateWindow.setType(java.awt.Window.Type.POPUP);

        TemplateNameLabel.setLabelFor(TemplateNameTextInput);
        TemplateNameLabel.setText("Template name:");
        TemplateNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        SaveTemplateButton.setText("Save");
        SaveTemplateButton.setToolTipText("Save template");
        SaveTemplateButton.setFocusCycleRoot(true);
        SaveTemplateButton.addActionListener(this::SaveTemplateButtonActionPerformed);

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(this::CancelButtonActionPerformed);

        javax.swing.GroupLayout NewTemplateWindowLayout = new javax.swing.GroupLayout(NewTemplateWindow.getContentPane());
        NewTemplateWindow.getContentPane().setLayout(NewTemplateWindowLayout);
        NewTemplateWindowLayout.setHorizontalGroup(
            NewTemplateWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewTemplateWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NewTemplateWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NewTemplateWindowLayout.createSequentialGroup()
                        .addComponent(TemplateNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TemplateNameTextInput, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewTemplateWindowLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(CancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveTemplateButton)))
                .addContainerGap())
        );
        NewTemplateWindowLayout.setVerticalGroup(
            NewTemplateWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewTemplateWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NewTemplateWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TemplateNameLabel)
                    .addComponent(TemplateNameTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(NewTemplateWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveTemplateButton)
                    .addComponent(CancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setLayout(new java.awt.BorderLayout());

        CodeEditorToolbar.setRollover(true);
        CodeEditorToolbar.setPreferredSize(new java.awt.Dimension(600, 26));

        FunctionsListLabel.setText(" Current function: ");
        CodeEditorToolbar.add(FunctionsListLabel);

        CodeEditorToolbar.add(FunctionsListButton);
        CodeEditorToolbar.add(ToolbarSeparator10);

        GoToButton.setIcon(getIcon("go_to_view", 16));
        GoToButton.setToolTipText("Go to");
        GoToButton.setFocusable(false);
        GoToButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GoToButton.setMaximumSize(new java.awt.Dimension(24, 24));
        GoToButton.setMinimumSize(new java.awt.Dimension(24, 24));
        GoToButton.setPreferredSize(new java.awt.Dimension(24, 24));
        GoToButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GoToButton.addActionListener(this::GoToButtonActionPerformed);
        CodeEditorToolbar.add(GoToButton);
        CodeEditorToolbar.add(ToolbarSeparator11);

        InsertStructureButton.setIcon(getIcon("ins_structure", 16));
        InsertStructureButton.setToolTipText("Insert structure");
        InsertStructureButton.setFocusable(false);
        InsertStructureButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        InsertStructureButton.setMaximumSize(new java.awt.Dimension(24, 24));
        InsertStructureButton.setMinimumSize(new java.awt.Dimension(24, 24));
        InsertStructureButton.setPreferredSize(new java.awt.Dimension(24, 24));
        InsertStructureButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        InsertStructureButton.addActionListener(this::InsertStructureButtonActionPerformed);
        CodeEditorToolbar.add(InsertStructureButton);

        InsertEnumButton.setIcon(getIcon("ins_enum", 16));
        InsertEnumButton.setToolTipText("Insert enumeration");
        InsertEnumButton.setFocusable(false);
        InsertEnumButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        InsertEnumButton.setMaximumSize(new java.awt.Dimension(24, 24));
        InsertEnumButton.setMinimumSize(new java.awt.Dimension(24, 24));
        InsertEnumButton.setPreferredSize(new java.awt.Dimension(24, 24));
        InsertEnumButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        InsertEnumButton.addActionListener(this::InsertEnumButtonActionPerformed);
        CodeEditorToolbar.add(InsertEnumButton);

        InsertFunctionButton.setIcon(getIcon("ins_function", 16));
        InsertFunctionButton.setToolTipText("Insert function");
        InsertFunctionButton.setFocusable(false);
        InsertFunctionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        InsertFunctionButton.setMaximumSize(new java.awt.Dimension(24, 24));
        InsertFunctionButton.setMinimumSize(new java.awt.Dimension(24, 24));
        InsertFunctionButton.setPreferredSize(new java.awt.Dimension(24, 24));
        InsertFunctionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        InsertFunctionButton.addActionListener(this::InsertFunctionButtonActionPerformed);
        CodeEditorToolbar.add(InsertFunctionButton);
        CodeEditorToolbar.add(ToolbarSeparator12);

        NewBookmarkButton.setIcon(getIcon("new_bookmark", 16));
        NewBookmarkButton.setToolTipText("Add new bookmark");
        NewBookmarkButton.setFocusable(false);
        NewBookmarkButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NewBookmarkButton.setMaximumSize(new java.awt.Dimension(24, 24));
        NewBookmarkButton.setMinimumSize(new java.awt.Dimension(24, 24));
        NewBookmarkButton.setPreferredSize(new java.awt.Dimension(24, 24));
        NewBookmarkButton.addActionListener(this::NewBookmarkButtonActionPerformed);
        CodeEditorToolbar.add(NewBookmarkButton);

        PrevBookmarkButton.setIcon(getIcon("prev_bookmark", 16));
        PrevBookmarkButton.setToolTipText("Preview bookmark");
        PrevBookmarkButton.setFocusable(false);
        PrevBookmarkButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PrevBookmarkButton.setMaximumSize(new java.awt.Dimension(24, 24));
        PrevBookmarkButton.setMinimumSize(new java.awt.Dimension(24, 24));
        PrevBookmarkButton.setPreferredSize(new java.awt.Dimension(24, 24));
        PrevBookmarkButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PrevBookmarkButton.addActionListener(this::PrevBookmarkButtonActionPerformed);
        CodeEditorToolbar.add(PrevBookmarkButton);

        NextBookmarkButton.setIcon(getIcon("next_bookmark", 16));
        NextBookmarkButton.setToolTipText("Next bookmark");
        NextBookmarkButton.setFocusable(false);
        NextBookmarkButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NextBookmarkButton.setMaximumSize(new java.awt.Dimension(24, 24));
        NextBookmarkButton.setMinimumSize(new java.awt.Dimension(24, 24));
        NextBookmarkButton.setPreferredSize(new java.awt.Dimension(24, 24));
        NextBookmarkButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        NextBookmarkButton.addActionListener(this::NextBookmarkButtonActionPerformed);
        CodeEditorToolbar.add(NextBookmarkButton);

        add(CodeEditorToolbar, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    // Undo last action by editor text area popup menu item click : event
    private void UndoActionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoActionItemActionPerformed
        this.undoLastAction();
    }//GEN-LAST:event_UndoActionItemActionPerformed

    // Redo last action by editor text area popup menu item click : event
    private void RedoActionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RedoActionItemActionPerformed
        this.redoLastAction();
    }//GEN-LAST:event_RedoActionItemActionPerformed

    // Cut selected text by editor text area popup menu item click : event
    private void CutActionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CutActionItemActionPerformed
        this.cutTextAction();
    }//GEN-LAST:event_CutActionItemActionPerformed

    // Copy selected text by editor text area popup menu item click : event
    private void CopyActionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopyActionItemActionPerformed
        this.copyTextAction();
    }//GEN-LAST:event_CopyActionItemActionPerformed

    // Paste copied text from buffer by editor text area popup menu item click : event
    private void PasteActionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasteActionItemActionPerformed
        this.pasteTextAction();
    }//GEN-LAST:event_PasteActionItemActionPerformed

    // Show go to dialog window by toolbar button click : event
    private void GoToButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoToButtonActionPerformed
        showGoToDialogWindow();
    }//GEN-LAST:event_GoToButtonActionPerformed

    // Go to line by number : event
    private void GoToLineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoToLineButtonActionPerformed
        goToLineByNumber(Integer.parseInt(GoToLineTextInput.getText()));
        
        GoToLineTextInput.setText("");
        GoToStringTextInput.setText("");
        GoToDialogWindow.setVisible(false);
    }//GEN-LAST:event_GoToLineButtonActionPerformed

    private void GoToStringButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoToStringButtonActionPerformed
        goToLineByString(GoToStringTextInput.getText());
        
        GoToLineTextInput.setText("");
        GoToStringTextInput.setText("");
        GoToDialogWindow.setVisible(false);
    }//GEN-LAST:event_GoToStringButtonActionPerformed

    // Create/delete bookmark by toolbar button click : event
    private void NewBookmarkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewBookmarkButtonActionPerformed
        toggleBookmarkAction();
    }//GEN-LAST:event_NewBookmarkButtonActionPerformed

    // Go to previous bookmark by toolbar button click : event
    private void PrevBookmarkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrevBookmarkButtonActionPerformed
        goToPrevBookmark();
    }//GEN-LAST:event_PrevBookmarkButtonActionPerformed

    // Go to next bookmark by toolbar button click : event
    private void NextBookmarkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextBookmarkButtonActionPerformed
        goToNextBookmark();
    }//GEN-LAST:event_NextBookmarkButtonActionPerformed

    // Add C structure snippet by toolbar button click : event
    private void InsertStructureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertStructureButtonActionPerformed
        addCStructSnippet();
    }//GEN-LAST:event_InsertStructureButtonActionPerformed

    // Add C enumeration snippet by toolbar button click : event
    private void InsertEnumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertEnumButtonActionPerformed
        addCEnumSnippet();
    }//GEN-LAST:event_InsertEnumButtonActionPerformed

    // Add C function snippet by toolbar button click : event
    private void InsertFunctionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertFunctionButtonActionPerformed
        addCFunctionSnippet();
    }//GEN-LAST:event_InsertFunctionButtonActionPerformed

    // Show new template window by "New" button click : event
    private void NewTemplateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewTemplateButtonActionPerformed
        NewTemplateWindow.setLocationRelativeTo(null);
        NewTemplateWindow.setVisible(true);
    }//GEN-LAST:event_NewTemplateButtonActionPerformed

    // Close new template window by "Cancel" button click : event
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        NewTemplateWindow.dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    // Save current source code as template : event
    private void SaveTemplateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveTemplateButtonActionPerformed
        
        // Templates folder path
        File templatesPath = new File(System.getProperty("user.home") + "/Orion IDE/Templates/");
        
        if(!templatesPath.exists()) {
            templatesPath.mkdirs();
        }
        
        // Set template file
        String fileName = TemplateNameTextInput.getText();
        File templateFile = new File(templatesPath, fileName + ".tpl");
            
        // Get source code text from editor text area
        String sourceText = editorTextArea.getText();
            
        // Save source code text to template file
        try(Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(templateFile), StandardCharsets.UTF_8))) {
            fileWriter.write(sourceText);
            
            NewTemplateWindow.dispose();
            loadTemplateList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_SaveTemplateButtonActionPerformed

    // Delete template by button click : event
    private void DeleteTemplateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteTemplateButtonActionPerformed
        
        // Set current template
        String currentTemplate = TemplateList.getSelectedValue();
        
        // Show message dialog about template not selected
        if(currentTemplate == null) {
            JOptionPane.showMessageDialog(null, "Please, choose template for deleting.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Show confirm dialog before template deleting
        int responseDeleting = JOptionPane.showConfirmDialog(
                null,
                "You want deleting this template \"" + currentTemplate + "\"?",
                "Confirm deleting",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        
        // Nothing to do by "No" answer
        if(responseDeleting != JOptionPane.YES_OPTION) {
            return;
        }
        
        // Set selected template file path
        File templateFilePath = new File(System.getProperty("user.home") + "/Orion IDE/Templates/" + currentTemplate + ".tpl");
        
        // Deleting selected template
        if(templateFilePath.exists()) {
            if(templateFilePath.delete()) {
                DefaultListModel<String> model = (DefaultListModel<String>) TemplateList.getModel();
                model.removeElement(currentTemplate);
            } else {
                JOptionPane.showMessageDialog(null, "Template file is not exist", "Error", JOptionPane.ERROR_MESSAGE);
                
                DefaultListModel<String> model = (DefaultListModel<String>) TemplateList.getModel();
                model.removeElement(currentTemplate);
            }
        }
    }//GEN-LAST:event_DeleteTemplateButtonActionPerformed

    // Insert source text from template to editor text area by button click : event
    private void InsertTemplateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertTemplateButtonActionPerformed
                
        // Set current template
        String currentTemplate = TemplateList.getSelectedValue();
        
        // Show message dialog about template not selected
        if(currentTemplate == null) {
            JOptionPane.showMessageDialog(null, "Please, choose template for inserting.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Show confirm dialog before template inserting
        int responseInserting = JOptionPane.showConfirmDialog(
                null,
                "If you confirm, all text into editor will be replaced!",
                "Confirm inserting",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        
        // Nothing to do by "No" answer
        if(responseInserting != JOptionPane.YES_OPTION) {
            return;
        }
        
        // Set selected template file path
        File templateFilePath = new File(System.getProperty("user.home") + "/Orion IDE/Templates/" + currentTemplate + ".tpl");
        
        // Insert source code from selected template
        if(templateFilePath.exists()) {
            String content;
            
            try {
                content = Files.readString(templateFilePath.toPath(), StandardCharsets.UTF_8);
                editorTextArea.setText(content);
                
                TemplatesWindow.dispose();
            } catch (IOException ex) {
                System.getLogger(CodeEditorPanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Template file is not exist", "Error", JOptionPane.ERROR_MESSAGE);
                
            DefaultListModel<String> model = (DefaultListModel<String>) TemplateList.getModel();
            model.removeElement(currentTemplate);
        }
    }//GEN-LAST:event_InsertTemplateButtonActionPerformed

    // Load templates list : function
    private void loadTemplateList() {
        
        // Templates folder path
        File templatesPath = new File(System.getProperty("user.home") + "/Orion IDE/Templates/");
        
        // Set template list model
        DefaultListModel<String> templateListModel = new DefaultListModel<>();
        
        if(templatesPath.exists() && templatesPath.isDirectory()) {
            
            // Get all template files list by .tpl extension
            File[] templateFiles = templatesPath.listFiles((dir, name) -> name.toLowerCase().endsWith(".tpl"));
            
            if(templateFiles != null) {
                for(File file : templateFiles) {
                    String template = file.getName();
                    String templateName = template.substring(0, template.lastIndexOf("."));
                    
                    // Add templates to list model
                    templateListModel.addElement(templateName);
                }
            }
        }
        
        // Assign template list with model
        TemplateList.setModel(templateListModel);
    }
}