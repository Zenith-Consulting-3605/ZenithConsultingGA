package com.example.infs3605groupassignment.Objects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infs3605groupassignment.Home;
import com.example.infs3605groupassignment.Profiles.Profile;
import com.example.infs3605groupassignment.Profiles.ProfileDetail;
import com.example.infs3605groupassignment.R;

public class Project extends AppCompatActivity {
    public static final String CODE_EXTRA = "CODE_EXTRA";
    private TextView trial;

    private ImageView home;
    private ImageView project;
    private ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        home = findViewById(R.id.imvHome);
        project = findViewById(R.id.imvProject);
        profile = findViewById(R.id.imvProfile);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Project.this, Home.class));
            }
        });
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Project.this, Project.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Project.this, Profile.class));
            }
        });

        //WILL BE ERASED
        Intent intent = getIntent();
        String title = intent.getStringExtra(ProfileDetail.CODE_EXTRA);
        trial = findViewById(R.id.txvTRIAL);
        trial.setText(title);
        //WILL BE ERASED
    }
}
