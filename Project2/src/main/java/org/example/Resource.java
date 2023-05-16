package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Resource {
    private Queue<Task> tasks;

    public Resource() {
        this.tasks = new LinkedList<>();
    }
    public synchronized Task take() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait();//Wait, maybe someone will put sth here.
        }
        Task ret = tasks.remove();
        return ret;
    }
    public synchronized void put(Task task) {
        this.tasks.add(task);
        notifyAll();
    }

    public void clearTasks() {
        this.tasks.clear();
    }
}