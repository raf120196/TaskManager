package com.edu.taskmanager;

/**
 * Created by renat on 11.12.2016.
 */
public class ConsoleAgent {
    private static ConsoleAgent ourInstance = new ConsoleAgent();

    public static ConsoleAgent getInstance() {
        return ourInstance;
    }

    private ConsoleAgent() {
    }
}
