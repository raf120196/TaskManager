package com.edu.taskmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by renat on 12.12.2016.
 */
public class Parser {
    private Map<String, Object> operations = new HashMap<String, Object>();

    public void monitor() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String operation = "";
        while (true) {
            try
            {
                operation = in.readLine();
            } catch (IOException e) {
            }

            if (!"".equals(operation))
            {
                parse(operation);
            }
        }
    }

    public void parse(String operator) {
        String nameOperation = operator.substring(0, operator.indexOf(' ') - 1);
        if (operations.containsKey(nameOperation))
        {
            operations.get(nameOperation);
        }
    }
}
