package Model;

import beans.BeanSesion;
import java.io.Serializable;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Modelo implements Serializable {

    private static Gestor gestor;
    private static Modelo single;

    public Modelo() {
        gestor = new Gestor();
    }

    public static Modelo getInstance() {
        return single != null ? single : (single = new Modelo());
    }

    public String getfoto(String email) {
        try {
            return gestor.foto(email);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String foto(String email) {
        try {
            return "<img class='img-responsive rounded' src=\"" + gestor.foto(email) + "\" alt=\"image\" height=\"50\" width=\"50\" />";
        } catch (Exception ex) {
            return "photo unavailable";
        }
    }

    public void deleteNew(int n) throws Exception {
        gestor.eliminarNoticias(n);
    }

    public boolean guardarUsuario(String email, String nombre, String p_id, int type, String ruta_img, String pass) throws Exception {
        return gestor.guardarUsuario(email, nombre, p_id, type,ruta_img, pass);
    }

    public boolean verificarUsuario(String email, String password) throws Exception {
        return gestor.verificarUsuario(email, password);
    }

    public boolean actualizarUsuario(String idRegistro, String email, String nombre, String primerAp, String segundoAp, String direccion, String foto, Date fechaNac) {
        try {
            // gestor.actualizarUsuario(idRegistro, email, nombre, primerAp, segundoAp, direccion, foto, fechaNac);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean eliminarUsuario(String email) {
        try {
            gestor.eliminarUsuario(email);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    //Metodos de tabla ingreso
    public void guardarIngreso(String ususario, String pais) {
        try {
            gestor.guardarIngreso(ususario, pais);
        } catch (Exception ex) {
            String e = ex.getMessage();
        }
    }

    public String verificarIngreso(String usuario) {
        return gestor.verificarIngreso(usuario);
    }

    public void actualizarIngreso(String usuario, String pais) {
        try {
            gestor.actualizarIngreso(usuario, pais);
        } catch (Exception ex) {
            String e = ex.getMessage();
        }
    }

    public void eliminarIngreso(String usuario) throws Exception {
        gestor.eliminarIngreso(usuario);
    }

    public boolean elinarNoticia(int numero) {
        try {
            gestor.eliminarNoticias(numero);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean actualizarNoticia(String titulo, String descripcion, String email, int numero) {
        try {
            return gestor.actualizarNoticia(titulo, descripcion, email, numero);
        } catch (Exception e) {
            String a = e.getMessage();
            return false;
        }
    }

    public static int getNumeroUsuarios() {

        try {
            return Gestor.getNumeroUsuarios();
        } catch (Exception ex) {
            return 0;
        }
    }

    public static String getUsuariosConectadosHTML(int p) {
        try {
            return Gestor.getUsuariosConectadosHTML(p);
        } catch (Exception ex) {
            return "<p class='alert alert-success'>" + ex.getMessage() + "</p>";
        }
    }

    public static String getComentariosHTML(int p) {
        try {
            return Gestor.getComentariosHTML(p);
        } catch (Exception ex) {
            return "<p class='alert alert-success'>" + ex.getMessage() + "</p>";
        }
    }

    public boolean cerrarSesion(String user_id) {
        try {
            return gestor.cerrarSesion(user_id);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean guardarComentario(String desc, String usuario, int tipo) {
        return gestor.guardarComentario(desc, usuario, tipo);
    }

    public boolean guardarComentarioPersonal(String desc, String usuario_de, String usuario_para) {
        return gestor.guardarComentarioPersonal(desc, usuario_de, usuario_para);
    }
}
