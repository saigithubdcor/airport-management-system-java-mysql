package com.ams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserHome extends JFrame {
    CardLayout cardLayout;
    JPanel cards; // Panel that uses CardLayout
    JButton baggageButton, departureButton, supportButton, signOutButton, setButton, bSetButton, dSetButton, sSetButton, pilotButton, parkingButton, pSetButton, airlineButton, busyButton, reportButton, employeeButton, eButton, updateButton;
    JTextField employeeIdField, oldBranchIdField, newBranchIdField, bEmployeeIdField, bOldBranchIdField, bNewBranchIdField, dEmployeeIdField, dOldBranchIdField, dNewBranchIdField, sEmployeeIdField, sOldBranchIdField, sNewBranchIdField, pEmployeeIdField, pOldBranchIdField, pNewBranchIdField;

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";


    public UserHome() {
        setTitle("Employee Home");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Flight");

            // Process the result set if needed

            // Close the connection and statement when done
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Side Menu
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBackground(Color.BLACK);

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        JLabel welcomeLabel = new JLabel("WELCOME TO AMS!", SwingConstants.LEFT);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        signOutButton = new JButton("Sign out");

        headerPanel.add(welcomeLabel, BorderLayout.CENTER);
        headerPanel.add(signOutButton, BorderLayout.EAST);

        // Buttons
        baggageButton = createButton("Baggage");
        departureButton = createButton("Departure Details");
        supportButton = createButton("Passengers");
        pilotButton = createButton("Pilot Details");
        parkingButton = createButton("Parking Details");
        airlineButton = createButton("Airline Details");
        busyButton = createButton("Busiest Gate");
        reportButton = createButton("Report");
        employeeButton = createButton("Average Baggage Count");
        eButton = createButton("Employee Details");
        updateButton = createButton("Update Flight");


        sideMenu.add(Box.createVerticalGlue());
        sideMenu.add(baggageButton);
        sideMenu.add(departureButton);
        sideMenu.add(supportButton);
        sideMenu.add(pilotButton);
        sideMenu.add(parkingButton);
        sideMenu.add(airlineButton);
        sideMenu.add(busyButton);
        sideMenu.add(reportButton);
        sideMenu.add(employeeButton);
        sideMenu.add(eButton);
        sideMenu.add(updateButton);
        sideMenu.add(Box.createVerticalGlue());

        // Panels
        JPanel statementPanel = createBaggagePanel();
        JPanel cardPanel = createCardPanel();
        JPanel supportPanel = createOverbookedPanel();
        JPanel pilotPanel = createPilotPanel();
        JPanel parkingPanel = createParkingPanel();
        JPanel airlinePanel = createAirlinePanel();
        JPanel busyPanel = createBusyPanel();
        JPanel reportPanel = createReportPanel();
        JPanel employeePanel = createEmployeePanel();
        JPanel ePanel = createEPanel();
        JPanel updatePanel = createUpdatePanel();

        cards.add(statementPanel, "Statement");
        cards.add(cardPanel, "Card");
        cards.add(supportPanel, "Support");
        cards.add(pilotPanel, "Pilot");
        cards.add(parkingPanel, "Parking");
        cards.add(airlinePanel, "Airline");
        cards.add(busyPanel, "Busiest Gate");
        cards.add(reportPanel, "Report");
        cards.add(employeePanel, "Employee");
        cards.add(ePanel, "Employee Details");
        cards.add(updatePanel, "Update Flight");

        // Add action listeners to buttons
        baggageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Statement");
            }
        });
        departureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Card");
            }
        });
        supportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Support");
            }
        });
        pilotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Pilot");
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
                dispose();
            }
        });
        parkingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Parking");
            }
        });
        airlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Airline");
            }
        });
        busyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Busiest Gate");
            }
        });
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Report");
            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Employee");
            }
        });
        eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Employee Details");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Update Flight");
            }
        });

        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the flight ID entered by the user
                String flightID = employeeIdField.getText();

                // Validate flight ID (optional)
                if (flightID.isEmpty()) {
                    JOptionPane.showMessageDialog(UserHome.this, "Please enter a valid Gate ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Establish connection to the database
                    Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    Statement statement = connection.createStatement();

                    // Query to retrieve gate information based on flight ID
                    String query = "SELECT g.Gate_Number FROM Gate g WHERE g.Gate_ID = '" + flightID + "'";

                    // Execute the query
                    ResultSet resultSet = statement.executeQuery(query);

                    // Process the result set
                    if (resultSet.next()) {
                        // Get the gate number
                        int gateNumber = resultSet.getInt("Gate_Number");
                        // Display the gate number to the user (or perform any other action)
                        JOptionPane.showMessageDialog(UserHome.this, "Gate Number for Gate ID " + flightID + ": " + gateNumber, "Gate Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Flight ID not found
                        JOptionPane.showMessageDialog(UserHome.this, "Flight ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // Close the resources
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserHome.this, "Error occurred while retrieving gate information", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the flight ID entered by the user
                String passengerID = bEmployeeIdField.getText();
        
                // Validate flight ID (optional)
                if (passengerID.isEmpty()) {
                    JOptionPane.showMessageDialog(UserHome.this, "Please enter a valid Flight ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                try {
                Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                String query = "SELECT COUNT(*) AS baggageCount FROM Baggage b WHERE b.Passenger_ID = '" + passengerID + "'";

                ResultSet resultSet = statement.executeQuery(query);

                int baggageCount = 0;
                if (resultSet.next()) {
                    baggageCount = resultSet.getInt("baggageCount");
                }

                if (baggageCount > 0) {
                    JOptionPane.showMessageDialog(UserHome.this, "Passenger " + passengerID + " has " + baggageCount + " baggages.", "Baggage Count", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(UserHome.this, "Passenger " + passengerID + " has no baggage.", "No Baggage", JOptionPane.INFORMATION_MESSAGE);
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(UserHome.this, "Error occurred while executing query", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
            
        });
        dSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startTime = dEmployeeIdField.getText();
                String endTime = dOldBranchIdField.getText();
                
                // Validate input (you may add more validation as needed)
                if (startTime.isEmpty() || endTime.isEmpty()) {
                    JOptionPane.showMessageDialog(UserHome.this, "Please enter start and end times", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Execute SQL query
                try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
                    String query = "SELECT COUNT(DISTINCT P.Passenger_ID) AS Total_Passengers " +
                                   "FROM Passenger P " +
                                   "JOIN Tickets T ON P.Passenger_ID = T.Passenger_ID " +
                                   "JOIN Flight F ON T.Flight_ID = F.Flight_ID " +
                                   "WHERE F.Departure_time BETWEEN ? AND ?";
                    
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, startTime);
                        statement.setString(2, endTime);
                        
                        try (ResultSet resultSet = statement.executeQuery()) {
                            if (resultSet.next()) {
                                int totalPassengers = resultSet.getInt("Total_Passengers");
                                JOptionPane.showMessageDialog(UserHome.this, "Total passengers between " + startTime + " and " + endTime + ": " + totalPassengers, "Query Result", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(UserHome.this, "No passengers found for the specified time range", "Query Result", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserHome.this, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        sSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Establish database connection
                    Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        
                    // Create and execute the SQL query
                    String query = "SELECT FE.Flight_Employee_Name AS Pilot_Name, COUNT(F.Flight_ID) AS Flights_Flown_In_Week FROM flight_employees FE JOIN flight F ON FE.flight_employee_ID = F.Flight_Employee_ID WHERE FE.Flight_Employee_ID = ? AND FE.Position = 'Pilot' GROUP BY FE.Flight_Employee_Name";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, Integer.parseInt(sEmployeeIdField.getText())); // Assuming Flight_Employee_ID 2 is hardcoded
                    ResultSet resultSet = preparedStatement.executeQuery();
        
                    // Process the query result
                    StringBuilder resultMessage = new StringBuilder();
                    while (resultSet.next()) {
                        String pilotName = resultSet.getString("Pilot_Name");
                        int flightsFlown = resultSet.getInt("Flights_Flown_In_Week");
                        resultMessage.append("Pilot Name: ").append(pilotName).append(", Flights Flown in Week: ").append(flightsFlown).append("\n");
                    }
        
                    // Display the result to the user
                    if (resultMessage.length() > 0) {
                        JOptionPane.showMessageDialog(UserHome.this, resultMessage.toString(), "Pilots and Flights Flown in Week", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(UserHome.this, "No data found for the specified criteria", "No Data", JOptionPane.INFORMATION_MESSAGE);
                    }
        
                    // Close database resources
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserHome.this, "Error occurred while executing query", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Adding Components to Frame
        add(headerPanel, BorderLayout.NORTH);
        add(sideMenu, BorderLayout.WEST);
        add(cards, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
        button.setForeground(Color.WHITE); // Set the text color to white for visibility
        button.setBackground(Color.BLACK); // Match the background color of side menu
        button.setFocusPainted(false);
        return button;
    }
    
    private JPanel createPanel(String text) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(text));
        return panel;
    }


        
    
    private JPanel createCardPanel() {
        JPanel transferPanel = new JPanel();
        transferPanel.setLayout(new BoxLayout(transferPanel, BoxLayout.Y_AXIS));
        transferPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        employeeIdField = new JTextField();
        employeeIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        oldBranchIdField = new JTextField();
        oldBranchIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        newBranchIdField = new JTextField();
        newBranchIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        setButton = new JButton("GET GATE");
        
    
        transferPanel.add(createLabeledField("Gate ID", employeeIdField));
        transferPanel.add(Box.createVerticalStrut(10));
        transferPanel.add(createButtonPanel(setButton));
    
        return transferPanel;
    }
    

    private JPanel createBaggagePanel() {
        JPanel transferPanel = new JPanel();
        transferPanel.setLayout(new BoxLayout(transferPanel, BoxLayout.Y_AXIS));
        transferPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        bEmployeeIdField = new JTextField();
        bEmployeeIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        bOldBranchIdField = new JTextField();
        bOldBranchIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        bNewBranchIdField = new JTextField();
        bNewBranchIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        bSetButton = new JButton("GET NUMBER OF BAGGAGE");
        
    
        transferPanel.add(createLabeledField("Passenger ID", bEmployeeIdField));
        transferPanel.add(Box.createVerticalStrut(10));
        transferPanel.add(createButtonPanel(bSetButton));
    
        return transferPanel;
    }

    private JPanel createOverbookedPanel()
    {
        JPanel transferPanel = new JPanel();
        transferPanel.setLayout(new BoxLayout(transferPanel, BoxLayout.Y_AXIS));
        transferPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        dEmployeeIdField = new JTextField();
        dEmployeeIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        dOldBranchIdField = new JTextField();
        dOldBranchIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        dNewBranchIdField = new JTextField();
        dNewBranchIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        dSetButton = new JButton("GET FLIGHTS");
        
    
        transferPanel.add(createLabeledField("Start Time", dEmployeeIdField));
        transferPanel.add(Box.createVerticalStrut(10));
        transferPanel.add(createLabeledField("End Time",dOldBranchIdField));
        transferPanel.add(Box.createVerticalStrut(10));
        transferPanel.add(createButtonPanel(dSetButton));
    
        return transferPanel;
    }
    private JPanel createPilotPanel()
    {
        JPanel transferPanel = new JPanel();
        transferPanel.setLayout(new BoxLayout(transferPanel, BoxLayout.Y_AXIS));
        transferPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        sEmployeeIdField = new JTextField();
        sEmployeeIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        sOldBranchIdField = new JTextField();
        sOldBranchIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        sNewBranchIdField = new JTextField();
        sNewBranchIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
        sSetButton = new JButton("GET NO OF HOURS FLOWN IN A WEEK");
        
    
        transferPanel.add(createLabeledField("Employee ID", sEmployeeIdField));
        transferPanel.add(Box.createVerticalStrut(10));
        transferPanel.add(createButtonPanel(sSetButton));
    
        return transferPanel;
    }

    private JPanel createParkingPanel()
    {
        JPanel revenuePanel = new JPanel();
    revenuePanel.setLayout(new BoxLayout(revenuePanel, BoxLayout.Y_AXIS));
    revenuePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

    JLabel titleLabel = new JLabel("Hourly Revenue Report");
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Create text area to display hourly revenue report
    JTextArea reportTextArea = new JTextArea();
    reportTextArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(reportTextArea);
    scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

    JButton getRevenueButton = new JButton("GET HOURLY REVENUE");
    getRevenueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    getRevenueButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Establish database connection
                Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    
                // Create and execute the SQL query
                String query = "SELECT HOUR(p.Entry_Time) AS Hour, SUM(p.Rev_Collected) AS Total_Revenue FROM Parking p JOIN Revenue r ON p.Rev_ID = r.Rev_ID WHERE r.Revenue_Type = 'Parking Fees' GROUP BY HOUR(p.Entry_Time)";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
    
                // Process the query result
                StringBuilder resultMessage = new StringBuilder();
                while (resultSet.next()) {
                    int hour = resultSet.getInt("Hour");
                    double totalRevenue = resultSet.getDouble("Total_Revenue");
                    resultMessage.append("Hour: ").append(hour).append(", Total Revenue: ").append(totalRevenue).append("\n");
                }
    
                // Display the result to the user
                if (resultMessage.length() > 0) {
                    JOptionPane.showMessageDialog(UserHome.this, resultMessage.toString(), "Hourly Parking Revenue", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(UserHome.this, "No data found for the specified criteria", "No Data", JOptionPane.INFORMATION_MESSAGE);
                }
    
                // Close database resources
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(UserHome.this, "Error occurred while executing query", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        }
    });
    

    // Add components to panel
    revenuePanel.add(titleLabel);
    revenuePanel.add(Box.createVerticalStrut(10));
    revenuePanel.add(scrollPane);
    revenuePanel.add(Box.createVerticalStrut(10));
    revenuePanel.add(getRevenueButton);

    return revenuePanel;

    }

    private JPanel createAirlinePanel()
    {
        JPanel airlinePanel = new JPanel();
        airlinePanel.setLayout(new BoxLayout(airlinePanel, BoxLayout.Y_AXIS));
        airlinePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
    
        JLabel titleLabel = new JLabel("Average Passengers per Flight by Airline");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        final JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JButton getAverageButton = new JButton("GET AVERAGE PASSENGERS");
        getAverageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        getAverageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    Statement statement = connection.createStatement();
                    String query = "SELECT A.Airline_Name, AVG(PassengerCount) AS Avg_Passengers_Per_Flight FROM Airline A LEFT JOIN ( SELECT F.Airline_ID, COUNT(DISTINCT T.Passenger_ID) AS PassengerCount FROM Flight F LEFT JOIN Tickets T ON F.Flight_ID = T.Flight_ID GROUP BY F.Airline_ID, F.Flight_ID ) AS PassengerCounts ON A.Airline_ID = PassengerCounts.Airline_ID GROUP BY A.Airline_Name;";
                    ResultSet resultSet = statement.executeQuery(query);
    
                    // Clear existing result
                    resultTextArea.setText("");
    
                    // Display result in text area
                    while (resultSet.next()) {
                        String airlineName = resultSet.getString("Airline_Name");
                        double avgPassengers = resultSet.getDouble("Avg_Passengers_Per_Flight");
                        resultTextArea.append("Airline: " + airlineName + ", Average Passengers per Flight: " + avgPassengers + "\n");
                    }
    
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserHome.this, "Error occurred while retrieving average passengers per flight", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        airlinePanel.add(titleLabel);
        airlinePanel.add(Box.createVerticalStrut(10));
        airlinePanel.add(scrollPane);
        airlinePanel.add(Box.createVerticalStrut(10));
        airlinePanel.add(getAverageButton);
    
        return airlinePanel;
    
    }
    private JPanel createBusyPanel()
    {
        JPanel busyPanel = new JPanel();
        busyPanel.setLayout(new BoxLayout(busyPanel, BoxLayout.Y_AXIS));
        busyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
    
        JLabel titleLabel = new JLabel("Busiest Gate");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        final JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JButton getAverageButton = new JButton("GET BUSIEST GATE");
        getAverageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        getAverageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    Statement statement = connection.createStatement();
                    String query = "SELECT G.Gate_Number, COUNT(F.Flight_ID) AS Flights_Scheduled FROM Gate G LEFT JOIN Flight F ON G.Gate_ID = F.Gate_ID GROUP BY G.Gate_Number ORDER BY Flights_Scheduled DESC LIMIT 1";
                    ResultSet resultSet = statement.executeQuery(query);
    
                    // Clear existing result
                    resultTextArea.setText("");
    
                    // Display result in text area
                    if (resultSet.next()) {
                        int gateNumber = resultSet.getInt("Gate_Number");
                        int flightsScheduled = resultSet.getInt("Flights_Scheduled");
                        resultTextArea.append("Busiest Gate: " + gateNumber + ", Flights Scheduled: " + flightsScheduled + "\n");
                    }
    
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserHome.this, "Error occurred while retrieving busiest gate", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        busyPanel.add(titleLabel);
        busyPanel.add(Box.createVerticalStrut(10));
        busyPanel.add(scrollPane);
        busyPanel.add(Box.createVerticalStrut(10));
        busyPanel.add(getAverageButton);
    
        return busyPanel;
    }
    private JPanel createReportPanel()
    {
        JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
    cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
    
    // Create components

    JButton getRevenueButton = new JButton("Get Revenue");
    final JTextArea revenueInfoTextArea = new JTextArea(10, 20);
    revenueInfoTextArea.setEditable(false);
    
    // Add action listener to the button
    getRevenueButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                
                String query = "SELECT * FROM Revenue ";
                
                ResultSet resultSet = statement.executeQuery(query);
                
                StringBuilder message = new StringBuilder();
                while (resultSet.next()) {
                    // Assuming Revenue table has columns: Rev_ID, Revenue_Type, Rev_Collected, Revenue_Date
                    int revID = resultSet.getInt("Rev_ID");
                    String revenueType = resultSet.getString("Revenue_Type");
                    int revenueCollected = resultSet.getInt("Rev_Collected");
                    
                    message.append("Rev ID: ").append(revID).append(", ");
                    message.append("Type: ").append(revenueType).append(", ");
                    message.append("Collected: ").append(revenueCollected).append(", ");
                    message.append("\n");
                }
                
                revenueInfoTextArea.setText(message.toString());
                
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(UserHome.this, "Error occurred while executing query", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    
    // Add components to the panel
    cardPanel.add(getRevenueButton);
    cardPanel.add(revenueInfoTextArea);
    
    return cardPanel;

    }
    private JPanel createEmployeePanel()
    {
        JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
    cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
    
    // Create components
    JButton getAverageButton = new JButton("Get Average Baggage Count");
    JLabel averageLabel = new JLabel("Average Baggage Count per Passenger:");
    final JTextField averageTextField = new JTextField(10);
    averageTextField.setEditable(false);
    
    // Add action listener to the button
    getAverageButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                
                String query = "SELECT AVG(baggage_count) AS Avg_Baggage_Per_Passenger " +
                               "FROM ( " +
                               "    SELECT p.Passenger_ID, COUNT(b.Baggage_ID) AS baggage_count " +
                               "    FROM Passenger p " +
                               "    LEFT JOIN Baggage b ON p.Passenger_ID = b.Passenger_ID " +
                               "    GROUP BY p.Passenger_ID " +
                               ") AS baggage_counts";
                
                ResultSet resultSet = statement.executeQuery(query);
                
                if (resultSet.next()) {
                    double averageBaggageCount = resultSet.getDouble("Avg_Baggage_Per_Passenger");
                    averageTextField.setText(String.valueOf(averageBaggageCount));
                } else {
                    averageTextField.setText("N/A");
                }
                
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(UserHome.this, "Error occurred while executing query", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    
    // Add components to the panel
    cardPanel.add(getAverageButton);
    cardPanel.add(averageLabel);
    cardPanel.add(averageTextField);
    
    return cardPanel;

    }
    private JPanel createEPanel()
    {
        JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
    cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
    
    // Create components
    JButton getTopEmployeesButton = new JButton("Get Top Employees");
    final JTextArea topEmployeesInfoTextArea = new JTextArea(10, 20);
    topEmployeesInfoTextArea.setEditable(false);
    
    // Add action listener to the button
    getTopEmployeesButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                
                String query = "SELECT fe.Flight_Employee_Name, COUNT(f.Flight_ID) AS Flights_Worked " +
                               "FROM Flight_Employees fe " +
                               "JOIN Flight f ON fe.Flight_Employee_ID = f.Flight_Employee_ID " +
                               "GROUP BY fe.Flight_Employee_Name " +
                               "ORDER BY Flights_Worked DESC " +
                               "LIMIT 5";
                
                ResultSet resultSet = statement.executeQuery(query);
                
                StringBuilder message = new StringBuilder();
                while (resultSet.next()) {
                    String employeeName = resultSet.getString("Flight_Employee_Name");
                    int flightsWorked = resultSet.getInt("Flights_Worked");
                    
                    message.append("Employee Name: ").append(employeeName).append(", ");
                    message.append("Flights Worked: ").append(flightsWorked).append("\n");
                }
                
                topEmployeesInfoTextArea.setText(message.toString());
                
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(UserHome.this, "Error occurred while executing query", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    
    // Add components to the panel
    cardPanel.add(getTopEmployeesButton);
    cardPanel.add(topEmployeesInfoTextArea);
    
    return cardPanel;

    }
    private JPanel createUpdatePanel()
    {
            JPanel transferPanel = new JPanel();
            transferPanel.setLayout(new BoxLayout(transferPanel, BoxLayout.Y_AXIS));
            transferPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
            pEmployeeIdField = new JTextField();
            pEmployeeIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
            pOldBranchIdField = new JTextField();
            pOldBranchIdField.setMaximumSize(new Dimension(150, 20)); // Thinner width
            JButton uButton = new JButton("ADD AIRLINE");
            // Thinner width
            
            
        
            transferPanel.add(createLabeledField("Airline ID", pEmployeeIdField));
            transferPanel.add(createLabeledField("New Airline Name", pOldBranchIdField));
            transferPanel.add(Box.createVerticalStrut(10));
            transferPanel.add(createButtonPanel(uButton));

            
            uButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String airlineId = pEmployeeIdField.getText();
                        String airlineName = pOldBranchIdField.getText();

                        Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        String query = "INSERT INTO Airline (Airline_ID, Airline_Name) VALUES (?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, airlineId);
                        preparedStatement.setString(2, airlineName);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(UserHome.this, "Airline added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(UserHome.this, "Error occurred while adding airline", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        
            return transferPanel;
    }

    private JPanel createLabeledField(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JPanel createButtonPanel(JButton button) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(button);
        return buttonPanel;
    }

   

// Run the application
public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            UserHome app = new UserHome();
            app.setVisible(true);
        }
    });

}
}
