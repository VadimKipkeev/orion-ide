/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
 */

/*
 *******************************************************************************
 * Application main window class
 *******************************************************************************
 * Controled all sub windows and panels
 *******************************************************************************
 */
package orion.ide.ui;

import javax.swing.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class MainWindow extends JFrame {
    
    /**
     * Set FlatLaf SVG icons
     */
    private final String iconsFolder = getIconsFolder(); // Icons folder by current theme type
    
    // File menu icons
    public final FlatSVGIcon newFileIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/new_file.svg", 16, 16);
    public final FlatSVGIcon openFileIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/open_file.svg", 16, 16);
    public final FlatSVGIcon saveFileIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/save_file.svg", 16, 16);
    public final FlatSVGIcon saveAsIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/save_as.svg", 16, 16);
    public final FlatSVGIcon newProjectIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/new_project.svg", 16, 16);
    public final FlatSVGIcon openProjectIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/open_project.svg", 16, 16);
    public final FlatSVGIcon saveProjectIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/save_project.svg", 16, 16);
    public final FlatSVGIcon saveAllIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/save_all.svg", 16, 16);
    public final FlatSVGIcon printFileIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/print_file.svg", 16, 16);
    public final FlatSVGIcon printSetupIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/print_setup.svg", 16, 16);
    public final FlatSVGIcon quitAppIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/quit_app.svg", 16, 16);
    
    // Edit menu icons
    public final FlatSVGIcon undoEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/undo_edit.svg", 16, 16);
    public final FlatSVGIcon redoEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/redo_edit.svg", 16, 16);
    public final FlatSVGIcon cutEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/cut_edit.svg", 16, 16);
    public final FlatSVGIcon copyEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/copy_edit.svg", 16, 16);
    public final FlatSVGIcon pasteEditIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/paste_edit.svg", 16, 16);
    public final FlatSVGIcon findAndReplaceIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/find_and_replace.svg", 16, 16);
    public final FlatSVGIcon newBookmarkIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/new_bookmark.svg", 16, 16);
    public final FlatSVGIcon nextBookmarkIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/next_bookmark.svg", 16, 16);
    public final FlatSVGIcon prevBookmarkIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/prev_bookmark.svg", 16, 16);
    public final FlatSVGIcon settingsAppIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/settings_app.svg", 16, 16);
    
    // View menu icons
    public final FlatSVGIcon zoomInViewIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/zoom_in.svg", 16, 16);
    public final FlatSVGIcon zoomOutViewIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/zoom_out.svg", 16, 16);
    public final FlatSVGIcon setDefaultViewIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/set_def_view.svg", 16, 16);
    public final FlatSVGIcon goToViewIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/go_to_view.svg", 16, 16);
    
    // Insert menu icons
    public final FlatSVGIcon structureInsertIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/ins_structure.svg", 16, 16);
    public final FlatSVGIcon enumerationInsertIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/ins_enum.svg", 16, 16);
    public final FlatSVGIcon functionInsertIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/ins_function.svg", 16, 16);
    public final FlatSVGIcon templateInsertIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/ins_template.svg", 16, 16);
    
    // Build menu icons
    public final FlatSVGIcon releaseBuildIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/release_build.svg", 16, 16);
    public final FlatSVGIcon debugBuildIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/debug_build.svg", 16, 16);
    public final FlatSVGIcon installPackageBuildIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/install_package_build.svg", 16, 16);
    public final FlatSVGIcon configBuildIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/config_build.svg", 16, 16);
    
    // Tools menu icons
    public final FlatSVGIcon gitToolsIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/git_tools.svg", 16, 16);
    public final FlatSVGIcon terminalToolsIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/terminal_tools.svg", 16, 16);
    public final FlatSVGIcon uiDesignerToolsIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/ui_designer_tools.svg", 16, 16);
    public final FlatSVGIcon resourcesManagerIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/resources_manager.svg", 16, 16);
    
    // Help menu icons
    public final FlatSVGIcon contentsHelpIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/contents_help.svg", 16, 16);
    public final FlatSVGIcon samplesHelpIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/samples_help.svg", 16, 16);
    public final FlatSVGIcon aboutHelpIcon = new FlatSVGIcon("resources/icons/" + iconsFolder + "/about_help.svg", 16, 16);

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }
    
    // Get icons folder name by current theme type function
    private static String getIconsFolder() {
        String iconsFolder;
        
        if(!ThemeManager.getCurrentThemeType()) {
            iconsFolder = "light";
        } else {
            iconsFolder = "dark";
        }
        
        return iconsFolder;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CommonToolbar = new javax.swing.JToolBar();
        NewFileButton = new javax.swing.JButton();
        OpenFileButton = new javax.swing.JButton();
        SaveFileButton = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JToolBar.Separator();
        ContentsHelpButton = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        UndoEditButton = new javax.swing.JButton();
        RedoEditButton = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JToolBar.Separator();
        FindAndReplaceButton = new javax.swing.JButton();
        MainMenubar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        NewFileItem = new javax.swing.JMenuItem();
        OpenFileItem = new javax.swing.JMenuItem();
        SaveFileItem = new javax.swing.JMenuItem();
        SaveAsFileItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        NewProjectItem = new javax.swing.JMenuItem();
        OpenProjectItem = new javax.swing.JMenuItem();
        SaveProjectItem = new javax.swing.JMenuItem();
        SaveAllItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        PrintFileItem = new javax.swing.JMenuItem();
        PrintSetupItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        QuitItem = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        UndoEditItem = new javax.swing.JMenuItem();
        RedoEditItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        CutEditItem = new javax.swing.JMenuItem();
        CopyEditItem = new javax.swing.JMenuItem();
        PasteEditItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        FindEditItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        BookmarksMenu = new javax.swing.JMenu();
        NewBookmarkItem = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        PrevBookmarkItem = new javax.swing.JMenuItem();
        NextBookmarkItem = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        SettingsItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        ZoomInViewItem = new javax.swing.JMenuItem();
        ZoomOutViewItem = new javax.swing.JMenuItem();
        SetDefViewItem = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        GoToViewItem = new javax.swing.JMenuItem();
        InsertMenu = new javax.swing.JMenu();
        StructInsertItem = new javax.swing.JMenuItem();
        EnumInsertItem = new javax.swing.JMenuItem();
        FunctInsertItem = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        TemplateInsertItem = new javax.swing.JMenuItem();
        BuildMenu = new javax.swing.JMenu();
        ReleaseBuildItem = new javax.swing.JMenuItem();
        DebugBuildItem = new javax.swing.JMenuItem();
        InstallPkgBuildItem = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        ConfigBuildItem = new javax.swing.JMenuItem();
        ResManagerToolsItem = new javax.swing.JMenu();
        GitToolsItem = new javax.swing.JMenuItem();
        TerminalToolsItem = new javax.swing.JMenuItem();
        DesignerToolsItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        ContentsHelpItem = new javax.swing.JMenuItem();
        SamplesHelpItem = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        AboutHelpItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Orion IDE");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(240, 320));
        setName("MainWindow"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 768));

        CommonToolbar.setFloatable(true);
        CommonToolbar.setRollover(true);
        CommonToolbar.setName(""); // NOI18N

        NewFileButton.setIcon(newFileIcon);
        NewFileButton.setToolTipText("Create new file");
        NewFileButton.setAlignmentX(0.5F);
        NewFileButton.setFocusable(false);
        NewFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NewFileButton.setMaximumSize(new java.awt.Dimension(24, 24));
        NewFileButton.setMinimumSize(new java.awt.Dimension(24, 24));
        NewFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(NewFileButton);

        OpenFileButton.setIcon(openFileIcon);
        OpenFileButton.setToolTipText("Open file");
        OpenFileButton.setFocusable(false);
        OpenFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        OpenFileButton.setMaximumSize(new java.awt.Dimension(24, 24));
        OpenFileButton.setMinimumSize(new java.awt.Dimension(24, 24));
        OpenFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(OpenFileButton);

        SaveFileButton.setIcon(saveFileIcon);
        SaveFileButton.setToolTipText("Save file");
        SaveFileButton.setFocusable(false);
        SaveFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SaveFileButton.setMaximumSize(new java.awt.Dimension(24, 24));
        SaveFileButton.setMinimumSize(new java.awt.Dimension(24, 24));
        SaveFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(SaveFileButton);
        CommonToolbar.add(jSeparator13);

        ContentsHelpButton.setIcon(contentsHelpIcon);
        ContentsHelpButton.setToolTipText("Show help manual");
        ContentsHelpButton.setFocusable(false);
        ContentsHelpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ContentsHelpButton.setMaximumSize(new java.awt.Dimension(24, 24));
        ContentsHelpButton.setMinimumSize(new java.awt.Dimension(24, 24));
        ContentsHelpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(ContentsHelpButton);
        CommonToolbar.add(jSeparator14);

        UndoEditButton.setIcon(undoEditIcon);
        UndoEditButton.setToolTipText("Undo");
        UndoEditButton.setFocusable(false);
        UndoEditButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        UndoEditButton.setMaximumSize(new java.awt.Dimension(24, 24));
        UndoEditButton.setMinimumSize(new java.awt.Dimension(24, 24));
        UndoEditButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(UndoEditButton);

        RedoEditButton.setIcon(redoEditIcon);
        RedoEditButton.setToolTipText("Redo");
        RedoEditButton.setFocusable(false);
        RedoEditButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RedoEditButton.setMaximumSize(new java.awt.Dimension(24, 24));
        RedoEditButton.setMinimumSize(new java.awt.Dimension(24, 24));
        RedoEditButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(RedoEditButton);
        CommonToolbar.add(jSeparator15);

        FindAndReplaceButton.setIcon(findAndReplaceIcon);
        FindAndReplaceButton.setToolTipText("Find and replace");
        FindAndReplaceButton.setFocusable(false);
        FindAndReplaceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FindAndReplaceButton.setMaximumSize(new java.awt.Dimension(24, 24));
        FindAndReplaceButton.setMinimumSize(new java.awt.Dimension(24, 24));
        FindAndReplaceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(FindAndReplaceButton);

        FileMenu.setText("File");

        NewFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        NewFileItem.setIcon(newFileIcon);
        NewFileItem.setText("New...");
        FileMenu.add(NewFileItem);

        OpenFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        OpenFileItem.setIcon(openFileIcon);
        OpenFileItem.setText("Open...");
        FileMenu.add(OpenFileItem);

        SaveFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SaveFileItem.setIcon(saveFileIcon);
        SaveFileItem.setText("Save");
        FileMenu.add(SaveFileItem);

        SaveAsFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SaveAsFileItem.setIcon(saveAsIcon);
        SaveAsFileItem.setText("Save as...");
        FileMenu.add(SaveAsFileItem);
        FileMenu.add(jSeparator1);

        NewProjectItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        NewProjectItem.setIcon(newProjectIcon);
        NewProjectItem.setText("New project...");
        FileMenu.add(NewProjectItem);

        OpenProjectItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        OpenProjectItem.setIcon(openProjectIcon);
        OpenProjectItem.setText("Open project...");
        FileMenu.add(OpenProjectItem);

        SaveProjectItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SaveProjectItem.setIcon(saveProjectIcon);
        SaveProjectItem.setText("Save project");
        FileMenu.add(SaveProjectItem);

        SaveAllItem.setIcon(saveAllIcon);
        SaveAllItem.setText("Save all");
        FileMenu.add(SaveAllItem);
        FileMenu.add(jSeparator2);

        PrintFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        PrintFileItem.setIcon(printFileIcon);
        PrintFileItem.setText("Print");
        FileMenu.add(PrintFileItem);

        PrintSetupItem.setIcon(printSetupIcon);
        PrintSetupItem.setText("Print setup...");
        FileMenu.add(PrintSetupItem);
        FileMenu.add(jSeparator3);

        QuitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        QuitItem.setIcon(quitAppIcon);
        QuitItem.setText("Quit");
        FileMenu.add(QuitItem);

        MainMenubar.add(FileMenu);

        EditMenu.setText("Edit");

        UndoEditItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        UndoEditItem.setIcon(undoEditIcon);
        UndoEditItem.setText("Undo");
        EditMenu.add(UndoEditItem);

        RedoEditItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        RedoEditItem.setIcon(redoEditIcon);
        RedoEditItem.setText("Redo");
        EditMenu.add(RedoEditItem);
        EditMenu.add(jSeparator4);

        CutEditItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CutEditItem.setIcon(cutEditIcon);
        CutEditItem.setText("Cut");
        EditMenu.add(CutEditItem);

        CopyEditItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CopyEditItem.setIcon(copyEditIcon);
        CopyEditItem.setText("Copy");
        EditMenu.add(CopyEditItem);

        PasteEditItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        PasteEditItem.setIcon(pasteEditIcon);
        PasteEditItem.setText("Paste");
        EditMenu.add(PasteEditItem);
        EditMenu.add(jSeparator5);

        FindEditItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        FindEditItem.setIcon(findAndReplaceIcon);
        FindEditItem.setText("Find and replace...");
        EditMenu.add(FindEditItem);
        EditMenu.add(jSeparator6);

        BookmarksMenu.setText("Bookmarks");

        NewBookmarkItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        NewBookmarkItem.setIcon(newBookmarkIcon);
        NewBookmarkItem.setText("New");
        BookmarksMenu.add(NewBookmarkItem);
        BookmarksMenu.add(jSeparator7);

        PrevBookmarkItem.setIcon(prevBookmarkIcon);
        PrevBookmarkItem.setText("Preview");
        BookmarksMenu.add(PrevBookmarkItem);

        NextBookmarkItem.setIcon(nextBookmarkIcon);
        NextBookmarkItem.setText("Next");
        BookmarksMenu.add(NextBookmarkItem);

        EditMenu.add(BookmarksMenu);
        EditMenu.add(jSeparator10);

        SettingsItem.setIcon(settingsAppIcon);
        SettingsItem.setText("Settings...");
        EditMenu.add(SettingsItem);

        MainMenubar.add(EditMenu);

        ViewMenu.setText("View");

        ZoomInViewItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_EQUALS, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ZoomInViewItem.setIcon(zoomInViewIcon);
        ZoomInViewItem.setText("Zoom in");
        ViewMenu.add(ZoomInViewItem);

        ZoomOutViewItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_MINUS, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ZoomOutViewItem.setIcon(zoomOutViewIcon);
        ZoomOutViewItem.setText("Zoom out");
        ViewMenu.add(ZoomOutViewItem);

        SetDefViewItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SetDefViewItem.setIcon(setDefaultViewIcon);
        SetDefViewItem.setText("Set default");
        ViewMenu.add(SetDefViewItem);
        ViewMenu.add(jSeparator8);

        GoToViewItem.setIcon(goToViewIcon);
        GoToViewItem.setText("Go to...");
        ViewMenu.add(GoToViewItem);

        MainMenubar.add(ViewMenu);

        InsertMenu.setText("Insert");

        StructInsertItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK));
        StructInsertItem.setIcon(structureInsertIcon);
        StructInsertItem.setText("Structure");
        InsertMenu.add(StructInsertItem);

        EnumInsertItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_DOWN_MASK));
        EnumInsertItem.setIcon(enumerationInsertIcon);
        EnumInsertItem.setText("Enumeration");
        InsertMenu.add(EnumInsertItem);

        FunctInsertItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_DOWN_MASK));
        FunctInsertItem.setIcon(functionInsertIcon);
        FunctInsertItem.setText("Function");
        InsertMenu.add(FunctInsertItem);
        InsertMenu.add(jSeparator9);

        TemplateInsertItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_DOWN_MASK));
        TemplateInsertItem.setIcon(templateInsertIcon);
        TemplateInsertItem.setText("Template...");
        InsertMenu.add(TemplateInsertItem);

        MainMenubar.add(InsertMenu);

        BuildMenu.setText("Build");

        ReleaseBuildItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        ReleaseBuildItem.setIcon(releaseBuildIcon);
        ReleaseBuildItem.setText("Release");
        BuildMenu.add(ReleaseBuildItem);

        DebugBuildItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        DebugBuildItem.setIcon(debugBuildIcon);
        DebugBuildItem.setText("Debug");
        BuildMenu.add(DebugBuildItem);

        InstallPkgBuildItem.setIcon(installPackageBuildIcon);
        InstallPkgBuildItem.setText("Install package");
        BuildMenu.add(InstallPkgBuildItem);
        BuildMenu.add(jSeparator11);

        ConfigBuildItem.setIcon(configBuildIcon);
        ConfigBuildItem.setText("Configure...");
        BuildMenu.add(ConfigBuildItem);

        MainMenubar.add(BuildMenu);

        ResManagerToolsItem.setText("Tools");

        GitToolsItem.setIcon(gitToolsIcon);
        GitToolsItem.setText("Git");
        ResManagerToolsItem.add(GitToolsItem);

        TerminalToolsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        TerminalToolsItem.setIcon(terminalToolsIcon);
        TerminalToolsItem.setText("Terminal");
        ResManagerToolsItem.add(TerminalToolsItem);

        DesignerToolsItem.setIcon(uiDesignerToolsIcon);
        DesignerToolsItem.setText("UI designer");
        ResManagerToolsItem.add(DesignerToolsItem);

        jMenuItem1.setIcon(resourcesManagerIcon);
        jMenuItem1.setText("Resources manager...");
        ResManagerToolsItem.add(jMenuItem1);

        MainMenubar.add(ResManagerToolsItem);

        HelpMenu.setText("Help");

        ContentsHelpItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        ContentsHelpItem.setIcon(contentsHelpIcon);
        ContentsHelpItem.setText("Contents...");
        HelpMenu.add(ContentsHelpItem);

        SamplesHelpItem.setIcon(samplesHelpIcon);
        SamplesHelpItem.setText("Samples");
        HelpMenu.add(SamplesHelpItem);
        HelpMenu.add(jSeparator12);

        AboutHelpItem.setIcon(aboutHelpIcon);
        AboutHelpItem.setText("About...");
        HelpMenu.add(AboutHelpItem);

        MainMenubar.add(HelpMenu);

        setJMenuBar(MainMenubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CommonToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 824, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CommonToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 720, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("MainWindow");
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AboutHelpItem;
    private javax.swing.JMenu BookmarksMenu;
    private javax.swing.JMenu BuildMenu;
    private javax.swing.JToolBar CommonToolbar;
    private javax.swing.JMenuItem ConfigBuildItem;
    private javax.swing.JButton ContentsHelpButton;
    private javax.swing.JMenuItem ContentsHelpItem;
    private javax.swing.JMenuItem CopyEditItem;
    private javax.swing.JMenuItem CutEditItem;
    private javax.swing.JMenuItem DebugBuildItem;
    private javax.swing.JMenuItem DesignerToolsItem;
    private javax.swing.JMenu EditMenu;
    private javax.swing.JMenuItem EnumInsertItem;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JButton FindAndReplaceButton;
    private javax.swing.JMenuItem FindEditItem;
    private javax.swing.JMenuItem FunctInsertItem;
    private javax.swing.JMenuItem GitToolsItem;
    private javax.swing.JMenuItem GoToViewItem;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JMenu InsertMenu;
    private javax.swing.JMenuItem InstallPkgBuildItem;
    private javax.swing.JMenuBar MainMenubar;
    private javax.swing.JMenuItem NewBookmarkItem;
    private javax.swing.JButton NewFileButton;
    private javax.swing.JMenuItem NewFileItem;
    private javax.swing.JMenuItem NewProjectItem;
    private javax.swing.JMenuItem NextBookmarkItem;
    private javax.swing.JButton OpenFileButton;
    private javax.swing.JMenuItem OpenFileItem;
    private javax.swing.JMenuItem OpenProjectItem;
    private javax.swing.JMenuItem PasteEditItem;
    private javax.swing.JMenuItem PrevBookmarkItem;
    private javax.swing.JMenuItem PrintFileItem;
    private javax.swing.JMenuItem PrintSetupItem;
    private javax.swing.JMenuItem QuitItem;
    private javax.swing.JButton RedoEditButton;
    private javax.swing.JMenuItem RedoEditItem;
    private javax.swing.JMenuItem ReleaseBuildItem;
    private javax.swing.JMenu ResManagerToolsItem;
    private javax.swing.JMenuItem SamplesHelpItem;
    private javax.swing.JMenuItem SaveAllItem;
    private javax.swing.JMenuItem SaveAsFileItem;
    private javax.swing.JButton SaveFileButton;
    private javax.swing.JMenuItem SaveFileItem;
    private javax.swing.JMenuItem SaveProjectItem;
    private javax.swing.JMenuItem SetDefViewItem;
    private javax.swing.JMenuItem SettingsItem;
    private javax.swing.JMenuItem StructInsertItem;
    private javax.swing.JMenuItem TemplateInsertItem;
    private javax.swing.JMenuItem TerminalToolsItem;
    private javax.swing.JButton UndoEditButton;
    private javax.swing.JMenuItem UndoEditItem;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JMenuItem ZoomInViewItem;
    private javax.swing.JMenuItem ZoomOutViewItem;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JToolBar.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    // End of variables declaration//GEN-END:variables
}