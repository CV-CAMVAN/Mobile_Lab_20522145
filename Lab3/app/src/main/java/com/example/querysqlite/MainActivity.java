package com.example.querysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText fullName;
    EditText phoneNumber;
    Button local;
    Button query;
    DatabaseHelper databaseHelper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.fullname);
        phoneNumber = findViewById(R.id.number);
        local = findViewById(R.id.local);
        query = findViewById(R.id.query);
        context = getApplicationContext();
        databaseHelper = new DatabaseHelper(context);


        local.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = String.valueOf(fullName.getText());
                String PN = String.valueOf(phoneNumber.getText());
                databaseHelper.QueryData("INSERT INTO USER VALUES("+name+","+PN+")");
            }
        });

        query.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListInforView.class);
                startActivity(intent);
            }
        });
    }
}