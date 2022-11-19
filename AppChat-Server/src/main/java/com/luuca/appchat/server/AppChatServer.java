/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.luuca.appchat.server;

import controllers.HibernateConnect;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import org.hibernate.Session;

/**
 *
 * @author luuca
 */
public class AppChatServer {
    public static volatile ServerThreadHelper serverThreadBus;
    public static Socket socketOfServer;
    

    public static void main(String[] args) {
        int clientNumber = 0;
        ServerSocket listener = null;
        serverThreadBus = new ServerThreadHelper();
        System.out.println("Server is waiting to accept user...");
        try {
            listener = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, // corePoolSize
                100, // maximumPoolSize
                10, // thread timeout
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8) // queueCapacity
        );
        try {
            while (true) {
                // Chấp nhận một yêu cầu kết nối từ phía Client.
                // Đồng thời nhận được một đối tượng Socket tại server.
                socketOfServer = listener.accept();
                ServerThread serverThread = new ServerThread(socketOfServer, clientNumber++);
                serverThreadBus.add(serverThread);
                System.out.println("Running threads: "+serverThreadBus.getLength());
                executor.execute(serverThread);
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
