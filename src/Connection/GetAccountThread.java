/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Encryption.AESEncryption;
import Helper.SessionManager;
import Model.Account;
import Model.Response;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author omarsabbagh
 */
public class GetAccountThread {
       Socket connectToServer;
       OutputStream out;
       BufferedReader in;
       String msg;
       String result;
       SessionManager sessionManager;
       String serverName = "127.0.0.1";
       int port = 6060;
       
       Response response ;
       
       public GetAccountThread(Response response){
           this.response = response;
           sessionManager = new SessionManager();
           
       }
       
       
       
   public void run() throws ClassNotFoundException {
     try {
         
            connectToServer = new Socket(serverName, port);
                        
            DataOutputStream dostream = new DataOutputStream(new BufferedOutputStream(connectToServer.getOutputStream()));

            dostream.writeUTF("Get-Account");
            
            dostream.flush();
            
            
            try{
            
            ArrayList<Account> result = (ArrayList<Account>) AESEncryption.decrypt(connectToServer.getInputStream());
            
            response.setAccounts(result);
            
            response.setStatus(200);

            }catch(InvalidKeyException e)
            {
                response.setStatus(401);
            }
            
            connectToServer.close ( );
            
            
      }catch(IOException e) {
         e.printStackTrace();
         
      }    catch (NoSuchAlgorithmException ex) {
               Logger.getLogger(GetAccountThread.class.getName()).log(Level.SEVERE, null, ex);
           } catch (NoSuchPaddingException ex) {
               Logger.getLogger(GetAccountThread.class.getName()).log(Level.SEVERE, null, ex);
           }
   }

    
}