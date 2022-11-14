/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.luuca.appchat.client.AppChatClient;
import com.luuca.appchat.client.Chat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author luuca
 */
public class ChatHelper implements ActionListener{
    
    AppChatClient frame;
    Chat chatPanel;
    
    public ChatHelper(AppChatClient frame, Chat chatPanel){
        this.frame = frame;
        this.chatPanel = chatPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        
    }
    
}
