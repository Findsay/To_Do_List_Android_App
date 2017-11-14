package com.example.fiona1.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fiona1 on 13/11/2017.
 */

public class SubTaskAdapter extends ArrayAdapter<SubTask> {

    public SubTaskAdapter(Context context, ArrayList<SubTask> subtasks){
        super(context, 0, subtasks);
    }

    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.subtask_item, parent, false);
        }

        SubTask currentSubTask = getItem(position);

        CheckBox taskName = (CheckBox) listItemView.findViewById(R.id.checkBoxSubTask);
        taskName.setText(currentSubTask.getName());
        taskName.setTag(currentSubTask);


        return listItemView;
    }
}
