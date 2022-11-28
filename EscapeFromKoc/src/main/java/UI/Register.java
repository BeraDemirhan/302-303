package UI;

import Backend.ButtonResponders;
import Backend.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class Register extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JButton signUp = new JButton("SIGN UP");
    JCheckBox showPassword = new JCheckBox("Show Password");
    private boolean LoginSuccess = false;


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


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(signUp);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        signUp.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            boolean loginSuccess = false;
            try {
                loginSuccess = ButtonResponders.LoginButton(userTextField.getText(), passwordField.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (loginSuccess) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                GameManager.startGame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                userTextField.setText("");
                passwordField.setText("");
            }
        }

        //Coding Part of RESET button
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
    }
}


