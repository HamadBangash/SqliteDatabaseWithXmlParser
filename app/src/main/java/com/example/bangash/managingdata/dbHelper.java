package com.example.bangash.managingdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Bangash on 9/30/2016.
 */
public class dbHelper extends SQLiteOpenHelper {
    public static final String dbName = "mySecondDatabase.db";
    public static final int dbVersion = 17;

    public dbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Tours (name varchar(24),description varchar(30))");
        db.execSQL("CREATE TABLE Tours1 (name varchar(24) PRIMARY KEY ,description varchar(30))");
        Log.d("Result :", "Table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Tours");
        db.execSQL("DROP TABLE IF EXISTS Tours1");
        onCreate(db);
    }
}
