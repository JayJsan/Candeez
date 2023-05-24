package com.application.project2java;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;
    private static DataProvider dataProvider;
    private static DataMutator dataMutator;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        databaseHelper.copyDatabase();
        dataProvider = new DataProvider(context);
        dataMutator = new DataMutator(context);
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static DataProvider getDataProvider() {
        return dataProvider;
    }

    public static DataMutator getDataMutator() {
        return dataMutator;
    }

}
