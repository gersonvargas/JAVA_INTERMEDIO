package Model;

import Model.Entidades.Sesion;
import Model.Entidades.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONArray;

public class Gestor implements Serializable {

    // Comandos SQL********************************************
    private static final String COMANDO_INSERTAR_USUARIO
            = "INSERT INTO USUARIOS"
            + "(EMAIL,NAME, TYPE, PASSWORD) "
            + "VALUES (?,?,?,AES_ENCRYPT(?,'passw'));";

    private static final String COMANDO_INSERTAR_SESION
            = "INSERT INTO SESIONES"
            + "(USUARIO_ID,STATUS) "
            + "VALUES (?,?);";

    private static final String COMANDO_INSERTAR_COMENTARIO
            = "INSERT INTO COMENTARIOS"
            + "(DESCRIPCION,USUARIO_ID,TIPO_MSJ) "
            + "VALUES (?,?,?);";
    /*
    ID INT PRIMARY KEY,
   NAME VARCHAR(20),
   EMAIL VARCHAR(100),
   TYPE INT DEFAULT 1,
   PASSWORD BLOB
     */
    private static final String COMANDO_ACTUALIZAR_USUARIO
            = "UPDATE Usuario SET "
            + "email = ?, nombre =?,"
            + "apellido1 = ?, apellido2 =?,"
            + "password = ?, rutaFoto = ?,"
            + "nacimiento = ?"
            + " WHERE email=?; ";
    private static final String COMANDO_ELIMINAR_USUARIO
            = "DELETE FROM Usuario "
            + "WHERE email=?; ";

//SELECT * FROM USUARIOS WHERE mail_user=? and  AES_DECRYPT(pswd_user,'passwd')=?;
    private static final String COMANDO_VERIFICAR_USUARIO = "SELECT * FROM USUARIOS " + "WHERE email=? and AES_DECRYPT(password,'passw')=?;";

    private static final String COMANDO_ISADMINISTRADOR = "SELECT isAdministrador FROM Usuario " + "WHERE email=?";

    private static final String[] NOMB_COLUMNAS = {"email", "nombre", "apellido1",
        "apellido2", "password", "rutaFoto", "nacimiento", "isAdministrador"};
    // Comandos SQL PARA INGRESO********************************************
    private static final String COMANDO_INSERTAR_INGRESO
            //cnx.prepareStatement("insert into usuario values('gersonvargasgalvez@yahoo.com', 'Gerson', 'Vargas','Galvez','una.ac.cr', 'log.ico','2014/03/01',0)");//
            = "INSERT INTO Ingreso"
            + "(usuario ,horaIngreso, pais)"
            + "VALUES (?,?,?);";
    private static final String COMANDO_ACTUALIZAR_INGRESO
            = "UPDATE Ingreso SET "
            + "horaIngreso=?, pais=?"
            + " WHERE usuario=?; ";
    private static final String COMANDO_ELIMINAR_INGRESO
            = "DELETE FROM Ingreso "
            + "WHERE usuario=?; ";

    private static final String COMANDO_ELIMINAR_REGISTRO_NOTICIA
            = "DELETE FROM noticia "
            + "WHERE usuario=?; ";
    private static final String COMANDO_VERIFICAR_INGRESO = "SELECT horaIngreso,pais FROM Ingreso " + "WHERE usuario=?; ";

    private static final String[] NOMB_COLUMNAS_INGRESO = {"usuario", "horaIngreso", "pais"};
    // Comandos SQL PARA noticia********************************************
    private static final String COMANDO_INSERTAR_NOTICIA
            = "INSERT INTO noticia"
            + "(numero ,titulo, descripcion,usuario,horaingreso)"
            + "VALUES (?,?,?,?,?);";
    private static final String COMANDO_ACTUALIZAR_NOTICIA
            = "UPDATE noticia SET "
            + "titulo=?,descripcion=?,usuario=?,horaingreso=? "
            + " WHERE numero=?; ";
    private static final String COMANDO_ELIMINAR_NOTICIA
            = "DELETE FROM noticia "
            + "WHERE numero=?; ";

    private static final String COMANDO_OBTENER_NUMERO_NOTICIA
            = "SELECT NUMERO FROM noticia; ";
    private static final String[] NOMB_COLUMNAS_NOTICIA = {"numero", "titulo", "descripcion", "usuario", "horaingreso"};

    private static final String COMANDO_FOTO = "SELECT rutaFoto from Usuario WHERE email=?;";

    public Gestor() {
    }

    public boolean guardarUsuario(String email, String nombre, String p_id, int type, String pass) {
        try {
            Usuario nuevoRegistro = new Usuario(email, nombre, p_id, type, pass);
            Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

            PreparedStatement stm = cnx.prepareStatement(COMANDO_INSERTAR_USUARIO);
            stm.clearParameters();
            stm.setString(1, nuevoRegistro.getEmail());
            stm.setString(2, nuevoRegistro.getNombre());//(ID ,NAME, EMAIL,TYPE, PASSWORD)
            stm.setInt(3, nuevoRegistro.getType());
            stm.setString(4, nuevoRegistro.getPass());

            if (stm.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception ex) {
            String m = ex.getMessage();
            return false;
        } finally {
            Conexion.obtenerInstancia().cerrarConexion();
        }

        return true;
    }

    public boolean guardarComentario(String desc, String usuario, int tipo) {
        try {

            Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

            PreparedStatement stm = cnx.prepareStatement(COMANDO_INSERTAR_COMENTARIO);
            stm.clearParameters();
            stm.setString(1, desc);
            stm.setString(2, usuario);
            stm.setInt(3, tipo);

            if (stm.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception ex) {
            String m = ex.getMessage();
            return false;
        } finally {
            Conexion.obtenerInstancia().cerrarConexion();
        }

        return true;
    }

    public boolean verificarUsuario(String email, String password) {
        try {
            Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
            PreparedStatement stm = cnx.prepareStatement(COMANDO_VERIFICAR_USUARIO);
            stm.clearParameters();
            stm.setString(1, email);
            stm.setString(2, password);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String id = rs.getString("EMAIL");
                this.crearSesion(id, 1);
                Conexion.obtenerInstancia().cerrarConexion();
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {

            String e = ex.getMessage();
            return false;
        } finally {
            Conexion.obtenerInstancia().cerrarConexion();
        }

    }

    public boolean crearSesion(String user_id, int status) {
        try {
            Sesion nuevoRegistro = new Sesion(0, user_id, status);
            Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

            PreparedStatement stm = cnx.prepareStatement(COMANDO_INSERTAR_SESION);
            stm.clearParameters();
            stm.setString(1, nuevoRegistro.getUsuario());
            stm.setInt(2, nuevoRegistro.getStatus());

            if (stm.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception ex) {
            String m = ex.getMessage();
            return false;
        } finally {
            Conexion.obtenerInstancia().cerrarConexion();
        }

        return true;
    }

//    public boolean actualizarUsuario(String idRegistro, String email, String nombre, String primerAp, String segundoAp, String direccion, String foto, Date fechaNac) throws Exception {
//        Usuario nuevoRegistro = new Usuario(email, nombre, primerAp, segundoAp, direccion, foto, fechaNac, 0);
//        eliminarRegistroNoticia(idRegistro);
//        eliminarIngreso(idRegistro);
//
//        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
//
//        PreparedStatement stm = cnx.prepareStatement(COMANDO_ACTUALIZAR_USUARIO);
//        stm.clearParameters();
//
//        stm.setString(1, nuevoRegistro.getEmail());
//        stm.setString(2, nuevoRegistro.getNombre());
//        stm.setString(3, nuevoRegistro.getPrimerAp());
//        stm.setString(4, nuevoRegistro.getSegundoAp());
//        stm.setString(5, nuevoRegistro.getDireccion());
//        stm.setString(6, nuevoRegistro.getFoto());
//        Date momentoDate = nuevoRegistro.getFechaNac();
//        stm.setTimestamp(7, new Timestamp(momentoDate.getTime()));
//
//        stm.setString(8, idRegistro);
//        if (stm.executeUpdate() != 1) {
//            return false;
//        }
//        Conexion.obtenerInstancia().cerrarConexion();
//        return true;
//    }
    public void eliminarUsuario(String email) throws Exception {
        eliminarRegistroNoticia(email);
        eliminarIngreso(email);
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        PreparedStatement stm = cnx.prepareStatement(COMANDO_ELIMINAR_USUARIO);
        stm.clearParameters();
        stm.setString(1, email);
        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }
        Conexion.obtenerInstancia().cerrarConexion();
    }

    public boolean isAdministrador(String email) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

        PreparedStatement stm = cnx.prepareStatement(COMANDO_ISADMINISTRADOR);
        stm.clearParameters();
        stm.setString(1, email);

        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            if (rs.getBoolean(1)) {
                Conexion.obtenerInstancia().cerrarConexion();
                return true;
            } else {
                Conexion.obtenerInstancia().cerrarConexion();
                return false;
            }

        } else {
            throw new Exception();
        }
    }

    public String foto(String email) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

        PreparedStatement stm = cnx.prepareStatement(COMANDO_FOTO);
        stm.clearParameters();
        stm.setString(1, email);

        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("rutaFoto");

        } else {
            return "photo unavailable";
        }
    }

//    public ArrayList getUsersAll() throws Exception {
//        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
//        Statement stm = cnx.createStatement();
//        ResultSet rs = stm.executeQuery("select "
//                + "*from Usuario order by(nacimiento) desc;");
//        ArrayList<Usuario> registros = new ArrayList<>();
//
//        while (rs.next()) {
//            String email = (rs.getString("email"));
//            String nombre = rs.getString("nombre");
//            String ap1 = rs.getString("apellido1");
//            String ap2 = rs.getString("apellido2");
//            String pass = rs.getString("password");
//            //String foto = rs.getString("rutaFoto");
//            Date d_Date = rs.getTimestamp("nacimiento");
//            Usuario u = new Usuario(email, nombre, ap1, ap2, pass, null, d_Date, 0);
//            registros.add(u);
//        }
//        return registros;
//    }
    //Metodos de tabla ingreso
    public void guardarIngreso(String ususario, String pais) throws Exception {

        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

        PreparedStatement stm = cnx.prepareStatement(COMANDO_INSERTAR_INGRESO);
        stm.clearParameters();
        stm.setString(1, ususario);
        // Manejo de las fechas
        Date d_Date = Calendar.getInstance().getTime();
        stm.setTimestamp(2, new Timestamp(d_Date.getTime()));
        stm.setString(3, pais);
        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }

        Conexion.obtenerInstancia().cerrarConexion();
    }

    public JSONArray getIngreso() throws Exception {
        //StringBuilder r=new StringBuilder();
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        Statement stm = cnx.createStatement();
        ResultSet rs
                = stm.executeQuery("select pais,(count(usuario))c from Ingreso group by pais order by c desc;");
        JSONArray registros = new JSONArray();
        int i = 0;
        while (rs.next() && i < 8) {
            String pais = rs.getString("pais");
            int cantidad = rs.getInt("c");
            Ingreso ingreso = new Ingreso(pais, cantidad);
            registros.put(ingreso.toJSON());
            i++;
        }
        return registros;
    }

    public String getIngresoString() throws Exception {
        StringBuilder r = new StringBuilder();
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        Statement stm = cnx.createStatement();
        ResultSet rs
                = stm.executeQuery("select pais,(count(usuario))c from Ingreso group by pais order by c desc;");
        JSONArray registros = new JSONArray();
        int i = 0;
        while (rs.next() && i < 8) {
            String pais = rs.getString("pais");
            int cantidad = rs.getInt("c");
            r.append(String.format("%s", pais + " *"));
            r.append(String.format("%s", cantidad + " *"));
            i++;
        }
        return r.toString();
    }

    public String verificarIngreso(String usuario) {
        try {
            Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

            PreparedStatement stm = cnx.prepareStatement(COMANDO_VERIFICAR_INGRESO);
            stm.clearParameters();
            stm.setString(1, usuario);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String var = rs.getString("horaIngreso");
                String var2 = rs.getString("pais");
                Conexion.obtenerInstancia().cerrarConexion();
                return "Ultima Conexión : " + var + " , " + var2;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean actualizarIngreso(String usuario, String pais) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        PreparedStatement stm = cnx.prepareStatement(COMANDO_ACTUALIZAR_INGRESO);
        stm.clearParameters();

        Date d_Date = Calendar.getInstance().getTime();
        stm.setTimestamp(1, new Timestamp(d_Date.getTime()));
        stm.setString(2, pais);
        stm.setString(3, usuario);
        if (stm.executeUpdate() != 1) {
            return false;
        }
        Conexion.obtenerInstancia().cerrarConexion();
        return true;
    }

    public boolean eliminarIngreso(String usuario) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

        PreparedStatement stm = cnx.prepareStatement(COMANDO_ELIMINAR_INGRESO);
        stm.clearParameters();
        stm.setString(1, usuario);
        if (stm.executeUpdate() != 1) {
            return false;
        }
        Conexion.obtenerInstancia().cerrarConexion();
        return true;
    }

    public boolean eliminarRegistroNoticia(String usuario) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

        PreparedStatement stm = cnx.prepareStatement(COMANDO_ELIMINAR_REGISTRO_NOTICIA);
        stm.clearParameters();
        stm.setString(1, usuario);
        if (stm.executeUpdate() != 1) {
            return false;
        }

        Conexion.obtenerInstancia().cerrarConexion();
        return true;
    }

    public int calcularNumero() throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select numero from noticia order by (numero);");
        int numero = 0;
        while (rs.next()) {
            numero = rs.getInt("numero");
        }
        return numero + 1;
    }

    //**************************************noticias***********************************
    public boolean guardarNoticia(int numero, String titulo, String descripcion, String usuario) throws Exception {

        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

        PreparedStatement stm = cnx.prepareStatement(COMANDO_INSERTAR_NOTICIA);
        stm.clearParameters();
        stm.setInt(1, numero);
        stm.setString(2, titulo);
        stm.setString(3, descripcion);
        stm.setString(4, usuario);
        stm.setTimestamp(5, new Timestamp(Calendar.getInstance().getTimeInMillis()));
        if (stm.executeUpdate() != 1) {
            return false;
        }

        Conexion.obtenerInstancia().cerrarConexion();
        return true;
    }

    public static String getUsuariosConectadosHTML(int p) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = null;

        if (p == 1) {
            rs = stm.executeQuery("SELECT USUARIO_ID,EMAIL,NAME,FECHA,STATUS "
                    + " FROM sesiones "
                    + " left join usuarios "
                    + " ON usuarios.EMAIL = sesiones.USUARIO_ID "
                    + " where sesiones.STATUS=1 "
                    + " GROUP BY usuarios.EMAIL "
                    + " order by (FECHA) desc;");
        } else {
            rs = stm.executeQuery("SELECT USUARIO_ID,EMAIL,NAME,FECHA,STATUS "
                    + " FROM sesiones "
                    + " left join usuarios "
                    + " ON usuarios.EMAIL = sesiones.USUARIO_ID "
                    + " where sesiones.STATUS!=1 "
                    + " GROUP BY usuarios.EMAIL "
                    + " order by (FECHA) desc;");
        }

        StringBuilder r = new StringBuilder();
        r.append("<table class=\"table tablaUsuarios table-striped table-hover \">");
        r.append(String.format("<tr><th>%s</th>",
                "ID"));
        r.append(String.format("<th><em>%s</em></th>",
                "nickname"));
        r.append(String.format("</th><th>%s</th>",
                "EMAIL"));

        r.append(String.format("</th><th>%s</th></tr>",
                "FECHA"));

        while (rs.next()) {
            //USUARIO_ID,EMAIL,NAME,fecha,STATUS
            String user_id = rs.getString("USUARIO_ID");
            String name = rs.getString("NAME");
            String email = rs.getString("EMAIL");
            String fecha = rs.getTimestamp("FECHA") + "";
            int status = rs.getInt("STATUS");

            r.append("<tr>");

            r.append(String.format("<td class=\"celdaEstado\">%s</td>",
                    String.format("<img alt=\"(status icon)\" src=\"%s\" />",
                            p == 1 ? "icons/status-online.png" : "icons/status-busy.png")));
            //r.append(String.format("<td>%s</td>", user_id));
            r.append(String.format("<td>%s</td>", name));
            r.append(String.format("<td>%s</td>", email));
            r.append(String.format("<td><span class='chat_date'>%s</span></td>", fecha));
            r.append("</tr>");

//            else {
//                r.append(String.format("<tr><td colspan=\"3\">%s</td></tr>",
//                        "(No hay registros en la base)"));
//            }
        }

        r.append("</table>");
        return r.toString();
    }

    public static String getComentariosHTML(int p) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = null;

        if (p == 1) {
            rs = stm.executeQuery("SELECT * FROM COMENTARIOS WHERE TIPO_MSJ=1;");
        } else {
            rs = stm.executeQuery("SELECT * FROM COMENTARIOS WHERE TIPO_MSJ!=1;");
        }

        StringBuilder r = new StringBuilder();

        while (rs.next()) {
            //USUARIO_ID,EMAIL,NAME,fecha,STATUS
//            String user_id = rs.getString("USUARIO_ID");
//            String name = rs.getString("NAME");
//            String email = rs.getString("EMAIL");
//            String fecha = rs.getTimestamp("FECHA") + "";
//            int status = rs.getInt("STATUS");

            r.append("<div class='chat_list'>");
            r.append("<div class='chat_people active_chat'>");
            r.append("<div class='chat_img'> <img src='https://ptetutorials.com/images/user-profile.png' alt='sunil'> </div>");
            r.append("<div class='chat_ib'>");
            r.append("<h5>"+rs.getString("USUARIO_ID")+" <span class='chat_date'>"+rs.getString("FECHA")+"</span></h5>");
            r.append("<p>"+rs.getString("DESCRIPCION")+"</p>");
         
            r.append("</div>");
            r.append("</div>");
            r.append("</div>");

//            else {
//                r.append(String.format("<tr><td colspan=\"3\">%s</td></tr>",
//                        "(No hay registros en la base)"));
//            }
        }

        return r.toString();
    }

    public static ArrayList getNumeroNoticias() throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(COMANDO_OBTENER_NUMERO_NOTICIA);
        ResultSetMetaData md = rs.getMetaData();
        ArrayList<Object> registros = new ArrayList<>();
        while (rs.next()) {
            int numero = rs.getInt("numero");
            registros.add(numero);
        }
        return registros;
    }

    public static String generarMenu() throws Exception {
        StringBuilder r = new StringBuilder();
        ArrayList usuarios = getNumeroNoticias();
        r.append("<select id=\"numero\" name=\"numero\" style=\"width:150px\">");
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) != null) {
                r.append("<option value=\"").append(usuarios.get(i)).append("\">");
                r.append(usuarios.get(i));
                r.append("</option>");

            }

        }

        r.append("</select>");
        return r.toString();
    }

    public boolean actualizarNoticia(String titulo, String descripcion, String email, int numero) throws Exception {

        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        PreparedStatement stm = cnx.prepareStatement(COMANDO_ACTUALIZAR_NOTICIA);

        stm.clearParameters();
        stm.setString(1, titulo);
        stm.setString(2, descripcion);
        stm.setString(3, email);
        stm.setTimestamp(4, new Timestamp(Calendar.getInstance().getTimeInMillis()));
        stm.setInt(5, numero);
        if (stm.executeUpdate() != 1) {
            return false;
        }
        Conexion.obtenerInstancia().cerrarConexion();
        return true;
    }

    public boolean eliminarNoticias(int numero) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        PreparedStatement stm = cnx.prepareStatement("delete from noticia where numero=?;");
        stm.clearParameters();
        stm.setInt(1, numero);
        if (stm.executeUpdate() != 1) {
            return false;
        }
        Conexion.obtenerInstancia().cerrarConexion();
        return true;
    }
}
