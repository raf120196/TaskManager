package com.mytaskmanager.alert;

import com.mytaskmanager.tasks.Task;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by renat on 11.12.2016.
 */
public class AlertingService implements AlertInterface {
    private static AlertingService ourInstance = new AlertingService();

    public static AlertingService getInstance() {
        return ourInstance;
    }

    private AlertingService() {
    }

    public void publicizeUserAboutTask(int TID, Task task) {
        System.out.println("Внимание! Настало время задачи " + task.getName() + ": " + task.getDescription() + ". Контакты: " + task.getContacts() + ".\n");

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                SwingAlert frame = new SwingAlert(TID, task);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        try {
            File soundFile = new File("sound.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0);
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);
            clip.stop();
            clip.close();
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        }
        catch (InterruptedException exc) {
        }
    }
}
