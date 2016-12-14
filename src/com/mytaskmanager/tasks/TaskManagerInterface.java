package com.mytaskmanager.tasks;

import java.util.Calendar;

/**
 * Created by renat on 10.12.2016.
 */
public interface TaskManagerInterface {
    int addTask(String name, String description, Calendar taskTime, String contacts);

    boolean deferTask(int TID);

    boolean deferTask(int TID, Calendar newDate);

    boolean deleteTask(int TID);

    void publicizeUser(Task task);

    void editNameTask(int TID, String name);

    void editDescriptionTask(int TID, String description);

    void editDateTask(int TID, Calendar calendar);

    void editContactsTask(int TID, String contacts);
}
