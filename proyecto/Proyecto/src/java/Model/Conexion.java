package Model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion implements Serializable {

    private static Conexion instancia = null;
    private static String PROTOCOLO = "jdbc:mysql:";
    private static String URL = "//localhost:3306/";
    private static String BASE_DATOS = "ICAIPROYECTO";
    private static String USUARIO = "root";
    private static String CLAVE = "root";
    private Connection cnx = null;
    String protocolo;
    String url;
    String baseDatos;
    String usuario;
    String clave;

    private Conexion(String protocolo, String url, String baseDatos, String usuario, String clave) {
        this.cnx = null;
        this.protocolo = protocolo;
        this.url = url;
        this.baseDatos = baseDatos;
        this.usuario = usuario;
        this.clave = clave;
    }

    private Conexion() throws Exception {
        this(PROTOCOLO, URL, BASE_DATOS, USUARIO, CLAVE);
    }

    public static Conexion obtenerInstancia() {
        if (instancia == null) {
            try {
                instancia = new Conexion();
            } catch (Exception exc) {
            }
        }
        return instancia;
    }

    public synchronized Connection obtenerConexion() throws Exception {
        
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(protocolo + url + baseDatos, usuario, clave);
       
        return cnx;
    }

    public void cerrarConexion() {
        try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }
    }

}
