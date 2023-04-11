package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editUsername;
    EditText editSalary;
    Button butCal;
    ScrollView scrollViewResult;

    List<PerSaraly> perSalaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsername = findViewById(R.id.edit_name);
        editSalary = findViewById(R.id.edit_salary);
        butCal = findViewById(R.id.but_cal);
        scrollViewResult = findViewById(R.id.scrollview_result);
        perSalaryList = new ArrayList<>();

        butCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                int salary = Integer.parseInt(editSalary.getText().toString());

                PerSaraly perSalary = new PerSaraly(username, salary);
                perSalary.calSalary();
                perSalaryList.add(perSalary);
                showResult();
            }
        });
    }
    private void showResult() {
        LinearLayout linearLayoutResult = findViewById(R.id.linearlayout_result);
        linearLayoutResult.removeAllViews();

        for (PerSaraly perSalary : perSalaryList) {
            TextView textView = new TextView(this);
            DecimalFormat decimalFormat = new DecimalFormat("#");
            String netSalaryStr = decimalFormat.format(perSalary.getNetSalary());
            textView.setText("Full name: " + perSalary.getUsername() + ", Net salary: " + netSalaryStr);
            linearLayoutResult.addView(textView);
        }
    }
}
