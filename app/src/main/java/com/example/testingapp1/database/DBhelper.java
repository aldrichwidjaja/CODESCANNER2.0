package com.example.testingapp1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.testingapp1.Model.Listitem;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "my_table";
    public static final String DATABASE_NAME = "qrdb.db";

    public static final String COL_1 = "id";
    public static final String COL_2 = "code";
    public static final String COL_3 = "type";



    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " code TEXT, type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String code, String type)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, code);
        contentValues.put(COL_3, type);

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public ArrayList<Listitem> getAllInfo()
    {
        ArrayList<Listitem> arrayList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from "+TABLE_NAME, null);

        if(cursor!=null)
        {
            while(cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String code = cursor.getString(1);
                String type = cursor.getString(2);

                Listitem listitem = new Listitem(id, code, type);

                arrayList.add(listitem);
            }
        }
        cursor.close();
        return arrayList;
    }


    public void deleteRow(int value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME + "= WHERE "+COL_1+"='"+value+"'");

    }


}
