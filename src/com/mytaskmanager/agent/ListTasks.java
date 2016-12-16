package com.mytaskmanager.agent;

import java.util.ArrayList;

/**
 * Created by renat on 15.12.2016.
 */
public class ListTasks implements Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        facade.listTasks();
    }
}
