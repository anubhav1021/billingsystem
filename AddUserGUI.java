package billingsystempackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddUserGUI extends JFrame {
    private JTextField nameField, emailField;
    private JButton submitButton;
    private JLabel statusLabel;

    public AddUserGUI() {
        setTitle("Add User to Database");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        nameField = new JTextField();
        emailField = new JTextField();
        submitButton = new JButton("Add User");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        add(new JLabel("Enter Name:", SwingConstants.CENTER));
        add(nameField);
        add(new JLabel("Enter Email:", SwingConstants.CENTER));
        add(emailField);
        add(submitButton);
        add(statusLabel);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                if (!name.isEmpty() && !email.isEmpty()) {
                    boolean success = insertIntoDatabase(name, email);
                    if (success) {
                        statusLabel.setText("✅ Data added successfully!");
                        nameField.setText("");
                        emailField.setText("");
                    } else {
                        statusLabel.setText("❌ Failed to add data.");
                    }
                } else {
                    statusLabel.setText("⚠️ Please fill in all fields.");
                }
            }
        });
    }

    private boolean insertIntoDatabase(String name, String email) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";           // replace with your username
        String password = "1106";   // replace with your password

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // Load JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found.");
            return;
        }

        // Show GUI
        SwingUtilities.invokeLater(() -> {
            AddUserGUI gui = new AddUserGUI();
            gui.setVisible(true);
        });
    }
}
