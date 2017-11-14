package com.example.fiona1.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.view.ViewGroup.LayoutParams;

import java.util.Date;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.id.list;
import static com.example.fiona1.todolist.R.drawable.starfilled;

public class ShowTasksActivity extends AppCompatActivity {

    private Bundle extras;
    private DBHelper dbHelper;
    private TextView textListName;
    private Button showHide;
    private Button addTask;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> tasks;
    private ListView listView;
    private ListView completedListView;
    private TaskAdapter completedTaskAdapter;
    private ArrayList<Task> completedTasks;
    private int listId;
    private String listName;
    private Boolean specialList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks);

        dbHelper = new DBHelper(this);

        extras = getIntent().getExtras();
        listId = extras.getInt("id");
        listName = extras.getString("name");
        textListName = (TextView) findViewById(R.id.txtListName);
        textListName.setText(listName);

        addTask = (Button) findViewById(R.id.btnAddTask);
        specialList = (listName.equals("Today") || listName.equals("Starred"));

        if (specialList) {
            addTask.setVisibility(View.GONE);
        } else {
            addTask.setVisibility(View.VISIBLE);
        }


        listView = (ListView) findViewById(R.id.lvTasks);
        createTaskAdapter();

        showHide = (Button) findViewById(R.id.btnShowHide);
        completedListView = (ListView) findViewById(R.id.lvCompleted);
        createCompletedTaskAdapter();


    }

    public void addTask(View button) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra("listID", listId);
        intent.putExtra("listName", listName);
        startActivity(intent);

    }

    public void onCheckboxClicked(View view) {
        Task task = (Task) view.getTag();
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            task.setStatus("Complete");
            DBHelper dbHelper = new DBHelper(this);
            task.update(dbHelper);
            createTaskAdapter();
            createCompletedTaskAdapter();
            String displayText = task.getName() + " added to Completed";
            Toast.makeText(ShowTasksActivity.this,
                    displayText,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void clickStarred(View view) {
        Task task = (Task) view.getTag();
        DBHelper dbHelper = new DBHelper(this);

        if (task.getPinned().equals("Not Pinned")) {
            task.setPinned("Pinned");
            task.update(dbHelper);
        } else {
            task.setPinned("Not Pinned");
            task.update(dbHelper);
        }
        createTaskAdapter();
    }

    public void editTask(View listItem) {
        Task task = (Task) listItem.getTag();
        Intent intent = new Intent(this, ViewEditTask.class);
        intent.putExtra("taskID", task.getId());
        startActivity(intent);
    }

    public void createTaskAdapter() {
        if (specialList) {
            if (listName.equals("Starred")) {
                tasks = Task.findAllStarred(dbHelper);
            }

        } else {
            tasks = Task.findByList(dbHelper, listId);
        }
        taskAdapter = new TaskAdapter(this, tasks);
        listView.setAdapter(taskAdapter);
        LayoutParams list = (LayoutParams) listView.getLayoutParams();
        list.height = 200 * tasks.size();
        listView.setAdapter(taskAdapter);
    }

    public void createCompletedTaskAdapter() {
        completedTasks = Task.findCompleted(dbHelper, listId);
        completedTaskAdapter = new TaskAdapter(this, completedTasks);
        completedListView.setAdapter(completedTaskAdapter);
        completedListView.setVisibility(View.GONE);

        if (completedTasks.size() == 0) {
            showHide.setVisibility(View.GONE);

        } else {
            showHide.setVisibility(View.VISIBLE);
            showHide.setText(R.string.Show);
        }
    }

    public void clickShowHide(View button) {
        String text = showHide.getText().toString();
        if (text.equals("Show Completed")) {
            showHide.setText(R.string.Hide);
            completedListView.setVisibility(View.VISIBLE);
            LayoutParams list = (LayoutParams) completedListView.getLayoutParams();
            list.height = 200 * completedTasks.size();

        } else {
            showHide.setText(R.string.Show);
            completedListView.setVisibility(View.GONE);


        }
    }


}
