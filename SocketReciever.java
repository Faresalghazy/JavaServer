/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketreciever;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author fares
 */
public class SocketReciever {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
     
        int portNumber;

        //Check if user specified port number, otherwise find any open port
        if (args.length != 0) {
            portNumber = Integer.parseInt(args[0]);
        } else {
            portNumber = 0;
        }

        try {

            //Create socket( this pc)
            try (ServerSocket serverSocket1 = new ServerSocket(portNumber)) {
                portNumber = serverSocket1.getLocalPort();
                //Notify user of which port is being used
                System.out.println("Created socket at port " + portNumber);
                //Read data
                while (true) {
                    Socket clientSocket = serverSocket1.accept();

                    DataInputStream DIS = new DataInputStream(clientSocket.getInputStream()); //get input from socket
                  
                    //Print recieved value
                    System.out.println(DIS.readUTF());

                }

            }
        } catch (Exception e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getClass().getSimpleName());
        }

    }

}
