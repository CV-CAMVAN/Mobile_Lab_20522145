package com.example.lab4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;




public class Login extends AppCompatActivity {

    private EditText Username, Password;
    private Button btnLogin;
    private TextView Signupnow;
    SharedPreferences ref;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences  = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

        Signupnow = findViewById(R.id.text_signup);
        Username = findViewById(R.id.Edit_UserName);
        Password = findViewById(R.id.Edit_Password);
        btnLogin = findViewById(R.id.button_login);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting data from edittext fields.
                String username = Username.getText().toString();
                String password = Password.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(username)) {
                    Username.setError("Please enter User Name");
                }
                if(ValidateUsername(username) == false){
                    Toast.makeText(getApplicationContext(),"username must be alphanumeric and above 6 characters ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(ValidatePassword(password) == false){
                    Toast.makeText(getApplicationContext(),"Password must be more than 6 characters",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Please enter password");
                }
                db.collection("users")
                        .whereEqualTo("username",username)
                        .whereEqualTo("password", Encrypt.HashPasswordMd5(password))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(!task.getResult().isEmpty()){
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", username);
                                    editor.apply();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),"Sign Up Successfully \n",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"registration failed\n",Toast.LENGTH_LONG).show();

                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"registration failed \n",Toast.LENGTH_LONG).show();

                            }
                        });

            }
            public boolean ValidateUsername(String username) {
                String usernameRegex = "^[a-zA-Z]{6,}$";
                if (username.matches(usernameRegex)) {
                    return true;
                } else {
                    return false;}
            }
            public boolean ValidatePassword(String password) {
                if (password.length() >= 6) {
                    return true;
                } else { return false;}
            }
        });
        Signupnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(Login.this, register.class);
                startActivity(switchActivityIntent);
                finishAffinity();
                }
            });
    }
}
