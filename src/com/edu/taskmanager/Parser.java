package com.edu.taskmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by renat on 12.12.2016.
 */
public class Parser {
    private Map<String, Object> operations = new HashMap<String, Object>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
    private boolean finish = false;

    public synchronized boolean getFlag() {
        return finish;
    }

    public void parse(String operator) {
        if (operator.equals("help")) {
            System.out.println("Раздел помощи:");
            System.out.println("addtsk <task_name> <description> <dd.MM.yyyy hh:mm>\tДобавление новой задачи.");
            System.out.println("deltsk <task_id>\tУдаление задачи с номером <task_id>.");
            System.out.println("deftsk <task_id>\tОтложить задание с номером <task_id> на 10 минут.");
            System.out.println("dfrtsk <task_id> <dd.MM.yyyy hh:mm\tОтложить задачу с номером <task_id> на новое время <dd.MM.yyyy hh:mm>.");
            System.out.println("help\tВыводит данную справочную информацию.");
            System.out.println("exit\tЗавершение работы планировщика задач.");
            return;
        }

        if (operator.equals("exit")) {
            finish = true;
            return;
        }

        int x = operator.indexOf(' ');
        if (x == -1) {
            System.out.println("Введена неверная команда.");
            return;
        }

        String[] arr = operator.split(" ");
        String nameOperation = operator.substring(0, x - 1);
        if (operations.containsKey(nameOperation)) {
            operations.get(nameOperation);
        }

        Date date = null;
        try {
            date = sdf.parse("12.12.2016 22:09");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        System.out.println("Yes!");
    }
}
