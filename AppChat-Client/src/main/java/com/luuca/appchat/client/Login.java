/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luuca.appchat.client;

import controllers.LoginHelper;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
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
    
    JLabel login;
    
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
        
        login = new JLabel();
        login.setBounds(0,0,400,600);
        ImageIcon onlineTabBG = new ImageIcon("src/main/resources/images/login.png");
        login.setIcon(onlineTabBG);
        
        this.add(login);
        
        loginForm = new JPanel();
        loginForm.setBounds(70,200,260,50);
        loginForm.setLayout(new GridLayout(2,2));
        login.add(loginForm);
        
        titlePanel = new JPanel();
        titlePanel.setBounds(0,20,400,150);
        titlePanel.setOpaque(false);
        title = new JLabel();
        ImageIcon nChat = new ImageIcon("src/main/resources/images/NChat.png");
        title.setIcon(nChat);
        titlePanel.add(title);
        login.add(titlePanel);
        
        
        
        lblUser = new JLabel("Username: ");
        lblUser.setSize(50,50);
        lblUser.setOpaque(false);
        
        lblPassword = new JLabel("Password: ");
        lblPassword.setSize(50,50);
        
        tfUser = new JTextField();
        tfPassword = new JPasswordField();
//        tfPassword.setEchoChar('*');
        
        loginForm.add(lblUser);
        loginForm.add(tfUser);
        loginForm.add(lblPassword);
        loginForm.add(tfPassword);
        loginForm.setOpaque(false);
        
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(action);
        btnLogin.setBounds(160,420,80,30);
        login.add(btnLogin);
        
        btnSignup = new JButton("Signup");
        btnSignup.addActionListener(action);
        btnSignup.setBounds(160,460,80,30);
        login.add(btnSignup);
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
    public JPasswordField getTfPassword(){
        return tfPassword;
    }

    public int getCredentialState() {
        return credentialState;
    }

    public void setCredentialState(int credentialState) {
        this.credentialState = credentialState;
    }
    
}
