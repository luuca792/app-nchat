/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.luuca.appchat.client.AppChatClient;
import com.luuca.appchat.client.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            frame.getCard().show(frame.getContPanel(), "2");
        }
    }
    
}
