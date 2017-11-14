package com.example.fiona1.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;

import java.util.ArrayList;

import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.M;
import static com.example.fiona1.todolist.DBHelper.LISTS_COLUMN_ID;
import static com.example.fiona1.todolist.DBHelper.LISTS_COLUMN_NAME;
import static com.example.fiona1.todolist.DBHelper.LISTS_TABLE_NAME;
import static com.example.fiona1.todolist.DBHelper.TASKS_COLUMN_DUEDATE;
import static com.example.fiona1.todolist.DBHelper.TASKS_COLUMN_ID;
import static com.example.fiona1.todolist.DBHelper.TASKS_COLUMN_LISTID;
import static com.example.fiona1.todolist.DBHelper.TASKS_COLUMN_NAME;
import static com.example.fiona1.todolist.DBHelper.TASKS_COLUMN_NOTES;
import static com.example.fiona1.todolist.DBHelper.TASKS_COLUMN_PINNED;
import static com.example.fiona1.todolist.DBHelper.TASKS_COLUMN_STATUS;
import static com.example.fiona1.todolist.DBHelper.TASKS_TABLE_NAME;

/**
 * Created by Fiona1 on 10/11/2017.
 */

public class List {

    private String name;
    private int id;


    public List(String name) {
        this.name = name;
    }

    public List(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    public boolean save(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LISTS_COLUMN_NAME, this.name);

        db.insert(LISTS_TABLE_NAME, null, cv);

        return true;
    }

    public static ArrayList<List> all(DBHelper dbHelper) {
        ArrayList<List> lists = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + LISTS_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(LISTS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(LISTS_COLUMN_NAME));

            List list = new List(id, name);
            lists.add(list);
        }
        cursor.close();
        return lists;
    }

    public static boolean deleteAll(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + LISTS_TABLE_NAME);
        return true;
    }

    public boolean delete(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String stringId = String.valueOf(id);
        String[] args = new String[]{stringId};
        db.delete(LISTS_TABLE_NAME, LISTS_COLUMN_ID + " =?", args);
        return true;
    }

    public static List findListbyID(DBHelper dbHelper, int listId) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + LISTS_TABLE_NAME + " WHERE " + LISTS_COLUMN_ID + " = ?";
        String stringId = String.valueOf(listId);
        String[] args = new String[]{stringId};
        Cursor cursor = db.rawQuery(sql, args);

        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex(LISTS_COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(LISTS_COLUMN_NAME));

        List list = new List(id, name);
        cursor.close();
        return list;
    }


    public static void seedDB(DBHelper dbHelper) {

//        List list2 = new List("Today");
//        list2.save(dbHelper);
//        List list3 = new List("Starred");
//        list3.save(dbHelper);
    }

    public int getTaskCount(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + TASKS_TABLE_NAME + " WHERE " + TASKS_COLUMN_LISTID + " = ?";
        String stringId = String.valueOf(id);
        String[] args = new String[]{stringId};
        Cursor cursor = db.rawQuery(sql, args);
        int count = cursor.getCount();
        return count;
    }
}








