package com.edu.taskmanager;

/**
 * Created by renat on 11.12.2016.
 */
public class AlertingService implements AlertInterface {
    private static AlertingService ourInstance = new AlertingService();

    public static AlertingService getInstance() {
        return ourInstance;
    }

    private AlertingService() {
    }

    public void publicizeUserAboutTask(Task task) {
        System.out.println(task.getDescription());
    }
}
