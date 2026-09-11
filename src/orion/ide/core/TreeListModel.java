/*
 * =============================================================================
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * =============================================================================
 */

/*
 * -----------------------------------------------------------------------------
 * Tree list model class
 * -----------------------------------------------------------------------------
 * Add new item, insert, rename, delete item and set item icon methods
 * -----------------------------------------------------------------------------
 */
package orion.ide.core;

/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION BEGIN
 * -----------------------------------------------------------------------------
 */
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.swing.*;
import javax.swing.tree.*;
/*
 * -----------------------------------------------------------------------------
 * IMPORTS SECTION END
 * -----------------------------------------------------------------------------
 */

public class TreeListModel extends DefaultTreeModel {
    
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
    private final JTree treeList;
    /*
     * -------------------------------------------------------------------------
     * CLASS FIELDS SECTION END
     * -------------------------------------------------------------------------
     */
    
    /*
     * -------------------------------------------------------------------------
     * Internal node data class
     * -------------------------------------------------------------------------
     */
    public static class NodeData {
        
        /*
         * ---------------------------------------------------------------------
         * INTERNAL CLASS FIELDS SECTION BEGIN
         * ---------------------------------------------------------------------
         */

        /*
         * ---------------------------------------------------------------------
         * PUBLIC INTERNAL CLASS FIELDS
         * ---------------------------------------------------------------------
         */
        public String nodeName;
        public boolean isFolder;
        
        public File fileParam;
        /*
         * ---------------------------------------------------------------------
         * INTERNAL CLASS FIELDS SECTION END
         * ---------------------------------------------------------------------
         */
        
        /*
         * ---------------------------------------------------------------------
         * PUBLIC INTERNAL CLASS METHODS
         * ---------------------------------------------------------------------
         */

        // Internal class constructor
        public NodeData(String nodeName, boolean isFolder, File fileParam) {
            this.nodeName = nodeName;
            this.isFolder = isFolder;
            this.fileParam = fileParam;
        }
        
        @Override
        public String toString() {
            return nodeName; // => selected node name
        }
    }
    
    /*
     * -------------------------------------------------------------------------
     * PUBLIC CLASS METHODS
     * -------------------------------------------------------------------------
     */

    // Constructor
    public TreeListModel(JTree treeList, String rootName) {
        super(new DefaultMutableTreeNode(new NodeData(rootName, true, null)));
        this.treeList = treeList;
        treeList.setModel(this);
    }
    
    // Add new folder to root : method
    public DefaultMutableTreeNode addFolderToRoot(String nodeName) {
        return addNode((DefaultMutableTreeNode) getRoot(), nodeName, true, null);
    }
    
    // Add new file to root : method
    public DefaultMutableTreeNode addFileToRoot(String nodeName, File file) {
        return addNode((DefaultMutableTreeNode) getRoot(), nodeName, false, file);
    }
    
    // Add new node to selected folder : method
    public DefaultMutableTreeNode addToSelected(String nodeName, boolean isFolder, File file) {
        TreePath path = treeList.getSelectionPath();
        
        if(path == null) {
            return null;
        }
        
        DefaultMutableTreeNode selected = (DefaultMutableTreeNode) path.getLastPathComponent();
        
        // Set not able to add file to file
        if(!isFolder(selected)) {
            return null;
        }
        
        return addNode(selected, nodeName, isFolder, file);
    }
    
    // Add new node by type : method
    public DefaultMutableTreeNode addNodeByType(String nodeName, boolean isFolder, File file) {
        TreePath path = treeList.getSelectionPath();
        
        if(path == null) {
            return null;
        }
        
        DefaultMutableTreeNode selected = (DefaultMutableTreeNode) path.getLastPathComponent();
        
        // Compare selected node to folder and insert new node to folder
        if(isFolder(selected)) {
            return addNode(selected, nodeName, isFolder, file);
        }
        
        // New file insert to parent folder
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selected.getParent();
        
        // Check deleted node to parent
        if(parentNode == null) {
            return null;
        }
        
        // Parent node always is folder
        return addNode(parentNode, nodeName, isFolder, file);
    }

    // Check node type to folder : method
    public boolean isFolder(DefaultMutableTreeNode node) {
        if(node == null)
            return false;
        
        Object userObject = node.getUserObject();
        
        if(userObject instanceof NodeData nodeData) {
            return nodeData.isFolder; // => true
        }
        
        return false;
    }
    
    // Check node type to file : method
    public boolean isFile(DefaultMutableTreeNode node) {
        if(node == null)
            return false;
        
        Object userObject = node.getUserObject();
        
        if(userObject instanceof NodeData nodeData) {
            return !nodeData.isFolder; // => false
        }
        
        return false;
    }
    
    // Rename selected node : method
    public void renameNode(DefaultMutableTreeNode node, String nodeName) {
        if(node == null)
            return;
        Object userObject = node.getUserObject();
        
        // Using is EDT for treads savety
        if(userObject instanceof NodeData nodeData) {
            nodeData.nodeName = nodeName;
            SwingUtilities.invokeLater(() -> nodeChanged(node));
        }
    }
    
    // Delete selected node : method
    public void deleteNode(DefaultMutableTreeNode node) {
        
        // NullPointerException fix
        if(node == null || node.isRoot()) {
            return;
        }
        
        SwingUtilities.invokeLater(() -> removeNodeFromParent(node));
    }
    
    // Get child elements count : method
    public int getChildCount(DefaultMutableTreeNode parentNode) {
        return parentNode == null ? 0 : parentNode.getChildCount();
    }
    
    // Get child list : method
    public List<DefaultMutableTreeNode> getChildNodes(DefaultMutableTreeNode parentNode) {
        List<DefaultMutableTreeNode> arrayList = new ArrayList<>();
        
        if(parentNode != null) {
            for(int i = 0; i < parentNode.getChildCount(); i++) {
                arrayList.add((DefaultMutableTreeNode) parentNode.getChildAt(i));
            }
        }
        
        return arrayList;
    }
    
    // Get selected node : method
    public DefaultMutableTreeNode getSelectedNode() {
        TreePath path = treeList.getSelectionPath();
        
        if(path == null) {
            return null;
        }
        
        return (DefaultMutableTreeNode) path.getLastPathComponent();
    }

    /*
     * -------------------------------------------------------------------------
     * PRIVATE CLASS FUNCTIONS
     * -------------------------------------------------------------------------
     */
    
    // Add new node : function
    private DefaultMutableTreeNode addNode(DefaultMutableTreeNode parentNode, String nodeName, boolean isFolder, File file) {
        
        // Check new node to exists
        if(file != null) {
            DefaultMutableTreeNode existingNode = findChildByFile(parentNode, file);
            
            if(existingNode != null) {
                return existingNode;
            }
        }
        
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeData(nodeName, isFolder, file));
        
        // Using is EDT for treads savety
        SwingUtilities.invokeLater(() -> {
            insertNodeInto(node, parentNode, parentNode.getChildCount());
            expandList(parentNode);
        });
        
        return node;
    }
    
    // Find already existed node for not dublicate nodes : function
    private DefaultMutableTreeNode findChildByFile(DefaultMutableTreeNode parentNode, File file) {
        if(parentNode == null || file == null)
            return null;
        
        for(int i = 0; i < parentNode.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) parentNode.getChildAt(i);
            Object userObject = child.getUserObject();
            
            if(userObject instanceof NodeData data) {
                
                if(data.fileParam != null && data.fileParam.getAbsolutePath().equals(file.getAbsolutePath())) {
                    return child;
                }
            }
        }
        
        return null;
    }
    
    // Expand folder : function
    private void expandList(DefaultMutableTreeNode node) {
        TreePath path = new TreePath(node.getPath());
        treeList.expandPath(path);
    }
}