package com.example.fiona1.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.id;
import static android.R.id.list;
import static com.example.fiona1.todolist.R.drawable.starfilled;

public class ShowTasksActivity extends AppCompatActivity {

    private Task task1;
    private Task task2;
    private Bundle extras;
    private DBHelper dbHelper;
    private TextView textListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks);

        dbHelper = new DBHelper(this);

        extras = getIntent().getExtras();
        int id = extras.getInt("id");
        String name = extras.getString("name");

        ArrayList<Task> tasks = Task.findByList(dbHelper, id);

        textListName = (TextView)findViewById(R.id.txtListName);
        textListName.setText(name);

        TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
        ListView listView = (ListView)findViewById(R.id.lvTasks);
        listView.setAdapter(taskAdapter);




    }

    public void addTask(View button){
        extras = getIntent().getExtras();
        int id = extras.getInt("id");
        String name = extras.getString("name");

        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra("listID", id);
        intent.putExtra("listName", name);
        startActivity(intent);

    }

    public void onCheckboxClicked(View view){
        Task task = (Task) view.getTag();
        boolean checked = ((CheckBox) view).isChecked();
        if (checked){
            task.setStatus("Complete");
            DBHelper dbHelper = new DBHelper(this);
            task.update(dbHelper);

            ArrayList<Task> tasks = Task.findByList(dbHelper, task.getListID());
            TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
            ListView listView = (ListView)findViewById(R.id.lvTasks);
            listView.setAdapter(taskAdapter);
            taskAdapter.notifyDataSetChanged();

            String displayText = task.getName()+ " added to Completed";
            Toast.makeText(ShowTasksActivity.this,
                    displayText,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void clickStarred(View view){
        Task task = (Task) view.getTag();
        DBHelper dbHelper = new DBHelper(this);

        if(task.getPinned().equals("Not Pinned")){
            task.setPinned("Pinned");
            task.update(dbHelper);
        }else{
            task.setPinned("Not Pinned");
            task.update(dbHelper);
        }
        ArrayList<Task> tasks = Task.findByList(dbHelper, task.getListID());
        TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
        ListView listView = (ListView)findViewById(R.id.lvTasks);
        listView.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();
    }

    public void editTask(View listItem){
        Task task = (Task) listItem.getTag();
        Intent intent = new Intent(this, ViewEditTask.class);
       intent.putExtra("taskID", task.getId());
        startActivity(intent);
    }


}
