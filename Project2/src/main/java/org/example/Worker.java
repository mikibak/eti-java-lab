package org.example;

import java.io.IOException;

public class Worker implements Runnable {

    private Resource resource;
    private DivisorCollectResults results;
    private Task task;
    public Worker(Resource resource, DivisorCollectResults results) {
        this.resource = resource;
        this.results = results;
    }
    @Override
    public void run() {
        while(true) {
            try {
                task = resource.take();
                if(task != null) {
                    results.add((Long)task.resultHash(), (Long)task.execute());
                }
            } catch (InterruptedException ignored) {
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}