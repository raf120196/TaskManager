package com.mytaskmanager.alert;

import com.mytaskmanager.tasks.Task;

/**
 * Created by renat on 11.12.2016.
 */
public interface AlertInterface {
    void publicizeUserAboutTask(int TID, Task task);
}
