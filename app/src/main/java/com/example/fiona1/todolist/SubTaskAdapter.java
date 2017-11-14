package com.example.fiona1.todolist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Fiona1 on 13/11/2017.
 */

public class SubTaskAdapter extends ArrayAdapter<SubTask> {

    private CheckBox taskCheckbox;
    private TextView subTaskName;

    public SubTaskAdapter(Context context, ArrayList<SubTask> subtasks){
        super(context, 0, subtasks);
    }

    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.subtask_item, parent, false);
        }

        SubTask currentSubTask = getItem(position);

        taskCheckbox = (CheckBox) listItemView.findViewById(R.id.checkBoxSubTask);
        taskCheckbox.setTag(currentSubTask);
        subTaskName = (TextView)listItemView.findViewById(R.id.txtSubTask);
        subTaskName.setText(currentSubTask.getName());

        if (currentSubTask.getStatus().equals("Complete")){
            taskCheckbox.setChecked(true);
            subTaskName.setPaintFlags(subTaskName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }



        return listItemView;



    }
}
