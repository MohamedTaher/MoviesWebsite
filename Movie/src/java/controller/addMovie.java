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
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohamed2
 */
@WebServlet(name = "addMovie", urlPatterns = {"/addMovie"})
public class addMovie extends HttpServlet 
{
    private Connection conn = null;
    private PreparedStatement stmt = null ;

    private HashMap <Integer,String> movieCategories ; 


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
         
            try  
            { 
                Class.forName("com.mysql.jdbc.Driver");
                movieCategories =  new HashMap<Integer , String>();
                int movieID = 0;
                conn = DBConnection.createConnection();
                
                String sql = "insert into movie (Name,Actor,rate,Poster,Overview,rentalPrice,NumberOfMovie,releaseDate,DirectorName)  values(?,?,?,?,?,?,?,?,?);";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, request.getParameter("movieName"));
                stmt.setString(2, request.getParameter("actorName"));
                stmt.setFloat(3, Float.parseFloat(request.getParameter("rate")));
                stmt.setString(4, request.getParameter("poster"));
                stmt.setString(5, request.getParameter("description"));
                stmt.setFloat(6, Float.parseFloat(request.getParameter("price")));
                stmt.setInt(7, Integer.parseInt(request.getParameter("numInStock")));
                String date = request.getParameter("releaseDate");
                stmt.setString(8,date );
                stmt.setString(9, request.getParameter("directorName")); 
                int rowsAffected = stmt.executeUpdate();
                
                if(rowsAffected == 1)
                {
                    sql = "select ID from movie where releaseDate = ? ;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, request.getParameter("releaseDate"));
                    ResultSet rs  = stmt.executeQuery();
                    while(rs.next())
                    {
                        movieID = rs.getInt("ID");
                    }
                    
                    sql = "select * from category;";
                    stmt = conn.prepareStatement(sql);
                    rs.close();
                    rs  = stmt.executeQuery();
                    while(rs.next())
                    {
                        movieCategories.put(rs.getInt("ID"), rs.getString("Name"));
                    }
                    int temp = 0 ;
                    //System.out.print(request.getParameter("History"));
                    for(int i = 1 ; i <= 6 ; ++i )
                    {
                        if(request.getParameter(movieCategories.get(i)) == null)
                        {
                            continue;
                        }
                        else
                        {
                            //System.out.print(i);
                            sql = "insert into categorymovie values(?,?);";
                            stmt = conn.prepareStatement(sql);
                            stmt.setInt(1,movieID );
                            stmt.setInt(2, i);
                            temp  = stmt.executeUpdate();
                            if(temp == 0)
                            {
                                response.sendRedirect("404.html");
                            }
                        }  
                    }
                    int numOfTrailers = Integer.parseInt(request.getParameter("counter"));
                    for(int i = 1 ; i <= numOfTrailers ; ++i)
                    {
                        if(!request.getParameter("url_"+i).isEmpty())
                        {
                            sql = "insert into tailer (Link , movieID) values(?,?);";
                            stmt = conn.prepareStatement(sql);
                            stmt.setString(1, request.getParameter("url_"+i));
                            stmt.setInt(2, movieID);
                            temp  = stmt.executeUpdate();
                            if(temp == 0)
                            {
                                response.sendRedirect("404.html");
                            }
                        }
                    }
                    DBConnection.closeConnection();
                    
                    //response.sendRedirect("admin.jsp");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('saved successfuly');");
                    out.println("location='admin.jsp';");
                    out.println("</script>");
                    
                }
                else
                {   
                    //response.sendRedirect("404.html");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('something went wrong');");
                    out.println("location='admin.jsp';");
                    out.println("</script>");
                }
                stmt.close();
                conn.close();
                
            }
            catch(SQLException se)
            {    
                //Handle errors for JDBC 
                se.printStackTrace();
                out.println("<script type=\"text/javascript\">");
                    out.println("alert('something went wrong');");
                    out.println("location='admin.jsp';");
                    out.println("</script>");
                //response.sendRedirect("404.html");
            }
            catch(Exception e)
            {    
                //Handle errors for Class.forName 
                e.printStackTrace();
                out.println("<script type=\"text/javascript\">");
                    out.println("alert('something went wrong');");
                    out.println("location='admin.jsp';");
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
