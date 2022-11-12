/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.luuca.appchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luuca
 */
public class AppChatServer {
    
    public static Socket socketOfServer;

    public static void main(String[] args) {
        ServerSocket listener = null;
        System.out.println("Server is waiting to accept user...");
        try {
            listener = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        try {
            while (true) {
                // Chấp nhận một yêu cầu kết nối từ phía Client.
                // Đồng thời nhận được một đối tượng Socket tại server.
                socketOfServer = listener.accept();
            }
        } catch (IOException ex) {
        } finally {
            try {
                listener.close();
            } catch (IOException ex) {
                Logger.getLogger(AppChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        }
}
