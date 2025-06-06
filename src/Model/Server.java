package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int index;

    public Server(int index){
        this.index = index;
        tasks = new LinkedBlockingQueue<Task>();
        waitingPeriod = new AtomicInteger(0);
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    @Override
    public void run() {
        while (true){
            try {
                Task task = tasks.peek();
                if(task != null) {
                    waitingPeriod.decrementAndGet();
                    if (task.getServiceTime() == 1) {
                        tasks.poll();
                    } else {
                        tasks.peek().setServiceTime(tasks.peek().getServiceTime() - 1);
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Task[] getTasks(){
        return tasks.toArray(new Task[tasks.size()]);
    }


    @Override
    public String toString() {
        return "Server{" +
                "tasks=" + tasks +
                ", waitingPeriod=" + waitingPeriod +
                ", index=" + index +
                '}';
    }
}
