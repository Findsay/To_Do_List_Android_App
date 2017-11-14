package com.example.fiona1.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.edit;
import static android.R.id.list;
import static com.example.fiona1.todolist.R.id.editSubTaskName;

public class ViewEditTask extends AppCompatActivity {

    private Bundle extras;
    private TextView listName;
    private EditText taskName;
    private EditText dueDate;
    private EditText taskNote;
    private TextView listname;
    private EditText subTaskName;
    private int taskId;
    private SubTaskAdapter subTaskAdapter;
    private ArrayList<SubTask> subTasks;
    private DBHelper dbHelper;
    private ListView listView;
    private Task task;
    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_task);

        extras = getIntent().getExtras();
        taskId = extras.getInt("taskID");

        dbHelper = new DBHelper(this);
        task = Task.findTaskbyID(dbHelper, taskId);
        list = List.findListbyID(dbHelper, task.getListID());

        taskName = (EditText) findViewById(R.id.editTaskName2);
        taskName.setText(task.getName());
        taskName.setEnabled(false);

        dueDate = (EditText) findViewById(R.id.editDate2);
        dueDate.setText(task.getDueDate());
        dueDate.setEnabled(false);

        listName = (TextView) findViewById(R.id.txtListName2);
        listName.setText(list.getName());

        taskNote = (EditText) findViewById(R.id.editNote2);
        taskNote.setText(task.getNotes());
        taskNote.setEnabled(false);

        dbHelper = new DBHelper(this);

        listView = (ListView) findViewById(R.id.lvSubTasks);
        createListAdapter();

    }

    public void createListAdapter() {
        subTasks = SubTask.findByTaskId(dbHelper, taskId);
        subTaskAdapter = new SubTaskAdapter(ViewEditTask.this, subTasks);
        listView.setAdapter(subTaskAdapter);
        if (subTaskAdapter.getCount() == 0) {
            listView.setVisibility(View.GONE);
        } else {
            listView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) listView.getLayoutParams();
            list.height = 200 * subTasks.size();

        }


    }

    public void addSubTask(View button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a subtask");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.subtask_dialog_view, null);
        subTaskName = (EditText) dialogView.findViewById(R.id.editSubTaskName);
        builder.setView(dialogView);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = subTaskName.getText().toString();
                SubTask task = new SubTask(text, "Not Complete", taskId);
                task.save(dbHelper);
                createListAdapter();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onCheckboxClicked(View view) {
        SubTask subTask = (SubTask) view.getTag();
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            subTask.setStatus("Complete");
            subTask.update(dbHelper);
            createListAdapter();
        }
    }

    public void onClickOk(View button) {
        task.update(dbHelper);
        Intent intent = new Intent(this, ShowTasksActivity.class);
        intent.putExtra("id", list.getId());
        intent.putExtra("name", list.getName());
        startActivity(intent);
    }

}
