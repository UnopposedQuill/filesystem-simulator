
package gui;

import java.io.IOException;
import java.lang.System.Logger;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.DriveManager;

/**
 *
 * @author darkl
 */
public class JFrameMainWindow extends javax.swing.JFrame {

    private DriveManager driveManager = null;
    
    /**
     * Creates new form JFrameMainWindow
     */
    public JFrameMainWindow() {
        initComponents();
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
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFileContents = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVirtualDriveContents = new javax.swing.JTable();
        jButtonGoDirectory = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemCreateVirtualDisk = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemCreateFile = new javax.swing.JMenuItem();
        jMenuItemCreateDirectory = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemFind = new javax.swing.JMenuItem();
        jMenuItemCopy = new javax.swing.JMenuItem();
        jMenuItemMove = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemRemove = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextFieldCurrentDirectory.setText("jTextField1");

        jScrollPane1.setViewportView(jTree1);

        jTableFileContents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Byte((byte) 0),  new Byte((byte) 1),  new Byte((byte) 2),  new Byte((byte) 3)},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Byte.class, java.lang.Byte.class, java.lang.Byte.class, java.lang.Byte.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableFileContents.setShowGrid(true);
        jTableFileContents.setTableHeader(null);
        jScrollPane2.setViewportView(jTableFileContents);

        jTableVirtualDriveContents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "127", "-128", "100"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableVirtualDriveContents.setShowGrid(true);
        jTableVirtualDriveContents.setTableHeader(null);
        jScrollPane3.setViewportView(jTableVirtualDriveContents);

        jButtonGoDirectory.setText("Go");

        jMenu1.setText("File");

        jMenuItemCreateVirtualDisk.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemCreateVirtualDisk.setText("Create Virtual Disk");
        jMenuItemCreateVirtualDisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreateVirtualDiskActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCreateVirtualDisk);
        jMenu1.add(jSeparator1);

        jMenuItemCreateFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemCreateFile.setText("Create File");
        jMenuItemCreateFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreateFileActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCreateFile);

        jMenuItemCreateDirectory.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemCreateDirectory.setText("Create New Directory");
        jMenuItemCreateDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreateDirectoryActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCreateDirectory);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItemFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemFind.setText("Find");
        jMenuItemFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFindActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemFind);

        jMenuItemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemCopy.setText("Copy");
        jMenu2.add(jMenuItemCopy);

        jMenuItemMove.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemMove.setText("Move");
        jMenu2.add(jMenuItemMove);
        jMenu2.add(jSeparator2);

        jMenuItemRemove.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuItemRemove.setText("Remove");
        jMenu2.add(jMenuItemRemove);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldCurrentDirectory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonGoDirectory)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
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
            
            this.jTextFieldCurrentDirectory.setText("\\");
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
            driveManager.createFile();
        }
    }//GEN-LAST:event_jMenuItemCreateFileActionPerformed

    private void jMenuItemCreateDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCreateDirectoryActionPerformed
        System.out.println("Create new directory in current virtual disk");
        if (driveManager == null) {
            JOptionPane.showMessageDialog(null, "Please first create a virtual drive");
        } else {
            driveManager.makeDirectory();
        }
    }//GEN-LAST:event_jMenuItemCreateDirectoryActionPerformed

    private void jMenuItemFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFindActionPerformed
        System.out.println("Find");
    }//GEN-LAST:event_jMenuItemFindActionPerformed

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGoDirectory;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemCreateDirectory;
    private javax.swing.JMenuItem jMenuItemCreateFile;
    private javax.swing.JMenuItem jMenuItemCreateVirtualDisk;
    private javax.swing.JMenuItem jMenuItemFind;
    private javax.swing.JMenuItem jMenuItemMove;
    private javax.swing.JMenuItem jMenuItemRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTableFileContents;
    private javax.swing.JTable jTableVirtualDriveContents;
    private javax.swing.JTextField jTextFieldCurrentDirectory;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
