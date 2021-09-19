package com.example.wellcome.utils;

import android.provider.BaseColumns;

public class HostingContract {
    public HostingContract() { }

    public static class HostingEntry implements BaseColumns {
        public static final String TABLE_NAME = "hosting";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STREET = "street";
        public static final String COLUMN_NAME_ADDITIONAL_ADDRESS = "additionalAddress";
        public static final String COLUMN_NAME_HOSTER_EMAIL = "hosterEmail";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HostingEntry.TABLE_NAME + " (" +
                    HostingEntry._ID + " INTEGER PRIMARY KEY," +
                    HostingEntry.COLUMN_NAME_COUNTRY + " TEXT," +
                    HostingEntry.COLUMN_NAME_CITY + " TEXT," +
                    HostingEntry.COLUMN_NAME_STREET + " TEXT," +
                    HostingEntry.COLUMN_NAME_ADDITIONAL_ADDRESS + " TEXT," +
                    HostingEntry.COLUMN_NAME_HOSTER_EMAIL + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HostingContract.HostingEntry.TABLE_NAME;
}
