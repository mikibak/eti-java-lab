package org.example;

import java.util.HashMap;

public class Results {
    private class Result {
        public Result(boolean isPrime) {
            this.isPrime = isPrime;
            this.counter = 0;
        }
        public boolean isPrime;
        public int counter;
    }
    private HashMap<Integer, Result> primes;

    public Results() {
        this.primes = new HashMap<>();
    }

    public void add(int number, boolean isDivisible) {
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

    public boolean get(int number) {
        return primes.get(number).isPrime;
    }
}
