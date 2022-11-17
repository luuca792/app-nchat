/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.luuca.appchat.client.AppChatClient;
import com.luuca.appchat.client.Chat;
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
public class ChatHelper implements ActionListener{
    
    AppChatClient frame;
    Chat chatPanel;
    
    public ChatHelper(AppChatClient frame, Chat chatPanel){
        this.frame = frame;
        this.chatPanel = chatPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==chatPanel.getBtnSend()){
            String messageContent = chatPanel.getTfMessage().getText();
            if (!messageContent.isEmpty()){
                if (chatPanel.getCbbUser().getSelectedIndex()==0){
                    try {
                        frame.write("send-to-global"+","+messageContent+","+frame.getID()+","+frame.getUsername());
                        chatPanel.getTxtAreaChat().setText(chatPanel.getTxtAreaChat().getText()+"Bạn: "+messageContent+"\n");
                        chatPanel.getTxtAreaChat().setCaretPosition(chatPanel.getTxtAreaChat().getDocument().getLength());
                    } catch (IOException ex) {
                        Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
}
