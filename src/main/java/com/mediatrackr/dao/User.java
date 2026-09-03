package com.mediatrackr.dao;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {
    private String firstname;
    private String lastname;

    private String email;
    private String password;

    private String userID;

    @XmlElement(name = "Firstname")
    public void setFirstname(String fname){
        this.firstname = fname;
    }

    public String getFirstname(){
        return this.firstname;
    }

    @XmlElement(name = "Lastname")
    public void setLastname(String lname){
        this.lastname = lname;
    }

    public String getLastname(){
        return this.lastname;
    }

    @XmlElement(name = "Email")
    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    @XmlElement(name = "Password")
    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    @XmlElement(name = "ID")
    public void setUserID(String userid){
        this.userID = userid;
    }

    public String getUserID(){
        return this.userID;
    }
}
