package com.example.testingapp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testingapp1.Model.SettingsActivity;


public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;
    Button button3;
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreftest = getSharedPreferences("bgColorFile", Context.MODE_PRIVATE);
        int colorValue = sharedPreftest.getInt("color", 0);
        View test = this.getWindow().getDecorView();
        test.setBackgroundColor(colorValue);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

    }

    public void buttonClicked(View view) {

        if (view.getId() == R.id.button) {
            // Save color preference
            Intent intent = new Intent(view.getContext(), qrgenerator.class);
            startActivity(intent);
        } else if (view.getId() == R.id.button2) {
            // Save color preference
            Intent intent = new Intent(view.getContext(), qrscanner.class);
            startActivity(intent);
        } else if (view.getId() == R.id.button3) {
            int color2 = Color.parseColor("#FFFFFF");
            // Save color preference
            SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("bgColorFile", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("color", color2);
            editor.apply();
            View test = this.getWindow().getDecorView();
            test.setBackgroundColor(color2);
        } else if (view.getId() == R.id.button4) {
            int color3 = Color.parseColor("#2A363B");
            // Save color preference
            SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("bgColorFile", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("color", color3);
            editor.apply();
            View test = this.getWindow().getDecorView();
            test.setBackgroundColor(color3);
        }

    }
}