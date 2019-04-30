package com.engineering.season.hisabappdroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper_Balance extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    private static final String CREATE_TABLE = "create table "+DbContract.TABLE_NAME+
            "( id integer primary key autoincrement,"+
            DbContract.BALANCE+" text);";
    private static final String DROP_TABLE = "drop table if exists "+DbContract.TABLE_NAME;
    public DbHelper_Balance(Context context){
        super(context,DbContract.DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public void saveToLocalDatabase( String balance, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.BALANCE,balance);
        database.insert(DbContract.TABLE_NAME,null,contentValues);
    }
    public Cursor readFromLocalDatabase (SQLiteDatabase database){
        String[] projection = {DbContract.BALANCE};
        return (database.query(DbContract.TABLE_NAME,projection,null,null,null,null,null));

    }
    public void dropandresetsqlite(SQLiteDatabase db){
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}