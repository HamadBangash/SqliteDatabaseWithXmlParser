package com.example.bangash.managingdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    dataSource dataSourceObj;
    ListView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        dataSourceObj = new dataSource(this);
        retrievingAllData();

    }


    private void retrievingAllData() {
        List<Food> dataList = new ArrayList<>();
        dataList = dataSourceObj.retrievingAllData(this);
        if (dataList.isEmpty()==true) {
            sendingData();
            dataList = dataSourceObj.retrievingAllData(this);
        }
        int imgs[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
        boolean flage = getIntent().getBooleanExtra("Value", false);
        if (flage == true) {
            customAdapter customAdapter = new customAdapter(this, dataList, imgs);
            lvItems.setAdapter(customAdapter);
            final List<Food> finalDataList = dataList;
            lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Food food = finalDataList.get(position);
                    String img[] = {"image_1", "image_2", "image_3", "image_4", "image_5"};
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra("name", food.getName());
                    intent.putExtra("description", food.getDescription());
                    intent.putExtra("img", img[position].toString());
                    startActivity(intent);
                }
            });
        } else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList);
            lvItems.setAdapter(arrayAdapter);
        }

    }


    private void retrievingSelectedData(String flowerName) {

        List<Food> dataList = new ArrayList<>();
        dataList = dataSourceObj.retrievingSelectedData(this, flowerName);
        if (dataList.size() == 0) {
            sendingData();
            dataList = dataSourceObj.retrievingSelectedData(this, flowerName);
        }
        int imgs[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
        boolean flage = getIntent().getBooleanExtra("Value", false);
        if (flage == true) {
            customAdapter customAdapter = new customAdapter(this, dataList, imgs);
            lvItems.setAdapter(customAdapter);
            lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = lvItems.getItemAtPosition(position).toString();
                    String img[] = {"image_1", "image_2", "image_3", "image_4", "image_5"};
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra("selectedItem", selectedItem);
                    intent.putExtra("img", img[position].toString());
                    startActivity(intent);
                }
            });
        } else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList);
            lvItems.setAdapter(arrayAdapter);
        }
    }


    public void sendingData() {
        FlowerXMLParser xmlPullParser = new FlowerXMLParser();
        List<Food> dataSendList = xmlPullParser.parseFeed(MainActivity.this);
        for (Food food : dataSendList) {
            dataSourceObj.insertingDataInFirstTable(food);
        }
    }

    //For opening and closing database
    @Override
    protected void onPause() {
        super.onPause();
        dataSourceObj.closeDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSourceObj.openDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.All) {
            retrievingAllData();
        } else if (id == R.id.Chamili) {
            retrievingSelectedData("Chamili");

        } else if (id == R.id.Flower) {
            retrievingSelectedData("Flower");

        } else if (id == R.id.Chrysanthemum) {
            retrievingSelectedData("Chrysanthemum");

        } else if (id == R.id.Setting) {
            Intent intent = new Intent(MainActivity.this, DisplaySetting.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.ShowList) {
            Intent intent=new Intent(MainActivity.this,SecondDataList.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
