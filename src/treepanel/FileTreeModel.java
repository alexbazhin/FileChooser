package treepanel;

import tree.ChooserConst;
import tree.NodeDirectoryTree;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.*;


public class FileTreeModel implements TreeModel {

    private Map map;
    private File root;
    private NodeDirectoryTree rootTree;

    public FileTreeModel() {
        rootTree = new NodeDirectoryTree(ChooserConst.NAME_TREE_ROOT);
    }

    @Override
    public Object getRoot() {
        return rootTree;
    }

    @Override
    public Object getChild(Object node, int index) {
        NodeDirectoryTree parentTrue = (NodeDirectoryTree)node;
        return parentTrue.getChild(index);
    }

    @Override
    public int getChildCount(Object parent) {
        NodeDirectoryTree parentTrue = (NodeDirectoryTree)parent;
        return parentTrue.getChildCount();
    }

   @Override
    public boolean isLeaf(Object node) {
       NodeDirectoryTree parentTrue = (NodeDirectoryTree)node;
       return parentTrue.isLeaf();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        NodeDirectoryTree parentTrue = (NodeDirectoryTree)parent;
        return parentTrue.getIndexOfChild(child);
    }

    private List children(Object node) {
        File file = (File) node;
        Object value = map.get(file);
        if (value == null) return null;
        List children = (List) value;
        if (children == null) {
            List<File> listFiles = new ArrayList<File>(Arrays.asList(file.listFiles()));
            Collections.sort(listFiles);
            if (listFiles != null) {
                children = new ArrayList(listFiles.size());
                for (int len = listFiles.size(), i = 0; i < len; i++) {
                    if (listFiles.get(i).isDirectory()) children.add(listFiles.get(i));
                    if (!listFiles.get(i).isDirectory()) map.put(listFiles.get(i), 0);
                }
            } else {
                children = new ArrayList(0);
            }
            map.put(file, children);
        }
        return children;
    }

    @Override
    public void valueForPathChanged(TreePath treePath, Object o) {
    }

    @Override
    public void addTreeModelListener(TreeModelListener treeModelListener) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener treeModelListener) {
    }

}
