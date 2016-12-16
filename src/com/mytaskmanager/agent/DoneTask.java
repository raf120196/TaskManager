package com.mytaskmanager.agent;

import java.util.ArrayList;

/**
 * Created by renat on 16.12.2016.
 */
public class DoneTask implements Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        facade.doneTask(Integer.parseInt(parameters.get(0)));
    }
}