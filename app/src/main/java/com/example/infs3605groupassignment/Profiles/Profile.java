package com.example.infs3605groupassignment.Profiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infs3605groupassignment.Home;
import com.example.infs3605groupassignment.MainActivity;
import com.example.infs3605groupassignment.Projects.ManageProject;
import com.example.infs3605groupassignment.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {
    private ImageView home;
    private ImageView project;
    private ImageView profile;

    private TextView experience;
    private TextView skill;
    private TextView qualification;

    private View dividerExperience;
    private View dividerSkill;
    private View dividerQualification;

    private ActionBar toolbar;

    private FloatingActionButton fab;

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        final int userID = intent.getIntExtra("userID", 0);

        toolbar = getSupportActionBar();
        BottomNavigationView nav = findViewById(R.id.bnvNav);
        nav.setSelectedItemId(R.id.profile);
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
                        Intent intent2 = new Intent(getApplicationContext(), Profile.class);
                        intent2.putExtra("userID", userID);
                        startActivity(intent2);
                        return true;
                }
                return false;
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new ExperienceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("userID", userID);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment, fragment).commit();

        experience = findViewById(R.id.txvExperience);
        skill = findViewById(R.id.txvSkill);
        qualification = findViewById(R.id.txvQualification);

        dividerExperience = findViewById(R.id.divExperience);
        dividerSkill = findViewById(R.id.divSkill);
        dividerQualification = findViewById(R.id.divQualification);

        fab = findViewById(R.id.fabAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExperienceAdd.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        });



        experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ExperienceFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("userID", userID);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment, fragment).commit();
                dividerExperience.setBackgroundResource(R.color.dark_magenta);
                dividerSkill.setBackgroundResource(R.color.white);
                dividerQualification.setBackgroundResource(R.color.white);
                fab.setVisibility(View.VISIBLE);
            }
        });

        skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SkillFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("userID", userID);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment, fragment).commit();
                dividerExperience.setBackgroundResource(R.color.white);
                dividerSkill.setBackgroundResource(R.color.dark_magenta);
                dividerQualification.setBackgroundResource(R.color.white);
                fab.setVisibility(View.GONE);

            }
        });

        qualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProjectFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("userID", userID);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment, fragment).commit();
                dividerExperience.setBackgroundResource(R.color.white);
                dividerSkill.setBackgroundResource(R.color.white);
                dividerQualification.setBackgroundResource(R.color.dark_magenta);
                fab.setVisibility(View.GONE);
            }
        });

    }

}
