package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public abstract class Strategy {
    public abstract void addTask(List<Server> servers, Task task);
}
