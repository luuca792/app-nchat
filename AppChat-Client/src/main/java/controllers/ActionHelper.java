/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.luuca.appchat.client.AppChatClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author luuca
 */
public class ActionHelper implements ActionListener{
    
    AppChatClient frame;
    
    public ActionHelper(AppChatClient frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==frame.getBtnLogin()){
            frame.getCard().show(frame.getContPanel(), "2");
        }
    }
    
}
