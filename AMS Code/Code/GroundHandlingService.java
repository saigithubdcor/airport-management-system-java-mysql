package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GroundHandlingService extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form GroundHandlingService
     */
    public GroundHandlingService() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displayServices();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        serviceTable = new javax.swing.JTable();
        addServiceButton = new javax.swing.JButton();
        updateServiceButton = new javax.swing.JButton();
        deleteServiceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service ID", "Service Type"
            }
        ));
        jScrollPane1.setViewportView(serviceTable);

        addServiceButton.setText("Add");
        addServiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addServiceButtonActionPerformed(evt);
            }
        });

        updateServiceButton.setText("Update");
        updateServiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateServiceButtonActionPerformed(evt);
            }
        });

        deleteServiceButton.setText("Delete");
        deleteServiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteServiceButtonActionPerformed(evt);
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
                .addComponent(addServiceButton)
                .addGap(84, 84, 84)
                .addComponent(updateServiceButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteServiceButton)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addServiceButton)
                    .addComponent(updateServiceButton)
                    .addComponent(deleteServiceButton))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addServiceButtonActionPerformed
        String serviceId = JOptionPane.showInputDialog(this, "Enter service ID:");
        String serviceType = JOptionPane.showInputDialog(this, "Enter service type:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO ground_handling_service (Service_ID, Service_Type) VALUES (?, ?)");
            pst.setString(1, serviceId);
            pst.setString(2, serviceType);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Service added successfully!");
            displayServices();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding service: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addServiceButtonActionPerformed

    private void updateServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateServiceButtonActionPerformed
        int selectedRow = serviceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a service to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int serviceId = (int) serviceTable.getValueAt(selectedRow, 0);
        String serviceType = JOptionPane.showInputDialog(this, "Enter new service type:");
        try {
            PreparedStatement pst = conn.prepareStatement("UPDATE ground_handling_service SET Service_Type=? WHERE Service_ID=?");
            pst.setString(1, serviceType);
            pst.setInt(2, serviceId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Service updated successfully!");
            displayServices();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating service: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateServiceButtonActionPerformed

    private void deleteServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteServiceButtonActionPerformed
        int selectedRow = serviceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a service to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int serviceId = (int) serviceTable.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this service?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM ground_handling_service WHERE Service_ID=?");
                pst.setInt(1, serviceId);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Service deleted successfully!");
                displayServices();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting service: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteServiceButtonActionPerformed

    private void displayServices() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM ground_handling_service");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("Service_ID"),
                    rs.getString("Service_Type")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching services: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GroundHandlingService().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addServiceButton;
    private javax.swing.JButton deleteServiceButton;
    private javax.swing.JTable serviceTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton updateServiceButton;
    // End of variables declaration//GEN-END:variables
}
