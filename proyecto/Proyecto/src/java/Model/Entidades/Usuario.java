/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entidades;

import java.io.Serializable;

public class Usuario implements Serializable{

    String email;
    String nombre;
    String Id;
    String pass;
    int type;

    public Usuario(String email, String nombre, String p_id,int type, String pass) {
        this.email = email;
        this.nombre = nombre;
        this.Id=p_id;
        this.type=type;
        this.pass=pass;
    }

    @Override
    public String toString() {
        String r = "Registro: "
                + String.valueOf(email) + ","
                + String.valueOf(nombre) + ","
                + String.valueOf(Id) + ","
                + String.valueOf(type) + ","
                + String.valueOf(pass) ;
        return r;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return Id;
    }

    public String getPass() {
        return pass;
    }

    public int getType() {
        return type;
    }

   

    public Object[] toStringArray() {
        Object[] r = new Object[6];
        r[0] = getEmail();
        r[1] = getNombre();
        r[2] = getId();
        r[3] = getPass();
        r[4] = getType();
        return r;
    }

}
