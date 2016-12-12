package com.edu.taskmanager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by renat on 10.12.2016.
 */
public class TaskManager implements TaskManagerInterface, Runnable {
    private Map<Integer, Task> tasks;
    private int countTasks = 0;
    private int countActiveTasks = 0;
    private ConsoleAgent consoleAgent;
    private AlertingService alertManager;
    private static final TaskManager taskManager = new TaskManager();
    private Parser parser;

    private TaskManager() {
        this.tasks = new HashMap<Integer, Task>();
        this.consoleAgent = ConsoleAgent.getInstance();
        this.alertManager = AlertingService.getInstance();
    }

    public static TaskManager getInstance() {
        return taskManager;
    }

    @Override
    public int addTask(String name, String description, Calendar taskTime, String contacts) {
        this.tasks.put(countTasks + 1, new Task(name, description, taskTime, contacts));
        countActiveTasks++;
        return ++countTasks;
    }

    @Override
    public boolean deferTask(int TID) {
        if (!tasks.containsKey(TID)) {
            return false;
        }

        Task tmp = tasks.get(TID);
        Calendar c = tmp.getTaskTime();
        c.add(Calendar.MINUTE, 10);
        tmp.setDate(c);

        return true;
    }

    @Override
    public boolean deferTask(int TID, Calendar newDate) {
        if (!tasks.containsKey(TID)) {
            return false;
        }

        Task tmp = tasks.get(TID);
        if (newDate.before(tmp.getTaskTime())) {
            return false;
        }

        tmp.setDate(newDate);

        return true;
    }

    @Override
    public boolean deleteTask(int TID) {
        if (!tasks.containsKey(TID)) {
            return false;
        }

        tasks.remove(TID);
        return true;
    }

    @Override
    public void publicizeUser(Task task) {
        alertManager.publicizeUserAboutTask(task);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                for (Task task : tasks.values()) {
                    Date d = new Date();
                    if (Math.abs(task.getTaskTime().getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) < 10000) {
                        publicizeUser(task);
                        System.exit(0);
                    }
                }
            }
        }
    }
}
