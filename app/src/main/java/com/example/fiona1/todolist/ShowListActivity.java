package com.example.fiona1.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.fiona1.todolist.List.all;


public class ShowListActivity extends AppCompatActivity {

   private DBHelper dbHelper;
    private EditText txtListName;
    private ListView listView;
    private ListAdapter listAdapter;
    private ArrayList<List> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        dbHelper = new DBHelper(this);

        listView = (ListView)findViewById(R.id.lvLists);
        createListAdapter();

    }

    public void getList(View listItem){
        List list = (List) listItem.getTag();
        Intent intent = new Intent(this, ShowTasksActivity.class);
        intent.putExtra("id", list.getId());
        intent.putExtra("name", list.getName());
        startActivity(intent);
    }

    public void clickAddList(View button){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add List");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.list_dialog_view, null);
        txtListName = (EditText) dialogView.findViewById(R.id.editListAdd);
        builder.setView(dialogView);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = txtListName.getText().toString();
                List list = new List(text, 0);
                list.save(dbHelper);
                createListAdapter();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void createListAdapter(){
        lists = List.all(dbHelper);
        listAdapter = new ListAdapter(this, lists);
        listView.setAdapter(listAdapter);
        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) listView.getLayoutParams();
        list.height = 150 * lists.size();

    }


}
