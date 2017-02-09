


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import model.*;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author taher
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out= response.getWriter())
        {
            String username=request.getParameter("username");
            String enteredPassword=request.getParameter("password");
            User userData=new User();
            
            String databasePassword="";
            String selectResult="";
            
            String line;
            Connection Con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Con=DBConnection.createConnection();
                out.print("Connection done successfully <br/>");
                Stmt = Con.createStatement();

                line = "SELECT * FROM User where email= '"+username+"' ;" ;//+ request.getParameter("id") + ";";
                RS = Stmt.executeQuery(line);
                RS.next();
                databasePassword=RS.getString("password");
                
                
                if(databasePassword.equals(enteredPassword))
                {
                    userData.setEmail(username);
                    
                    userData.setAddress(line);
                    
                    selectResult=RS.getString("email");
                    userData.setEmail(selectResult);
                    selectResult=RS.getString("address");
                    userData.setAddress(selectResult);
                    selectResult=RS.getString("Fname");
                    userData.setFname(selectResult);
                    selectResult=RS.getString("Lname");
                    userData.setLname(selectResult);
                    int ID=RS.getInt("ID");
                    userData.setID(ID);
                    selectResult=RS.getString("phoneNumber");
                    userData.setPhoneNumber(selectResult);
                    userData.setPassword(enteredPassword);
                    //
                    HttpSession session=request.getSession(true);

                    
                    line = "SELECT * FROM Admin where id= '"+Integer.toString(ID)+"' ;" ;//+ request.getParameter("id") + ";";
                    System.out.print(ID);
                    RS = Stmt.executeQuery(line);
                    if(RS.next()){
                        
                        System.err.println("RS.next");
                        userData.setAdmin(1);
                    }
                    else{
                        userData.setAdmin(0);
                    }
                     System.out.print("userAdmin" + userData.getAdmin());
                    session.setAttribute("userData", userData);
                    response.sendRedirect("index.jsp");
                }
                else{
                    response.sendRedirect("login.html");  
                }
                RS.close();
                Stmt.close();
                Con.close();

            } catch (Exception cnfe) {
                out.print("unable to SQL");
                System.err.println("Exception: " + cnfe);
            }
           
            
            
        }
                
       // response.sendRedirect("index.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}