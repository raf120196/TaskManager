package com.edu.taskmanager;

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
    private ArrayList<String> contacts;

    public Task(String name, String description, Calendar taskTime, String contacts) {
        this.name = name;
        this.description = description;
        this.taskTime = taskTime;
        this.contacts = new ArrayList<String>();
        this.contacts.add(contacts);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getTaskTime() {
        return taskTime;
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public void setDate(Calendar newDate) {
        this.taskTime = newDate;
    }
}
