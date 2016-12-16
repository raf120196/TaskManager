package com.mytaskmanager.agent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by renat on 14.12.2016.
 */
public class DfrTask implements Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        if (parameters.size() != 3) {
            System.out.println("Введены неверные параметры для команды dfrtsk.\n");
            return;
        }

        int TID;
        try {
            TID = Integer.parseInt(parameters.get(0));
        }
        catch (NumberFormatException e)
        {
            System.out.println("Введены неверные параметры для команды dfrtsk.\n");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = null;

        try {
            date = sdf.parse(parameters.get(1) + " " + parameters.get(2));
        } catch (ParseException e) {
            System.out.println("Дата введена не корректно. Повторите попытку.\n");
            return;
        }

        Calendar calendar = Calendar.getInstance();
        assert date != null;
        calendar.setTime(date);

        if (calendar.before(Calendar.getInstance())) {
            System.out.println("Введена не актуальная дата. Повторите попытку.\n");
            return;
        }

        facade.deferTask(TID, calendar);
    }
}
