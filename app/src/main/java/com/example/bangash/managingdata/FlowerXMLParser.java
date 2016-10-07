package com.example.bangash.managingdata;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 9/8/2016.
 */
public class FlowerXMLParser {

    public List<Food> parseFeed(Context context) {
        try {

            boolean isDataItemTag = false;
            String CurrentTagName = "";
            Food food = null;
            List<Food> flowerList = new ArrayList<>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            InputStream stream = context.getResources().openRawResource(R.raw.flower);
            parser.setInput(stream, null);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        CurrentTagName = parser.getName();
                        if (CurrentTagName.equals("product")) {
                            isDataItemTag = true;
                            food = new Food();
                            flowerList.add(food);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("product")) {
                            isDataItemTag = false;
                        }
                        CurrentTagName = "";
                        break;
                    case XmlPullParser.TEXT:
                        if (isDataItemTag && food != null) {
                            switch (CurrentTagName) {

                                case "name":
                                    food.setName(parser.getText());
                                    break;
                                case "description":
                                    food.setDescription(parser.getText());
                                    break;
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
            return flowerList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
