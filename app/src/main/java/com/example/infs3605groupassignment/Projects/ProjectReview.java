package com.example.infs3605groupassignment.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.R;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProjectReview extends AppCompatActivity {
    private String TAG = "PROJECT_REVIEW";
    private int[] invitees;
    private Button complete, inviteMore;
    private String projectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_review);

        Intent intent = getIntent();
        final int userID = intent.getIntExtra("userID", 0);

        final DbHelper dbHelper = new DbHelper(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        int[] inviteeArray = extras.getIntArray("Invitee_Array");
        projectName = extras.getString("projName");

        Log.d(TAG, "Passed Array " + Arrays.toString(inviteeArray));
        Log.d(TAG, "PROJECT NAME " + projectName);

        invitees = removeDuplicates(inviteeArray);

        inviteMore = findViewById(R.id.btnInvMore);
        inviteMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProjectInvitation.class);
                intent.putExtra("Invitee_Array", invitees);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

        complete = findViewById(R.id.btnCompleteProject);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int projID = dbHelper.getProjectID(projectName, userID);
                Log.d(TAG, "corresponding ID is " + projID);
                for(int i = 0; i < invitees.length; i++) {
                    dbHelper.sendInvitation(invitees[i], userID, projID);

                    Log.d(TAG, "INFO IS " + invitees[i] + " " + 1);

                    Intent intent = new Intent(getApplicationContext(), ManageProject.class);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                }
            }
        });

    }

    public int[] removeDuplicates(int[] invitees) {
        Set<Integer> setString = new LinkedHashSet<Integer>();
        for(int i = 0; i < invitees.length; i++) {
            setString.add(invitees[i]);
        }

        int[] uniqueArray = new int[setString.size()];
        int dex = 0;
        for (Integer i : setString) {
            uniqueArray[dex++] = i;
        }
        Log.d(TAG, "Unique Array" + Arrays.toString(uniqueArray));

        return uniqueArray;
    }
}