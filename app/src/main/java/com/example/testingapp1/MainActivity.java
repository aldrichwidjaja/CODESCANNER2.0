package com.example.testingapp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        TextView topbar = (TextView) findViewById(R.id.topbar);
        ImageView logo = (ImageView) findViewById(R.id.editText);
        RelativeLayout whole = (RelativeLayout) findViewById(R.id.whole) ;

        SharedPreferences sharedPreftest2 = getSharedPreferences("button_color", Context.MODE_PRIVATE);
        int colorValue2 = sharedPreftest2.getInt("color_button", 0);

        if (colorValue == 0 | colorValue2 == 0)
        {
            button.setBackgroundColor(getResources().getColor(R.color.red));
            button2.setBackgroundColor(getResources().getColor(R.color.red));
            topbar.setBackgroundColor(getResources().getColor(R.color.red));
            logo.setColorFilter(getResources().getColor(R.color.red));
            whole.setBackgroundColor(getResources().getColor(R.color.defaultcolor));
        } else {
            button.setBackgroundColor(colorValue2);
            button2.setBackgroundColor(colorValue2);
            topbar.setBackgroundColor(colorValue2);
            System.out.println(colorValue2);
            logo.setColorFilter(colorValue2);
            whole.setBackgroundColor(colorValue);
        }


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