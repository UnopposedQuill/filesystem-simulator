
package gui;

import java.io.IOException;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import model.DirectoryNode;
import model.DriveManager;
import model.FileNode;
import model.FileSector;
import model.FileSystemNode;

/**
 *
 * @author darkl
 */
public class JFrameMainWindow extends javax.swing.JFrame {

    private DriveManager driveManager = null;
    
    private final int fileEditorColumnCount, diskContentsColumnCount;
    
    /**
     * Creates new form JFrameMainWindow
     */
    public JFrameMainWindow() {
        initComponents();
        this.fileEditorColumnCount = this.jTableFileContents.getColumnCount();
        this.diskContentsColumnCount = this.jTableVirtualDriveContents.getColumnCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldCurrentDirectory = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeDirectoryTree = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFileContents = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVirtualDriveContents = new javax.swing.JTable();
        jButtonGoDirectory = new javax.swing.JButton();
        jButtonSaveChanges = new javax.swing.JButton();
        jButtonDiscard = new javax.swing.JButton();
        jLabelNodeName = new javax.swing.JLabel();
        jLabelFileSize = new javax.swing.JLabel();
        jLabelCreationDate = new javax.swing.JLabel();
        jLabelModificationDate = new javax.swing.JLabel();
        jLabelParentElement = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemCreateVirtualDisk = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemCreateFile = new javax.swing.JMenuItem();
        jMenuItemCreateDirectory = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemFind = new javax.swing.JMenuItem();
        jMenuItemCopy = new javax.swing.JMenuItem();
        jMenuItemMove = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemRemove = new javax.swing.JMenuItem();
        jMenuSettings = new javax.swing.JMenu();
        jCheckBoxMenuItemFastCd = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextFieldCurrentDirectory.setText("Disco no inicializado");
        jTextFieldCurrentDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCurrentDirectoryActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTreeDirectoryTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreeDirectoryTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreeDirectoryTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTreeDirectoryTree);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTableFileContents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableFileContents.setShowGrid(true);
        jTableFileContents.setTableHeader(null);
        jScrollPane2.setViewportView(jTableFileContents);

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTableVirtualDriveContents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        jTableVirtualDriveContents.setShowGrid(true);
        jTableVirtualDriveContents.setTableHeader(null);
        jScrollPane3.setViewportView(jTableVirtualDriveContents);

        jButtonGoDirectory.setText("Go");
        jButtonGoDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGoDirectoryActionPerformed(evt);
            }
        });

        jButtonSaveChanges.setText("Save");
        jButtonSaveChanges.setEnabled(false);
        jButtonSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveChangesActionPerformed(evt);
            }
        });

        jButtonDiscard.setText("Discard");
        jButtonDiscard.setEnabled(false);
        jButtonDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDiscardActionPerformed(evt);
            }
        });

        jLabelNodeName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelNodeName.setText("<No File Selected>");

        jLabelFileSize.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelFileSize.setText("N/A B");

        jLabelCreationDate.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelCreationDate.setText("N/A");

        jLabelModificationDate.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelModificationDate.setText("N/A");

        jLabelParentElement.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelParentElement.setText("N/A");

        jMenuFile.setText("File");

        jMenuItemCreateVirtualDisk.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemCreateVirtualDisk.setText("Create Virtual Disk");
        jMenuItemCreateVirtualDisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreateVirtualDiskActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemCreateVirtualDisk);
        jMenuFile.add(jSeparator1);

        jMenuItemCreateFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemCreateFile.setText("Create File");
        jMenuItemCreateFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreateFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemCreateFile);

        jMenuItemCreateDirectory.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemCreateDirectory.setText("Create New Directory");
        jMenuItemCreateDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreateDirectoryActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemCreateDirectory);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setText("Edit");

        jMenuItemFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemFind.setText("Find");
        jMenuItemFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFindActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemFind);

        jMenuItemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemCopy.setText("Copy");
        jMenuEdit.add(jMenuItemCopy);

        jMenuItemMove.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemMove.setText("Move");
        jMenuEdit.add(jMenuItemMove);
        jMenuEdit.add(jSeparator2);

        jMenuItemRemove.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuItemRemove.setText("Remove");
        jMenuItemRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRemoveActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemRemove);

        jMenuBar1.add(jMenuEdit);

        jMenuSettings.setText("Preferences");

        jCheckBoxMenuItemFastCd.setText("Fast Directory Changes");
        jMenuSettings.add(jCheckBoxMenuItemFastCd);

        jMenuBar1.add(jMenuSettings);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldCurrentDirectory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonGoDirectory))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonSaveChanges)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonDiscard))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelNodeName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(jLabelFileSize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelCreationDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelModificationDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelParentElement, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCurrentDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGoDirectory))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNodeName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFileSize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCreationDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelModificationDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelParentElement)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSaveChanges)
                            .addComponent(jButtonDiscard)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCreateVirtualDiskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCreateVirtualDiskActionPerformed
        System.out.println("Create new virtual disk");
        
        int diskSize, sectorSize;
        
        try{
            diskSize = Integer.parseInt(JOptionPane.showInputDialog("Please enter number of bytes for the virtual disk", 4098));
            sectorSize = Integer.parseInt(JOptionPane.showInputDialog("Please enter number of bytes for the virtual disk", 8));

            this.driveManager = new DriveManager(diskSize, sectorSize);
            
            this.jTextFieldCurrentDirectory.setText("/");
            this.updateTree();
            this.updateDiskContents();
        } catch (NumberFormatException ex){
            java.util.logging.Logger.getLogger(JFrameMainWindow.class.getName()).log(java.util.logging.Level.WARNING, "Number couldn't be parsed", ex);
        } catch (IOException ex){
            java.util.logging.Logger.getLogger(JFrameMainWindow.class.getName()).log(java.util.logging.Level.WARNING, "Drive couldn't be created, please check directory", ex);
        }
    }//GEN-LAST:event_jMenuItemCreateVirtualDiskActionPerformed

    private void jMenuItemCreateFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCreateFileActionPerformed
        System.out.println("Create new file in current virtual disk");
        if (driveManager == null) {
            JOptionPane.showMessageDialog(null, "Please first create a virtual drive");
        } else {
            try{
                //I need to go through each of the three parameters to check for cancelation
                Object fileName = JOptionPane.showInputDialog("Please enter the name for the file", "New File");
                if (!fileName.equals(JOptionPane.CANCEL_OPTION)) {
                    
                    Object fileExtension = JOptionPane.showInputDialog("Please enter the file's extension", "txt");
                    if (!fileExtension.equals(JOptionPane.CANCEL_OPTION)) {

                        Object fileSize = JOptionPane.showInputDialog("Please enter the number of bytes for the file", 32);
                        if (!fileSize.equals(JOptionPane.CANCEL_OPTION)) {

                            //Use the input info to create the file
                            driveManager.createFile(Integer.parseInt(fileSize.toString()), fileExtension.toString(), fileName.toString());

                            //Now I need to update the tree to change accordingly
                            this.updateTree();
                            this.focusNode((DefaultMutableTreeNode)this.jTreeDirectoryTree.getModel().getRoot(),
                                    new DefaultMutableTreeNode(this.driveManager.getCurrentDirectory()));
                            this.updateDiskContents();
                        }
                    }
                }
            } catch (NumberFormatException ex){
                java.util.logging.Logger.getLogger(JFrameMainWindow.class.getName()).log(java.util.logging.Level.WARNING, "Number couldn't be parsed", ex);
            }
        }
    }//GEN-LAST:event_jMenuItemCreateFileActionPerformed

    private void jMenuItemCreateDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCreateDirectoryActionPerformed
        System.out.println("Create new directory in current virtual disk");
        if (driveManager == null) {
            JOptionPane.showMessageDialog(null, "Please first create a virtual drive");
        } else {
            
            //Time to ask for the new directory name
            Object name = JOptionPane.showInputDialog("Please enter the directory name", "New Folder");
            
            //Check for cancelation
            if (!name.equals(JOptionPane.CANCEL_OPTION)){
                
                //Check for valid name
                if (!name.equals("")) {
                    driveManager.makeDirectory((String)name);
                    System.out.println("New Directory created");
                    
                    //Now I need to update the tree to change accordingly
                    this.updateTree();
                    this.focusNode((DefaultMutableTreeNode)this.jTreeDirectoryTree.getModel().getRoot(),
                            new DefaultMutableTreeNode(this.driveManager.getCurrentDirectory()));
                }
            }
        }
    }//GEN-LAST:event_jMenuItemCreateDirectoryActionPerformed

    private void jMenuItemFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFindActionPerformed
        System.out.println("Find");
    }//GEN-LAST:event_jMenuItemFindActionPerformed

    private void jButtonGoDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGoDirectoryActionPerformed
        System.out.println("Create new directory in current virtual disk");
        if (driveManager == null) {
            JOptionPane.showMessageDialog(null, "Please first create a virtual drive");
        } else {
            if (this.driveManager.changeDirectory(this.jTextFieldCurrentDirectory.getText())) {
                //Directory change successfull
                this.jTextFieldCurrentDirectory.setText(this.driveManager.getCurrentDirectory().getRoute());

                //Now I need to update the tree to change accordingly
                this.focusNode((DefaultMutableTreeNode)this.jTreeDirectoryTree.getModel().getRoot(),
                        new DefaultMutableTreeNode(this.driveManager.getCurrentDirectory()));

            } else {
                //Error
                JOptionPane.showMessageDialog(null, "Couldn't change to directory");
            }
        }
    }//GEN-LAST:event_jButtonGoDirectoryActionPerformed

    private void jTextFieldCurrentDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCurrentDirectoryActionPerformed
        this.jButtonGoDirectory.doClick();
    }//GEN-LAST:event_jTextFieldCurrentDirectoryActionPerformed

    private void jTreeDirectoryTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTreeDirectoryTreeValueChanged
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) 
                this.jTreeDirectoryTree.getLastSelectedPathComponent();

        //If selection changed into none
        if (selectedNode == null) return;

        //There's a node selected
        FileSystemNode nodeInfo = (FileSystemNode)selectedNode.getUserObject();

        this.updateFileContents(nodeInfo);
    }//GEN-LAST:event_jTreeDirectoryTreeValueChanged

    private void jButtonSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveChangesActionPerformed
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) 
                this.jTreeDirectoryTree.getLastSelectedPathComponent();

        //Selected node shouldn't have changed to null
        if (selectedNode == null) return;

        //There's a node selected
        FileSystemNode nodeInfo = (FileSystemNode)selectedNode.getUserObject();

        //I'm adding this if to prevent any awful errors
        if (nodeInfo instanceof FileNode) {
            try{
                FileNode fileNode = (FileNode) nodeInfo;
                char[] data = new char[fileNode.getSize()];

                //For each byte in the file
                for (int i = 0; i < fileNode.getSize(); i++) {
                    
                    //First get the cell value, then parse it to an int, the to a char
                    Object originalCellValue = this.jTableFileContents.getModel().getValueAt(i/this.fileEditorColumnCount, i%this.fileEditorColumnCount);
                    
                    //This is for edited cells
                    if (originalCellValue instanceof String) {
                        int integerCellValue = Integer.parseInt((String)originalCellValue);
                        data[i] = (char)integerCellValue;
                    } else if (originalCellValue instanceof Integer){
                        int integerCellValue = (int)originalCellValue;
                        data[i] = (char)integerCellValue;
                    } else {
                        //Undefined type
                        data[i] = 0;
                    }
                }

                this.driveManager.saveData(fileNode, data);
                this.updateFileContents(nodeInfo);
                this.updateDiskContents();

                //Add a confirmation message
                JOptionPane.showMessageDialog(null, "Contents Saved");
            } catch (ClassCastException ex){
                JOptionPane.showMessageDialog(null, "Incompatible data in editor");
                java.util.logging.Logger.getLogger(JFrameMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, "Incompatible data in editor", ex);
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Couldn't parse numbers in editor");
                java.util.logging.Logger.getLogger(JFrameMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, "Couldn't parse numbers in editor", ex);
            }
        }
    }//GEN-LAST:event_jButtonSaveChangesActionPerformed

    private void jButtonDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDiscardActionPerformed
        //Redraw using the previous selected node
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) 
                this.jTreeDirectoryTree.getLastSelectedPathComponent();

        //Selected node shouldn't have changed to null
        if (selectedNode == null) return;

        //There's a node selected
        FileSystemNode nodeInfo = (FileSystemNode)selectedNode.getUserObject();

        this.updateFileContents(nodeInfo);
    }//GEN-LAST:event_jButtonDiscardActionPerformed

    private void jMenuItemRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRemoveActionPerformed
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) 
                this.jTreeDirectoryTree.getLastSelectedPathComponent();

        //Selected node shouldn't have changed to null
        if (selectedNode == null) return;

        //There's a node selected
        FileSystemNode nodeInfo = (FileSystemNode)selectedNode.getUserObject();
        
        //To avoid errors
        this.jTreeDirectoryTree.clearSelection();
        
        //Remove the node and update the view
        this.driveManager.removeNode(nodeInfo);
        this.updateTree();
        this.clearFileContents();
        this.clearFileData();
    }//GEN-LAST:event_jMenuItemRemoveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new JFrameMainWindow().setVisible(true);
        });
    }
    
    /**
     * This function will update the tree view to the current state of the program
     */
    public void updateTree(){
        
        /*
        I need a new DefaultTreeModel to supply the treeView with
        Note the "true", these are to signal the tree so it differentiates
        between nodes with childs and nodes without childs
        */
        DefaultMutableTreeNode treeRootNode = new DefaultMutableTreeNode(this.driveManager.getRootDirectory(), true);
        
        //For each of its children, call the auxiliar recursive function
        this.driveManager.getRootDirectory().getChildren().forEach(fileSystemNode -> {
            treeRootNode.add(getTreeNode(fileSystemNode));
        });
        
        //Create a new Tree model based on the root
        DefaultTreeModel dtmDirectory = new DefaultTreeModel(treeRootNode, true);
        
        //Finally set the new model
        this.jTreeDirectoryTree.setModel(dtmDirectory);
    }
    
    /**
     * This function will return a Default Mutable Tree Node that stores the
     * representation of it and its childs
     * @param node The node which will be transformed into a DMTN
     * @return A jTree compatible node representing the original node
     */
    public DefaultMutableTreeNode getTreeNode(FileSystemNode node){
        //In case of errors
        if (node == null) {
            return null;
        }
        
        //Now I need to check if it's a file, or a directory node
        if (node instanceof FileNode) {
            return new DefaultMutableTreeNode(node, false);
        }
        
        //It's a directory, need to go through each node
        DefaultMutableTreeNode currentTreeNode = new DefaultMutableTreeNode(node, true);
        
        //Time to populate it
        ((DirectoryNode)node).getChildren().forEach(fileSystemNode -> {
            currentTreeNode.add(getTreeNode(fileSystemNode));
        });
        
        //Return the populated node
        return currentTreeNode;
    }
    
    public void focusNode(DefaultMutableTreeNode node, DefaultMutableTreeNode nodeToFocus){
        //I can only expand or collapse directories
        if (node.getUserObject() instanceof DirectoryNode) {
            //It's a directory, do a call for each subsequential child
            Collections.list(node.children()).forEach(treeNode -> {
                focusNode((DefaultMutableTreeNode) treeNode, nodeToFocus);
            });
            
            if (node.isRoot()) return;
            
            TreePath nodePath = new TreePath(node.getPath());
            
            //I'll check if it's the same to nodeToFocus            
            if (node.getUserObject().equals(nodeToFocus.getUserObject())) {
                this.jTreeDirectoryTree.expandPath(nodePath);
            } else {
                this.jTreeDirectoryTree.collapsePath(nodePath);
            }
        }
    }
    
    /**
     * This function will take care of updating the fileContents table editor
     * to load the file or directory contents
     * @param fileSystemNode The node from which the contents will be loaded from
     */
    public void updateFileContents(FileSystemNode fileSystemNode){
        /*
        Now, I have to show it's info in the information panel, and the contents
        should the selected node be a file
        */
        this.jLabelFileSize.setText(String.valueOf(fileSystemNode.getSize()) + " B");
        this.jLabelCreationDate.setText(fileSystemNode.getCreationDate().toString());
        this.jLabelModificationDate.setText(fileSystemNode.getModificationDate().toString());
        if (fileSystemNode.getParent() != null) {
            this.jLabelParentElement.setText(fileSystemNode.getParent().toString());
        } else {
            this.jLabelParentElement.setText("N/A");
        }
        
        //Now I'm missing specific information from the node itself: its contents
        //And name
        if (fileSystemNode instanceof FileNode) {
            FileNode fileNode = (FileNode) fileSystemNode;
            this.jLabelNodeName.setText("File: " + fileNode.getName());
            
            //Need to split the file information into the column count
            char [] contents = this.driveManager.getData(fileNode);
            
            int rowCount = (int)Math.ceil((double)contents.length/this.fileEditorColumnCount);
            Integer [][] data = new Integer[rowCount][this.fileEditorColumnCount];
            for (int i = 0; i < contents.length; i++) {
                data[i/this.fileEditorColumnCount][i%this.fileEditorColumnCount] = (int)contents[i];
            }
            
            //Now that I have the data, I need the columns
            String[] columnNames = new String[this.fileEditorColumnCount];
            for (int i = 0; i < this.fileEditorColumnCount; i++) {
                columnNames[i] = "Title " + String.valueOf(i + 1);
            }

            //Now I can build a model using the data
            DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);
            
            //Set the model into the table
            this.jTableFileContents.setModel(defaultTableModel);
            
            //And enable the editing buttons
            this.jButtonSaveChanges.setEnabled(true);
            this.jButtonDiscard.setEnabled(true);
            
        } else if (fileSystemNode instanceof DirectoryNode) {
            DirectoryNode directoryNode = (DirectoryNode) fileSystemNode;
            this.jLabelNodeName.setText("Directory: " + directoryNode.getName());
            
            //Update the content table to an empty table
            this.clearFileContents();
        }
    }
    
    /**
     * This will sync the table so that the contents in it reflect the current diskStatus
     */
    public void updateDiskContents(){
        //Need to split the file information into the column count
            
            int rowCount = (int)Math.ceil((double)this.driveManager.getContent().length/this.diskContentsColumnCount);
            Integer [][] data = new Integer[rowCount][this.diskContentsColumnCount];
            for (int i = 0; i < this.driveManager.getContent().length; i++) {
                data[i/this.diskContentsColumnCount][i%this.diskContentsColumnCount] = (int)this.driveManager.getContent()[i];
            }
            
            //Now that I have the data, I need the columns
            String[] columnNames = new String[this.diskContentsColumnCount];
            for (int i = 0; i < this.diskContentsColumnCount; i++) {
                columnNames[i] = "Title " + String.valueOf(i + 1);
            }

            //Now I can build a model using the data
            DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);
            
            //Set the model into the table
            this.jTableVirtualDriveContents.setModel(defaultTableModel);
    }
    
    private void clearFileContents() {
        
        String[] columnNames = new String[this.fileEditorColumnCount];
        for (int i = 0; i < this.fileEditorColumnCount; i++) {
            columnNames[i] = "Title " + String.valueOf(i + 1);
        }
        
        this.jTableFileContents.setModel(new DefaultTableModel(new Object[0][0], columnNames));
            
        //And disable the editing buttons
        this.jButtonSaveChanges.setEnabled(false);
        this.jButtonDiscard.setEnabled(false);
    }
    
    private void clearFileData(){
        this.jLabelNodeName.setText("<No File Selected>");
        this.jLabelFileSize.setText("N/A B");
        this.jLabelCreationDate.setText("N/A");
        this.jLabelModificationDate.setText("N/A");
        this.jLabelParentElement.setText("N/A");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDiscard;
    private javax.swing.JButton jButtonGoDirectory;
    private javax.swing.JButton jButtonSaveChanges;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemFastCd;
    private javax.swing.JLabel jLabelCreationDate;
    private javax.swing.JLabel jLabelFileSize;
    private javax.swing.JLabel jLabelModificationDate;
    private javax.swing.JLabel jLabelNodeName;
    private javax.swing.JLabel jLabelParentElement;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemCreateDirectory;
    private javax.swing.JMenuItem jMenuItemCreateFile;
    private javax.swing.JMenuItem jMenuItemCreateVirtualDisk;
    private javax.swing.JMenuItem jMenuItemFind;
    private javax.swing.JMenuItem jMenuItemMove;
    private javax.swing.JMenuItem jMenuItemRemove;
    private javax.swing.JMenu jMenuSettings;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTableFileContents;
    private javax.swing.JTable jTableVirtualDriveContents;
    private javax.swing.JTextField jTextFieldCurrentDirectory;
    private javax.swing.JTree jTreeDirectoryTree;
    // End of variables declaration//GEN-END:variables
}
