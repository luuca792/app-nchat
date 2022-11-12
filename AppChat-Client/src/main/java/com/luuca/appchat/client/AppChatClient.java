/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.luuca.appchat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author luuca
 */
public class AppChatClient extends JFrame {

    private Thread thread;
    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;

    public AppChatClient() {
        initComponents();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setUpSocket();
    }

    private void initComponents() {

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
