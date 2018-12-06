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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BDADMIN
 */
@WebServlet(name = "ComentarioPersonal", urlPatterns = {"/ComentarioPersonal"})
public class ComentarioPersonal extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        if (session != null) {
            Modelo modelo = Modelo.getInstance();

            String descripcion = new String(request.getParameter("comentario").getBytes("ISO-8859-1"));
            String usuario_de = (String) request.getSession().getAttribute("email");
            String usuario_para = new String(request.getParameter("usuario_para").getBytes("ISO-8859-1"));
            if (!usuario_de.equals(usuario_para)) {
                if (modelo.guardarComentarioPersonal(descripcion, usuario_de, usuario_para)) {
                    request.getRequestDispatcher("Chat.jsp").forward(request, response);
                } else {
                    request.getSession(true).setAttribute("error", "No se ha podido ingresar el comentario.");
                    request.getRequestDispatcher("Chat.jsp").forward(request, response);
                }

            } else {
                request.getSession(true).setAttribute("error", "No puede comentar a este usuario.");
                request.getRequestDispatcher("Chat.jsp").forward(request, response);
            }
        } else {
            request.getSession(true).setAttribute("error", "No tiene una sesion activa.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
