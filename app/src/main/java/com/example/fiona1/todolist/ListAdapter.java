package com.example.fiona1.todolist;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fiona1 on 12/11/2017.
 */

public class ListAdapter extends ArrayAdapter<List> {


    public ListAdapter(Context context, ArrayList<List> lists){
        super(context, 0, lists);
    }

    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        List currentList = getItem(position);

        TextView listName = (TextView)listItemView.findViewById(R.id.txtListName);
        listName.setText(currentList.getName().toString());

        listItemView.setTag(currentList);

        return listItemView;
    }


}
