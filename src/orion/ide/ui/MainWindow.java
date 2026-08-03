/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 *******************************************************************************
 * Application main window class
 *******************************************************************************
 * Controled all sub windows and panels
 *******************************************************************************
 */
package orion.ide.ui;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import orion.ide.core.SettingsManager;
import orion.ide.core.TreeListIconRenderer;
import orion.ide.core.TreeListModel;
import orion.ide.core.SourceFileFilter;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class MainWindow extends JFrame {
    
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */
    // Settings control object
    private final SettingsManager settings = new SettingsManager();
    
    // Set project structure tree list model
    private final TreeListModel structureTreeListModel;
    
    // Current editor theme style
    private static String editorThemeStyle;
    
    // New file name string
    private static String newFileFullName;
    
    // New file extension
    public static String newFileExtension;
    
    // Project file path string
    public static String projectFilePath = "";

    // Set FlatLaf SVG icons
    public static String iconsFolder = getIconsFolder(); // Icons folder by current theme type
    
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
    
    // Settings window icons
    public final FlatSVGIcon appearSettingsIcon = new FlatSVGIcon("resources/icons/commons/appearance_settings.svg", 24, 24);
    public final FlatSVGIcon gitSettingsIcon = new FlatSVGIcon("resources/icons/commons/git_settings.svg", 24, 24);
    public final FlatSVGIcon buildSettingsIcon = new FlatSVGIcon("resources/icons/commons/build_settings.svg", 24, 24);

    // File types icons
    public final FlatSVGIcon cHeaderFileTypeIcon = new FlatSVGIcon("resources/icons/commons/c_header_file.svg", 32, 32);
    public final FlatSVGIcon cSourceFileTypeIcon = new FlatSVGIcon("resources/icons/commons/c_source_file.svg", 32, 32);
    public final FlatSVGIcon cppClassFileTypeIcon = new FlatSVGIcon("resources/icons/commons/cpp_class_file.svg", 32, 32);
    public final FlatSVGIcon uiFormFileTypeIcon = new FlatSVGIcon("resources/icons/commons/form_design_file.svg", 32, 32);
    public final FlatSVGIcon iniFileTypeIcon = new FlatSVGIcon("resources/icons/commons/ini_file.svg", 32, 32);
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    // Constructor
    public MainWindow() {
        initComponents();
        this.structureTreeListModel = new TreeListModel(StructureTreeList, "");
        StructureTreeList.setCellRenderer(new TreeListIconRenderer());
        this.settings.init();
        
        // Set current editor theme style
        editorThemeStyle = settings.getParam("Appearance", "currentEditorStyle");
    }
    
    // Get icons folder name by current theme type : function
    private static String getIconsFolder() {
        String folder;
        
        if(!ThemeManager.getCurrentThemeType()) {
            folder = "light";
        } else {
            folder = "dark";
        }
        
        return folder;
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
        SettingsWindow = new javax.swing.JDialog();
        SettingsTabs = new javax.swing.JTabbedPane();
        AppearanceSettingsPanel = new javax.swing.JPanel();
        AppearanceSettingsLabel = new javax.swing.JLabel();
        WindowThemeListButton = new javax.swing.JComboBox<>();
        WindowThemeLabel = new javax.swing.JLabel();
        ThemeNotificationLabel = new javax.swing.JLabel();
        EditorStyleListButton = new javax.swing.JComboBox<>();
        EditorStyleLabel = new javax.swing.JLabel();
        EditorFontSizeSpinner = new javax.swing.JSpinner();
        EditorFontSizeLabel = new javax.swing.JLabel();
        GitSettingsPanel = new javax.swing.JPanel();
        GitSettingsLabel = new javax.swing.JLabel();
        GitLoginTextInput = new javax.swing.JTextField();
        GitLoginLabel = new javax.swing.JLabel();
        GitPasswordLabel = new javax.swing.JLabel();
        GitTokenLabel = new javax.swing.JLabel();
        GitTokenTextInput = new javax.swing.JTextField();
        GitPasswordTextInput = new javax.swing.JPasswordField();
        BuildSettingsPanel = new javax.swing.JPanel();
        BuildSettingsLabel = new javax.swing.JLabel();
        NeptuneSDKPathTextInput = new javax.swing.JTextField();
        NeptuneSDKPathLabel = new javax.swing.JLabel();
        NeptuneSDKPathButton = new javax.swing.JButton();
        MCORESDKPathLabel = new javax.swing.JLabel();
        MCORESDKPathTextInput = new javax.swing.JTextField();
        MCORESDKPathButton = new javax.swing.JButton();
        SaveSettingsButton = new javax.swing.JButton();
        CancelSettingsButton = new javax.swing.JButton();
        NewFileWindow = new javax.swing.JDialog();
        NewFileWindowTitleLabel = new javax.swing.JLabel();
        NewFileSetupPanel = new javax.swing.JPanel();
        NewFileNameTextInput = new javax.swing.JTextField();
        NewFileNameLabel = new javax.swing.JLabel();
        CHeaderFileTypeButton = new javax.swing.JButton();
        CSourceFileTypeButton = new javax.swing.JButton();
        CPPClassFileTypeButton = new javax.swing.JButton();
        FormDesignFileTypeButton = new javax.swing.JButton();
        INIConfigFileTypeButton = new javax.swing.JButton();
        CreateNewFileButton = new javax.swing.JButton();
        CloseNewFileWindowButton = new javax.swing.JButton();
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
        GitOutputScroller = new javax.swing.JScrollPane();
        GitOutputViewer = new javax.swing.JEditorPane();
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

        SettingsWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        SettingsWindow.setTitle("Settings");
        SettingsWindow.setMinimumSize(new java.awt.Dimension(800, 600));
        SettingsWindow.setModal(true);
        SettingsWindow.setName("SettingsWindow"); // NOI18N
        SettingsWindow.setResizable(false);
        SettingsWindow.setSize(new java.awt.Dimension(800, 600));

        AppearanceSettingsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        AppearanceSettingsLabel.setText("Appearance settings");

        WindowThemeListButton.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VS Light", "VS Dark" }));
        WindowThemeListButton.addItemListener(this::WindowThemeListButtonItemStateChanged);

        WindowThemeLabel.setLabelFor(WindowThemeListButton);
        WindowThemeLabel.setText("Window theme:");

        ThemeNotificationLabel.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        ThemeNotificationLabel.setText("Need to application restart");
        ThemeNotificationLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ThemeNotificationLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        EditorStyleListButton.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Light theme", "Dark theme", "Eclipse light", "IDEA light", "Visual Studio light", "Monokai dark", "Druid dark" }));

        EditorStyleLabel.setLabelFor(EditorStyleListButton);
        EditorStyleLabel.setText("Editor style:");

        EditorFontSizeLabel.setLabelFor(EditorFontSizeSpinner);
        EditorFontSizeLabel.setText("Editor font size:");

        javax.swing.GroupLayout AppearanceSettingsPanelLayout = new javax.swing.GroupLayout(AppearanceSettingsPanel);
        AppearanceSettingsPanel.setLayout(AppearanceSettingsPanelLayout);
        AppearanceSettingsPanelLayout.setHorizontalGroup(
            AppearanceSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AppearanceSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AppearanceSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AppearanceSettingsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(AppearanceSettingsPanelLayout.createSequentialGroup()
                        .addGroup(AppearanceSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AppearanceSettingsPanelLayout.createSequentialGroup()
                                .addComponent(WindowThemeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(WindowThemeListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AppearanceSettingsPanelLayout.createSequentialGroup()
                                .addGroup(AppearanceSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(EditorStyleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EditorFontSizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(AppearanceSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ThemeNotificationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(EditorStyleListButton, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EditorFontSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 488, Short.MAX_VALUE)))
                .addContainerGap())
        );
        AppearanceSettingsPanelLayout.setVerticalGroup(
            AppearanceSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AppearanceSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AppearanceSettingsLabel)
                .addGap(18, 18, 18)
                .addGroup(AppearanceSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WindowThemeListButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WindowThemeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ThemeNotificationLabel)
                .addGap(18, 18, 18)
                .addGroup(AppearanceSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditorStyleListButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditorStyleLabel))
                .addGap(18, 18, 18)
                .addGroup(AppearanceSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditorFontSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditorFontSizeLabel))
                .addContainerGap(290, Short.MAX_VALUE))
        );

        SettingsTabs.addTab("", appearSettingsIcon, AppearanceSettingsPanel, "Appearance settings");

        GitSettingsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        GitSettingsLabel.setText("Git configuration");

        GitLoginTextInput.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        GitLoginTextInput.setToolTipText("Git login");

        GitLoginLabel.setLabelFor(GitLoginTextInput);
        GitLoginLabel.setText("Git login:");

        GitPasswordLabel.setLabelFor(GitPasswordTextInput);
        GitPasswordLabel.setText("Git password:");

        GitTokenLabel.setLabelFor(GitLoginTextInput);
        GitTokenLabel.setText("Git token:");

        GitTokenTextInput.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        GitTokenTextInput.setToolTipText("Git token");

        GitPasswordTextInput.setToolTipText("Git password");

        javax.swing.GroupLayout GitSettingsPanelLayout = new javax.swing.GroupLayout(GitSettingsPanel);
        GitSettingsPanel.setLayout(GitSettingsPanelLayout);
        GitSettingsPanelLayout.setHorizontalGroup(
            GitSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GitSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GitSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GitSettingsPanelLayout.createSequentialGroup()
                        .addComponent(GitSettingsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(GitSettingsPanelLayout.createSequentialGroup()
                        .addGroup(GitSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GitSettingsPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(GitLoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(GitLoginTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(GitSettingsPanelLayout.createSequentialGroup()
                                .addComponent(GitPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(GitPasswordTextInput))
                            .addGroup(GitSettingsPanelLayout.createSequentialGroup()
                                .addComponent(GitTokenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(GitTokenTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34))))
        );
        GitSettingsPanelLayout.setVerticalGroup(
            GitSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GitSettingsPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(GitSettingsLabel)
                .addGap(18, 18, 18)
                .addGroup(GitSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GitLoginTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GitLoginLabel))
                .addGap(18, 18, 18)
                .addGroup(GitSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GitPasswordLabel)
                    .addComponent(GitPasswordTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(GitSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GitTokenTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GitTokenLabel))
                .addContainerGap(312, Short.MAX_VALUE))
        );

        SettingsTabs.addTab("", gitSettingsIcon, GitSettingsPanel, "Git configuration");

        BuildSettingsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BuildSettingsLabel.setText("Build configuration");

        NeptuneSDKPathLabel.setText("Neptune LTE SDK path:");

        NeptuneSDKPathButton.setText("...");
        NeptuneSDKPathButton.addActionListener(this::NeptuneSDKPathButtonActionPerformed);

        MCORESDKPathLabel.setText("M-CORE SDK path:");

        MCORESDKPathButton.setText("...");
        MCORESDKPathButton.addActionListener(this::MCORESDKPathButtonActionPerformed);

        javax.swing.GroupLayout BuildSettingsPanelLayout = new javax.swing.GroupLayout(BuildSettingsPanel);
        BuildSettingsPanel.setLayout(BuildSettingsPanelLayout);
        BuildSettingsPanelLayout.setHorizontalGroup(
            BuildSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuildSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BuildSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BuildSettingsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BuildSettingsPanelLayout.createSequentialGroup()
                        .addGroup(BuildSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BuildSettingsPanelLayout.createSequentialGroup()
                                .addComponent(NeptuneSDKPathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NeptuneSDKPathTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NeptuneSDKPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(BuildSettingsPanelLayout.createSequentialGroup()
                                .addComponent(MCORESDKPathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(MCORESDKPathTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MCORESDKPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 96, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BuildSettingsPanelLayout.setVerticalGroup(
            BuildSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuildSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BuildSettingsLabel)
                .addGap(18, 18, 18)
                .addGroup(BuildSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NeptuneSDKPathTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NeptuneSDKPathLabel)
                    .addComponent(NeptuneSDKPathButton))
                .addGap(18, 18, 18)
                .addGroup(BuildSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MCORESDKPathTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MCORESDKPathLabel)
                    .addComponent(MCORESDKPathButton))
                .addContainerGap(352, Short.MAX_VALUE))
        );

        SettingsTabs.addTab("", buildSettingsIcon, BuildSettingsPanel, "Build configuration");

        SaveSettingsButton.setText("Save");
        SaveSettingsButton.addActionListener(this::SaveSettingsButtonActionPerformed);

        CancelSettingsButton.setText("Cancel");
        CancelSettingsButton.addActionListener(this::CancelSettingsButtonActionPerformed);

        javax.swing.GroupLayout SettingsWindowLayout = new javax.swing.GroupLayout(SettingsWindow.getContentPane());
        SettingsWindow.getContentPane().setLayout(SettingsWindowLayout);
        SettingsWindowLayout.setHorizontalGroup(
            SettingsWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SettingsTabs)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SettingsWindowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CancelSettingsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SaveSettingsButton)
                .addGap(26, 26, 26))
        );
        SettingsWindowLayout.setVerticalGroup(
            SettingsWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsWindowLayout.createSequentialGroup()
                .addComponent(SettingsTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(SettingsWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveSettingsButton)
                    .addComponent(CancelSettingsButton))
                .addContainerGap())
        );

        NewFileWindow.setTitle("Create new file");
        NewFileWindow.setMinimumSize(new java.awt.Dimension(364, 400));
        NewFileWindow.setModal(true);
        NewFileWindow.setName("NewFileWindow"); // NOI18N
        NewFileWindow.setResizable(false);
        NewFileWindow.setSize(new java.awt.Dimension(364, 400));
        NewFileWindow.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                NewFileWindowComponentShown(evt);
            }
        });

        NewFileWindowTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NewFileWindowTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NewFileWindowTitleLabel.setText(" Choose file type:");
        NewFileWindowTitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        NewFileWindow.getContentPane().add(NewFileWindowTitleLabel, java.awt.BorderLayout.PAGE_START);

        NewFileNameLabel.setLabelFor(NewFileNameTextInput);
        NewFileNameLabel.setText("Enter file name:");

        CHeaderFileTypeButton.setIcon(cHeaderFileTypeIcon);
        CHeaderFileTypeButton.setText("C/C++ header");
        CHeaderFileTypeButton.setToolTipText("C/C++ header file");
        CHeaderFileTypeButton.setBorder(null);
        CHeaderFileTypeButton.setFocusCycleRoot(true);
        CHeaderFileTypeButton.setFocusTraversalPolicyProvider(true);
        CHeaderFileTypeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CHeaderFileTypeButton.setMaximumSize(new java.awt.Dimension(100, 100));
        CHeaderFileTypeButton.setMinimumSize(new java.awt.Dimension(100, 100));
        CHeaderFileTypeButton.setPreferredSize(new java.awt.Dimension(100, 100));
        CHeaderFileTypeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CHeaderFileTypeButton.addActionListener(this::CHeaderFileTypeButtonActionPerformed);

        CSourceFileTypeButton.setIcon(cSourceFileTypeIcon);
        CSourceFileTypeButton.setText("C source");
        CSourceFileTypeButton.setToolTipText("C source file");
        CSourceFileTypeButton.setBorder(null);
        CSourceFileTypeButton.setFocusCycleRoot(true);
        CSourceFileTypeButton.setFocusTraversalPolicyProvider(true);
        CSourceFileTypeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CSourceFileTypeButton.setMaximumSize(new java.awt.Dimension(100, 100));
        CSourceFileTypeButton.setMinimumSize(new java.awt.Dimension(100, 100));
        CSourceFileTypeButton.setPreferredSize(new java.awt.Dimension(100, 100));
        CSourceFileTypeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CSourceFileTypeButton.addActionListener(this::CSourceFileTypeButtonActionPerformed);

        CPPClassFileTypeButton.setIcon(cppClassFileTypeIcon);
        CPPClassFileTypeButton.setText("C++ class");
        CPPClassFileTypeButton.setToolTipText("C++ class file");
        CPPClassFileTypeButton.setBorder(null);
        CPPClassFileTypeButton.setFocusCycleRoot(true);
        CPPClassFileTypeButton.setFocusTraversalPolicyProvider(true);
        CPPClassFileTypeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CPPClassFileTypeButton.setMaximumSize(new java.awt.Dimension(100, 100));
        CPPClassFileTypeButton.setMinimumSize(new java.awt.Dimension(100, 100));
        CPPClassFileTypeButton.setPreferredSize(new java.awt.Dimension(100, 100));
        CPPClassFileTypeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CPPClassFileTypeButton.addActionListener(this::CPPClassFileTypeButtonActionPerformed);

        FormDesignFileTypeButton.setIcon(uiFormFileTypeIcon);
        FormDesignFileTypeButton.setText("Form design");
        FormDesignFileTypeButton.setToolTipText("Form design file");
        FormDesignFileTypeButton.setBorder(null);
        FormDesignFileTypeButton.setEnabled(false);
        FormDesignFileTypeButton.setFocusCycleRoot(true);
        FormDesignFileTypeButton.setFocusTraversalPolicyProvider(true);
        FormDesignFileTypeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FormDesignFileTypeButton.setMaximumSize(new java.awt.Dimension(100, 100));
        FormDesignFileTypeButton.setMinimumSize(new java.awt.Dimension(100, 100));
        FormDesignFileTypeButton.setPreferredSize(new java.awt.Dimension(100, 100));
        FormDesignFileTypeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FormDesignFileTypeButton.addActionListener(this::FormDesignFileTypeButtonActionPerformed);

        INIConfigFileTypeButton.setIcon(iniFileTypeIcon);
        INIConfigFileTypeButton.setText("INI config");
        INIConfigFileTypeButton.setToolTipText("INI config file");
        INIConfigFileTypeButton.setBorder(null);
        INIConfigFileTypeButton.setFocusCycleRoot(true);
        INIConfigFileTypeButton.setFocusTraversalPolicyProvider(true);
        INIConfigFileTypeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        INIConfigFileTypeButton.setMaximumSize(new java.awt.Dimension(100, 100));
        INIConfigFileTypeButton.setMinimumSize(new java.awt.Dimension(100, 100));
        INIConfigFileTypeButton.setPreferredSize(new java.awt.Dimension(100, 100));
        INIConfigFileTypeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        INIConfigFileTypeButton.addActionListener(this::INIConfigFileTypeButtonActionPerformed);

        CreateNewFileButton.setText("Create");
        CreateNewFileButton.addActionListener(this::CreateNewFileButtonActionPerformed);

        CloseNewFileWindowButton.setText("Cancel");
        CloseNewFileWindowButton.addActionListener(this::CloseNewFileWindowButtonActionPerformed);

        javax.swing.GroupLayout NewFileSetupPanelLayout = new javax.swing.GroupLayout(NewFileSetupPanel);
        NewFileSetupPanel.setLayout(NewFileSetupPanelLayout);
        NewFileSetupPanelLayout.setHorizontalGroup(
            NewFileSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewFileSetupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NewFileSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NewFileNameTextInput)
                    .addComponent(NewFileNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addGroup(NewFileSetupPanelLayout.createSequentialGroup()
                        .addGroup(NewFileSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NewFileSetupPanelLayout.createSequentialGroup()
                                .addComponent(CHeaderFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CSourceFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CPPClassFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(NewFileSetupPanelLayout.createSequentialGroup()
                                .addComponent(FormDesignFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(INIConfigFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewFileSetupPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(CloseNewFileWindowButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CreateNewFileButton)))
                .addContainerGap())
        );
        NewFileSetupPanelLayout.setVerticalGroup(
            NewFileSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewFileSetupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NewFileNameLabel)
                .addGap(2, 2, 2)
                .addComponent(NewFileNameTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(NewFileSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CHeaderFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CSourceFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CPPClassFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(NewFileSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FormDesignFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(INIConfigFileTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(NewFileSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreateNewFileButton)
                    .addComponent(CloseNewFileWindowButton))
                .addContainerGap())
        );

        NewFileWindow.getContentPane().add(NewFileSetupPanel, java.awt.BorderLayout.CENTER);

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
        NewFileButton.addActionListener(this::NewFileButtonActionPerformed);
        CommonToolbar.add(NewFileButton);

        OpenFileButton.setIcon(openFileIcon);
        OpenFileButton.setToolTipText("Open file");
        OpenFileButton.setFocusable(false);
        OpenFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        OpenFileButton.setMaximumSize(new java.awt.Dimension(24, 24));
        OpenFileButton.setMinimumSize(new java.awt.Dimension(24, 24));
        OpenFileButton.setPreferredSize(new java.awt.Dimension(24, 24));
        OpenFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OpenFileButton.addActionListener(this::OpenFileButtonActionPerformed);
        CommonToolbar.add(OpenFileButton);

        SaveFileButton.setIcon(saveFileIcon);
        SaveFileButton.setToolTipText("Save file");
        SaveFileButton.setFocusable(false);
        SaveFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SaveFileButton.setMaximumSize(new java.awt.Dimension(24, 24));
        SaveFileButton.setMinimumSize(new java.awt.Dimension(24, 24));
        SaveFileButton.setPreferredSize(new java.awt.Dimension(24, 24));
        SaveFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SaveFileButton.addActionListener(this::SaveFileButtonActionPerformed);
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

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Empty");
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
        GitOutputScroller.setViewportView(GitOutputViewer);

        javax.swing.GroupLayout GitPanelLayout = new javax.swing.GroupLayout(GitPanel);
        GitPanel.setLayout(GitPanelLayout);
        GitPanelLayout.setHorizontalGroup(
            GitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GitToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
            .addComponent(GitOutputScroller)
        );
        GitPanelLayout.setVerticalGroup(
            GitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GitPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(GitToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GitOutputScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
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
            .addGap(0, 819, Short.MAX_VALUE)
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
        NewFileItem.addActionListener(this::NewFileItemActionPerformed);
        FileMenu.add(NewFileItem);

        OpenFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        OpenFileItem.setIcon(openFileIcon);
        OpenFileItem.setText("Open...");
        OpenFileItem.addActionListener(this::OpenFileItemActionPerformed);
        FileMenu.add(OpenFileItem);

        SaveFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SaveFileItem.setIcon(saveFileIcon);
        SaveFileItem.setText("Save");
        SaveFileItem.addActionListener(this::SaveFileItemActionPerformed);
        FileMenu.add(SaveFileItem);

        SaveAsFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SaveAsFileItem.setIcon(saveAsIcon);
        SaveAsFileItem.setText("Save as...");
        SaveAsFileItem.addActionListener(this::SaveAsFileItemActionPerformed);
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
        SaveAllItem.addActionListener(this::SaveAllItemActionPerformed);
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
        SettingsItem.addActionListener(this::SettingsItemActionPerformed);
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
    
    // Show about application dialog window by main menu item click : event
    private void AboutHelpItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutHelpItemActionPerformed
        showAboutDialogWindow();
    }//GEN-LAST:event_AboutHelpItemActionPerformed

    // Show about application dialog window : function
    private void showAboutDialogWindow() {
        AboutDialogWindow.setSize(455, 250); // Set window size
        AboutDialogWindow.setLocationRelativeTo(null); // Set window position to center of screen
        AboutDialogWindow.setVisible(true);
    }
    
    // Close about application dialog window by "OK" button click : event
    private void AboutDialogOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutDialogOkButtonActionPerformed
        AboutDialogWindow.dispose();
    }//GEN-LAST:event_AboutDialogOkButtonActionPerformed
    
    // Close main window and exit from application : event
    private void QuitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitItemActionPerformed
        this.dispose();
        System.exit(0); // Exist from application process
    }//GEN-LAST:event_QuitItemActionPerformed

    // Set focus on project explorer frame : event 
    private void ProjectExplorerFrameInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_ProjectExplorerFrameInternalFrameActivated
        try {
            OutputFrame.setSelected(false); // Switch focus from output frame
        } catch (PropertyVetoException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_ProjectExplorerFrameInternalFrameActivated

    // Set focus on output frame : event
    private void OutputFrameInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_OutputFrameInternalFrameActivated
        try {
            ProjectExplorerFrame.setSelected(false); // Switch focus from project explorer frame
        } catch (PropertyVetoException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_OutputFrameInternalFrameActivated

    // Switch project explorer frame view state : event
    private void ProjectExplorerItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProjectExplorerItemActionPerformed
        if(ProjectExplorerItem.isSelected()) {
            FrameSplitPanel.setDividerLocation(ProjectExplorerFrame.getWidth()); // Set left side of split panel
            ProjectExplorerFrame.show();
        } else {
            ProjectExplorerFrame.hide();
        }
    }//GEN-LAST:event_ProjectExplorerItemActionPerformed

    private void ProjectExplorerFrameComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_ProjectExplorerFrameComponentHidden
        ProjectExplorerItem.setSelected(false);
    }//GEN-LAST:event_ProjectExplorerFrameComponentHidden

    // Switch output frame view state : event
    private void OutputWindowItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OutputWindowItemActionPerformed
        if(OutputWindowItem.isSelected()) {
            EditorSplitPanel.setDividerLocation(EditorMDIFrame.getHeight() - OutputFrame.getHeight());
            OutputFrame.show();
        } else {
            OutputFrame.hide();
        }
    }//GEN-LAST:event_OutputWindowItemActionPerformed

    private void OutputFrameComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_OutputFrameComponentHidden
        OutputWindowItem.setSelected(false);
    }//GEN-LAST:event_OutputFrameComponentHidden

    // Switch output window to "Terminal" tab by main menu item click : event
    private void TerminalToolsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminalToolsItemActionPerformed
        showTerminalTab();
    }//GEN-LAST:event_TerminalToolsItemActionPerformed

    // Switch output window to "Terminal" tab by toolbar button click : event
    private void TerminalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminalButtonActionPerformed
        showTerminalTab();
    }//GEN-LAST:event_TerminalButtonActionPerformed

    // Switch output window to "Terminal" tab : function
    private void showTerminalTab() {
        if(OutputFrame.isShowing()) {
            OutputFrameTabs.setSelectedComponent(TerminalPanel);
        } else {
            EditorSplitPanel.setDividerLocation(EditorMDIFrame.getHeight() - OutputFrame.getHeight());
            OutputWindowItem.setSelected(true);
            OutputFrameTabs.setSelectedComponent(TerminalPanel);
            OutputFrame.show();
        }
    }
    
    // Switch output window to "Git" tab by main menu item click : event
    private void GitToolsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GitToolsItemActionPerformed
        showGitTab();
    }//GEN-LAST:event_GitToolsItemActionPerformed

    // Switch output window to "Git" tab : function
    private void showGitTab() {
        if(OutputFrame.isShowing()) {
            OutputFrameTabs.setSelectedComponent(GitPanel);
        } else {
            EditorSplitPanel.setDividerLocation(EditorMDIFrame.getHeight() - OutputFrame.getHeight());
            OutputWindowItem.setSelected(true);
            OutputFrameTabs.setSelectedComponent(GitPanel);
            OutputFrame.show();
        }
    }

    // Show settings window by main menu item click : event
    private void SettingsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsItemActionPerformed
        showSettingsWindow();
    }//GEN-LAST:event_SettingsItemActionPerformed

    // Show settings window : function
    private void showSettingsWindow() {
        
        // Read settings params
        readSettingsParams();
        
        SettingsWindow.setLocationRelativeTo(null);
        SettingsWindow.setVisible(true);
    }
    
    // Read settings window UI controls params from file : function
    private void readSettingsParams() {
        WindowThemeListButton.setSelectedIndex(Integer.parseInt(settings.getParam("Appearance", "currentTheme")));
        EditorStyleListButton.setSelectedIndex(Integer.parseInt(settings.getParam("Appearance", "currentEditorStyle")));
        EditorFontSizeSpinner.setValue(Integer.valueOf(settings.getParam("Appearance", "currentFontSize")));
        
        GitLoginTextInput.setText(settings.getParam("Git", "gitLogin"));
        GitPasswordTextInput.setText(settings.getParam("Git", "gitPassword"));
        GitTokenTextInput.setText(settings.getParam("Git", "gitToken"));
        
        NeptuneSDKPathTextInput.setText(settings.getParam("Build", "neptuneSDKPath"));
        MCORESDKPathTextInput.setText(settings.getParam("Build", "m-coreSDKPath"));
    }
    
    // Save and close settings window by "Save" button click : event
    private void SaveSettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveSettingsButtonActionPerformed
        
        // Save settings params
        saveSettingsParams();
        
        // Set current editor theme style
        editorThemeStyle = settings.getParam("Appearance", "currentEditorStyle");
        
        // Update editor theme style for all opened MDI windows
        SwingUtilities.invokeLater(() -> {
            for(JInternalFrame editorWindows : EditorMDIFrame.getAllFrames()) {
                
                // Get code editor panel component
                CodeEditorPanel editorPanel = (CodeEditorPanel) editorWindows.getContentPane().getComponent(0);
                
                // Update editor theme style
                editorPanel.updateEditorTheme(editorThemeStyle);
            }
        });
        
        // Close settings window
        SettingsWindow.dispose();
    }//GEN-LAST:event_SaveSettingsButtonActionPerformed

    // Save settings params to file : function
    private void saveSettingsParams() {
        settings.storeParam("Appearance", "currentTheme", Integer.toString(WindowThemeListButton.getSelectedIndex()));
        settings.storeParam("Appearance", "currentEditorStyle", Integer.toString(EditorStyleListButton.getSelectedIndex()));
        settings.storeParam("Appearance", "currentFontSize", Integer.toString((int) EditorFontSizeSpinner.getValue()));
        
        settings.storeParam("Git", "gitLogin", GitLoginTextInput.getText());
        settings.storeParam("Git", "gitPassword", String.valueOf(GitPasswordTextInput.getPassword()));
        settings.storeParam("Git", "gitToken", GitTokenTextInput.getText());
        
        settings.storeParam("Build", "neptuneSDKPath", NeptuneSDKPathTextInput.getText());
        settings.storeParam("Build", "m-coreSDKPath", MCORESDKPathTextInput.getText());
    }
    
    // Close settings window without saving configuration by "Cancel" button click : event
    private void CancelSettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelSettingsButtonActionPerformed
        SettingsWindow.dispose();
    }//GEN-LAST:event_CancelSettingsButtonActionPerformed

    // Show Neptune LTE SDK folder change dialog window : event
    private void NeptuneSDKPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NeptuneSDKPathButtonActionPerformed
        javax.swing.JFileChooser selectFolderDialogWindow = new javax.swing.JFileChooser();
        selectFolderDialogWindow.setDialogTitle("Choose SDK folder");
        selectFolderDialogWindow.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        selectFolderDialogWindow.setAcceptAllFileFilterUsed(false);
        
        int openedResult = selectFolderDialogWindow.showOpenDialog(null);
        
        // Set SDK folder path
        if(openedResult == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File resultFolderPath = selectFolderDialogWindow.getSelectedFile();
            NeptuneSDKPathTextInput.setText(resultFolderPath.getAbsolutePath());
        }
    }//GEN-LAST:event_NeptuneSDKPathButtonActionPerformed

    // Show M-CORE SDK folder change dialog window : event
    private void MCORESDKPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCORESDKPathButtonActionPerformed
        javax.swing.JFileChooser selectFolderDialogWindow = new javax.swing.JFileChooser();
        selectFolderDialogWindow.setDialogTitle("Choose SDK folder");
        selectFolderDialogWindow.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        selectFolderDialogWindow.setAcceptAllFileFilterUsed(false);
        
        int openedResult = selectFolderDialogWindow.showOpenDialog(null);
        
        // Set SDK folder path
        if(openedResult == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File resultFolderPath = selectFolderDialogWindow.getSelectedFile();
            MCORESDKPathTextInput.setText(resultFolderPath.getAbsolutePath());
        }
    }//GEN-LAST:event_MCORESDKPathButtonActionPerformed

    // Show new file window by main menu item click : event
    private void NewFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewFileItemActionPerformed
        showNewFileWindow();
    }//GEN-LAST:event_NewFileItemActionPerformed

    // Show new file window by toolbar button click : event
    private void NewFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewFileButtonActionPerformed
        showNewFileWindow();
    }//GEN-LAST:event_NewFileButtonActionPerformed

    // Show new file window : function
    private void showNewFileWindow() {
        newFileFullName = "";
        newFileExtension = "";
        
        NewFileWindow.setLocationRelativeTo(null);
        NewFileWindow.setVisible(true);
    }

    // When new file window is shown, set file name input to empty : event
    private void NewFileWindowComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_NewFileWindowComponentShown
        NewFileNameTextInput.setText("");
        
        // Set empty tooltips
        if(!"".equals(NewFileNameTextInput.getText()) && !"".equals(newFileExtension)) {
            NewFileNameTextInput.setToolTipText("");
            CreateNewFileButton.setToolTipText("");
        }
    }//GEN-LAST:event_NewFileWindowComponentShown

    // Set .h file extension by button click : event
    private void CHeaderFileTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHeaderFileTypeButtonActionPerformed
        newFileExtension = ".h";
        
        // Set empty tooltip for create new file button
        CreateNewFileButton.setToolTipText("");
    }//GEN-LAST:event_CHeaderFileTypeButtonActionPerformed

    // Set .c file extension by button click : event
    private void CSourceFileTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CSourceFileTypeButtonActionPerformed
        newFileExtension = ".c";
        
        // Set empty tooltip for create new file button
        CreateNewFileButton.setToolTipText("");
    }//GEN-LAST:event_CSourceFileTypeButtonActionPerformed

    // Set .cpp file extension by button click : event
    private void CPPClassFileTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPPClassFileTypeButtonActionPerformed
        newFileExtension = ".cpp";
        
        // Set empty tooltip for create new file button
        CreateNewFileButton.setToolTipText("");
    }//GEN-LAST:event_CPPClassFileTypeButtonActionPerformed

    // Set .ui file extension by button click : event
    private void FormDesignFileTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormDesignFileTypeButtonActionPerformed
        newFileExtension = ".ui";
        
        // Set empty tooltip for create new file button
        CreateNewFileButton.setToolTipText("");
    }//GEN-LAST:event_FormDesignFileTypeButtonActionPerformed

    // Set .ini file extension by button click : event
    private void INIConfigFileTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INIConfigFileTypeButtonActionPerformed
        newFileExtension = ".ini";
        
        // Set empty tooltip for create new file button
        CreateNewFileButton.setToolTipText("");
    }//GEN-LAST:event_INIConfigFileTypeButtonActionPerformed

    // Create new file by "Create" button click : event
    private void CreateNewFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateNewFileButtonActionPerformed

        // Set new file name
        newFileFullName = NewFileNameTextInput.getText() + newFileExtension;
        
        if(!"".equals(NewFileNameTextInput.getText()) && !"".equals(newFileExtension)) {
            
            // Close new file window
            NewFileWindow.dispose();
        
            // Create new code editor MDI window
            if(".h".equals(newFileExtension) || ".c".equals(newFileExtension)
                || ".cpp".equals(newFileExtension) || ".ini".equals(newFileExtension)) {
                
                // Create new editor MDI window
                JInternalFrame editorWindow = new JInternalFrame(newFileFullName, true, true, true, true);
                
                CodeEditorPanel editorPanel = new CodeEditorPanel();
                editorPanel.setEditorSourceText("");
                editorPanel.updateTextBuffer();
                editorPanel.updateEditorTheme(editorThemeStyle);
                editorPanel.setEditorSyntaxStyle();
                
                editorWindow.setSize(600, 400);
                editorWindow.add(editorPanel);
                editorWindow.setVisible(true);
                editorWindow.putClientProperty("file", null);
        
                EditorMDIFrame.add(editorWindow);
        
                editorWindow.toFront();
                
                try {
                    editorWindow.setSelected(true);
                } catch (PropertyVetoException ex) {
                    System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        } else {
            if("".equals(NewFileNameTextInput.getText())) {
                
                // Set empty tooltip for create new file button
                CreateNewFileButton.setToolTipText("");
                
                // Show tooltip about empty file name text input
                NewFileNameTextInput.setToolTipText("File name is not be empty!");
                
                Timer timer = new Timer(300, event -> showTooltip(NewFileNameTextInput));
                timer.setRepeats(false);
                timer.start();
            } else if("".equals(newFileExtension)) {
                
                // Set empty tooltip for new file name text input
                NewFileNameTextInput.setToolTipText("");
                
                // Show tooltip about change file type
                CreateNewFileButton.setToolTipText("File type not changed!");
                
                Timer timer = new Timer(300, event -> showTooltip(CreateNewFileButton));
                timer.setRepeats(false);
                timer.start();
            }
        }
    }//GEN-LAST:event_CreateNewFileButtonActionPerformed
    
    // Show tooltip by event handler : function
    private static void showTooltip(JComponent component) {
        ToolTipManager tooltipManager = ToolTipManager.sharedInstance();
        
        Point point = new Point(component.getWidth() / 2, component.getHeight() / 2);
        
        MouseEvent event = new MouseEvent(component, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, point.x, point.y, 0, false);
        
        tooltipManager.mouseMoved(event);
    }
    
    // Save file by main menu item click : event
    private void SaveFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveFileItemActionPerformed
        try {
            saveTextFile();
        } catch (IOException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_SaveFileItemActionPerformed

    // Save file by toolbar button click : event
    private void SaveFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveFileButtonActionPerformed
        try {
            saveTextFile();
        } catch (IOException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_SaveFileButtonActionPerformed

    // Save as file by main menu item click : event
    private void SaveAsFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAsFileItemActionPerformed
        
        // Get current MDI window in focus
        JInternalFrame currentActiveWindow = EditorMDIFrame.getSelectedFrame();
        
        try {
            saveAsTextFile(currentActiveWindow);
        } catch (IOException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_SaveAsFileItemActionPerformed

    // Open file by main menu item click : event
    private void OpenFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenFileItemActionPerformed
        try {
            openTextFile();
        } catch (IOException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_OpenFileItemActionPerformed

    // Open file by toolbar button click : event
    private void OpenFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenFileButtonActionPerformed
        try {
            openTextFile();
        } catch (IOException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_OpenFileButtonActionPerformed

    // Close new file window by "Cancel" button click : event
    private void CloseNewFileWindowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseNewFileWindowButtonActionPerformed
        NewFileWindow.dispose();
    }//GEN-LAST:event_CloseNewFileWindowButtonActionPerformed

    // Save all files by main menu item click : event
    private void SaveAllItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAllItemActionPerformed
        
        // Using EDT for treads savety
        SwingUtilities.invokeLater(() -> {
            saveAllFiles();
        });
    }//GEN-LAST:event_SaveAllItemActionPerformed

    // Set editor dark theme if application dark theme changed : event
    private void WindowThemeListButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_WindowThemeListButtonItemStateChanged
        if(WindowThemeListButton.getSelectedIndex() == 0)
            EditorStyleListButton.setSelectedIndex(0);
        else if(WindowThemeListButton.getSelectedIndex() == 1)
            EditorStyleListButton.setSelectedIndex(1);
    }//GEN-LAST:event_WindowThemeListButtonItemStateChanged
   
    // Save current source code file : function
    private boolean saveTextFile() throws IOException {
        
        // Get current MDI window in focus
        JInternalFrame currentActiveWindow = EditorMDIFrame.getSelectedFrame();
        
        if(currentActiveWindow == null)
            return false;
        
        // Get file path from current MDI window property
        File currentFile = (File) currentActiveWindow.getClientProperty("file");
        
        // Check file to saved satatus, new file call save as function
        if(currentFile == null) {
            saveAsTextFile(currentActiveWindow);
            return true;
        }
        
        // Get code editor panel component
        CodeEditorPanel editorPanel = (CodeEditorPanel) currentActiveWindow.getContentPane().getComponent(0);
        
        // Get source code text from code editor panel component
        String sourceText = editorPanel.getEditorSourceText();
        
        // Save source code text to file
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(currentFile))) {
            fileWriter.write(sourceText);
            
            currentActiveWindow.putClientProperty("file", currentFile);
            currentActiveWindow.setTitle(currentFile.getName());
            
            editorPanel.updateTextBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return true;
    }
    
    // Save as for source code file : function
    private boolean saveAsTextFile(JInternalFrame window) throws IOException {
        
        // Get code editor panel component
        CodeEditorPanel editorPanel = (CodeEditorPanel) window.getContentPane().getComponent(0);
        
        // Get source code text from code editor panel component
        String sourceText = editorPanel.getEditorSourceText();
        
        // Set current file name
        String fileName;
        
        if(window.getTitle().endsWith("*")) {
            fileName = window.getTitle().substring(0, window.getTitle().length() - 1);
        } else {
            fileName = window.getTitle();
        }
        
        // Set source files extensions filter
        SourceFileFilter fileFilter = new SourceFileFilter("Source files: (*.h, *.c, *.cpp, *.ui, *.ini)",
                                                           new String[] {"h", "c", "cpp", "ui", "ini"});
        
        // Create save as dialog window
        JFileChooser fileChooserWindow = new JFileChooser();
        fileChooserWindow.setDialogTitle("Save as");
        fileChooserWindow.setSelectedFile(new File(fileName));
        fileChooserWindow.setFileFilter(fileFilter);
        fileChooserWindow.setApproveButtonText("Save");
        
        // Save selected file path from save as dialog window by "Save" button click
        if(fileChooserWindow.showSaveDialog(window) == JFileChooser.APPROVE_OPTION) {
            File currentFile = fileChooserWindow.getSelectedFile();
            
            // Save source code text to file
            try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(currentFile))) {
                fileWriter.write(sourceText);
                
                window.putClientProperty("file", currentFile);
                window.setTitle(currentFile.getName());
                
                if(currentFile.getName().endsWith(".h"))
                    newFileExtension = ".h";
                else if(currentFile.getName().endsWith(".c"))
                    newFileExtension = ".c";
                else if(currentFile.getName().endsWith(".cpp"))
                    newFileExtension = ".cpp";
                else if(currentFile.getName().endsWith(".ini"))
                    newFileExtension = ".ini";
                
                editorPanel.updateTextBuffer();
                editorPanel.setEditorSyntaxStyle();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            // Add new file to project structure tree list
            if(projectFilePath != null && !("").equals(projectFilePath)) {
                if(structureTreeListModel.isLeaf(structureTreeListModel.getRoot())) {
                    structureTreeListModel.addFileToRoot(currentFile.getName(), currentFile);
                } else {
                    if(structureTreeListModel.getSelectedNode() == null) {
                        structureTreeListModel.addFileToRoot(currentFile.getName(), currentFile);
                    } else {
                        structureTreeListModel.addNodeByType(currentFile.getName(), false, currentFile);
                    }
                }
            }
        } else {
            return false;
        }
        
        return true;
    }
    
    // Save all opened files : function
    private boolean saveAllFiles() {
        for(JInternalFrame editorWindows : EditorMDIFrame.getAllFrames()) {
        
            // Get code editor panel component
            CodeEditorPanel editorPanel = (CodeEditorPanel) editorWindows.getContentPane().getComponent(0);
        
            // Get source code text from code editor panel component
            String sourceText = editorPanel.getEditorSourceText();
            
            // Get current file
            File currentFile = (File) editorWindows.getClientProperty("file");
            
            if(currentFile != null) {
            
                // Save source code text to file
                try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(currentFile))) {
                    fileWriter.write(sourceText);
                    
                    editorWindows.setTitle(currentFile.getName());
                    
                    editorPanel.updateTextBuffer();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        return true;
    }
    
    // Open text file : function
    private boolean openTextFile() throws IOException {
        
        // Set source files extensions filter
        SourceFileFilter fileFilter = new SourceFileFilter("Source files: (*.h, *.c, *.cpp, *.ui, *.ini)",
                                                           new String[] {"h", "c", "cpp", "ui", "ini"});
        
        // Create open file dialog window
        JFileChooser fileChooserWindow = new JFileChooser();
        fileChooserWindow.setDialogTitle("Open");
        fileChooserWindow.setFileFilter(fileFilter);
        fileChooserWindow.setApproveButtonText("Open");
        
        // Open selected file from open file dialog window by "Open" button click
        if(fileChooserWindow.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File currentFile = fileChooserWindow.getSelectedFile();
            
            if(currentFile.getName().endsWith(".h"))
                newFileExtension = ".h";
            else if(currentFile.getName().endsWith(".c"))
                newFileExtension = ".c";
            else if(currentFile.getName().endsWith(".cpp"))
                newFileExtension = ".cpp";
            else if(currentFile.getName().endsWith(".ini"))
                newFileExtension = ".ini";
            
                // Create new editor MDI window
                JInternalFrame editorWindow = new JInternalFrame(currentFile.getName(), true, true, true, true);
                
                CodeEditorPanel editorPanel = new CodeEditorPanel();
                editorPanel.setEditorSourceText(Files.readString(Path.of(currentFile.getPath())));
                editorPanel.updateTextBuffer();
                editorPanel.updateEditorTheme(editorThemeStyle);
                editorPanel.setEditorSyntaxStyle();
                
                editorWindow.setSize(600, 400);
                editorWindow.add(editorPanel);
                editorWindow.setVisible(true);
                editorWindow.putClientProperty("file", currentFile);
        
                EditorMDIFrame.add(editorWindow);
        
                editorWindow.toFront();
                
                try {
                    editorWindow.setSelected(true);
                } catch (PropertyVetoException ex) {
                    System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            
        } else {
            return false;
        }
        
        return true;
    }
    
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
    private javax.swing.JLabel AppearanceSettingsLabel;
    private javax.swing.JPanel AppearanceSettingsPanel;
    private javax.swing.JMenu BookmarksMenu;
    private javax.swing.JButton BuildDebugButton;
    private javax.swing.JPanel BuildLogPanel;
    private javax.swing.JScrollPane BuildLogScroller;
    private javax.swing.JToolBar BuildLogToolbar;
    private javax.swing.JEditorPane BuildLogViewer;
    private javax.swing.JMenu BuildMenu;
    private javax.swing.JButton BuildReleaseButton;
    private javax.swing.JLabel BuildSettingsLabel;
    private javax.swing.JPanel BuildSettingsPanel;
    private javax.swing.JToolBar BuildToolbar;
    private javax.swing.JButton CHeaderFileTypeButton;
    private javax.swing.JButton CPPClassFileTypeButton;
    private javax.swing.JButton CSourceFileTypeButton;
    private javax.swing.JButton CancelSettingsButton;
    private javax.swing.JLabel CapsStatusLabel;
    private javax.swing.JButton ClearBuildLogButton;
    private javax.swing.JButton CloseNewFileWindowButton;
    private javax.swing.JToolBar CommonToolbar;
    private javax.swing.JMenuItem ConfigBuildItem;
    private javax.swing.JButton ContentsHelpButton;
    private javax.swing.JMenuItem ContentsHelpItem;
    private javax.swing.JMenuItem CopyEditItem;
    private javax.swing.JButton CreateNewFileButton;
    private javax.swing.JMenuItem CutEditItem;
    private javax.swing.JMenuItem DebugBuildItem;
    private javax.swing.JMenuItem DesignerToolsItem;
    private javax.swing.JMenu EditMenu;
    private javax.swing.JLabel EditorFontSizeLabel;
    private javax.swing.JSpinner EditorFontSizeSpinner;
    private javax.swing.JDesktopPane EditorMDIFrame;
    private javax.swing.JSplitPane EditorSplitPanel;
    private javax.swing.JLabel EditorStyleLabel;
    private javax.swing.JComboBox<String> EditorStyleListButton;
    private javax.swing.JLabel EncodeStatusLabel;
    private javax.swing.JMenuItem EnumInsertItem;
    private javax.swing.JButton ErrorsFilterButton;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JTree FilesTreeList;
    private javax.swing.JScrollPane FilesTreeScroller;
    private javax.swing.JButton FindAndReplaceButton;
    private javax.swing.JMenuItem FindEditItem;
    private javax.swing.JButton FormDesignFileTypeButton;
    private javax.swing.JSplitPane FrameSplitPanel;
    private javax.swing.JMenuItem FunctInsertItem;
    private javax.swing.JButton GitCheckoutButton;
    private javax.swing.JButton GitCommitButton;
    private javax.swing.JButton GitFetchButton;
    private javax.swing.JLabel GitLoginLabel;
    private javax.swing.JTextField GitLoginTextInput;
    private javax.swing.JScrollPane GitOutputScroller;
    private javax.swing.JEditorPane GitOutputViewer;
    private javax.swing.JPanel GitPanel;
    private javax.swing.JLabel GitPasswordLabel;
    private javax.swing.JPasswordField GitPasswordTextInput;
    private javax.swing.JButton GitPullButton;
    private javax.swing.JButton GitPushButton;
    private javax.swing.JLabel GitSettingsLabel;
    private javax.swing.JPanel GitSettingsPanel;
    private javax.swing.JLabel GitTokenLabel;
    private javax.swing.JTextField GitTokenTextInput;
    private javax.swing.JToolBar GitToolbar;
    private javax.swing.JMenuItem GitToolsItem;
    private javax.swing.JMenuItem GoToViewItem;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JButton INIConfigFileTypeButton;
    private javax.swing.JMenu InsertMenu;
    private javax.swing.JMenuItem InstallPkgBuildItem;
    private javax.swing.JButton MCORESDKPathButton;
    private javax.swing.JLabel MCORESDKPathLabel;
    private javax.swing.JTextField MCORESDKPathTextInput;
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
    private javax.swing.JButton NeptuneSDKPathButton;
    private javax.swing.JLabel NeptuneSDKPathLabel;
    private javax.swing.JTextField NeptuneSDKPathTextInput;
    private javax.swing.JMenuItem NewBookmarkItem;
    private javax.swing.JButton NewFileButton;
    private javax.swing.JMenuItem NewFileItem;
    private javax.swing.JLabel NewFileNameLabel;
    private javax.swing.JTextField NewFileNameTextInput;
    private javax.swing.JPanel NewFileSetupPanel;
    private javax.swing.JDialog NewFileWindow;
    private javax.swing.JLabel NewFileWindowTitleLabel;
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
    private javax.swing.JButton SaveSettingsButton;
    private javax.swing.JMenuItem SetDefViewItem;
    private javax.swing.JMenuItem SettingsItem;
    private javax.swing.JTabbedPane SettingsTabs;
    private javax.swing.JDialog SettingsWindow;
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
    private javax.swing.JLabel ThemeNotificationLabel;
    private javax.swing.JToolBar.Separator ToolbarSeparator1;
    private javax.swing.JToolBar.Separator ToolbarSeparator2;
    private javax.swing.JToolBar.Separator ToolbarSeparator3;
    private javax.swing.JToolBar.Separator ToolbarSeparator5;
    private javax.swing.JSeparator ToolbarSeparator6;
    private javax.swing.JToolBar.Separator ToolbarSeparator7;
    private javax.swing.JToolBar.Separator ToolbarSeparator8;
    private javax.swing.JToolBar.Separator ToolbarSeparator9;
    private javax.swing.JMenu ToolsMenu;
    private javax.swing.JButton UndoEditButton;
    private javax.swing.JMenuItem UndoEditItem;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JLabel WindowThemeLabel;
    private javax.swing.JComboBox<String> WindowThemeListButton;
    private javax.swing.JMenuItem ZoomInViewItem;
    private javax.swing.JMenuItem ZoomOutViewItem;
    // End of variables declaration//GEN-END:variables
}