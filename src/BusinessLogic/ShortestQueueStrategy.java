package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ShortestQueueStrategy extends Strategy{
    @Override
    public void addTask(List<Server> servers, Task task) {
        int x = 999;
        Server s = null;
        for (Server server : servers) {
            int y = server.getTasks().length;
            if(y < x){
                x = y;
                s = server;
            }
        }
        if(s != null) {
            s.addTask(task);
        }
    }
}
