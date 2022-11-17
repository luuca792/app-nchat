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
                if (!signupPanel.getTfUsername().getText().equals("")){
                    frame.setUsername(signupPanel.getTfUsername().getText());
                    frame.write("check-account"+","+frame.getID()+","+frame.getUsername());
                    Thread.sleep(10);
                    System.out.println("Account exist state: "+signupPanel.getAccountExist());
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
