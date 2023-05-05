package org.example;

public class Worker implements Runnable {

    private Resource resource;
    private Results results;
    private Task task;
    public Worker(Resource resource, Results results) {
        this.resource = resource;
        this.results = results;
    }
    @Override
    public void run() {
        while(true) {
            try {
                task = resource.take();
                if(task != null) {
                    results.add((Integer)task.resultHash(), task.execute());
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
}