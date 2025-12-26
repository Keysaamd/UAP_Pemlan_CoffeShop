package org.example.Page;

import org.example.MainFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Login extends JPanel {
    public Login(MainFrame frame) {
        setLayout(new GridBagLayout());
        setBackground(new Color(51, 36, 33));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("COFFEE SHOP LOGIN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26)); // Font lebih modern
        lblTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0); // Jarak bawah judul lebih besar
        add(lblTitle, gbc);

        gbc.insets = new Insets(5, 5, 5, 5); // Reset insets
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel lblUser = new JLabel("Username");
        lblUser.setForeground(new Color(200, 200, 200)); // Warna abu muda agar lebih soft
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(lblUser, gbc);

        JTextField txtUser = new JTextField(15);
        txtUser.setPreferredSize(new Dimension(200, 30));
        txtUser.setBorder(BorderFactory.createCompoundBorder(
                txtUser.getBorder(), new EmptyBorder(2, 5, 2, 5))); // Padding dalam teks
        gbc.gridx = 1;
        add(txtUser, gbc);

        // --- PASSWORD ---
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblPass = new JLabel("Password");
        lblPass.setForeground(new Color(200, 200, 200));
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(lblPass, gbc);

        JPasswordField txtPassword = new JPasswordField(15);
        txtPassword.setPreferredSize(new Dimension(200, 30));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                txtPassword.getBorder(), new EmptyBorder(2, 5, 2, 5)));
        gbc.gridx = 1;
        add(txtPassword, gbc);

        // --- LOGIN BUTTON ---
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(245, 245, 235));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setPreferredSize(new Dimension(0, 40));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 0, 0, 0);
        add(btnLogin, gbc);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPassword.getPassword());

            if (user.equals("admin") && pass.equals("123")) {
                frame.switchPage("Dashboard");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Username atau Password salah!!", "Login Gagal!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}