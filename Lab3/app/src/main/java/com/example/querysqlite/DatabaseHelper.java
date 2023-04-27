package com.example.querysqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DBName = "DBINFO";
    public static final int DBVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    public void QueryData (String sqlData) {
        SQLiteDatabase getData = getWritableDatabase();
        getData.execSQL(sqlData);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE USER(fullname String, number String )";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS USER";
        db.execSQL(sql);
        onCreate(db);
    }
}