package com.example.infs3605groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User.db";
    private SQLiteDatabase db;

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
                DbContract.UsersTable.TABLE_NAME + "( " +
                DbContract.UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContract.UsersTable.EMAIL + " TEXT, " +
                DbContract.UsersTable.PASSWORD + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.UsersTable.TABLE_NAME);
        onCreate(db);
    }
}
