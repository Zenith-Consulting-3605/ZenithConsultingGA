package com.example.infs3605groupassignment.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Experience;
import com.example.infs3605groupassignment.R;


public class ExperienceAdd extends AppCompatActivity {
    public String TAG = "ExperienceAdd";

    private TextView title, company, location, description, startYear, endYear;
    private Spinner empType, startMonth, endMonth;
    private Button add;
    private ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_add);

        Intent intent = getIntent();
        final int userID = intent.getIntExtra("userID", 0);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.9), (int) (height*0.9));

        title = findViewById(R.id.txvTitleAdd);
        company = findViewById(R.id.txvCompanyAdd);
        location = findViewById(R.id.txvLocationAdd);
        description = findViewById(R.id.txvDescriptionAdd);
        startYear = findViewById(R.id.txvStartYearAdd);
        endYear = findViewById(R.id.txvEndYearAdd);

        empType = findViewById(R.id.sprEmpTypeAdd);
        startMonth = findViewById(R.id.sprStartMonthAdd);
        endMonth = findViewById(R.id.sprEndMonthAdd);

        String[] empTypeOptions = new String[] {"Full-Time", "Part-Time", "Casual"};
        String[] monthOptions = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, empTypeOptions);
        empType.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, monthOptions);
        startMonth.setAdapter(adapter1);
        endMonth.setAdapter(adapter1);

        final DbHelper dbHelper = new DbHelper(getApplicationContext());
//
        add = findViewById(R.id.btnExperienceAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assemTitle = title.getText().toString();
                String assemEmp = empType.getSelectedItem().toString();
                String assemCompany = company.getText().toString();
                String assemLocation = location.getText().toString();
                String assemStart = startYear.getText() + "-" + monthSet(startMonth.getSelectedItem().toString()) + "-01";
                String assemEnd = endYear.getText() + "-" + monthSet(endMonth.getSelectedItem().toString()) + "-01";
                String assemDescription = description.getText().toString();

                Log.d(TAG, "The Experience is: " + assemTitle);
                Experience newExp = new Experience(assemTitle, assemEmp, assemCompany, assemLocation, assemStart, assemEnd, assemDescription);

                dbHelper.addExperience(newExp, userID);

                finish();

                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        close = findViewById(R.id.imvClose1);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), Profile.class));
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
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