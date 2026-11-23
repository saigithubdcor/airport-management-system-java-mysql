package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ticket extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form Ticket
     */
    public Ticket() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displayTicketEntries();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ticketTable = new javax.swing.JTable();
        addTicketEntryButton = new javax.swing.JButton();
        deleteTicketEntryButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ticketTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ticket ID", "Passenger ID", "Flight ID"
            }
        ));
        jScrollPane1.setViewportView(ticketTable);

        addTicketEntryButton.setText("Add");
        addTicketEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTicketEntryButtonActionPerformed(evt);
            }
        });

        deleteTicketEntryButton.setText("Delete");
        deleteTicketEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTicketEntryButtonActionPerformed(evt);
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
                .addComponent(addTicketEntryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteTicketEntryButton)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTicketEntryButton)
                    .addComponent(deleteTicketEntryButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addTicketEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTicketEntryButtonActionPerformed
        String ticketId = JOptionPane.showInputDialog(this, "Enter Ticket ID:");
        String passengerId = JOptionPane.showInputDialog(this, "Enter Passenger ID:");
        String flightId = JOptionPane.showInputDialog(this, "Enter Flight ID:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO ticket (Ticket_ID, Passenger_ID, Flight_ID) VALUES (?, ?, ?)");
            pst.setString(1, ticketId);
            pst.setString(2, passengerId);
            pst.setString(3, flightId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ticket entry added successfully!");
            displayTicketEntries();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding ticket entry: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addTicketEntryButtonActionPerformed

    private void deleteTicketEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTicketEntryButtonActionPerformed
        int selectedRow = ticketTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ticket entry to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String ticketId = (String) ticketTable.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this ticket entry?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM ticket WHERE Ticket_ID=?");
                pst.setString(1, ticketId);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Ticket entry deleted successfully!");
                displayTicketEntries();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting ticket entry: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteTicketEntryButtonActionPerformed

    private void displayTicketEntries() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM ticket");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) ticketTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Ticket_ID"),
                    rs.getString("Passenger_ID"),
                    rs.getString("Flight_ID")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching ticket entries: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ticket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTicketEntryButton;
    private javax.swing.JButton deleteTicketEntryButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable ticketTable;
    // End of variables declaration//GEN-END:variables
}
