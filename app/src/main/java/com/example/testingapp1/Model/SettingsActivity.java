package com.example.testingapp1.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testingapp1.MainActivity;
import com.example.testingapp1.R;

public class SettingsActivity extends Activity implements View.OnClickListener {

    Button toggleNightMode;
    Button togglelightmode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toggleNightMode = (Button) findViewById(R.id.button1);
        toggleNightMode.setOnClickListener(this); // calling onClick() method
        togglelightmode = (Button) findViewById(R.id.button2);
        togglelightmode.setOnClickListener(this);

        SharedPreferences sharedPreftest = getSharedPreferences("bgColorFile", Context.MODE_PRIVATE);
        int colorValue = sharedPreftest.getInt("color", 0);
        View test = this.getWindow().getDecorView();
        test.setBackgroundColor(colorValue);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1:
                // do your code
                // Set the color of this activity

                int color = Color.parseColor("#2A363B");
                // Save color preference
                SharedPreferences sharedPref = SettingsActivity.this.getSharedPreferences("bgColorFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("color", color);
                editor.apply();

                int color2 = Color.parseColor("#E84A5F");
                // Save color preference
                SharedPreferences sharedPref2 = SettingsActivity.this.getSharedPreferences("button_color", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPref2.edit();
                editor2.putInt("color_button", color2);
                editor2.apply();

                View test = this.getWindow().getDecorView();
                test.setBackgroundColor(color);
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

            case R.id.button2:
                // do your code
                // Set the color of this activity
                int color3 = Color.parseColor("#f3f2e7");
                // Save color preference
                SharedPreferences sharedPref3 = SettingsActivity.this.getSharedPreferences("bgColorFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor3 = sharedPref3.edit();
                editor3.putInt("color", color3);
                editor3.apply();

                int color4 = Color.parseColor("#3792CB");
                // Save color preference
                SharedPreferences sharedPref4 = SettingsActivity.this.getSharedPreferences("button_color", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor4 = sharedPref4.edit();
                editor4.putInt("color_button", color4);
                editor4.apply();

                View test2 = this.getWindow().getDecorView();
                test2.setBackgroundColor(color3);
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;

            default:
                break;
        }

    }
}
