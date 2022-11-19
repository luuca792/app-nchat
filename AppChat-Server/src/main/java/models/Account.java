package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account", schema = "PUBLIC")
public class Account{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;
    
    @Column(name="displayname")
    private String displayname;

    public Account(){}

    public Account(String username, String password, String displayname) {
        this.username = username;
        this.password = password;
        this.displayname = displayname;
    }
    @Override
    public String toString(){
        return id+"-"+username+"-"+password+"-"+displayname+"\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getDisplayname() {
        return displayname;
    }
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    
}