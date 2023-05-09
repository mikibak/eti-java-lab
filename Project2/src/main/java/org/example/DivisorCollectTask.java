package org.example;

import java.util.LinkedList;
import java.util.List;

public class DivisorCollectTask implements Task{

    private final long number;

    public DivisorCollectTask(long number) {
        this.number = number;
    }

    public Object execute() throws InterruptedException {
        List<Long> list = new LinkedList<>();

        for(long divisor = 1; divisor <= number; divisor++) {
            if(number % divisor == 0) {
                list.add(divisor);
            };
        }

        Thread.sleep(5000);

        return list;
    }

    @Override
    public Object resultHash() {
        return number;
    }
}
