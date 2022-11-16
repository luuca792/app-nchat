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
    public int getLength(){
        return listServerThreads.size();
    }
        public void sendAccountExistState(int id, boolean state){
        for(ServerThread serverThread : AppChatServer.serverThreadBus.getListServerThreads()){
            if(serverThread.getClientNumber()==id){
                try {
                    serverThread.write("check-account"+","+state);
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}