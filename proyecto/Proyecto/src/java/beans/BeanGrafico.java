/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

public class BeanGrafico implements Serializable{
    private String ingreso;

    public String getIngreso() {
      //  ingreso=getIngresoString();
 
        return "Hola mundo";
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }
    
}
