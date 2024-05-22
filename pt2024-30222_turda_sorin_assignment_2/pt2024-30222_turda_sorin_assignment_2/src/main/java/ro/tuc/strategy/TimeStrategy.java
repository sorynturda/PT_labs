package ro.tuc.strategy;

import ro.tuc.Server;
import ro.tuc.SimulationManager;
import ro.tuc.Task;

import java.util.List;

public class TimeStrategy implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
//        int minTime = Integer.MAX_VALUE;
        Server minServer = servers.get(0);
//        for (Server server : servers)
//            minTime = Integer.min(minTime, server.getWaitingPeriod());
        for (Server server : servers)
            if (minServer.getWaitingPeriod() >  server.getWaitingPeriod()) {
//                server.addTask(t);
//                return;
                minServer = server;
            }
        minServer.addTask(t);
    }
}
