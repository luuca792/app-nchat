/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luuca.appchat.client;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author luuca
 */
public final class Chat extends JPanel {

    AppChatClient frame;
    
    JTabbedPane tab;
    JTextArea txtAreaOnline;
    JScrollPane onlineTab;
    JPanel panelChat;
    JLabel global;
    JTextArea txtAreaChat;
    JScrollPane scChat;
    JLabel toUser;
    JComboBox cbbUser;
    JLabel message;
    JTextField tfMessage;
    JButton btnSend;
    
    public Chat(AppChatClient frame) {
        this.frame = frame;
        initComponents();
    }

    public void initComponents() {
        this.setLayout(null);
        tab = new JTabbedPane();
        tab.setBounds(0, 0, 395, 570);

        // ONLINE TAB
        txtAreaOnline = new JTextArea();
        txtAreaOnline.setEditable(false);
        onlineTab = new JScrollPane(txtAreaOnline,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//        txtAreaOnline.setText("Luu Ca is online");
        tab.addTab("Online", onlineTab);
        // CHAT TAB
        panelChat = new JPanel();
        panelChat.setLayout(null);
        global = new JLabel("Global");
        global.setBounds(170, 5, 50, 50);
        panelChat.add(global);

        txtAreaChat = new JTextArea();
        txtAreaChat.setEditable(false);
        scChat = new JScrollPane(txtAreaChat,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scChat.setBounds(20, 50, 340, 300);
        panelChat.add(scChat);

        toUser = new JLabel("To user:");
        toUser.setBounds(20, 360, 100, 20);
        panelChat.add(toUser);

        cbbUser = new JComboBox();
        cbbUser.setBounds(20, 390, 340, 20);
        panelChat.add(cbbUser);

        message = new JLabel("Message:");
        message.setBounds(20, 420, 100, 20);
        panelChat.add(message);

        tfMessage = new JTextField();
        tfMessage.setBounds(20, 450, 340, 20);
        panelChat.add(tfMessage);

        btnSend = new JButton("Send");
        btnSend.setBounds(20, 490, 340, 30);
        panelChat.add(btnSend);

        tab.add("Chat", panelChat);

        this.add(tab);
    }

}
