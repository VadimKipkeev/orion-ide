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
import java.beans.PropertyVetoException;

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
    
    // Output window icons
    public final FlatSVGIcon showAllLogIcon = new FlatSVGIcon("resources/icons/commons/show_all_log.svg", 16, 16);
    public final FlatSVGIcon showErrorsLogIcon = new FlatSVGIcon("resources/icons/commons/show_errors_log.svg", 16, 16);
    public final FlatSVGIcon clearBuildLogIcon = new FlatSVGIcon("resources/icons/commons/clear_build_log.svg", 16, 16);
    public final FlatSVGIcon gitCommitIcon = new FlatSVGIcon("resources/icons/commons/git_commit.svg", 16, 16);
    public final FlatSVGIcon gitFetchIcon = new FlatSVGIcon("resources/icons/commons/git_fetch.svg", 16, 16);
    public final FlatSVGIcon gitPullIcon = new FlatSVGIcon("resources/icons/commons/git_pull.svg", 16, 16);
    public final FlatSVGIcon gitCheckoutIcon = new FlatSVGIcon("resources/icons/commons/git_checkout.svg", 16, 16);
    public final FlatSVGIcon gitPushIcon = new FlatSVGIcon("resources/icons/commons/git_push.svg", 16, 16);

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
        AppDescriptionScroller = new javax.swing.JScrollPane();
        AppDescriptionText = new javax.swing.JTextArea();
        AboutDialogOkButton = new javax.swing.JButton();
        MainToolbarsPanel = new javax.swing.JPanel();
        CommonToolbar = new javax.swing.JToolBar();
        NewFileButton = new javax.swing.JButton();
        OpenFileButton = new javax.swing.JButton();
        SaveFileButton = new javax.swing.JButton();
        ToolbarSeparator1 = new javax.swing.JToolBar.Separator();
        ContentsHelpButton = new javax.swing.JButton();
        ToolbarSeparator2 = new javax.swing.JToolBar.Separator();
        UndoEditButton = new javax.swing.JButton();
        RedoEditButton = new javax.swing.JButton();
        ToolbarSeparator3 = new javax.swing.JToolBar.Separator();
        FindAndReplaceButton = new javax.swing.JButton();
        CodeToolbar = new javax.swing.JToolBar();
        GoToViewButton = new javax.swing.JButton();
        ToolbarSeparator4 = new javax.swing.JToolBar.Separator();
        InsertStructureButton = new javax.swing.JButton();
        InsertEnumButton = new javax.swing.JButton();
        InsertFunctionButton = new javax.swing.JButton();
        BuildToolbar = new javax.swing.JToolBar();
        BuildReleaseButton = new javax.swing.JButton();
        BuildDebugButton = new javax.swing.JButton();
        ToolbarSeparator5 = new javax.swing.JToolBar.Separator();
        TerminalButton = new javax.swing.JButton();
        StatusbarPanel = new javax.swing.JPanel();
        AppStatusPanel = new javax.swing.JPanel();
        AppStatusLabel = new javax.swing.JLabel();
        AppIndicationPanel = new javax.swing.JPanel();
        CapsStatusLabel = new javax.swing.JLabel();
        ToolbarSeparator6 = new javax.swing.JSeparator();
        EncodeStatusLabel = new javax.swing.JLabel();
        FrameSplitPanel = new javax.swing.JSplitPane();
        ProjectExplorerFrame = new javax.swing.JInternalFrame();
        ProjectExplorerTabs = new javax.swing.JTabbedPane();
        ProjectStructurePanel = new javax.swing.JPanel();
        StructureTreeScroller = new javax.swing.JScrollPane();
        StructureTreeList = new javax.swing.JTree();
        ProjectFilesPanel = new javax.swing.JPanel();
        FilesTreeScroller = new javax.swing.JScrollPane();
        FilesTreeList = new javax.swing.JTree();
        EditorSplitPanel = new javax.swing.JSplitPane();
        OutputFrame = new javax.swing.JInternalFrame();
        OutputFrameTabs = new javax.swing.JTabbedPane();
        BuildLogPanel = new javax.swing.JPanel();
        BuildLogToolbar = new javax.swing.JToolBar();
        ShowAllMessageButton = new javax.swing.JButton();
        ErrorsFilterButton = new javax.swing.JButton();
        ToolbarSeparator7 = new javax.swing.JToolBar.Separator();
        ClearBuildLogButton = new javax.swing.JButton();
        BuildLogScroller = new javax.swing.JScrollPane();
        BuildLogViewer = new javax.swing.JEditorPane();
        TerminalPanel = new javax.swing.JPanel();
        TerminalScroller = new javax.swing.JScrollPane();
        TerminalViewer = new javax.swing.JEditorPane();
        GitPanel = new javax.swing.JPanel();
        GitToolbar = new javax.swing.JToolBar();
        GitCommitButton = new javax.swing.JButton();
        ToolbarSeparator8 = new javax.swing.JToolBar.Separator();
        GitFetchButton = new javax.swing.JButton();
        GitPullButton = new javax.swing.JButton();
        GitCheckoutButton = new javax.swing.JButton();
        ToolbarSeparator9 = new javax.swing.JToolBar.Separator();
        GitPushButton = new javax.swing.JButton();
        GitOutputViewer = new java.awt.TextArea();
        EditorMDIFrame = new javax.swing.JDesktopPane();
        MainMenubar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        NewFileItem = new javax.swing.JMenuItem();
        OpenFileItem = new javax.swing.JMenuItem();
        SaveFileItem = new javax.swing.JMenuItem();
        SaveAsFileItem = new javax.swing.JMenuItem();
        MenuSeparator1 = new javax.swing.JPopupMenu.Separator();
        NewProjectItem = new javax.swing.JMenuItem();
        OpenProjectItem = new javax.swing.JMenuItem();
        SaveProjectItem = new javax.swing.JMenuItem();
        SaveAllItem = new javax.swing.JMenuItem();
        MenuSeparator2 = new javax.swing.JPopupMenu.Separator();
        PrintFileItem = new javax.swing.JMenuItem();
        PrintSetupItem = new javax.swing.JMenuItem();
        MenuSeparator3 = new javax.swing.JPopupMenu.Separator();
        QuitItem = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        UndoEditItem = new javax.swing.JMenuItem();
        RedoEditItem = new javax.swing.JMenuItem();
        MenuSeparator4 = new javax.swing.JPopupMenu.Separator();
        CutEditItem = new javax.swing.JMenuItem();
        CopyEditItem = new javax.swing.JMenuItem();
        PasteEditItem = new javax.swing.JMenuItem();
        MenuSeparator5 = new javax.swing.JPopupMenu.Separator();
        FindEditItem = new javax.swing.JMenuItem();
        MenuSeparator6 = new javax.swing.JPopupMenu.Separator();
        BookmarksMenu = new javax.swing.JMenu();
        NewBookmarkItem = new javax.swing.JMenuItem();
        MenuSeparator8 = new javax.swing.JPopupMenu.Separator();
        PrevBookmarkItem = new javax.swing.JMenuItem();
        NextBookmarkItem = new javax.swing.JMenuItem();
        MenuSeparator7 = new javax.swing.JPopupMenu.Separator();
        SettingsItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        ZoomInViewItem = new javax.swing.JMenuItem();
        ZoomOutViewItem = new javax.swing.JMenuItem();
        SetDefViewItem = new javax.swing.JMenuItem();
        MenuSeparator9 = new javax.swing.JPopupMenu.Separator();
        GoToViewItem = new javax.swing.JMenuItem();
        MenuSeparator10 = new javax.swing.JPopupMenu.Separator();
        ProjectExplorerItem = new javax.swing.JCheckBoxMenuItem();
        OutputWindowItem = new javax.swing.JCheckBoxMenuItem();
        InsertMenu = new javax.swing.JMenu();
        StructInsertItem = new javax.swing.JMenuItem();
        EnumInsertItem = new javax.swing.JMenuItem();
        FunctInsertItem = new javax.swing.JMenuItem();
        MenuSeparator11 = new javax.swing.JPopupMenu.Separator();
        TemplateInsertItem = new javax.swing.JMenuItem();
        BuildMenu = new javax.swing.JMenu();
        ReleaseBuildItem = new javax.swing.JMenuItem();
        DebugBuildItem = new javax.swing.JMenuItem();
        InstallPkgBuildItem = new javax.swing.JMenuItem();
        MenuSeparator12 = new javax.swing.JPopupMenu.Separator();
        ConfigBuildItem = new javax.swing.JMenuItem();
        ToolsMenu = new javax.swing.JMenu();
        GitToolsItem = new javax.swing.JMenuItem();
        TerminalToolsItem = new javax.swing.JMenuItem();
        DesignerToolsItem = new javax.swing.JMenuItem();
        ResManagerToolsItem = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        ContentsHelpItem = new javax.swing.JMenuItem();
        SamplesHelpItem = new javax.swing.JMenuItem();
        MenuSeparator13 = new javax.swing.JPopupMenu.Separator();
        AboutHelpItem = new javax.swing.JMenuItem();

        AboutDialogWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        AboutDialogWindow.setTitle("About");
        AboutDialogWindow.setMinimumSize(new java.awt.Dimension(455, 250));
        AboutDialogWindow.setModal(true);
        AboutDialogWindow.setName("AboutDialogWindow"); // NOI18N
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
        AppDescriptionScroller.setViewportView(AppDescriptionText);

        AboutDialogOkButton.setText("OK");
        AboutDialogOkButton.addActionListener(this::AboutDialogOkButtonActionPerformed);

        javax.swing.GroupLayout AboutDialogWindowLayout = new javax.swing.GroupLayout(AboutDialogWindow.getContentPane());
        AboutDialogWindow.getContentPane().setLayout(AboutDialogWindowLayout);
        AboutDialogWindowLayout.setHorizontalGroup(
            AboutDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AboutDialogWindowLayout.createSequentialGroup()
                .addGroup(AboutDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AboutDialogWindowLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AboutDialogOkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AboutDialogWindowLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(AboutDialogWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AppTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AppVersionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AppDescriptionScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))))
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
                .addComponent(AppDescriptionScroller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AboutDialogOkButton)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Orion IDE");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(240, 320));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 768));

        MainToolbarsPanel.setMaximumSize(new java.awt.Dimension(32767, 26));
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
        CommonToolbar.add(ToolbarSeparator1);

        ContentsHelpButton.setIcon(contentsHelpIcon);
        ContentsHelpButton.setToolTipText("Show help manual");
        ContentsHelpButton.setFocusable(false);
        ContentsHelpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ContentsHelpButton.setMaximumSize(new java.awt.Dimension(24, 24));
        ContentsHelpButton.setMinimumSize(new java.awt.Dimension(24, 24));
        ContentsHelpButton.setPreferredSize(new java.awt.Dimension(24, 24));
        ContentsHelpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CommonToolbar.add(ContentsHelpButton);
        CommonToolbar.add(ToolbarSeparator2);

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
        CommonToolbar.add(ToolbarSeparator3);

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
        CodeToolbar.add(ToolbarSeparator4);

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
        BuildToolbar.add(ToolbarSeparator5);

        TerminalButton.setIcon(terminalToolsIcon);
        TerminalButton.setToolTipText("Show terminal window");
        TerminalButton.setFocusable(false);
        TerminalButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TerminalButton.setMaximumSize(new java.awt.Dimension(24, 24));
        TerminalButton.setMinimumSize(new java.awt.Dimension(24, 24));
        TerminalButton.setPreferredSize(new java.awt.Dimension(24, 24));
        TerminalButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        TerminalButton.addActionListener(this::TerminalButtonActionPerformed);
        BuildToolbar.add(TerminalButton);

        MainToolbarsPanel.add(BuildToolbar);

        getContentPane().add(MainToolbarsPanel, java.awt.BorderLayout.NORTH);

        StatusbarPanel.setMaximumSize(new java.awt.Dimension(32767, 26));
        StatusbarPanel.setMinimumSize(new java.awt.Dimension(800, 26));
        StatusbarPanel.setPreferredSize(new java.awt.Dimension(1024, 26));
        StatusbarPanel.setLayout(new java.awt.BorderLayout());

        AppStatusPanel.setFocusable(false);
        AppStatusPanel.setMaximumSize(new java.awt.Dimension(100, 26));
        AppStatusPanel.setMinimumSize(new java.awt.Dimension(100, 26));
        AppStatusPanel.setName(""); // NOI18N
        AppStatusPanel.setPreferredSize(new java.awt.Dimension(100, 26));
        AppStatusPanel.setRequestFocusEnabled(false);
        AppStatusPanel.setVerifyInputWhenFocusTarget(false);

        AppStatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        AppStatusLabel.setText("Ready");
        AppStatusLabel.setAlignmentY(0.0F);
        AppStatusLabel.setFocusable(false);
        AppStatusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        AppStatusLabel.setMaximumSize(new java.awt.Dimension(90, 26));
        AppStatusLabel.setMinimumSize(new java.awt.Dimension(90, 26));
        AppStatusLabel.setName(""); // NOI18N
        AppStatusLabel.setPreferredSize(new java.awt.Dimension(90, 26));
        AppStatusLabel.setRequestFocusEnabled(false);
        AppStatusLabel.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout AppStatusPanelLayout = new javax.swing.GroupLayout(AppStatusPanel);
        AppStatusPanel.setLayout(AppStatusPanelLayout);
        AppStatusPanelLayout.setHorizontalGroup(
            AppStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AppStatusPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AppStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        AppStatusPanelLayout.setVerticalGroup(
            AppStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AppStatusPanelLayout.createSequentialGroup()
                .addComponent(AppStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        StatusbarPanel.add(AppStatusPanel, java.awt.BorderLayout.WEST);

        AppIndicationPanel.setFocusable(false);
        AppIndicationPanel.setMaximumSize(new java.awt.Dimension(100, 26));
        AppIndicationPanel.setMinimumSize(new java.awt.Dimension(100, 26));
        AppIndicationPanel.setPreferredSize(new java.awt.Dimension(100, 26));
        AppIndicationPanel.setRequestFocusEnabled(false);
        AppIndicationPanel.setVerifyInputWhenFocusTarget(false);
        AppIndicationPanel.setLayout(new java.awt.BorderLayout());

        CapsStatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CapsStatusLabel.setText("OFF");
        CapsStatusLabel.setToolTipText("Caps Lock status");
        CapsStatusLabel.setAlignmentY(0.0F);
        CapsStatusLabel.setFocusable(false);
        CapsStatusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CapsStatusLabel.setMaximumSize(new java.awt.Dimension(48, 26));
        CapsStatusLabel.setMinimumSize(new java.awt.Dimension(48, 26));
        CapsStatusLabel.setPreferredSize(new java.awt.Dimension(48, 26));
        CapsStatusLabel.setRequestFocusEnabled(false);
        CapsStatusLabel.setVerifyInputWhenFocusTarget(false);
        AppIndicationPanel.add(CapsStatusLabel, java.awt.BorderLayout.WEST);

        ToolbarSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        AppIndicationPanel.add(ToolbarSeparator6, java.awt.BorderLayout.CENTER);

        EncodeStatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EncodeStatusLabel.setText("NOT");
        EncodeStatusLabel.setToolTipText("Text encoding information");
        EncodeStatusLabel.setAlignmentY(0.0F);
        EncodeStatusLabel.setFocusable(false);
        EncodeStatusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EncodeStatusLabel.setMaximumSize(new java.awt.Dimension(48, 26));
        EncodeStatusLabel.setMinimumSize(new java.awt.Dimension(48, 26));
        EncodeStatusLabel.setPreferredSize(new java.awt.Dimension(48, 26));
        EncodeStatusLabel.setRequestFocusEnabled(false);
        EncodeStatusLabel.setVerifyInputWhenFocusTarget(false);
        AppIndicationPanel.add(EncodeStatusLabel, java.awt.BorderLayout.EAST);

        StatusbarPanel.add(AppIndicationPanel, java.awt.BorderLayout.EAST);

        getContentPane().add(StatusbarPanel, java.awt.BorderLayout.SOUTH);

        FrameSplitPanel.setDividerLocation(200);
        FrameSplitPanel.setLastDividerLocation(200);
        FrameSplitPanel.setMinimumSize(new java.awt.Dimension(200, 36));
        FrameSplitPanel.setPreferredSize(new java.awt.Dimension(200, 36));

        ProjectExplorerFrame.setClosable(true);
        ProjectExplorerFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ProjectExplorerFrame.setResizable(true);
        ProjectExplorerFrame.setTitle("Project explorer");
        ProjectExplorerFrame.setAlignmentX(0.0F);
        ProjectExplorerFrame.setAlignmentY(0.0F);
        ProjectExplorerFrame.setFrameIcon(null);
        ProjectExplorerFrame.setMinimumSize(new java.awt.Dimension(200, 13));
        ProjectExplorerFrame.setName(""); // NOI18N
        ProjectExplorerFrame.setPreferredSize(new java.awt.Dimension(200, 36));
        ProjectExplorerFrame.setVisible(true);
        ProjectExplorerFrame.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                ProjectExplorerFrameInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        ProjectExplorerFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                ProjectExplorerFrameComponentHidden(evt);
            }
        });

        ProjectExplorerTabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Empty");
        StructureTreeList.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        StructureTreeList.setCellRenderer(null);
        StructureTreeList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        StructureTreeList.setEditable(true);
        StructureTreeScroller.setViewportView(StructureTreeList);

        javax.swing.GroupLayout ProjectStructurePanelLayout = new javax.swing.GroupLayout(ProjectStructurePanel);
        ProjectStructurePanel.setLayout(ProjectStructurePanelLayout);
        ProjectStructurePanelLayout.setHorizontalGroup(
            ProjectStructurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StructureTreeScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        ProjectStructurePanelLayout.setVerticalGroup(
            ProjectStructurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StructureTreeScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        ProjectExplorerTabs.addTab("Structure", null, ProjectStructurePanel, "Project structure");

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Empty");
        FilesTreeList.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        FilesTreeScroller.setViewportView(FilesTreeList);

        javax.swing.GroupLayout ProjectFilesPanelLayout = new javax.swing.GroupLayout(ProjectFilesPanel);
        ProjectFilesPanel.setLayout(ProjectFilesPanelLayout);
        ProjectFilesPanelLayout.setHorizontalGroup(
            ProjectFilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FilesTreeScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        ProjectFilesPanelLayout.setVerticalGroup(
            ProjectFilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FilesTreeScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        ProjectExplorerTabs.addTab("Files", null, ProjectFilesPanel, "Project files");

        javax.swing.GroupLayout ProjectExplorerFrameLayout = new javax.swing.GroupLayout(ProjectExplorerFrame.getContentPane());
        ProjectExplorerFrame.getContentPane().setLayout(ProjectExplorerFrameLayout);
        ProjectExplorerFrameLayout.setHorizontalGroup(
            ProjectExplorerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProjectExplorerTabs)
        );
        ProjectExplorerFrameLayout.setVerticalGroup(
            ProjectExplorerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProjectExplorerTabs)
        );

        ProjectExplorerTabs.getAccessibleContext().setAccessibleName("");

        FrameSplitPanel.setLeftComponent(ProjectExplorerFrame);

        EditorSplitPanel.setDividerLocation(800);
        EditorSplitPanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        EditorSplitPanel.setLastDividerLocation(800);
        EditorSplitPanel.setMinimumSize(new java.awt.Dimension(93, 200));
        EditorSplitPanel.setPreferredSize(new java.awt.Dimension(93, 200));

        OutputFrame.setClosable(true);
        OutputFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        OutputFrame.setResizable(true);
        OutputFrame.setTitle("Output");
        OutputFrame.setAlignmentX(0.0F);
        OutputFrame.setAlignmentY(0.0F);
        OutputFrame.setFrameIcon(null);
        OutputFrame.setMinimumSize(new java.awt.Dimension(56, 200));
        OutputFrame.setPreferredSize(new java.awt.Dimension(28, 200));
        OutputFrame.setVisible(true);
        OutputFrame.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                OutputFrameInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        OutputFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                OutputFrameComponentHidden(evt);
            }
        });

        BuildLogToolbar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        BuildLogToolbar.setRollover(true);
        BuildLogToolbar.setAlignmentX(0.5F);
        BuildLogToolbar.setAlignmentY(0.0F);
        BuildLogToolbar.setAutoscrolls(true);
        BuildLogToolbar.setMaximumSize(new java.awt.Dimension(32, 3000));
        BuildLogToolbar.setMinimumSize(new java.awt.Dimension(32, 100));
        BuildLogToolbar.setName(""); // NOI18N
        BuildLogToolbar.setPreferredSize(new java.awt.Dimension(32, 100));

        ShowAllMessageButton.setIcon(showAllLogIcon);
        ShowAllMessageButton.setToolTipText("Show all build log messages");
        ShowAllMessageButton.setAlignmentX(0.5F);
        ShowAllMessageButton.setAlignmentY(0.0F);
        ShowAllMessageButton.setFocusable(false);
        ShowAllMessageButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ShowAllMessageButton.setMaximumSize(new java.awt.Dimension(24, 24));
        ShowAllMessageButton.setMinimumSize(new java.awt.Dimension(24, 24));
        ShowAllMessageButton.setPreferredSize(new java.awt.Dimension(24, 24));
        ShowAllMessageButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuildLogToolbar.add(ShowAllMessageButton);

        ErrorsFilterButton.setIcon(showErrorsLogIcon);
        ErrorsFilterButton.setToolTipText("Show only errors build log messages");
        ErrorsFilterButton.setAlignmentX(0.5F);
        ErrorsFilterButton.setAlignmentY(0.0F);
        ErrorsFilterButton.setFocusable(false);
        ErrorsFilterButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ErrorsFilterButton.setMaximumSize(new java.awt.Dimension(24, 24));
        ErrorsFilterButton.setMinimumSize(new java.awt.Dimension(24, 24));
        ErrorsFilterButton.setPreferredSize(new java.awt.Dimension(24, 24));
        ErrorsFilterButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuildLogToolbar.add(ErrorsFilterButton);

        ToolbarSeparator7.setAlignmentX(0.5F);
        ToolbarSeparator7.setAlignmentY(0.0F);
        BuildLogToolbar.add(ToolbarSeparator7);

        ClearBuildLogButton.setIcon(clearBuildLogIcon);
        ClearBuildLogButton.setToolTipText("Clear build log data");
        ClearBuildLogButton.setAlignmentX(0.5F);
        ClearBuildLogButton.setAlignmentY(0.0F);
        ClearBuildLogButton.setFocusable(false);
        ClearBuildLogButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ClearBuildLogButton.setMaximumSize(new java.awt.Dimension(24, 24));
        ClearBuildLogButton.setMinimumSize(new java.awt.Dimension(24, 24));
        ClearBuildLogButton.setPreferredSize(new java.awt.Dimension(24, 24));
        ClearBuildLogButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuildLogToolbar.add(ClearBuildLogButton);

        BuildLogScroller.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        BuildLogScroller.setAlignmentX(0.0F);
        BuildLogScroller.setAlignmentY(0.0F);

        BuildLogViewer.setEditable(false);
        BuildLogViewer.setAlignmentX(0.0F);
        BuildLogViewer.setAlignmentY(0.0F);
        BuildLogViewer.setFocusable(false);
        BuildLogViewer.setRequestFocusEnabled(false);
        BuildLogViewer.setVerifyInputWhenFocusTarget(false);
        BuildLogScroller.setViewportView(BuildLogViewer);

        javax.swing.GroupLayout BuildLogPanelLayout = new javax.swing.GroupLayout(BuildLogPanel);
        BuildLogPanel.setLayout(BuildLogPanelLayout);
        BuildLogPanelLayout.setHorizontalGroup(
            BuildLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuildLogPanelLayout.createSequentialGroup()
                .addComponent(BuildLogToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BuildLogScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE))
        );
        BuildLogPanelLayout.setVerticalGroup(
            BuildLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BuildLogToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BuildLogScroller)
        );

        OutputFrameTabs.addTab("Build log", null, BuildLogPanel, "Build log");

        TerminalScroller.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        TerminalScroller.setViewportView(TerminalViewer);

        javax.swing.GroupLayout TerminalPanelLayout = new javax.swing.GroupLayout(TerminalPanel);
        TerminalPanel.setLayout(TerminalPanelLayout);
        TerminalPanelLayout.setHorizontalGroup(
            TerminalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TerminalScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
        );
        TerminalPanelLayout.setVerticalGroup(
            TerminalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TerminalScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        OutputFrameTabs.addTab("Terminal", null, TerminalPanel, "Terminal");

        GitToolbar.setRollover(true);
        GitToolbar.setAlignmentX(0.0F);
        GitToolbar.setMaximumSize(new java.awt.Dimension(4, 26));
        GitToolbar.setMinimumSize(new java.awt.Dimension(4, 26));
        GitToolbar.setPreferredSize(new java.awt.Dimension(100, 26));

        GitCommitButton.setIcon(gitCommitIcon);
        GitCommitButton.setToolTipText("Commit changes");
        GitCommitButton.setFocusable(false);
        GitCommitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GitCommitButton.setMaximumSize(new java.awt.Dimension(24, 24));
        GitCommitButton.setMinimumSize(new java.awt.Dimension(24, 24));
        GitCommitButton.setPreferredSize(new java.awt.Dimension(24, 24));
        GitCommitButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GitToolbar.add(GitCommitButton);
        GitToolbar.add(ToolbarSeparator8);

        GitFetchButton.setIcon(gitFetchIcon);
        GitFetchButton.setToolTipText("Fetch from current branch");
        GitFetchButton.setFocusable(false);
        GitFetchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GitFetchButton.setMaximumSize(new java.awt.Dimension(24, 24));
        GitFetchButton.setMinimumSize(new java.awt.Dimension(24, 24));
        GitFetchButton.setPreferredSize(new java.awt.Dimension(24, 24));
        GitFetchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GitToolbar.add(GitFetchButton);

        GitPullButton.setIcon(gitPullIcon);
        GitPullButton.setToolTipText("Pull from current branch");
        GitPullButton.setFocusable(false);
        GitPullButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GitPullButton.setMaximumSize(new java.awt.Dimension(24, 24));
        GitPullButton.setMinimumSize(new java.awt.Dimension(24, 24));
        GitPullButton.setPreferredSize(new java.awt.Dimension(24, 24));
        GitPullButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GitToolbar.add(GitPullButton);

        GitCheckoutButton.setIcon(gitCheckoutIcon);
        GitCheckoutButton.setToolTipText("Checkout files");
        GitCheckoutButton.setFocusable(false);
        GitCheckoutButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GitCheckoutButton.setMaximumSize(new java.awt.Dimension(24, 24));
        GitCheckoutButton.setMinimumSize(new java.awt.Dimension(24, 24));
        GitCheckoutButton.setPreferredSize(new java.awt.Dimension(24, 24));
        GitCheckoutButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GitToolbar.add(GitCheckoutButton);
        GitToolbar.add(ToolbarSeparator9);

        GitPushButton.setIcon(gitPushIcon);
        GitPushButton.setToolTipText("Push to current branch");
        GitPushButton.setFocusable(false);
        GitPushButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GitPushButton.setMaximumSize(new java.awt.Dimension(24, 24));
        GitPushButton.setMinimumSize(new java.awt.Dimension(24, 24));
        GitPushButton.setPreferredSize(new java.awt.Dimension(24, 24));
        GitPushButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GitToolbar.add(GitPushButton);

        GitOutputViewer.setEditable(false);

        javax.swing.GroupLayout GitPanelLayout = new javax.swing.GroupLayout(GitPanel);
        GitPanel.setLayout(GitPanelLayout);
        GitPanelLayout.setHorizontalGroup(
            GitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GitToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
            .addComponent(GitOutputViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        GitPanelLayout.setVerticalGroup(
            GitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GitPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(GitToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(GitOutputViewer, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
        );

        OutputFrameTabs.addTab("Git", null, GitPanel, "Git repository control");

        javax.swing.GroupLayout OutputFrameLayout = new javax.swing.GroupLayout(OutputFrame.getContentPane());
        OutputFrame.getContentPane().setLayout(OutputFrameLayout);
        OutputFrameLayout.setHorizontalGroup(
            OutputFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OutputFrameTabs)
        );
        OutputFrameLayout.setVerticalGroup(
            OutputFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OutputFrameTabs)
        );

        OutputFrameTabs.getAccessibleContext().setAccessibleName("");

        EditorSplitPanel.setBottomComponent(OutputFrame);

        EditorMDIFrame.setAlignmentX(0.0F);
        EditorMDIFrame.setAlignmentY(0.0F);
        EditorMDIFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        EditorMDIFrame.setDesktopManager(null);

        javax.swing.GroupLayout EditorMDIFrameLayout = new javax.swing.GroupLayout(EditorMDIFrame);
        EditorMDIFrame.setLayout(EditorMDIFrameLayout);
        EditorMDIFrameLayout.setHorizontalGroup(
            EditorMDIFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 824, Short.MAX_VALUE)
        );
        EditorMDIFrameLayout.setVerticalGroup(
            EditorMDIFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
        );

        EditorSplitPanel.setTopComponent(EditorMDIFrame);

        FrameSplitPanel.setBottomComponent(EditorSplitPanel);

        getContentPane().add(FrameSplitPanel, java.awt.BorderLayout.CENTER);

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
        FileMenu.add(MenuSeparator1);

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
        FileMenu.add(MenuSeparator2);

        PrintFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        PrintFileItem.setIcon(printFileIcon);
        PrintFileItem.setText("Print");
        FileMenu.add(PrintFileItem);

        PrintSetupItem.setIcon(printSetupIcon);
        PrintSetupItem.setText("Print setup...");
        FileMenu.add(PrintSetupItem);
        FileMenu.add(MenuSeparator3);

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
        EditMenu.add(MenuSeparator4);

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
        EditMenu.add(MenuSeparator5);

        FindEditItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        FindEditItem.setIcon(findAndReplaceIcon);
        FindEditItem.setText("Find and replace...");
        EditMenu.add(FindEditItem);
        EditMenu.add(MenuSeparator6);

        BookmarksMenu.setText("Bookmarks");

        NewBookmarkItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        NewBookmarkItem.setIcon(newBookmarkIcon);
        NewBookmarkItem.setText("New");
        BookmarksMenu.add(NewBookmarkItem);
        BookmarksMenu.add(MenuSeparator8);

        PrevBookmarkItem.setIcon(prevBookmarkIcon);
        PrevBookmarkItem.setText("Preview");
        BookmarksMenu.add(PrevBookmarkItem);

        NextBookmarkItem.setIcon(nextBookmarkIcon);
        NextBookmarkItem.setText("Next");
        BookmarksMenu.add(NextBookmarkItem);

        EditMenu.add(BookmarksMenu);
        EditMenu.add(MenuSeparator7);

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
        ViewMenu.add(MenuSeparator9);

        GoToViewItem.setIcon(goToViewIcon);
        GoToViewItem.setText("Go to...");
        ViewMenu.add(GoToViewItem);
        ViewMenu.add(MenuSeparator10);

        ProjectExplorerItem.setSelected(true);
        ProjectExplorerItem.setText("Project explorer");
        ProjectExplorerItem.addActionListener(this::ProjectExplorerItemActionPerformed);
        ViewMenu.add(ProjectExplorerItem);

        OutputWindowItem.setSelected(true);
        OutputWindowItem.setText("Output window");
        OutputWindowItem.addActionListener(this::OutputWindowItemActionPerformed);
        ViewMenu.add(OutputWindowItem);

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
        InsertMenu.add(MenuSeparator11);

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
        BuildMenu.add(MenuSeparator12);

        ConfigBuildItem.setIcon(configBuildIcon);
        ConfigBuildItem.setText("Configure...");
        BuildMenu.add(ConfigBuildItem);

        MainMenubar.add(BuildMenu);

        ToolsMenu.setText("Tools");

        GitToolsItem.setIcon(gitToolsIcon);
        GitToolsItem.setText("Git");
        GitToolsItem.addActionListener(this::GitToolsItemActionPerformed);
        ToolsMenu.add(GitToolsItem);

        TerminalToolsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        TerminalToolsItem.setIcon(terminalToolsIcon);
        TerminalToolsItem.setText("Terminal");
        TerminalToolsItem.addActionListener(this::TerminalToolsItemActionPerformed);
        ToolsMenu.add(TerminalToolsItem);

        DesignerToolsItem.setIcon(uiDesignerToolsIcon);
        DesignerToolsItem.setText("UI designer");
        ToolsMenu.add(DesignerToolsItem);

        ResManagerToolsItem.setIcon(resourcesManagerIcon);
        ResManagerToolsItem.setText("Resources manager...");
        ToolsMenu.add(ResManagerToolsItem);

        MainMenubar.add(ToolsMenu);

        HelpMenu.setText("Help");

        ContentsHelpItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        ContentsHelpItem.setIcon(contentsHelpIcon);
        ContentsHelpItem.setText("Contents...");
        HelpMenu.add(ContentsHelpItem);

        SamplesHelpItem.setIcon(samplesHelpIcon);
        SamplesHelpItem.setText("Samples");
        HelpMenu.add(SamplesHelpItem);
        HelpMenu.add(MenuSeparator13);

        AboutHelpItem.setIcon(aboutHelpIcon);
        AboutHelpItem.setText("About...");
        AboutHelpItem.addActionListener(this::AboutHelpItemActionPerformed);
        HelpMenu.add(AboutHelpItem);

        MainMenubar.add(HelpMenu);

        setJMenuBar(MainMenubar);

        getAccessibleContext().setAccessibleName("MainFrame");
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
    private void AboutDialogOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutDialogOkButtonActionPerformed
        AboutDialogWindow.dispose();
    }//GEN-LAST:event_AboutDialogOkButtonActionPerformed
    
    // Close main window and exit from application function
    private void QuitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitItemActionPerformed
        this.dispose();
        System.exit(0); // Exist from application process
    }//GEN-LAST:event_QuitItemActionPerformed

    // Set focus on project explorer frame function 
    private void ProjectExplorerFrameInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_ProjectExplorerFrameInternalFrameActivated
        try {
            OutputFrame.setSelected(false); // Switch focus from output frame
        } catch (PropertyVetoException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_ProjectExplorerFrameInternalFrameActivated

    // Set focus on output frame function
    private void OutputFrameInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_OutputFrameInternalFrameActivated
        try {
            ProjectExplorerFrame.setSelected(false); // Switch focus from project explorer frame
        } catch (PropertyVetoException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_OutputFrameInternalFrameActivated

    // Switch project explorer frame view state function
    private void ProjectExplorerItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProjectExplorerItemActionPerformed
        if(ProjectExplorerItem.isSelected()) {
            FrameSplitPanel.setDividerLocation(ProjectExplorerFrame.getWidth()); // Set left side of split panel
            ProjectExplorerFrame.show();
        } else {
            ProjectExplorerFrame.hide();
        }
    }//GEN-LAST:event_ProjectExplorerItemActionPerformed

    // Same too function
    private void ProjectExplorerFrameComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_ProjectExplorerFrameComponentHidden
        ProjectExplorerItem.setSelected(false);
    }//GEN-LAST:event_ProjectExplorerFrameComponentHidden

    // Switch output frame view state function
    private void OutputWindowItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OutputWindowItemActionPerformed
        if(OutputWindowItem.isSelected()) {
            EditorSplitPanel.setDividerLocation(EditorMDIFrame.getHeight() - OutputFrame.getHeight());
            OutputFrame.show();
        } else {
            OutputFrame.hide();
        }
    }//GEN-LAST:event_OutputWindowItemActionPerformed

    // Same too function
    private void OutputFrameComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_OutputFrameComponentHidden
        OutputWindowItem.setSelected(false);
    }//GEN-LAST:event_OutputFrameComponentHidden

    // Switch output window to "Terminal" tab function
    private void TerminalToolsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminalToolsItemActionPerformed
        if(OutputFrame.isShowing()) {
            OutputFrameTabs.setSelectedComponent(TerminalPanel);
        } else {
            EditorSplitPanel.setDividerLocation(EditorMDIFrame.getHeight() - OutputFrame.getHeight());
            OutputWindowItem.setSelected(true);
            OutputFrameTabs.setSelectedComponent(TerminalPanel);
            OutputFrame.show();
        }
    }//GEN-LAST:event_TerminalToolsItemActionPerformed

    // Same too function
    private void TerminalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminalButtonActionPerformed
        if(OutputFrame.isShowing()) {
            OutputFrameTabs.setSelectedComponent(TerminalPanel);
        } else {
            EditorSplitPanel.setDividerLocation(EditorMDIFrame.getHeight() - OutputFrame.getHeight());
            OutputWindowItem.setSelected(true);
            OutputFrameTabs.setSelectedComponent(TerminalPanel);
            OutputFrame.show();
        }
    }//GEN-LAST:event_TerminalButtonActionPerformed

    // Switch output window to "Git" tab function
    private void GitToolsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GitToolsItemActionPerformed
        if(OutputFrame.isShowing()) {
            OutputFrameTabs.setSelectedComponent(GitPanel);
        } else {
            EditorSplitPanel.setDividerLocation(EditorMDIFrame.getHeight() - OutputFrame.getHeight());
            OutputWindowItem.setSelected(true);
            OutputFrameTabs.setSelectedComponent(GitPanel);
            OutputFrame.show();
        }
    }//GEN-LAST:event_GitToolsItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AboutDialogOkButton;
    private javax.swing.JDialog AboutDialogWindow;
    private javax.swing.JMenuItem AboutHelpItem;
    private javax.swing.JScrollPane AppDescriptionScroller;
    private javax.swing.JTextArea AppDescriptionText;
    private javax.swing.JPanel AppIndicationPanel;
    private javax.swing.JLabel AppStatusLabel;
    private javax.swing.JPanel AppStatusPanel;
    private javax.swing.JLabel AppTitleLabel;
    private javax.swing.JLabel AppVersionLabel;
    private javax.swing.JMenu BookmarksMenu;
    private javax.swing.JButton BuildDebugButton;
    private javax.swing.JPanel BuildLogPanel;
    private javax.swing.JScrollPane BuildLogScroller;
    private javax.swing.JToolBar BuildLogToolbar;
    private javax.swing.JEditorPane BuildLogViewer;
    private javax.swing.JMenu BuildMenu;
    private javax.swing.JButton BuildReleaseButton;
    private javax.swing.JToolBar BuildToolbar;
    private javax.swing.JLabel CapsStatusLabel;
    private javax.swing.JButton ClearBuildLogButton;
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
    private javax.swing.JDesktopPane EditorMDIFrame;
    private javax.swing.JSplitPane EditorSplitPanel;
    private javax.swing.JLabel EncodeStatusLabel;
    private javax.swing.JMenuItem EnumInsertItem;
    private javax.swing.JButton ErrorsFilterButton;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JTree FilesTreeList;
    private javax.swing.JScrollPane FilesTreeScroller;
    private javax.swing.JButton FindAndReplaceButton;
    private javax.swing.JMenuItem FindEditItem;
    private javax.swing.JSplitPane FrameSplitPanel;
    private javax.swing.JMenuItem FunctInsertItem;
    private javax.swing.JButton GitCheckoutButton;
    private javax.swing.JButton GitCommitButton;
    private javax.swing.JButton GitFetchButton;
    private java.awt.TextArea GitOutputViewer;
    private javax.swing.JPanel GitPanel;
    private javax.swing.JButton GitPullButton;
    private javax.swing.JButton GitPushButton;
    private javax.swing.JToolBar GitToolbar;
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
    private javax.swing.JPopupMenu.Separator MenuSeparator1;
    private javax.swing.JPopupMenu.Separator MenuSeparator10;
    private javax.swing.JPopupMenu.Separator MenuSeparator11;
    private javax.swing.JPopupMenu.Separator MenuSeparator12;
    private javax.swing.JPopupMenu.Separator MenuSeparator13;
    private javax.swing.JPopupMenu.Separator MenuSeparator2;
    private javax.swing.JPopupMenu.Separator MenuSeparator3;
    private javax.swing.JPopupMenu.Separator MenuSeparator4;
    private javax.swing.JPopupMenu.Separator MenuSeparator5;
    private javax.swing.JPopupMenu.Separator MenuSeparator6;
    private javax.swing.JPopupMenu.Separator MenuSeparator7;
    private javax.swing.JPopupMenu.Separator MenuSeparator8;
    private javax.swing.JPopupMenu.Separator MenuSeparator9;
    private javax.swing.JMenuItem NewBookmarkItem;
    private javax.swing.JButton NewFileButton;
    private javax.swing.JMenuItem NewFileItem;
    private javax.swing.JMenuItem NewProjectItem;
    private javax.swing.JMenuItem NextBookmarkItem;
    private javax.swing.JButton OpenFileButton;
    private javax.swing.JMenuItem OpenFileItem;
    private javax.swing.JMenuItem OpenProjectItem;
    private javax.swing.JInternalFrame OutputFrame;
    private javax.swing.JTabbedPane OutputFrameTabs;
    private javax.swing.JCheckBoxMenuItem OutputWindowItem;
    private javax.swing.JMenuItem PasteEditItem;
    private javax.swing.JMenuItem PrevBookmarkItem;
    private javax.swing.JMenuItem PrintFileItem;
    private javax.swing.JMenuItem PrintSetupItem;
    private javax.swing.JInternalFrame ProjectExplorerFrame;
    private javax.swing.JCheckBoxMenuItem ProjectExplorerItem;
    private javax.swing.JTabbedPane ProjectExplorerTabs;
    private javax.swing.JPanel ProjectFilesPanel;
    private javax.swing.JPanel ProjectStructurePanel;
    private javax.swing.JMenuItem QuitItem;
    private javax.swing.JButton RedoEditButton;
    private javax.swing.JMenuItem RedoEditItem;
    private javax.swing.JMenuItem ReleaseBuildItem;
    private javax.swing.JMenuItem ResManagerToolsItem;
    private javax.swing.JMenuItem SamplesHelpItem;
    private javax.swing.JMenuItem SaveAllItem;
    private javax.swing.JMenuItem SaveAsFileItem;
    private javax.swing.JButton SaveFileButton;
    private javax.swing.JMenuItem SaveFileItem;
    private javax.swing.JMenuItem SaveProjectItem;
    private javax.swing.JMenuItem SetDefViewItem;
    private javax.swing.JMenuItem SettingsItem;
    private javax.swing.JButton ShowAllMessageButton;
    private javax.swing.JPanel StatusbarPanel;
    private javax.swing.JMenuItem StructInsertItem;
    private javax.swing.JTree StructureTreeList;
    private javax.swing.JScrollPane StructureTreeScroller;
    private javax.swing.JMenuItem TemplateInsertItem;
    private javax.swing.JButton TerminalButton;
    private javax.swing.JPanel TerminalPanel;
    private javax.swing.JScrollPane TerminalScroller;
    private javax.swing.JMenuItem TerminalToolsItem;
    private javax.swing.JEditorPane TerminalViewer;
    private javax.swing.JToolBar.Separator ToolbarSeparator1;
    private javax.swing.JToolBar.Separator ToolbarSeparator2;
    private javax.swing.JToolBar.Separator ToolbarSeparator3;
    private javax.swing.JToolBar.Separator ToolbarSeparator4;
    private javax.swing.JToolBar.Separator ToolbarSeparator5;
    private javax.swing.JSeparator ToolbarSeparator6;
    private javax.swing.JToolBar.Separator ToolbarSeparator7;
    private javax.swing.JToolBar.Separator ToolbarSeparator8;
    private javax.swing.JToolBar.Separator ToolbarSeparator9;
    private javax.swing.JMenu ToolsMenu;
    private javax.swing.JButton UndoEditButton;
    private javax.swing.JMenuItem UndoEditItem;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JMenuItem ZoomInViewItem;
    private javax.swing.JMenuItem ZoomOutViewItem;
    // End of variables declaration//GEN-END:variables
}