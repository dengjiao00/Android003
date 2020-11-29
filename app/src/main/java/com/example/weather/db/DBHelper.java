package com.example.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Create By Dengjiao
 * on 2020.11.02
 */
public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context){
        super(context,"myforecast.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        创建表的操作
        String sql = "create table info(_id integer primary key autoincrement,city varchar(20) unique not null,content text not null)";
        String sql1 = "create table info1(_id integer primary key autoincrement,city varchar(20) unique not null,content text not null)";

        db.execSQL(sql);
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
