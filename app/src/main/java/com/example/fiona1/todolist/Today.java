package com.example.fiona1.todolist;

import java.util.Calendar;

/**
 * Created by Fiona1 on 14/11/2017.
 */

public class Today {

    public static String getDate(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String date = year + "/" + month + "/" + day;
        return date;
    }

    public String compareDate(String date){
        if(date.equals(getDate())){
            return "Today";
        }else{
            return date;
        }
    }
}
