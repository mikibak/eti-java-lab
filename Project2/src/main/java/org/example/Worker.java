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
        boolean running = true;
        while(true) {
            try {
                if (!running) {
                    return;
                }
                task = resource.take();
                if(task != null) {
                    results.add(task.resultHash(), task.execute());
                }
            } catch (InterruptedException e) {
                running = false;
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}