/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luuca.appchat.client;

import controllers.SignupHelper;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author luuca
 */
public final class Signup extends JPanel { //card 1
    
    AppChatClient frame;
    
    JPanel signupForm;
    JLabel lblUser;
    JLabel lblPassword;
    JLabel lblRePassword;
    JPanel titlePanel;
    JLabel title;
    JTextField tfUser;
    JPasswordField tfPassword;
    JPasswordField tfRePassword;
    JButton btnSignup;
    JButton btnBack;
    
    SignupHelper action;
    
    private boolean accountExist = false;
    
    public Signup(AppChatClient frame){
        this.frame = frame;
        action = new SignupHelper(frame, this);
        initComponent();
    }
    public void initComponent(){
        this.setLayout(null);
        
        signupForm = new JPanel();
        signupForm.setBounds(70,250,260,60);
        this.add(signupForm);
        
        titlePanel = new JPanel();
        titlePanel.setBounds(0,50,400,50);
        title = new JLabel("Register a brand new account!");
        titlePanel.add(title);
        this.add(titlePanel);
        
        signupForm.setLayout(new GridLayout(3,2));
        
        lblUser = new JLabel("Username: ");
        lblUser.setSize(50,50);
        
        lblPassword = new JLabel("Password: ");
        lblPassword.setSize(50,50);
        
        lblRePassword = new JLabel("Re-enter password: ");
        lblRePassword.setSize(50,50);
        
        tfUser = new JTextField();
        tfPassword = new JPasswordField();
//        tfPassword.setEchoChar('*');
        tfRePassword = new JPasswordField();
//        tfRePassword.setEchoChar('*');
        
        signupForm.add(lblUser);
        signupForm.add(tfUser);
        signupForm.add(lblPassword);
        signupForm.add(tfPassword);
        signupForm.add(lblRePassword);
        signupForm.add(tfRePassword);
        
        btnSignup = new JButton("Sign Up");
        btnSignup.addActionListener(action);
        btnSignup.setBounds(160,320,80,30);
        this.add(btnSignup);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(action);
        btnBack.setBounds(160,360,80,30);
        this.add(btnBack);
    }
    public JButton getBtnSignup(){
        return btnSignup;
    }
//    public String setUsernameFromTextField(){
//        return frame.setUsername(tfUser.getText());
//    }
    public JTextField getTfUsername(){
        return tfUser;
    }

    public JPasswordField getTfPassword() {
        return tfPassword;
    }

    public JPasswordField getTfRePassword() {
        return tfRePassword;
    }
    
    public String getPassword(){
        return Arrays.toString(tfPassword.getPassword());
    }
    public String getRePassword(){
        return Arrays.toString(tfRePassword.getPassword());
    }
    public JButton getBtnBack(){
        return btnBack;
    }
    public void setAccountExist(boolean state){
        this.accountExist = state;
    }
    public boolean getAccountExist(){
        return accountExist;
    }
}
