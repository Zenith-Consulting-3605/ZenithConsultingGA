package com.example.infs3605groupassignment.Projects;

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
import android.widget.TextView;

import com.example.infs3605groupassignment.Home;
import com.example.infs3605groupassignment.Profiles.Profile;
import com.example.infs3605groupassignment.Profiles.ProfileDetail;
import com.example.infs3605groupassignment.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManageProject extends AppCompatActivity {
    public static final String CODE_EXTRA = "CODE_EXTRA";
    private TextView project, invitation;
    private View dividerProject, dividerInvitation;
    private Button create;

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_project);

        Intent intent = getIntent();
        final int userID = intent.getIntExtra("userID", 0);

        toolbar = getSupportActionBar();
        BottomNavigationView nav = findViewById(R.id.bnvNav);
        nav.setSelectedItemId(R.id.project);
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
        Fragment fragment = new ProjectManageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("userID", userID);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment1, fragment).commit();

        project = findViewById(R.id.txvProjectPM);
        invitation = findViewById(R.id.txvInvitationPM);
        dividerProject = findViewById(R.id.dividerProject);
        dividerInvitation = findViewById(R.id.dividerInvitation);

        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProjectManageFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("userID", userID);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment1, fragment).commit();
                dividerProject.setBackgroundResource(R.color.dark_magenta);
                dividerInvitation.setBackgroundResource(R.color.white);
            }
        });

        invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new InvitationFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("userID", userID);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.scvFragment1, fragment).commit();
                dividerInvitation.setBackgroundResource(R.color.dark_magenta);
                dividerProject.setBackgroundResource(R.color.white);
            }
        });

        create = findViewById(R.id.btnCreateProj);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProjectCreation.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });


    }
}
