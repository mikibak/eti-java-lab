package org.example;

import java.util.Scanner;
import java.io.*;
import java.net.*;


public class SocketClient
{
    public static void main(String[] args) throws IOException
    {
        Messsage messsage = new Messsage();
        String received;
        int n = 10;
        try
        {
            Scanner ourNewscanner = new Scanner(System.in);
            InetAddress inetadress = InetAddress.getByName("localhost");
            // establishing the connection
            Socket ournewsocket = new Socket(inetadress, 5056);
            DataInputStream ournewDataInputstream = new DataInputStream(ournewsocket.getInputStream());
            DataOutputStream ournewDataOutputstream = new DataOutputStream(ournewsocket.getOutputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ournewsocket.getOutputStream());

            while (true)
            {
                received = ournewDataInputstream.readUTF();
                System.out.println(received);
                if(received.equals("ready")) {
                    System.out.println("Sending n");
                    ournewDataOutputstream.writeUTF(String.valueOf(n));
                    break;
                }
            }

            // In the following loop, the client and client handle exchange data.
            while (true)
            {
                received = ournewDataInputstream.readUTF();
                System.out.println(received);
                if(received.equals("ready for messages")) {
                    for(int i = 0; i < n; i++) {
                        System.out.println("Sending message " + i);
                        objectOutputStream.writeObject(messsage);
                    }
                    break;
                }
            }

            ourNewscanner.close();
            ournewDataInputstream.close();
            ournewDataOutputstream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
