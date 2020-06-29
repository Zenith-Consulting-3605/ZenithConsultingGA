package com.example.infs3605groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        home = findViewById(R.id.imvHome);
        project = findViewById(R.id.imvProject);
        profile = findViewById(R.id.imvProfile);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Home.class));
            }
        });
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Project.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Profile.class));
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new ExperienceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment, fragment).commit();

        experience = findViewById(R.id.txvExperience);
        skill = findViewById(R.id.txvSkill);
        qualification = findViewById(R.id.txvQualification);

        dividerExperience = findViewById(R.id.divExperience);
        dividerSkill = findViewById(R.id.divSkill);
        dividerQualification = findViewById(R.id.divQualification);


        experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ExperienceFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment, fragment).commit();
                dividerExperience.setBackgroundResource(R.color.dark_magenta);
                dividerSkill.setBackgroundResource(R.color.white);
                dividerQualification.setBackgroundResource(R.color.white);
            }
        });

        skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SkillFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment, fragment).commit();
                dividerExperience.setBackgroundResource(R.color.white);
                dividerSkill.setBackgroundResource(R.color.dark_magenta);
                dividerQualification.setBackgroundResource(R.color.white);
            }
        });

        qualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SkillFragment(); //NEED TO CREATE CORRECT FRAGMENT
                getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment, fragment).commit();
                dividerExperience.setBackgroundResource(R.color.white);
                dividerSkill.setBackgroundResource(R.color.white);
                dividerQualification.setBackgroundResource(R.color.dark_magenta);
            }
        });





    }
}
