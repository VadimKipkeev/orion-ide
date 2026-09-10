/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 *******************************************************************************
 * Tree list icon renderer class
 *******************************************************************************
 * Set SVG icons to elements tree list by node type
 *******************************************************************************
 */
package orion.ide.core;

/* -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import orion.ide.ui.IconProvider;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class TreeListIconRenderer extends DefaultTreeCellRenderer {
    
    /* -------------------------------------------------------------------------
     * CLASS FIELDS SECTION BEGIN
     * -------------------------------------------------------------------------
     */
    // Set node types icons
    public final Icon folderTreeIcon;
    public final Icon hFileTreeIcon;
    public final Icon cFileTreeIcon;
    public final Icon cppFileTreeIcon;
    public final Icon imageFileTreeIcon;
    public final Icon uiFileTreeIcon;
    public final Icon iniFileTreeIcon;
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    // Constructor
    public TreeListIconRenderer() {
        folderTreeIcon = IconProvider.getIcon("folder", 16);
        hFileTreeIcon = IconProvider.getIcon("c_header_file.svg", 16);
        cFileTreeIcon = IconProvider.getIcon("c_source_file.svg", 16);
        cppFileTreeIcon = IconProvider.getIcon("cpp_class_file.svg", 16);
        imageFileTreeIcon = IconProvider.getIcon("image_file.svg", 16);
        uiFileTreeIcon = IconProvider.getIcon("form_design_file.svg", 16);
        iniFileTreeIcon = IconProvider.getIcon("ini_file.svg", 16);
    }
    
    // Renderer tree list cell : method
    @Override
    public Component getTreeCellRendererComponent(JTree treeList, Object value, boolean sel,
                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(treeList, value, sel, expanded, leaf, row, hasFocus);
        
        if(value instanceof DefaultMutableTreeNode node) {
            Object object = node.getUserObject();
            
            if(object instanceof TreeListModel.NodeData nodeData) {
                if(!nodeData.isFolder) {
                   
                    // Get file extension
                    String nodeName = nodeData.nodeName.toLowerCase();
                    
                    if(nodeName.endsWith(".h"))
                        setIcon(hFileTreeIcon);
                    else if(nodeName.endsWith(".c"))
                        setIcon(cFileTreeIcon);
                    else if(nodeName.endsWith(".cpp"))
                        setIcon(cppFileTreeIcon);
                    else if(nodeName.endsWith(".bmp") || nodeName.endsWith(".jpg") || nodeName.endsWith("gif"))
                        setIcon(imageFileTreeIcon);
                    else if(nodeName.endsWith(".ui"))
                        setIcon(uiFileTreeIcon);
                    else if(nodeName.endsWith(".ini"))
                        setIcon(iniFileTreeIcon);
                } else {
                    setIcon(folderTreeIcon);
                }
            }
        }
        
        return this; // => Component
    }
}