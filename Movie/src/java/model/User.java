package model;

import connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Menna Mamdouh
 */
public class User {
    private int ID;
    private String Fname;
    private String Lname;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private int Admin=0;
    

    public User() 
    {
        
    }
    
    public User(User user)
    {
        ID=user.getID();
        Fname=user.getFname();
        Lname=user.getLname();
        password=user.getPassword();
        email=user.getEmail();
        address=user.getAddress();
        phoneNumber=user.getPhoneNumber();
    }

    public User(int ID, String Fname, String Lname, String password, String email, String address, String phoneNumber) {
        this.ID = ID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public static void updateUser(User _user){
    
        try {
            Connection conn = DBConnection.createConnection();
            
            Class.forName("com.mysql.jdbc.Driver");
        
            Statement Stmt = null;
            ResultSet RS = null;
            
            Stmt = conn.createStatement();
            Stmt.executeUpdate(
                    "update User set Fname = '" + _user.Fname + "' ,Lname = '" + _user.Lname + "' ,password = '"+ _user.password 
                            + "' ,email = '"+ _user.email + "' ,address = '"+ _user.address + "' ,phoneNumber = '"+ _user.phoneNumber 
                            + "'  where id = " + _user.getID() + ";"
            );
            
            
            DBConnection.closeConnection();
        } catch (Exception ex) {
            
        }
        
        
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAdmin() {
        return Admin;
    }

    public void setAdmin(int Admin) {
        this.Admin = Admin;
    }
    
    
    
}