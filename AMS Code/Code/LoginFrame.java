package com.ams;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JButton userLoginButton;
    private JButton adminLoginButton;

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

    public LoginFrame() {
        setTitle("Airline Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(new Color(32, 32, 32));

        userLoginButton = new JButton("Employee Login");
        adminLoginButton = new JButton("Admin Login");

        // Add action listeners for buttons here...
        
        JPanel userLoginPanel = new JPanel();
        userLoginPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        userLoginPanel.add(userLoginButton);
        userLoginPanel.add(adminLoginButton);
        userLoginPanel.setOpaque(false);


         userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLogin userLoginFrame = new UserLogin();
                userLoginFrame.setVisible(true);
                dispose();
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin adminLoginFrame = new AdminLogin();
                adminLoginFrame.setVisible(true);
                dispose();
            }
        });

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