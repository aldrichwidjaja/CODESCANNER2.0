package com.example.testingapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testingapp1.Adapters.MyAdapter;
import com.example.testingapp1.Model.Listitem;
import com.example.testingapp1.database.DBhelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class qrscanner extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Listitem> arrayList;

    DBhelper helper;

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        recyclerView = (RecyclerView) findViewById(R.id.r_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helper = new DBhelper(this);

        SharedPreferences sharedPreftest = getSharedPreferences("bgColorFile", Context.MODE_PRIVATE);
        int colorValue = sharedPreftest.getInt("color", 0);
        View test = this.getWindow().getDecorView();
        test.setBackgroundColor(colorValue);
        // fetch data from database.. if it is avail = show in recyclerview

        SharedPreferences sharedPreftest2 = getSharedPreferences("button_color", Context.MODE_PRIVATE);
        int colorValue2 = sharedPreftest2.getInt("color_button", 0);

        TextView topbarr = (TextView) findViewById(R.id.topbarr);
        topbarr.setBackgroundColor(colorValue2);

        arrayList = helper.getAllInfo();

        if(arrayList.size()>0)
        {
            myAdapter = new MyAdapter(arrayList, this);
            recyclerView.setAdapter(myAdapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "NO DATA FOUND", Toast.LENGTH_LONG).show();
        }

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                Listitem listitem = arrayList.get(position);

                helper.deleteRow(listitem.getId());
                arrayList.remove(position);
                myAdapter.notifyItemRemoved(position);
                myAdapter.notifyItemRangeChanged(position, arrayList.size());

            }
        }).attachToRecyclerView(recyclerView);


        final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setCameraId(0);


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floating);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator.initiateScan();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null)
        {
            if(result.getContents() == null)
            {
                Toast.makeText(getApplicationContext(), "No Result Found", Toast.LENGTH_SHORT).show();

            }else
            {
                boolean isInserted = helper.insertData(result.getFormatName(), result.getContents());

                if(isInserted)
                {
                    arrayList.clear();
                    arrayList = helper.getAllInfo();
                    myAdapter = new MyAdapter(arrayList, this);
                    recyclerView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
