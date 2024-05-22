package ro.tuc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks; //queue
    private AtomicInteger waitingPeriod;
    private boolean stop;
    private boolean done;

    public Server() {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger();
    }

    public void addTask(Task newTask) {
        if (waitingPeriod.get() != 0)
            newTask.setWaitingTime(waitingPeriod.get() - 1);
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime()); //trebe adaugat de fapt service time la task
    }

    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public int getTasksSize() {
        return tasks.size();
    }

    @Override
    public void run() {
        while (!stop) {// gata totul break
            Task currentTask = tasks.peek();
            if (currentTask != null) {
//                System.out.println(tasks);
                if (currentTask.getServiceTime() <= 1) {
                    tasks.poll();
                    currentTask.setServiceTime(0);
                } else {
                    currentTask.decrementServiceTime();
                }
            }
            if (waitingPeriod.intValue() > 0)
                waitingPeriod.decrementAndGet();
            // decrementez cu 1 service time la task-ul curent
            // daca st la task curent < 1, il scot din coada
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        stop = true;
    }

    public boolean isDone() {
        return stop;
    }


    public Task[] getTasks() {
        Task[] res = new Task[tasks.size()];
        int n = 0;
        for (Task it : tasks)
            res[n++] = it;
        return res;
    }

    public String printQueue(int i, String str) {
        System.out.println("Q" + i + ": " + tasks);
        str += "Q" + i + ": " + tasks + "\n";
        return str;
    }
}
