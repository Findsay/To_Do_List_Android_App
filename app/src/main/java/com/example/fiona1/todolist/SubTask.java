package com.example.fiona1.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.fiona1.todolist.DBHelper.SUBTASKS_COLUMN_ID;
import static com.example.fiona1.todolist.DBHelper.SUBTASKS_COLUMN_NAME;
import static com.example.fiona1.todolist.DBHelper.SUBTASKS_COLUMN_STATUS;
import static com.example.fiona1.todolist.DBHelper.SUBTASKS_COLUMN_TASKID;
import static com.example.fiona1.todolist.DBHelper.SUBTASKS_TABLE_NAME;
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

public class SubTask {

    private String name;
    private String status;
    private int taskID;
    private int id;

    public SubTask(String name, String status, int taskID) {
        this.name = name;
        this.status = status;
        this.taskID = taskID;
    }

    public SubTask(int id, String name, String status, int taskID) {
        this.name = name;
        this.status = status;
        this.taskID = taskID;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean save(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SUBTASKS_COLUMN_NAME, this.name);
        cv.put(SUBTASKS_COLUMN_STATUS, this.status);
        cv.put(SUBTASKS_COLUMN_TASKID, this.taskID);

        db.insert(SUBTASKS_TABLE_NAME, null, cv);

        return true;
    }


    public static boolean deleteAll(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + SUBTASKS_TABLE_NAME);
        return true;
    }

    public static ArrayList<SubTask> findByTaskId(DBHelper dbHelper, int taskId) {
        ArrayList<SubTask> subtasks = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + SUBTASKS_TABLE_NAME + " WHERE " + SUBTASKS_COLUMN_TASKID + " = ?";
        String stringId = String.valueOf(taskId);
        String[] args = new String[]{stringId};
        Cursor cursor = db.rawQuery(sql, args);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(SUBTASKS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(SUBTASKS_COLUMN_NAME));
            String status = cursor.getString(cursor.getColumnIndex(SUBTASKS_COLUMN_STATUS));
            int taskID = cursor.getInt(cursor.getColumnIndex(SUBTASKS_COLUMN_TASKID));
            SubTask subTask = new SubTask(id, name, status, taskID);
            subtasks.add(subTask);
        }
        cursor.close();
        return subtasks;
    }

    public boolean update(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SUBTASKS_COLUMN_NAME, this.name);
        cv.put(SUBTASKS_COLUMN_STATUS, this.status);
        cv.put(SUBTASKS_COLUMN_TASKID, this.taskID);
        String id = String.valueOf(this.id);

        String[] args = new String[]{id};

        db.update(SUBTASKS_TABLE_NAME, cv, SUBTASKS_COLUMN_ID + " =?", args);

        return true;

    }

}
