package com.example.infs3605groupassignment.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.R;

public class ProjectDetail extends AppCompatActivity {
    private TextView name, company, country, description, category, funding, progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        Intent intent = getIntent();
        int userID = intent.getIntExtra("userID", 0);
        int projID = intent.getIntExtra("projID", 0);

        DbHelper dbHelper = new DbHelper(getApplicationContext());

        Project project = dbHelper.getDetailProject(projID);

        name = findViewById(R.id.txvDPName);
        company = findViewById(R.id.txvDPCompany);
        country = findViewById(R.id.txvDPCountry);
        description = findViewById(R.id.txvDPDescription);
        category = findViewById(R.id.txvDPCategory);
        funding = findViewById(R.id.txvDPFunding);
        progress = findViewById(R.id.txvDPProgress);

        name.setText(project.getName());
        company.setText(project.getCompany());
        country.setText(project.getCountry());
        description.setText(project.getDescription());
        category.setText(project.getCategory());
        funding.setText(project.getFunding());
        progress.setText(project.getProgress());
    }
}