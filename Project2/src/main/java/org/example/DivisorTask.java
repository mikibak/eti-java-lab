package org.example;

public class DivisorTask implements Task{

    private final int number;
    private final int divisor;

    public DivisorTask(int number, int divisor) {
        this.number = number;
        this.divisor = divisor;
    }

    public boolean execute() {
        return number % divisor == 0;
    }

    @Override
    public Object resultHash() {
        return number;
    }
}
