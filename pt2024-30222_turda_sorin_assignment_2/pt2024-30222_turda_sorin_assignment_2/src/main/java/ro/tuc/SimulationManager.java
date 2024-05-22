package ro.tuc;

import ro.tuc.gui.SimulationFrame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {
    //data read from UI
    private int timeLimit; //maximum processing time read from UI
    private int maxProcessingTime;
    private int minProcessingTime;
    private int minArrivalTime;
    private int maxArrivalTime ;
    private int numberOfServers; //queue
    private int numberOfClients; //de la tastatura //taskuri
    private int clientsDone;
    private double averageWaitingTime;
    private double averageServiceTime;
    public SelectionPolicy selectionPolicy;
    //entity responsible with queue management and client distribution
    private Scheduler scheduler;
    private int peekHour;
    private int maxQueues;
    //pool of tasks (client shopping in the store)
    private Log log;
    private List<Task> generatedTasks, generatedTasksClone;

    public SimulationManager() {
        // initialize the scheduler
        // => create and start numberOfServers threads
        // => initialize selection strategy => createStrategy
        // initialize frame to display simulation
    }

    private void init() {
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        generateNRandomTasks();
    }

    public void setParameters(int a, int b, int c, int d, int e, int f, int g, int p) {
        timeLimit = a;
        minArrivalTime = b;
        maxArrivalTime = c;
        minProcessingTime = d;
        maxProcessingTime = e;
        numberOfServers = f;
        numberOfClients = g;
        if (p == 0)
            selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
        else
            selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        init();
    }

    private void generateNRandomTasks() {
        generatedTasks = new ArrayList<>();
        generatedTasksClone = new ArrayList<>();
        Random rn = new Random();
        for (int id = 1; id <= numberOfClients; id++) {
            int arrivalTime = rn.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            int processingTime = rn.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            generatedTasks.add(new Task(id, arrivalTime, processingTime));
            averageServiceTime += processingTime;
        }
        generatedTasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getArrivalTime() - o2.getArrivalTime();
            }
        });
        try {
            writeGeneratedTasks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeGeneratedTasks() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("generatedTasks.txt"));
        for (Task task : generatedTasks) {
            writer.write(task.toString() + "\n");
            generatedTasksClone.add(task);
        }
        writer.close();
    }

    @Override
    public void run() {
        int currentTime = 0;
        String logOutput = "";

        log = new Log(numberOfClients, numberOfServers, minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime, timeLimit);
        while (currentTime < timeLimit) {
            // iterate generatedTasks list and pick tasks that have the
            //arrivalTime equal with the currentTime
            //  - send task to queue by calling the dispatchTask method
            //from Scheduler
            //  - delete cilent from list
            // update UI frame
            System.out.println(currentTime);
            log.append(currentTime + "\n");
            if (generatedTasks.isEmpty())
                scheduler.stop();
            ArrayList<Task> tasksAdded = new ArrayList<>();
            for (Task task : generatedTasks)
                if (task.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(task);
                    tasksAdded.add(task);
                }
            for (Task task : tasksAdded)
                generatedTasks.remove(task);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("Waiting list: ");
            log.append("Waiting list: ");
            for (Task task : generatedTasks) {
                System.out.print(task + ", ");
                log.append(task + ", ");
            }
            log.append("\n");
            System.out.println();
            log.append(scheduler.printQueues());
            if(maxQueues < scheduler.getQueuesSize()){
                peekHour = currentTime;
                maxQueues = scheduler.getQueuesSize();
            }
            currentTime++;
            if (scheduler.done())
                break;
            //wait an interval of 1 second
        }
        scheduler.stop();
        scheduler.stopAll();
        for (Task task : generatedTasksClone) {
            System.out.println(task + ", " + task.getWaitingTime());
            if (task.getServiceTime() == 0) {
                clientsDone++;
                averageWaitingTime += task.getWaitingTime();
            } else
                averageServiceTime -= task.getServiceTime();
        }
        averageServiceTime /= clientsDone;
        averageWaitingTime /= clientsDone;
        System.out.println("peek hour: " +  peekHour);
        log.append("peek hour: " + peekHour + "\n");
        System.out.println("tasks done: " + clientsDone);
        log.append("tasks done: " + clientsDone + "\n");
        System.out.println("average waiting time: " + averageWaitingTime + "\naverage service time = " + averageServiceTime);
        log.append("average waiting time: " + averageWaitingTime + "\naverage service time = " + averageServiceTime + "\n");
    }


//    public static void main(String[] args) {
//        SimulationManager gen = new SimulationManager();
//        Thread t = new Thread(gen);
//        gen.begin(t);
//    }

    private void begin(Thread t) {
        try {
            t.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
