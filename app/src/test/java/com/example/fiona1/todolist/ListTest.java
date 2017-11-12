package com.example.fiona1.todolist;

import org.junit.Before;
import org.junit.Test;

import static android.R.id.list;
import static org.junit.Assert.assertEquals;

/**
 * Created by Fiona1 on 10/11/2017.
 */

public class ListTest {

    List list1;

    @Before
    public void setUp() throws Exception {
        list1 = new List("Personal");
    }

    @Test
    public void canGetName() throws Exception {
        assertEquals("Personal", list1.getName());
    }
}
