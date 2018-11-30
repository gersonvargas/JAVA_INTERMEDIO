/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Modelo;
import beans.BeanSesion;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BDADMIN
 */
public class Registrar extends HttpServlet {

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
            Modelo modelo = Modelo.getInstance();

            String email = new String(request.getParameter("email").getBytes("ISO-8859-1"));

            String nombre = new String(request.getParameter("name").getBytes("ISO-8859-1"));
            String id = new String(request.getParameter("id").getBytes("ISO-8859-1"));
            String ap2 = request.getParameter("apellido2");
            String clave = new String(request.getParameter("password").getBytes("ISO-8859-1"));

            //SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
            //String nueva = date.replace('/', '-');
            // Date f = sdf.parse(nueva);
            BeanSesion s = (BeanSesion) request.getSession().getAttribute("sesion");

            if (registrar(email,  nombre,  id, 0,  clave)) {

                s.setEmail(email);
                HttpSession se = request.getSession(true);
                se.setAttribute("valida", true);
                se.setMaxInactiveInterval(2 * 60);
                s.setAdmin(false);
                request.getRequestDispatcher("Login.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("Registrarse.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            request.getRequestDispatcher("Registrarse.jsp").forward(request, response);
        }
    }
private boolean registrar(String email, String nombre, String p_id,int type, String pass) throws Exception {
        if (email != null && nombre != null && pass != null) {
            return Modelo.getInstance().guardarUsuario(email,  nombre,  p_id, type,  pass);
        } else {
            return false;
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
