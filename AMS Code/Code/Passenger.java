package com.ams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Passenger extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";
    private Connection conn;

    /**
     * Creates new form Passenger
     */
    public Passenger() {
        initComponents();
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        displayPassengers();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        passengerTable = new javax.swing.JTable();
        addPassengerButton = new javax.swing.JButton();
        deletePassengerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        passengerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Passenger ID", "Passenger Name", "Ticket Number", "Flight ID"
            }
        ));
        jScrollPane1.setViewportView(passengerTable);

        addPassengerButton.setText("Add");
        addPassengerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPassengerButtonActionPerformed(evt);
            }
        });

        deletePassengerButton.setText("Delete");
        deletePassengerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePassengerButtonActionPerformed(evt);
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
                .addComponent(addPassengerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deletePassengerButton)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPassengerButton)
                    .addComponent(deletePassengerButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addPassengerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPassengerButtonActionPerformed
        String passengerId = JOptionPane.showInputDialog(this, "Enter Passenger ID:");
        String passengerName = JOptionPane.showInputDialog(this, "Enter Passenger Name:");
        String ticketNumber = JOptionPane.showInputDialog(this, "Enter Ticket Number:");
        String flightId = JOptionPane.showInputDialog(this, "Enter Flight ID:");
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO passenger (Passenger_ID, Passenger_Name, Ticket_Number, Flight_ID) VALUES (?, ?, ?, ?)");
            pst.setString(1, passengerId);
            pst.setString(2, passengerName);
            pst.setString(3, ticketNumber);
            pst.setString(4, flightId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Passenger added successfully!");
            displayPassengers();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding passenger: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addPassengerButtonActionPerformed

    private void deletePassengerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePassengerButtonActionPerformed
        int selectedRow = passengerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a passenger to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String passengerId = (String) passengerTable.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this passenger?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM passenger WHERE Passenger_ID=?");
                pst.setString(1, passengerId);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Passenger deleted successfully!");
                displayPassengers();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting passenger: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deletePassengerButtonActionPerformed

    private void displayPassengers() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM passenger");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) passengerTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getString("Passenger_ID"),
                    rs.getString("Passenger_Name"),
                    rs.getString("Ticket_Number"),
                    rs.getString("Flight_ID")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching passengers: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Passenger().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPassengerButton;
    private javax.swing.JButton deletePassengerButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable passengerTable;
    // End of variables declaration//GEN-END:variables
}
