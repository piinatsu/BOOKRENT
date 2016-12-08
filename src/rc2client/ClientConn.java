/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc2client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Udmin
 */
public class ClientConn extends Thread{
    Socket s = null;
    ObjectInputStream in;
    ObjectOutputStream out;
    RC2Client clientGUI;
    
    public ClientConn(RC2Client exClientGUI)  
    {
        clientGUI = exClientGUI;
        try
        {
            //String serverIP = "127.0.0.1";
            //String serverIP = "173.16.19.12";
            //String serverIP = "192.168.1.131";
            //String serverIP = "192.168.1.110"; //HOME
            //String serverIP = "192.168.0.116"; //IAN
            //String serverIP = "172.10.7.40"; //LAB
            //String serverIP = "172.16.84.71";
            String serverIP = "192.168.1.10";//FIRA
            int serverPort = 8678;
            s = new Socket(serverIP, serverPort);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        } 
        
        catch (UnknownHostException e)
        {
            System.out.println("Sock: "+e.getMessage());
        }        
        catch (EOFException e)
        {
            System.out.println("EOF: "+e.getMessage());
        }        
        catch (IOException e)
        {
            System.out.println("IO: "+e.getMessage());
        }     
    }
    
    public Object send(String str) throws IOException, ClassNotFoundException
    {
	out.writeObject(str);
	Object data = in.readObject();
        out.flush();
//      out.reset();
	return data;
    }
    
}
