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

        SharedPreferences sharedPreftest2 = getSharedPreferences("button_color", Context.MODE_PRIVATE);
        int colorValue2 = sharedPreftest2.getInt("color_button", 0);

        button.setBackgroundColor(colorValue2);
        button2.setBackgroundColor(colorValue2);

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
            Intent intent = new Intent(view.getContext(), SettingsActivity.class);
            startActivity(intent);
        }

    }
}