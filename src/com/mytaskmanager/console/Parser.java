package com.mytaskmanager.console;

import com.mytaskmanager.agent.*;

import java.util.*;

/**
 * Created by renat on 12.12.2016.
 */
class Parser {
    private Map<String, Command> operations = new HashMap<String, Command>();
    private boolean finish = false;

    synchronized boolean getFlag() {
        return finish;
    }

    Parser() {
        Command commandToAdd = new AddTask();
        operations.put("addtsk", commandToAdd);
        Command commandToDelete = new DeleteTask();
        operations.put("deltsk", commandToDelete);
        Command commandToDefer = new DeferTask();
        operations.put("deftsk", commandToDefer);
        Command commandToDfr = new DfrTask();
        operations.put("dfrtsk", commandToDfr);
        Command commandToEditName = new EditName();
        operations.put("editname", commandToEditName);
        Command commandToEditDescr = new EditDescr();
        operations.put("editdescr", commandToEditDescr);
        Command commandToEditCont = new EditCont();
        operations.put("editcont", commandToEditCont);
        Command commandToHelp = new Help();
        operations.put("help", commandToHelp);
        Command commandToExit = new Exit();
        operations.put("exit", commandToExit);
        Command commandListTasks = new ListTasks();
        operations.put("listtasks", commandListTasks);
    }

    void parse(String operator) {
        int x = operator.indexOf(" : ");
        if (x == -1) {
            if (operator.equals("exit")) {
                finish = true;
            }

            if (operations.containsKey(operator)) {
                operations.get(operator).execute(new ArrayList<String>());
            } else {
                System.out.println("Введена не существующая команда. Повторите попытку.\n");
            }
            return;
        }

        String[] arr = operator.split(" : ");
        if (!operations.containsKey(arr[0])) {
            System.out.println("Введена не существующая команда. Повторите попытку.\n");
            return;
        }

        List<String> parametres = new ArrayList<>();
        parametres.addAll(Arrays.asList(arr).subList(1, arr.length));
        operations.get(arr[0]).execute((ArrayList<String>) parametres);
    }
}
