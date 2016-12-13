package com.edu.taskmanager;

/**
 * Created by renat on 13.12.2016.
 */
public class Facade {
    private static Facade ourInstance = new Facade();

    public static Facade getInstance() {
        return ourInstance;
    }

    private Facade() {
    }
}
