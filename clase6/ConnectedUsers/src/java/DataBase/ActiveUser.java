/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class ActiveUser implements HttpSessionBindingListener{
    
public static Map<ActiveUser,HttpSession> login=new ConcurrentHashMap<>();
public Long id;
public String userName;

public static String[] getConnectedUsers(){
    String []users =new String[login.size()];
    int i=0;
    for (Map.Entry<ActiveUser, HttpSession> entry : login.entrySet()) {
        ActiveUser key = entry.getKey();
        HttpSession value = entry.getValue();
        users[i++]=key.userName;
    }
    return users;
}

    public ActiveUser(String userName) {
        this.userName = userName;
    }

@Override
    public boolean equals(Object o){
        return (o instanceof ActiveUser)&&
                (id!=null)? id.equals(((ActiveUser)o).id):o==this
                &&(userName!=null)?
                userName.equals(((ActiveUser)o).userName):
                o==this;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.userName);
        return hash;
    }
@Override
    public void valueBound(HttpSessionBindingEvent evt){
        HttpSession session=login.remove(this);
        if(session!=null){
            session.invalidate();
        }
        login.put(this, evt.getSession());
    }
@Override
   public void valueUnbound(HttpSessionBindingEvent evt){
        HttpSession session=login.remove(this);
        if(session!=null){
            session.invalidate();
        }
        login.put(this, evt.getSession());
    }
    
}
