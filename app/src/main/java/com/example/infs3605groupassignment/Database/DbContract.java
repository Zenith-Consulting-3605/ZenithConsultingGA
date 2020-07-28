package com.example.infs3605groupassignment.Database;

import android.provider.BaseColumns;

import java.sql.Date;

public final class DbContract {

    private DbContract(){}

    public static class UsersTable implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        //public static final String USER_TYPE = "user_type";
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
        public static final String USER_ID = "user_id"; //CHECK THIS IF RETRIEVING USER_IDS AS FOREIGN KEYS POSE PROBLEMS
    }

    public static class SkillTable implements BaseColumns {
        public static final String TABLE_NAME = "skill";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String DUMMY = "dummy";
        public static final String USER_ID = "user_id"; //CHECK THIS IF RETRIEVING USER_IDS AS FOREIGN KEYS POSE PROBLEMS
    }

    public static class ProjectTable implements BaseColumns {
        public static final String TABLE_NAME = "project";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String FUNDING = "funding";
        public static final String CATEGORY = "category";
        public static final String PROGRESS = "progress";
        public static final String COUNTRY = "country";
        public static final String COMPANY = "company";
        public static final String OWNER = "owner"; //CHECK THIS IF RETRIEVING USER_IDS AS FOREIGN KEYS POSE PROBLEMS
    }

    public static class CollaboratorTable implements BaseColumns {
        public static final String TABLE_NAME = "collaborator";
        public static final String MEMBER = "member";
        public static final String OWNER = "user_id";
        public static final String PROJECT_ID = "project_id";
    }




}
