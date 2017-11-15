package com.example.fiona1.todolist;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.fiona1.todolist.R.id.txtListName;

public class AddTaskActivity extends AppCompatActivity {

    private TextView listName;
    private Bundle extras;
    private DatePickerFragment datePicker;

    private EditText taskName;
    private EditText taskNote;
    private Button chooseDate;
    private String date;
    private int listId;
    private List list;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        dbHelper = new DBHelper(this);
        extras = getIntent().getExtras();
        listId = extras.getInt("listID");
        list = List.findListbyID(dbHelper, listId);
        String txtListName = list.getName();

        datePicker = new DatePickerFragment();

        taskName = (EditText) findViewById(R.id.editTaskName);
        chooseDate = (Button) findViewById(R.id.btnDueDate);
        chooseDate.setText(R.string.dueDate);
        taskNote = (EditText) findViewById(R.id.editTaskNote);

        extras = getIntent().getExtras();



        listName = (TextView) findViewById(R.id.txtTaskListName);
        listName.setText(txtListName);



    }

    public void addTaskComplete(View button) {
        String txtListName = list.getName();

        String name = taskName.getText().toString();
        String date = chooseDate.getText().toString();

        if ( date.equals("Set Due Date")){
            date = "9999/99/99";
        }

        String note = taskNote.getText().toString();
        String status = "Not Complete";
        String pinned = "Not Pinned";
        Task task = new Task(name, date, note, status, pinned, listId);
        task.save(dbHelper);

        int taskCount = list.getTaskCount(dbHelper);
        list.setTaskCount(taskCount);
        list.update(dbHelper);

        Intent intent = new Intent(this, ShowTasksActivity.class);
        intent.putExtra("id", listId);
        intent.putExtra("name", txtListName);
        startActivity(intent);

    }

    public void showDatePickerDialog(View v) {
        datePicker.show(getFragmentManager(),"Today Picker");
    }




}
