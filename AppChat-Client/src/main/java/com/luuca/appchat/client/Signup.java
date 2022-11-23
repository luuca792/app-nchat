/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luuca.appchat.client;

import controllers.SignupHelper;
import java.awt.GridLayout;
import java.util.Arrays;
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
public final class Signup extends JPanel { //card 1
    
    AppChatClient frame;
    
    JPanel signupForm;
    JLabel lblUser;
    JLabel lblPassword;
    JLabel lblRePassword;
//    JPanel titlePanel;
//    JLabel title;
    JTextField tfUser;
    JPasswordField tfPassword;
    JPasswordField tfRePassword;
    JButton btnSignup;
    JButton btnBack;
    
    SignupHelper action;
    
    JLabel bg;
    
    private boolean accountExist = false;
    
    public Signup(AppChatClient frame){
        this.frame = frame;
        action = new SignupHelper(frame, this);
        initComponent();
    }
    public void initComponent(){
        this.setLayout(null);
        
        bg = new JLabel();
        bg.setBounds(0,0,400,600);
        ImageIcon bgSignup = new ImageIcon("src/main/resources/images/signup.png");
        bg.setIcon(bgSignup);
        this.add(bg);
        
        signupForm = new JPanel();
        signupForm.setBounds(70,250,260,80);
        signupForm.setLayout(new GridLayout(3,2));
        signupForm.setOpaque(false);
        bg.add(signupForm);
        
//        titlePanel = new JPanel();
//        titlePanel.setBounds(0,50,400,50);
//        titlePanel.setOpaque(false);
//        title = new JLabel();
//        titlePanel.add(title);
//        bg.add(titlePanel);
        
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
        btnSignup.setBounds(160,420,80,30);
        bg.add(btnSignup);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(action);
        btnBack.setBounds(160,460,80,30);
        bg.add(btnBack);
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
