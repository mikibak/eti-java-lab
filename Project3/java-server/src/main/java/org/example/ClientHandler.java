package org.example;

import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler implements Runnable
{
    final DataInputStream ournewDataInputstream;
    final DataOutputStream ournewDataOutputstream;
    final ObjectInputStream objectInputStream;
    final Socket mynewSocket;


    // Constructor
    public ClientHandler(Socket mynewSocket, DataInputStream ournewDataInputstream, DataOutputStream ournewDataOutputstream, ObjectInputStream objectInputStream)
    {
        this.mynewSocket = mynewSocket;
        this.ournewDataInputstream = ournewDataInputstream;
        this.ournewDataOutputstream = ournewDataOutputstream;
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void run()
    {
        String receivedString;
        String stringToReturn;
        while (true)
        {
            try {
                ournewDataOutputstream.writeUTF("ready");
                System.out.println("sending ready");

                // getting answers from client
                receivedString = ournewDataInputstream.readUTF();

                int n = Integer.parseInt(receivedString);
                ournewDataOutputstream.writeUTF("ready for messages");
                System.out.println("sending ready for messages");

                Messsage messages[] = new Messsage[n];

                for(int i = 0; i < n; i++) {
                    messages[i] =  (Messsage) objectInputStream.readObject();
                    System.out.println("Content of message " + i + ": " + messages[i].getContent());
                }

                ournewDataOutputstream.writeUTF("finished");
                System.out.println("Connection closing...");
                this.mynewSocket.close();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        try
        {
            // closing resources
            this.ournewDataInputstream.close();
            this.ournewDataOutputstream.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}