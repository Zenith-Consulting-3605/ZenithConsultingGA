package com.example.infs3605groupassignment.Profiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.infs3605groupassignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileDetail extends AppCompatActivity {
    public static final String CODE_EXTRA = "CODE_EXTRA";
    private TextView trial;
    private FloatingActionButton fabR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra(ProfileDetail.CODE_EXTRA);
        final int userID = intent.getIntExtra("userID", 0);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new ExperienceDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString("title", title);
        arguments.putInt("userID", userID);
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.scvDC1, fragment).commit();

        fabR = findViewById(R.id.fabReturn);
        fabR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });
    }
}
