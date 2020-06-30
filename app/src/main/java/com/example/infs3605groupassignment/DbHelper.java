package com.example.infs3605groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User.db";
    private SQLiteDatabase db;

    private List<Experience> experienceList = new ArrayList<>();
    private List<Skill> skillList = new ArrayList<>();

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        db.execSQL("DROP TABLE IF EXISTS " + DbContract.ExperienceTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.UsersTable.TABLE_NAME);

        final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
                DbContract.UsersTable.TABLE_NAME + "( " +
                DbContract.UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContract.UsersTable.EMAIL + " TEXT, " +
                DbContract.UsersTable.PASSWORD + " TEXT," +
                DbContract.UsersTable.FIRST_NAME + " TEXT, " +
                DbContract.UsersTable.LAST_NAME + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_USERS_TABLE);

        db.execSQL("CREATE TABLE " + DbContract.ProfileTable.TABLE_NAME + "( " + DbContract.ProfileTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.ProfileTable.FIRST_NAME + " TEXT, " + DbContract.ProfileTable.LAST_NAME + " TEXT, " + DbContract.ProfileTable.OCCUPATION + " TEXT, " + DbContract.ProfileTable.LOCATION + " TEXT )");
        db.execSQL("CREATE TABLE " + DbContract.ExperienceTable.TABLE_NAME + "( " + DbContract.ExperienceTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.ExperienceTable.TITLE + " TEXT, " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + " TEXT, " + DbContract.ExperienceTable.COMPANY + " TEXT, " + DbContract.ExperienceTable.LOCATION + " TEXT, " + DbContract.ExperienceTable.START_DATE + " TEXT, " + DbContract.ExperienceTable.END_DATE + " TEXT, " + DbContract.ExperienceTable.DESCRIPTION + " TEXT, " + DbContract.ExperienceTable.USER_ID + " INTEGER )");
        db.execSQL("CREATE TABLE " + DbContract.SkillTable.TABLE_NAME + "( " + DbContract.SkillTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.SkillTable.NAME + " TEXT, " + DbContract.SkillTable.DESCRIPTION + " TEXT, " + DbContract.SkillTable.USER_ID + " INTEGER )");

        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Web Designer, Frontend Developer', 'Full-Time', 'Champion', 'Sydney, Australia', '2009-12-12', '2010-09-11', 'Worked at Champion like an absolute Champ aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Frontend Developer', 'Full-Time', 'BigBoi Co.', 'Sydney, Australia', '2005-12-14', '2006-11-20', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Intern @ Deloitte Digital', 'Full-Time', 'Deloitte Australia', 'Sydney, Australia', '2020-12-12', '2021-01-01', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('CBA Customer Specialist', 'Full-Time', 'Commonwealth Bank of Australia', 'Sydney, Australia', '1998-01-03', '2001-04-01', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('JIT System Developer', 'Full-Time', 'Bye', 'Sydney, Australia', '2011-08-13', '2011-11-23', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Engineering Intern @ TerumoBCT', 'Full-Time', 'To Say', 'Sydney, Australia', '2014-08-13', '2014-09-14', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('AUTOCad Developer', 'Full-Time', 'Haw', 'Sydney, Australia', '2014-08-14', '2015-12-15', 'Was a BigLad', 1)");

        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ") VALUES ('UI Design', 'Learnt to design UIs professionally', 1)");
        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ") VALUES ('UX Design', 'Learnt to design UXs professionally', 1)");
        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ") VALUES ('Adobe XD', 'Learnt to design UX/UI using Adobe XD', 1)");
        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ") VALUES ('Web Design', 'Learnt to HTML5 to design webpages', 1)");
        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ") VALUES ('Android Studio v1.2', 'Learnt to design UIs professionally in Android Studio', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.UsersTable.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + DbContract.ProfileTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.ExperienceTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.SkillTable.TABLE_NAME);

        onCreate(db);
    }

    public boolean insert(String firstName, String lastName, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", firstName);
        contentValues.put("last_name", lastName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        //contentValues.put("user_type", userType);

        long ins = db.insert("users", null, contentValues);
        if(ins==-1) return false;
        else return true;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=?", new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean loginCheck(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? and password=?", new String[]{email,password});
        if(cursor.getCount()>0)return true;
        else return false;
    }





    public List<Experience> getExperiences() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + " FROM " + DbContract.ExperienceTable.TABLE_NAME + " WHERE " + DbContract.ExperienceTable.USER_ID + " = " + 1 + " ORDER BY " + DbContract.ExperienceTable.START_DATE + " DESC", null); //NEED TO SWITCH USER_ID WHEN USER PROFILES ARE CORRECTLY SET UP

        int titleCol = cursor.getColumnIndex(DbContract.ExperienceTable.TITLE);
        int companyCol = cursor.getColumnIndex(DbContract.ExperienceTable.COMPANY);
        int startCol = cursor.getColumnIndex(DbContract.ExperienceTable.START_DATE);
        int endCol = cursor.getColumnIndex(DbContract.ExperienceTable.END_DATE);

        while (cursor.moveToNext()) {
            String title = cursor.getString(titleCol);
            String company = cursor.getString(companyCol);
            String start = cursor.getString(startCol);
            String end = cursor.getString(endCol);
            Experience retrievedExp = new Experience(title, company, start, end);
            experienceList.add(retrievedExp);
        }

        return experienceList;
    }

    public List<Experience> getDetailExperiences() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DbContract.ExperienceTable.TABLE_NAME + " WHERE " + DbContract.ExperienceTable.USER_ID + " = " + 1 + " ORDER BY " + DbContract.ExperienceTable.START_DATE + " DESC", null);

        int titleCol = cursor.getColumnIndex(DbContract.ExperienceTable.TITLE);
        int empTypeCol = cursor.getColumnIndex(DbContract.ExperienceTable.EMPLOYMENT_TYPE);
        int companyCol = cursor.getColumnIndex(DbContract.ExperienceTable.COMPANY);
        int locationCol = cursor.getColumnIndex(DbContract.ExperienceTable.LOCATION);
        int startCol = cursor.getColumnIndex(DbContract.ExperienceTable.START_DATE);
        int endCol = cursor.getColumnIndex(DbContract.ExperienceTable.END_DATE);
        int descriptionCol = cursor.getColumnIndex(DbContract.ExperienceTable.DESCRIPTION);

        while (cursor.moveToNext()) {
            String title = cursor.getString(titleCol);
            String empType = cursor.getString(empTypeCol);
            String company = cursor.getString(companyCol);
            String location = cursor.getString(locationCol);
            String start = cursor.getString(startCol);
            String end = cursor.getString(endCol);
            String description = cursor.getString(descriptionCol);
            Experience retrievedExp = new Experience(title, empType, company, location, start, end, description);
            experienceList.add(retrievedExp);
        }

        return experienceList;
    }

    public List<Skill> getSkills() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + DbContract.SkillTable.NAME + " FROM " + DbContract.SkillTable.TABLE_NAME + " WHERE " + DbContract.SkillTable.USER_ID + " = " + 1 + " ORDER BY " + DbContract.SkillTable.NAME, null); //NEED TO SWITCH USER_ID WHEN USER PROFILES ARE CORRECTLY SET UP

        while (cursor.moveToNext()) {
            Skill retrievedSkill = new Skill(cursor.getString(cursor.getColumnIndex(DbContract.SkillTable.NAME)));
            skillList.add(retrievedSkill);
        }

        return skillList;
    }
}
