package com.edu.taskmanager;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by renat on 10.12.2016.
 */
public interface TaskManagerInterface {
    int addTask(String name, String description, Calendar taskTime, String contacts);

    boolean deferTask(int TID);

    boolean deferTask(int TID, Calendar newDate);

    boolean deleteTask(int TID);

    void publicizeUser(Task task);

    void run();
}
