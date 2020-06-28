package com.example.infs3605groupassignment;

import android.provider.BaseColumns;

public final class DbContract {

    private DbContract(){}

    public static class UsersTable implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
    }

    public static class ProfileTable implements BaseColumns{
        public static final String TABLE_NAME = "profile";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String OCCUPATION = "occupation";
        public static final String LOCATION = "location";
    }

    public static class ExperienceTable implements BaseColumns {
        public static final String TABLE_NAME = "experience";
        public static final String TITLE = "title";
        public static final String EMPLOYMENT_TYPE = "employment_type";
        public static final String COMPANY = "company";
        public static final String LOCATION = "location";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
        public static final String DESCRIPTION = "description";
        public static final String USER_ID = "user_id";
    }



}
