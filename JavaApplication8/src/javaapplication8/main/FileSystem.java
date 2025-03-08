/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.main;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication8.primitivas.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author RDG
 */
public class FileSystem {
    public JFrame frame;
    public JTextArea nameC;
    public JTextArea sizeC;
    public JButton createButtonC;
    public JLabel errorC;
    public JTextArea nameRUD;
    public JTextArea sizeRUD;
    public JButton updateButtonRUD;
    public JButton deleteButtonRUD;
    public JLabel errorRUD;
    public JButton mode;
    public boolean isAdmin;
    String lastName;
    
    public FileSystem(){
        frame = new JFrame("File System");
        JPanel div = new JPanel();
        div.setLayout(new BoxLayout(div, BoxLayout.X_AXIS));
        
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        
        JLabel modeLeabel = new JLabel("Presione para cambiar de Modo");
        modeLeabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(modeLeabel);
        
        mode = new JButton("Actualmente Modo Administrador");
        mode.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(mode); 
        
        isAdmin = true;
        mode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameRUD.setEditable(!isAdmin);
                nameC.setEditable(!isAdmin);
                sizeC.setEditable(!isAdmin);
                updateButtonRUD.setEnabled(!isAdmin);
                deleteButtonRUD.setEnabled(!isAdmin);
                createButtonC.setEnabled(!isAdmin);
                if (isAdmin){
                    mode.setText("Actualmente Modo Usuario");
                    isAdmin = !isAdmin;
                } else {
                    mode.setText("Actualmente Modo Administrador");
                    isAdmin = !isAdmin;
                }
            }
        });
        
        JPanel panelRUD = new JPanel();
        panelRUD.setLayout(new BoxLayout(panelRUD, BoxLayout.Y_AXIS));
        
        JPanel panelUD = new JPanel();
        panelUD.setLayout(new BoxLayout(panelUD, BoxLayout.X_AXIS));
        
        JLabel RUDLabel = new JLabel("Leer, Actualizar y Eliminar");
        Font RUDLabelFont = RUDLabel.getFont();
        RUDLabel.setFont(new Font(RUDLabelFont.getName(), Font.BOLD, 20));
        RUDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelRUD.add(RUDLabel);
        JLabel nameLabelRUD = new JLabel("Nombre");
        nameLabelRUD.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelRUD.add(nameLabelRUD);
        nameRUD = new JTextArea();
        panelRUD.add(nameRUD);
        
        JLabel sizeLabelRUD = new JLabel("Tamaño (No Modificable)");
        sizeLabelRUD.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelRUD.add(sizeLabelRUD);
        sizeRUD = new JTextArea();
        sizeRUD.setEditable(false);
        panelRUD.add(sizeRUD);        
        
        errorRUD = new JLabel("");
        errorRUD.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelRUD.add(errorRUD);
        
        updateButtonRUD = new JButton("Actualizar");
        updateButtonRUD.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelUD.add(updateButtonRUD);        
        
        updateButtonRUD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getNameRUD().equals("")){
                    if (getSizeRUD() != -1){
                        if (getSizeRUD() > 0){
                            if (main.fileDisplay.selectedNode != null){
                                for (int i = 0; i < main.files.isSize(); i++) {
                                    File tempFile = (File) main.files.searchPos(i);
                                    if (tempFile.name.equals(lastName)){
                                        if (tempFile.size == getSizeRUD()){
                                            main.tableDisplay.editFile(tempFile, getNameRUD());
                                            main.fileDisplay.editFile(tempFile, main.fileDisplay.root,main.fileDisplay.selectedNode.getParent().toString(), getNameRUD());
                                            tempFile.name = getNameRUD();
                                            main.files.replacePos(i, tempFile);
                                            main.fileDisplay.selectedNode = null;
                                            main.fileDisplay.tree.clearSelection();
                                            nameRUD.setText("");
                                            sizeRUD.setText("");
                                            main.miscSystem.updateLog("File " + lastName + " Was Updated to " + tempFile.name);
                                            lastName = "";
                                        }
                                    }
                                }
                            }
                        } else {
                            File tempFile = new File();
                            tempFile.name = lastName;
                            main.fileDisplay.editFolder(tempFile, main.fileDisplay.root,main.fileDisplay.selectedNode.getParent().toString(), getNameRUD());
                            main.fileDisplay.selectedNode = null;
                            main.fileDisplay.tree.clearSelection();
                            main.miscSystem.updateLog("Folder " + lastName + " Was Updated to " + getNameRUD());
                            nameRUD.setText("");
                            sizeRUD.setText("");
                            lastName = "";
                        }
                    }
                }
            }
        });
        
        deleteButtonRUD = new JButton("Eliminar");
        deleteButtonRUD.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelUD.add(deleteButtonRUD);
        
        panelRUD.add(panelUD);
        
        deleteButtonRUD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getNameRUD().equals("")){
                    if (getSizeRUD() != -1){
                        if (main.fileDisplay.selectedNode != null){
                            if (getSizeRUD() > 0){
                                for (int i = 0; i < main.files.isSize(); i++) {
                                    File tempFile = (File) main.files.searchPos(i);
                                    if (tempFile.name.equals(getNameRUD())){
                                        if (tempFile.size == getSizeRUD()){
                                            main.files.delPos(i);
                                            main.blockDisplay.delFile(tempFile);
                                            main.fileDisplay.delFile(tempFile, main.fileDisplay.root,main.fileDisplay.selectedNode.getParent().toString());
                                            main.fileDisplay.selectedNode = null;
                                            main.fileDisplay.tree.clearSelection();
                                            main.tableDisplay.delFile(tempFile);
                                            nameRUD.setText("");
                                            sizeRUD.setText("");
                                            main.miscSystem.updateLog("File " + tempFile.name + " Was deleted");
                                        }
                                    }
                                }
                            } else {
                                deleteRecursive(main.fileDisplay.selectedNode);
                                main.fileDisplay.selectedNode = null;
                                main.fileDisplay.tree.clearSelection();
                                nameRUD.setText("");
                                sizeRUD.setText("");
                            }
                        }
                    }
                }
            }
        });
        
        JPanel panelC = new JPanel();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        
        JLabel CLabel = new JLabel("Crear");
        CLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font CLabelFont = CLabel.getFont();
        CLabel.setFont(new Font(CLabelFont.getName(), Font.BOLD, 20));
        panelC.add(CLabel);
        
        JLabel nameLabel = new JLabel("Nombre");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelC.add(nameLabel);
        nameC = new JTextArea();
        panelC.add(nameC);
        
        JLabel sizeLabel = new JLabel("Tamaño (0 Para Carpetas)");
        sizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelC.add(sizeLabel);
        sizeC = new JTextArea();
        panelC.add(sizeC);        
        
        errorC = new JLabel("");
        errorC.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelC.add(errorC);
        
        createButtonC = new JButton("Crear");
        createButtonC.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelC.add(createButtonC);
        
        createButtonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getNameC().equals("")){
                    if (getSizeC() != -1){
                        if (getSizeC() > 0){
                            File file = new File();
                            file.name = getNameC();
                            file.size = getSizeC();
                            if (main.fileDisplay.selectedNode == null){
                                if (main.blockDisplay.addFile(file)){
                                    main.fileDisplay.addFile(file, main.fileDisplay.root, "Root");
                                    main.tableDisplay.addFile(file);
                                    file.parent = "Root";
                                    file.level = 0;
                                    main.files.addEnd(file);
                                    main.miscSystem.updateLog("File " + file.name + " Added");
                                    nameC.setText("");
                                    sizeC.setText("");
                                } else {
                                    main.fileSystem.errorC.setText("No hay Espacio Suficiente");
                                }
                            } else {
                                if (!main.fileDisplay.selectedNode.toString().contains("(") && main.blockDisplay.addFile(file)){
                                    main.fileDisplay.addFile(file, main.fileDisplay.selectedNode, main.fileDisplay.selectedNode.toString());
                                    main.tableDisplay.addFile(file);
                                    boolean canLeave = false;
                                    DefaultMutableTreeNode lastNode = main.fileDisplay.selectedNode;
                                    file.parent = main.fileDisplay.selectedNode.toString();
                                    while (!canLeave){
                                        if (lastNode.getParent() != null){
                                            file.parent = file.parent + "," + lastNode.getParent().toString() + ",";
                                            lastNode = (DefaultMutableTreeNode) lastNode.getParent();
                                        } else {
                                            canLeave = true;
//                                            file.parent = file.parent.substring(0, file.parent.length()-5);
                                        }
                                        
                                    }
                                    file.level = main.fileDisplay.selectedNode.getLevel();
                                    main.fileDisplay.selectedNode = null;
                                    main.fileDisplay.tree.clearSelection();
                                    main.files.addEnd(file);
                                    main.miscSystem.updateLog("File " + file.name + " Added");
                                    nameC.setText("");
                                    sizeC.setText("");
                                } else {
                                    if (main.fileDisplay.selectedNode.toString().contains("(")){
                                        main.fileSystem.errorC.setText("No hay una carpeta seleccionada");
                                    } else {
                                        main.fileSystem.errorC.setText("No hay Espacio Suficiente");
                                    }
                                }
                            }
                        } else {
                            File file = new File();
                            file.name = getNameC();
                            file.size = getSizeC();
                            if (main.fileDisplay.selectedNode == null){
                                main.fileDisplay.addFolder(file, main.fileDisplay.root, "Root");
                                main.miscSystem.updateLog("Folder " + file.name + " Added");
                                nameC.setText("");
                                sizeC.setText("");
                            } else {
                                main.fileDisplay.addFolder(file, main.fileDisplay.selectedNode, main.fileDisplay.selectedNode.toString());
                                main.fileDisplay.selectedNode = null;
                                main.fileDisplay.tree.clearSelection();
                                main.miscSystem.updateLog("Folder " + file.name + " Added");
                                nameC.setText("");
                                sizeC.setText("");
                            }
                        }
                    }
                }
            }
        });
        
        div.add(panelC);
        div.add(new JPanel());
        div.add(panelRUD);
        header.add(div);
        frame.add(header);
        frame.setSize(400, 400);
        frame.setLocation(400, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        main.miscSystem.updateLog("FileSystem started Correctly");
    }
    
    public void dispose(){
        frame.dispose();
    }
    
    public String getNameC(){
        if (nameC.getText().isBlank() || nameC.getText().isEmpty()){
            errorC.setText("Nombre esta Vacio");
            return "";
        } else {
            errorC.setText("");
            return nameC.getText();
        }
    }
    
    public int getSizeC(){
        try {
            int parsedSize = Integer.parseInt(sizeC.getText());
            if (parsedSize >= 0){
                errorC.setText("");
                return parsedSize;
            } else {
                errorC.setText("Tamaño debe ser 0 o mayor");
                return -1;
            }
        } catch (NumberFormatException e) {
            errorC.setText("Tamaño debe ser un numero");
            return -1;
        }
    }
    
    public String getNameRUD(){
        if (nameRUD.getText().isBlank() || nameRUD.getText().isEmpty()){
            errorRUD.setText("Nombre esta Vacio");
            return "";
        } else {
            errorRUD.setText("");
            return nameRUD.getText();
        }
    }
    
    public int getSizeRUD(){
        try {
            int parsedSize = Integer.parseInt(sizeRUD.getText());
            if (parsedSize >= 0){
                errorRUD.setText("");
                return parsedSize;
            } else {
                errorRUD.setText("Tamaño debe ser mayor a 0");
                return -1;
            }
        } catch (NumberFormatException e) {
            errorRUD.setText("Tamaño debe ser un numero");
            return -1;
        }
    }
    
    public void deleteRecursive(DefaultMutableTreeNode node){
        System.out.print(node.toString());
        if (node.getChildCount() > 0){
            while (node.getChildCount() != 0){
                deleteRecursive((DefaultMutableTreeNode) node.getChildAt(0));
            }
            deleteRecursive(node);
        } else {
            if (node.toString().contains("(")){
                System.out.println(" File");
                for (int i = 0; i < main.files.isSize(); i++) {
                    File tempFile = (File) main.files.searchPos(i);
                    String realValue = node.toString();
                    String realName = "";
                    String realSize = "";
                    int valueIndex = 0;
                    for (int j = 0; j < realValue.length(); j++) {
                        if (realValue.charAt(j) == '('){
                            realName = realName.substring(0, realName.length()-1);
                            valueIndex++;
                        } else if (realValue.charAt(j) == ')'){
                            valueIndex++;
                        } else {
                            switch(valueIndex) {
                                case 0 -> {
                                    realName = realName + realValue.charAt(j);
                                }
                                case 1 -> {
                                    realSize = realSize + realValue.charAt(j);
                                }
                              }
                        }
                    }
                    if (tempFile.name.equals(realName)){
                        if (tempFile.size == Integer.parseInt(realSize)){
                            main.files.delPos(i);
                            main.blockDisplay.delFile(tempFile);
                            main.fileDisplay.delFile(tempFile, main.fileDisplay.root,node.getParent().toString());
                            main.tableDisplay.delFile(tempFile);
                            main.miscSystem.updateLog("File " + tempFile.name + " Was deleted");
                        }
                    }
                }
            } else {
                System.out.println(" Folder");
                File tempFile = new File();
                tempFile.name = node.toString();
                main.fileDisplay.delFolder(tempFile, main.fileDisplay.root,node.getParent().toString());
                main.miscSystem.updateLog("Folder " + lastName + " Was deleted");
            }
        }
    }
}
