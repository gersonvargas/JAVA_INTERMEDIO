package Model;

import Model.Entidades.Usuario;
import beans.BeanSesion;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;

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

    public static String foto(BeanSesion s) {
        try {
            return "<img src=\"" + gestor.foto(s.getEmail()) + "\" alt=\"image\" height=\"150\" width=\"120\" />";
        } catch (Exception ex) {
            return "photo unavailable";
        }
    }

    public JSONArray getIngreso() {
        try {
            return gestor.getIngreso();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getIngresoString() {
        try {
            return gestor.getIngresoString();
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean addNews(int numero, String titulo, String descripcion, String usuario) {
        try {
            gestor.guardarNoticia(numero, titulo, descripcion, usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

 

//    public String getUsersAll() {
//        try {
//            ArrayList lis = gestor.getUsersAll();
//            StringBuilder r = new StringBuilder();
//
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            r.append("<table id=\"reftabla\">");
//             r.append(String.format("<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>", "Email","Name","Surname","Second Surname","Password","Birth date"));             
//            for (int i = 0; i < lis.size(); i++) {
//                Usuario u = (Usuario) lis.get(i);
//
//                r.append("<tr>");
//                String fecha = sdf.format(u.getFechaNac());
//                r.append(String.format("<td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>",
//                        u.getEmail(), u.getNombre(), u.getPrimerAp(), u.getSegundoAp(), u.getDireccion(), fecha));
//                r.append("</tr>");
//            }
//            r.append("</table>");
//            return r.toString();
//        } catch (Exception ex) {
//            return ex.getMessage();
//        }
//    }
    public void deleteNew(int n) throws Exception {
        gestor.eliminarNoticias(n);
    }

    public boolean guardarUsuario(String email, String nombre, String p_id, int type, String pass) throws Exception {
        return gestor.guardarUsuario(email, nombre, p_id, type, pass);
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

    public boolean isAdministrador(String email) throws Exception {
        return gestor.isAdministrador(email);
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

    public int calcularNumero() throws Exception {
        return gestor.calcularNumero();
    }

    public boolean actualizarNoticia(String titulo, String descripcion, String email, int numero) {
        try {
            return gestor.actualizarNoticia(titulo, descripcion, email, numero);
        } catch (Exception e) {
            String a = e.getMessage();
            return false;
        }
    }

    public static String generarMenu() {
        try {
            return Gestor.generarMenu();
        } catch (Exception ex) {
            return "<p>Lamentamos los inconvenientes</p>";
        }
    }

    public static String getUsuariosConectadosHTML(int p) {
        try {
            return Gestor.getUsuariosConectadosHTML(p);
        } catch (Exception ex) {
            return "<p class='alert alert-success'>" + ex.getMessage() + "</p>";
        }
    }
    public static String getComentariosHTML(int p){
        try {
            return Gestor.getComentariosHTML(p);
        } catch (Exception ex) {
             return "<p class='alert alert-success'>" + ex.getMessage() + "</p>";
        }
    }

    public boolean guardarComentario(String desc, String usuario, int tipo) {
        return gestor.guardarComentario(desc, usuario, tipo);
    }
}
