/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.luuca.appchat.client.AppChatClient;
import com.luuca.appchat.client.Signup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author luuca
 */
public class SignupHelper implements ActionListener{
    
    AppChatClient frame;
    Signup signupPanel;
    
    public SignupHelper(AppChatClient frame, Signup signupPanel){
        this.frame = frame;
        this.signupPanel = signupPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==signupPanel.getBtnBack()){
            frame.getCard().show(frame.getContPanel(), "1");
        }
        else if (e.getSource()==signupPanel.getBtnSignup()){
            try {
                String username = signupPanel.getTfUsername().getText();
                String password = String.valueOf(signupPanel.getTfPassword().getPassword());
                String rePassword = String.valueOf(signupPanel.getTfRePassword().getPassword());
                if (!username.equals("")&& !password.equals("")&& !rePassword.equals("")){
                    frame.setUsername(signupPanel.getTfUsername().getText());
                    frame.write("check-account"+","+frame.getID()+","+frame.getUsername());
                    Thread.sleep(10);
//                    System.out.println("Account exist state: "+signupPanel.getAccountExist());
                    if (signupPanel.getAccountExist()==true){
                        JOptionPane.showMessageDialog(signupPanel, "Username already exist!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!signupPanel.getPassword().equals(signupPanel.getRePassword())){
                        JOptionPane.showMessageDialog(signupPanel, "Password in 2 fields does not match!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    frame.write("create-account"+","+username+","+password);
                    JOptionPane.showMessageDialog(signupPanel, "Account regsitered!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Thread.sleep(10);
                    frame.write("reload-accounts");
                }
                
//            JOptionPane.showMessageDialog(signupPanel, "Ten nguoi dung da ton tai!", "Loi", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(SignupHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(SignupHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
