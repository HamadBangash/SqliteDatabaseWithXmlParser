package com.example.bangash.managingdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bangash on 10/1/2016.
 */
public class dataSource {
    dbHelper dbHelper;
    SQLiteDatabase database;

    public dataSource(Context context) {
        dbHelper = new dbHelper(context);
    }

    public void openDatabase() {
        database = dbHelper.getWritableDatabase();
        Log.i("Result:", "Database Opened");
    }

    public void closeDatabase() {
        dbHelper.close();
        Log.i("Result:", "Database Closed");
    }

    //inserting data in database
    public void insertingDataInFirstTable(Food food) {
        database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", food.getName());
        cv.put("description", food.getDescription());
        Log.d("Result:", "Data inserted");
        database.insert("Tours", null, cv);
        database.close();
    }

    public boolean insertingDataInSecondTable(Food food) {
        database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", food.getName());
        cv.put("description", food.getDescription());
        Log.d("Result:", "Data inserted in second table");
        long result = database.insert("Tours1", null, cv);
        return (result != -1);
    }


    //retrieving data from database
    public List<Food> retrievingAllData(Context context) {
        database = dbHelper.getWritableDatabase();
        List<Food> datalist = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tours", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Food food = new Food();
                food.setName(cursor.getString(cursor.getColumnIndex("name")));
                datalist.add(food);
                Log.d("kwas",datalist.toString());
            }
        }

        return datalist;
    }


    public List<Food> retrievingSelectedData(Context context, String flowerName) {
        database = dbHelper.getWritableDatabase();
        List<Food> datalist = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tours WHERE name='" + flowerName + "'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Food food = new Food();
                food.setName(cursor.getString(cursor.getColumnIndex("name")));
                food.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                datalist.add(food);
            }
        }

        return datalist;
    }

    public List<Food> retrievingTaleSecondData(Context context) {
        database = dbHelper.getWritableDatabase();
        List<Food> datalist = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tours1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Food food = new Food();
                food.setName(cursor.getString(cursor.getColumnIndex("name")));
                food.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                datalist.add(food);
            }
            while (cursor.moveToNext());
        }

        return datalist;
    }
    public void deleteData(Food food)
    {
        database = dbHelper.getWritableDatabase();
        String query="DELETE FROM Tours1 WHERE name='"+food.getName()+"'";
        database.execSQL(query);
    }

}
