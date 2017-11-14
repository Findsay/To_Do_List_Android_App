package com.example.fiona1.todolist;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.id;
import static org.junit.Assert.assertEquals;

/**
 * Created by Fiona1 on 10/11/2017.
 */

public class TaskTest {

    Task task1;


    @Before
    public void setUp() throws Exception {
        String date = "12/12/2017";
        String notes = "This is a note";
        String status = "Not Complete";
        String pinned = "Pinned";
        int listID = 1;
        task1 = new Task("Do Stuff", date, notes, status, pinned, listID);
    }

    @Test
    public void getTaskName() throws Exception {
        assertEquals("Do Stuff", task1.getName());
    }

    @Test
    public void getDueDate() throws Exception {
        Date date = new Date(11 / 10 / 2017);
        assertEquals(date, task1.getDueDate());
    }

    @Test
    public void getNotes() throws Exception {
        assertEquals("This is a note", task1.getNotes());
    }

    @Test
    public void getStatus() throws Exception {
        assertEquals("Not Complete", task1.getStatus());
    }

    @Test
    public void getPinned() throws Exception {
        assertEquals("Pinned", task1.getPinned());
    }
}
