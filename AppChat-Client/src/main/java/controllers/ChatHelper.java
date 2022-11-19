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
                        frame.write("send-to-global"+","+messageContent+","+frame.getID()+","+frame.getDisplayname());
                        chatPanel.getTxtAreaChat().setText(chatPanel.getTxtAreaChat().getText()+"Bạn: "+messageContent+"\n");
                        chatPanel.getTxtAreaChat().setCaretPosition(chatPanel.getTxtAreaChat().getDocument().getLength());
                    } catch (IOException ex) {
                        Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                     String partner = (String)chatPanel.getCbbUser().getSelectedItem();
//                    System.out.println("Partner: "+partner);
                    try {
                        frame.write("send-to-person"+","+messageContent+","+frame.getDisplayname()+","+partner);
                        chatPanel.getTxtAreaChat().setText(chatPanel.getTxtAreaChat().getText()+"Bạn (to "+partner+"): "+messageContent+"\n");
                        chatPanel.getTxtAreaChat().setCaretPosition(chatPanel.getTxtAreaChat().getDocument().getLength());
                    } catch (IOException ex) {
                        Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else if (e.getSource() == chatPanel.getBtnLogout()){
            try {
                frame.write("inform-logout");
                frame.getCard().show(frame.getContPanel(), "1");
            } catch (IOException ex) {
                Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource() == chatPanel.getBtnChangeUsername()){
            if (chatPanel.getTfChangeDisplayname().getText().equals(frame.getDisplayname())) {
                JOptionPane.showMessageDialog(chatPanel, "Display name must be different than previous!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    frame.write("update-displayname"+","+frame.getUsername()+","+chatPanel.getTfChangeDisplayname().getText());
                    Thread.sleep(100);
                    JOptionPane.showMessageDialog(chatPanel, "Display name updated to "+frame.getDisplayname()+"!\n You'll need to log in again.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    frame.write("inform-logout");
                    frame.getCard().show(frame.getContPanel(), "1");
                } catch (IOException ex) {
                    Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
