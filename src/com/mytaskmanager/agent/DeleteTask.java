package com.mytaskmanager.agent;

import java.util.ArrayList;

/**
 * Created by renat on 13.12.2016.
 */
public class DeleteTask implements Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        if (parameters.size() != 1) {
            System.out.println("Неверные параметры для команды deltsk. Повторите попытку.\n");
            return;
        }

        int TID;
        try {
            TID = Integer.parseInt(parameters.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Введены неверные параметры для команды dfrtsk.\n");
            return;
        }
        facade.deleteTask(TID);
    }
}
