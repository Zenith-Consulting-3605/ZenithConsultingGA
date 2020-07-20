package com.example.infs3605groupassignment.Projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.infs3605groupassignment.DbHelper;
import com.example.infs3605groupassignment.MainActivity;
import com.example.infs3605groupassignment.R;
import com.example.infs3605groupassignment.SignUp;

public class ProjectCreation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DbHelper db;
    EditText projectName,description;
    Spinner spinner = findViewById(R.id.spinnerCategory);
    RadioGroup progress;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_creation);


        db = new DbHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        projectName = findViewById(R.id.edtProjectName);
        description = findViewById(R.id.edtProjectName);
        spinner = findViewById(R.id.spinnerCategory);
        progress = findViewById(R.id.rgProgress);
        create = findViewById(R.id.btnCreate);


    create.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String n = projectName.getText().toString();
            String d = description.getText().toString();

            //RadioButton checkBtn = findViewById(userType.getCheckedRadioButtonId());
            //String utv = checkBtn.getText().toString();
            Boolean insertProjects = db.insertProjects(n,d);

            if (insertProjects = true){
                Toast.makeText(getApplicationContext(), "Project Creation Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProjectCreation.this, MainActivity.class));
            }else{
                Toast.makeText(getApplicationContext(), "Project Creation Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

    }

}

