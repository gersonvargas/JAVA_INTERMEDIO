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
public class Comentario {

    int comentario_id;
    String descripcion;
    
    String usuario_id;
    int tipo_msj;

    public Comentario(int comentario_id, String descripcion, String usuario_id, int tipo_msj) {
        this.comentario_id = comentario_id;
        this.descripcion = descripcion;
        this.usuario_id = usuario_id;
        this.tipo_msj = tipo_msj;
    }

    public int getComentario_id() {
        return comentario_id;
    }

    public void setComentario_id(int comentario_id) {
        this.comentario_id = comentario_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getTipo_msj() {
        return tipo_msj;
    }

    public void setTipo_msj(int tipo_msj) {
        this.tipo_msj = tipo_msj;
    }
    
}
