package com.example.weather.base;
/**
 * Create By Dengjiao
 * on 2020.11.02
 */
import android.app.Application;

import com.example.weather.db.DBManager;

import org.xutils.x;

public class UniteApp extends Application {
//全局声明
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        DBManager.initDB(this);
    }
}
