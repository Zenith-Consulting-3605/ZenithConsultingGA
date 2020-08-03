package com.example.infs3605groupassignment.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.MainActivity;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.R;

public class ProjectCreation extends AppCompatActivity {
    private EditText name, description, company, country;
    private RadioButton ideation, development, complete, selectedProgress;
    private RadioButton sponsorship, notProfit, selectedFunding;
    private RadioGroup progress;
    private RadioGroup funding;
    private Button create, cancel;
    private Spinner category;

    private String TAG = "PROJECT_CREATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_creation);

        Intent intent = getIntent();
        final int userID = intent.getIntExtra("userID", 0);

        final DbHelper dbHelper = new DbHelper(getApplicationContext());

        name = findViewById(R.id.edtProjectName);
        description = findViewById(R.id.edtProjectDescription);
        company = findViewById(R.id.edtProjectCompany);
        country = findViewById(R.id.edtProjectCountry);

        ideation = findViewById(R.id.rbtIdeation);
        development = findViewById(R.id.rbtInDevelopment);
        complete = findViewById(R.id.rbtComplete);

        ideation.setChecked(false);
        development.setChecked(false);
        complete.setChecked(false);

        ideation.setEnabled(true);
        development.setEnabled(true);
        complete.setEnabled(true);

        progress = findViewById(R.id.rgpProgress);
        funding = findViewById(R.id.rgpFunding);
        sponsorship = findViewById(R.id.rbtRS);
        notProfit = findViewById(R.id.rbtNFP);

        create = findViewById(R.id.btnNextCreateProject);

        category = findViewById(R.id.sprProjCategory);
        String[] categoryOptions = new String[] {"Creative Writing & Copywriting", "Graphic Design", "UX/UI Design", "Product Design", "Front-end Development", "Mobile & Web Development", "Illustration & Animation", "Fashion & Textile Design", "Architecture", "Photography & Videography"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryOptions);
        category.setAdapter(adapter);

        cancel = findViewById(R.id.btnCPC);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageProject.class);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projName = name.getText().toString();
                String projDescription = description.getText().toString();
                String projCompany = company.getText().toString();
                String projCountry = country.getText().toString();

                int checkedProgress = progress.getCheckedRadioButtonId();
                selectedProgress = findViewById(checkedProgress);
                String bProgress = selectedProgress.getText().toString();
                String projProgress;

                if (bProgress.equals("Ideation")) {
                    projProgress = "A";
                } else if (bProgress.equals("In Development")) {
                    projProgress = "B";
                } else {
                    projProgress = "C";
                }

                int checkedFunding = funding.getCheckedRadioButtonId();
                selectedFunding = findViewById(checkedFunding);
                String projFunding = selectedFunding.getText().toString();

                String projCategory = category.getSelectedItem().toString();

//                Log.d(TAG, projName + projDescription + projCompany + projCountry + projProgress + projFunding + projCategory);
                Project newProject = new Project(projName, projDescription, projCategory, projFunding, projProgress, projCountry, projCompany);
                dbHelper.addProject(newProject, userID);

                Intent intent = new Intent(getApplicationContext(), ProjectInvitation.class);
                intent.putExtra("projName", projName);
                intent.putExtra("userID", userID);
                startActivity(intent);

            }
        });


    }

}

