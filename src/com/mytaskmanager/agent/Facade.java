package com.mytaskmanager.agent;

import com.mytaskmanager.tasks.TaskManager;

import java.util.Calendar;

/**
 * Created by renat on 13.12.2016.
 */
public class Facade {
    private static Facade ourInstance = new Facade();
    private static TaskManager singletonTaskManager = TaskManager.getInstance();

    public static Facade getInstance() {
        return ourInstance;
    }

    private Facade() {
    }

    public void addTask(String name, String description, Calendar calendar, String contacts) {
        singletonTaskManager.addTask(name, description, calendar, contacts);
    }
}
