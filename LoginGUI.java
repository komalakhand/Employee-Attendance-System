import javax.swing.*;
import java.awt.*;

public class LoginGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Admin Login");
        frame.setSize(320, 220);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background color
        frame.getContentPane().setBackground(new Color(220, 240, 255));

        // Title Label
        JLabel title = new JLabel("Admin Login");
        title.setBounds(90, 5, 150, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(title);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 40, 80, 30);
        userLabel.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(120, 40, 140, 30);
        frame.add(userField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 80, 80, 30);
        passLabel.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(120, 80, 140, 30);
        frame.add(passField);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 130, 100, 35);
        loginBtn.setBackground(new Color(0, 150, 0));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(loginBtn);

        // Login Action
        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                frame.dispose();
                GUI.main(null);
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong Credentials!");
            }
        });

        // ENTER key support (important UX)
        frame.getRootPane().setDefaultButton(loginBtn);

        // Center screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}