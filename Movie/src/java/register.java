


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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

/**
 *
 * @author Menna Mamdouh
 */
@WebServlet(urlPatterns = {"/register"})
public class register extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String isadmin=(String)request.getParameter("adminCode");
            User userData=new User();
            
            if(isadmin!=null)
            {
                String ValidationCode=(String) request.getParameter("code");
                
                if(ValidationCode.equals("123"))
                {
                    userData.setAdmin(1);
                }
                else{
                    response.sendRedirect("404.html");
                    return;
                }
            }
            
            userData.setFname(request.getParameter("first_name"));
            userData.setLname(request.getParameter("last_name"));
            userData.setAddress(request.getParameter("address"));
            userData.setPhoneNumber(request.getParameter("phone_number"));
            userData.setEmail(request.getParameter("email"));
            userData.setPassword(request.getParameter("password"));
            String checkPassword=request.getParameter("password_confirm");
            
            //System.err.println("KOKORAy");
//            String url = "jdbc:mysql://localhost:3306/databaseIA";
//            String user = "root";
//            String password = "admin";
            String databasePassword="";
            String selectResult="";
            
            String line;
            Connection Con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            try {
               
                if(!checkPassword.equals(userData.getPassword())){
                    out.print(checkPassword+","+userData.getPassword());
                    //response.sendRedirect("registeration.jsp");
                }
                else{
                Class.forName("com.mysql.jdbc.Driver");
                Con = DBConnection.createConnection();
                out.print("Connection done successfully <br/>");
                Stmt = Con.createStatement();

                line = "SELECT email FROM User where email= '"+userData.getEmail()+"' ;" ;//+ request.getParameter("id") + ";";
                RS = Stmt.executeQuery(line);
                
                if(RS.next())
                {
                    response.sendRedirect("registration.jsp");
                    return;
                }
                else{
                    String SQL_INSERT=" INSERT INTO User (Fname,Lname,password,email,address,phoneNumber) VALUES (?,?,?,?,?,?)";
                    PreparedStatement statement = Con.prepareStatement(SQL_INSERT,
                                      Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, userData.getFname());
                    statement.setString(2, userData.getLname());
                    statement.setString(3, userData.getPassword());
                    statement.setString(4, userData.getEmail());
                    statement.setString(5, userData.getAddress());
                    statement.setString(6, userData.getPhoneNumber());
                  //  line="INSERT INTO user (Fname,Lname,password,email,address,phoneNumber) VALUES ('"+FirstName+"', '"+LastName+"', '"+password+"', '"+Email+"','"+address+"','"+phonenumber+"');";
                    //out.print(line);
                   int affectedRows = statement.executeUpdate();

                    if (affectedRows == 0) {
                        throw new SQLException("Creating user failed, no rows affected.");
                    }

                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            userData.setID((int)generatedKeys.getLong(1));
                            out.print(userData.getID());
                        }
                        else {
                            throw new SQLException("Creating user failed, no ID obtained.");
                        }
                    
                    }
                    if(userData.getAdmin()==1)
                    {
                        UserDatabase.addNewAdmin(userData.getID());
                    }
                    HttpSession session=request.getSession(true);
                    session.setAttribute("userData", userData);
                    RS.close();
                    Stmt.close();
                    Con.close();
                    response.sendRedirect("index.jsp");
                    return;
                }

                
            }
                }
        
            catch(Exception e)
            {
                e.printStackTrace();
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
