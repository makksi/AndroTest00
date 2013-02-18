package com.makksi.androtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME= "nome db";
    private static final int DB_VERSION = 1;
    
    public MyDatabaseHelper(Context context){
    	super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate (SQLiteDatabase db){
    	String sql="";
    	sql += "CREATE TABLE agenda (";
    	sql += " _id INTEGER PRIMARY KEY,";
    	sql += " nome TEXT NOT NULL,";
    	sql += " cognome TEXT NOT NULL,";    	
    	sql += " telefono TEXT NOT NULL,";   
    	sql += ")";
    	db.execSQL(sql);
     }
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
    	
    }
}
