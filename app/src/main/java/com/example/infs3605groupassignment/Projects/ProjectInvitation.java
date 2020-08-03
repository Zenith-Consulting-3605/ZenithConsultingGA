package com.example.infs3605groupassignment.Projects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.service.autofill.TextValueSanitizer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.User;
import com.example.infs3605groupassignment.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectInvitation extends AppCompatActivity implements CollaboratorSearchFragment.onNextPass {
    private String TAG = "PROJECT_INVITATION";

    private TextView project;
    private EditText search;
    private Button back, next;
    private List<User> inviteList;
    private int[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_invitation);

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        final String projName = extras.getString("projName");
        final int userID = intent.getIntExtra("userID", 0);
//        Log.d(TAG, projName);
        project = findViewById(R.id.txvDisplayProjName);
        project.setText(projName);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new CollaboratorSearchFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.scvSearchContainer, fragment).commit();

        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNextReview);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProjectCreation.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Created Array " + Arrays.toString(ids));
                Intent intent = new Intent(getApplicationContext(), ProjectReview.class);
                intent.putExtra("Invitee_Array", ids);
                intent.putExtra("userID", userID);
                intent.putExtra("projName", projName);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onNextPass (List<User> inviteList) {
        this.inviteList = inviteList;

        ids = new int[inviteList.size()];

        for (int i = 0; i < inviteList.size(); i++) {
            int id = inviteList.get(i).getID();
            ids[i] = id;

        }
        Log.d(TAG, "id " + Arrays.toString(ids));
    }



}