package com.example.bangash.managingdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    Food food;

    dataSource source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        source = new dataSource(this);
        source.openDatabase();
        ImageView ivSelectedImage = (ImageView) findViewById(R.id.ivSelectedImage);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvDesc = (TextView) findViewById(R.id.tvDesc);
        food = new Food();
        String Title = getIntent().getStringExtra("name");
        tvTitle.setText(Title);
        String img = getIntent().getStringExtra("img");
        int res = getResources().getIdentifier(img, "drawable", getPackageName());
        String Description = getIntent().getStringExtra("description");
        ivSelectedImage.setImageResource(res);
        tvDesc.setText(Description);

        food.setName(Title);
        food.setDescription(Description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_app_menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Add:
                if (source.insertingDataInSecondTable(food)) {
                    Toast.makeText(DetailsActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailsActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }

        }
        return super.onOptionsItemSelected(item);
    }
}
