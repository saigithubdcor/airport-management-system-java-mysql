package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Revenue extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form Revenue
     */
    public Revenue() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displayRevenueEntries();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        revenueTable = new javax.swing.JTable();
        addRevenueEntryButton = new javax.swing.JButton();
        deleteRevenueEntryButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        revenueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rev ID", "Revenue Type", "Rev Collected"
            }
        ));
        jScrollPane1.setViewportView(revenueTable);

        addRevenueEntryButton.setText("Add");
        addRevenueEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRevenueEntryButtonActionPerformed(evt);
            }
        });

        deleteRevenueEntryButton.setText("Delete");
        deleteRevenueEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRevenueEntryButtonActionPerformed(evt);
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
                .addComponent(addRevenueEntryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteRevenueEntryButton)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRevenueEntryButton)
                    .addComponent(deleteRevenueEntryButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addRevenueEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRevenueEntryButtonActionPerformed
        String revId = JOptionPane.showInputDialog(this, "Enter Rev ID:");
        String revenueType = JOptionPane.showInputDialog(this, "Enter Revenue Type:");
        String revCollected = JOptionPane.showInputDialog(this, "Enter Revenue Collected:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO revenue (Rev_ID, Revenue_Type, Rev_Collected) VALUES (?, ?, ?)");
            pst.setString(1, revId);
            pst.setString(2, revenueType);
            pst.setString(3, revCollected);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Revenue entry added successfully!");
            displayRevenueEntries();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding revenue entry: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addRevenueEntryButtonActionPerformed

    private void deleteRevenueEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRevenueEntryButtonActionPerformed
        int selectedRow = revenueTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a revenue entry to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String revId = (String) revenueTable.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this revenue entry?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM revenue WHERE Rev_ID=?");
                pst.setString(1, revId);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Revenue entry deleted successfully!");
                displayRevenueEntries();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting revenue entry: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteRevenueEntryButtonActionPerformed

    private void displayRevenueEntries() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM revenue");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) revenueTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Rev_ID"),
                    rs.getString("Revenue_Type"),
                    rs.getString("Rev_Collected")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching revenue entries: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Revenue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRevenueEntryButton;
    private javax.swing.JButton deleteRevenueEntryButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable revenueTable;
    // End of variables declaration//GEN-END:variables
}
