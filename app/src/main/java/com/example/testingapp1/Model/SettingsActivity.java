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
        int color = Color.parseColor("#2A363B");
        toggleNightMode = (Button) findViewById(R.id.button1);
        toggleNightMode.setOnClickListener(this); // calling onClick() method
        togglelightmode = (Button) findViewById(R.id.button2);
        togglelightmode.setOnClickListener(this);
        int colorValue = color;
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
                View test = this.getWindow().getDecorView();
                test.setBackgroundColor(color);
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

            case R.id.button2:
                // do your code
                // Set the color of this activity
                int color2 = Color.parseColor("#FFFFFF");
                // Save color preference
                SharedPreferences sharedPref2 = SettingsActivity.this.getSharedPreferences("bgColorFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPref2.edit();
                editor2.putInt("color", color2);
                editor2.apply();
                View test2 = this.getWindow().getDecorView();
                test2.setBackgroundColor(color2);
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;

            default:
                break;
        }

    }
}
