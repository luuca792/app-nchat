/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.luuca.appchat.client.AppChatClient;
import com.luuca.appchat.client.Login;
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
public class LoginHelper implements ActionListener{
    
    AppChatClient frame;
    Login loginPanel;
    
    public LoginHelper(AppChatClient frame, Login loginPanel){
        this.frame = frame;
        this.loginPanel = loginPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginPanel.getBtnLogin()){
            if (!loginPanel.getTfUser().getText().isEmpty() 
                    && !loginPanel.getTfPassword().getText().isEmpty()){
                if (!"demo".equals(loginPanel.getTfUser().getText()) 
                        || !"demo".equals(loginPanel.getTfPassword().getText())){
                    try {
                    frame.write("check-credential"+","
                            +frame.getID()+","
                            +loginPanel.getTfUser().getText() +","
                            +loginPanel.getTfPassword().getText());
                    Thread.sleep(10);
//                    System.out.println("Credential state: "+loginPanel.getCredentialState());
                    if (loginPanel.getCredentialState()==0){
                        JOptionPane.showMessageDialog(loginPanel, "Non-exist username.");
                        return;
                    }
                    else if (loginPanel.getCredentialState()==1) {
                        JOptionPane.showMessageDialog(loginPanel, "Invalid password.");
                        return;
                    }
                    frame.setUsername(loginPanel.getTfUser().getText());
                    } catch (InterruptedException | IOException ex) {
                        Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try { //inform the ServerThread this Client's username
                    frame.write("inform-username"+","+frame.getUsername());
                } catch (IOException ex) {
                    Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
                //clear username and password input
                loginPanel.getTfUser().setText("");
                loginPanel.getTfPassword().setText("");
                //switch to chat card
                frame.getCard().show(frame.getContPanel(), "2");
            }
            
        }
        else if (e.getSource()==loginPanel.getBtnSignup()){
            frame.getCard().show(frame.getContPanel(), "3");
        }
    }
    
}
