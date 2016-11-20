/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Helper.SessionManager;
import Model.Client;
import Model.Response;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omarsabbagh
 */
public class CheckLoginThread implements Runnable {

    Socket connectToServer;
    OutputStream out;
    BufferedReader in;
    String msg;
    String result;
    String serverName = "127.0.0.1";
    int port = 6060;
    Response response;

    SessionManager sessionManager;

    public CheckLoginThread(Response response) {
        this.response = response;
        sessionManager = new SessionManager();
    }

    public void run() {
        try {

            connectToServer = new Socket(serverName, port, InetAddress.getByName(serverName), Integer.parseInt(sessionManager.get_port()));

            DataOutputStream dostream = new DataOutputStream(new BufferedOutputStream(connectToServer.getOutputStream()));

            dostream.writeUTF("Check_login");

            dostream.flush();

            Client client = new Client();

            client.setClient_ip(serverName);
            client.setClient_port(Integer.parseInt(sessionManager.get_port()));

            ObjectOutputStream oos = new ObjectOutputStream(connectToServer.getOutputStream());
            oos.writeObject(client);
            oos.flush();

            DataInputStream dis = new DataInputStream(new BufferedInputStream(connectToServer.getInputStream()));
            boolean b = dis.readBoolean();
            if (b) {
                ObjectInputStream ois = new ObjectInputStream(connectToServer.getInputStream());
                Client returnedClient = (Client) ois.readObject();
                sessionManager.set_client_id(returnedClient.getClient_id());
                sessionManager.set_port(returnedClient.getClient_port() + "");
                sessionManager.set_domain(returnedClient.getClient_domain());
                sessionManager.set_ip(returnedClient.getClient_ip());
                response.setStatus(200);
            } else {
                response.setStatus(404);
            }
            connectToServer.close();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckLoginThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
