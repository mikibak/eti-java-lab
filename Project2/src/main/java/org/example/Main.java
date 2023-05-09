package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int N_OF_WORKERS = 5;
    public static void main(String[] args) throws IOException {
        Resource resource = new Resource();
        DivisorCollectResults results = new DivisorCollectResults();

        List<Worker> workers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < N_OF_WORKERS; i++) {
            Worker worker = new Worker(resource, results);
            Thread thread = new Thread(worker);
            workers.add(worker);
            threads.add(thread);
            thread.start();
        }

        //clear file
        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"));
        writer.close();

        //read initial numbers
        try {
            File myObj = new File("liczby.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                long number;
                try{
                    number = Long.parseLong(data);
                } catch (NumberFormatException n) {
                    number = 0;
                    System.out.println("Number too long!");
                }

                if (number == 0) {
                    continue;
                }

                for(long divisor = 1; divisor <= number; divisor++) {
                    Task task = new DivisorCollectTask(number, divisor);
                    resource.put(task);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while(true) {
            System.out.println("Enter an integer: ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Reading data using readLine
            String string = reader.readLine();

            // Printing the read line
            System.out.println(string);

            long number;
            try{
                number = Long.parseLong(string);
            } catch (NumberFormatException n) {
                number = 0;
                System.out.println("Number too long!");
            }

            if (number == 0) {
                break;
            }

            for(long divisor = 1; divisor <= number; divisor++) {
                Task task = new DivisorCollectTask(number, divisor);
                resource.put(task);
            }
        }

        try {
            for(Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}