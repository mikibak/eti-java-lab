package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DivisorCollectResults {

    private final HashMap<Long, List<Long>> divisors;
    private static final Object lock = new Object();

    public DivisorCollectResults() {
        this.divisors = new HashMap<>();
    }

    public void writeResultsToFile(Object hash, Object result)
            throws IOException {

        long number = (Long)hash;
        List<Long> divisors = (List<Long>)result;

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        writer.append(number+": ");

        for(long div : divisors) {
            writer.append(' ');
            writer.append(div+"");
        }
        writer.append('\n');

        writer.close();
    }

    public void add(Object objectHash, Object objectResult) throws IOException {
        long number = (Long)objectHash;
        List<Long> divisorList = (List<Long>)objectResult;
        synchronized (lock) {

            divisors.put(number, divisorList);

            System.out.print("Calculations complete for number " + number);
            System.out.println(divisors.get(number));

            writeResultsToFile(number, divisors.get(number));

        }
    }

    public List<Long> get(long number) {
        synchronized (lock) {
            return divisors.get(number);
        }
    }
}
