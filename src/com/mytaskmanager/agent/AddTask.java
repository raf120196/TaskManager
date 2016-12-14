package com.mytaskmanager.agent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by renat on 13.12.2016.
 */
public class AddTask implements Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        if (parameters.size() != 5)
        {
            System.out.println("Неверные параметры для команды addtsk. Повторите попытку.");
            return;
        }

        String name = parameters.get(0), description = parameters.get(1), time = parameters.get(2) + " " + parameters.get(3),
                    contacts = parameters.get(4);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Date date = null;

        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            System.out.println("Дата введена не корректно.");
            return;
        }

        Calendar calendar = Calendar.getInstance();
        assert date != null;
        calendar.setTime(date);

        if (calendar.before(Calendar.getInstance())) {
            System.out.println("Введена не актуальная дата.");
            return;
        }

        facade.addTask(name, description, calendar, contacts);
    }
}
