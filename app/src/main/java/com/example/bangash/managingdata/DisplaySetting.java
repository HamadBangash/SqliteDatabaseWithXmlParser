package com.example.bangash.managingdata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class DisplaySetting extends AppCompatActivity {
    boolean cbSelection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_setting);
        final CheckBox cbDisplay = (CheckBox) findViewById(R.id.cbDisplay);
        cbDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;

                if (checkBox.isChecked()) {
                    cbSelection = true;
                } else {
                    cbSelection = false;
                }

            }
        });

        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplaySetting.this, MainActivity.class);
                intent.putExtra("Value", cbSelection);
                startActivity(intent);
                finish();

            }
        });
    }
}
