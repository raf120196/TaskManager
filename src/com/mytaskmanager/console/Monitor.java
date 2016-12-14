package com.mytaskmanager.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Thread.yield;

/**
 * Created by renat on 13.12.2016.
 */
public class Monitor implements Runnable{
    private static Monitor ourInstance = new Monitor();

    public static Monitor getInstance() {
        return ourInstance;
    }

    private Monitor() {
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser();
        String operation = "";

        synchronized (this)
        {
            while (!parser.getFlag()) {
                try {
                    operation = in.readLine();
                } catch (IOException ignored) {
                }

                if (!"".equals(operation)) {
                    parser.parse(operation.toLowerCase());
                }

                yield();
            }
        }
    }
}
