package BusinessLogic;

import Model.Server;
import Model.Task;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {

    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfClients;
    public SelectionPolicy selectionPolicy;

    private Scheduler scheduler;
    private List<Task> generatedTasks;
    private Random random = new Random();
    private JTextArea logArea;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfClients, SelectionPolicy selectionPolicy,  JTextArea logArea) {
        this.logArea = logArea;
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.selectionPolicy = selectionPolicy;
        generateNTasks();
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        scheduler.changeStrategy(selectionPolicy);
    }

    private void generateNTasks(){
        generatedTasks = new ArrayList<Task>();
        for(int i = 0; i < numberOfClients; i++){
            Task task = new Task(i, random.nextInt(maxProcessingTime) + 1 , random.nextInt((maxProcessingTime -minProcessingTime) + 1) + minProcessingTime);
            generatedTasks.add(task);
        }
    }

    @Override
    public void run() {

        int currentTime = 0;

        List<Thread> threads = scheduler.getThreads();
        for(Thread thread : threads){
            thread.start();
        }

        while(currentTime < timeLimit){


            if(!generatedTasks.isEmpty()) {
                Iterator<Task> iterator = generatedTasks.iterator();
                while (iterator.hasNext()) {
                    Task task = iterator.next();
                    if (task.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(task);
                        iterator.remove();
                    }
                }

            }

            System.out.println("Time:" + currentTime);
            System.out.println("Clients:" + generatedTasks);
            for(Server server : scheduler.getServers()){
                System.out.println("Server:" + server);
            }

            currentTime++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            java.nio.file.Files.writeString(
                    java.nio.file.Path.of("simulation_log.txt"),
                    logArea.getText()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
