/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.*;

/**
 *
 * @author taher
 */
public class DBConnection {

    public static Connection con = null;

    public static Connection createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/databaseia";
        String user = "root";
        String password = "00000";
        Statement Stmt = null;
        ResultSet RS = null;
        
        con = DriverManager.getConnection(url, user, password);
        
        return con;
    }
    
    public static void closeConnection() throws SQLException{
        if (con != null)
        {
            con.close();
        }
    }

}
