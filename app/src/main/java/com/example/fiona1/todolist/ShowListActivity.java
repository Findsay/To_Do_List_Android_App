package com.example.fiona1.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.fiona1.todolist.List.all;


public class ShowListActivity extends AppCompatActivity {

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        dbHelper = new DBHelper(this);
        List.deleteAll(dbHelper);

        List.seedDB(dbHelper);
        Task.seedDB(dbHelper);

        ArrayList<List> lists = new ArrayList<>();
        lists = List.all(dbHelper);

        ListAdapter listAdapter = new ListAdapter(this, lists);
        ListView listView = (ListView)findViewById(R.id.lvLists);
        listView.setAdapter(listAdapter);
    }

    public void getList(View listItem){
        List list = (List) listItem.getTag();
        Intent intent = new Intent(this, ShowTasksActivity.class);
        intent.putExtra("id", list.getId());
        startActivity(intent);
    }
}
