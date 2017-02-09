/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author mohamed2
 */
@WebServlet(name = "Renting", urlPatterns = {"/Renting"})
public class Renting extends HttpServlet 
{
    
    private Connection conn = null;
    private PreparedStatement stmt = null ;
   

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
            try
            {
                String movieName = new String();
                float moviePrice = 0;
                int numInStock = 0 , userBalance = 0;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DBConnection.createConnection();
                //movieID
                String sql = "select Name,rentalPrice,NumberOfMovie from movie where ID = ? ; ";
                stmt = conn.prepareStatement(sql);
               // System.out.print(1);
                stmt.setInt(1,Integer.parseInt(request.getParameter("MovieID")));
                //System.out.print(Integer.parseInt(request.getParameter("MovieID")));
                ResultSet rs  = stmt.executeQuery();
               if(rs.next())
               {
                    movieName = rs.getString("Name");
                    moviePrice = rs.getFloat("rentalPrice");
                    numInStock = rs.getInt("NumberOfMovie");
               }
                
                
                User currentUser = (User)request.getSession().getAttribute("userData");
                sql = "select balance from user where id = ?;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,currentUser.getID());
                rs.close();
                rs  = stmt.executeQuery();
                if(rs.next())
                {
                    userBalance = rs.getInt("balance");
                }
                
                if(numInStock > 0)
                {
                    if((float)userBalance >= moviePrice)
                    {
                        userBalance -= ((int)moviePrice);
                        sql = "insert into rentingdata values(?,?,?,?,?,?);";
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1,Integer.parseInt(request.getParameter("MovieID")));
                        stmt.setInt(2,currentUser.getID());
                        String currentDate ;
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar calobj = Calendar.getInstance();
                        currentDate = df.format(calobj.getTime());
                        stmt.setString(3, currentDate);
                        String []dateFields = currentDate.split("/");
                        int currentMonth = Integer.parseInt(dateFields[1]);
                        if(currentMonth == 12)
                        {
                            currentMonth = 1;
                            int currentYear = Integer.parseInt(dateFields[2]);
                            currentYear++;
                        }
                        else currentMonth++;
                        stmt.setString(4 , dateFields[0]+"/"+currentMonth+"/"+dateFields[2]);
                        stmt.setInt(5, 0);
                        stmt.setInt(6, 0);
                        int numOfRowsAffected = stmt.executeUpdate();
                        if(numOfRowsAffected == 1)
                        {
                            sql = "update user set balance = ? where ID = ?;";
                            stmt = conn.prepareStatement(sql);
                            stmt.setInt(1, userBalance);
                            stmt.setInt(2, currentUser.getID());
                            numOfRowsAffected = stmt.executeUpdate();
                            if(numOfRowsAffected == 1)
                            {
                                sql = "update movie set NumberOfMovie = ? where ID = ?;";
                                stmt = conn.prepareStatement(sql);
                                numInStock--;
                                stmt.setInt(1, numInStock);
                                stmt.setInt(2,Integer.parseInt(request.getParameter("MovieID")));
                                numOfRowsAffected = stmt.executeUpdate();
                                if(numOfRowsAffected == 1)
                                {
                                    response.sendRedirect("rentMovie.jsp?Name="+movieName+"&RentalPrice="+moviePrice+"&check="+1);
                                     DBConnection.closeConnection();
                                }
                            }
                            
                        }
                        
                    }
                    else
                    {
                        response.sendRedirect("rentMovie.jsp?Name="+movieName+"&RentalPrice="+moviePrice+"&check="+2);
                         DBConnection.closeConnection();
                    }
                }
                else
                {
                    response.sendRedirect("rentMovie.jsp?Name="+movieName+"&RentalPrice="+moviePrice+"&check="+3); 
                    DBConnection.closeConnection();
                    
                }
            }
            catch(SQLException se)
            {    
                //Handle errors for JDBC 
                se.printStackTrace();
                out.println("<script type=\"text/javascript\">");
                    out.println("alert('something went wrong');");
                    out.println("location='Movie';");
                    out.println("</script>");
                //response.sendRedirect("404.html");
            }
            catch(Exception e)
            {    
                //Handle errors for Class.forName 
                e.printStackTrace();
                out.println("<script type=\"text/javascript\">");
                    out.println("alert('something went wrong');");
                    out.println("location='Movie';");
                    out.println("</script>");
                //response.sendRedirect("404.html");
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
