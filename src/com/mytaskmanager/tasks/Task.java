package com.mytaskmanager.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by renat on 10.12.2016.
 */
public class Task {
    private String name;
    private String description;
    private Calendar taskTime;
    private String contacts;

    Task(String name, String description, Calendar taskTime, String contacts) {
        this.name = name;
        this.description = description;
        this.taskTime = taskTime;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Calendar getTaskTime() {
        return taskTime;
    }

    void setDate(Calendar newDate) {
        this.taskTime = newDate;
    }

    public String getContacts() {
        return contacts;
    }

    void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
