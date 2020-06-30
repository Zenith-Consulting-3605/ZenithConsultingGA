package com.example.infs3605groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Project extends AppCompatActivity {
    public static final String CODE_EXTRA = "CODE_EXTRA";
    private TextView trial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        //WILL BE ERASED
        Intent intent = getIntent();
        String title = intent.getStringExtra(ProfileDetail.CODE_EXTRA);
        trial = findViewById(R.id.txvTRIAL);
        trial.setText(title);
        //WILL BE ERASED
    }
}
