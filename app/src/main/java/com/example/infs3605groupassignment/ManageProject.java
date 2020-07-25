package com.example.infs3605groupassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.infs3605groupassignment.Profiles.Profile;
import com.example.infs3605groupassignment.Profiles.ProfileDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManageProject extends AppCompatActivity {
    public static final String CODE_EXTRA = "CODE_EXTRA";
    private TextView trial;

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_project);

        toolbar = getSupportActionBar();
        BottomNavigationView nav = findViewById(R.id.bnvNav);
        nav.setSelectedItemId(R.id.project);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        return true;
                    case R.id.project:
                        startActivity(new Intent(getApplicationContext(), ManageProject.class));
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        return true;
                }
                return false;
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
