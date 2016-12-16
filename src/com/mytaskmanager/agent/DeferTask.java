package com.mytaskmanager.agent;

import com.mytaskmanager.tasks.Task;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by renat on 13.12.2016.
 */
public class DeferTask implements Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        if (parameters.size() != 1) {
            System.out.println("Введены неверные параметры для команды deftsk.\n");
            return;
        }

        int TID;
        try {
            TID = Integer.parseInt(parameters.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Введены неверные параметры для команды deftsk.\n");
            return;
        }

        facade.deferTask(TID);
    }
}
