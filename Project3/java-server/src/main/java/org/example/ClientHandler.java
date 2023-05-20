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
                receivedString = ournewDataInputstream.readUTF();
                if(receivedString.equals("DROP DATABASE")) {
                    break;
                }

                for(Socket socket : SocketServer.sockets) {
                    if(socket != mynewSocket) {
                        DataInputStream ournewDataInputstream = new DataInputStream(socket.getInputStream());
                        DataOutputStream ournewDataOutputstream = new DataOutputStream(socket.getOutputStream());
                        ournewDataOutputstream.writeUTF(receivedString);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
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