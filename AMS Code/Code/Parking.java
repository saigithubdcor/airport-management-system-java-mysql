package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Parking extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form Parking
     */
    public Parking() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displayParkingEntries();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        parkingTable = new javax.swing.JTable();
        addParkingEntryButton = new javax.swing.JButton();
        deleteParkingEntryButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        parkingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vehicle No", "Entry Time", "Exit Time", "Rev ID", "Rev Collected"
            }
        ));
        jScrollPane1.setViewportView(parkingTable);

        addParkingEntryButton.setText("Add");
        addParkingEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addParkingEntryButtonActionPerformed(evt);
            }
        });

        deleteParkingEntryButton.setText("Delete");
        deleteParkingEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteParkingEntryButtonActionPerformed(evt);
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
                .addComponent(addParkingEntryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteParkingEntryButton)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addParkingEntryButton)
                    .addComponent(deleteParkingEntryButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addParkingEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addParkingEntryButtonActionPerformed
        String vehicleNo = JOptionPane.showInputDialog(this, "Enter Vehicle No:");
        String entryTime = JOptionPane.showInputDialog(this, "Enter Entry Time:");
        String exitTime = JOptionPane.showInputDialog(this, "Enter Exit Time:");
        String revId = JOptionPane.showInputDialog(this, "Enter Revenue ID:");
        String revCollected = JOptionPane.showInputDialog(this, "Enter Revenue Collected:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO parking (Vehicle_No, Entry_Time, Exit_Time, Rev_ID, Rev_Collected) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, vehicleNo);
            pst.setString(2, entryTime);
            pst.setString(3, exitTime);
            pst.setString(4, revId);
            pst.setString(5, revCollected);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Parking entry added successfully!");
            displayParkingEntries();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding parking entry: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addParkingEntryButtonActionPerformed

    private void deleteParkingEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteParkingEntryButtonActionPerformed
        int selectedRow = parkingTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a parking entry to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String vehicleNo = (String) parkingTable.getValueAt(selectedRow, 0);
        String entryTime = (String) parkingTable.getValueAt(selectedRow, 1);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this parking entry?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM parking WHERE Vehicle_No=? AND Entry_Time=?");
                pst.setString(1, vehicleNo);
                pst.setString(2, entryTime);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Parking entry deleted successfully!");
                displayParkingEntries();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting parking entry: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteParkingEntryButtonActionPerformed

    private void displayParkingEntries() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM parking");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) parkingTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Vehicle_No"),
                    rs.getString("Entry_Time"),
                    rs.getString("Exit_Time"),
                    rs.getString("Rev_ID"),
                    rs.getString("Rev_Collected")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching parking entries: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Parking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addParkingEntryButton;
    private javax.swing.JButton deleteParkingEntryButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable parkingTable;
    // End of variables declaration//GEN-END:variables
}
