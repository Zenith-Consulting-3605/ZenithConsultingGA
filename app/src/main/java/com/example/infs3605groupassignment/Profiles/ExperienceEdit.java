package com.example.infs3605groupassignment.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.infs3605groupassignment.DbHelper;
import com.example.infs3605groupassignment.Objects.Experience;
import com.example.infs3605groupassignment.R;

public class ExperienceEdit extends AppCompatActivity {
    public String TAG = "ExperienceEdit";
    public static final String CODE_EXTRA = "CODE_EXTRA";
    private TextView title, company, location, description, startYear, endYear;
    private Spinner empType, startMonth, endMonth;
    private Button save;
    private ImageView close;
    private String savedTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_edit);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.9), (int) (height*0.9));

        Intent intent = getIntent();
        String identifier = intent.getStringExtra(ExperienceEdit.CODE_EXTRA);

        title = findViewById(R.id.txvTitle);
        company = findViewById(R.id.txvCompany);
        location = findViewById(R.id.txvLocation);
        description = findViewById(R.id.txvDescription);
        startYear = findViewById(R.id.txvStartYear);
        endYear = findViewById(R.id.txvEndYear);

        empType = findViewById(R.id.sprEmpType);
        startMonth = findViewById(R.id.sprStartMonth);
        endMonth = findViewById(R.id.sprEndMonth);

        String[] empTypeOptions = new String[] {"Full-Time", "Part-Time", "Casual"};
        String[] monthOptions = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, empTypeOptions);
        empType.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, monthOptions);
        startMonth.setAdapter(adapter1);
        endMonth.setAdapter(adapter1);

        final DbHelper dbHelper = new DbHelper(getApplicationContext());

        Experience experience = dbHelper.getExperience(identifier);
        title.setText(experience.getTitle());
        savedTitle = experience.getTitle();
        company.setText(experience.getCompany());
        location.setText(experience.getLocation());
        description.setText(experience.getDescription());

        String startDate = experience.getStartDate();
        String[] startParts = startDate.split("-");
        String startYear1 = startParts[0];
        String startMonth1 = startParts[1];
        String startDay1 = startParts[2];

        final String employType = experience.getEmploymentType();
        String endDate = experience.getEndDate();
        String[] endParts = endDate.split("-");
        String endYear1 = endParts[0];
        String endMonth1 = endParts[1];
        String endDay1 = endParts[2];

        startYear.setText(startYear1);
        endYear.setText(endYear1);

        if (employType != null) {
            int position = adapter.getPosition(employType);
            empType.setSelection(position);
        }

        if(startMonth1 != null) {
            int position = adapter1.getPosition(monthRet(startMonth1));
            startMonth.setSelection(position);
        }

        if(endMonth1 != null) {
            int position = adapter1.getPosition(monthRet(endMonth1));
            endMonth.setSelection(position);
        }


        save = findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assemTitle = title.getText().toString();
                String assemEmp = empType.getSelectedItem().toString();
                String assemCompany = company.getText().toString();
                String assemLocation = location.getText().toString();
                String assemStart = startYear.getText() + "-" + monthSet(startMonth.getSelectedItem().toString()) + "-01";
                String assemEnd = endYear.getText() + "-" + monthSet(endMonth.getSelectedItem().toString()) + "-01";
                String assemDescription = description.getText().toString();

                Experience editExp = new Experience(assemTitle, assemEmp, assemCompany, assemLocation, assemStart, assemEnd, assemDescription);
                dbHelper.setExperience(editExp, savedTitle);

                finish();
                startActivity(new Intent(ExperienceEdit.this, ProfileDetail.class));
                overridePendingTransition(0, 0);
            }
        });

        close = findViewById(R.id.imvClose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ExperienceEdit.this, ProfileDetail.class));
                overridePendingTransition(0, 0);
            }
        });
    }

    public String monthRet(String retrieved) {
        String monthS = null;
        switch(retrieved) {
            case "1":
                monthS = "January";
                break;
            case "2":
                monthS = "February";
                break;
            case "3":
                monthS = "March";
                break;
            case "4":
                monthS = "April";
                break;
            case "5":
                monthS = "May";
                break;
            case "6":
                monthS = "June";
                break;
            case "7":
                monthS = "July";
                break;
            case "8":
                monthS = "August";
                break;
            case "9":
                monthS = "September";
                break;
            case "10":
                monthS = "October";
                break;
            case "11":
                monthS = "November";
                break;
            case "12":
                monthS = "December";
                break;
        }
        return monthS;
    }

    public String monthSet(String setter) {
        String monthS = null;
        switch(setter) {
            case "January":
                monthS = "1";
                break;
            case "February":
                monthS = "2";
                break;
            case "March":
                monthS = "3";
                break;
            case "April":
                monthS = "4";
                break;
            case "May":
                monthS = "5";
                break;
            case "June":
                monthS = "6";
                break;
            case "July":
                monthS = "7";
                break;
            case "August":
                monthS = "8";
                break;
            case "September":
                monthS = "9";
                break;
            case "October":
                monthS = "10";
                break;
            case "November":
                monthS = "11";
                break;
            case "December":
                monthS = "12";
                break;
        }
        return monthS;
    }
}
