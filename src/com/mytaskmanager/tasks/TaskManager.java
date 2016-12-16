package com.mytaskmanager.tasks;

import com.mytaskmanager.alert.*;
import com.mytaskmanager.log.LogWriter;
import com.mytaskmanager.log.XMLWriter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.yield;

/**
 * Created by renat on 10.12.2016.
 */
public class TaskManager implements TaskManagerInterface, Runnable {
    private ConcurrentHashMap<Integer, Task> tasks;
    private ConcurrentHashMap<Integer, Boolean> flagIsPublicize;
    private int countTasks = 0;
    private AlertInterface alertManager = AlertingService.getInstance();
    private LogWriter logWriter = (LogWriter) XMLWriter.getInstance();
    private boolean finish = false;
    private static final TaskManager taskManager = new TaskManager();

    private TaskManager() {
        this.tasks = new ConcurrentHashMap<>();
        this.flagIsPublicize = new ConcurrentHashMap<>();
    }

    public static TaskManager getInstance() {
        return taskManager;
    }

    @Override
    public void addTask(String name, String description, Calendar taskTime, String contacts) {
        this.tasks.put(++countTasks, new Task(name, description, taskTime, contacts));
        this.flagIsPublicize.put(countTasks, false);
        System.out.println("Задача успешно добавлена. Ей присвоен идентификатор " + countTasks + ".\n");
        logWriter.addElement(countTasks, tasks.get(countTasks));
    }

    @Override
    public void deferTask(int TID) {
        if (!tasks.containsKey(TID)) {
            System.out.println("Задачи с таким идентификатором не обнаружено. Повторите попытку.\n");
            return;
        }

        Task tmp = tasks.get(TID);
        Calendar c = tmp.getTaskTime();
        c.add(Calendar.MINUTE, 10);
        System.out.println("Задача №" + TID + " отложена на 10 минут.\n");
        tmp.setDate(c);

        logWriter.addElement(TID, tasks.get(TID));
        flagIsPublicize.replace(TID, false);
    }

    @Override
    public void deferTask(int TID, Calendar newDate) {
        if (!tasks.containsKey(TID)) {
            System.out.println("Задачи с таким идентификатором не обнаружено. Повторите попытку.\n");
            return;
        }

        Task tmp = tasks.get(TID);
        tmp.setDate(newDate);
        System.out.println("Задача №" + TID + " перенесена на " + dateOut(newDate) + ".\n");

        logWriter.addElement(TID, tasks.get(TID));
        flagIsPublicize.replace(TID, false);
    }

    @Override
    public void deleteTask(int TID) {
        if (!tasks.containsKey(TID)) {
            System.out.println("Задачи с таким идентификатором не обнаружено. Повторите попытку.\n");
            return;
        }

        tasks.remove(TID);
        flagIsPublicize.remove(TID);
        System.out.println("Задача с идентификатором " + TID + " успешно удалена.\n");
        return;
    }

    @Override
    public void publicizeUser(int TID, Task task) {
        if (!flagIsPublicize.get(TID)) {
            alertManager.publicizeUserAboutTask(TID, task);
        }
    }

    @Override
    public void finishManager() {
        finish = true;
    }

    @Override
    public void helpOut() {
        System.out.println("Раздел помощи:");
        System.out.println("addtsk <task_name> <description> <dd.MM.yyyy hh:mm> <contacts>\tДобавление новой задачи.");
        System.out.println("deltsk <task_id>\tУдаление задачи с номером <task_id>.");
        System.out.println("deftsk <task_id>\tОтложить задание с номером <task_id> на 10 минут.");
        System.out.println("dfrtsk <task_id> <dd.MM.yyyy hh:mm\tОтложить задачу с номером <task_id> на новое время <dd.MM.yyyy hh:mm>.");
        System.out.println("editname <task_id> <new_name>\tИзменение названия у задачи <task_id>.");
        System.out.println("editdescr <task_id> <new_description>\tИзменение описания у задачи <task_id>.");
        System.out.println("editcont <task_id> <new_contacts>\tИзменение контактов у задачи <task_id>.");
        System.out.println("listtasks\tВыводит список активных задач.");
        System.out.println("help\tВыводит данную справочную информацию.");
        System.out.println("exit\tЗавершение работы планировщика задач.");
        System.out.println();
    }

    @Override
    public void editNameTask(int TID, String newName) {
        if (!tasks.containsKey(TID)) {
            System.out.println("Задачи с таким идентификатором не обнаружено. Повторите попытку.\n");
            return;
        }

        Task task = tasks.get(TID);
        task.setName(newName);
        System.out.println("Задаче №" + TID + " изменено название.\n");

        logWriter.addElement(TID, tasks.get(TID));
    }

    @Override
    public void editDescriptionTask(int TID, String newDescription) {
        if (!tasks.containsKey(TID)) {
            System.out.println("Задачи с таким идентификатором не обнаружено. Повторите попытку.\n");
            return;
        }

        Task task = tasks.get(TID);
        task.setDescription(newDescription);
        System.out.println("У задачи №" + TID + " изменено описание.\n");

        logWriter.addElement(TID, tasks.get(TID));
    }

    @Override
    public void editContactsTask(int TID, String newContacts) {
        if (!tasks.containsKey(TID)) {
            System.out.println("Задачи с таким идентификатором не обнаружено. Повторите попытку.\n");
            return;
        }

        Task task = tasks.get(TID);
        task.setContacts(newContacts);
        System.out.println("У задачи №" + TID + " изменены контакты.\n");

        logWriter.addElement(TID, tasks.get(TID));
    }

    @Override
    public void listTasks() {
        System.out.println("Список активных задач:");
        for (Integer TID : tasks.keySet()) {
            Task task = tasks.get(TID);
            System.out.println(TID + "\t" + task.getName() + "\t" + task.getDescription() + "\t" + dateOut(task.getTaskTime()) + "\t" + task.getContacts());
        }
        System.out.println();
    }

    private String dateOut(Calendar c) {
        return (c.get(c.DAY_OF_MONTH) + "." + (c.get(c.MONTH) + 1) + "." + c.get(c.YEAR) + " " + c.get(c.HOUR_OF_DAY) + ":" + c.get(c.MINUTE));
    }

    @Override
    public void doneTask(int TID) {
        tasks.remove(TID);
        flagIsPublicize.remove(TID);
        System.out.println("Задача №" + TID + " успешно выполнена.\n");
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Calendar c = Calendar.getInstance();
        System.out.println("Планировщик задач запущен в " + dateOut(c) + ".\n");

        while (!finish) {
            synchronized (this) {
                for (Integer TID : tasks.keySet()) {
                    Task task = tasks.get(TID);
                    if (Math.abs(task.getTaskTime().getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) < 10000) {
                        publicizeUser(TID, task);
                        flagIsPublicize.replace(TID, true);
                    }
                }
            }

            yield();
        }

        c = Calendar.getInstance();
        logWriter.write();
        System.out.println("Планировщик задач завершил работу в " + dateOut(c) + ".");
        System.exit(0);
    }
}