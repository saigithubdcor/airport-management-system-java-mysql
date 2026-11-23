package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FlightGate extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form FlightGate
     */
    public FlightGate() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displayGates();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        gateTable = new javax.swing.JTable();
        addGateButton = new javax.swing.JButton();
        updateGateButton = new javax.swing.JButton();
        deleteGateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Gate ID", "Gate Number"
            }
        ));
        jScrollPane2.setViewportView(gateTable);

        addGateButton.setText("Add");
        addGateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGateButtonActionPerformed(evt);
            }
        });

        updateGateButton.setText("Update");
        updateGateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateGateButtonActionPerformed(evt);
            }
        });

        deleteGateButton.setText("Delete");
        deleteGateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(addGateButton)
                .addGap(84, 84, 84)
                .addComponent(updateGateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteGateButton)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addGateButton)
                    .addComponent(updateGateButton)
                    .addComponent(deleteGateButton))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addGateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGateButtonActionPerformed
        String gateId = JOptionPane.showInputDialog(this, "Enter gate ID:");
        String gateNumber = JOptionPane.showInputDialog(this, "Enter gate number:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO gate (Gate_ID, Gate_Number) VALUES (?, ?)");
            pst.setString(1, gateId);
            pst.setString(2, gateNumber);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Gate added successfully!");
            displayGates();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding gate: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addGateButtonActionPerformed

    private void updateGateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateGateButtonActionPerformed
        int selectedRow = gateTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a gate to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int gateId = (int) gateTable.getValueAt(selectedRow, 0);
        String gateNumber = JOptionPane.showInputDialog(this, "Enter new gate number:");
        try {
            PreparedStatement pst = conn.prepareStatement("UPDATE gate SET Gate_Number=? WHERE Gate_ID=?");
            pst.setString(1, gateNumber);
            pst.setInt(2, gateId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Gate updated successfully!");
            displayGates();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating gate: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateGateButtonActionPerformed

    private void deleteGateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGateButtonActionPerformed
        int selectedRow = gateTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a gate to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int gateId = (int) gateTable.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this gate?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM gate WHERE Gate_ID=?");
                pst.setInt(1, gateId);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Gate deleted successfully!");
                displayGates();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting gate: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteGateButtonActionPerformed

    private void displayGates() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM gate");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) gateTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("Gate_ID"),
                    rs.getInt("Gate_Number")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching gates: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FlightGate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGateButton;
    private javax.swing.JButton deleteGateButton;
    private javax.swing.JTable gateTable;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton updateGateButton;
    // End of variables declaration//GEN-END:variables
}
