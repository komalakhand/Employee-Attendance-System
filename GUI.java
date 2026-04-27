import java.io.*;
import java.time.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI {

    static HashMap<Integer, Attendance> records = new HashMap<>();
    static DefaultTableModel model;

    static void saveToFile(int id, String name, String login, String logout, long hours, String status) {
        try {
            FileWriter fw = new FileWriter("attendance.txt", true);
            fw.write(id + "," + name + "," + login + "," + logout + "," + hours + "," + status + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("File Error");
        }
    }
    

    public static void main(String[] args) {

        JFrame frame = new JFrame("Employee Attendance System");
        frame.getContentPane().setBackground(new java.awt.Color(220, 240, 255));
        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
nameLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        idLabel.setBounds(50, 20, 100, 30);
        frame.add(idLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(300, 20, 100, 30);
        frame.add(nameLabel);

        // Fields
        JTextField idField = new JTextField();
        idField.setBounds(150, 20, 120, 30);
        frame.add(idField);

        JTextField nameField = new JTextField();
        nameField.setBounds(350, 20, 150, 30);
        frame.add(nameField);

        // Buttons
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
logoutBtn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        loginBtn.setBackground(new java.awt.Color(0, 180, 0));
loginBtn.setForeground(java.awt.Color.WHITE);

logoutBtn.setBackground(new java.awt.Color(200, 50, 50));
logoutBtn.setForeground(java.awt.Color.WHITE);
        loginBtn.setBounds(100, 70, 120, 40);
        frame.add(loginBtn);
        

        JButton logoutBtn = new JButton("Logout");
        loginBtn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
logoutBtn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        JButton clearBtn = new JButton("Clear");
clearBtn.setBounds(230, 70, 120, 40);
frame.add(clearBtn);
        logoutBtn.setBounds(300, 70, 120, 40);
        frame.add(logoutBtn);
        

        // Table
        String[] columns = {"Date", "ID", "Name", "Login Time", "Logout Time", "Hours", "Status"};
        model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 150, 500, 250);
        frame.add(pane);

        // LOGIN ACTION
        loginBtn.addActionListener(e ->
            clearBtn.addActionListener(e -> {
    idField.setText("");
    nameField.setText("");
}); {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();

                Attendance att = new Attendance();
                att.empId = id;
                att.login();

                records.put(id, att);

                LocalDate today = LocalDate.now();

                model.addRow(new Object[]{
                        today, id, name, att.loginTime, "-", "-",
                        att.loginTime.isAfter(LocalTime.of(9, 0)) ? "Late" : "On Time"
                });

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input!");
            }
        });

        // LOGOUT ACTION
        logoutBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                Attendance att = records.get(id);

                if (att != null) {
                    att.logout();

                    long minutes = Duration.between(att.loginTime, att.logoutTime).toMinutes();
                    double hours = minutes / 60.0;

                    String status;
                    if (hours >= 8) status = "Present";
                    else if (hours >= 4) status = "Half Day";
                    else if (hours > 0) status = "Short Time";
                    else status = "Absent";

                    // Save to file
                    saveToFile(id, nameField.getText(),
                            att.loginTime.toString(),
                            att.logoutTime.toString(),
                            (long) hours,
                            status);

                    String formattedHours = String.format("%.2f", hours);

                    // Update table
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if ((int) model.getValueAt(i, 1) == id) {
                            model.setValueAt(att.logoutTime, i, 4);
                            model.setValueAt(formattedHours, i, 5);
                            model.setValueAt(status, i, 6);
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "Login First!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error!");
            }
        });

        frame.setVisible(true);
    }
}