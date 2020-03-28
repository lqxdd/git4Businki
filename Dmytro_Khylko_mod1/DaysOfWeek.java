package com.modul1;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
public class DaysOfWeek {
    private enum Days {
        SUN, MON, TUE, WED, THU, FRI, SAT
    }
    private JFrame mainFrame;       //These two elements are got from Main class
    private JPanel displayDays;     //
    private  JPanel displayDayInfo = new JPanel(); //This panel is used to display tasks of each day,
    private final int MAX_TASKS_COUNT = 15;
    private GridBagLayout DaysLayout = new GridBagLayout();
    private GridBagConstraints DaysGbc = new GridBagConstraints();

    private class DayOfWeek{
        private JButton dayButton;
        private JLabel dayName;
        private LinkedList<JLabel> tasksList = new LinkedList<>();
        private DayOfWeek(LocalDate weekBegins, LocalDate today, int dayCell, Days day){
            this.dayButton = new JButton(weekBegins.plusDays(dayCell).format(DateTimeFormatter.ofPattern("dd")));
            this.dayName = new JLabel(day.toString());
            if(day.equals(Days.SUN) || day.equals(Days.SAT)){
                dayName.setForeground(Color.decode("#ed4c5c"));
                dayButton.setBackground(Color.decode("#e87682"));
            }else {
                dayButton.setBackground(Color.decode("#fdffe8"));
            }
            if(today.equals(weekBegins.plusDays(dayCell))){
                dayButton.setBackground(Color.decode("#706ef5"));
            }
            dayButton.addActionListener(e -> { // Listener that reacts when number of day in week is clicked
                GridBagConstraints mainFrameLayoutGbc = new GridBagConstraints();
                mainFrameLayoutGbc.gridx = 0;
                mainFrameLayoutGbc.gridy = 1;
                JTextArea tasksHeader = new JTextArea();
                tasksHeader.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                tasksHeader.setText("Tasks for " +
                        weekBegins.plusDays(dayCell).format(DateTimeFormatter.ofPattern("dd-MMM-YYY")));
                tasksHeader.setBackground(Color.decode("#beffb0"));
                tasksHeader.setFont(new Font("Arial", Font.BOLD, 20));
                displayDayInfo.removeAll();
                BoxLayout layout = new BoxLayout(displayDayInfo, BoxLayout.Y_AXIS);
                displayDayInfo.setLayout(layout);
                if(tasksList.isEmpty()){
                    displayDayInfo.add(tasksHeader);
                    JLabel emptyList = new JLabel("There are no tasks yet");
                    emptyList.setFont(new Font("Serif", Font.BOLD, 18));
                    displayDayInfo.add(emptyList);
                }
                else{
                    displayDayInfo.add(tasksHeader);
                    for(JLabel taskMessage: tasksList){
                        displayDayInfo.add(taskMessage);
                    }
                }
                JButton addTask = new JButton("Add new task");
                JButton clearTasksList = new JButton("Clear all");
                displayDayInfo.add(addTask);
                addTask.addActionListener(e1 -> { // Listener to add task to exact day
                    if(tasksList.size() < MAX_TASKS_COUNT){
                        String taskMessage = JOptionPane.showInputDialog("Input your task");
                        if (taskMessage != null && !taskMessage.equalsIgnoreCase("")) {
                            JLabel task = new JLabel("Task #" + (tasksList.size() + 1) + ":    " + taskMessage);
                            task.setFont(new Font("Arial", Font.BOLD, 18));
                            tasksList.add(task);
                            displayDayInfo.removeAll();
                            displayDayInfo.add(tasksHeader);
                            for (JLabel taskMessageNew : tasksList) {
                                displayDayInfo.add(taskMessageNew);
                            }
                            displayDayInfo.add(addTask);
                            displayDayInfo.add(clearTasksList);
                            mainFrame.setVisible(true);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"Too many tasks for today." +
                                "\nComplete previous and delete\n(Everything is made with small steps☺)"
                                ,"Too many tasks", JOptionPane.WARNING_MESSAGE);
                    }
                });
                displayDayInfo.add(clearTasksList);
                clearTasksList.addActionListener(e1 -> { // Listener to clear task list of exact day
                    tasksList.clear();
                    displayDayInfo.removeAll();
                    displayDayInfo.add(tasksHeader);
                    JLabel emptyList = new JLabel("There are no tasks yet");
                    emptyList.setFont(new Font("Serif", Font.BOLD, 18));
                    displayDayInfo.add(emptyList);
                    displayDayInfo.add(addTask);
                    displayDayInfo.add(clearTasksList);
                    mainFrame.setVisible(true);
                });
                mainFrame.add(displayDayInfo, mainFrameLayoutGbc);
                mainFrame.setVisible(true);
            });
        }
    }

    public DaysOfWeek(JPanel displayDays, JFrame mainFrame){
        this.mainFrame = mainFrame;
        this.displayDays = displayDays;
        organizeWeek();
    }

    private void organizeWeek(){
        DayOfWeek[] daysOfWeek = new DayOfWeek[7];
        LocalDate today = LocalDate.now();
        LocalDate weekBegins = today.with(previousOrSame(SUNDAY));
        Days[] days = Days.values();
        displayDays.setLayout(DaysLayout);
        int dayCell = 0;
        for (Days day: days) { //Iterate through Days enum to create week
            daysOfWeek[dayCell] = new DayOfWeek(weekBegins, today, dayCell, day); // create instance of each day
            DaysGbc.gridx = dayCell;
            DaysGbc.gridy = 0;
            displayDays.add(daysOfWeek[dayCell].dayName, DaysGbc);
            DaysGbc.gridx = dayCell;
            DaysGbc.gridy = 1;
            displayDays.add(daysOfWeek[dayCell].dayButton, DaysGbc);
            dayCell++;
        }
    }
}
