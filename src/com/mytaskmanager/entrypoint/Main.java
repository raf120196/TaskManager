package com.mytaskmanager.entrypoint;

import com.mytaskmanager.console.Monitor;
import com.mytaskmanager.tasks.TaskManager;
import com.mytaskmanager.tasks.TaskManagerInterface;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by renat on 11.12.2016.
 */
public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        System.setProperty("console.encoding","Cp866");
        TaskManagerInterface taskManager = TaskManager.getInstance();
        Monitor monitor = Monitor.getInstance();
        Thread threadManager = new Thread((Runnable) taskManager);
        threadManager.start();
        Thread threadMonitor = new Thread(monitor);
        threadMonitor.start();
    }
}
