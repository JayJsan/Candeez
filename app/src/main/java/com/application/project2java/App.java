package com.application.project2java;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;


    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        databaseHelper.copyDatabase();
    }

    public static Context getAppContext() {
        return App.context;
    }


}
