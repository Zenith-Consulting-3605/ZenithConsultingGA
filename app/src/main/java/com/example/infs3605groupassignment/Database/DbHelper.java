package com.example.infs3605groupassignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.infs3605groupassignment.Objects.Experience;
import com.example.infs3605groupassignment.Objects.Profile;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.Objects.Skill;
import com.example.infs3605groupassignment.Objects.User;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User.db";
    private SQLiteDatabase db;
    private String TAG = "Db_Helper";

    private List<Experience> experienceList = new ArrayList<>();
    private List<Skill> skillList = new ArrayList<>();
    private List<Project> projectList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();

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

        db.execSQL("CREATE TABLE " + DbContract.ProfileTable.TABLE_NAME + "( " + DbContract.ProfileTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.ProfileTable.FIRST_NAME + " TEXT, " + DbContract.ProfileTable.LAST_NAME + " TEXT, " + DbContract.ProfileTable.LOCATION + " TEXT, " + DbContract.ProfileTable.OCCUPATION + " TEXT, "  +  DbContract.ProfileTable.USER_ID + " INTEGER )");
        db.execSQL("CREATE TABLE " + DbContract.ExperienceTable.TABLE_NAME + "( " + DbContract.ExperienceTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.ExperienceTable.TITLE + " TEXT, " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + " TEXT, " + DbContract.ExperienceTable.COMPANY + " TEXT, " + DbContract.ExperienceTable.LOCATION + " TEXT, " + DbContract.ExperienceTable.START_DATE + " TEXT, " + DbContract.ExperienceTable.END_DATE + " TEXT, " + DbContract.ExperienceTable.DESCRIPTION + " TEXT, " + DbContract.ExperienceTable.USER_ID + " INTEGER )");
        db.execSQL("CREATE TABLE " + DbContract.SkillTable.TABLE_NAME + "( " + DbContract.SkillTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.SkillTable.NAME + " TEXT, " + DbContract.SkillTable.DESCRIPTION + " TEXT, " + DbContract.SkillTable.USER_ID + " INTEGER, " + DbContract.SkillTable.DUMMY + " INTEGER)");
        db.execSQL("CREATE TABLE " + DbContract.ProjectTable.TABLE_NAME + "( " + DbContract.ProjectTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.ProjectTable.NAME + " TEXT, " + DbContract.ProjectTable.DESCRIPTION + " TEXT, " + DbContract.ProjectTable.FUNDING + " TEXT, " + DbContract.ProjectTable.CATEGORY + " TEXT, " + DbContract.ProjectTable.PROGRESS + " TEXT, " + DbContract.ProjectTable.COUNTRY + " TEXT, " + DbContract.ProjectTable.COMPANY + " TEXT, " + DbContract.ProjectTable.OWNER + " INTEGER)"); //CREATE TABLE FOR PROJECTS
        db.execSQL("CREATE TABLE " + DbContract.CollaboratorTable.TABLE_NAME + "( " + DbContract.CollaboratorTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DbContract.CollaboratorTable.MEMBER + " INTEGER, " + DbContract.CollaboratorTable.OWNER + " INTEGER, " + DbContract.CollaboratorTable.PROJECT_ID + " INTEGER, " + DbContract.CollaboratorTable.STATUS + " TEXT)"); //CREATE TABLE FOR COLLABORATORS

        db.execSQL("INSERT INTO " + DbContract.UsersTable.TABLE_NAME + "( " + DbContract.UsersTable.FIRST_NAME + ", " + DbContract.UsersTable.LAST_NAME + ", " + DbContract.UsersTable.EMAIL + ", " + DbContract.UsersTable.PASSWORD + ") VALUES ('James', 'Cook', 'james.cook@gmail.com', 'jamescook')");
        db.execSQL("INSERT INTO " + DbContract.UsersTable.TABLE_NAME + "( " + DbContract.UsersTable.FIRST_NAME + ", " + DbContract.UsersTable.LAST_NAME + ", " + DbContract.UsersTable.EMAIL + ", " + DbContract.UsersTable.PASSWORD + ") VALUES ('Sam', 'Smith', 'sammyboy@gmail.com', 'samsmith')");
        db.execSQL("INSERT INTO " + DbContract.UsersTable.TABLE_NAME + "( " + DbContract.UsersTable.FIRST_NAME + ", " + DbContract.UsersTable.LAST_NAME + ", " + DbContract.UsersTable.EMAIL + ", " + DbContract.UsersTable.PASSWORD + ") VALUES ('Shiv', 'Kumar', 'shivkumar@hotmail.com', 'shivkumar')");
        db.execSQL("INSERT INTO " + DbContract.UsersTable.TABLE_NAME + "( " + DbContract.UsersTable.FIRST_NAME + ", " + DbContract.UsersTable.LAST_NAME + ", " + DbContract.UsersTable.EMAIL + ", " + DbContract.UsersTable.PASSWORD + ") VALUES ('Vidal', 'Coe', 'coe.vidal@outlook.com', 'vidalcoe')");
        db.execSQL("INSERT INTO " + DbContract.UsersTable.TABLE_NAME + "( " + DbContract.UsersTable.FIRST_NAME + ", " + DbContract.UsersTable.LAST_NAME + ", " + DbContract.UsersTable.EMAIL + ", " + DbContract.UsersTable.PASSWORD + ") VALUES ('Jesus', 'Christ', 'jesus@gmail.com', 'jesuschrist')");
        db.execSQL("INSERT INTO " + DbContract.UsersTable.TABLE_NAME + "( " + DbContract.UsersTable.FIRST_NAME + ", " + DbContract.UsersTable.LAST_NAME + ", " + DbContract.UsersTable.EMAIL + ", " + DbContract.UsersTable.PASSWORD + ") VALUES ('a', 'a', 'a', 'a')");

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
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Mobile Application for Valorant', 'When analysing the market for mobile games, there is a need for FPS on mobile experiences. This is the project for converting Valorant to a mobile application which can be monetised', 'Mobile & Web Development', 'Not-for-profit', 'A', 'South Korea', 'Riot Games', 2)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Avatar: The Last Airbender(S)', 'Continuing from the original Avatar: The Last Airbender. Avatar: The Last Airbender(S) is the sequel which is a satircal work which will satsify all weaboos', 'Illustration & Animation', 'Requires Sponsorship', 'C', 'Canada', 'Avatary Boys', 1)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Virgil Abloh: Fall Season 3', 'Join Virgil Abloh in creating the hottest styles for the next season. Compensation will be sufficient to last you a lifetime', 'Fashion & Textile Design', 'Requires Sponsorship', 'B', 'USA', 'Off-White', 3)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Falling Clouds', 'Instead of Falling Water, join Frank Lloyd Wright in designing and building Falling Clouds. Falling Clouds is his next big project for the Australian Government and will define the next generation of architects', 'Architecture', 'Not-for-profit', 'A', 'USA', 'Masterton Artchitects', 4)");
        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('Fast & Furious 21', 'Join the leading producers from the Fast & Furious franchise as a photographer. Your primary role will be to find views which befit the storied movie franchise', 'Photography & Videography', 'Requires Sponsorship', 'B', 'Japan', 'Roadshow Studios', 4)");

        db.execSQL("INSERT INTO " + DbContract.ProfileTable.TABLE_NAME + " (" + DbContract.ProfileTable.FIRST_NAME + ", " + DbContract.ProfileTable.LAST_NAME + ", " + DbContract.ProfileTable.LOCATION + ", " + DbContract.ProfileTable.OCCUPATION + ", " + DbContract.ProfileTable.USER_ID + ") VALUES ('James', 'Cook', 'Sydney', 'Captain', 1)");
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


//        try {
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        } finally {
//            cursor.close();
//        }

    //FIXED//
    public List<Experience> getExperiences(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + " FROM " + DbContract.ExperienceTable.TABLE_NAME + " WHERE " + DbContract.ExperienceTable.USER_ID + " = '" + userID + "' ORDER BY " + DbContract.ExperienceTable.START_DATE + " DESC", null);
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
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return experienceList;
    }
    //FIXED//
    public List<Experience> getDetailExperiences(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DbContract.ExperienceTable.TABLE_NAME + " WHERE " + DbContract.ExperienceTable.USER_ID + " = '" + userID + "' ORDER BY " + DbContract.ExperienceTable.START_DATE + " DESC", null);

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
    //FIXED//
    public Experience getExperience(String identifier, int userID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Experience retrievedExp = null;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + DbContract.ExperienceTable.TABLE_NAME + " WHERE " + DbContract.ExperienceTable.USER_ID + " = '" + userID + "' AND " + DbContract.ExperienceTable.TITLE + " = '" + identifier + "'", null);

            int titleCol = cursor.getColumnIndex(DbContract.ExperienceTable.TITLE);
            int empTypeCol = cursor.getColumnIndex(DbContract.ExperienceTable.EMPLOYMENT_TYPE);
            int companyCol = cursor.getColumnIndex(DbContract.ExperienceTable.COMPANY);
            int locationCol = cursor.getColumnIndex(DbContract.ExperienceTable.LOCATION);
            int startCol = cursor.getColumnIndex(DbContract.ExperienceTable.START_DATE);
            int endCol = cursor.getColumnIndex(DbContract.ExperienceTable.END_DATE);
            int descriptionCol = cursor.getColumnIndex(DbContract.ExperienceTable.DESCRIPTION);
            String title, empType, company, location, start, end, description;


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
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return retrievedExp;
    }
    //MAY NEED FIXING//
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
    //FIXED//
    public void deleteExperience(int expId){
        SQLiteDatabase db = this.getWritableDatabase();
      db.execSQL("DELETE FROM " + DbContract.ExperienceTable.TABLE_NAME + " WHERE " + DbContract.ExperienceTable._ID + " = '" + expId + "'");

    }
    //FIXED//
    public void addExperience(Experience newExperience, int userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String title = newExperience.getTitle();
        String empType = newExperience.getEmploymentType();
        String company = newExperience.getCompany();
        String location = newExperience.getLocation();
        String description = newExperience.getDescription();
        String startDate = newExperience.getStartDate();
        String endDate = newExperience.getEndDate();

        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " (" + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('" + title + "', '" + empType + "', '" + company + "', '" + location + "', '" + startDate + "', '" + endDate + "', '" + description + "', " + userID + ")"); // NEED TO SWITCH TO WORK WITH INDIVIDUAL PROFILES
    }
    //FIXED//
    public List<Skill> getSkills(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT " + DbContract.SkillTable.NAME + " FROM " + DbContract.SkillTable.TABLE_NAME + " WHERE " + DbContract.SkillTable.USER_ID + " = '" + userID + "' ORDER BY " + DbContract.SkillTable.DUMMY + ", " + DbContract.SkillTable.NAME, null); //NEED TO SWITCH USER_ID WHEN USER PROFILES ARE CORRECTLY SET UP

            while (cursor.moveToNext()) {
                Skill retrievedSkill = new Skill(cursor.getString(cursor.getColumnIndex(DbContract.SkillTable.NAME)));
                skillList.add(retrievedSkill);
            }
            return skillList;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return skillList;
    }
    //FIXED//
    public void addSkill(Skill newSkill, int userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String addName = newSkill.getName();
        String addDescription = newSkill.getDescription();

        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ", " + DbContract.SkillTable.DUMMY + ") VALUES ('" + addName + "', '" + addDescription + "', + '" + userID + "', 0)");
    }
    //FIXED//
    public List<Project> getIndividualProjects(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.PROGRESS + " FROM " + DbContract.ProjectTable.TABLE_NAME + " WHERE " + DbContract.ProjectTable.OWNER + " = '" + userID + "' ORDER BY " + DbContract.ProjectTable.PROGRESS, null);

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
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return projectList;
    }
    //FIXED//
    public List<Project> getProjectManageList(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.PROGRESS + " FROM " + DbContract.ProjectTable.TABLE_NAME + " WHERE " + DbContract.ProjectTable.OWNER + " = '" + userID + "' ORDER BY " + DbContract.ProjectTable.PROGRESS, null);

            int nameCol = cursor.getColumnIndex(DbContract.ProjectTable.NAME);
            int fundingCol = cursor.getColumnIndex(DbContract.ProjectTable.FUNDING);
            int companyCol = cursor.getColumnIndex(DbContract.ProjectTable.COMPANY);
            int countryCol = cursor.getColumnIndex(DbContract.ProjectTable.COUNTRY);
            int descriptionCol = cursor.getColumnIndex(DbContract.ProjectTable.DESCRIPTION);
            int progressCol = cursor.getColumnIndex(DbContract.ProjectTable.PROGRESS);

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameCol);
                String funding = cursor.getString(fundingCol);
                String company = cursor.getString(companyCol);
                String country = cursor.getString(countryCol);
                String description = cursor.getString(descriptionCol);
                String progress = cursor.getString(progressCol);
                Project retreivedProj = new Project(name, funding, company, country, description, progress);
                projectList.add(retreivedProj);
            }

            return projectList;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return projectList;
    }
    //FIXED//
    public void addProject(Project newProject, int userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String name = newProject.getName();
        String description = newProject.getDescription();
        String category = newProject.getCategory();
        String funding = newProject.getFunding();
        String progress = newProject.getProgress();
        String country = newProject.getCountry();
        String company = newProject.getCompany();

        db.execSQL("INSERT INTO " + DbContract.ProjectTable.TABLE_NAME + " (" + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.CATEGORY + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.OWNER + ") VALUES ('" + name + "', '" + description + "', '" + category + "', '" + funding + "', '" + progress + "', '" + country + "', '" + company + "', '" + userID + "')"); // NEED TO SWITCH TO WORK WITH INDIVIDUAL PROFILES
    }
    //FIXED//
    public int getProjectID(String projName, int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        int projID = 0;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.ProjectTable._ID + " FROM " + DbContract.ProjectTable.TABLE_NAME + " WHERE " + DbContract.ProjectTable.NAME + " = '" + projName + "' AND " + DbContract.ProjectTable.OWNER + " = " + userID, null);

            while (cursor.moveToNext()) {
                projID = cursor.getInt(cursor.getColumnIndex(DbContract.ProjectTable._ID));
            }
            return projID;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return projID;
    }
    //WILL NEED TO PERHAPS FILTER OUT INDIVIDUAL OWNING PROJECT//
    public List<User> getUserList(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.UsersTable._ID + ", " + DbContract.UsersTable.FIRST_NAME + ", " + DbContract.UsersTable.LAST_NAME + ", " + DbContract.UsersTable.EMAIL + " FROM " + DbContract.UsersTable.TABLE_NAME + " WHERE " + DbContract.UsersTable._ID + " != " + userID, null);

            int idCol = cursor.getColumnIndex(DbContract.UsersTable._ID);
            int fNameCol = cursor.getColumnIndex(DbContract.UsersTable.FIRST_NAME);
            int lNameCol = cursor.getColumnIndex(DbContract.UsersTable.LAST_NAME);
            int emailCol = cursor.getColumnIndex(DbContract.UsersTable.EMAIL);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idCol);
                String fName = cursor.getString(fNameCol);
                String lName = cursor.getString(lNameCol);
                String email = cursor.getString(emailCol);
                User retreivedUser = new User(id, fName, lName,email);
                userList.add(retreivedUser);
            }

            return userList;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return userList;
    }
    //FIXED//
    public int getUserID(String email, String password) {
        SQLiteDatabase db= this.getReadableDatabase();
        int userID = 0;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.UsersTable._ID + " FROM " + DbContract.UsersTable.TABLE_NAME + " WHERE " + DbContract.UsersTable.EMAIL + " = '" + email + "' AND " + DbContract.UsersTable.PASSWORD + " = '" + password + "'", null);

            while (cursor.moveToNext()) {
                userID = cursor.getInt(cursor.getColumnIndex(DbContract.UsersTable._ID));
            }
            return userID;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return userID;
    }

    public int getExperienceID(String experience, int userID){
        SQLiteDatabase db = this.getReadableDatabase();
        int expID = 0;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.ExperienceTable._ID + " FROM " + DbContract.ExperienceTable.TABLE_NAME + " WHERE " + DbContract.ExperienceTable.TITLE + " = '" + experience + "' AND " + DbContract.ExperienceTable.USER_ID + " = " + userID, null);

            while (cursor.moveToNext()) {
                expID = cursor.getInt(cursor.getColumnIndex(DbContract.ExperienceTable._ID));
            }
            return expID;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return expID;
    }
    //FIXED//
    public void sendInvitation(int member, int owner, int projectID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + DbContract.CollaboratorTable.TABLE_NAME + " (" + DbContract.CollaboratorTable.MEMBER + ", " + DbContract.CollaboratorTable.OWNER + ", " + DbContract.CollaboratorTable.PROJECT_ID + ", " + DbContract.CollaboratorTable.STATUS + ") VALUES (" + member + ", " + owner + ", " + projectID + ", '" + false + "')");
    }
    //FIXED//
    public List<Project> getInvitations(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        Cursor cursor1 = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.CollaboratorTable.PROJECT_ID + ", " + DbContract.CollaboratorTable.OWNER + " FROM " + DbContract.CollaboratorTable.TABLE_NAME + " WHERE " + DbContract.CollaboratorTable.STATUS + " = 'false' AND " + DbContract.CollaboratorTable.MEMBER + " = " + userID, null);

            int IDCol = cursor.getColumnIndex(DbContract.CollaboratorTable.PROJECT_ID);
            int ownerCol = cursor.getColumnIndex(DbContract.CollaboratorTable.OWNER);

            String projName = "";

            while(cursor.moveToNext()) {
                int projID = cursor.getInt(IDCol);
                int ownerID = cursor.getInt(ownerCol);

                cursor1 = db.rawQuery("SELECT " + DbContract.ProjectTable.NAME + " FROM " + DbContract.ProjectTable.TABLE_NAME + " WHERE " + DbContract.ProjectTable._ID + " = " + projID, null);

                int nameCol = cursor1.getColumnIndex(DbContract.ProjectTable.NAME);

                while(cursor1.moveToNext()) {
                    projName = cursor1.getString(nameCol);
                }

                Project invitation = new Project(projID, ownerID, projName);
                projectList.add(invitation);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return projectList;
    }

    public String getProjectName(int projID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String projName = "";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.ProjectTable.NAME + " FROM " + DbContract.ProjectTable.TABLE_NAME + " WHERE " + DbContract.ProjectTable._ID + " = '" + projID + "'", null);

            int nameCol = cursor.getColumnIndex(DbContract.ProjectTable.NAME);

            while(cursor.moveToNext()) {
                String name = cursor.getString(nameCol);

                projName = name;
            }

            return projName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return projName;
    }

    public void rejectInvitation(int projID, int userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE " + DbContract.CollaboratorTable.TABLE_NAME + " SET " + DbContract.CollaboratorTable.STATUS + " = 'rejected' WHERE " + DbContract.CollaboratorTable.PROJECT_ID + " = '" + projID + "' AND " + DbContract.CollaboratorTable.MEMBER + " = '" + userID + "'");
    }

    public void acceptInvitation(int projID, int userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE " + DbContract.CollaboratorTable.TABLE_NAME + " SET " + DbContract.CollaboratorTable.STATUS + " = 'accepted' WHERE " + DbContract.CollaboratorTable.PROJECT_ID + " = '" + projID + "' AND " + DbContract.CollaboratorTable.MEMBER + " = '" + userID + "'");
    }

    public void createProfile(Profile nProfile, int userID) {
            SQLiteDatabase db = this.getWritableDatabase();

            String fName = nProfile.getFirst_name();
            String lName = nProfile.getLast_name();
            String location = nProfile.getLocation();
            String occupation = nProfile.getOccupation();

            db.execSQL("INSERT INTO " + DbContract.ProfileTable.TABLE_NAME + " (" + DbContract.ProfileTable.FIRST_NAME + ", " + DbContract.ProfileTable.LAST_NAME + ", " + DbContract.ProfileTable.LOCATION + ", " +  DbContract.ProfileTable.OCCUPATION +  ", " + DbContract.ProfileTable.USER_ID + ") " +
                    "VALUES ('" + fName + "', '" + lName + "', '" + location + "', '" + occupation + "', " + userID + ")");

    }

    public Profile getProfile(int userID) {
        Profile failedProfile = new Profile("failedtoretrieve","failedtoretrieve","failedtoretrieve","failedtoretrieve");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.ProfileTable.FIRST_NAME + ", " + DbContract.ProfileTable.LAST_NAME + ", " + DbContract.ProfileTable.LOCATION + ", " + DbContract.ProfileTable.OCCUPATION + " FROM " + DbContract.ProfileTable.TABLE_NAME + " WHERE " + DbContract.ProfileTable.USER_ID + " = " + userID, null);

            int fnameCol = cursor.getColumnIndex(DbContract.ProfileTable.FIRST_NAME);
            int lnameCol = cursor.getColumnIndex(DbContract.ProfileTable.LAST_NAME);
            int locationCol = cursor.getColumnIndex(DbContract.ProfileTable.LOCATION);
            int occupationCol = cursor.getColumnIndex(DbContract.ProfileTable.OCCUPATION);

            while(cursor.moveToNext()) {

                String fname = cursor.getString(fnameCol);
                String lname = cursor.getString(lnameCol);
                String location = cursor.getString(locationCol);
                String occupation = cursor.getString(occupationCol);

                Profile retrievedProfile = new Profile(fname, lname, location, occupation);
                //   Log.d(TAG, "getProfile: " + fname);
                return retrievedProfile;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return failedProfile;
    }

    public void editProfile(Profile profile, int userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String fname = profile.getFirst_name();
        String lname = profile.getLast_name();
        String location = profile.getLocation();
        String occupation = profile.getOccupation();

        db.execSQL("UPDATE " + DbContract.ProfileTable.TABLE_NAME + " SET " + DbContract.ProfileTable.FIRST_NAME+ " = '" + fname + "' WHERE " + DbContract.ProfileTable.USER_ID + " = '" + userID + "'");
        db.execSQL("UPDATE " + DbContract.ProfileTable.TABLE_NAME + " SET " + DbContract.ProfileTable.LAST_NAME+ " = '" + lname + "' WHERE " + DbContract.ProfileTable.USER_ID + " = '" + userID + "'");
        db.execSQL("UPDATE " + DbContract.ProfileTable.TABLE_NAME + " SET " + DbContract.ProfileTable.LOCATION+ " = '" + location + "' WHERE " + DbContract.ProfileTable.USER_ID + " = '" + userID + "'");
        db.execSQL("UPDATE " + DbContract.ProfileTable.TABLE_NAME + " SET " + DbContract.ProfileTable.OCCUPATION+ " = '" + occupation + "' WHERE " + DbContract.ProfileTable.USER_ID + " = '" + userID + "'");
    }
    //FIXED//
    public List<Project> getFeaturedProjects(int id1, int id2, int id3) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.ProjectTable._ID + ", " + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.CATEGORY + " FROM " + DbContract.ProjectTable.TABLE_NAME + " WHERE " + DbContract.ProjectTable._ID + " = " + id1 + " OR " + DbContract.ProjectTable._ID + " = " + id2 + " OR " + DbContract.ProjectTable._ID + " = " + id3, null);

            int IDCol = cursor.getColumnIndex(DbContract.ProjectTable._ID);
            int nameCol = cursor.getColumnIndex(DbContract.ProjectTable.NAME);
            int companyCol = cursor.getColumnIndex(DbContract.ProjectTable.COMPANY);
            int categoryCol = cursor.getColumnIndex(DbContract.ProjectTable.CATEGORY);


            while (cursor.moveToNext()) {
                int ID = cursor.getInt(IDCol);
                String name = cursor.getString(nameCol);
                String company = cursor.getString(companyCol);
                String category = cursor.getString(categoryCol);

                Project retreivedProj = new Project(ID, name, company, category);
                projectList.add(retreivedProj);
            }

            return projectList;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return projectList;
    }

    public int getNumberProjects() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        int number = 0;
        try {
            cursor = db.rawQuery("SELECT * FROM " + DbContract.ProjectTable.TABLE_NAME, null);

//            while(cursor.moveToNext()) {
                number = cursor.getCount();
//            }
            return number;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return number;
    }

    public void generateAddSkill(int userId){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + DbContract.SkillTable.TABLE_NAME + " (" + DbContract.SkillTable.NAME + ", " + DbContract.SkillTable.DESCRIPTION + ", " + DbContract.SkillTable.USER_ID + ", " + DbContract.SkillTable.DUMMY + ") VALUES ('Add Skill+', 'Add Skill+', " + userId + ", 1)");
    }

    public List<Project> getAllProjects() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT " + DbContract.ProjectTable._ID + ", " + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.CATEGORY + " FROM " + DbContract.ProjectTable.TABLE_NAME, null);

            int idCol = cursor.getColumnIndex(DbContract.ProjectTable._ID);
            int nameCol = cursor.getColumnIndex(DbContract.ProjectTable.NAME);
            int fundingCol = cursor.getColumnIndex(DbContract.ProjectTable.FUNDING);
            int companyCol = cursor.getColumnIndex(DbContract.ProjectTable.COMPANY);
            int countryCol = cursor.getColumnIndex(DbContract.ProjectTable.COUNTRY);
            int descriptionCol = cursor.getColumnIndex(DbContract.ProjectTable.DESCRIPTION);
            int progressCol = cursor.getColumnIndex(DbContract.ProjectTable.PROGRESS);
            int categoryCol = cursor.getColumnIndex(DbContract.ProjectTable.CATEGORY);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idCol);
                String name = cursor.getString(nameCol);
                String funding = cursor.getString(fundingCol);
                String company = cursor.getString(companyCol);
                String country = cursor.getString(countryCol);
                String description = cursor.getString(descriptionCol);
                String progress = cursor.getString(progressCol);
                String category = cursor.getString(categoryCol);
                Project retreivedProj = new Project(name, funding, company, country, description, progress, category);
                projectList.add(retreivedProj);
            }

            return projectList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return projectList;
    }

//    public List<Project> getProjectList() {
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = null;
//        try {
//            cursor = db.rawQuery("SELECT " + DbContract.ProjectTable._ID + ", " + DbContract.ProjectTable.NAME + ", " + DbContract.ProjectTable.FUNDING + ", " + DbContract.ProjectTable.COMPANY + ", " + DbContract.ProjectTable.COUNTRY + ", " + DbContract.ProjectTable.DESCRIPTION + ", " + DbContract.ProjectTable.PROGRESS + ", " + DbContract.ProjectTable.CATEGORY + " FROM " + DbContract.ProjectTable.TABLE_NAME, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            cursor.close();
//        }
//    }

}
