package com.example.fiona1.todolist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.edit;
import static com.example.fiona1.todolist.R.id.editSubTaskName;

public class ViewEditTask extends AppCompatActivity {

    private TextView listName;
    private Bundle extras;

    private EditText taskName;
    private EditText dueDate;
    private EditText taskNote;
    private TextView listname;


    private SubTaskAdapter subTaskAdapter;
    private ArrayList<SubTask> subTasks;

    DBHelper dbHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_task);

        extras = getIntent().getExtras();
        int taskId = extras.getInt("taskID");

        dbHelper = new DBHelper(this);
        Task task = Task.findTaskbyID(dbHelper, taskId);
        List list = List.findListbyID(dbHelper,task.getListID());


        taskName = (EditText) findViewById(R.id.editTaskName2);
        taskName.setText(task.getName());
        taskName.setEnabled(false);

        dueDate = (EditText) findViewById(R.id.editDate2);
        dueDate.setText(task.getDueDate());
        dueDate.setEnabled(false);

        listname = (TextView)findViewById(R.id.txtListName2);
        listname.setText(list.getName());

        taskNote = (EditText) findViewById(R.id.editNote2);
        taskNote.setText(task.getNotes());
        taskNote.setEnabled(false);

        dbHelper = new DBHelper(this);



        subTasks = SubTask.findByTaskId(dbHelper, taskId);

        subTaskAdapter = new SubTaskAdapter(this, subTasks);

        listView = (ListView)findViewById(R.id.lvSubTasks);
        listView.setAdapter(subTaskAdapter);

//        if (subTaskAdapter.getCount() == 0){
//            listView.setVisibility(View.GONE);
//        }else{
//            listView.setVisibility(View.VISIBLE);
//        }

    }




    public void addSubTask(View button) {
        extras = getIntent().getExtras();
        final int taskId = extras.getInt("taskID");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a subtask");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.subtask_dialog_view, null);
        final EditText editText = (EditText)dialogView.findViewById(R.id.editSubTaskName);
        builder.setView(dialogView);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editText.getText().toString();
                SubTask task = new SubTask(text, "Not Complete", taskId);
                task.save(dbHelper);
                subTasks = SubTask.findByTaskId(dbHelper, taskId);
                subTaskAdapter = new SubTaskAdapter(ViewEditTask.this, subTasks);
                listView.setAdapter(subTaskAdapter);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }

}
