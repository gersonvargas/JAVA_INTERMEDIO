/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import org.json.JSONObject;

/**
 *
 * @author Gerson
 */
public class Ingreso implements Serializable{

    String pais;
    int cantidad;

    public Ingreso(String pais, int cantidad) {
        this.pais = pais;
        this.cantidad = cantidad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public JSONObject toJSON() {
        JSONObject resultado = new JSONObject();
        resultado
                .put("pais", getPais())
                .put("cantidad", getCantidad());
        return resultado;
    }
}
