package org.example;

import java.util.HashMap;

public class PrimeResults {
    private class Result {
        public Result(boolean isPrime) {
            this.isPrime = isPrime;
            this.counter = 0;
        }
        public boolean isPrime;
        public int counter;
    }
    private HashMap<Long, Result> primes;
    private static final Object lock = new Object();

    public PrimeResults() {
        this.primes = new HashMap<>();
    }

    public void add(long number, boolean isDivisible) {
        synchronized (lock) {
            if(primes.containsKey(number) && isDivisible) {
                Result result = primes.get(number);
                //no need to wait for all threads
                //result.counter = number/2;
                result.isPrime = false;
                result.counter++;
                primes.put(number, result);
            } else if(primes.containsKey(number) && !isDivisible) {
                Result result = primes.get(number);
                result.counter++;
                primes.put(number, result);
            } else if(!primes.containsKey(number)) {
                primes.put(number, new Result(!isDivisible));
            }

            if(primes.get(number).counter == number/2 - 3) {
                //calculations complete
                System.out.print("Calculations complete for number " + number);
                if(primes.get(number).isPrime) {
                    System.out.println(": Number is prime");
                } else {
                    System.out.println(": Number is not prime");
                }
            }
        }
    }

    public boolean get(long number) {
        synchronized (lock) {
            return primes.get(number).isPrime;
        }
    }
}
