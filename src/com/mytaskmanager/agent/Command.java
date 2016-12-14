package com.mytaskmanager.agent;

import java.util.ArrayList;

/**
 * Created by renat on 13.12.2016.
 */
public interface Command {
    Facade facade = Facade.getInstance();
    void execute(ArrayList<String> parameters);
}
