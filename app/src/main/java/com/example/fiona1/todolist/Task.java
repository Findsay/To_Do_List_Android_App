package com.example.fiona1.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.category;
import static android.R.id.list;
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

public class Task {

    private int id;
    private String name;
    private String dueDate;
    private String notes;
    private String status;
    private String pinned;
    private int listID;

    public Task(String name, String dueDate, String notes, String status, String pinned, int listID) {
        this.name = name;
        this.dueDate = dueDate;
        this.notes = notes;
        this.status = status;
        this.pinned = pinned;
        this.listID = listID;
    }

    public Task(int id, String name, String dueDate, String notes, String status, String pinned, int listID) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.notes = notes;
        this.status = status;
        this.pinned = pinned;
        this.listID = listID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }

    public String getPinned() {
        return pinned;
    }

    public int getListID() {
        return listID;
    }

    public void setName(String name) {
        this.name = name;
    }




    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPinned(String pinned) {
        this.pinned = pinned;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public boolean save(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASKS_COLUMN_NAME, this.name);
        cv.put(TASKS_COLUMN_DUEDATE, this.dueDate);
        cv.put(TASKS_COLUMN_NOTES, this.notes);
        cv.put(TASKS_COLUMN_STATUS, this.status);
        cv.put(TASKS_COLUMN_PINNED, this.pinned);
        cv.put(TASKS_COLUMN_LISTID, this.listID);

        db.insert(TASKS_TABLE_NAME, null, cv);

        return true;
    }

    public static ArrayList<Task> all(DBHelper dbHelper) {
        ArrayList<Task> tasks = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TASKS_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_NAME));
            String dueDate = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_DUEDATE));
            String notes = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_NOTES));
            String status = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_STATUS));
            String pinned = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_PINNED));
            int listID = cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_LISTID));


            Task task = new Task(id, name, dueDate, notes, status, pinned, listID);
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }

    public static ArrayList<Task> findByList(DBHelper dbHelper, int listId) {
        ArrayList<Task> tasks = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TASKS_TABLE_NAME+" WHERE "+ TASKS_COLUMN_LISTID+ " = "+ listId, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_NAME));
            String dueDate = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_DUEDATE));
            String notes = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_NOTES));
            String status = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_STATUS));
            String pinned = cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_PINNED));
            int listID = cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_LISTID));


            Task task = new Task(id, name, dueDate, notes, status, pinned, listID);
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }



    public static boolean deleteAll(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TASKS_TABLE_NAME);
        return true;
    }

    public static void seedDB(DBHelper dbHelper){
        ArrayList<List> lists = List.all(dbHelper);
        for (List list : lists){
            int id =   list.getId();
            Task task = new Task("Test Task", "12/12/2017", "Blah", "Not Complete", "Not Pinned", id);
            task.save(dbHelper);
        }
    }
}