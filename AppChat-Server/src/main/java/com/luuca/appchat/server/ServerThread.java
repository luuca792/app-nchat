/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luuca.appchat.server;

import controllers.HibernateConnect;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import org.hibernate.Query;
import org.hibernate.Session;

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
    
    List<Account> rs;

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
            write("get-id" + "," + this.clientNumber);// messageSplit[0]="get-id" messageSplit[1]="0"
            rs = this.loadAllAccounts();
            
            String message;
            while (!isClosed) {
                message = is.readLine();
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split(",");
                if(messageSplit[0].equals("check-account")){
                    boolean exist = false;
//                    List<Account> rs = this.loadAllAccounts();
                    for (int i=0; i<rs.size(); i++){
                        if (rs.get(i).getUsername().equals(messageSplit[2])) exist = true;
                    }
//                    System.out.println(exist);
                    AppChatServer.serverThreadBus.sendAccountExistState(Integer.parseInt(messageSplit[1]), exist);
            
                }
            }
        } catch (IOException ex) {
            isClosed = true;
            System.out.println(this.clientNumber+" has logged out");
        }
    
    }
    public int getClientNumber() {
        return clientNumber;
    }
    public void write(String message) throws IOException{
        os.write(message);
        os.newLine();
        os.flush();
    }
    public List<Account> loadAllAccounts(){
        Session session = HibernateConnect.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select i from Account i");
        List<Account> rs = query.getResultList();
        return rs;
    }
    
}
