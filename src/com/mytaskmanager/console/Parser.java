package com.mytaskmanager.console;

import com.mytaskmanager.agent.*;

import java.util.*;

/**
 * Created by renat on 12.12.2016.
 */
public class Parser {
    private Command commandToAdd = new AddTask();
    private Command commandToDelete = new DeleteTask();
    private Command commandToDefer = new DeferTask();
    private Command commandToDfr = new DfrTask();
    private Command commandToEdit = new EditTask();
    private Map<String, Command> operations = new HashMap<String, Command>();
    private boolean finish = false;

    synchronized boolean getFlag() {
        return finish;
    }

    public Parser() {
        operations.put("addtsk", commandToAdd);
        operations.put("deltsk", commandToDelete);
        operations.put("deftsk", commandToDefer);
        operations.put("dfrtsk", commandToDfr);
        operations.put("edittsk", commandToEdit);
    }

    void parse(String operator) {
        if (operator.equals("help")) {
            System.out.println("Раздел помощи:");
            System.out.println("addtsk <task_name> <description> <dd.MM.yyyy hh:mm> <contacts>\tДобавление новой задачи.");
            System.out.println("deltsk <task_id>\tУдаление задачи с номером <task_id>.");
            System.out.println("deftsk <task_id>\tОтложить задание с номером <task_id> на 10 минут.");
            System.out.println("dfrtsk <task_id> <dd.MM.yyyy hh:mm\tОтложить задачу с номером <task_id> на новое время <dd.MM.yyyy hh:mm>.");
            System.out.println("edittsk <task_id> <task_name> <description> <dd.MM.yyyy hh:mm>\tДобавление новой задачи.");
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
            System.out.println("Введена неверная команда. Повторите попытку.");
            return;
        }

        String[] arr = operator.split(" ");
        if (!operations.containsKey(arr[0])) {
            System.out.println("Введена неверная команда. Повторите попытку.");
            return;
        }

        List<String> parametres = new ArrayList<>();
        for (int i = 1; i < arr.length; i++)
        {
            parametres.add(arr[i]);
        }
        operations.get(arr[0]).execute((ArrayList<String>) parametres);
    }
}
