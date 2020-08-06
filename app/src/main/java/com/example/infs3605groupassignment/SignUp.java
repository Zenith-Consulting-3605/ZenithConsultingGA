package com.example.infs3605groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.infs3605groupassignment.Database.DbHelper;

public class SignUp extends AppCompatActivity {

    DbHelper db;
    private EditText firstName, lastName, email, password;
    private Button signup;
    private RadioGroup userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new DbHelper(this);

        firstName = findViewById(R.id.edtFirstName);
        lastName = findViewById(R.id.edtLastName);
        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);
        signup = findViewById(R.id.btnSignUp);
        userType = findViewById(R.id.rgUserType);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn = firstName.getText().toString();
                String ln = lastName.getText().toString();
                String em = email.getText().toString();
                String ps = password.getText().toString();

                //RadioButton checkBtn = findViewById(userType.getCheckedRadioButtonId());
                //String utv = checkBtn.getText().toString();


                if(firstName.equals("")||lastName.equals("")||email.equals("")||password.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill in empty fields", Toast.LENGTH_SHORT).show();
                }else {
                    if (em.equals(em)) {
                        Boolean checkEmail = db.checkEmail(em);
                        if (checkEmail == true) {
                            Boolean insert = db.insert(fn, ln, em, ps);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                int userId = db.getUserID(em, ps);
                                db.generateAddSkill(userId);
                                startActivity(new Intent(SignUp.this, MainActivity.class));
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{}
                }

            }
        });

    }
}
