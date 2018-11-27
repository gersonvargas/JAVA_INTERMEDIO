/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BDADMIN
 */
public class LoginValidator {
    public static boolean checkUser(String user, String pass){
        boolean ok=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/icai?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
            PreparedStatement ps=cnx.prepareStatement("select * from persona where email='"+user+"' and password='"+pass+"';");
            ResultSet set=ps.executeQuery();
            ok=set.next();
        } catch (ClassNotFoundException | SQLException ex) {
            ok=false;
        }
        return ok;
    }
}
