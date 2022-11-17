/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.luuca.appchat.client;

import controllers.LoginHelper;
import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author luuca
 */
public class AppChatClient extends JFrame {

    private Thread thread;
    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    
    private int id;
    private String username = "NONAME";
    
    JPanel contPanel;
    Login loginPanel; //card 1
    Chat chatPanel; //card 2
    Signup signupPanel; //card 3
    
    CardLayout card;
    LoginHelper action;

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
        contPanel = new JPanel();
        card = new CardLayout();
        contPanel.setLayout(card);
        
        loginPanel = new Login(this);
        chatPanel = new Chat(this);
        signupPanel = new Signup(this);
        
        // CONTAINER PANEL
        contPanel.add(loginPanel, "1");
        contPanel.add(chatPanel, "2");
        contPanel.add(signupPanel, "3");
        
        card.show(contPanel, "1");
        this.add(contPanel);
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
                        message = is.readLine();
                        if(message==null){
                            break;
                        }
                        String[] messageSplit = message.split(",");
                            if(messageSplit[0].equals("get-id")){ //loai request
                                setID(Integer.parseInt(messageSplit[1]));
                                setIDTitle();
                            }
                            else if(messageSplit[0].equals("check-account")){
                                        
                                signupPanel.setAccountExist(Boolean.parseBoolean(messageSplit[1]));
                                
                            }
                            else if(messageSplit[0].equals("check-credential")){
                                        
                                loginPanel.setCredentialState(Integer.parseInt(messageSplit[1]));
                                
                            }
                            else if(messageSplit[0].equals("global-message")){
                                chatPanel.getTxtAreaChat().setText(chatPanel.getTxtAreaChat().getText()+messageSplit[1]+"\n");
                                chatPanel.getTxtAreaChat().setCaretPosition(chatPanel.getTxtAreaChat().getDocument().getLength());
                            }
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AppChatClient.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        };
        thread.run();
    }
    private void setID(int id){
        this.id = id;
    }
    public int getID(){
        return id;
    }
      private void setIDTitle(){
        this.setTitle(this.getTitle()+" (ID: "+this.id+")");
    }
    public void write(String message) throws IOException{
        os.write(message);
        os.newLine();
        os.flush();
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public static void main(String[] args) {
        AppChatClient appChatClient = new AppChatClient();
    }
}
