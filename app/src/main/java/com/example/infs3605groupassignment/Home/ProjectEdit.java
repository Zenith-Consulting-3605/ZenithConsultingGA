package com.example.infs3605groupassignment.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.Projects.ManageProject;
import com.example.infs3605groupassignment.R;

public class ProjectEdit extends AppCompatActivity {
    private int userID, projID;
    private EditText name, description, company, country;
    private RadioButton ideation, development, complete, selectedProgress;
    private RadioButton sponsorship, notProfit, selectedFunding;
    private RadioGroup progress;
    private RadioGroup funding;
    private Button save;
    private ImageView cancel;
    private Spinner category;
    private Project project = new Project();

    private String TAG = "PROJECT_EDIT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_edit);

        final Intent intent = getIntent();
        userID = intent.getIntExtra("userID", 0);
        projID = intent.getIntExtra("projID", 0);
        Log.d(TAG, "projID is: " + projID);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.9), (int) (height*0.9));

        name = findViewById(R.id.edtProjName);
        description = findViewById(R.id.edtProjDescription);
        company = findViewById(R.id.edtProjCompany);
        country = findViewById(R.id.edtProjCountry);

        ideation = findViewById(R.id.rbtIdeation1);
        development = findViewById(R.id.rbtInDevelopment1);
        complete = findViewById(R.id.rbtComplete1);

        ideation.setChecked(false);
        development.setChecked(false);
        complete.setChecked(false);

        ideation.setEnabled(true);
        development.setEnabled(true);
        complete.setEnabled(true);

        progress = findViewById(R.id.rgpProgress1);
        funding = findViewById(R.id.rgpFunding1);
        sponsorship = findViewById(R.id.rbtRS1);
        notProfit = findViewById(R.id.rbtNFP1);

        save = findViewById(R.id.btnProjSave);

        category = findViewById(R.id.sprProjectCategory);
        String[] categoryOptions = new String[] {"Creative Writing & Copywriting", "Graphic Design", "UX/UI Design", "Product Design", "Front-end Development", "Mobile & Web Development", "Illustration & Animation", "Fashion & Textile Design", "Architecture", "Photography & Videography"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryOptions);
        category.setAdapter(adapter);

        cancel = findViewById(R.id.imvClose2);

        final DbHelper dbHelper = new DbHelper(getApplicationContext());

        Project project = dbHelper.getDetailProject(projID);

        name.setText(project.getName());
        description.setText(project.getDescription());
        company.setText(project.getCompany());
        country.setText(project.getCountry());

        String p = project.getProgress();
//        Log.d(TAG, "PROGRESS" + p);

        if (p.equals("A")) {
            ideation.setChecked(true);
        } else if (p.equals("B")) {
            development.setChecked(true);
        } else {
            complete.setChecked(true);
        }

//        Log.d(TAG, "FUNDING" + project.getFunding());
        if (project.getFunding().equals("Requires Sponsorship")) {
            sponsorship.setChecked(true);
        } else {
            notProfit.setChecked(true);
        }

        String c = project.getCategory();
//        Log.d(TAG, "CATEGORY" + c);
        if (c.equals("Creative Writing & Copywriting")) {
            category.setSelection(0);
        } else if (c.equals("Graphic Design")) {
            category.setSelection(1);
        } else if (c.equals("UX/UI Design")) {
            category.setSelection(2);
        } else if (c.equals("Product Design")) {
            category.setSelection(3);
        } else if (c.equals("Front-end Development")) {
            category.setSelection(4);
        } else if (c.equals("Mobile & Web Development")) {
            category.setSelection(5);
        } else if (c.equals("Illustration & Animation")) {
            category.setSelection(6);
        } else if (c.equals("Fashion & Textile Design")) {
            category.setSelection(7);
        } else if (c.equals("Architecture")) {
            category.setSelection(8);
        } else {
            category.setSelection(9);
        }

        save = findViewById(R.id.btnProjSave);
        save.setOnClickListener(new View.OnClickListener() {
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

                Project editProject = new Project(projName, projDescription, projCategory, projFunding, projProgress, projCountry, projCompany);
                dbHelper.setProject(editProject, projID);

                finish();
                Intent intent2 = new Intent(ProjectEdit.this, ManageProject.class);
                intent2.putExtra("userID", userID);
                startActivity(intent2);
                overridePendingTransition(0,0);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent1 = new Intent(ProjectEdit.this, ManageProject.class);
                intent1.putExtra("userID", userID);
                startActivity(intent1);
                overridePendingTransition(0,0);
            }
        });





    }

}