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
    private int id;
    private String username;
    private boolean isClosed;
    private BufferedReader is;
    private BufferedWriter os;
    
    List<Account> rs;

    public ServerThread(Socket socketOfServer, int id) {
        this.socketOfServer = socketOfServer;
        this.id = id;
        System.out.println("Server thread number " + id + " started");
        isClosed = false;
    }
    
    @Override
    public void run() {
        try {
            is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
            System.out.println("New thread start successfully, ID: " + id);
            write("get-id" + "," + this.id);// messageSplit[0]="get-id" messageSplit[1]="0"
            //AppChatServer.serverThreadBus.sendOnlineList(); //update online list for every clients
            System.out.println("Fetching accounts from database...");
            rs = this.loadAllAccounts(); //Pre-load all account information
            
            String message;
            while (!isClosed) {
                message = is.readLine();// Message recevied from Client's output stream
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split(",");
            
                if(messageSplit[0].equals("check-account")){
                    boolean exist = false;
                    for (int i=0; i<rs.size(); i++){
                        if (rs.get(i).getUsername().equals(messageSplit[2])) exist = true;
                    }
                    AppChatServer.serverThreadBus.sendAccountExistState(Integer.parseInt(messageSplit[1]), exist);
                }
                else if(messageSplit[0].equals("inform-username")){
                    this.username = messageSplit[1];
                    AppChatServer.serverThreadBus.sendOnlineList();
                    AppChatServer.serverThreadBus.mutilCastSend("global-message"+","+"---"+this.username+" has logged in---");
                    
                }
                else if(messageSplit[0].equals("check-credential")){
                    int state = -1; // (0: non-exist username; 1: invalid password; 2: valid credentials)
                    for (int i=0; i<rs.size(); i++){
                        if (rs.get(i).getUsername().equals(messageSplit[2])) {
                            if (rs.get(i).getPassword().equals(messageSplit[3])) 
                                state = 2;
                            else state = 1;
                        }
                        else state = 0;
                    }
                    AppChatServer.serverThreadBus.sendCredentialState(Integer.parseInt(messageSplit[1]), state);
                }
                
                else if(messageSplit[0].equals("send-to-global")){
                    AppChatServer.serverThreadBus.broardCast(this.getId(),"global-message"+","+messageSplit[3]+": "+messageSplit[1]);
                }
                else if(messageSplit[0].equals("send-to-person")){
                    AppChatServer.serverThreadBus.sendMessageToPerson(messageSplit[3],messageSplit[2]+" (to you): "+messageSplit[1]);
                }
            }
        } catch (IOException ex) {
            isClosed = true;
            AppChatServer.serverThreadBus.remove(id);
            System.out.println(this.id+" has logged out");
            AppChatServer.serverThreadBus.sendOnlineList();
            AppChatServer.serverThreadBus.mutilCastSend("global-message"+","+"---"+this.username+" has logged out---");
        }
    
    }
    public int getId() {
        return id;
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

    public String getUsername() {
        return username;
    }
    
}
