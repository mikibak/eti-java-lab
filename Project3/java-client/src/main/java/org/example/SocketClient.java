package org.example;

import java.util.Scanner;
import java.io.*;
import java.net.*;


public class SocketClient
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            Scanner ourNewscanner = new Scanner(System.in);
            InetAddress inetadress = InetAddress.getByName("localhost");
            // establishing the connection
            Socket ournewsocket = new Socket(inetadress, 5056);
            DataInputStream ournewDataInputstream = new DataInputStream(ournewsocket.getInputStream());
            DataOutputStream ournewDataOutputstream = new DataOutputStream(ournewsocket.getOutputStream());
            // In the following loop, the client and client handle exchange data.
            while (true)
            {
                System.out.println(ournewDataInputstream.readUTF());
                String tosend = ourNewscanner.nextLine();
                ournewDataOutputstream.writeUTF(tosend);
                // Exiting from a while loo should be done when a client gives an exit message.
                if(tosend.equals("Exit"))
                {
                    System.out.println("Connection closing... : " + ournewsocket);
                    ournewsocket.close();
                    System.out.println("Closed");
                    break;
                }

                // printing date or time as requested by client
                String newresuiltReceivedString = ournewDataInputstream.readUTF();
                System.out.println(newresuiltReceivedString);
            }

            ourNewscanner.close();
            ournewDataInputstream.close();
            ournewDataOutputstream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
