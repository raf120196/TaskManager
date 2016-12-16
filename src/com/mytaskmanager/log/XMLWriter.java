package com.mytaskmanager.log;

import com.mytaskmanager.tasks.Task;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.util.Calendar;

/**
 * Created by renat on 15.12.2016.
 */
public class XMLWriter implements LogWriter {
    private static XMLWriter ourInstance = new XMLWriter();
    private Element rootElement;
    Document doc;

    public static XMLWriter getInstance() {
        return ourInstance;
    }

    private XMLWriter() {
        this.rootElement = new Element("Tasks");
        this.doc = new Document(rootElement);
    }

    private String dateOut(Calendar c) {
        return (c.get(c.DAY_OF_MONTH) + "." + (c.get(c.MONTH) + 1) + "." + c.get(c.YEAR) + " " + c.get(c.HOUR_OF_DAY) + ":" + c.get(c.MINUTE));
    }

    @Override
    public void addElement(int TID, Task task) {
        Element tsk = new Element("task");
        tsk.setAttribute("task_id", Integer.toString(TID));
        tsk.setAttribute("name", task.getName());
        tsk.setAttribute("description", task.getDescription());
        tsk.setAttribute("datetime", dateOut(task.getTaskTime()));
        tsk.setAttribute("contacts", task.getContacts());
        rootElement.addContent(tsk);
    }

    @Override
    public void write() {
        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter("tasks.xml");
            outputter.output(doc, fw);
            fw.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
