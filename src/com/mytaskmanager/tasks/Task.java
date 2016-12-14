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

    public Task(String name, String description, Calendar taskTime, String contacts) {
        this.name = name;
        this.description = description;
        this.taskTime = taskTime;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getTaskTime() {
        return taskTime;
    }

    public void setDate(Calendar newDate) {
        this.taskTime = newDate;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
