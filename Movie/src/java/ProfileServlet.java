/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author taher
 */
public class ProfileServlet extends HttpServlet {

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
            User user = new User();
            HttpSession session = request.getSession();
            user = (User) session.getAttribute("userData");
            String NewUserName = request.getParameter("Uedit_name");
            String NewEmail = request.getParameter("Uedit_email");
            String NewPhone = request.getParameter("Uedit_phoneNumber");
            String NewAddress = request.getParameter("Uedit_Address");

            String[] s = NewUserName.split(" ");

            String FirstName = s[0];
            String LastName = (s.length > 1 ? s[1] : "");
            if (FirstName != null) {
                user.setFname(FirstName);
            }
            if (LastName != null) {
                user.setLname(LastName);
            }
            if (NewEmail != null) {
                user.setEmail(NewEmail);
            }
            if (NewAddress != null) {
                user.setAddress(NewAddress);
            }
            if (NewPhone != null) {
                user.setPhoneNumber(NewPhone);
            }
            User.updateUser(user);
            session.setAttribute("userData", user);
            //Cookie cookie= new Cookie ("userUpdatedData",user.toString());
            //response.addCookie(cookie);
            //  out.println(user.toString());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
            requestDispatcher.forward(request, response);
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
