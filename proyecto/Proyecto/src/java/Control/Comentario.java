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
            try {
                Modelo modelo = Modelo.getInstance();
                String direccion = request.getSession().getServletContext().getRealPath("/CommentImages");
                UploadBean upBean = new UploadBean();
                upBean.setFolderstore(direccion);
                upBean.setWhitelist("*.jpg,*.gif,*.png,*.jpeg");
                upBean.setOverwritepolicy("nametimestamp");
                if (MultipartFormDataRequest.isMultipartFormData(request)) {

                    try {
                        MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request);
                        String descripcion = mrequest.getParameter("descripcion");
                        int tipo = Integer.parseInt(mrequest.getParameter("tipo"));
                        String usuario = (String) request.getSession(true).getAttribute("email");

                        Hashtable files = mrequest.getFiles();
                        

                            String archivo = ((UploadFile) mrequest.getFiles().get("route")).getFileName();
                            if (archivo!=null) {
                            int posicionPunto = archivo.indexOf(".");
                            String extension = archivo.substring(posicionPunto);
                            String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
                            String nombreImagen = usuario + timeStamp + extension;
                            ((UploadFile) mrequest.getFiles().get("route")).setFileName(nombreImagen);
                            UploadFile file = (UploadFile) files.get("route");
                            String direccionImagen = "CommentImages/" + file.getFileName();
                            String ruta = direccionImagen;

                            if (usuario != null) {
                                if (modelo.guardarComentario(descripcion, usuario, tipo, ruta)) {
                                    upBean.store(mrequest, "route");
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
                            if (descripcion != null && usuario != null) {
                                if (modelo.guardarComentario(descripcion, usuario, tipo, null)) {

                                    request.getRequestDispatcher("Chat.jsp").forward(request, response);
                                } else {
                                    request.getSession(true).setAttribute("error", "No se ha podido ingresar el comentario.");
                                    request.getRequestDispatcher("Chat.jsp").forward(request, response);
                                }

                            } else {
                                request.getSession(true).setAttribute("error", "No ha completado los campos!");
                                request.getRequestDispatcher("Chat.jsp").forward(request, response);
                            }
                        }
                    } catch (UploadException ex) {
                        request.getSession(true).setAttribute("error", ex.getMessage());
                        request.getRequestDispatcher("Chat.jsp").forward(request, response);
                    }
                }
            } catch (UploadException ex) {
                request.getSession(true).setAttribute("error", ex.getMessage());
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
