package com.example.fiona1.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Fiona1 on 14/11/2017.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private String date;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);

    }


    public void onDateSet(DatePicker view, int year, int month, int day) {
        date = view.getYear() + "/" + view.getMonth() + "/" + view.getDayOfMonth();
        Button dueDate = (Button) getActivity().findViewById(R.id.btnDueDate);
        dueDate.setText(date);

    }

    public String getDateSet(){
        return date;
    }

}
