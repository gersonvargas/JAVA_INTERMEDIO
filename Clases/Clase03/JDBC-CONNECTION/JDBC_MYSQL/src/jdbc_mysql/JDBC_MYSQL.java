/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author GVARGAS
 */
public class JDBC_MYSQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gerson?"
                    + "user=root&password=root");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from gerson.usuario;");
            while (rs.next()) {
                String name = rs.getString("nombre");
                System.out.println(" Nombre: " + name);
                String first_name = rs.getString("first_name");
                System.out.println(" First Name: " + first_name);
                String last_name = rs.getString("last_name");
                System.out.println(" First Name: " + last_name);
                Date birth_day = rs.getDate("birth_day");
                System.out.println(" Birth day: " + birth_day.toString());
                Date hire_day = rs.getDate("birth_day");
                System.out.println(" Hire day: " + hire_day.toString());
                /*
                 emp_no INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
   nombre VARCHAR(20) NOT NULL,
   birth_day DATE,
   first_name VARCHAR(20),
   last_name VARCHAR(20),
   hire_date DATE,
   gender INT
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
