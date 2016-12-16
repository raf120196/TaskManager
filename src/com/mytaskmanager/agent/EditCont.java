package com.mytaskmanager.agent;

import java.util.ArrayList;

/**
 * Created by renat on 15.12.2016.
 */
public class EditCont implements Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        if (parameters.size() != 2) {
            System.out.println("Введены неверные параметры для команды editcont.\n");
            return;
        }

        int TID;
        try {
            TID = Integer.parseInt(parameters.get(0));
        }
        catch (NumberFormatException e)
        {
            System.out.println("Введены неверные параметры для команды editcont.\n");
            return;
        }

        facade.editContactsTask(TID, parameters.get(1));
    }
}
