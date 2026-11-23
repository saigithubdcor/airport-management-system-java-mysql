package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RestrictedArea extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form RestrictedArea
     */
    public RestrictedArea() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displayRestrictedAreas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        restrictedAreaTable = new javax.swing.JTable();
        addRestrictedAreaButton = new javax.swing.JButton();
        deleteRestrictedAreaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        restrictedAreaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Area ID", "Area Type"
            }
        ));
        jScrollPane1.setViewportView(restrictedAreaTable);

        addRestrictedAreaButton.setText("Add");
        addRestrictedAreaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRestrictedAreaButtonActionPerformed(evt);
            }
        });

        deleteRestrictedAreaButton.setText("Delete");
        deleteRestrictedAreaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRestrictedAreaButtonActionPerformed(evt);
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
                .addComponent(addRestrictedAreaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteRestrictedAreaButton)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRestrictedAreaButton)
                    .addComponent(deleteRestrictedAreaButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addRestrictedAreaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRestrictedAreaButtonActionPerformed
        String areaId = JOptionPane.showInputDialog(this, "Enter Area ID:");
        String areaType = JOptionPane.showInputDialog(this, "Enter Area Type:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO restricted_area (Area_ID, Area_Type) VALUES (?, ?)");
            pst.setString(1, areaId);
            pst.setString(2, areaType);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Restricted area added successfully!");
            displayRestrictedAreas();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding restricted area: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addRestrictedAreaButtonActionPerformed

    private void deleteRestrictedAreaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRestrictedAreaButtonActionPerformed
        int selectedRow = restrictedAreaTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a restricted area to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String areaId = (String) restrictedAreaTable.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this restricted area?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM restricted_area WHERE Area_ID=?");
                pst.setString(1, areaId);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Restricted area deleted successfully!");
                displayRestrictedAreas();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting restricted area: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteRestrictedAreaButtonActionPerformed

    private void displayRestrictedAreas() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM restricted_area");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) restrictedAreaTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Area_ID"),
                    rs.getString("Area_Type")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching restricted areas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RestrictedArea().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRestrictedAreaButton;
    private javax.swing.JButton deleteRestrictedAreaButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable restrictedAreaTable;
    // End of variables declaration//GEN-END:variables
}
