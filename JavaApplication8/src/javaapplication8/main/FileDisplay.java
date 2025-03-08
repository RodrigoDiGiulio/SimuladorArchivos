package javaapplication8.main;

import java.util.Enumeration;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.*;
import javaapplication8.primitivas.File;
import javaapplication8.primitivas.Folder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * @author RDG
 */
public class FileDisplay {
    public JFrame frame;
    public DefaultMutableTreeNode root;
    public JScrollPane scrollPane;
    public JTree tree;
    public DefaultMutableTreeNode selectedNode;

    public FileDisplay() {
        frame = new JFrame("File Display");

        root = new DefaultMutableTreeNode("Root");
        tree = new JTree(root);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                if (selectedNode != null){
                    String tempRealName = selectedNode.toString();
                    if (tempRealName.contains("(")){
                        for (int i = 0; i < main.files.isSize(); i++) {
                            File tempFile = (File) main.files.searchPos(i);
                            String realName = "";
                            String realSize = "";
                            int numberStart = 0;
                            for (int j = 0; j < tempRealName.length(); j++) {
                                if (tempRealName.charAt(j) == '('){
                                    realName = tempRealName.substring(0, j-1);
                                    numberStart = j;
                                } else if (tempRealName.charAt(j) == ')'){
                                    realSize = tempRealName.substring(numberStart+1,j);
                                }
                            }
                            int realSizeInt;
                            try {
                                realSizeInt = Integer.parseInt(realSize);
                                if (tempFile.name.equals(realName)){
                                    if (tempFile.size == realSizeInt){
                                        main.fileSystem.nameRUD.setText(tempFile.name);
                                        main.fileSystem.lastName = tempFile.name;
                                        main.fileSystem.sizeRUD.setText(String.valueOf(tempFile.size));
                                        break;
                                    }
                                }
                            } catch (NumberFormatException f) {
                            }
                        }
                    } else {
                        main.fileSystem.nameRUD.setText(tempRealName);
                        main.fileSystem.lastName = tempRealName;
                        main.fileSystem.sizeRUD.setText("0");
                    }
                }   
            }
        });
        scrollPane = new JScrollPane(tree);
        frame.add(scrollPane);
        frame.setSize(400, 400);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        main.miscSystem.updateLog("FileDisplay started Correctly");
    }
    
    public void dispose(){
        frame.dispose();
    }
    
    public void addFile(File fileData, DefaultMutableTreeNode node, String folderName) {
        String data = fileData.name + " (" + fileData.size + ")";
        Enumeration<TreeNode> children = node.children();
        if (node.toString().equals(folderName)) {
            if (!node.toString().contains("(")){
                DefaultMutableTreeNode file = new DefaultMutableTreeNode(data);
                node.add(file);
                ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
            }
        } else {
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
                addFile(fileData, child, folderName);
            }
        }
    }
    
    public void editFile(File fileData, DefaultMutableTreeNode node, String folderName, String newName) {
        String data = fileData.name + " (" + fileData.size + ")";
        Enumeration<TreeNode> children = node.children();
        if (node.toString().equals(folderName)) {
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChildAt(i).toString().equals(data)){
                    String newData = newName + " (" + fileData.size + ")";
                    DefaultMutableTreeNode lastNode = (DefaultMutableTreeNode) node.getChildAt(i);
                    lastNode.setUserObject(newData);
                }
            }
            ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
        } else {
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
                editFile(fileData, child, folderName,newName);
            }
        }
    }
    
    public void editFolder(File fileData, DefaultMutableTreeNode node, String folderName, String newName) {
        String data = fileData.name;
        Enumeration<TreeNode> children = node.children();
        if (node.toString().equals(folderName)) {
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChildAt(i).toString().equals(data)){
                    String newData = newName;
                    DefaultMutableTreeNode lastNode = (DefaultMutableTreeNode) node.getChildAt(i);
                    lastNode.setUserObject(newData);
                }
            }
            ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
        } else {
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
                editFolder(fileData, child, folderName,newName);
            }
        }
    }
    
    public void delFile(File fileData, DefaultMutableTreeNode node, String folderName) {
        String data = fileData.name + " (" + fileData.size + ")";
        Enumeration<TreeNode> children = node.children();
        if (node.toString().equals(folderName)) {
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChildAt(i).toString().equals(data)){
                    node.remove(i);
                }
            }
            ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
        } else {
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
                delFile(fileData, child, folderName);
            }
        }
    }
    
    public void addFolder(File folderData, DefaultMutableTreeNode node, String folderName) {
        String data = folderData.name;
        Enumeration<TreeNode> children = node.children();
        System.out.println("Folder " + folderName + "Node " + node.toString());
        if (node.toString().equals(folderName)) {
            System.out.println("EQUAL");
            boolean exists = false;
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChildAt(i).toString().equals(data)){
                    exists = true;
                }
            }
            System.out.println(exists);
            if (!exists){
                System.out.println("FOLDER " + folderData.name + "ADDED");
                DefaultMutableTreeNode file = new DefaultMutableTreeNode(data);
                node.add(file);
                ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
            }
        } else {
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
                addFolder(folderData, child, folderName);
            }
        }
    }    
    
    public void delFolder(File folderData, DefaultMutableTreeNode node, String folderName) {
        String data = folderData.name;
        Enumeration<TreeNode> children = node.children();
        if (node.toString().equals(folderName)) {
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChildAt(i).toString().equals(data)){
                    node.remove(i);
                    break;
                }
            }
            ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
        } else {
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
                delFolder(folderData, child, folderName);
            }
        }
    }
 
    public boolean searchFile(DefaultMutableTreeNode node, String fileName) {
        if (node.toString().equals(fileName)) {
            return true;
        } else {
            Enumeration<TreeNode> children = node.children();
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
                if (searchFile(child, fileName)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean searchFolder(DefaultMutableTreeNode node, String folderName) {
        if (node.toString().equals(folderName)) {
            return true;
        } else {
            Enumeration<TreeNode> children = node.children();
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
                if (searchFolder(child, folderName)) {
                    return true;
                }
            }
            return false;
        }
    }
}