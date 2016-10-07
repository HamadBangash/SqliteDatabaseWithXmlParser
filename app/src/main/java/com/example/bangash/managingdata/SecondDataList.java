package com.example.bangash.managingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondDataList extends AppCompatActivity {
    dataSource dataSourceObj;
    ListView lvItems;
    List<Food> dataList;
    Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_data_list);
        dataSourceObj=new dataSource(this);

        lvItems= (ListView) findViewById(R.id.lvItems);
        showingList();
    }



    private void showingList() {
         dataList = new ArrayList<>();
        dataList = dataSourceObj.retrievingTaleSecondData(this);
        if (dataList.size() == 0) {
            dataList = dataSourceObj.retrievingTaleSecondData(this);
        }
        int imgs[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
        customAdapter customAdapter = new customAdapter(this, dataList, imgs);
        lvItems.setAdapter(customAdapter);
       // final List<Food> finalDataList = dataList;
//        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Food food = finalDataList.get(position);
//
////                String img[] = {"image_1", "image_2", "image_3", "image_4", "image_5"};
////                Intent intent = new Intent(SecondDataList.this, DetailsActivity.class);
////                intent.putExtra("name", food.getName());
////                intent.putExtra("description", food.getDescription());
////                intent.putExtra("img", img[position].toString());
////                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_app_menu3,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.Delete)
        {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                food=dataList.get(position);
                dataSourceObj.deleteData(food);
                showingList();
            }
        });
        }

        return super.onOptionsItemSelected(item);
    }
}
