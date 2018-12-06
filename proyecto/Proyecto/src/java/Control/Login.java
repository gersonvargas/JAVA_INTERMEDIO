/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Modelo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BDADMIN
 */
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
        Modelo modelo = Modelo.getInstance();
        //BeanSesion s = (BeanSesion) request.getSession().getAttribute("sesion");

        String email = new String(request.getParameter("email").getBytes("ISO-8859-1"));
        String clave = new String(request.getParameter("password").getBytes("ISO-8859-1"));
        try {
            String nombreUsuario = modelo.verificarUsuario(email, clave);
            if (nombreUsuario != null) {

//                    s.setEmail(email);
//                    s.setId(email);
                HttpSession se = request.getSession(true);
                se.setAttribute("valida", true);
                se.setAttribute("usuario", nombreUsuario);
                se.setAttribute("email", email);
                se.setAttribute("foto", Modelo.foto(email));
                se.setMaxInactiveInterval(20 * 60);
                request.getRequestDispatcher("Chat.jsp").forward(request, response);
            } else {
                request.getSession(true).setAttribute("error", "No se ha podido verificar el usuario!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.getSession(true).setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
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
