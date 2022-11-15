/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.luuca.appchat.client.AppChatClient;
import com.luuca.appchat.client.Signup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            // goi yc kiem tra username trong csdl toi server
//            JOptionPane.showMessageDialog(signupPanel, "Ten nguoi dung da ton tai!", "Loi", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
