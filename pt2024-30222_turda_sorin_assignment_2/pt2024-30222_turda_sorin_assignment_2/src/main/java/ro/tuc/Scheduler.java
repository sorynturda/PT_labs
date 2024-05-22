package ro.tuc;

import ro.tuc.strategy.ShortestQueueStrategy;
import ro.tuc.strategy.TimeStrategy;
import ro.tuc.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer; //verificare sa nu fie server plin
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        servers = new ArrayList<>();
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        changeStrategy(SelectionPolicy.SHORTEST_QUEUE);
        // for maxNoServers
        // - create server object
        // create thread with the object
        ExecutorService executorService = Executors.newFixedThreadPool(maxNoServers);

        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server();
            servers.add(server);
            executorService.execute(server);
        }
        executorService.shutdown();
    }

    public void changeStrategy(SelectionPolicy policy) {
        //apply strategy patter to instantiate the strategy with the concrete
        //strategy corresponding to policy
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }
    }

    public void dispatchTask(Task t) {
        for (Server server : servers)
            if (server.getTasksSize() < maxTasksPerServer) {
                strategy.addTask(servers, t);
                return;
            }
    }

    public void stop() {
        for (Server server : servers)
            if (server.getTasksSize() < 1)
                server.stop();
    }

    public boolean done() {
        for (Server server : servers)
            if (!server.isDone())
                return false;
        return true;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void stopAll() {
        for (Server server : servers)
            server.stop();
    }

    public String printQueues() {
        int i=0;
        String str = "";
        for(Server server : servers)
            str = server.printQueue(++i, str);
        return str;
    }

    public int getQueuesSize() {
        int size=0;
        for(Server server : servers)
            size+=server.getTasksSize();
        return size;
    }
}