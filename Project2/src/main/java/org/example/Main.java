package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int N_OF_WORKERS = 5;
    public static void main(String[] args) {
        Resource resource = new Resource();
        Results results = new Results();

        List<Worker> workers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < N_OF_WORKERS; i++) {
            Worker worker = new Worker(resource, results);
            Thread thread = new Thread(worker);
            workers.add(worker);
            threads.add(thread);
            thread.start();
        }

        while(true) {
            System.out.println("Enter an integer: ");
            Scanner in = new Scanner(System.in);
            int number = in.nextInt();

            if (number == 0) {
                break;
            }
            else if(number == 1) {
                boolean isPrime = results.get(in.nextInt());
                System.out.println(isPrime);
            }

            for(int divisor = 2; divisor < number/2; divisor++) {
                Task task = new DivisorTask(number, divisor);
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