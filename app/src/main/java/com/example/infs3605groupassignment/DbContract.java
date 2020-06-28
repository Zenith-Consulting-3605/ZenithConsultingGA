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
        public static final String TABLE_NAME = "users";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
    }





}
