/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.DBConnection;
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Menna Mamdouh
 */
public class UserDatabase {
    
    
    public static void addNewAdmin(int ID)
    {
        try{
             Connection conn=DBConnection.createConnection();
             PreparedStatement updateemp = conn.prepareStatement("insert into admin values(?)");
             updateemp.setInt(1,ID);
             updateemp.executeUpdate();
             DBConnection.closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static boolean CheckIfEmailFound(String Email)
    {
        
        try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DBConnection.createConnection();
                PreparedStatement updateemp = conn.prepareStatement("SELECT email FROM User where email= ? ;");
                updateemp.setString(1,Email);
                ResultSet rs=updateemp.executeQuery();
                if(rs.next())
                {
                    return true;
                }
                DBConnection.closeConnection();
                return false;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return false;
        
    }
}
