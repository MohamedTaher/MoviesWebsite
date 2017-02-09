/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
@WebServlet(urlPatterns = {"/Extend"})
public class Extend extends HttpServlet {

    private PreparedStatement stmt = null;

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
        try (PrintWriter out = response.getWriter()) {

            int movieID = Integer.parseInt(request.getParameter("MovieID"));
            try {
                Connection conn;
                String movieName = new String();
                float moviePrice = 0;
                int numInStock = 0, userBalance = 0;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DBConnection.createConnection();
                String sql = "select * from rentingData where MovieID = ? and IDUser = ? ;";
                stmt = conn.prepareStatement(sql);
                // System.out.print(1);
                stmt.setInt(1, Integer.parseInt(request.getParameter("MovieID")));
                User user = (User) request.getSession(true).getAttribute("userData");
                stmt.setInt(2, user.getID());
                //System.out.print(Integer.parseInt(request.getParameter("MovieID")));
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String currentDate;
                    //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    //Calendar calobj = Calendar.getInstance();
                    currentDate = (rs.getString("ExpiredTime"));
                    //stmt.setString(3, currentDate);
                    String[] dateFields = currentDate.split("/");
                    int currentMonth = Integer.parseInt(dateFields[1]);
                    if (currentMonth == 12) {
                        currentMonth = 1;
                        int currentYear = Integer.parseInt(dateFields[2]);
                        currentYear++;
                    } else {
                        currentMonth++;
                    }
                    sql = "update rentingData set ExpiredTime = ? where MovieID = ? and IDUser = ? ";
                    stmt = conn.prepareStatement(sql);
                    // System.out.print(1);
                    stmt.setString(1, dateFields[0] + "/" + currentMonth + "/" + dateFields[2]);
                    stmt.setInt(2, Integer.parseInt(request.getParameter("MovieID")));
                    stmt.setInt(3, user.getID());
                    int rows = stmt.executeUpdate();
                    if(rows == 1)
                    {
                        out.println("<script type=\"text/javascript\">");
                out.println("alert('extended');");
                out.println("location='Movie';");
                out.println("</script>");
                    }
                        
                }

            } catch (SQLException se) {
                //Handle errors for JDBC 
                se.printStackTrace();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('something went wrong');");
                out.println("location='Movie';");
                out.println("</script>");
                //response.sendRedirect("404.html");
            } catch (Exception e) {
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
