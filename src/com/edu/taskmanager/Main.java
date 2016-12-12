package com.edu.taskmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by renat on 11.12.2016.
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        TaskManagerInterface taskManager = TaskManager.getInstance();
        //new Thread((Runnable) taskManager).start();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            Date date = sdf.parse(br.readLine());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Date date = sdf.parse("11.12.2016 22:57");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        taskManager.addTask("Ложись спать!", "Пора отдохнуть...", calendar, "renatfara@mail.ru");

        taskManager.run();
    }
}
