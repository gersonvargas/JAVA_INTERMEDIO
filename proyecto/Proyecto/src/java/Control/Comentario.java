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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BDADMIN
 */
public class Comentario extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        if (session != null) {
             Modelo modelo = Modelo.getInstance();
            BeanSesion s = (BeanSesion) request.getSession().getAttribute("sesion");

            String descripcion = request.getParameter("descripcion");
            int tipo = Integer.parseInt(request.getParameter("tipo"));

            String usuario = (String) request.getSession(true).getAttribute("email");
            if (descripcion != null && usuario != null) {
                if (modelo.guardarComentario(descripcion, usuario, tipo)) {
                    request.getRequestDispatcher("Chat.jsp").forward(request, response);
                } else {
                      request.getSession(true).setAttribute("error", "No se ha podido ingresar el comentario.");
                    request.getRequestDispatcher("Chat.jsp").forward(request, response);
                }

            } else {
                  request.getSession(true).setAttribute("error", "No ha completado los campos!");
                request.getRequestDispatcher("Chat.jsp").forward(request, response);
            }
        } else {
              request.getSession(true).setAttribute("error", "No se ha inciado sesion.");
            request.getRequestDispatcher("Chat.jsp").forward(request, response);
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
