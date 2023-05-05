package org.example;

import java.util.HashMap;

public class Results {
    private HashMap<Integer, Boolean> primes;

    public Results() {
        this.primes = new HashMap<>();
    }

    public void add(int number, boolean isDivisible) {
        if(primes.containsKey(number) && isDivisible) {
            primes.put(number, false);
        } else if(!primes.containsKey(number)) {
            primes.put(number, !isDivisible);
        }
    }

    public boolean get(int number) {
        return primes.get(number);
    }
}
