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
    JTextField tfPassword;
    JButton btnLogin;
    
    LoginHelper action;
    
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
        tfPassword = new JTextField();
        loginForm.add(lblUser);
        loginForm.add(tfUser);
        loginForm.add(lblPassword);
        loginForm.add(tfPassword);
        
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(action);
        btnLogin.setBounds(160,320,80,30);
        this.add(btnLogin);
    }
     public JButton getBtnLogin(){
        return btnLogin;
    }
}
