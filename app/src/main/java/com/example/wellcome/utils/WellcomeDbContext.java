package com.example.wellcome.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.wellcome.enums.Gender;

public class WellcomeDbContext {
    private WellcomeDbHelper dbHelper;

    public WellcomeDbContext(Context context)
    {
        dbHelper = new WellcomeDbHelper(context);
    }



    public long insertUser(User user){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_NAME_FIRST_NAME, user.getFirstName());
        values.put(UserContract.UserEntry.COLUMN_NAME_LAST_NAME, user.getLastName());
        values.put(UserContract.UserEntry.COLUMN_NAME_EMAIL user.getEmail());
        values.put(UserContract.UserEntry.COLUMN_NAME_BIRTH_DATE, user.getBirthDate().toString());
        values.put(UserContract.UserEntry.COLUMN_NAME_GENDER, user.getGender().toString());

        return db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
    }

    public User getUserLogin(String email){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                UserContract.UserEntry.COLUMN_NAME_FIRST_NAME,
                UserContract.UserEntry.COLUMN_NAME_LAST_NAME,
                UserContract.UserEntry.COLUMN_NAME_EMAIL,
                UserContract.UserEntry.COLUMN_NAME_BIRTH_DATE,
                UserContract.UserEntry.COLUMN_NAME_GENDER
        };

        String selection = UserContract.UserEntry.COLUMN_NAME_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null
        );

        cursor.moveToFirst();

        long id = cursor.getLong(
                cursor.getColumnIndexOrThrow(UserContract.UserEntry._ID));
        String firstName = cursor.getString(
                cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_FIRST_NAME));
        String lastName = cursor.getString(
                cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_LAST_NAME));
        String birthDate = cursor.getString(
                cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_BIRTH_DATE));
        String gender = cursor.getString(
                cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_BIRTH_DATE));

        return new User(id, firstName, lastName, email, DateParser.parse(birthDate), Gender.valueOf(gender));
    }


}
