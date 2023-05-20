package org.example;

import java.util.Scanner;
import java.io.*;
import java.net.*;


public class SocketClient
{
    public static void main(String[] args) throws IOException
    {
        Messsage messsage = new Messsage();
        String str;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
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

            Thread thread = new Thread(new Reader(ournewsocket, ournewDataInputstream, ournewDataOutputstream));
            thread.start();


            while (true)
            {
                // Reading data using readLine
                str = reader.readLine();
                ournewDataOutputstream.writeUTF(String.valueOf(n));
            }


            //ourNewscanner.close();
            //ournewDataInputstream.close();
            //ournewDataOutputstream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
