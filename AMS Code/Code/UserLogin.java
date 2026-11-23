package com.ams;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserLogin extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/ams";
    static final String USERNAME = "root";
    static final String PASSWORD = "root@123";

    public UserLogin() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Employee Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the login form
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        loginPanel.setBackground(new Color(32, 32, 32)); // Dark background color

        // Add components to the login panel
        final JTextField nameField = createTextField("User ID");
        final JTextField emailField = createTextField("Email ID");

        JButton signInButton = new JButton("Sign in");
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeID = nameField.getText();
                String email = emailField.getText();

                if (employeeExists(employeeID, email)) {
                    UserHome userHome = new UserHome();
                    userHome.setVisible(true);
                    dispose(); // Close the login frame
                } else {
                    JOptionPane.showMessageDialog(UserLogin.this, "Invalid Employee ID or Email", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Other components...
        
        loginPanel.add(nameField);
        loginPanel.add(emailField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        loginPanel.add(signInButton);
        
        // Add the login panel to the frame
        add(loginPanel, BorderLayout.CENTER);
    }

    // Method to check if employee exists in the database
    private boolean employeeExists(String employeeID, String email) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Flight_Employees WHERE Flight_Employee_ID = ?");
        ) {
            statement.setString(1, employeeID);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField(15);
        styleTextField(textField, placeholder);
        return textField;
    }

    private void styleTextField(JComponent field, String placeholder) {
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        ((JTextField) field).setForeground(Color.GRAY);
        final String finalPlaceholder = placeholder;
        ((JTextField) field).setText(finalPlaceholder);
        ((JTextField) field).addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (((JTextField) e.getSource()).getText().equals(finalPlaceholder)) {
                    ((JTextField) e.getSource()).setText("");
                    ((JTextField) e.getSource()).setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (((JTextField) e.getSource()).getText().isEmpty()) {
                    ((JTextField) e.getSource()).setForeground(Color.GRAY);
                    ((JTextField) e.getSource()).setText(finalPlaceholder);
                }
            }
        });
        // Styling code...
    }

    // Other methods...
    private JButton createIconButton(Color color, char iconLetter) {
        JButton button = new JButton(Character.toString(iconLetter)); // Placeholder for icon
        button.setForeground(color);
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        return button;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserLogin frame = new UserLogin();
                frame.setVisible(true);
            }
        });
    }
}
