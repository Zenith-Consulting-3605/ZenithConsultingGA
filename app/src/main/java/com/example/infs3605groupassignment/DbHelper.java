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

        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Champ', 'Full-Time', 'Champion', 'Sydney, Australia', '12/11/2009', '12/04/2008', 'Worked at Champion like an absolute Champ', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('BigLad', 'Full-Time', 'Champion', 'Sydney, Australia', '12/12/2010', '15/04/2012', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Chimp', 'Full-Time', 'Champion', 'Sydney, Australia', '12/12/2020', '15/04/2022', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('CBA Assistant', 'Full-Time', 'Champion', 'Sydney, Australia', '12/12/2030', '15/04/2042', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Hello', 'Full-Time', 'Champion', 'Sydney, Australia', '12/12/2110', '15/04/2112', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('Im out of words', 'Full-Time', 'Champion', 'Sydney, Australia', '15/12/2010', '16/04/2012', 'Was a BigLad', 1)");
        db.execSQL("INSERT INTO " + DbContract.ExperienceTable.TABLE_NAME + " ( " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.EMPLOYMENT_TYPE + ", " + DbContract.ExperienceTable.COMPANY + ", " + DbContract.ExperienceTable.LOCATION + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + ", " + DbContract.ExperienceTable.DESCRIPTION + ", " + DbContract.ExperienceTable.USER_ID + ") VALUES ('YEE', 'Full-Time', 'Champion', 'Sydney, Australia', '20/12/2010', '11/04/2012', 'Was a BigLad', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.UsersTable.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + DbContract.ProfileTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.ExperienceTable.TABLE_NAME);

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





    public List<Experience> getExperiences() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + DbContract.ExperienceTable.TITLE + ", " + DbContract.ExperienceTable.START_DATE + ", " + DbContract.ExperienceTable.END_DATE + " FROM " + DbContract.ExperienceTable.TABLE_NAME + " WHERE " + DbContract.ExperienceTable.USER_ID + " = " + 1, null);

                int titleCol = cursor.getColumnIndex(DbContract.ExperienceTable.TITLE);
                int startCol = cursor.getColumnIndex(DbContract.ExperienceTable.START_DATE);
                int endCol = cursor.getColumnIndex(DbContract.ExperienceTable.END_DATE);

                while (cursor.moveToNext()) {
                    String title = cursor.getString(titleCol);
                    String start = cursor.getString(startCol);
                    String end = cursor.getString(endCol);
                    Experience retrievedExp = new Experience(title, start, end);
                    experienceList.add(retrievedExp);
                }

        return experienceList;
    }
}
