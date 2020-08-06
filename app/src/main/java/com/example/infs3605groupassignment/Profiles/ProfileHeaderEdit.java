package com.example.infs3605groupassignment.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Profile;
import com.example.infs3605groupassignment.R;

public class ProfileHeaderEdit extends AppCompatActivity {
    public String TAG = "ProfileHeaderEdit";
    public static final String CODE_EXTRA = "CODE_EXTRA";
    private TextView fname, lname, location, occupation;
    private Button save;
    private ImageView close, option1, option2;
    private String savedTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_header_edit);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.9));

        Intent intent = getIntent();
        String identifier = intent.getStringExtra(ProfileHeaderEdit.CODE_EXTRA);
        final int userID = intent.getIntExtra("userID", 0);

        fname = findViewById(R.id.txvFName);
        lname = findViewById(R.id.txvLName);
        location = findViewById(R.id.txvLocation);
        occupation = findViewById(R.id.txvOccupation);
        option1 = findViewById(R.id.profileOption1);
        option2 = findViewById(R.id.profileOption2);

        final DbHelper dbHelper = new DbHelper(getApplicationContext());

        final Profile profile = dbHelper.getProfile(userID);

        fname.setText(profile.getFirst_name());
        lname.setText(profile.getLast_name());
        location.setText(profile.getLocation());
        occupation.setText(profile.getOccupation());

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setProfilePreference(1);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setProfilePreference(2);
            }
        });

        save = findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assemFname = fname.getText().toString();
                String assemLname = lname.getText().toString();
                String assemLocation = location.getText().toString();
                String assemOccupation = occupation.getText().toString();

                Profile editProf = new Profile(assemFname, assemLname, assemLocation, assemOccupation, profile.getProfilePreference());
                dbHelper.editProfile(editProf, userID);
                finish();
                Intent intent = new Intent(ProfileHeaderEdit.this, ProfileActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        close = findViewById(R.id.imvClose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ProfileHeaderEdit.this, ProfileActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }
}
