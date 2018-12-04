/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entidades;

/**
 *
 * @author BDADMIN
 */
public class Sesion {
    
    int id;
    String usuario;
    int status;

    public Sesion(int id, String usuario, int status) {
        this.id=id;
        this.usuario=usuario;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


   

    public Object[] toStringArray() {
        Object[] r = new Object[6];
        r[0] = getId();
        r[1] = getUsuario();
        r[2] = getStatus();
        return r;
    }
}
