package com.example.infs3605groupassignment.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

//import com.example.infs3605groupassignment.Profiles.Profile;
import com.example.infs3605groupassignment.Profiles.ProfileActivity;
import com.example.infs3605groupassignment.Projects.ManageProject;
import com.example.infs3605groupassignment.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private String TAG = "HOME";
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        final int userID = intent.getIntExtra("userID", 0);
        Log.d(TAG, "userID is " + userID);

        toolbar = getSupportActionBar();
        BottomNavigationView nav = findViewById(R.id.bnvNav);
        nav.setSelectedItemId(R.id.home);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        intent.putExtra("userID", userID);
                        startActivity(intent);
                        return true;
                    case R.id.project:
                        Intent intent1 = new Intent(getApplicationContext(), ManageProject.class);
                        intent1.putExtra("userID", userID);
                        startActivity(intent1);
                        return true;
                    case R.id.profile:
                        Intent intent2 = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent2.putExtra("userID", userID);
                        startActivity(intent2);
                        return true;
                }
                return false;
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new FeaturedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("userID", userID);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.scvFeatured, fragment).commit();

    }
}
