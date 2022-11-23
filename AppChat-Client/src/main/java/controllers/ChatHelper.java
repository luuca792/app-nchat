/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.luuca.appchat.client.AppChatClient;
import com.luuca.appchat.client.Chat;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author luuca
 */
public class ChatHelper implements ActionListener {

    AppChatClient frame;
    Chat chatPanel;
    SimpleAttributeSet attr;

    public ChatHelper(AppChatClient frame, Chat chatPanel) {
        this.frame = frame;
        this.chatPanel = chatPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chatPanel.getBtnSend()) {
            String messageContent = chatPanel.getTfMessage().getText();
            if (!messageContent.isEmpty()) {
                if (chatPanel.getCbbUser().getSelectedIndex() == 0) {
                    try {
                        frame.write("send-to-global" + "," + messageContent + "," + frame.getID() + "," + frame.getDisplayname());
//                      chatPanel.getTxtAreaChat().setText(chatPanel.getTxtAreaChat().getText() + "You: " + messageContent + "\n");
                        attr = new SimpleAttributeSet();
                        StyleConstants.setForeground(attr, new Color(13,153,0));
                        StyleConstants.setBold(attr, true);
                        chatPanel.getDoc().insertString(chatPanel.getDoc().getLength(), "You: ", attr);
                        
                        attr = new SimpleAttributeSet();
                        StyleConstants.setForeground(attr, Color.black);
                        chatPanel.getDoc().insertString(chatPanel.getDoc().getLength(), messageContent + "\n", attr);
                        
                        chatPanel.getTxtAreaChat().setCaretPosition(chatPanel.getTxtAreaChat().getDocument().getLength());
                    } catch (IOException ex) {
                        Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    String partner = (String) chatPanel.getCbbUser().getSelectedItem();
//                    System.out.println("Partner: "+partner);
                    try {
                        frame.write("send-to-person" + "," + messageContent + "," + frame.getDisplayname() + "," + partner);
//                        chatPanel.getTxtAreaChat().setText(chatPanel.getTxtAreaChat().getText() + "You (to " + partner + "): " + messageContent + "\n");
                        attr = new SimpleAttributeSet();
                        StyleConstants.setForeground(attr, new Color(13,153,0));
                        StyleConstants.setBold(attr, true);
                        chatPanel.getDoc().insertString(chatPanel.getDoc().getLength(), "You: ", attr);
                        
                        attr = new SimpleAttributeSet();
                        StyleConstants.setForeground(attr, Color.black);
                        chatPanel.getDoc().insertString(chatPanel.getDoc().getLength(), "(to "+partner+"): "+messageContent + "\n", attr);
                        chatPanel.getTxtAreaChat().setCaretPosition(chatPanel.getTxtAreaChat().getDocument().getLength());
                    } catch (IOException ex) {
                        Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                chatPanel.getTfMessage().setText("");
            }
        } else if (e.getSource() == chatPanel.getBtnLogout()) {
            try {
                frame.write("inform-logout");
                frame.resetCredential();
                chatPanel.getTxtAreaChat().setText("");
                frame.getCard().show(frame.getContPanel(), "1");
            } catch (IOException ex) {
                Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == chatPanel.getBtnChangeUsername()) {
            if (chatPanel.getTfChangeDisplayname().getText().equals(frame.getDisplayname())) {
                JOptionPane.showMessageDialog(chatPanel, "Display name must be different than previous!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    frame.write("update-displayname" + "," + frame.getUsername() + "," + chatPanel.getTfChangeDisplayname().getText());
                    Thread.sleep(100);
                    JOptionPane.showMessageDialog(chatPanel, "Display name updated to " + frame.getDisplayname() + "!\n You'll need to log in again.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    frame.write("inform-logout");
                    frame.resetCredential();
                    frame.getCard().show(frame.getContPanel(), "1");
                } catch (IOException ex) {
                    Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (e.getSource() == chatPanel.getBtnChangePassword()) {
            String oldPass = String.valueOf(chatPanel.getTfOldPassword().getPassword());
            String newPass = String.valueOf(chatPanel.getTfNewPassword().getPassword());
            if (oldPass.isEmpty() || newPass.isEmpty()) {
                JOptionPane.showMessageDialog(chatPanel, "Please provide old password and new password!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                frame.write("check-password" + "," + frame.getUsername() + "," + oldPass);
                Thread.sleep(100);
                if (chatPanel.getOldPasswordState() == false) {
                    JOptionPane.showMessageDialog(chatPanel, "Old password incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (oldPass.equals(newPass)) {
                    JOptionPane.showMessageDialog(chatPanel, "New password can not be the same with old password!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                frame.write("set-password" + "," + frame.getUsername() + "," + newPass);
                JOptionPane.showMessageDialog(chatPanel, "Change password succesfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                chatPanel.getTfOldPassword().setText("");
                chatPanel.getTfNewPassword().setText("");
                frame.write("inform-logout");
                frame.resetCredential();
                frame.getCard().show(frame.getContPanel(), "1");
            } catch (IOException ex) {
                Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChatHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
