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
public class Sumas2 implements Runnable{
 String nameId;
 private Thread t;

    public Sumas2(String nameId) {
        this.nameId = nameId;
        this.t = new Thread(this);
    }
 

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String name) {
        this.nameId = name;
    }
    public void  start(){
        this.t.start();
    }
    @Override
    public void run() {
      for (int i = 0; i <(long)9999999; i++) {
            System.out.println(this.nameId+i);
            
        }
    }
    
}
