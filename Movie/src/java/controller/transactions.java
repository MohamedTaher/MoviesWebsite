/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.SendMail;
import connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author mohamed2
 */
@WebServlet(name = "transactions", urlPatterns = {"/transactions"})
public class transactions extends HttpServlet {

    
    
    private Connection conn = null;
    private PreparedStatement stmt = null ;
    private String user = "movierental20130214@gmail.com";
    private String pass = "Admin20130214";
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
        try (PrintWriter out = response.getWriter()) 
        {
            int movieID = 0 , userID =0;
            String startTime , expiredTime;
            movieID =Integer.parseInt(request.getParameter("movieID"));
            userID = Integer.parseInt(request.getParameter("IDUser"));
            startTime = request.getParameter("startTime");
            expiredTime = request.getParameter("expiredTime");
            
            if(request.getParameter("restore") == null)//Sending mail
            {
                try  
                { 
                    Class.forName("com.mysql.jdbc.Driver");
                    
                    conn = DBConnection.createConnection();
         
                    String sql = "select email from user where ID = ?;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, userID);
                    ResultSet rs = stmt.executeQuery();
                    ResultSet rs1 ;
                    if(rs.next())
                    {
                        sql = "select Name from movie where ID = ?;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, movieID);
                        rs1 = stmt.executeQuery();
                        rs1.next();
                        
                        SendMail.send(rs.getString("email"),"movie rental team", "deley on returning " +rs1.getString("Name") + " movie !!", user, pass);
                        rs1.close();
                        sql = "update rentingData set mailSend = 1 where MovieID = ? and IDUser = ?;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, movieID);
                        stmt.setInt(2, userID);
                         int rowsAffected = stmt.executeUpdate();
                        if(rowsAffected == 1)
                        {
                            DBConnection.closeConnection();
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('email sent successfuly');");
                            out.println("location='transactions.jsp';");
                            out.println("</script>");
                        }
                    }
                    rs.close();
                }
                catch(SQLException se)
                {    
                //Handle errors for JDBC 
                    se.printStackTrace();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('something went wrong');");
                    out.println("location='transactions.jsp';");
                    out.println("</script>");
                //response.sendRedirect("404.html");
                }
                catch(Exception e)
                {       
                //Handle errors for Class.forName 
                    e.printStackTrace();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('something went wrong');");
                    out.println("location='transactions.jsp';");
                    out.println("</script>");
                //response.sendRedirect("404.html");
                }
                
            }
            else //restore movie
            {
                try  
                { 
                    Class.forName("com.mysql.jdbc.Driver");
                    
                    conn =  DBConnection.createConnection();
         
                    String sql = "update movie set NumberOfMovie = NumberOfMovie + 1 where ID = ?;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, movieID);
                    int rowsAffected = stmt.executeUpdate();
                    if(rowsAffected == 1)
                    {
                        sql = "update rentingData set returned = 1  where MovieID = ? and IDUser = ?;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, movieID);
                        stmt.setInt(2, userID);
                        rowsAffected = stmt.executeUpdate();
                        
                        if(rowsAffected == 1)
                        {
                            DBConnection.closeConnection();
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('successfully returned');");
                            out.println("location='transactions.jsp';");
                            out.println("</script>");
                        }
                    }

                }
                catch(SQLException se)
                {    
                //Handle errors for JDBC 
                    se.printStackTrace();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('something went wrong');");
                    out.println("location='transactions.jsp';");
                    out.println("</script>");
                //response.sendRedirect("404.html");
                }
                catch(Exception e)
                {       
                //Handle errors for Class.forName 
                    e.printStackTrace();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('something went wrong');");
                    out.println("location='transactions.jsp';");
                    out.println("</script>");
                //response.sendRedirect("404.html");
                }
            }
        }
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
