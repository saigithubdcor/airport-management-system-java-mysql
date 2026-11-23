package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SecurityManagement extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form SecurityManagement
     */
    public SecurityManagement() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displaySecuritySystems();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        securityTable = new javax.swing.JTable();
        addSecuritySystemButton = new javax.swing.JButton();
        deleteSecuritySystemButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        securityTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Security ID", "Security Type", "Belt Count", "Area ID"
            }
        ));
        jScrollPane1.setViewportView(securityTable);

        addSecuritySystemButton.setText("Add");
        addSecuritySystemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSecuritySystemButtonActionPerformed(evt);
            }
        });

        deleteSecuritySystemButton.setText("Delete");
        deleteSecuritySystemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSecuritySystemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(addSecuritySystemButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteSecuritySystemButton)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSecuritySystemButton)
                    .addComponent(deleteSecuritySystemButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addSecuritySystemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSecuritySystemButtonActionPerformed
        String securityId = JOptionPane.showInputDialog(this, "Enter Security ID:");
        String securityType = JOptionPane.showInputDialog(this, "Enter Security Type:");
        String beltCount = JOptionPane.showInputDialog(this, "Enter Belt Count:");
        String areaId = JOptionPane.showInputDialog(this, "Enter Area ID:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO security_system (Security_ID, Security_Type, Belt_Count, Area_ID) VALUES (?, ?, ?, ?)");
            pst.setString(1, securityId);
            pst.setString(2, securityType);
            pst.setString(3, beltCount);
            pst.setString(4, areaId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Security system added successfully!");
            displaySecuritySystems();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding security system: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addSecuritySystemButtonActionPerformed

    private void deleteSecuritySystemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSecuritySystemButtonActionPerformed
        int selectedRow = securityTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a security system to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String securityId = (String) securityTable.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this security system?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM security_system WHERE Security_ID=?");
                pst.setString(1, securityId);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Security system deleted successfully!");
                displaySecuritySystems();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting security system: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteSecuritySystemButtonActionPerformed

    private void displaySecuritySystems() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM security_system");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) securityTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Security_ID"),
                    rs.getString("Security_Type"),
                    rs.getString("Belt_Count"),
                    rs.getString("Area_ID")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching security systems: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecurityManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSecuritySystemButton;
    private javax.swing.JButton deleteSecuritySystemButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable securityTable;
    // End of variables declaration//GEN-END:variables
}
