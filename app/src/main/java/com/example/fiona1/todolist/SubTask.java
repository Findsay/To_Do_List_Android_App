package com.example.fiona1.todolist;

/**
 * Created by Fiona1 on 10/11/2017.
 */

public class SubTask {

    private String name;
    private String status;
    private int taskID;
    private int id;

    public SubTask(String name, String status, int taskID) {
        this.name = name;
        this.status = status;
        this.taskID = taskID;
    }

    public SubTask(int id, String name, String status, int taskID) {
        this.name = name;
        this.status = status;
        this.taskID = taskID;
        this.id = id;
    }
}
