package com.mytaskmanager.alert;

import com.mytaskmanager.agent.*;
import com.mytaskmanager.tasks.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * Created by renat on 15.12.2016.
 */
class SwingAlert extends JFrame {

    SwingAlert(int TID, Task task) {
        super("Task Manager v.1.0");
        createGUI(TID, task);
    }

    private void createGUI(int TID, Task task) {
        closing();
        JLabel label = new JLabel("Выполните назначенное задание:");
        label.setHorizontalAlignment(JLabel.CENTER);
        JLabel label1 = new JLabel("Название: " + task.getName());
        label.setHorizontalAlignment(JLabel.LEFT);
        JLabel label2 = new JLabel("Описание: " + task.getDescription());
        label.setHorizontalAlignment(JLabel.LEFT);
        JLabel label3 = new JLabel("Контакты: " + task.getContacts());
        label.setHorizontalAlignment(JLabel.LEFT);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(label);
        panel.add(new JLabel());
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        JButton okButton = new JButton("ОК");
        panel1.add(okButton);

        JButton deferButton = new JButton("Отложить");
        panel1.add(deferButton);

        JButton exitButton = new JButton("Завершить работу Task Manager");
        panel1.add(exitButton);

        ActionListener okListener = new OKActionListener(TID);
        ActionListener deferListener = new DeferActionListener(TID);
        ActionListener exitListener = new ExitActionListener();

        okButton.addActionListener(okListener);
        deferButton.addActionListener(deferListener);
        exitButton.addActionListener(exitListener);

        setPreferredSize(new Dimension(500, 200));

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(panel1, BorderLayout.SOUTH);
    }

    private class OKActionListener implements ActionListener {
        int TID;
        OKActionListener(int TID) {this.TID = TID;}
        public void actionPerformed(ActionEvent e) {
            Command ok = new DoneTask();
            ArrayList<String> list = new ArrayList<String>();
            list.add(Integer.toString(TID));
            ok.execute(list);
            setVisible(false);
        }
    }

    private class DeferActionListener implements ActionListener {
        int TID;
        Command commandToDefer;
        DeferActionListener(int TID) {
            this.commandToDefer = new DeferTask();
            this.TID = TID;
        }

        public void actionPerformed(ActionEvent e) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(Integer.toString(TID));
            commandToDefer.execute(list);
            setVisible(false);
        }
    }

    private class ExitActionListener implements ActionListener {
        Command commandToExit;
        ExitActionListener() {
            this.commandToExit = new Exit();
        }
        public void actionPerformed(ActionEvent e) {
            commandToExit.execute(new ArrayList<String>());
            setVisible(false);
        }
    }

    private void closing() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowListener() {

            public void windowActivated(WindowEvent event) {
            }

            public void windowClosed(WindowEvent event) {
            }

            public void windowDeactivated(WindowEvent event) {
            }

            public void windowDeiconified(WindowEvent event) {
            }

            public void windowIconified(WindowEvent event) {
            }

            public void windowOpened(WindowEvent event) {
            }

            public void windowClosing(WindowEvent event) {
                Object[] options = {"Да", "Нет"};
                int rc = JOptionPane.showOptionDialog(event.getWindow(), "Закрыть окно?", "Подтверждение", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (rc == 0) {
                    event.getWindow().setVisible(false);
                }
            }
        });
    }
}
