/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luuca.appchat.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luuca
 */
public class ServerThreadBus {
    private List<ServerThread> listServerThreads;
    
    public List<ServerThread> getListServerThreads() {
        return listServerThreads;
    }
    public ServerThreadBus() {
        listServerThreads = new ArrayList<>();
    }
    public void add(ServerThread serverThread){
        listServerThreads.add(serverThread);
    }
    public void remove(int id){
        for(int i=0; i<AppChatServer.serverThreadBus.getLength(); i++){
            if(AppChatServer.serverThreadBus.getListServerThreads().get(i).getId()==id){
                AppChatServer.serverThreadBus.listServerThreads.remove(i);
            }
        }
    }
    public int getLength(){
        return listServerThreads.size();
    }
    public void sendOnlineList(){
        String allUsernameString = "";
        List<ServerThread> threadbus = AppChatServer.serverThreadBus.getListServerThreads();
        for(ServerThread serverThread : threadbus){
//            System.out.println("USERNAME: "+serverThread.getUsername());
            if (serverThread.getUsername() != null) //Prevent listing clients who's online but haven't log in.
                allUsernameString+=serverThread.getUsername()+"-";
        }
        AppChatServer.serverThreadBus.mutilCastSend("update-online-list"+","+allUsernameString);
    }
    public void sendAccountExistState(int id, boolean state){
        for(ServerThread serverThread : AppChatServer.serverThreadBus.getListServerThreads()){
            if(serverThread.getId()==id){
                try {
                    serverThread.write("check-account"+","+state);
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
       public void sendCredentialState(int id, int state){ //Send to Client requested checking credential state to login
        for(ServerThread serverThread : AppChatServer.serverThreadBus.getListServerThreads()){
            if(serverThread.getId()==id){
                try {
                    serverThread.write("check-credential"+","+state);
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void broardCast(int id, String message){ //Broadcast to every client (except for self)
        for(ServerThread serverThread : AppChatServer.serverThreadBus.getListServerThreads()){
            if (serverThread.getId() == id) {
                continue;
            } else {
                try {
                    serverThread.write(message);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void mutilCastSend(String message){ //like sockets.emit in socket.io
        for(ServerThread serverThread : AppChatServer.serverThreadBus.getListServerThreads()){
            try {
                serverThread.write(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void sendMessageToPerson(String username, String message){
        for(ServerThread serverThread : AppChatServer.serverThreadBus.getListServerThreads()){
            if(serverThread.getUsername().equals(username)){
                try {
                    serverThread.write("global-message"+","+message);
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
