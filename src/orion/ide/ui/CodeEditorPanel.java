/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 *******************************************************************************
 * Code editor panel class
 *******************************************************************************
 * Editor UI control methods
 *******************************************************************************
 */
package orion.ide.ui;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.io.IOException;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;
import orion.ide.core.CodeEditorTextAreaZoomListener;
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
    private final String iconsFolder = MainWindow.iconsFolder;
    private String fileExtension;
    private String textBuffer = new String();
    private boolean isFileModified;
    
    // Editor text area font size used by default zoom size
    public int defaultEditorFontSize;
    
    // Set toolbar buttons icons
    public final FlatSVGIcon goToViewIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/go_to_view.svg", 16, 16);
    public final FlatSVGIcon structureInsertIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/ins_structure.svg", 16, 16);
    public final FlatSVGIcon enumerationInsertIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/ins_enum.svg", 16, 16);
    public final FlatSVGIcon functionInsertIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/ins_function.svg", 16, 16);
    public final FlatSVGIcon newBookmarkIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/new_bookmark.svg", 16, 16);
    public final FlatSVGIcon nextBookmarkIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/next_bookmark.svg", 16, 16);
    public final FlatSVGIcon prevBookmarkIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/prev_bookmark.svg", 16, 16);
    
    // Set editor text area popup menu icons
    public final FlatSVGIcon undoEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/undo_edit.svg", 16, 16);
    public final FlatSVGIcon redoEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/redo_edit.svg", 16, 16);
    public final FlatSVGIcon cutEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/cut_edit.svg", 16, 16);
    public final FlatSVGIcon copyEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/copy_edit.svg", 16, 16);
    public final FlatSVGIcon pasteEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/paste_edit.svg", 16, 16);
    
    // Code editor view
    public RSyntaxTextArea editorTextArea = new RSyntaxTextArea();
    public RTextScrollPane editorTextAreaScroller = new RTextScrollPane(editorTextArea);
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    // Constructor
    public CodeEditorPanel() {
        initComponents();
  
        editorTextArea.setPopupMenu(EditorTextPopupMenu); // Set editor text area popup menu
        editorTextArea.addMouseWheelListener(new CodeEditorTextAreaZoomListener(editorTextArea)); // Set editor text area mouse wheel scroll event listener
        this.add(editorTextAreaScroller);
        
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
    
    // Check source text and text buffer to hidden symbols : method
    public boolean isModified() {
        String currentText = editorTextArea.getText().replace("\r\n", "\n").trim();
        String currentBuffer = textBuffer.replace("\r\n", "\n").trim();
        
        return !currentText.equals(currentBuffer); // => true or false
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
        UndoActionItem.setIcon(undoEditIcon);
        UndoActionItem.setText("Undo");
        UndoActionItem.addActionListener(this::UndoActionItemActionPerformed);
        EditorTextPopupMenu.add(UndoActionItem);

        RedoActionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        RedoActionItem.setIcon(redoEditIcon);
        RedoActionItem.setText("Redo");
        RedoActionItem.addActionListener(this::RedoActionItemActionPerformed);
        EditorTextPopupMenu.add(RedoActionItem);
        EditorTextPopupMenu.add(MenuSeparator13);

        CutActionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CutActionItem.setIcon(cutEditIcon);
        CutActionItem.setText("Cut");
        CutActionItem.addActionListener(this::CutActionItemActionPerformed);
        EditorTextPopupMenu.add(CutActionItem);

        CopyActionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CopyActionItem.setIcon(copyEditIcon);
        CopyActionItem.setText("Copy");
        CopyActionItem.addActionListener(this::CopyActionItemActionPerformed);
        EditorTextPopupMenu.add(CopyActionItem);

        PasteActionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        PasteActionItem.setIcon(pasteEditIcon);
        PasteActionItem.setText("Paste");
        PasteActionItem.addActionListener(this::PasteActionItemActionPerformed);
        EditorTextPopupMenu.add(PasteActionItem);

        setLayout(new java.awt.BorderLayout());

        CodeEditorToolbar.setRollover(true);
        CodeEditorToolbar.setPreferredSize(new java.awt.Dimension(600, 26));

        FunctionsListLabel.setText(" Current function: ");
        CodeEditorToolbar.add(FunctionsListLabel);

        CodeEditorToolbar.add(FunctionsListButton);
        CodeEditorToolbar.add(ToolbarSeparator10);

        GoToButton.setIcon(goToViewIcon);
        GoToButton.setToolTipText("Go to");
        GoToButton.setFocusable(false);
        GoToButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GoToButton.setMaximumSize(new java.awt.Dimension(24, 24));
        GoToButton.setMinimumSize(new java.awt.Dimension(24, 24));
        GoToButton.setPreferredSize(new java.awt.Dimension(24, 24));
        GoToButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CodeEditorToolbar.add(GoToButton);
        CodeEditorToolbar.add(ToolbarSeparator11);

        InsertStructureButton.setIcon(structureInsertIcon);
        InsertStructureButton.setToolTipText("Insert structure");
        InsertStructureButton.setFocusable(false);
        InsertStructureButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        InsertStructureButton.setMaximumSize(new java.awt.Dimension(24, 24));
        InsertStructureButton.setMinimumSize(new java.awt.Dimension(24, 24));
        InsertStructureButton.setPreferredSize(new java.awt.Dimension(24, 24));
        InsertStructureButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CodeEditorToolbar.add(InsertStructureButton);

        InsertEnumButton.setIcon(enumerationInsertIcon);
        InsertEnumButton.setToolTipText("Insert enumeration");
        InsertEnumButton.setFocusable(false);
        InsertEnumButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        InsertEnumButton.setMaximumSize(new java.awt.Dimension(24, 24));
        InsertEnumButton.setMinimumSize(new java.awt.Dimension(24, 24));
        InsertEnumButton.setPreferredSize(new java.awt.Dimension(24, 24));
        InsertEnumButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CodeEditorToolbar.add(InsertEnumButton);

        InsertFunctionButton.setIcon(functionInsertIcon);
        InsertFunctionButton.setToolTipText("Insert function");
        InsertFunctionButton.setFocusable(false);
        InsertFunctionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        InsertFunctionButton.setMaximumSize(new java.awt.Dimension(24, 24));
        InsertFunctionButton.setMinimumSize(new java.awt.Dimension(24, 24));
        InsertFunctionButton.setPreferredSize(new java.awt.Dimension(24, 24));
        InsertFunctionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CodeEditorToolbar.add(InsertFunctionButton);
        CodeEditorToolbar.add(ToolbarSeparator12);

        NewBookmarkButton.setIcon(newBookmarkIcon);
        NewBookmarkButton.setToolTipText("Add new bookmark");
        NewBookmarkButton.setFocusable(false);
        NewBookmarkButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NewBookmarkButton.setMaximumSize(new java.awt.Dimension(24, 24));
        NewBookmarkButton.setMinimumSize(new java.awt.Dimension(24, 24));
        NewBookmarkButton.setPreferredSize(new java.awt.Dimension(24, 24));
        CodeEditorToolbar.add(NewBookmarkButton);

        PrevBookmarkButton.setIcon(prevBookmarkIcon);
        PrevBookmarkButton.setToolTipText("Preview bookmark");
        PrevBookmarkButton.setFocusable(false);
        PrevBookmarkButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PrevBookmarkButton.setMaximumSize(new java.awt.Dimension(24, 24));
        PrevBookmarkButton.setMinimumSize(new java.awt.Dimension(24, 24));
        PrevBookmarkButton.setPreferredSize(new java.awt.Dimension(24, 24));
        PrevBookmarkButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CodeEditorToolbar.add(PrevBookmarkButton);

        NextBookmarkButton.setIcon(nextBookmarkIcon);
        NextBookmarkButton.setToolTipText("Next bookmark");
        NextBookmarkButton.setFocusable(false);
        NextBookmarkButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NextBookmarkButton.setMaximumSize(new java.awt.Dimension(24, 24));
        NextBookmarkButton.setMinimumSize(new java.awt.Dimension(24, 24));
        NextBookmarkButton.setPreferredSize(new java.awt.Dimension(24, 24));
        NextBookmarkButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar CodeEditorToolbar;
    private javax.swing.JMenuItem CopyActionItem;
    private javax.swing.JMenuItem CutActionItem;
    private javax.swing.JPopupMenu EditorTextPopupMenu;
    private javax.swing.JComboBox<String> FunctionsListButton;
    private javax.swing.JLabel FunctionsListLabel;
    private javax.swing.JButton GoToButton;
    private javax.swing.JButton InsertEnumButton;
    private javax.swing.JButton InsertFunctionButton;
    private javax.swing.JButton InsertStructureButton;
    private javax.swing.JPopupMenu.Separator MenuSeparator13;
    private javax.swing.JButton NewBookmarkButton;
    private javax.swing.JButton NextBookmarkButton;
    private javax.swing.JMenuItem PasteActionItem;
    private javax.swing.JButton PrevBookmarkButton;
    private javax.swing.JMenuItem RedoActionItem;
    private javax.swing.JToolBar.Separator ToolbarSeparator10;
    private javax.swing.JToolBar.Separator ToolbarSeparator11;
    private javax.swing.JToolBar.Separator ToolbarSeparator12;
    private javax.swing.JMenuItem UndoActionItem;
    // End of variables declaration//GEN-END:variables
}