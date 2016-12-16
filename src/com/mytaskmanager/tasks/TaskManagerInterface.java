package com.mytaskmanager.tasks;

import java.util.Calendar;

/**
 * Created by renat on 10.12.2016.
 */
public interface TaskManagerInterface {
    void addTask(String name, String description, Calendar taskTime, String contacts);

    void deferTask(int TID);

    void deferTask(int TID, Calendar newDate);

    void deleteTask(int TID);

    void publicizeUser(int TID, Task task);

    void finishManager();

    void helpOut();

    void editNameTask(int TID, String name);

    void editDescriptionTask(int TID, String description);

    void editContactsTask(int TID, String contacts);

    void listTasks();

    void doneTask(int TID);
}
