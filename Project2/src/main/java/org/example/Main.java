package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static int N_OF_WORKERS = 2;
    public static void main(String[] args) throws IOException, InterruptedException {
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
                long number = getLongFromString(data);

                if (number == 0) {
                    continue;
                }

                Task task = new DivisorCollectTask(number);
                resource.put(task);
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

            if(Objects.equals(string, "exit")) break;

            long number = getLongFromString(string);

            if (number == 0) continue;

            Task task = new DivisorCollectTask(number);
            resource.put(task);
        }

        for(Thread thread : threads) {
            resource.clearTasks();
            thread.interrupt();
            System.out.println("Closed thread");
        }
    }

    private static long getLongFromString(String str) {
        long number;
        try{
            number = Long.parseLong(str);
        } catch (NumberFormatException n) {
            number = 0;
            System.out.println("Number too long!");
        }
        return number;
    }
}