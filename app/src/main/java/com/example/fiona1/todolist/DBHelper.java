package com.example.fiona1.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.util.HashMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Fiona1 on 12/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "todolists.db";

    public static final String LISTS_TABLE_NAME = "lists";
    public static final String LISTS_COLUMN_ID = "id";
    public static final String LISTS_COLUMN_NAME = "name";

    public static final String TASKS_TABLE_NAME = "tasks";
    public static final String TASKS_COLUMN_ID = "id";
    public static final String TASKS_COLUMN_NAME = "name";
    public static final String TASKS_COLUMN_DUEDATE = "dueDate";
    public static final String TASKS_COLUMN_NOTES = "notes";
    public static final String TASKS_COLUMN_STATUS = "status";
    public static final String TASKS_COLUMN_PINNED = "pinned";
    public static final String TASKS_COLUMN_LISTID = "listID";

    public static final String SUBTASKS_TABLE_NAME = "subtasks";
    public static final String SUBTASKS_COLUMN_ID = "id";
    public static final String SUBTASKS_COLUMN_NAME = "name";
    public static final String SUBTASKS_COLUMN_STATUS = "status";
    public static final String SUBTASKS_COLUMN_TASKID = "taskID";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 3);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + LISTS_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT)");
        db.execSQL("CREATE TABLE " + TASKS_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT, dueDate DATE, notes TEXT, status TEXT, pinned TEXT, listID INTEGER)");
        db.execSQL("CREATE TABLE " + SUBTASKS_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT, status TEXT, taskID INTEGER)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + SUBTASKS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TASKS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LISTS_TABLE_NAME);
        onCreate(db);
    }

}
