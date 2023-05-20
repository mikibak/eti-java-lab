package org.example;

import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reader implements Runnable
{
    final DataInputStream ournewDataInputstream;
    final DataOutputStream ournewDataOutputstream;
    final Socket mynewSocket;


    // Constructor
    public Reader(Socket mynewSocket, DataInputStream ournewDataInputstream, DataOutputStream ournewDataOutputstream)
    {
        this.mynewSocket = mynewSocket;
        this.ournewDataInputstream = ournewDataInputstream;
        this.ournewDataOutputstream = ournewDataOutputstream;
    }

    @Override
    public void run()
    {
        String receivedString;
        while (true)
        {
            try {
                receivedString = ournewDataInputstream.readUTF();
                System.out.println(receivedString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*try
        {
            // closing resources
            //this.ournewDataInputstream.close();
            this.ournewDataOutputstream.close();

        }catch(IOException e){
            e.printStackTrace();
        }*/
    }
}