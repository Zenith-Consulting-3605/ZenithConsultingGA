package com.example.infs3605groupassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.infs3605groupassignment.Profiles.Profile;
import com.example.infs3605groupassignment.Projects.ManageProject;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = getSupportActionBar();
        BottomNavigationView nav = findViewById(R.id.bnvNav);
        nav.setSelectedItemId(R.id.home);
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


    }
}
