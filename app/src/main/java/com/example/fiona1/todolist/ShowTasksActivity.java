package com.example.fiona1.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class ShowTasksActivity extends AppCompatActivity {

    private Task task1;
    private Task task2;
    private Bundle extras;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks);

        dbHelper = new DBHelper(this);

        extras = getIntent().getExtras();
        int id = extras.getInt("id");

        ArrayList<Task> tasks = new ArrayList<>();
        tasks = Task.findByList(dbHelper, id);

        TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
        ListView listView = (ListView)findViewById(R.id.lvTasks);
        listView.setAdapter(taskAdapter);
    }
}
