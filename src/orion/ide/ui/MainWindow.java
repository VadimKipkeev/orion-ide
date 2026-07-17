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

        AboutDialogWindow = new javax.swing.JDialog();
        AppTitleLabel = new javax.swing.JLabel();
        AppVersionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AppDescriptionText = new javax.swing.JTextArea();
        OkButton = new javax.swing.JButton();
        MainToolbarsPanel = new javax.swing.JPanel();
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
        CodeToolbar = new javax.swing.JToolBar();
        GoToViewButton = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JToolBar.Separator();
        InsertStructureButton = new javax.swing.JButton();
        InsertEnumButton = new javax.swing.JButton();
        InsertFunctionButton = new javax.swing.JButton();
        BuildToolbar = new javax.swing.JToolBar();
        BuildReleaseButton = new javax.swing.JButton();
        BuildDebugButton = new javax.swing.JButton();
        jSeparator17 = new javax.swing.JToolBar.Separator();
        TerminalButton = new javax.swing.JButton();
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

        AboutDialogWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        AboutDialogWindow.setTitle("About");
        AboutDialogWindow.setMaximumSize(new java.awt.Dimension(455, 250));
        AboutDialogWindow.setMinimumSize(new java.awt.Dimension(455, 250));
        AboutDialogWindow.setModal(true);
        AboutDialogWindow.setName("AboutDialogWindow"); // NOI18N
        AboutDialogWindow.setPreferredSize(new java.awt.Dimension(455, 250));
        AboutDialogWindow.setResizable(false);
        AboutDialogWindow.setSize(new java.awt.Dimension(455, 250));
        AboutDialogWindow.setType(java.awt.Window.Type.POPUP);

        AppTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        AppTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        AppTitleLabel.setText("Orion IDE");

        AppVersionLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AppVersionLabel.setText("version 1.0.0 (Alnitak)");

        AppDescriptionText.setEditable(false);
        AppDescriptionText.setColumns(20);
        AppDescriptionText.setLineWrap(true);
        AppDescriptionText.setRows(5);
        AppDescriptionText.setText("(c) 2026 CrayZor. All rights reserved.\nCode editor and IDE for development native Motorola Platform 2000 applications, know as ELFs.");
        AppDescriptionText.setEnabled(false);
        AppDescriptionText.setFocusable(false);
        jScrollPane1.setViewportView(AppDescriptionText);

        OkButton.setText("OK");
        OkButton.addActionListener(this::OkButtonActionPerformed);

        javax.swing.GroupLayout AboutDialogWindowLayout = new javax.swing.GroupLayout(AboutDialogWindow.getContentPane());
        AboutDialogWindow.getContentPane().setLayout(AboutDialogWindowLayout);
        AboutDialogWindowLayout.setHorizontalGroup(
            AboutDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AboutDialogWindowLayout.createSequentialGroup()
                .addGroup(AboutDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AboutDialogWindowLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(OkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AboutDialogWindowLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(AboutDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AppTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AppVersionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))))
                .addContainerGap())
        );
        AboutDialogWindowLayout.setVerticalGroup(
            AboutDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutDialogWindowLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(AppTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AppVersionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OkButton)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Orion IDE");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(240, 320));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 768));

        MainToolbarsPanel.setMinimumSize(new java.awt.Dimension(800, 26));
        MainToolbarsPanel.setPreferredSize(new java.awt.Dimension(1024, 26));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        MainToolbarsPanel.setLayout(flowLayout1);

        CommonToolbar.setFloatable(true);
        CommonToolbar.setRollover(true);
        CommonToolbar.setMaximumSize(new java.awt.Dimension(200, 26));
        CommonToolbar.setMinimumSize(new java.awt.Dimension(200, 26));
        CommonToolbar.setName(""); // NOI18N
        CommonToolbar.setPreferredSize(new java.awt.Dimension(200, 26));

        NewFileButton.setIcon(newFileIcon);
        NewFileButton.setToolTipText("Create new file");
        NewFileButton.setFocusable(false);
        NewFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NewFileButton.setMaximumSize(new java.awt.Dimension(24, 24));
        NewFileButton.setMinimumSize(new java.awt.Dimension(24, 24));
        NewFileButton.setPreferredSize(new java.awt.Dimension(24, 24));
        NewFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(NewFileButton);

        OpenFileButton.setIcon(openFileIcon);
        OpenFileButton.setToolTipText("Open file");
        OpenFileButton.setFocusable(false);
        OpenFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        OpenFileButton.setMaximumSize(new java.awt.Dimension(24, 24));
        OpenFileButton.setMinimumSize(new java.awt.Dimension(24, 24));
        OpenFileButton.setPreferredSize(new java.awt.Dimension(24, 24));
        OpenFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(OpenFileButton);

        SaveFileButton.setIcon(saveFileIcon);
        SaveFileButton.setToolTipText("Save file");
        SaveFileButton.setFocusable(false);
        SaveFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SaveFileButton.setMaximumSize(new java.awt.Dimension(24, 24));
        SaveFileButton.setMinimumSize(new java.awt.Dimension(24, 24));
        SaveFileButton.setPreferredSize(new java.awt.Dimension(24, 24));
        SaveFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(SaveFileButton);
        CommonToolbar.add(jSeparator13);

        ContentsHelpButton.setIcon(contentsHelpIcon);
        ContentsHelpButton.setToolTipText("Show help manual");
        ContentsHelpButton.setFocusable(false);
        ContentsHelpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ContentsHelpButton.setMaximumSize(new java.awt.Dimension(24, 24));
        ContentsHelpButton.setMinimumSize(new java.awt.Dimension(24, 24));
        ContentsHelpButton.setPreferredSize(new java.awt.Dimension(24, 24));
        ContentsHelpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(ContentsHelpButton);
        CommonToolbar.add(jSeparator14);

        UndoEditButton.setIcon(undoEditIcon);
        UndoEditButton.setToolTipText("Undo");
        UndoEditButton.setFocusable(false);
        UndoEditButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        UndoEditButton.setMaximumSize(new java.awt.Dimension(24, 24));
        UndoEditButton.setMinimumSize(new java.awt.Dimension(24, 24));
        UndoEditButton.setPreferredSize(new java.awt.Dimension(24, 24));
        UndoEditButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(UndoEditButton);

        RedoEditButton.setIcon(redoEditIcon);
        RedoEditButton.setToolTipText("Redo");
        RedoEditButton.setFocusable(false);
        RedoEditButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RedoEditButton.setMaximumSize(new java.awt.Dimension(24, 24));
        RedoEditButton.setMinimumSize(new java.awt.Dimension(24, 24));
        RedoEditButton.setPreferredSize(new java.awt.Dimension(24, 24));
        RedoEditButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(RedoEditButton);
        CommonToolbar.add(jSeparator15);

        FindAndReplaceButton.setIcon(findAndReplaceIcon);
        FindAndReplaceButton.setToolTipText("Find and replace text");
        FindAndReplaceButton.setFocusable(false);
        FindAndReplaceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FindAndReplaceButton.setMaximumSize(new java.awt.Dimension(24, 24));
        FindAndReplaceButton.setMinimumSize(new java.awt.Dimension(24, 24));
        FindAndReplaceButton.setPreferredSize(new java.awt.Dimension(24, 24));
        FindAndReplaceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(FindAndReplaceButton);

        MainToolbarsPanel.add(CommonToolbar);

        CodeToolbar.setFloatable(true);
        CodeToolbar.setRollover(true);
        CodeToolbar.setMaximumSize(new java.awt.Dimension(115, 26));
        CodeToolbar.setMinimumSize(new java.awt.Dimension(115, 26));
        CodeToolbar.setName(""); // NOI18N
        CodeToolbar.setPreferredSize(new java.awt.Dimension(115, 26));

        GoToViewButton.setIcon(goToViewIcon);
        GoToViewButton.setToolTipText("Go to line");
        GoToViewButton.setFocusable(false);
        GoToViewButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GoToViewButton.setMaximumSize(new java.awt.Dimension(24, 24));
        GoToViewButton.setMinimumSize(new java.awt.Dimension(24, 24));
        GoToViewButton.setPreferredSize(new java.awt.Dimension(24, 24));
        GoToViewButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CodeToolbar.add(GoToViewButton);
        CodeToolbar.add(jSeparator16);

        InsertStructureButton.setIcon(structureInsertIcon);
        InsertStructureButton.setToolTipText("Add new structure");
        InsertStructureButton.setFocusable(false);
        InsertStructureButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        InsertStructureButton.setMaximumSize(new java.awt.Dimension(24, 24));
        InsertStructureButton.setMinimumSize(new java.awt.Dimension(24, 24));
        InsertStructureButton.setPreferredSize(new java.awt.Dimension(24, 24));
        InsertStructureButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CodeToolbar.add(InsertStructureButton);

        InsertEnumButton.setIcon(enumerationInsertIcon);
        InsertEnumButton.setToolTipText("Add new enumeration");
        InsertEnumButton.setFocusable(false);
        InsertEnumButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        InsertEnumButton.setMaximumSize(new java.awt.Dimension(24, 24));
        InsertEnumButton.setMinimumSize(new java.awt.Dimension(24, 24));
        InsertEnumButton.setPreferredSize(new java.awt.Dimension(24, 24));
        InsertEnumButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CodeToolbar.add(InsertEnumButton);

        InsertFunctionButton.setIcon(functionInsertIcon);
        InsertFunctionButton.setToolTipText("Add new function");
        InsertFunctionButton.setFocusable(false);
        InsertFunctionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        InsertFunctionButton.setMaximumSize(new java.awt.Dimension(24, 24));
        InsertFunctionButton.setMinimumSize(new java.awt.Dimension(24, 24));
        InsertFunctionButton.setPreferredSize(new java.awt.Dimension(24, 24));
        InsertFunctionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CodeToolbar.add(InsertFunctionButton);

        MainToolbarsPanel.add(CodeToolbar);

        BuildToolbar.setFloatable(true);
        BuildToolbar.setRollover(true);
        BuildToolbar.setMaximumSize(new java.awt.Dimension(95, 26));
        BuildToolbar.setMinimumSize(new java.awt.Dimension(95, 26));
        BuildToolbar.setName(""); // NOI18N
        BuildToolbar.setPreferredSize(new java.awt.Dimension(95, 26));

        BuildReleaseButton.setIcon(releaseBuildIcon);
        BuildReleaseButton.setToolTipText("Build project (release)");
        BuildReleaseButton.setFocusable(false);
        BuildReleaseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BuildReleaseButton.setMaximumSize(new java.awt.Dimension(24, 24));
        BuildReleaseButton.setMinimumSize(new java.awt.Dimension(24, 24));
        BuildReleaseButton.setPreferredSize(new java.awt.Dimension(24, 24));
        BuildReleaseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuildToolbar.add(BuildReleaseButton);

        BuildDebugButton.setIcon(debugBuildIcon);
        BuildDebugButton.setToolTipText("Build project (debug)");
        BuildDebugButton.setFocusable(false);
        BuildDebugButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BuildDebugButton.setMaximumSize(new java.awt.Dimension(24, 24));
        BuildDebugButton.setMinimumSize(new java.awt.Dimension(24, 24));
        BuildDebugButton.setPreferredSize(new java.awt.Dimension(24, 24));
        BuildDebugButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuildToolbar.add(BuildDebugButton);
        BuildToolbar.add(jSeparator17);

        TerminalButton.setIcon(terminalToolsIcon);
        TerminalButton.setToolTipText("Show terminal window");
        TerminalButton.setFocusable(false);
        TerminalButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TerminalButton.setMaximumSize(new java.awt.Dimension(24, 24));
        TerminalButton.setMinimumSize(new java.awt.Dimension(24, 24));
        TerminalButton.setPreferredSize(new java.awt.Dimension(24, 24));
        TerminalButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuildToolbar.add(TerminalButton);

        MainToolbarsPanel.add(BuildToolbar);

        getContentPane().add(MainToolbarsPanel, java.awt.BorderLayout.NORTH);

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
        QuitItem.addActionListener(this::QuitItemActionPerformed);
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
        AboutHelpItem.addActionListener(this::AboutHelpItemActionPerformed);
        HelpMenu.add(AboutHelpItem);

        MainMenubar.add(HelpMenu);

        setJMenuBar(MainMenubar);

        getAccessibleContext().setAccessibleName("");
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Show about application dialog window function
    private void AboutHelpItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutHelpItemActionPerformed
        AboutDialogWindow.setSize(455, 250); // Set window size
        AboutDialogWindow.setLocationRelativeTo(null); // Set window position to center of screen
        AboutDialogWindow.setVisible(true);
    }//GEN-LAST:event_AboutHelpItemActionPerformed

    // Close about application dialog window by OK button click function
    private void OkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkButtonActionPerformed
        AboutDialogWindow.dispose();
    }//GEN-LAST:event_OkButtonActionPerformed
    
    // Close main window and exit from application
    private void QuitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitItemActionPerformed
        this.dispose();
        System.exit(0); // Exist from application process
    }//GEN-LAST:event_QuitItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AboutDialogWindow;
    private javax.swing.JMenuItem AboutHelpItem;
    private javax.swing.JTextArea AppDescriptionText;
    private javax.swing.JLabel AppTitleLabel;
    private javax.swing.JLabel AppVersionLabel;
    private javax.swing.JMenu BookmarksMenu;
    private javax.swing.JButton BuildDebugButton;
    private javax.swing.JMenu BuildMenu;
    private javax.swing.JButton BuildReleaseButton;
    private javax.swing.JToolBar BuildToolbar;
    private javax.swing.JToolBar CodeToolbar;
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
    private javax.swing.JButton GoToViewButton;
    private javax.swing.JMenuItem GoToViewItem;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JButton InsertEnumButton;
    private javax.swing.JButton InsertFunctionButton;
    private javax.swing.JMenu InsertMenu;
    private javax.swing.JButton InsertStructureButton;
    private javax.swing.JMenuItem InstallPkgBuildItem;
    private javax.swing.JMenuBar MainMenubar;
    private javax.swing.JPanel MainToolbarsPanel;
    private javax.swing.JMenuItem NewBookmarkItem;
    private javax.swing.JButton NewFileButton;
    private javax.swing.JMenuItem NewFileItem;
    private javax.swing.JMenuItem NewProjectItem;
    private javax.swing.JMenuItem NextBookmarkItem;
    private javax.swing.JButton OkButton;
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
    private javax.swing.JButton TerminalButton;
    private javax.swing.JMenuItem TerminalToolsItem;
    private javax.swing.JButton UndoEditButton;
    private javax.swing.JMenuItem UndoEditItem;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JMenuItem ZoomInViewItem;
    private javax.swing.JMenuItem ZoomOutViewItem;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JToolBar.Separator jSeparator15;
    private javax.swing.JToolBar.Separator jSeparator16;
    private javax.swing.JToolBar.Separator jSeparator17;
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