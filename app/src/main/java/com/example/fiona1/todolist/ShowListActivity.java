package com.example.fiona1.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.fiona1.todolist.List.all;


public class ShowListActivity extends AppCompatActivity {

   private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        dbHelper = new DBHelper(this);
        ArrayList<List>lists = List.all(dbHelper);
        ListAdapter listAdapter = new ListAdapter(this, lists);
        ListView listView = (ListView)findViewById(R.id.lvLists);
        listView.setAdapter(listAdapter);
    }

    public void getList(View listItem){
        List list = (List) listItem.getTag();
        Intent intent = new Intent(this, ShowTasksActivity.class);
        intent.putExtra("id", list.getId());
        intent.putExtra("name", list.getName());
        startActivity(intent);
    }

//    public void clickAddList(View button){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Add a subtask");
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.subtask_dialog_view, null);
//        subTaskName = (EditText) dialogView.findViewById(R.id.editSubTaskName);
//        builder.setView(dialogView);
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String text = subTaskName.getText().toString();
//                SubTask task = new SubTask(text, "Not Complete", taskId);
//                task.save(dbHelper);
//                createListAdapter();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
}
