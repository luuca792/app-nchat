package com.luuca.appchat.client;

import controllers.ChatHelper;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author luuca
 */
public final class Chat extends JPanel {

    AppChatClient frame;

    JTabbedPane tab;
    JTextAreaPlus txtAreaOnline;
    JScrollPane onlineTab;
    JLabel panelChat;
    JLabel global;
    JTextPane txtAreaChat;
    JScrollPane scChat;
    JLabel toUser;
    JComboBox cbbUser;
    JLabel message;
    JTextField tfMessage;
    JButton btnSend;

    JLabel panelSystem;
    JLabel lblChangeUsername;
    JTextField tfChangeDisplayname;
    JButton btnChangeUsername;

    JLabel lblChangePassword;
    JLabel lblOldPassword;
    JPasswordField tfOldPassword;
    JLabel lblNewPassword;
    JPasswordField tfNewPassword;
    JButton btnChangePassword;
    Boolean OldPasswordState = false;
    JButton btnLogout;

//    JTextPane edit;
//    JScrollPane scrollEdit;
    SimpleAttributeSet attr;
    Document doc;

    ChatHelper action;

    public Chat(AppChatClient frame) {
        this.frame = frame;
        action = new ChatHelper(frame, this);
        initComponents();

    }

    public void initComponents() {
        this.setLayout(null);
        tab = new JTabbedPane();
        tab.setBounds(0, 0, 395, 570);
        // ONLINE TAB
        txtAreaOnline = new JTextAreaPlus();
        ImageIcon bgOnline = new ImageIcon("src/main/resources/images/online.png");
        
        txtAreaOnline.setImage(bgOnline);
        Font font = new Font("Arial", Font.BOLD, 15);
        txtAreaOnline.setFont(font);
        txtAreaOnline.setForeground(Color.red);
        
        txtAreaOnline.setEditable(false);
        onlineTab = new JScrollPane(txtAreaOnline,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//        txtAreaOnline.setText("Luu Ca is online");
        tab.addTab("Online", onlineTab);
        // CHAT TAB
        panelChat = new JLabel();
        ImageIcon bgChat = new ImageIcon("src/main/resources/images/chat.png");
        panelChat.setIcon(bgChat);
        panelChat.setLayout(null);
        global = new JLabel("Global");
        global.setBounds(170, 5, 50, 50);
        panelChat.add(global);

        txtAreaChat = new JTextPane();
        doc = txtAreaChat.getStyledDocument();

        txtAreaChat.setEditable(false);
        scChat = new JScrollPane(txtAreaChat,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scChat.setBounds(20, 50, 340, 300);
        panelChat.add(scChat);

        toUser = new JLabel("To user:");
        toUser.setBounds(20, 360, 100, 20);
        panelChat.add(toUser);

        cbbUser = new JComboBox();
        cbbUser.setBounds(20, 390, 340, 20);
        cbbUser.addItem("Global");
        panelChat.add(cbbUser);

        message = new JLabel("Message:");
        message.setBounds(20, 420, 100, 20);
        panelChat.add(message);

        tfMessage = new JTextField();
        tfMessage.setBounds(20, 450, 340, 20);
        panelChat.add(tfMessage);

        btnSend = new JButton("Send");
        btnSend.setBounds(20, 490, 340, 30);
        btnSend.addActionListener(action);
        panelChat.add(btnSend);

        tab.add("Chat", panelChat);
        // SYSTEM TAB
        panelSystem = new JLabel();
        ImageIcon bgSystem = new ImageIcon("src/main/resources/images/system.png");
        panelSystem.setIcon(bgSystem);
        panelSystem.setLayout(null);

        lblChangeUsername = new JLabel("Change display name");
        lblChangeUsername.setBounds(20, 10, 300, 20);
        tfChangeDisplayname = new JTextField();
        tfChangeDisplayname.setBounds(40, 40, 150, 20);
        btnChangeUsername = new JButton("Change");
        btnChangeUsername.setBounds(40, 70, 100, 25);
        btnChangeUsername.addActionListener(action);

        lblChangePassword = new JLabel("Change password");
        lblChangePassword.setBounds(20, 125, 300, 20);
        lblOldPassword = new JLabel("Input old password");
        lblOldPassword.setBounds(40, 155, 300, 20);
        tfOldPassword = new JPasswordField();
        tfOldPassword.setBounds(40, 185, 150, 20);
        lblNewPassword = new JLabel("Input new password");
        lblNewPassword.setBounds(40, 215, 300, 20);
        tfNewPassword = new JPasswordField();
        tfNewPassword.setBounds(40, 245, 150, 20);
        btnChangePassword = new JButton("Change");
        btnChangePassword.setBounds(40, 275, 100, 25);
        btnChangePassword.addActionListener(action);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(20, 320, 100, 25);
        btnLogout.addActionListener(action);

        panelSystem.add(lblChangeUsername);
        panelSystem.add(tfChangeDisplayname);
        panelSystem.add(btnChangeUsername);
        panelSystem.add(lblChangePassword);
        panelSystem.add(lblOldPassword);
        panelSystem.add(tfOldPassword);
        panelSystem.add(lblNewPassword);
        panelSystem.add(tfNewPassword);
        panelSystem.add(btnChangePassword);
        panelSystem.add(btnLogout);

        tab.add("System", panelSystem);
        this.add(tab);
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public JTextField getTfMessage() {
        return tfMessage;
    }

    public JComboBox getCbbUser() {
        return cbbUser;
    }

    public JTextPane getTxtAreaChat() {
        return txtAreaChat;
    }

    public JTextArea getTxtAreaOnline() {
        return txtAreaOnline;
    }

    public JButton getBtnChangeUsername() {
        return btnChangeUsername;
    }

    public JButton getBtnChangePassword() {
        return btnChangePassword;
    }

    public JPasswordField getTfOldPassword() {
        return tfOldPassword;
    }

    public JPasswordField getTfNewPassword() {
        return tfNewPassword;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JTextField getTfChangeDisplayname() {
        return tfChangeDisplayname;
    }

    public void setOldPasswordState(Boolean OldPasswordState) {
        this.OldPasswordState = OldPasswordState;
    }

    public Boolean getOldPasswordState() {
        return OldPasswordState;
    }

    public SimpleAttributeSet getAttr() {
        return attr;
    }

    public Document getDoc() {
        return doc;
    }
    

}
