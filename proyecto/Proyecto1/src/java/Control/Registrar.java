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
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;

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
        try  {
            String direccion = request.getSession().getServletContext().getRealPath("/UserImages");
           
            UploadBean upBean = new UploadBean();
            upBean.setFolderstore(direccion);
            upBean.setWhitelist("*.jpg,*.gif,*.png,*.jpeg");
            upBean.setOverwritepolicy("nametimestamp");
            if (MultipartFormDataRequest.isMultipartFormData(request)) {
                
              MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request);
              
                String email = new String(mrequest.getParameter("email").getBytes("ISO-8859-1"));

                String nombre = new String(mrequest.getParameter("name").getBytes("ISO-8859-1"));
                String id = new String(mrequest.getParameter("id").getBytes("ISO-8859-1"));

                String clave = new String(mrequest.getParameter("password").getBytes("ISO-8859-1"));
                Hashtable files = mrequest.getFiles();
                String archivo = ((UploadFile) mrequest.getFiles().get("route")).getFileName();
                int posicionPunto = archivo.indexOf(".");
                String extension = archivo.substring(posicionPunto);
                String nombreImagen = email + extension;
                ((UploadFile) mrequest.getFiles().get("route")).setFileName(nombreImagen);
                UploadFile file = (UploadFile) files.get("route");
                String direccionImagen = "Images/" + file.getFileName();
                String ruta = direccionImagen;
                if (registrar(email, nombre, id, 0, ruta, clave)) {                  
                        upBean.store(mrequest, "route");
                    
                    HttpSession se = request.getSession(true);
                    se.setAttribute("valida", true);
                    se.setMaxInactiveInterval(2 * 60);

                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("Registrarse.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("Registrarse.jsp").forward(request, response);
            }

        } catch (IOException | UploadException ex) {
            request.getRequestDispatcher("Registrarse.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean registrar(String email, String nombre, String p_id, int type, String ruta_img, String pass) throws Exception {
        if (email != null && nombre != null && pass != null) {
            return Modelo.getInstance().guardarUsuario(email, nombre, p_id, type, ruta_img, pass);
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
