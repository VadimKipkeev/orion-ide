/*
 * -----------------------------------------------------------------------------
 * Orion IDE Project
 * -----------------------------------------------------------------------------
 * (c) 2026 CrayZor. All rights reserved
 * -----------------------------------------------------------------------------
 */

/*
 *******************************************************************************
 * Tree list model class
 *******************************************************************************
 * Add new item, insert, rename, delete item and set item icon methods
 *******************************************************************************
 */
package orion.ide.core;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.*;

public class TreeListModel extends DefaultTreeModel {
    private final JTree treeList;
    
    /*
     ***************************************************************************
     * Internal node data class
     ***************************************************************************
     */
    public static class NodeData {
        public String nodeName;
        public boolean isFolder;
        
        // Internal class constructor
        public NodeData(String nodeName, boolean isFolder) {
            this.nodeName = nodeName;
            this.isFolder = isFolder;
        }
        
        @Override
        public String toString() {
            return nodeName; // => selected node name
        }
    }
    
    // Constructor
    public TreeListModel(JTree treeList, String rootName) {
        super(new DefaultMutableTreeNode(rootName));
        this.treeList = treeList;
        treeList.setModel(this);
    }
    
    // Add new folder to root method
    public DefaultMutableTreeNode addToRoot(String nodeName) {
        return addNode((DefaultMutableTreeNode) getRoot(), nodeName, true);
    }
    
    // Add new file to root method
    public DefaultMutableTreeNode addFileToRoot(String nodeName) {
        return addNode((DefaultMutableTreeNode) getRoot(), nodeName, false);
    }
    
    // Add new node to selected folder method
    public DefaultMutableTreeNode addToSelected(String nodeName, boolean isFolder) {
        TreePath path = treeList.getSelectionPath();
        
        if(path == null) {
            return null;
        }
        
        DefaultMutableTreeNode selected = (DefaultMutableTreeNode) path.getLastPathComponent();
        
        // Set not able to add file to file
        if(!isFolder(selected)) {
            return null;
        }
        
        return addNode(selected, nodeName, isFolder);
    }
    
    // Add new node method
    private DefaultMutableTreeNode addNode(DefaultMutableTreeNode parentNode, String nodeName, boolean isFolder) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeData(nodeName, isFolder));
        insertNodeInto(node, parentNode, parentNode.getChildCount());
        expandList(parentNode);
        return node;
    }
    
    // Check node type to folder method
    public boolean isFolder(DefaultMutableTreeNode node) {
        NodeData data = (NodeData) node.getUserObject();
        return data.isFolder;
    }
    
    // Check node type to file method
    public boolean isFile(DefaultMutableTreeNode node) {
        NodeData data = (NodeData) node.getUserObject();
        return !data.isFolder;
    }
    
    // Rename selected node method
    public void renameNode(DefaultMutableTreeNode node, String nodeName) {
        NodeData data = (NodeData) node.getUserObject();
        data.nodeName = nodeName;
        nodeChanged(node);
    }
    
    // Delete selected node method
    public void deleteNode(DefaultMutableTreeNode node) {
        removeNodeFromParent(node);
    }
    
    // Get child elements count method
    public int getChildCount(DefaultMutableTreeNode parentNode) {
        int nodeCount = parentNode.getChildCount();
        return nodeCount;
    }
    
    // Get child list method
    public List<DefaultMutableTreeNode> getChildNodes(DefaultMutableTreeNode parentNode) {
        List<DefaultMutableTreeNode> arrayList = new ArrayList<>();
        
        for(int i = 0; i < parentNode.getChildCount(); i++) {
            arrayList.add((DefaultMutableTreeNode) parentNode.getChildAt(i));
        }
        
        return arrayList;
    }
    
    // Expand folder function
    private void expandList(DefaultMutableTreeNode node) {
        TreePath path = new TreePath(node.getPath());
        treeList.expandPath(path);
    }
}