package com.example.bangash.managingdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bangash on 10/3/2016.
 */
public class customAdapter extends ArrayAdapter {
    Context context;
    List<Food> names;
    int imgs[];

    public customAdapter(Context context, List<Food> names, int imgs[]) {
        super(context, R.layout.custom_listview_layout, names);
        this.context = context;
        this.names = names;
        this.imgs = imgs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Food food = new Food();
        food = names.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_listview_layout, null);
        ImageView ivListviewImage = (ImageView) view.findViewById(R.id.ivListviewImage);
        ivListviewImage.setImageResource(imgs[position]);
        TextView tvListItems = (TextView) view.findViewById(R.id.tvListviewItems);
        tvListItems.setText(food.getName());
        return view;
    }
}
