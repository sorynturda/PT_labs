package ro.tuc.strategy;

import ro.tuc.Server;
import ro.tuc.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
//        int minSize = Integer.MAX_VALUE;
        Server minServer = servers.get(0);
//        for (Server server : servers)
//            minSize = Integer.min(minSize, server.getTasksSize());
        for (Server server : servers)
            if (server.getTasksSize() < minServer.getTasksSize()) {
//                server.addTask(t);
//                return;
                minServer = server;
            }
        minServer.addTask(t);
    }
}
