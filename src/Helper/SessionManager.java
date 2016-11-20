/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import java.util.prefs.Preferences;

/**
 *
 * @author omarsabbagh
 */
public class SessionManager {
    Preferences prefs;
    
    final String domain = "domain";
    final String ip = "ip";
    final String port = "port";
    final String client_id = "client_id";
    final String account_id = "account_id";
    
    
    public SessionManager(){
        prefs = Preferences.userNodeForPackage(Helper.SessionManager.class);
    }
    
    public void set_domain(String s){
        System.out.println("domain has been set "+s);
        prefs.put(domain, s);
    }
    
    public String get_domain(){
        return prefs.get(domain,"");
    }
    
    public void set_ip(String s){
        System.out.println("ip has been set "+s);
        prefs.put(ip, s);
    }
    
    public String get_ip(){
        return prefs.get(ip, "");
    }
    
    public void set_port(String s){
        System.out.println("port has been set "+s);
        prefs.put(port, s);
    }
    
    public String get_port(){
        return prefs.get(port, "");
    }
    
   public void set_client_id(int i){
        System.out.println("clinet_id has been set "+i);
        prefs.put(client_id,i+"");
   }
    
   public int get_client_id(){
       return Integer.parseInt(prefs.get(client_id,"-1"));
   }
   
   public void set_account_id(int i ){
       System.out.println("account_id has been set "+i);
       prefs.put(account_id, i+"");
   }
   
   public int get_account_id(){
       return Integer.parseInt(prefs.get(account_id,"-1"));
   }
   
    
    
    
    
}
