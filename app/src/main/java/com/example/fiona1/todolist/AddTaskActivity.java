package com.example.fiona1.todolist;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddTaskActivity extends AppCompatActivity {

    private TextView listName;
    private Bundle extras;

    private EditText taskName;
    private EditText dueDate;
    private EditText taskNote;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskName = (EditText) findViewById(R.id.editTaskName);
//        dueDate = (EditText) findViewById(R.id.editDate);
        taskNote = (EditText) findViewById(R.id.editTaskNote);

        extras = getIntent().getExtras();

        String txtListName = extras.getString("listName");

        listName = (TextView) findViewById(R.id.txtTaskListName);
        listName.setText(txtListName);



    }

    public void addTaskComplete(View button) {
        dbHelper = new DBHelper(this);
        extras = getIntent().getExtras();
        int listId = extras.getInt("listID");
        String txtListName = extras.getString("listName");

        String name = taskName.getText().toString();
        String date = "";
        String note = taskNote.getText().toString();
        String status = "Not Complete";
        String pinned = "Not Pinned";
        Task task = new Task(name, date, note, status, pinned, listId);
        task.save(dbHelper);

        Intent intent = new Intent(this, ShowTasksActivity.class);
        intent.putExtra("id", listId);
        intent.putExtra("name", txtListName);
        startActivity(intent);

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");
    }




}
