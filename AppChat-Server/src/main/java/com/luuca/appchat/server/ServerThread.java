/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luuca.appchat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luuca
 */
public class ServerThread implements Runnable {
    
    private Socket socketOfServer;
    private int clientNumber;
    private boolean isClosed;
    private BufferedReader is;
    private BufferedWriter os;

    public ServerThread(Socket socketOfServer, int clientNumber) {
        this.socketOfServer = socketOfServer;
        this.clientNumber = clientNumber;
        System.out.println("Server thread number " + clientNumber + " started");
        isClosed = false;
    }
    
    @Override
    public void run() {
//        System.out.println("Running");
        try {
            is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
            System.out.println("New thread start successfully, ID: " + clientNumber);
            write("get-id" + "," + this.clientNumber);
        } catch (IOException ex) {
            isClosed = true;
            System.out.println(this.clientNumber+" has logged out");
        }
    
    }
    public void write(String message) throws IOException{
        os.write(message);
        os.newLine();
        os.flush();
    }
        
    
}
