package UI;

import Backend.GameControler;
import Backend.ButtonResponders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Register extends JFrame implements ActionListener {

    Container container = getContentPane();
    private JLabel userLabel = new JLabel("USERNAME");
    private JLabel passwordLabel = new JLabel("PASSWORD");
    private JTextField userTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("LOGIN");
    private JButton resetButton = new JButton("RESET");
    private JButton signUp = new JButton("SIGN UP");
    private JButton saveMethodButton = new JButton("SAVE TO MONGODB");
    private JCheckBox showPassword = new JCheckBox("Show Password");

    private JLabel background = new JLabel(new ImageIcon("EscapeFromKoc/resources/EscapeFomKoc.png"));

    public Register() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        signUp.setBounds(125, 300, 100, 30);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(30, 300, 100, 30);
        resetButton.setBounds(220, 300, 100, 30);
        saveMethodButton.setBounds(30, 350, 300, 30);
        background.setBounds(0, 0, 900, 950);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(saveMethodButton);
        container.add(signUp);
        container.add(background);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        signUp.addActionListener(this);
        saveMethodButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            boolean loginSuccess = false;
            try {
                loginSuccess = ButtonResponders.LoginButton(userTextField.getText(), passwordField.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (loginSuccess) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                // ScreenCoordinator.startGame();
                GameControler.buildGame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                userTextField.setText("");
                passwordField.setText("");
            }
        }

        // Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
        if (e.getSource() == signUp) {
            boolean signUpSuccess = false;
            try {
                signUpSuccess = ButtonResponders.SignUpButton(userTextField.getText(), passwordField.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (signUpSuccess == false) {
                JOptionPane.showMessageDialog(this, "Username already exists");
            }
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == saveMethodButton) {
            GameControler.setSaveMethod("MongoDB");
            JOptionPane.showMessageDialog(this, "Save method changed to MongoDB");
        }
    }
}
