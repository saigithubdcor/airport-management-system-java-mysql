package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Operates extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form Operates
     */
    public Operates() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displayOperations();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        operationsTable = new javax.swing.JTable();
        addOperationButton = new javax.swing.JButton();
        deleteOperationButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        operationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight ID", "Service ID"
            }
        ));
        jScrollPane1.setViewportView(operationsTable);

        addOperationButton.setText("Add");
        addOperationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOperationButtonActionPerformed(evt);
            }
        });

        deleteOperationButton.setText("Delete");
        deleteOperationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteOperationButtonActionPerformed(evt);
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
                .addComponent(addOperationButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteOperationButton)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addOperationButton)
                    .addComponent(deleteOperationButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addOperationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOperationButtonActionPerformed
        String flightId = JOptionPane.showInputDialog(this, "Enter flight ID:");
        String serviceId = JOptionPane.showInputDialog(this, "Enter service ID:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO operates (Flight_ID, Service_ID) VALUES (?, ?)");
            pst.setString(1, flightId);
            pst.setString(2, serviceId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Operation added successfully!");
            displayOperations();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding operation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addOperationButtonActionPerformed

    private void deleteOperationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteOperationButtonActionPerformed
        int selectedRow = operationsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an operation to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String flightId = (String) operationsTable.getValueAt(selectedRow, 0);
        String serviceId = (String) operationsTable.getValueAt(selectedRow, 1);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this operation?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM operates WHERE Flight_ID=? AND Service_ID=?");
                pst.setString(1, flightId);
                pst.setString(2, serviceId);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Operation deleted successfully!");
                displayOperations();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting operation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteOperationButtonActionPerformed

    private void displayOperations() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM operates");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) operationsTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Flight_ID"),
                    rs.getString("Service_ID")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching operations: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Operates().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addOperationButton;
    private javax.swing.JButton deleteOperationButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable operationsTable;
    // End of variables declaration//GEN-END:variables
}
