/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Helper.SessionManager;
import Model.Account;
import Model.Client;
import Model.Response;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author omarsabbagh
 */

public class GetClientsThread {
       Socket connectToServer;
       OutputStream out;
       BufferedReader in;
       String msg;
       String result;
       String serverName = "127.0.0.1";
       int port = 6060;
       Response response ;
       SessionManager sessionManager;
       public GetClientsThread(Response response){
           this.response = response;
           sessionManager = new SessionManager();
       }
       
       
       
   public void run() throws ClassNotFoundException {
     try {
         
            connectToServer = new Socket(serverName, port);
                        
            DataOutputStream dostream = new DataOutputStream(new BufferedOutputStream(connectToServer.getOutputStream()));

            dostream.writeUTF("Get-Clients");
            
            dostream.flush();
            
            ObjectInputStream ois = new ObjectInputStream(connectToServer.getInputStream());
            
            ArrayList<Client> result = (ArrayList<Client>) ois.readObject();
            
            response.setClients(result);
            
            connectToServer.close ();
            
            
      }catch(IOException e) {
         e.printStackTrace();
         
      }
   }

    
}