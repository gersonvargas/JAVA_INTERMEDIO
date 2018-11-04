/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

/**
 *
 * @author BDADMIN
 */
public class Sumas extends Thread {
    String nameId;

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String name) {
        this.nameId = name;
    }
    
    @Override
    public void run(){
        
        for (int i = 0; i <(long)9999999; i++) {
            System.out.println(this.nameId+i);
            
        }
    }
    
}
