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
                    && !String.valueOf(loginPanel.getTfPassword().getPassword()).isEmpty()){
                if (!"demo".equals(loginPanel.getTfUser().getText()) 
                        || !"demo".equals(String.valueOf(loginPanel.getTfPassword().getPassword()))){
                    try {
                    frame.write("check-credential"+","
                            +frame.getID()+","
                            +loginPanel.getTfUser().getText() +","
                            +String.valueOf(loginPanel.getTfPassword().getPassword()));
                    Thread.sleep(100);
//                    System.out.println("Credential state: "+loginPanel.getCredentialState());
                        switch (loginPanel.getCredentialState()) {
                            case 0:
                                JOptionPane.showMessageDialog(loginPanel, "Non-exist username.");
                                return;
                            case 1:
                                JOptionPane.showMessageDialog(loginPanel, "Invalid password.");
                                return;
                            case 2:
                                frame.setUsername(loginPanel.getTfUser().getText());
                                break;
                            default:
                                JOptionPane.showMessageDialog(loginPanel, "Something went wrong.");
                                return;
                        }
                    
                    } catch (InterruptedException | IOException ex) {
                        Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try { //inform the ServerThread this Client's username
                    frame.write("inform-username"+","+frame.getUsername());
                    Thread.sleep(10);
                    frame.getChatPanel().getTfChangeDisplayname().setText(frame.getDisplayname());
                } catch (IOException ex) {
                    Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
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
