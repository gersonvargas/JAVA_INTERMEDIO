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

public class Gestor implements Serializable {

    // Comandos SQL********************************************
    private static final String COMANDO_INSERTAR_USUARIO
            = "INSERT INTO USUARIOS"
            + "(EMAIL,NAME, TYPE,IMAGEN, PASSWORD) "
            + "VALUES (?,?,?,?,AES_ENCRYPT(?,'passw'));";
    private static final String COMANDO_INSERTAR_SESION
            = "INSERT INTO SESIONES"
            + "(USUARIO_ID,STATUS) "
            + "VALUES (?,?);";
    private static final String COMANDO_INSERTAR_COMENTARIO
            = "INSERT INTO COMENTARIOS"
            + "(DESCRIPCION,USUARIO_ID,TIPO_MSJ,IMAGEN) "
            + "VALUES (?,?,?,?);";
    private static final String COMANDO_INSERTAR_COMENTARIO_PRIVADO
            = "INSERT INTO COMENTARIOS_PERSONALES"
            + "(DESCRIPCION,USUARIO_DE,USUARIO_PARA) "
            + "VALUES (?,?,?);";

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

    private static final String COMANDO_FOTO = "SELECT IMAGEN from USUARIOS WHERE EMAIL=?;";

    public Gestor() {
    }

    public boolean guardarUsuario(String email, String nombre, String p_id, int type, String ruta_img, String pass) {
        try {
            Usuario nuevoRegistro = new Usuario(email, nombre, p_id, type, pass);
            Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

            PreparedStatement stm = cnx.prepareStatement(COMANDO_INSERTAR_USUARIO);
            stm.clearParameters();
            stm.setString(1, nuevoRegistro.getEmail());
            stm.setString(2, nuevoRegistro.getNombre());//(ID ,NAME, EMAIL,TYPE, PASSWORD)
            stm.setInt(3, nuevoRegistro.getType());
            stm.setString(4, ruta_img);
            stm.setString(5, nuevoRegistro.getPass());

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

    public boolean guardarComentario(String desc, String usuario, int tipo, String direccionImagen) {
        try {

            Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

            PreparedStatement stm = cnx.prepareStatement(COMANDO_INSERTAR_COMENTARIO);
            stm.clearParameters();
            stm.setString(1, desc);
            stm.setString(2, usuario);
            stm.setInt(3, tipo);
            stm.setString(4, direccionImagen);
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

    public boolean guardarComentarioPersonal(String desc, String usuario_de, String usuario_para) {
        try {

            Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

            PreparedStatement stm = cnx.prepareStatement(COMANDO_INSERTAR_COMENTARIO_PRIVADO);
            stm.clearParameters();
            stm.setString(1, desc);
            stm.setString(2, usuario_de);
            stm.setString(3, usuario_para);

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

    public String verificarUsuario(String email, String password) throws Exception {

        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        PreparedStatement stm = cnx.prepareStatement(COMANDO_VERIFICAR_USUARIO);
        stm.clearParameters();
        stm.setString(1, email);
        stm.setString(2, password);

        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            String id = rs.getString("EMAIL");
            String nombre = rs.getString("NAME");
            this.crearSesion(id, 1);
            Conexion.obtenerInstancia().cerrarConexion();
            return nombre;
        } else {
            Conexion.obtenerInstancia().cerrarConexion();
            return null;
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

    public String foto(String email) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

        PreparedStatement stm = cnx.prepareStatement(COMANDO_FOTO);
        stm.clearParameters();
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString("IMAGEN");

        } else {
            return "photo unavailable";
        }
    }

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
    //Metodos de tabla ingreso

    public boolean cerrarSesion(String ususario) throws Exception {

        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        String update = "UPDATE SESIONES SET STATUS=0 WHERE USUARIO_ID=?";
        PreparedStatement stm = cnx.prepareStatement(update);
        stm.clearParameters();
        stm.setString(1, ususario);
        if (stm.executeUpdate() != 1) {
            Conexion.obtenerInstancia().cerrarConexion();
            return false;
        }

        Conexion.obtenerInstancia().cerrarConexion();
        return true;
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
            rs = stm.executeQuery("SELECT USUARIO_ID,EMAIL,NAME,FECHA,STATUS,IMAGEN "
                    + " FROM sesiones "
                    + " left join usuarios "
                    + " ON usuarios.EMAIL = sesiones.USUARIO_ID "
                    + " where sesiones.STATUS!=1 "
                    + "  AND sesiones.USUARIO_ID not in(select sesiones.USUARIO_ID FROM sesiones where status=1) "
                    + " GROUP BY usuarios.EMAIL "
                    + " order by (FECHA) desc;");
        }

        StringBuilder r = new StringBuilder();
        r.append("<table class=\"table tablaUsuarios table-striped table-hover \">");
        r.append(String.format("<tr><th>%s</th>",
                "ID"));
        r.append(String.format("<th><em>%s</em></th>",
                "NICKNAME"));
        r.append(String.format("</th><th>%s</th>",
                "FECHA"));
        // r.append(String.format("</th><th>%s</th>",
        //       "COMENTAR"));
        r.append(String.format("</th><th>%s</th></tr>",
                "VER"));
        while (rs.next()) {
            String user_id = rs.getString("USUARIO_ID");
            String name = rs.getString("NAME");
            String email = rs.getString("EMAIL");
            String fecha = rs.getTimestamp("FECHA") + "";
            r.append("<tr>");
            r.append(String.format("<td class=\"celdaEstado\">%s</td>",
                    String.format("<img alt=\"(status icon)\" src=\"%s\" />",
                            p == 1 ? "icons/status-online.png" : "icons/status-busy.png")));
            r.append(String.format("<td>%s</td>", name));

            String aux2 = "<form method='POST' action='Chat.jsp'>"
                    + "<input type='hidden' name='usuario_para'  value='" + email + "'/>"
                    + " <div class='form-group'>"
                    + "      <div class='input-group mb-2'>"
                    + "             <div class='input-group-prepend'>"
                    + "                   <div class=''>"
                    + "                         <button type='submit'><i class='fa fa-comment text-info'></i></button>"
                    + "                    </div>"
                    + "             </div>"
                    + "      </div>"
                    + "</div>"
                    + "</form>";
            r.append(String.format("<td><span class='chat_date'>%s</span></td>", fecha));
            //           r.append(String.format("<td><span class='chat_date'>%s</span></td>", aux));
            r.append(String.format("<td><span class='chat_date'>%s</span></td>", aux2));
            r.append("</tr>");
        }

        r.append("</table>");
        return r.toString();
    }

    public static String getComentariosHTML(int p) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = null;

        if (p == 1) {
            rs = stm.executeQuery("SELECT COMENTARIOS.*,usuarios.IMAGEN USER,usuarios.NAME FROM COMENTARIOS LEFT JOIN usuarios ON usuarios.EMAIL = comentarios.USUARIO_ID WHERE TIPO_MSJ=1 ORDER BY comentarios.FECHA;");
        } else {
            rs = stm.executeQuery("SELECT * FROM COMENTARIOS WHERE TIPO_MSJ!=1;");
        }

        StringBuilder r = new StringBuilder();

        while (rs.next()) {
            r.append("<div class='chat_list'>");
            r.append("<div class='chat_people active_chat'>");
            r.append("<div class='chat_img'> <img src='" + rs.getString("USER") + "' alt='User'/> </div>");
            r.append("<div class='chat_ib'>");
            r.append("<h5>" + rs.getString("NAME") + " <span class='chat_date'>" + rs.getString("FECHA") + "</span></h5>");
            r.append("<p>" + rs.getString("DESCRIPCION") + "</p>");
            String image=rs.getString("IMAGEN");
            if(image!=null)
             r.append("<div class='chat_img'><a target='_blank' href='"+image+"'> <img src='" + image + "' alt='adjunto'/></a> </div>");
            r.append("</div>");
            r.append("</div>");
            r.append("</div>");
        }

        return r.toString();
    }

    public static String getComentariosPersonalesHTML(String usuario_de, String usuario_para) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

        PreparedStatement stm = cnx.prepareStatement("select COMENTARIO_ID, DESCRIPCION, FECHA, USUARIO_DE, USUARIO_PARA, TIPO_MSJ,USUARIOS.NAME,usuarios.IMAGEN FROM comentarios_personales left join usuarios ON usuarios.EMAIL = comentarios_personales.USUARIO_DE "
                + " where (USUARIO_DE=? and USUARIO_PARA=?) or (USUARIO_DE=? and USUARIO_PARA=?) ORDER BY FECHA DESC;");
        stm.clearParameters();
        stm.setString(1, usuario_de);
        stm.setString(2, usuario_para);
        stm.setString(3, usuario_para);
        stm.setString(4, usuario_de);
        ResultSet rs = null;
        rs = stm.executeQuery();
        StringBuilder r = new StringBuilder();

        while (rs.next()) {
            r.append("<div class='chat_list'>");
            r.append("<div class='chat_people active_chat'>");
            r.append("<div class='chat_img'> <img src='" + rs.getString("IMAGEN") + "' alt='sunil'> </div>");
            r.append("<div class='chat_ib'>");
            r.append("<h5> " + rs.getString("USUARIO_DE") + " <span class='chat_date'>" + rs.getString("FECHA") + "</span></h5>");
            r.append("<p>" + rs.getString("DESCRIPCION") + "</p>");
            r.append("</div>");
            r.append("</div>");
            r.append("</div>");
        }
        if (usuario_de != null && !usuario_de.equals(usuario_para)) {
            String aux = "<div class='type_msg'>"
                    + " <form method='POST' action='ComentarioPersonal'>"
                    + " <div class='input_msg_write'>"
                    + "         <input type='hidden' name='usuario_para'  value='" + usuario_para + "'/>"
                    + "          <textarea class='form-control'  name='comentario' placeholder='Comentar' required></textarea> "
                    + "          <button class='msg_send_btn' type='submit'><i class='fa fa-paper-plane-o text-info'></i></button>"
                    + "      </div>"
                    + "</form>"
                    + "</div>";
            r.append(aux);
        }
        return r.toString();
    }

    public static String getComentariosPersonalesSinFormHTML(String usuario_de, String usuario_para) throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();

        PreparedStatement stm = cnx.prepareStatement("select COMENTARIO_ID, DESCRIPCION, FECHA, USUARIO_DE, USUARIO_PARA, TIPO_MSJ FROM comentarios_personales\n"
                + "where (USUARIO_DE=? and USUARIO_PARA=?) or (USUARIO_DE=? and USUARIO_PARA=?)ORDER BY FECHA DESC;");
        stm.clearParameters();
        stm.setString(1, usuario_de);
        stm.setString(2, usuario_para);
        stm.setString(3, usuario_para);
        stm.setString(4, usuario_de);
        ResultSet rs = null;
        rs = stm.executeQuery();
        StringBuilder r = new StringBuilder();

        while (rs.next()) {
            r.append("<div class='chat_list'>");
            r.append("<div class='chat_people active_chat'>");
            r.append("<div class='chat_img'> <img src='https://ptetutorials.com/images/user-profile.png' alt='sunil'> </div>");
            r.append("<div class='chat_ib'>");
            r.append("<h5> " + rs.getString("USUARIO_DE") + " <span class='chat_date'>" + rs.getString("FECHA") + "</span></h5>");
            r.append("<p>" + rs.getString("DESCRIPCION") + "</p>");
            r.append("</div>");
            r.append("</div>");
            r.append("</div>");
        }

        return r.toString();
    }

    public static int getNumeroUsuarios() throws Exception {
        Connection cnx = Conexion.obtenerInstancia().obtenerConexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select count(*)NUMERO from usuarios;");
        ResultSetMetaData md = rs.getMetaData();
        ArrayList<Object> registros = new ArrayList<>();
        if (rs.next()) {
            int numero = rs.getInt("NUMERO");
            return numero;
        } else {
            return 0;
        }

    }

}
