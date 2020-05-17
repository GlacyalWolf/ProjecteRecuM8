package com.example.projecterecum8;

import android.app.Application;

import com.example.projecterecum8.Data.Repository;

public class DirectorioApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Repository.get(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
