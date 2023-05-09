package org.example;

public class DivisorCollectTask implements Task{

    private final long number;
    private final long divisor;

    public DivisorCollectTask(long number, long divisor) {
        this.number = number;
        this.divisor = divisor;
    }

    public Object execute() throws InterruptedException {
        //Thread.sleep(1000);
        if(number % divisor == 0) {
            return divisor;
        } else return (long)1;
    }

    @Override
    public Object resultHash() {
        return number;
    }
}
