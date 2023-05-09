package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DivisorCollectResults {

    private HashMap<Long, List<Long>> divisors;
    private static final Object lock = new Object();
    private final String filename = "results.txt";

    public DivisorCollectResults() {
        this.divisors = new HashMap<>();
    }

    public void whenAppendStringUsingBufferedWritter_thenOldContentShouldExistToo(long number, List<Long> divisors)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.append(number+": ");

        for(long div : divisors) {
            writer.append(' ');
            writer.append(div+"");
        }
        writer.append('\n');

        writer.close();
    }

    public void add(long number, long divisor) throws IOException {
        synchronized (lock) {
            if(divisors.get(number) == null) {
                divisors.put(number, new LinkedList<>());
                divisors.get(number).add((long)1);
            } else if(divisor != (long)1) {
                divisors.get(number).add(divisor);
            }

            if(divisor == number) {
                //calculations complete
                System.out.print("Calculations complete for number " + number);
                System.out.println(divisors.get(number));
                //write to file

                whenAppendStringUsingBufferedWritter_thenOldContentShouldExistToo(number, divisors.get(number));

            }
        }
    }

    public List<Long> get(long number) {
        synchronized (lock) {
            return divisors.get(number);
        }
    }
}
