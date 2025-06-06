package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class TimeStrategy extends Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        int x = 999;
        Server s = null;
        for(Server server : servers) {
            int y = server.getWaitingPeriod().get();
            if(y < x){
                s = server;
                x = y;
            }
        }
        if(s != null) {
            s.addTask(task);
        }

    }
}
