/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luuca.appchat.client;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

/**
 *
 * @author luuca
 */
public class JTextAreaPlus extends JTextArea{
    
    Image image;
    
    public JTextAreaPlus(){
        super();
    }
    public JTextAreaPlus(String text){
        super(text);
    }
    public void setImage(ImageIcon icon){
        this.image = icon.getImage();
//        System.out.print(image.toString());
        this.setOpaque(false);
        this.repaint();
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(image, 0, 0, null);
        super.paint(g);
    }
    
}
