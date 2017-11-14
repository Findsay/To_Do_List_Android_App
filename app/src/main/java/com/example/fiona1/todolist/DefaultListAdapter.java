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
 * Created by Fiona1 on 14/11/2017.
 */

public class DefaultListAdapter extends ArrayAdapter<List> {

    private CheckBox checkBox;

    public DefaultListAdapter(Context context, ArrayList<List> defaultLists){
        super(context, 0, defaultLists);
    }

    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.default_list_item, parent, false);
        }

        List currentList = getItem(position);

        checkBox = (CheckBox) listItemView.findViewById(R.id.checkBoxDfList);
        checkBox.setText(currentList.getName());
        checkBox.setTag(currentList);

        return listItemView;
    }
}
