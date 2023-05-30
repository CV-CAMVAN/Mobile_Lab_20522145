package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    private EditText fullname, username, phone, password;
    private Button btn_Register;
    private TextView Loginnow;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.Edit_FullName);
        username = findViewById(R.id.Edit_UserName);
        phone = findViewById(R.id.Edit_Phone);
        password = findViewById(R.id.Edit_Password);
        Loginnow = findViewById(R.id.text_login);
        btn_Register = findViewById(R.id.button_signup);

        Loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting data from edittext fields.
                String usernamet = username.getText().toString();
                String fullnamet = fullname.getText().toString();
                String Passwordt = password.getText().toString();
                String Phonet = phone.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(fullnamet)) {
                    username.setError("Please enter full name");
                } else if (TextUtils.isEmpty(Phonet)) {
                    password.setError("Please enter phone");
                } else if (TextUtils.isEmpty(usernamet)) {
                    password.setError("Please enter user name");
                }
                else if (TextUtils.isEmpty(Passwordt)) {
                    password.setError("Please enter password");
                }
                else {
                    // calling method to add data to Firebase Firestore.
                    addDataToFirestore(usernamet, fullnamet, Phonet, Passwordt);
                }
            }
        });
    }
    private void addDataToFirestore(String fullName, String Phone, String Username, String Password) {

        CollectionReference dbUsers = db.collection("users");

        // adding our data to our courses object class.
        User userr = new User(fullName, Phone, Username, Password);

        Map<String, Object> user = new HashMap<>();
        user.put("name", userr.getFullName());
        user.put("phone", userr.getPhone());
        user.put("username", userr.getUserName());
        user.put("password", Encrypt.HashPasswordMd5(Password));

        dbUsers.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Toast.makeText(register.this, "Sign Up Successfully \n", Toast.LENGTH_LONG).show();
                username.setText("");
                fullname.setText("");
                password.setText("");
                phone.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Log.d("abc",e.getStackTrace().toString());
                Toast.makeText(register.this, "registration failed\n \n" + e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

