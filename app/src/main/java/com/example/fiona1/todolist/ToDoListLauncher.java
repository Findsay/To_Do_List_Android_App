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

import java.util.ArrayList;

public class ToDoListLauncher extends AppCompatActivity {

    private DBHelper dbHelper;
    private ListView listView;
    private ArrayList<List> defaultLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_launcher);

        dbHelper = new DBHelper(this);

        SubTask.deleteAll(dbHelper);
        Task.deleteAll(dbHelper);
        List.deleteAll(dbHelper);
        List.seedDB(dbHelper);

        defaultLists = new ArrayList<>();
        for (Category category : Category.values()) {
            String name = category.getName();
            List list = new List(name, 0);
            defaultLists.add(list);
        }

        DefaultListAdapter dfListAdapter = new DefaultListAdapter(this, defaultLists);
        listView = (ListView)findViewById(R.id.lvDefault);
        listView.setAdapter(dfListAdapter);

    }

    public void onCheckboxClicked(View view){
        List list = (List) view.getTag();
        boolean checked = ((CheckBox) view).isChecked();
        if (checked){
            list.save(dbHelper);
        }else{
            list.delete(dbHelper);
        }
    }

    public void clickGetStarted(View button){
        Intent intent = new Intent(this, ShowListActivity.class);
        startActivity(intent);
    }


}
