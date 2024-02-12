package com.firsttime_pizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public LoginFrame() {
        setTitle("Login to Bubble Boulevard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        usernameField.setToolTipText("Username");
        passwordField.setToolTipText("Password");

        Set<AWTKeyStroke> forwardKeys = new HashSet<>(passwordField.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
        passwordField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);

        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");

        Action loginAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        };

        Action signupAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSignUpDialog();
            }
        };

        loginButton.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "login");
        loginButton.getActionMap().put("login", loginAction);
        signupButton.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "signup");
        signupButton.getActionMap().put("signup", signupAction);

        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.requestFocusInWindow();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        buttonPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (loginButton.isFocusOwner()) {
                        performLogin();
                    } else if (signupButton.isFocusOwner()) {
                        showSignUpDialog();
                    }
                }
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);

        setFocusTraversalPolicy(new FocusTraversalPolicy() {
            @Override
            public Component getComponentAfter(Container aContainer, Component aComponent) {
                return null;
            }

            @Override
            public Component getComponentBefore(Container aContainer, Component aComponent) {
                return null;
            }

            @Override
            public Component getFirstComponent(Container aContainer) {
                return usernameField;
            }

            @Override
            public Component getLastComponent(Container aContainer) {
                return signupButton;
            }

            @Override
            public Component getDefaultComponent(Container aContainer) {
                return usernameField;
            }
        });

        getRootPane().setDefaultButton(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();
                String enteredPasswordStr = new String(enteredPassword);
                if (DrinksApplication.userExists(enteredUsername, enteredPasswordStr)) {
                    dispose();
                    Registration frame = new Registration();
                    frame.setSize(510, 600);
                    frame.setResizable(false);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSignUpDialog();
            }
        });

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signupButton);
        add(loginPanel, BorderLayout.CENTER);
    }

    private void performLogin() {
        String enteredUsername = usernameField.getText();
        char[] enteredPassword = passwordField.getPassword();
        String enteredPasswordStr = new String(enteredPassword);

        if (DrinksApplication.userExists(enteredUsername, enteredPasswordStr)) {
            dispose();
            Registration frame = new Registration();
            frame.setSize(510, 600);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    private void showSignUpDialog() {
        String name = JOptionPane.showInputDialog(null, "Enter your name:", "Sign Up", JOptionPane.PLAIN_MESSAGE);
        String password = JOptionPane.showInputDialog(null, "Create a password:", "Sign Up", JOptionPane.PLAIN_MESSAGE);
        DrinksApplication.addUserAccount(name, password);
    }
}
