package com.mytaskmanager.agent;

import java.util.ArrayList;

/**
 * Created by renat on 15.12.2016.
 */
public class EditDescr implements Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        if (parameters.size() != 2) {
            System.out.println("Введены неверные параметры для команды editdescr.\n");
            return;
        }

        int TID;
        try {
            TID = Integer.parseInt(parameters.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Введены неверные параметры для команды editdescr.\n");
            return;
        }

        facade.editDescriptionTask(TID, parameters.get(1));
    }
}
