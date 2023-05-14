package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler implements Runnable
{
    final DataInputStream ournewDataInputstream;
    final DataOutputStream ournewDataOutputstream;
    final Socket mynewSocket;


    // Constructor
    public ClientHandler(Socket mynewSocket, DataInputStream ournewDataInputstream, DataOutputStream ournewDataOutputstream)
    {
        this.mynewSocket = mynewSocket;
        this.ournewDataInputstream = ournewDataInputstream;
        this.ournewDataOutputstream = ournewDataOutputstream;
    }

    @Override
    public void run()
    {
        String receivedString;
        String stringToReturn;
        while (true)
        {
            try {
                ournewDataOutputstream.writeUTF("Choose: [Date | Time]..\n"+
                        "Or Exit");

                // getting answers from client
                receivedString = ournewDataInputstream.readUTF();

                if(receivedString.equals("Exit"))
                {
                    System.out.println("Client " + this.mynewSocket + " sends exit...");
                    System.out.println("Connection closing...");
                    this.mynewSocket.close();
                    System.out.println("Closed");
                    break;
                }


                switch (receivedString) {

                    case "Date" :
                        ournewDataOutputstream.writeUTF("DUPA");
                        break;

                    case "Time" :
                        ournewDataOutputstream.writeUTF("DU_A");
                        break;

                    default:
                        ournewDataOutputstream.writeUTF("Invalid input");
                        break;
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