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
import org.hibernate.query.Query;
import org.hibernate.Session;

/**
 *
 * @author luuca
 */
public class ServerThread implements Runnable {
    
    private Socket socketOfServer;
    private int id;
    private String username;
    private String displayname;
    private boolean isClosed;
    private BufferedReader is;
    private BufferedWriter os;
    private boolean login = false;
    
    List<Account> rs;
    
    public ServerThread(Socket socketOfServer, int id) {
        this.socketOfServer = socketOfServer;
        this.id = id;
//        System.out.println("Server thread number " + id + " started");
        isClosed = false;
    }
    
    @Override
    public void run() {
        try {
            is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
            write("get-id" + "," + this.id);
//            System.out.println("Fetching accounts from database...");
            rs = this.loadAllAccounts(); //Pre-load all account information
            System.out.println("ID: " + id+" - has started");
//            this.listAllAccounts();
            System.out.println("Running threads: "+AppChatServer.serverThreadBus.getLength());
            String message;
            while (!isClosed) {
                message = is.readLine();// Message recevied from Client's output stream
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split(",");
                
                if (messageSplit[0].equals("check-account")) {
                    boolean exist = false;
                    for (int i = 0; i < rs.size(); i++) {
                        if (rs.get(i).getUsername().equals(messageSplit[2])) {
                            exist = true;
                        }
                    }
                    AppChatServer.serverThreadBus.sendAccountExistState(Integer.parseInt(messageSplit[1]), exist);
                } else if (messageSplit[0].equals("create-account")) {
                    this.createAccount(messageSplit[1], messageSplit[2]);
                } else if (messageSplit[0].equals("reload-accounts")) {
                    rs = this.loadAllAccounts();
                    Thread.sleep(100);
//                    this.listAllAccounts();
                } else if (messageSplit[0].equals("inform-username")) {
                    rs = this.loadAllAccounts();
                    this.username = messageSplit[1];
                    this.FetchDisplayName();
                    this.write("get-displayname" + "," + displayname);
                    this.login = true;
                    AppChatServer.serverThreadBus.sendOnlineList();
                    AppChatServer.serverThreadBus.mutilCastSend("global-message" + "," + "---" + this.displayname + " has logged in---"+","+"green");
//                    System.out.println("Display: "+displayname);
                } else if (messageSplit[0].equals("inform-logout")) {
                    System.out.println("ID " + this.id + " - has logged out");
                    AppChatServer.serverThreadBus.mutilCastSend("global-message" + "," + "---" + this.displayname + " has logged out---"+","+"red");
                    this.login = false;
                    this.username = null;
                    this.displayname = null;
                    AppChatServer.serverThreadBus.sendOnlineList();
                    System.out.println("Running threads: " + AppChatServer.serverThreadBus.getLength());
                } else if (messageSplit[0].equals("check-credential")) {
                    rs = this.loadAllAccounts();
//                    this.listAllAccounts();
                    int state = 0; // (0: non-exist username; 1: invalid password; 2: valid credentials)
                    for (int i = 0; i < rs.size(); i++) {
                        if (rs.get(i).getUsername().equals(messageSplit[2])) {
                            if (rs.get(i).getPassword().equals(messageSplit[3])) {
                                state = 2;
                                break;
                            } else {
                                state = 1;
                                break;
                            }
                        }
                    }
                    AppChatServer.serverThreadBus.sendCredentialState(Integer.parseInt(messageSplit[1]), state);
                } else if (messageSplit[0].equals("update-displayname")) {
                    this.displayname = messageSplit[2];
                    this.updateDisplayName(messageSplit[1], messageSplit[2]);
                    this.write("get-displayname" + "," + displayname);
                } else if (messageSplit[0].equals("send-to-global")) {
                    AppChatServer.serverThreadBus.broardCast(this.getId(), "global-message" + "," + messageSplit[3] + ": " + messageSplit[1]);
                } else if (messageSplit[0].equals("send-to-person")) {
                    AppChatServer.serverThreadBus.sendMessageToPerson(messageSplit[3], messageSplit[2] + " (to you): " + messageSplit[1]);
                } else if (messageSplit[0].equals("check-password")) {
                    Boolean state = false;
                    for (int i = 0; i < rs.size(); i++) {
                        if (rs.get(i).getUsername().equals(messageSplit[1])) {
                            if (rs.get(i).getPassword().equals(messageSplit[2])) {
                                state = true;
                            }
                        }
                    }
                    this.write("check-password" + "," + state);
                } else if (messageSplit[0].equals("set-password")){
                    this.updatePassword(messageSplit[1], messageSplit[2]);
                }
            }
        } catch (IOException ex) {
            isClosed = true;
            AppChatServer.serverThreadBus.remove(id);
            AppChatServer.serverThreadBus.sendOnlineList();
            System.out.println("ID " + this.id + " - has terminated");
            if (this.login) {
                AppChatServer.serverThreadBus.mutilCastSend("global-message" + "," + "---" + this.displayname + " has logged out---");
            }
            System.out.println("Running threads: " + AppChatServer.serverThreadBus.getLength());
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void FetchDisplayName() {
        if (this.username.equals("NONAME")) {
            this.displayname = "NONAME";
        } else {
            for (int i = 0; i < rs.size(); i++) {
                if (rs.get(i).getUsername().equals(this.username)) {
                    this.displayname = rs.get(i).getDisplayname();
                }
            }
        }
    }

    public void updateDisplayName(String username, String displayname) {
        Session session = HibernateConnect.getSessionFactory().openSession();
        session.beginTransaction();
        org.hibernate.query.Query query = session.createQuery("UPDATE Account a SET a.displayname = :displayname WHERE a.username = :username");
        query.setParameter("displayname", displayname);
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    public void updatePassword(String username, String password){
        Session session = HibernateConnect.getSessionFactory().openSession();
        session.beginTransaction();
        org.hibernate.query.Query query = session.createQuery("UPDATE Account a SET a.password = :password WHERE a.username = :username");
        query.setParameter("password", password);
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    public int getId() {
        return id;
    }

    public void write(String message) throws IOException {
        os.write(message);
        os.newLine();
        os.flush();
    }

    public List<Account> loadAllAccounts() {
        Session session = HibernateConnect.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select i from Account i");
        List<Account> rs = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return rs;
    }

    public void listAllAccounts() {
        for (int i = 0; i < rs.size(); i++) {
            System.out.println(rs.get(i).getUsername() + "-" + rs.get(i).getPassword());
        }
    }

    public void createAccount(String username, String password) {
        Session session = HibernateConnect.getSessionFactory().openSession();
        session.beginTransaction();
        
        Account a = new Account(username, password, username);
        session.persist(a);
        
        session.getTransaction().commit();
        session.close();
    }
    
    public String getUsername() {
        return username;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    
}
