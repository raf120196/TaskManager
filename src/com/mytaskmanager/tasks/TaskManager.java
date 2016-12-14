package com.mytaskmanager.tasks;

import com.mytaskmanager.agent.Facade;
import com.mytaskmanager.alert.*;
import com.mytaskmanager.console.Parser;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.yield;

/**
 * Created by renat on 10.12.2016.
 */
public class TaskManager implements TaskManagerInterface, Runnable {
    private ConcurrentHashMap<Integer, Task> tasks;
    private int countTasks = 0;
    private int countActiveTasks = 0;
    private AlertInterface alertManager = AlertingService.getInstance();;
    private static final TaskManager taskManager = new TaskManager();

    private TaskManager() {
        this.tasks = new ConcurrentHashMap<>();
    }

    public static TaskManager getInstance() {
        return taskManager;
    }

    @Override
    public int addTask(String name, String description, Calendar taskTime, String contacts) {
        this.tasks.put(countTasks + 1, new Task(name, description, taskTime, contacts));
        countActiveTasks++;
        System.out.println("Задача успешно добавлена!");
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

    @Override
    public void editNameTask(int TID, String name) {

    }

    @Override
    public void editDescriptionTask(int TID, String description) {

    }

    @Override
    public void editDateTask(int TID, Calendar calendar) {

    }

    @Override
    public void editContactsTask(int TID, String contacts) {

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
                for (Integer TID : tasks.keySet()) {
                    Task task = tasks.get(TID);
                    if (Math.abs(task.getTaskTime().getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) < 10000) {
                        publicizeUser(task);
                        tasks.remove(TID);
                    }
                }
            }

            yield();
        }
    }
}