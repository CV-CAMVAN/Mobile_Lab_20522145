package com.example.querysqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListInforView extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.querysqlite/databases/DBINFO", null, SQLiteDatabase.OPEN_READWRITE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        ArrayList<User> arrayList = new ArrayList<>();
        Adapter adapter;

        listView = findViewById(R.id.listview);
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            arrayList.add(new User(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();

        adapter = new Adapter(ListInforView.this, R.layout.listview, arrayList);
        listView.setAdapter(adapter);

    }
}
