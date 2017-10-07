package com.smkapps.fragmentsdemo;


import java.util.ArrayList;
import java.util.List;

public class TaskLab {
    private static  TaskLab ourInstance ;
    private List<Task> taskList = new ArrayList<>();

    public static TaskLab getInstance() {
        if (ourInstance == null) ourInstance = new TaskLab();
        return ourInstance;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    private TaskLab() {

    }
}
