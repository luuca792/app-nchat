/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luuca.appchat.client;

import controllers.LoginHelper;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author luuca
 */
public final class Login extends JPanel { //card 1
    
    AppChatClient frame;
    
    JPanel loginForm;
    JLabel lblUser;
    JLabel lblPassword;
    JPanel titlePanel;
    JLabel title;
    JTextField tfUser;
    JPasswordField tfPassword;
    JButton btnLogin;
    JButton btnSignup;
    
    LoginHelper action;
    private int credentialState  = -1;
    
    public Login(AppChatClient frame){
        this.frame = frame;
        action = new LoginHelper(frame, this);
        initComponent();
    }
    public void initComponent(){
        this.setLayout(null);
        
        loginForm = new JPanel();
        loginForm.setBounds(70,250,260,60);
        this.add(loginForm);
        
        titlePanel = new JPanel();
        titlePanel.setBounds(0,50,400,50);
        title = new JLabel("NCHAT");
        titlePanel.add(title);
        this.add(titlePanel);
        
        loginForm.setLayout(new GridLayout(2,2));
        
        lblUser = new JLabel("Username: ");
        lblUser.setSize(50,50);
        
        lblPassword = new JLabel("Password: ");
        lblPassword.setSize(50,50);
        
        tfUser = new JTextField();
        tfPassword = new JPasswordField();
        tfPassword.setEchoChar('*');
        
        loginForm.add(lblUser);
        loginForm.add(tfUser);
        loginForm.add(lblPassword);
        loginForm.add(tfPassword);
        
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(action);
        btnLogin.setBounds(160,320,80,30);
        this.add(btnLogin);
        
        btnSignup = new JButton("Signup");
        btnSignup.addActionListener(action);
        btnSignup.setBounds(160,360,80,30);
        this.add(btnSignup);
    }
    public JButton getBtnLogin(){
        return btnLogin;
    }
    public JButton getBtnSignup(){
        return btnSignup;
    }
    public JTextField getTfUser(){
        return tfUser;
    }
    public JTextField getTfPassword(){
        return tfPassword;
    }

    public int getCredentialState() {
        return credentialState;
    }

    public void setCredentialState(int credentialState) {
        this.credentialState = credentialState;
    }
    
}
