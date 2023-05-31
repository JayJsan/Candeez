package com.application.project2java;

import android.app.Application;
import android.content.Context;

import com.application.project2java.database.DataMutator;
import com.application.project2java.database.DataProvider;
import com.application.project2java.database.DatabaseHelper;

public class App extends Application {
    private static Context context;
    private static DataProvider dataProvider;
    private static DataMutator dataMutator;

    public static Context getAppContext() {
        return App.context;
    }

    public static DataProvider getDataProvider() {
        return dataProvider;
    }

    public static DataMutator getDataMutator() {
        return dataMutator;
    }

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        databaseHelper.copyDatabase();
        dataProvider = new DataProvider(context);
        dataMutator = new DataMutator(context);
    }

}
