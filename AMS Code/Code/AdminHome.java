package com.ams;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHome extends JFrame {
    private JButton userLoginButton;
    private JButton adminLoginButton;
    private JButton baggageButton;
    private JButton flightButton;
    private JButton passengerButton;
    private JButton raButton;
    private JButton ticketButton;
    private JButton opButton;
    private JButton usesButton;
    private JButton gsButton;
    private JButton parkingButton;
    private JButton revenueButton;
    private JButton smButton;

    JButton createIconButton(Color color, char iconLetter) {
        JButton button = new JButton(Character.toString(iconLetter)); // Placeholder for icon
        button.setForeground(color);
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        return button;
    }

    public AdminHome() {
        setTitle("Welcome Admin!");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(new Color(32, 32, 32));

        userLoginButton = new JButton("Airline");
        adminLoginButton = new JButton("Employee");
        baggageButton = new JButton("Baggage");
        flightButton = new JButton("Flight");
        gsButton = new JButton("Gate");
        opButton = new JButton("Operates");
        parkingButton = new JButton("Parking");
        passengerButton = new JButton("Passenger");
        raButton = new JButton("Restricted Area");
        revenueButton = new JButton("Revenue");
        smButton = new JButton("Security Management");
        ticketButton = new JButton("Ticket");
        usesButton = new JButton("ATC");




        // Add action listeners for buttons here...
        
        JPanel userLoginPanel = new JPanel();
        userLoginPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        userLoginPanel.add(userLoginButton);
        userLoginPanel.add(adminLoginButton);
        userLoginPanel.add(baggageButton);
        userLoginPanel.add(flightButton);
        userLoginPanel.add(gsButton);
        userLoginPanel.add(opButton);
        userLoginPanel.add(parkingButton);
        userLoginPanel.add(passengerButton);
        userLoginPanel.add(raButton);
        userLoginPanel.add(revenueButton);
        userLoginPanel.add(smButton);
        userLoginPanel.add(ticketButton);
        userLoginPanel.add(usesButton);
        userLoginPanel.setOpaque(false);


         userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Airline airlineFrame = new Airline();
                airlineFrame.setVisible(true);
                
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightEmployee adminLoginFrame = new FlightEmployee();
                adminLoginFrame.setVisible(true);
                
            }
        });
        baggageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Baggage baggageFrame = new Baggage();
                baggageFrame.setVisible(true);
                
            }
        });
        flightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight flightFrame = new Flight();
                flightFrame.setVisible(true);
                
            }
        });
        gsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroundHandlingService gateFrame = new GroundHandlingService();
                gateFrame.setVisible(true);
             
            }
        });

        opButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Operates operatesFrame = new Operates();
                operatesFrame.setVisible(true);
             
            }
        });

        parkingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parking parkingFrame = new Parking();
                parkingFrame.setVisible(true);
               
            }
        });

        passengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Passenger passengerFrame = new Passenger();
                passengerFrame.setVisible(true);
              
            }
        });
        flightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight flightFrame = new Flight();
                flightFrame.setVisible(true);
              
            }
        });
        raButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestrictedArea raFrame = new RestrictedArea();
                raFrame.setVisible(true);
               
            }
        });

        revenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Revenue revenueFrame = new Revenue();
                revenueFrame.setVisible(true);
                
            }
        });
        smButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SecurityManagement smFrame = new SecurityManagement();
                smFrame.setVisible(true);
                
            }
        });
        ticketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket ticketFrame = new Ticket();
                ticketFrame.setVisible(true);
                
            }
        });
        usesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AirTrafficControl usesFrame = new AirTrafficControl();
                usesFrame.setVisible(true);
                
            }
        });

        
        
        

        
    


        JPanel socialPanel = new JPanel();
        socialPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        socialPanel.add(createIconButton(new Color(0, 172, 237), 'f')); // Facebook icon placeholder
        socialPanel.add(createIconButton(new Color(29, 161, 242), 't')); // Twitter icon placeholder
        socialPanel.add(createIconButton(new Color(194, 48, 39), 'g')); // Google icon placeholder
        socialPanel.setOpaque(false);

        // Adding wrapped buttons and social panel to the frame
        add(Box.createVerticalGlue());
        add(userLoginPanel);
        add(Box.createVerticalGlue());

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame mainWindow = new LoginFrame();
                mainWindow.setLocationRelativeTo(null);
                mainWindow.setVisible(true);
            }
        });
    }
}