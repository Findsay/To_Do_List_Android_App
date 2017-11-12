package com.example.fiona1.todolist;

/**
 * Created by Fiona1 on 10/11/2017.
 */

public enum Category {

    INBOX("Inbox"),
    TODAY("Today"),
    PINNED("Pinned"),
    WORK("Work"),
    HOME("Home"),
    SHOPPING("Shopping"),
    TRAVEL("Travel"),
    BIRTHDAYS("Birthdays");


    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
