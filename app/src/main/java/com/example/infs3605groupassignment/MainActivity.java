package com.example.infs3605groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Profiles.Profile;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MAIN_ACTIVITY";
    DbHelper db;
    private Button signIn;
    private Button signUp;
    private EditText email, password;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbHelper(this);

        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);


        signIn = findViewById(R.id.btnLogin);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String ps = password.getText().toString();
                Boolean checkLogin = db.loginCheck(em,ps);

                Log.d(TAG, em);
                Log.d(TAG, ps);

                if(checkLogin==true){
                    Toast.makeText(getApplicationContext(),"Successful Login", Toast.LENGTH_SHORT).show();
                    int userID = db.getUserID(em, ps);
                    Log.d(TAG, "userID is " + userID);
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(),"Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signUp = findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });
    }
}
