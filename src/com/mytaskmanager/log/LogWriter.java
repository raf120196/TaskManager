package com.mytaskmanager.log;

import com.mytaskmanager.tasks.Task;

/**
 * Created by renat on 15.12.2016.
 */
public interface LogWriter {
    void addElement(int TID, Task task);

    void write();
}
