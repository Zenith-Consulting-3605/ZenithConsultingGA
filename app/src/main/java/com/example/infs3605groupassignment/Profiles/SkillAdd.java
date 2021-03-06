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
import com.example.infs3605groupassignment.Objects.Skill;
import com.example.infs3605groupassignment.R;

public class SkillAdd extends AppCompatActivity {
    private ImageView close;
    private Button add;
    private TextView name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_add);

        Intent intent = getIntent();
        final int userID = intent.getIntExtra("userID", 0);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.9), (int) (height*0.5));

        final DbHelper dbHelper = new DbHelper(getApplicationContext());

        close = findViewById(R.id.imvClose);
        add = findViewById(R.id.btnSkillAdd);
        name = findViewById(R.id.txvTitle);
        description = findViewById(R.id.txvCompany);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assemName = name.getText().toString();
                String assemDescription = description.getText().toString();
                Skill tbdSkill = new Skill(assemName, assemDescription);
                dbHelper.addSkill(tbdSkill, userID);
                finish();
                Intent intent = new Intent(SkillAdd.this, ProfileActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(SkillAdd.this, ProfileActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }
}