package ro.tuc.strategy;

import ro.tuc.Server;
import ro.tuc.Task;

import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task t);
}
