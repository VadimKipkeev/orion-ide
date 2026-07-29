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
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
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
    public final FlatSVGIcon folderTreeIcon;
    public final FlatSVGIcon hFileTreeIcon;
    public final FlatSVGIcon cFileTreeIcon;
    public final FlatSVGIcon cppFileTreeIcon;
    public final FlatSVGIcon imageFileTreeIcon;
    public final FlatSVGIcon uiFileTreeIcon;
    public final FlatSVGIcon iniFileTreeIcon;
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    // Constructor
    public TreeListIconRenderer() {
        folderTreeIcon = new FlatSVGIcon("resources/icons/commons/folder.svg", 16, 16);
        hFileTreeIcon = new FlatSVGIcon("resources/icons/commons/c_header_file.svg", 16, 16);
        cFileTreeIcon = new FlatSVGIcon("resources/icons/commons/c_source_file.svg", 16, 16);
        cppFileTreeIcon = new FlatSVGIcon("resources/icons/commons/cpp_class_file.svg", 16, 16);
        imageFileTreeIcon = new FlatSVGIcon("resources/icons/commons/image_file.svg", 16, 16);
        uiFileTreeIcon = new FlatSVGIcon("resources/icons/commons/form_design_file.svg", 16, 16);
        iniFileTreeIcon = new FlatSVGIcon("resources/icons/commons/ini_file.svg", 16, 16);
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