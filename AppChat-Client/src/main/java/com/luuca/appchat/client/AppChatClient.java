/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.luuca.appchat.client;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
public class AppChatClient extends JFrame {

    private Thread thread;
    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    
    JPanel contPanel;
    JPanel loginForm;
    JPanel loginPanel; //card 1
    JLabel lblUser;
    JLabel lblPassword;
    JPanel titlePanel;
    JLabel title;
    JTextField tfUser;
    JTextField tfPassword;
    
    JButton btnLogin;
    
    JPanel chatPanel; //card 2
    
    CardLayout card;
    controllers.ActionHelper action;

    public AppChatClient() {
        initComponents();
        this.setSize(400,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("NChat");
        this.setResizable(false);
        this.setVisible(true);
        setUpSocket();
    }

    private void initComponents() {
        
        action = new controllers.ActionHelper(this);
        
        contPanel = new JPanel();
        card = new CardLayout();
        contPanel.setLayout(card);
        
        loginPanel = new JPanel();
        loginForm = new JPanel();
        chatPanel = new JPanel();
        
    // LOGIN PANEL
//        loginPanel.setBackground(Color.blue);
        loginPanel.setLayout(null);
        loginForm.setBounds(70,250,260,60);
        loginPanel.add(loginForm);
        
        titlePanel = new JPanel();
        titlePanel.setBounds(0,50,400,50);
        title = new JLabel("NCHAT");
        titlePanel.add(title);
        loginPanel.add(titlePanel);
        
        loginForm.setLayout(new GridLayout(2,2));
        lblUser = new JLabel("Username: ");
        lblUser.setSize(50,50);
        lblPassword = new JLabel("Password: ");
        lblPassword.setSize(50,50);
        tfUser = new JTextField();
        tfPassword = new JTextField();
        loginForm.add(lblUser);
        loginForm.add(tfUser);
        loginForm.add(lblPassword);
        loginForm.add(tfPassword);
        
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(action);
        btnLogin.setBounds(160,320,80,30);
        loginPanel.add(btnLogin);
        
    // CHAT PANEL    
//        chatPanel.setBackground(Color.red);
        chatPanel.setLayout(null);
        JTabbedPane tab = new JTabbedPane();
        tab.setBounds(0,0,395,570);
        
        // ONLINE TAB
        JTextArea txtAreaOnline = new JTextArea();
        txtAreaOnline.setEditable(false);
        JScrollPane onlineTab = new JScrollPane(txtAreaOnline, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
//        txtAreaOnline.setText("Luu Ca is online");
        
        tab.addTab("Online", onlineTab);
        // CHAT TAB
        JPanel panelOnline = new JPanel();
        panelOnline.setLayout(null);
        JLabel global = new JLabel("Global");
        global.setBounds(170, 5, 50, 50);
        panelOnline.add(global);
        
        JTextArea txtAreaChat = new JTextArea();
        txtAreaChat.setEditable(false);
        JScrollPane scChat = new JScrollPane(txtAreaChat,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scChat.setBounds(20, 50, 340, 300);
        panelOnline.add(scChat);
        
        JLabel toUser = new JLabel("To user:");
        toUser.setBounds(20, 360, 100, 20);
        panelOnline.add(toUser);
        
        JComboBox cbbUser = new JComboBox();
        cbbUser.setBounds(20, 390, 340, 20);
        panelOnline.add(cbbUser);
        
        JLabel message = new JLabel("Message:");
        message.setBounds(20, 420, 100, 20);
        panelOnline.add(message);
        
        JTextField tfMessage = new JTextField();
        tfMessage.setBounds(20 , 450 , 340 , 20);
        panelOnline.add(tfMessage);
        
        JButton btnSend = new JButton("Send");
        btnSend.setBounds(20, 490, 340, 30);
        panelOnline.add(btnSend);
        
        tab.add("Chat", panelOnline);
        
        chatPanel.add(tab);
        
    // CONTAINER PANEL
        contPanel.add(loginPanel, "1");
        contPanel.add(chatPanel, "2");
        
        
        card.show(contPanel, "1");
        this.add(contPanel);
    }
    public JButton getBtnLogin(){
        return btnLogin;
    }
    public CardLayout getCard(){
        return card;
    }
    public JPanel getContPanel(){
        return contPanel;
    }

    private void setUpSocket() {
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    socketOfClient = new Socket("localhost", 6666);
                    System.out.println("Connect Successfully!");
                    // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
                    os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
                    // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
                    is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
                    String message;
                    while (true) {
                           
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AppChatClient.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        };
        thread.run();
    }

    public static void main(String[] args) {
        AppChatClient appChatClient = new AppChatClient();
    }
}
