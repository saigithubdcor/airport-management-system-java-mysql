package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GroundHandlingEquipment extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form GroundHandlingEquipment
     */
    public GroundHandlingEquipment() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displayEquipment();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        equipmentTable = new javax.swing.JTable();
        addEquipmentButton = new javax.swing.JButton();
        updateEquipmentButton = new javax.swing.JButton();
        deleteEquipmentButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        equipmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Equipment ID", "Equipment Type"
            }
        ));
        jScrollPane1.setViewportView(equipmentTable);

        addEquipmentButton.setText("Add");
        addEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEquipmentButtonActionPerformed(evt);
            }
        });

        updateEquipmentButton.setText("Update");
        updateEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateEquipmentButtonActionPerformed(evt);
            }
        });

        deleteEquipmentButton.setText("Delete");
        deleteEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEquipmentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(addEquipmentButton)
                .addGap(84, 84, 84)
                .addComponent(updateEquipmentButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteEquipmentButton)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEquipmentButton)
                    .addComponent(updateEquipmentButton)
                    .addComponent(deleteEquipmentButton))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEquipmentButtonActionPerformed
        String equipmentId = JOptionPane.showInputDialog(this, "Enter equipment ID:");
        String equipmentType = JOptionPane.showInputDialog(this, "Enter equipment type:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO ground_handling_equipment (Equipment_ID, Equipment_Type) VALUES (?, ?)");
            pst.setString(1, equipmentId);
            pst.setString(2, equipmentType);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Equipment added successfully!");
            displayEquipment();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding equipment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addEquipmentButtonActionPerformed

    private void updateEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateEquipmentButtonActionPerformed
        int selectedRow = equipmentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an equipment to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int equipmentId = (int) equipmentTable.getValueAt(selectedRow, 0);
        String equipmentType = JOptionPane.showInputDialog(this, "Enter new equipment type:");
        try {
            PreparedStatement pst = conn.prepareStatement("UPDATE ground_handling_equipment SET Equipment_Type=? WHERE Equipment_ID=?");
            pst.setString(1, equipmentType);
            pst.setInt(2, equipmentId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Equipment updated successfully!");
            displayEquipment();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating equipment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateEquipmentButtonActionPerformed

    private void deleteEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEquipmentButtonActionPerformed
        int selectedRow = equipmentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an equipment to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int equipmentId = (int) equipmentTable.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this equipment?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM ground_handling_equipment WHERE Equipment_ID=?");
                pst.setInt(1, equipmentId);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Equipment deleted successfully!");
                displayEquipment();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting equipment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteEquipmentButtonActionPerformed

    private void displayEquipment() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM ground_handling_equipment");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) equipmentTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("Equipment_ID"),
                    rs.getString("Equipment_Type")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching equipment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GroundHandlingEquipment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEquipmentButton;
    private javax.swing.JButton deleteEquipmentButton;
    private javax.swing.JTable equipmentTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton updateEquipmentButton;
    // End of variables declaration//GEN-END:variables
}
