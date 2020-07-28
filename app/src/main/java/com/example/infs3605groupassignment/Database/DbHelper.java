package com.example.infs3605groupassignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.infs3605groupassignment.Objects.Experience;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.Objects.Skill;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User.db";
    private SQLiteDatabase db;
    private String TAG = "Db_Helper";

    private List<Experience> experienceList = new ArrayList<>();
    private List<Skill> skillList = new ArrayList<>();
    private List<Project> projectList = new ArrayList<>();

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
        db.execSQL("CREATE TABLE " + DbContract.SkillTable.TABLE_NAME + "( " + DbContract.SkillTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.SkillTable.NAME + " TEXT, " + DbContract.SkillTable.DESCRIPTION + " TEXT, " + DbContract.SkillTable.USER_ID + " INTEGER, " + DbContract.SkillTable.DUMMY + " INTEGER)");
        db.execSQL("CREATE TABLE " + DbContract.ProjectTable.TABLE_NAME + "( " + DbContract.ProjectTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.ProjectTable.NAME + " TEXT, " + DbContract.ProjectTable.DESCRIPTION + " TEXT, " + DbContract.ProjectTable.FUNDING + " TEXT, " + DbContract.ProjectTable.CATEGORY + " TEXT, " + DbContract.ProjectTable.PROGRESS + " TEXT, " + DbContract.ProjectTable.COUNTRY + " TEXT, " + DbContract.ProjectTable.COMPANY + " TEXT, " + DbContract.ProjectTable.OWNER + " INTEGER)"); //CREATE TABLE FOR PROJECTS
//        db.execSQL("CREATE TABLE " + DbContract.CollaboratorTable.TABLE_NAME + "( " + DbContract.CollaboratorTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.CollaboratorTable.N); //CREATE TABLE FOR COLLABORATORS

        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Web Designer, Frontend Developer', 'Full-Time', 'Champion', 'Sydney, Australia', '2009-12-12', '2010-09-11', 'Worked at Champion like an absolute Champ aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Frontend Developer', 'Full-Time', 'BigBoi Co.', 'Sydney, Australia', '2005-12-14', '2006-11-20', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Intern @ Deloitte Digital', 'Full-Time', 'Deloitte Australia', 'Sydney, Australia', '2020-12-12', '2021-01-01', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('CBA Customer Specialist', 'Full-Time', 'Commonwealth Bank of Australia', 'Sydney, Australia', '1998-01-03', '2001-04-01', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('JIT System Developer', 'Full-Time', 'Bye', 'Sydney, Australia', '2011-08-13', '2011-11-23', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Engineering Intern @ TerumoBCT', 'Full-Time', 'To Say', 'Sydney, Australia', '2014-08-13', '2014-09-14', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('AUTOCad Developer', 'Full-Time', 'Haw', 'Sydney, Australia', '2014-08-14', '2015-12-15', 'Was a BigLad', 1)");

        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ", " + DbContract.SkillTable.DUMMY + ") VALUES ('UI Design', 'Learnt to design UIs professionally', 1, 0)");
        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ", " + DbContract.SkillTable.DUMMY + ") VALUES ('UX Design', 'Learnt to design UXs professionally', 1, 0)");
        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ", " + DbContract.SkillTable.DUMMY + ") VALUES ('Adobe XD', 'Learnt to design UX/UI using Adobe XD', 1, 0)");
        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ", " + DbContract.SkillTable.DUMMY + ") VALUES ('Web Design', 'Learnt to HTML5 to design webpages', 1, 0)");
        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ", " + DbContract.SkillTable.DUMMY + ") VALUES ('Android Studio v1.2', 'Learnt to design UIs professionally in Android Studio', 1, 0)");
        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ", " + DbContract.SkillTable.DUMMY + ") VALUES ('Add Skill+', 'Add Skill+', 1, 1)");

        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Development of Jaws 2', 'Big Box Office Production for the sequel to the original Jaws movie', 'Creative Writing & Copywriting', 'Requires Sponsorship', 'A', 'USA', 'Jawy Boys', 1)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Redesign of IBM Company Logo', 'A redesign of the IBM company logo has been commissioned', 'Graphic Design', 'Requires Sponsorship', 'B', 'USA', 'IBM', 1)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Designing the UI/UX of Mudskipper.IO', 'Mudskipper.IO is an up and coming company which is aiming to create a platform which will reduce fragmentation in the creative desing industry', 'UX/UI Design', 'Requires Sponsorship', 'A', 'India', 'Mudskipper.IO', 1)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Flex Tape', 'Flex Tape is the newest fad to hit the markets! It can seal up any cracks and problems', 'Product Design', 'Requires Sponsorship', 'C', 'New Zealand', 'Flex Seal & Tape', 2)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Development for InLinked', 'As a completely seperate app to LinkedIn, InLinked attempts to link individuals within themselves and find their true calling!', 'Front-end Development', 'Requires Sponsorship', 'A', 'Japan', 'InLinked', 2)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Mobile Application for Valorant', 'When analysing the market for mobile games, there is a need for FPS on mobile experiences. This is the project for converting Valorant to a mobile application which can be monetised', 'Mobile & Web Developent', 'Not-for-profit', 'A', 'South Korea', 'Riot Games', 2)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Avatar: The Last Airbender(S)', 'Continuing from the original Avatar: The Last Airbender. Avatar: The Last Airbender(S) is the sequel which is a satircal work which will satsify all weaboos', 'Illustration & Animation', 'Requires Sponsorship', 'C', 'Canada', 'Avatary Boys', 1)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Virgil Abloh: Fall Season 3', 'Join Virgil Abloh in creating the hottest styles for the next season. Compensation will be sufficient to last you a lifetime', 'Fashion & Textile Design', 'Requires Sponsorship', 'B', 'USA', 'Off-White', 3)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Falling Clouds', 'Instead of Falling Water, join Frank Lloyd Wright in designing and building Falling Clouds. Falling Clouds is his next big project for the Australian Government and will define the next generation of architects', 'Architecture', 'Not-for-profit', 'A', 'USA', 'Masterton Artchitects', 4)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Fast & Furious 21', 'Join the leading producers from the Fast & Furious franchise as a photographer. Your primary role will be to find views which befit the storied movie franchise', 'Photography & Videography', 'Requires Sponsorship', 'B', 'Japan', 'Roadshow Studios', 4)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.UsersTable.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + DbContract.ProfileTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.ExperienceTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.SkillTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.ProjectTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.CollaboratorTable.TABLE_NAME);

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

    public boolean insertProjects(String projectName, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", projectName);
        contentValues.put("description", description);

        long ins = db.insert("project",null, contentValues);
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

    public Experience getExperience(String identifier) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DbContract.ExperienceTable.TABLE_NAME + " WHERE " + DbContract.ExperienceTable.USER_ID + " = " + 1 + " AND " + DbContract.ExperienceTable.TITLE + " = '" + identifier + "'", null);

        int titleCol = cursor.getColumnIndex(DbContract.ExperienceTable.TITLE);
        int empTypeCol = cursor.getColumnIndex(DbContract.ExperienceTable.EMPLOYMENT_TYPE);
        int companyCol = cursor.getColumnIndex(DbContract.ExperienceTable.COMPANY);
        int locationCol = cursor.getColumnIndex(DbContract.ExperienceTable.LOCATION);
        int startCol = cursor.getColumnIndex(DbContract.ExperienceTable.START_DATE);
        int endCol = cursor.getColumnIndex(DbContract.ExperienceTable.END_DATE);
        int descriptionCol = cursor.getColumnIndex(DbContract.ExperienceTable.DESCRIPTION);
        String title, empType, company, location, start, end, description;
        Experience retrievedExp = null;

        while (cursor.moveToNext()) {
            title = cursor.getString(titleCol);
            empType = cursor.getString(empTypeCol);
            company = cursor.getString(companyCol);
            location = cursor.getString(locationCol);
            start = cursor.getString(startCol);
            end = cursor.getString(endCol);
            description = cursor.getString(descriptionCol);
            retrievedExp = new Experience(title, empType, company, location, start, end, description);
        }

        return retrievedExp;

    }

    public void setExperience(Experience editExp, String oldTitle) {
        SQLiteDatabase db = this.getWritableDatabase();

        String title = editExp.getTitle();
        String empType = editExp.getEmploymentType();
        String company = editExp.getCompany();
        String location = editExp.getLocation();
        String description = editExp.getDescription();
        String startDate = editExp.getStartDate();
        String endDate = editExp.getEndDate();

        db.execSQL("UPDATE " + DbContract.ExperienceTable.TABLE_NAME + " SET " + DbContract.ExperienceTable.TITLE + " = '" + title + "', " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + " = '" + empType + "', " + DbContract.ExperienceTable.COMPANY + " = '" + company + "', " + DbContract.ExperienceTable.LOCATION + " = '" + location + "', " + DbContract.ExperienceTable.DESCRIPTION + " = '" + description + "', " + DbContract.ExperienceTable.START_DATE + " = '" + startDate + "', " + DbContract.ExperienceTable.END_DATE + " = '" + endDate + "' WHERE " + DbContract.ExperienceTable.TITLE + " = '" + oldTitle + "'");
    }

    public void addExperience(Experience newExperience) {
        SQLiteDatabase db = this.getWritableDatabase();

        String title = newExperience.getTitle();
        String empType = newExperience.getEmploymentType();
        String company = newExperience.getCompany();
        String location = newExperience.getLocation();
        String description = newExperience.getDescription();
        String startDate = newExperience.getStartDate();
        String endDate = newExperience.getEndDate();

        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " (" + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('" + title + "', '" + empType + "', '" + company + "', '" + location + "', '" + startDate + "', '" + endDate + "', '" + description + "', " + 1 + ")"); // NEED TO SWITCH TO WORK WITH INDIVIDUAL PROFILES
    }

    public List<Skill> getSkills() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + DbContract.SkillTable.NAME + " FROM " + DbContract.SkillTable.TABLE_NAME + " WHERE " + DbContract.SkillTable.USER_ID + " = " + 1 + " ORDER BY " + DbContract.SkillTable.DUMMY + ", " + DbContract.SkillTable.NAME, null); //NEED TO SWITCH USER_ID WHEN USER PROFILES ARE CORRECTLY SET UP

        while (cursor.moveToNext()) {
            Skill retrievedSkill = new Skill(cursor.getString(cursor.getColumnIndex(DbContract.SkillTable.NAME)));
            skillList.add(retrievedSkill);
        }

        return skillList;
    }

    public void addSkill(Skill newSkill) {
        SQLiteDatabase db = this.getWritableDatabase();

        String addName = newSkill.getName();
        String addDescription = newSkill.getDescription();

        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ", " + DbContract.SkillTable.DUMMY + ") VALUES ('" + addName + "', '" + addDescription + "', 1, 0)");
    }

    public List<Project> getIndividualProjects() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.PROGRESS + " FROM " + DbContract.ProjectTable.TABLE_NAME + " WHERE " + DbContract.ProjectTable.OWNER + " = " + 1 + " ORDER BY " + DbContract.ProjectTable.PROGRESS, null);

        int nameCol = cursor.getColumnIndex(DbContract.ProjectTable.NAME);
        int companyCol = cursor.getColumnIndex(DbContract.ProjectTable.COMPANY);
        int categoryCol = cursor.getColumnIndex(DbContract.ProjectTable.CATEGORY);
        int progressCol = cursor.getColumnIndex(DbContract.ProjectTable.PROGRESS);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameCol);
            String company = cursor.getString(companyCol);
            String category = cursor.getString(categoryCol);
            String progress = cursor.getString(progressCol);
            Project retrievedProj = new Project(name, company, category, progress);
            Log.d(TAG, "getIndividualProjects: " + name);
            projectList.add(retrievedProj);
        }

        return projectList;
    }

}
