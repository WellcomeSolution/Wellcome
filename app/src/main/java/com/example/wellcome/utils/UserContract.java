package com.example.wellcome.utils;

import android.provider.BaseColumns;

public class UserContract {
    public UserContract() { }

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_FIRST_NAME = "firstName";
        public static final String COLUMN_NAME_LAST_NAME = "lastName";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_BIRTH_DATE = "birthDate";
        public static final String COLUMN_NAME_GENDER = "gender";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserEntry.COLUMN_NAME_FIRST_NAME + " TEXT," +
                    UserEntry.COLUMN_NAME_LAST_NAME + " TEXT," +
                    UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    UserEntry.COLUMN_NAME_BIRTH_DATE + " TEXT," +
                    UserEntry.COLUMN_NAME_GENDER + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
}
