package com.example.fiona1.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.fiona1.todolist.R.drawable.starfilled;
import static com.example.fiona1.todolist.R.drawable.staroutline;

/**
 * Created by Fiona1 on 12/11/2017.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, ArrayList<Task> tasks){
        super(context, 0, tasks);
    }

    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        Task currentTask = getItem(position);

        CheckBox taskName = (CheckBox) listItemView.findViewById(R.id.checkBoxTask);
        taskName.setText(currentTask.getName());
        taskName.setTag(currentTask);

        ImageButton starredBtn = (ImageButton) listItemView.findViewById(R.id.btnStarred);
        starredBtn.setTag(currentTask);
        if (currentTask.getPinned().equals("Not Pinned")){
            starredBtn.setImageResource(staroutline);
        }else{
            starredBtn.setImageResource(starfilled);
        }


//        listItemView.setTag(currentTask);

        return listItemView;
    }
}
