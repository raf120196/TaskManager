package com.mytaskmanager.agent;

import com.mytaskmanager.tasks.TaskManager;

import java.util.Calendar;

/**
 * Created by renat on 13.12.2016.
 */
class Facade {
    private static Facade ourInstance = new Facade();
    private static TaskManager singletonTaskManager = TaskManager.getInstance();

    static Facade getInstance() {
        return ourInstance;
    }

    private Facade() {
    }

    void addTask(String name, String description, Calendar calendar, String contacts) {
        singletonTaskManager.addTask(name, description, calendar, contacts);
    }

    void deleteTask(int TID) {
        singletonTaskManager.deleteTask(TID);
    }

    void deferTask(int TID) {
        singletonTaskManager.deferTask(TID);
    }

    void deferTask(int TID, Calendar calendar) {
        singletonTaskManager.deferTask(TID, calendar);
    }

    void editNameTask(int TID, String newName) {
        singletonTaskManager.editNameTask(TID, newName);
    }

    void editDescriptionTask(int TID, String newDescription) {
        singletonTaskManager.editDescriptionTask(TID, newDescription);
    }

    void editContactsTask(int TID, String newContacts) {
        singletonTaskManager.editContactsTask(TID, newContacts);
    }

    void helpOut() {
        singletonTaskManager.helpOut();
    }

    void listTasks() {
        singletonTaskManager.listTasks();
    }

    void finishManager() {
        singletonTaskManager.finishManager();
    }

    void doneTask(int TID) {
        singletonTaskManager.doneTask(TID);
    }
}
